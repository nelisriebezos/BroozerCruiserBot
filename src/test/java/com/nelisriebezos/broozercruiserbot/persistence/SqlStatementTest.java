package com.nelisriebezos.broozercruiserbot.persistence;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SqlStatementTest extends DatabaseTest {

  @Test
  public void testSqlStatement() throws SQLException, DatabaseException, IOException {

    CruiserDB cruiserDB = getCruiserDB();

    try (Connection connection = cruiserDB.getConnection()) {
      SqlStatement stmt =
          new SqlStatement(connection, "select ':test' \"alias\", name, version from cruiser_version where name <> :name;");
      assertEquals(1, stmt.getParamCount());
      stmt.setString("name", "person");
      ResultSet rs = stmt.executeQuery();
      assertTrue(rs.next());
      stmt.close();
      String fancyStmt = stmt.fancyStmt();
      SqlStatement stmt2 = new SqlStatement(connection, fancyStmt);
      assertEquals(1, stmt2.getParamCount());
    }
  }
}