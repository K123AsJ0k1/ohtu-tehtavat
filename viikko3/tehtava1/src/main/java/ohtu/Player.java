
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;

    public void setName(String name, String nationality, String team, int goals, int assists) {
        this.name = name;
        this.name = nationality;
        this.team = team;
        this.goals = goals;
        this.assists = assists;
              
    }

    public String getName() {
        return name;
    }
    
    public String getNationality() {
        return nationality;
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
        return name + " nationality " + nationality + " team " + team + " goals " + goals + " assists " + assists;
    }
      
}
