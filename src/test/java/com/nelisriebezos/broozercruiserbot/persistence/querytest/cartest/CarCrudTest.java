package com.nelisriebezos.broozercruiserbot.persistence.querytest.cartest;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import com.nelisriebezos.broozercruiserbot.persistence.util.SequenceGenerator;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CarCrudTest extends DatabaseTest {

    @Test
    public void createTest() throws SQLException, DatabaseException, IOException {
        CruiserDB cruiserDB = getCruiserDB();

        try (Connection connection = cruiserDB.getConnection();
                SqlStatement statement =
                new SqlStatement(connection,
                        cruiserDB.getQueryString("car_create"))) {
            statement.set("id", gen.)
        }
    }

//    create / read (positive + negative)
//    update / read (positive + negative)
//    delete / read (positive + negative)
}
