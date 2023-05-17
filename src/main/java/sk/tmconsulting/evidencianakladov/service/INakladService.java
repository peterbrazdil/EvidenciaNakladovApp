package sk.tmconsulting.evidencianakladov.service;
// Interface je superclass
import sk.tmconsulting.evidencianakladov.model.Naklad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

// Tvorba interface tj rozhrania, resp prototypu=konceptu triedy
public interface INakladService {
    void uloz(Naklad naklad);
    void uprav(Naklad naklad);
    void zmaz(Naklad naklad);
    void zobraz(Naklad naklad);
    void zobrazVsetky(ArrayList<Naklad> zoznamNakladov);
    double spocitajNakladyDatumuOdDo(LocalDate datumOd, LocalDate datumDo, ArrayList<Naklad>zoznamNakladov); // Ziskame celkovy sucet nakladov
    void ulozDoSuboru(ArrayList<Naklad> zoznamNakladov, String nazovSuboru) throws IOException; // Ukladame do suboru Arraylist cez nazovSuboru
    ArrayList<Naklad> otvorZoSuboru(String nazovSuboru) throws IOException, ClassNotFoundException; // Navratovy typ musi byt Arraylist<Naklad> lebo nacitame ulozeny zoznam, cize Arraylist
    void export2PDF(ArrayList<Naklad>zoznamNakladov);


}
