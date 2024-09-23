package com.patrimoine;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

public class Patrimoine {
    private String possesseur;
    private LocalDateTime derniereModification;

    public Patrimoine() {
    }

    public Patrimoine(String possesseur, LocalDateTime derniereModification) {
        this.possesseur = possesseur;
        this.derniereModification = derniereModification;
    }

    public String getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(String possesseur) {
        this.possesseur = possesseur;
    }

    public LocalDateTime getDerniereModification() {
        return derniereModification;
    }

    public void setDerniereModification(LocalDateTime derniereModification) {
        this.derniereModification = derniereModification;
    }

    // Convertit un objet Patrimoine en JSON
    public String toJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    // Convertit une chaîne JSON en objet Patrimoine
    public static Patrimoine fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Patrimoine.class);
    }
}
