package com.nelisriebezos.broozercruiserbot;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Safe {
    public static Secrets getApiKeys() {
        String sfile = "secrets/secrets.json";
        try {
            File secretsFile = new File(sfile);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(secretsFile, Secrets.class);
        } catch (Exception e) {
            throw new RuntimeException("com.nelisriebezos.broozercruiserbot.Secrets file " + sfile + " not found");
        }
    }
}