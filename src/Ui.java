import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ui {
    Tournament tournament = new Tournament();
    private static int maxTeams = 16;

    public static int getMaxTeams() {
        return maxTeams;
    }

    //public static ArrayList<Team> teams = new ArrayList<>();


    // input for team name, players amount, name of the players
    public static ArrayList<Team> createTeams() {
        for (int i = 1; i < maxTeams+1; i++) {
            int teamSize = -1;
            ArrayList<String> names = new ArrayList<>();
            String teamName = getUserInput("skriv navn på hold " + i + ":");
            if(teamName.toLowerCase().equals("q")) break;

            while(teamSize == -1) {
                try {
                    teamSize = Integer.parseInt(getUserInput("Skriv antal spiller på holdet:"));
                } catch (NumberFormatException e) {
                    System.out.println("Skal bruge et antal på hold størrelsen");
                }
            }

            for (int s = 1; s < teamSize+1; s++) {
                names.add(getUserInput("skriv navnet på spiller " + s + ":"));

            }
            Tournament.teams.add(new Team(teamName, teamSize, names, 0,0));

        }
        return Tournament.teams;
    }

    // print text for the menu and switch case for it
    public static void menu(){
        String input = getUserInput("Velkommen til menuen\n" +
                "\nTast 1 for at se hold og placering:\n" +
                "Tast 2 for at se kampprogram:\n" +
                "Tast 3 for at inserte point:\n" +
                "Tast 4 for at lukke og gemme programmet:\n");

        switch(input) {
            case "1":
                //Metode til at se hold + placering done
                displayTeams();
                menu();
                break;
            case "2":
                //Metode til at kampprogram
                //Tidspunkter for kampe (?)
                Match.showMatchProgram();
                menu();
                break;
            case "3":
                //Metode til at indsætte point
                //setgoals sort hold efter højest points
                Result result = new Result();
                menu();
                break;
            case "4":
                //quit and save done
                saveGameData();
                break;
        }



    }

    // menuen option 1 showing the sorted teams arraylist;
    public static void displayTeams(){
        Comparator<Team> compareByPointScore = Comparator.comparing(Team::getPoints).thenComparing(Team::getGoalScore);

        ArrayList<Team> sortedTeams;
        sortedTeams = Tournament.teams;
        Collections.sort(sortedTeams,compareByPointScore);
        Collections.reverse(sortedTeams);

        for(Team t : sortedTeams){
            System.out.println(t.toString());
        }
        System.out.println("\n");

    }

    // starts a new game
    public static void promptNewGame(){
        String input = getUserInput("Vil du starte et nyt spil? Y/N: ").trim();
        if(input.equalsIgnoreCase("y") || input.equals("")){
            Tournament.teams = createTeams();
        }
        else {
            menu();
        }
    }

    // checks for exiting file
    public static void startOrContinueGame(){
        Tournament.teams = Tournament.readFileData();
        if (Tournament.teams.size() == 0){
            Tournament.teams = createTeams();
        } else {
            promptNewGame();
        }
    }
    // a scanner + trim start/end of varible
    public static String getUserInput(String msg) {
        System.out.print(msg);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().trim();
    }
    // read the file game data
    public static String getGameData() {
        StringBuilder gameData = new StringBuilder();
        String teamData;

        // build a string for starthour and startday
        teamData = String.format("%d:%d\n",Match.startHour, Match.startDay);
        gameData.append(teamData);

        for (Team t : Tournament.teams) {
            teamData = String.format("%s:%d%s:%d:%d\n", t.teamName, t.playerCount, t.nameSplitter(), t.getPoints(), t.getGoalScore());
            gameData.append(teamData);

        }
        return gameData.toString();
    }

    public static void saveGameData() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("data.txt");
            writer.write(getGameData());
        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveGameData()");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveGameData()");
                e.printStackTrace();
            }
        }
        Controller.saveData();
    }
}