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
package com.nelisriebezos.broozercruiserbot.persistence.util;

import com.nelisriebezos.broozercruiserbot.persistence.util.impl.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseIdiomFactory {
  public static final String H2 = "h2";
  public static final String HSQLDB = "hsqldb";
  public static final String ORACLE = "oracle";
  public static final String POSTGRESQL = "postgresql";
  public static final String POSTGRESQL2 = "postgress";
  public static final String MYSQL = "mysql";
  public static final String DERBY = "derby";

  private DatabaseIdiomFactory() {
  }

  public static DatabaseIdiom getDatabaseIdiom(Connection conn) throws SQLException {
    String name = conn.getMetaData().getDriverName();

    return getDatabaseIdiom(name);
  }

  public static DatabaseIdiom getDatabaseIdiom(String name) {
    DatabaseIdiom result;
    if (name.toLowerCase().contains(DERBY))
      result = new DerbyDatabaseIdiom();
    else if (name.toLowerCase().contains(MYSQL))
      result = new MySQLDatabaseIdiom();
    else if (name.toLowerCase().contains(POSTGRESQL))
      result = new PostgreSqlDatabaseIdiom();
    else if (name.toLowerCase().contains(POSTGRESQL2))
      result = new PostgreSqlDatabaseIdiom();
    else if (name.toLowerCase().contains(ORACLE))
      result = new OracleDatabaseIdiom();
    else if (name.toLowerCase().contains(HSQLDB))
      result = new HSQLDatabaseIdiom();
    else if (name.toLowerCase().contains(H2))
      result = new H2DatabaseIdiom();
    else
      result = new GenericDatabaseIdiom();
    return result;
  }
}
