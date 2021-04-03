/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author niila
 */
public class QueryBuilder {
    
    Matcher matcher;
    
    public QueryBuilder() {
        matcher = new All();
    }
    
    public Matcher build() {
        return matcher;
    }
    
    public QueryBuilder playsIn(String team) {
        Matcher muisto = this.matcher;
        this.matcher = new And(muisto,new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        Matcher muisto = this.matcher;
        this.matcher = new And(muisto,new HasAtLeast(value,category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        Matcher muisto = this.matcher;
        this.matcher = new And(muisto,new HasFewerThan(value,category));
        return this;
    }
    
}
