import java.util.ArrayList;

public class Result {
// insert points for the games

// go on to next round after points

    public Result() {
        // to read which round we are in
        if (Match.quarter.size() < 1) {
            calculateResult(Match.preQuarter);
        } else if (Match.semi.size() < 1) {
            calculateResult(Match.quarter);
        } else if (Match.finale.size() < 1) {
            calculateResult(Match.semi);
            calculateResult(Match.finale);
        }

    }

    // calculate winners and send them to next round for quarters
    public static void calculateResult(ArrayList<String> bracket) {

        if (Match.quarter.size() < 1) {
            System.out.println("Der er ikke tildelt mål til de første kampe endnu. Tildel venligst mål: ");
            for (Team t : Tournament.teams) {
                // goal scan
                t.goals = Integer.parseInt(Match.getUserInput("Tildel mål til " + t.teamName + ": "));
            }
        }
        // loop that that goes thought 2 teams at a time
        for (int i = 0; i < bracket.size(); i += 2) { //
            int teamAGoals = 0;
            int teamBGoals = 0;
            String teamWin = "";
            String teamLose = "";

            // goals
            for (Team t : Tournament.teams) {
                if (t.teamName.equalsIgnoreCase(bracket.get(i))) {
                    teamAGoals = t.goals;
                }
                if (t.teamName.equalsIgnoreCase(bracket.get(i + 1))) {
                    teamBGoals = t.goals;
                }
            }
            // compare points and decides which move on
            if (teamAGoals > teamBGoals) {
                teamWin = bracket.get(i);
                teamLose = bracket.get(i + 1);
            } else {
                teamWin = bracket.get(i + 1);
                teamLose = bracket.get(i);
            }
            // gives 2 point and goal score
            for (Team t : Tournament.teams) {
                if (t.teamName.equalsIgnoreCase(teamWin)) {
                    t.points += 2;
                    // checks winner and plus to the winner
                    if (teamWin.equalsIgnoreCase(bracket.get(i))) {
                        t.goalScore += (teamAGoals - teamBGoals);
                    } else {
                        t.goalScore += (teamBGoals - teamAGoals);
                    }
                }
                // check loser and minus to the losers
                if (t.teamName.equalsIgnoreCase(teamLose)) {
                    if (teamLose.equals(bracket.get(i))) {
                        t.goalScore += (teamAGoals - teamBGoals);
                    } else {
                        t.goalScore += (teamBGoals - teamAGoals);
                    }
                }
                //t.goals = 0;
            }
            // decide which round we are in
            // for quarter1
            if (bracket.size() == Ui.getMaxTeams()) {     // == maxTeams
                Match.quarter.add(teamWin);
            } // for semi
            else if (bracket.size() == Ui.getMaxTeams() / 2) { // == maxteams/2
                Match.semi.add(teamWin);
            } // for finale
            else if (bracket.size() == Ui.getMaxTeams() / 4) { // == maxteam/4
                Match.finale.add(teamWin);
            } // for winner
            else if (bracket.size() == Ui.getMaxTeams() / 8) { // == maxteam/8
                System.out.println("Vinderen af turneringen er: " + teamWin);
            }
        }

        // for semi
        if (bracket.size() == Ui.getMaxTeams()) { // == maxteams
            System.out.println("Der er ikke tildelt mål til kvartfinale kampene endnu. Tildel venligst mål: ");
            for (String s : Match.quarter) {
                for (Team t : Tournament.teams) {
                    if (t.teamName.equalsIgnoreCase(s)) {
                        t.goals = Integer.parseInt(Match.getUserInput("Tildel mål til " + t.teamName + ": "));
                    }
                }
            }


        }

        // for finale
        else if (bracket.size() == Ui.getMaxTeams() / 2) { // == maxteam/2
            System.out.println("Der er ikke tildelt mål til semifinale kampene endnu. Tildel venligst mål: ");
            for (String s : Match.semi) {
                for (Team t : Tournament.teams) {
                    if (t.teamName.equalsIgnoreCase(s)) {
                        t.goals = Integer.parseInt(Match.getUserInput("Tildel mål til " + t.teamName + ": "));

                    }
                }
            }
        } // for winner
        else if (bracket.size() == Ui.getMaxTeams() / 4) { // == maxteam/4
            System.out.println("Der er ikke tildelt mål til finale kampene endnu. Tildel venligst mål: ");
            for (String s : Match.finale) {
                for (Team t : Tournament.teams) {
                    if (t.teamName.equalsIgnoreCase(s)) {
                        t.goals = Integer.parseInt(Match.getUserInput("Tildel mål til " + t.teamName + ": "));
                    }
                }
            }
        }
    }



}
