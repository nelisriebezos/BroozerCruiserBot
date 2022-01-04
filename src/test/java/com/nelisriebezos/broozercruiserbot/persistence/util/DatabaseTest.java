package com.nelisriebezos.broozercruiserbot.persistence.util;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.*;
import com.nelisriebezos.broozercruiserbot.domain.service.dao.*;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.util.impl.DDLException;
import com.nelisriebezos.broozercruiserbot.util.Configuration;
import com.nelisriebezos.broozercruiserbot.util.CruiserUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Properties;


public class DatabaseTest {
    private static File dbDir;
    static CruiserEnvironment cruiserEnvironment;
    private static final String TESTDATA_SCRIPT = "com/nelisriebezos/broozercruiserbot/db/test_fill_db.ddl";

    private static CruiserDB cruiserDB;
    protected Connection connection;
    protected CarService carService;
    protected PersonService personService;
    protected TripService tripService;
    protected TankSessionService tankSessionService;
    protected Car car;
    protected Person person;
    protected TankSession tankSession;
    protected Trip trip;
    protected Date date1;
    protected Date date2;

    @BeforeAll
    public static void setupDatabase() throws IOException, DatabaseException, SQLException, DDLException {
        cruiserDB = getCruiserDB();

        Connection connection = cruiserDB.getConnection();
        DatabaseIdiom idiom = DatabaseIdiomFactory.getDatabaseIdiom(connection);
        DDLExecuter executer = new DDLExecuter(connection, idiom);
        executer.execute(TESTDATA_SCRIPT);
        connection.commit();
        connection.close();
    }

    @BeforeEach
    public void init() throws SQLException {
        connection = cruiserDB.getConnection();
        carService = new CarService(connection);
        personService = new PersonService(connection);
        tankSessionService = new TankSessionService(connection);
        tripService = new TripService(connection);
        carService.setTankSessionService(tankSessionService);
        tankSessionService.setTripService(tripService);
        tripService.setPersonService(personService);
        tripService.setTankSessionService(tankSessionService);
        personService.setTripService(tripService);
        car = new Car();
        person = new Person();
        tankSession = new TankSession();
        trip = new Trip();
        date1 = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime();
        date2 = new GregorianCalendar(2001, Calendar.JANUARY, 1).getTime();
    }

    @AfterEach
    public void close() throws SQLException {
        connection.close();
    }

    @AfterAll
    public static void closeDatabase() throws IOException {
        cruiserDB.shutdown();
        cleanupTempFolder();

        cruiserDB = null;
        dbDir = null;
    }

    public static CruiserDB getCruiserDB() throws DatabaseException, IOException {

        dbDir = File.createTempFile("cruiser", "db");
        dbDir.delete();
        String databaseFolder = CruiserUtil.normalSlashes(dbDir.getAbsolutePath());

        Properties props = new Properties();
        props.setProperty("database.type", "embedded");
        props.setProperty("database.url", databaseFolder);
        props.setProperty("database.user", "cruiser");
        props.setProperty("database.password", "cruiser");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        props.store(bos, "");

        Configuration configuration = new Configuration();
        configuration.load(new ByteArrayInputStream(bos.toByteArray()));

        cruiserEnvironment = new CruiserEnvironment();
        cruiserEnvironment.setConfiguration(configuration);

        CruiserDB cruiserDB = cruiserEnvironment.getCruiserDB();
        cruiserDB.init();
        return cruiserDB;
    }

    private static void cleanupTempFolder() throws IOException {
        if (dbDir != null)
            cleanupTempFolder(dbDir);
    }

    private static void cleanupTempFolder(File dbDir) throws IOException {
        File tempFile = File.createTempFile("safe", "check");
        if (!tempFile.getParentFile().getAbsolutePath().equals(dbDir.getParentFile().getAbsolutePath())) {
            tempFile.delete();
            throw new IllegalArgumentException("Refuse to clean anything outside the tempfolder");
        }
        tempFile.delete();
        unsafeCleanupTempFolder(dbDir);
    }

    private static void unsafeCleanupTempFolder(File dbDir) {
        if (dbDir.isDirectory()) {
            File[] listFiles = dbDir.listFiles();
            if (listFiles != null)
                for (File child : listFiles)
                    unsafeCleanupTempFolder(child);
            dbDir.delete();
        } else if (dbDir.isFile())
            dbDir.delete();
    }
}
