package sk.tmconsulting.evidencianakladov;

import sk.tmconsulting.evidencianakladov.model.Kategoria;
import sk.tmconsulting.evidencianakladov.model.Naklad;
import sk.tmconsulting.evidencianakladov.service.NakladService;
import sk.tmconsulting.evidencianakladov.view.GUI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EvidenciaNakladovMain {
    public static void main(String[] args) {
        // Vytvorime si objekt s nazvom naklad1
        Naklad naklad1 = new Naklad();
        naklad1.setNazov("Nákup potravín");
        naklad1.setCena(12.60);
        naklad1.setKategoria(Kategoria.POTRAVINY);
        naklad1.setDatumZaznamu(LocalDate.now()); // Naplnili sme datumZaznamu aktualnym datumom

        // Vytvorime si objekt s nazvom naklad2 a naplnime ho priamo cez konstruktor
        Naklad naklad2 = new Naklad("Benzín", 45.0, Kategoria.PHM, LocalDate.now());

        // Vytvorime si objekt s nazvom naklad3 a naplnime ho iba niekolkymi hodnotami priamo cez konstruktor
        Naklad naklad3 = new Naklad("Lístok do kina", 8.5, Kategoria.KULTURA, LocalDate.now());

        // Naklad naklad4 = new Naklad("Lístok na Java školenie", 1200.0); // Toto nepojde, lebo nemam konstruktor s danym poctom parametrov

        ArrayList<Naklad> naklady = new ArrayList<>(); // Vytvorili sme prazdny zoznam nakladov, teda dynamicke pole, ktore neobsahuje zatial ziadny naklad
        naklady.add(naklad1);
        naklady.add(naklad2);
        naklady.add(naklad3);

/*        NakladService ns = new NakladService();
        try {
            ns.ulozDoSuboru(naklady, "naklady.ser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        GUI gui = new GUI();
        gui.vytvor();

    }
}
