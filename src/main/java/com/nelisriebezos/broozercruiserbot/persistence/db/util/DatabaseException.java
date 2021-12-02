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
package com.nelisriebezos.broozercruiserbot.persistence.db.util;

import com.nelisriebezos.broozercruiserbot.Exceptions.CruiserException;

public class DatabaseException extends CruiserException {
  private static final long serialVersionUID = 1L;

  public DatabaseException(Exception e) {
    super(e);
  }

  public DatabaseException(String message) {
    super(message);
  }

  public DatabaseException(String message, Throwable e) {
    super(message, e);
  }

}
