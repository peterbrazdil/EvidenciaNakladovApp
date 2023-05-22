package sk.tmconsulting.evidencianakladov.view;

import sk.tmconsulting.evidencianakladov.model.Kategoria;
import sk.tmconsulting.evidencianakladov.model.Naklad;
import sk.tmconsulting.evidencianakladov.service.NakladService;

import javax.swing.*;
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
    private ArrayList<Naklad> celkoveNaklady;

    public void vytvor() {

        JFrame frame = new JFrame("Evidencia nákladov"); // vytvorime okno

        frame.setMinimumSize(new Dimension(450, 320));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ked kliknem na X na okne (cize vo frame) tak sa zatvori standardne
        frame.setLocationRelativeTo(null); // vycentrovanie okna

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

        // ActionListener pre položku 1
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
                            modelZoznamu.addElement(konkretnyNaklad);
                    }
                    jlist.updateUI(); // Aktualizacia jList
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

        JButton btnUloz = new JButton("Potvrď"); // tlacidlo
        btnUloz.setBounds(110, 240, 80, 20); // x, y, sirka, vyska
        panel.add(btnUloz);

        btnUloz.addActionListener(new ActionListener() { // sluzi na "odchytenie" cize spracovanie zatlacenia tlacidla
            public void actionPerformed(ActionEvent e) {
                vybranyNaklad.setNazov(txfNazov.getText());
                vybranyNaklad.setCena(Double.parseDouble(txfCena.getText()));
                vybranyNaklad.setKategoria(Kategoria.valueOf(txfKategoria.getText()));
                vybranyNaklad.setDatumZaznamu(LocalDate.parse(txfDatum.getText()));
                jlist.updateUI();
            }
        });


        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(240, 240, 80, 20);
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
