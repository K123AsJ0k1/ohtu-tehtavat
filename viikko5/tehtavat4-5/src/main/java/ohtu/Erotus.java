package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Erotus implements Komento {

    JTextField tuloskentta;
    JTextField syotekentta;
    JButton nollaa;
    JButton undo;
    Sovelluslogiikka sovellus;
    Integer edellinen_arvo;

    public Erotus(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        edellinen_arvo = 0;
    }

    @Override
    public void suorita() {
        int arvo = 0;
        
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        
        sovellus.miinus(arvo);

        edellinen_arvo = arvo;

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
        sovellus.plus(this.edellinen_arvo);
        this.edellinen_arvo = 0;
        
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