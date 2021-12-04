package com.nelisriebezos.broozercruiserbot.persistence.util;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.util.Configuration;
import com.nelisriebezos.broozercruiserbot.util.CruiserUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class DatabaseTest {

    private File dbDir;
    CruiserEnvironment cruiserEnvironment;

    public CruiserDB getCruiserDB() throws DatabaseException, IOException {

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

    public CruiserEnvironment getCruiserEnvironment() {
        return cruiserEnvironment;
    }

    public void cleanupTempFolder() throws IOException {
        if (dbDir != null)
            cleanupTempFolder(dbDir);
    }

    protected void cleanupTempFolder(File dbDir) throws IOException {
        File tempFile = File.createTempFile("safe", "check");
        if (!tempFile.getParentFile().getAbsolutePath().equals(dbDir.getParentFile().getAbsolutePath())) {
            tempFile.delete();
            throw new IllegalArgumentException("Refuse to clean anything outside the tempfolder");
        }
        tempFile.delete();
        unsafeCleanupTempFolder(dbDir);
    }

    private void unsafeCleanupTempFolder(File dbDir) {
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
