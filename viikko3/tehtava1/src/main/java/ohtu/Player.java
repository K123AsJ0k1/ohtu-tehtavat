
package ohtu;

public class Player {
    private String name;
    private String team;
    private int goals;
    private int assists;

    public void setName(String name, String team, int goals, int assists) {
        this.name = name;
        this.team = team;
        this.goals = goals;
        this.assists = assists;
              
    }

    public String getName() {
        return name;
    }
    
    public String getTeam() {
        return team;
    }
    
    public int getGoals() {
        return goals;
    }
    
    public int getAssists() {
        return assists;
    }

    @Override
    public String toString() {
        return name + " team " + team + " goals " + goals + " assists " + assists ;
    }
      
}
