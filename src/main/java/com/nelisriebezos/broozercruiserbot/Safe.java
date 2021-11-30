package com.nelisriebezos.broozercruiserbot;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Safe {
    public static Secrets getSecrets() {
        String sfile = "secrets/secrets.json";
        try {
            File secretsFile = new File(sfile);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(secretsFile, Secrets.class);
        } catch (Exception e) {
            throw new RuntimeException("Secrets file " + sfile + " not found");
        }
    }


//    public static Secrets getDbPassword() {
//        String sfile = "secrets/dpPassword.json";
//        try {
//            File secretsFile = new File(sfile);
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(secretsFile, Secrets.class);
//        } catch (Exception e) {
//            throw new RuntimeException("Secrets file " + sfile + " not found");
//        }
//    }
}
