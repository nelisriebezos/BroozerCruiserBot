/* Copyright (c) 2016 W.T.J. Riezebos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nelisriebezos.broozercruiserbot.persistence;

import com.nelisriebezos.broozercruiserbot.util.Configuration;
import com.nelisriebezos.broozercruiserbot.util.CruiserUtil;
import com.nelisriebezos.broozercruiserbot.util.FinalWrapper;
import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CruiserEnvironment {
  private static final Logger LOG = LoggerFactory.getLogger(CruiserEnvironment.class);
  public static final String CONFIGKEY = "cruiser_configuration";
  public static final String QUERIES = "com/nelisriebezos/broozercruiserbot/db/queries.txt";

  private static FinalWrapper<CruiserEnvironment> environmentWrapper = null;
  private FinalWrapper<CruiserDB> cruiserDBWrapper = null;
  private Configuration configuration;

  private Map<String, String> queries = null;


  public static CruiserEnvironment getEnvironment() {
    FinalWrapper<CruiserEnvironment> wrapper = environmentWrapper;
    if (wrapper == null) {
      synchronized (CruiserEnvironment.class) {
        wrapper = environmentWrapper;
        if (wrapper == null) {
          wrapper = new FinalWrapper<CruiserEnvironment>(new CruiserEnvironment());
        }
        environmentWrapper = wrapper;
      }
    }
    return wrapper.value;
  }

  /**
   * This method is synchronized to make sure there will ever only be one ThothDB for this environment.
   *
   * @return
   * @throws SQLException
   */
  public CruiserDB getCruiserDB() throws DatabaseException {
    FinalWrapper<CruiserDB> wrapper = cruiserDBWrapper;
    if (wrapper == null) {
      synchronized (this) {
        wrapper = cruiserDBWrapper;
        if (wrapper == null) {
          wrapper = new FinalWrapper<CruiserDB>(createCruiserDB());
        }
        cruiserDBWrapper = wrapper;
      }
    }
    return wrapper.value;
  }

  protected CruiserDB createCruiserDB() throws DatabaseException {
    CruiserDB thothDB = new CruiserDB(this);
    thothDB.init();
    LOG.info("Database initialized");
    return thothDB;
  }


  public Configuration getConfiguration() {
    try {
      if (configuration == null) {
        String propertyPath = determinePropertyPath();
        if (propertyPath == null) {
          String msg = "There is no configuration defined. Set either and environment or system property named '" + CONFIGKEY + "' i.e. -D" + CONFIGKEY
              + "=conf/environment.properties and restart";
          throw new IllegalArgumentException(msg);
        } else {
          LOG.info("Using " + propertyPath + " for configuration");
          Configuration config = new Configuration();
          config.setPropertyFileName(propertyPath);
          config.reload();
          setConfiguration(config);
        }
      }
      return configuration;
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public void setConfiguration(Configuration configuration) {

    this.configuration = configuration;
  }

  public String determinePropertyPath() {
    String propertyPath = System.getProperty(CONFIGKEY);
    if (propertyPath == null)
      propertyPath = System.getenv(CONFIGKEY);
    return propertyPath;
  }

  public static String getQueryString(String queryName) {
    return getEnvironment().doGetQueryString(queryName);
  }

  protected String doGetQueryString(String queryName) {
    if (queries == null) {
      queries = loadQueries();
    }
    String query = queries.get(queryName.toLowerCase());
    if (query == null)
      throw new IllegalArgumentException("Query " + queryName + " not defined");
    return query;
  }

  protected Map<String, String> loadQueries() {
    try {
      Map<String, String> map = new HashMap<>();
      String body = CruiserUtil.readInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(QUERIES));
      if(body == null) throw new RuntimeException("Cannot find "+QUERIES);
      for (String line : body.split(";")) {
        if (!StringUtils.isBlank(line)) {
          int idx = line.indexOf('=');
          if (idx == -1)
            throw new IllegalArgumentException("Line " + line + " does not contain the '=' character");
          String name = line.substring(0, idx).trim();
          String query = line.substring(idx + 1).trim();
          if (map.containsKey(name))
            throw new IllegalArgumentException("Query named " + name + " not unique");
          map.put(name.toLowerCase(), query);
        }
      }
      return map;
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

}
