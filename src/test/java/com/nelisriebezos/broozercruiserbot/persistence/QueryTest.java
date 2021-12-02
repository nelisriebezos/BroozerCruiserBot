package com.nelisriebezos.broozercruiserbot.persistence;

import com.nelisriebezos.broozercruiserbot.persistence.db.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.db.util.DatabaseException;
import com.nelisriebezos.broozercruiserbot.persistence.db.util.SqlStatement;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueryTest extends DatabaseTest {

    @Test
    public void testSqlStatement() throws SQLException, DatabaseException, IOException {

        CruiserDB cruiserDB = getCruiserDB();

        try (Connection connection = cruiserDB.getConnection()) {
            SqlStatement stmt =
                    new SqlStatement(connection, "select name, version from cruiser_version");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            stmt.close();
        } finally {
            cleanupTempFolder();
        }
    }
}
