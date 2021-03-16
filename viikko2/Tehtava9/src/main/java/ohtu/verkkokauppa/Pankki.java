package ohtu.verkkokauppa;

@Component
public class Pankki implements PankkiRajapinta {

    private KirjanpitoRajapinta kirjanpito;
    
    @Autowired
    public Pankki(KirjanpitoRajapinta kirjanpitoViite) {
        kirjanpito = kirjanpitoViite;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
