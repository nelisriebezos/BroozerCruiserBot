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


import com.nelisriebezos.broozercruiserbot.persistence.util.impl.TranslationPair;

import java.util.List;
import java.util.Map;

public interface DatabaseIdiom {

  enum DatabaseFlavour {
    OTHER, DERBY, POSTGRES, ORACLE, MYSQL, H2, HSQLDB
  }

  String getTableName(String tableName);

  String getCurrentTimeDDL();

  String applyDialect(String command);

  void initDialect(List<TranslationPair> translations, List<TranslationPair> workarounds);

  String getCurrentTime();

  String getDatabaseEntity(String name);

  Map<String, String> getTranslations();

  DatabaseFlavour getFlavour();

  String getDefaultIndexType();

  String getTypeName(int type);

}
