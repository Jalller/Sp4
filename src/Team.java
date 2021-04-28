import java.util.ArrayList;

public class Team{
    int playerCount;
    public ArrayList<String> studentNames;
    String teamName;
    int points;
    int goalScore;
    int goals;

    public int getPoints() {
        return points;
    }

    public int getGoalScore() {
        return goalScore;
    }

    // holds the data varibles used
    public Team(String teamName, int playerCount, ArrayList<String> studentNames, int points, int goalScore) {
        this.teamName = teamName;
        this.playerCount = playerCount;
        this.studentNames = studentNames;
        this.points = points;
        this.goalScore = goalScore;

    }
    // splits on : symbol in a String
    public String nameSplitter(){
        String result="";
        for(String s : studentNames){
            result += ":" + s;
        }
        return result;
    }

    // for menu 1 team name, points and goal score
    @Override
    public String toString(){
        return  "Holdnavn: " + teamName + " | " +
                "point: " + points + " | " +
                "målscore: " + goalScore ;
    }
    // compare objects in the arraylist not done?
    /*@Override
    public int compareTo(Team compareTeam) {
        int comparePoints = ((Team)compareTeam).points;
        return comparePoints-this.points;

    }*/


}