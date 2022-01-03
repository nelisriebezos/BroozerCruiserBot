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
package com.nelisriebezos.broozercruiserbot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Configuration {
  private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);
  public static final String CLASSPATH_PREFIX = "classpath:";

  private Properties properties = new Properties();
  private String propertyFileName;

  protected void clear() {
    properties = new Properties();
  }

  public void load(InputStream inStream) {
    loadDefaults();
    readInputStream(inStream);
  }

  /**
   * Override this method to set default before actually loading any properties
   */
  protected void loadDefaults() {

  }

  protected void readInputStream(InputStream inStream) {
    try {
      properties.load(inStream);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public String getPropertyFileName() {
    return propertyFileName;
  }

  public void setPropertyFileName(String propertyFileName) {
    this.propertyFileName = propertyFileName;
  }

  public String getValue(String key) {
    String value = getValue(key, null);
    if (value == null)
      LOG.error("Property " + key + " is not set in the configuration");
    return value;
  }

  public String getValue(String key, String dflt) {
    String result = null;
    Object value = properties.get(key);
    if (value != null) {
      result = String.valueOf(value).trim();
      if (result.length() == 0)
        result = null;
    }
    return result == null ? dflt : result;
  }

  protected Properties getProperties() {
    return properties;
  }

  public void reload() throws FileNotFoundException {
    clear();
    String propertyFileName = getPropertyFileName();
    InputStream inStream;
    if (propertyFileName.startsWith(CLASSPATH_PREFIX))
      inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFileName.substring(CLASSPATH_PREFIX.length()));
    else
      inStream = new FileInputStream(propertyFileName);

    if (inStream == null)
      throw new FileNotFoundException(propertyFileName);
    load(inStream);
  }

  public String getDatabaseUrl() {
    return getValue("database.url", null);
  }

  public String getSecretsFile() {
    return getValue("secrets.file", "secrets.json");
  }

  public String getDatabaseUser() {
    return getValue("database.user", null);
  }

  public String getDatabasePassword() {
    return getValue("database.password", null);
  }

  public String getDatabaseType() {
    return getValue("database.type", null);
  }

  public SimpleDateFormat getTimestampFormat() {
    return new SimpleDateFormat(getValue("formatmask.timestamp"));
  }

  public String getBotUserName(String simpleName) {
    return getValue("bot.username", simpleName);
  }
}
