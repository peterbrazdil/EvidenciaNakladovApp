package sk.tmconsulting.evidencianakladov.view;

import javax.swing.*;
import java.awt.*;

public class Dialog extends JDialog {

    public Dialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal); // Slovickom super vlastne volame konstruktor dedenej triedy
    }
}
