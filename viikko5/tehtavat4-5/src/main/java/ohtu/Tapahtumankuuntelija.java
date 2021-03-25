package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.Map;
import java.util.HashMap;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton undo;
    private Sovelluslogiikka sovellus;

    private Map<JButton, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, nollaa, undo, sovellus));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() != undo) {
            Komento komento = this.komennot.get(ae.getSource());
            komento.suorita();
            this.edellinen = komento;
        } else {
            this.edellinen.peru();
            this.edellinen = null;
        }
        
    }
 
}