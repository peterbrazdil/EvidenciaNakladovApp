package sk.tmconsulting.evidencianakladov.view;

import sk.tmconsulting.evidencianakladov.model.Kategoria;
import sk.tmconsulting.evidencianakladov.model.Naklad;
import sk.tmconsulting.evidencianakladov.service.NakladService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GUI {
    private Naklad vybranyNaklad;
    private DefaultListModel<Naklad> modelZoznamu;
    private JList<Naklad> jlist;
    private ArrayList<Naklad> celkoveNaklady = new ArrayList<>();

    public void vytvor() {

        JFrame frame = new JFrame("Evidencia nákladov"); // Vytvorime okno

        frame.setMinimumSize(new Dimension(450, 320)); // Rozmery okna
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ked kliknem na X na okne (cize vo frame) tak sa zatvori standardne
        frame.setLocationRelativeTo(null); // Vycentrovanie okna

        // JFrame by mal obsahovat panel, teda container JPanel
        JPanel panel = new JPanel();
        panel.setLayout(null); // layout pre panel bude null, cize prazdny
        frame.setContentPane(panel); // my dany panel pridame do frame


        // Vytvorenie menu baru (menu bar)
        JMenuBar menuBar = new JMenuBar();
        // Vytvorenie menu
        JMenu menu = new JMenu("Menu");

        // Vytvorenie položky v menu
        JMenuItem item1 = new JMenuItem("Otvor");
        JMenuItem item2 = new JMenuItem("Ulož");

        // Pridanie položiek do menu
        menu.add(item1);
        menu.add(item2);

        // Pridanie menu do menu baru
        menuBar.add(menu);

        // Pridanie menu baru do rámca
        frame.setJMenuBar(menuBar);

        // ActionListener pre položku 1, cize otvorenie suboru
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Musime vytvorit service pre naklad
                NakladService nakladService = new NakladService();
                try {
                    celkoveNaklady = nakladService.otvorZoSuboru("naklady.ser");
                    modelZoznamu.clear(); // Vymazeme doterajsi model a nacitame don nove udaje
                    for (Naklad konkretnyNaklad : celkoveNaklady) {
                        // Naplnenie modelu zoznamu objektmi z ArrayListu
                        System.out.println(konkretnyNaklad);
                        modelZoznamu.addElement(konkretnyNaklad); // Naplnili sme zoznam udajov v jList ale cez DefaultListModel, teda modelZoznamu
                    }
                    //jlist.updateUI(); // Aktualizacia, refresh jList
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                } catch (ClassNotFoundException e2) {
                    throw new RuntimeException(e2);
                }
            }
        });


        // ActionListener pre položku 2
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tu môžete definovať, čo sa má stať po kliknutí na položku 1
                // Musime vytvorit service pre naklad
                NakladService nakladService = new NakladService();

                // Serializacia
                try {
                    nakladService.ulozDoSuboru(celkoveNaklady, "naklady.ser");
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });


        // JText
        JTextField txfNazov = new JTextField();
        txfNazov.setBounds(10, 20, 100, 30); // x, y, sirka, vyska
        txfNazov.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txfNazov);

        JTextField txfCena = new JTextField();
        txfCena.setBounds(120, 20, 100, 30); // x, y, sirka, vyska
        txfCena.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txfCena);

        // JText
        JTextField txfKategoria = new JTextField();
        txfKategoria.setBounds(230, 20, 100, 30); // x, y, sirka, vyska
        txfKategoria.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txfKategoria);

        JTextField txfDatum = new JTextField();
        txfDatum.setBounds(340, 20, 100, 30); // x, y, sirka, vyska
        txfDatum.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txfDatum);


        JLabel labelText = new JLabel("Java Developer Junior (c) Jan Zitniak"); // popisok
        labelText.setBounds(100, 52, 230, 20); // x, y, sirka, vyska
        panel.add(labelText);


        // Vytvorenie modelu zoznamu
        modelZoznamu = new DefaultListModel<>();

        // Vytvorenie JList s modelom zoznamu
        jlist = new JList<>(modelZoznamu);
        jlist.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    vybranyNaklad = jlist.getSelectedValue();
                    txfNazov.setText(vybranyNaklad.getNazov());
                    txfCena.setText(String.valueOf(vybranyNaklad.getCena()));
                    txfKategoria.setText(vybranyNaklad.getKategoria().toString());
                    txfDatum.setText(vybranyNaklad.getDatumZaznamu().toString());
                    System.out.println(vybranyNaklad);
                }
            }
        });


        //jlist.setBounds(70,80,200,50);
        //panel.add(jlist);

        JScrollPane jScrollPane = new JScrollPane(jlist);
        jScrollPane.setBounds(70, 80, 300, 150);
        panel.add(jScrollPane);


        JButton btnNovy = new JButton("Nový"); // tlacidlo
        btnNovy.setBounds(10, 240, 80, 20); // x, y, sirka, vyska
        panel.add(btnNovy);
        btnNovy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vyprazdnime hore formular pre vstupne udaje
                txfNazov.setText(""); // Vymaze obsah jTextField pre nazov
                txfKategoria.setText(""); // Vymaze obsah jTextField pre kategoria
                txfCena.setText(""); // Vymaze obsah jTextField pre cena
                txfDatum.setText(""); // Vymaze obsah jTextField pre datum

                txfNazov.setBorder(new LineBorder(Color.BLUE, 2));
                txfKategoria.setBorder(new LineBorder(Color.BLUE, 2));
                txfCena.setBorder(new LineBorder(Color.BLUE, 2));
                txfDatum.setBorder(new LineBorder(Color.BLUE, 2));

                btnNovy.setEnabled(false);
            }
        });

        JButton btnUloz = new JButton("Pridaj do zoznamu"); // tlacidlo
        btnUloz.setBounds(100, 240, 90, 20); // x, y, sirka, vyska
        panel.add(btnUloz);
        btnUloz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Metoda getText zo jTextField nam vzdy vrati obsah daneho grafickeho prvku (komponentu) vo forme textu
                Naklad novyNaklad = new Naklad(txfNazov.getText(), Double.parseDouble(txfCena.getText()), Kategoria.valueOf(txfKategoria.getText()), LocalDate.parse(txfDatum.getText()));
                modelZoznamu.addElement(novyNaklad);
                celkoveNaklady.add(novyNaklad); // Musime doplnit novyNaklad do ArrayList-u celkoveNaklady, pretoze dany ArrayList ukladame
                btnNovy.setEnabled(true);
            }
        });

        JButton btnAktualizuj = new JButton("Aktualizuj"); // tlacidlo
        btnAktualizuj.setBounds(200, 240, 80, 20); // x, y, sirka, vyska
        panel.add(btnAktualizuj);

        btnAktualizuj.addActionListener(new ActionListener() { // sluzi na "odchytenie" cize spracovanie zatlacenia tlacidla
            public void actionPerformed(ActionEvent e) {
                vybranyNaklad.setNazov(txfNazov.getText());
                vybranyNaklad.setCena(Double.parseDouble(txfCena.getText()));
                vybranyNaklad.setKategoria(Kategoria.valueOf(txfKategoria.getText()));
                vybranyNaklad.setDatumZaznamu(LocalDate.parse(txfDatum.getText()));
                jlist.updateUI();
            }
        });


        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(290, 240, 80, 20);
        panel.add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });


        // display it
        frame.pack();
        frame.setVisible(true);
    }

}
