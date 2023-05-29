package sk.tmconsulting.evidencianakladov.service;

import sk.tmconsulting.evidencianakladov.model.Naklad;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NakladService implements INakladService {

    @Override
    public void uloz(Naklad naklad) {

    }

    @Override
    public void uprav(Naklad naklad) {

    }

    @Override
    public void zmaz(Naklad naklad) {

    }

    @Override
    public void zobraz(Naklad naklad) {

    }

    @Override
    public void zobrazVsetky(ArrayList<Naklad> zoznamNakladov) {

    }

    @Override
    public double spocitajNakladyPodlaDatumuOdDo(LocalDate datumOd, LocalDate datumDo, ArrayList<Naklad> zoznamNakladov) {
        return 0;
    }

    @Override
    public void ulozDoSuboru(ArrayList<Naklad> zoznamNakladov, String nazovSuboru) throws IOException {

        System.out.println("Vypisujem konkretne náklady:");
        // Vypisem vsetky naklady pri ukladani
        for (Naklad konkretnyNaklad:zoznamNakladov) {
            System.out.println(konkretnyNaklad);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(nazovSuboru); // vytvorime subor s nazvom kniha.ser
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); // vytvorime Object stream pre ukladanie objektov
        objectOutputStream.writeObject(zoznamNakladov); // zapiseme objekt
        objectOutputStream.flush(); // realne uskutocnime operaciu zapisu
        objectOutputStream.close(); // zatvorime object output stream
        fileOutputStream.close(); // zatvorime file output stream, cize subor
    }

    @Override
    public ArrayList<Naklad> otvorZoSuboru(String nazovSuboru) throws IOException, ClassNotFoundException {
        // nacitanie ArrayList
        FileInputStream fileInputStream = new FileInputStream(nazovSuboru);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Naklad> zoznamNakladov = (ArrayList<Naklad>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return zoznamNakladov;
    }

    @Override
    public void export2PDF(ArrayList<Naklad> zoznamNakladov) {

    }
}