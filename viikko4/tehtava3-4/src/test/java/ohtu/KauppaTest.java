package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345"); 

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt()); 
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiTilisiirtoKutsutaanOikeillaParametreilla() {
        Pankki pankki = mock(Pankki.class);
        
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("pekka", "12345"); 
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5)); 
    }

    @Test
    public void eriTuotteitaVoiOstaa() {
        Pankki pankki = mock(Pankki.class);
        
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2); 
        k.tilimaksu("pekka", "12345"); 
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(8)); 
    }

    @Test
    public void samaaTuotettaVoiOstaaMonta() {
        Pankki pankki = mock(Pankki.class);
        
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345"); 
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10)); 
    }

    @Test
    public void asiakasLaskutetaanOikeallaTavalla() {
        Pankki pankki = mock(Pankki.class);
        
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2); 
        k.tilimaksu("pekka", "12345"); 
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5)); 
    }

    @Test
    public void monetAsiakaatVoivatKayttaaKauppaa() {
        Pankki pankki = mock(Pankki.class);
        
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        
        when(viite.uusi())
            .thenReturn(1)
            .thenReturn(2);
        
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2); 
        k.tilimaksu("pekka", "12345"); 
        
        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(8)); 

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(1);
        k.lisaaKoriin(1); 
        k.tilimaksu("teppo", "11343"); 
        
        verify(pankki).tilisiirto(eq("teppo"), eq(2), eq("11343"), eq("33333-44455"), eq(15)); 
    }

    @Test
    public void tavaraVoidaanPoistaaKorista() {
        Pankki pankki = mock(Pankki.class);
        
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.poistaKorista(1); 
        
        verify(varasto, times(1)).palautaVarastoon(new Tuote(1, "maito", 5));
    }

}

