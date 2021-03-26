package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Nollaa implements Komento {

    JTextField tuloskentta;
    JTextField syotekentta;
    JButton nollaa;
    JButton undo;
    Sovelluslogiikka sovellus;
    Integer edellinen;

    public Nollaa(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.edellinen = 0;
    }

    @Override
    public void suorita() {
        this.edellinen = sovellus.tulos();

        sovellus.nollaa();

        int laskunTulos = sovellus.tulos();
         
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        
        if (laskunTulos == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
        undo.setEnabled(true);
    }

    @Override
    public void peru() {
        sovellus.returnTulos(this.edellinen);

        int laskunTulos = sovellus.tulos();
         
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        
        if (laskunTulos == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
        undo.setEnabled(true);
    }


}