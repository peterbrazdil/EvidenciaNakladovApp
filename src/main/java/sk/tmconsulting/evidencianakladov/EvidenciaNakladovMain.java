package sk.tmconsulting.evidencianakladov;

import sk.tmconsulting.evidencianakladov.model.Kategoria;
import sk.tmconsulting.evidencianakladov.model.Naklad;
import sk.tmconsulting.evidencianakladov.service.NakladService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EvidenciaNakladovMain {
    public static void main(String[] args) {
    // Vytvorime si objekt s nazvom naklad1
        Naklad naklad1 = new Naklad();
        naklad1.setNazov("Nákup potravin");
        naklad1.setCena(19.60);
        naklad1.setKategoria(Kategoria.POTRAVINY);
        naklad1.setDatumZaznamu(LocalDate.now()); // Naplnili sme datumZaznamu aktualnym datumom

        // Vytvorime si dalsi objekt s nazvom naklad2 a naplnime ho priamo cez konstruktor- musime vypisat vsetky argumenty
        Naklad naklad2 = new Naklad("Benzin", 45.0, Kategoria.PHM, LocalDate.now());

        // Vytvorime si objekt s nazvom naklad3 a naplnime ho iba niekolkymi hodnotami priamo cez konstruktor
        Naklad naklad3 = new Naklad("Listok do kina", 8.5, Kategoria.KULTURA);
// Vytvorili sme prazdny zoznam nakladov, teda dynamicke jpole, ktore neobsahuje zatial ziadny naklad
        ArrayList<Naklad> naklady = new ArrayList<>();
        naklady.add(naklad1);
        naklady.add(naklad2);
        naklady.add(naklad3);
// Musime vytvorit service pre naklad
        NakladService nakladService = new NakladService();
        try {
            nakladService.ulozDoSuboru(naklady, "Naklady.ser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
