package ohtu;

public class TennisGame {
    
    private int first_score = 0;
    private int second_score = 0;
    private String player_one;
    private String player_two;

    public TennisGame(String player_one, String player_two) {
        this.player_one = player_one;
        this.player_two = player_two;
    }

    public void wonPoint(String player_name) {
        if (player_name == "player1") {
            first_score += 1;
        } else {
            second_score += 1;
        }
    }

    public String tieGiver(int first_score) {
        switch (first_score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
        }
        return "Deuce";
    }

    public String winGiver(int first_score, int second_score) {
        int score_difference = first_score-second_score;

        if (score_difference == 1) {
            return "Advantage player1";
        } 

        if (score_difference == -1) {
            return "Advantage player2";
        }

        if (score_difference >= 2) {
            return "Win for player1";
        }
        
        return "Win for player2";
    }

    public String situationGiver(int temporary_score, int first_score, int second_score) {
        String situation = "";
        
        for (int i=1; i<3; i++) {
             
             if (i == 1) {
                temporary_score = first_score;
             } else {
                situation += "-";
                temporary_score = second_score;
             }
             switch(temporary_score) {
                case 0:
                    situation += "Love";
                    break;
                case 1:
                    situation += "Fifteen";
                    break;
                case 2:
                    situation += "Thirty";
                    break;
                case 3:
                    situation += "Forty";
                    break;
            }
        }
        return situation;
    }

    public String getScore() {
        
        String score = "";
        int temporary_score = 0;
        
        if (first_score == second_score) {
            score = tieGiver(first_score);
        } else if (first_score >= 4 || second_score >= 4) {
            score = winGiver(first_score, second_score);
        } else {
            score = situationGiver(temporary_score, first_score, second_score);
        }
        return score;
    }
}