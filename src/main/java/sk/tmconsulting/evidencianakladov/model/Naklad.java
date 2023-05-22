package sk.tmconsulting.evidencianakladov.model;

import java.io.Serializable;
import java.time.LocalDate;

/* Vytvarame model s nazvom Naklad. Terminologia pre konkretny model je aj POJO */
public class Naklad implements Serializable {
    private String nazov;
    private double cena;
    private Kategoria kategoria;
    private LocalDate datumZaznamu;

    // Prazdny konstruktor
    public Naklad() {
    }

    // Konstruktor so 4-mi parametrami, t.j. Definovany (parametricky) konstruktor
    public Naklad(String nazov, double cena, Kategoria kategoria, LocalDate datumZaznamu) {
        this.nazov = nazov;
        this.cena = cena;
        this.kategoria = kategoria;
        this.datumZaznamu = datumZaznamu;
    }

    public Naklad(String nazov, double cena, Kategoria kategoria) {
        this.nazov = nazov;
        this.cena = cena;
        this.kategoria = kategoria;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public LocalDate getDatumZaznamu() {
        return datumZaznamu;
    }

    public void setDatumZaznamu(LocalDate datumZaznamu) {
        this.datumZaznamu = datumZaznamu;
    }

    @Override
    public String toString() {
        return "Naklad{" +
                "nazov='" + nazov + '\'' +
                ", cena=" + cena +
                ", kategoria=" + kategoria +
                ", datumZaznamu=" + datumZaznamu +
                '}';
    }
}