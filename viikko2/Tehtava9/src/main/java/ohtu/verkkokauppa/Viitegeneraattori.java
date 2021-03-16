package ohtu.verkkokauppa;
@Component
public class Viitegeneraattori implements GeneraattoriRajapinta {
    
    private int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
