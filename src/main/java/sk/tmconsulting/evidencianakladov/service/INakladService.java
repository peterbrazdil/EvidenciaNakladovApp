package sk.tmconsulting.evidencianakladov.service;

import sk.tmconsulting.evidencianakladov.model.Naklad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/* Tvorba interface (rozhrania), resp. prototypu triedy */
public interface INakladService {
    void uloz(Naklad naklad);
    void uprav(Naklad naklad);
    void zmaz(Naklad naklad);
    void zobraz(Naklad naklad);
    void zobrazVsetky(ArrayList<Naklad> zoznamNakladov);
    double spocitajNakladyPodlaDatumuOdDo(LocalDate datumOd, LocalDate datumDo, ArrayList<Naklad> zoznamNakladov); // Ziskame celkovy sucet nakladov
    void ulozDoSuboru(ArrayList<Naklad> zoznamNakladov, String nazovSuboru) throws IOException; // Ukladame do suboru ArrayList cez nazovSuboru
    ArrayList<Naklad> otvorZoSuboru(String nazovSuboru) throws IOException, ClassNotFoundException; // Navratovy typ musi byt ArrayList<Naklad> lebo nacitame ulozeny zoznam, cize ArrayList
    void export2PDF(ArrayList<Naklad> zoznamNakladov);
}