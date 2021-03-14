/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author niila
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
 
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri",   "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));
 
        return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void oma_testi_1() {
        Player pelaaja = stats.search("Semenko");
        assertEquals("Semenko", pelaaja.getName());
        assertEquals("EDM", pelaaja.getTeam());
        assertEquals(4, pelaaja.getGoals());
        assertEquals(12, pelaaja.getAssists());
    }
    
    @Test
    public void oma_testi_2() {
        Player ei_pelaajaa = stats.search("Testi");
        assertEquals(null, ei_pelaajaa);
    }
    
    @Test
    public void oma_testi_3() {
        List<Player> pelaaja_lista = stats.team("PIT");
        Player ensimmainen_pelaaja = pelaaja_lista.get(0);
        assertEquals("Lemieux", ensimmainen_pelaaja.getName());
        assertEquals("PIT", ensimmainen_pelaaja.getTeam());
        assertEquals(45, ensimmainen_pelaaja.getGoals());
        assertEquals(54, ensimmainen_pelaaja.getAssists());
    }
    
    @Test
    public void oma_testi_4() {
        List<Player> pelaaja_lista = stats.topScorers(1);
        Player ensimmainen_pelaaja = pelaaja_lista.get(0);
        assertEquals("Gretzky", ensimmainen_pelaaja.getName());
        assertEquals("EDM", ensimmainen_pelaaja.getTeam());
        assertEquals(35, ensimmainen_pelaaja.getGoals());
        assertEquals(89, ensimmainen_pelaaja.getAssists());
    }
    
}






