import java.util.*;

public class  Match {
    // arraylist for each rank of the tournement
    public static ArrayList<String> preQuarter = new ArrayList<>();
    public static ArrayList<String> quarter = new ArrayList<>();
    public static ArrayList<String> semi = new ArrayList<>();
    public static ArrayList<String> finale = new ArrayList<>();

    static Calendar currentTimeNow = Calendar.getInstance();
    static int startDay = 0;
    static int startHour = 0;
    static int matchTime = 30;


    Team teamA;
    Team teamB;


    // constructer for teamA/B and matchbracket for which bracket
    public Match(Team teamA, Team teamB, int matchBracket) {
        this.teamA = teamA;
        this.teamB = teamB;
        // if statement for each round of the bracket
        if (matchBracket == 8) Collections.addAll(preQuarter, teamA.teamName, teamB.teamName);
        else if (matchBracket == 4) Collections.addAll(quarter, teamA.teamName, teamB.teamName);
        else if (matchBracket == 2) Collections.addAll(semi, teamA.teamName, teamB.teamName);
        else if (matchBracket == 1) Collections.addAll(finale, teamA.teamName, teamB.teamName);
    }





    // show the first round of matches
    public static void showMatchProgram() {
        // timer for seconds, minute, hour,day that get added to each match
        currentTimeNow.set(Calendar.DAY_OF_MONTH,startDay);
        currentTimeNow.set(Calendar.HOUR_OF_DAY,startHour);
        currentTimeNow.set(Calendar.MINUTE,0);
        currentTimeNow.set(Calendar.SECOND,0);

        if (preQuarter.size() < 1) {
            assignTeamsToMatch();
            return;
        }
        //prequarters
        for (int i = 0; i < preQuarter.size(); i += 2) {
            System.out.println("\n"+"Pre-Quarter matches: ");
            System.out.print("| " + preQuarter.get(i));
            System.out.print(" vs " + preQuarter.get(i + 1) + " |"+"\n");
            System.out.println("Match start: " + currentTimeNow.getTime());

            currentTimeNow.add(Calendar.MINUTE, matchTime);
        }

        currentTimeNow.set(Calendar.HOUR_OF_DAY,startHour);
        currentTimeNow.set(Calendar.MINUTE,0);
        currentTimeNow.add(Calendar.HOUR,24);
        // quarter finale
        for (int i = 0; i < quarter.size(); i += 2) {
            System.out.println("\n"+"Kvartfinale: ");
            System.out.print("| " + quarter.get(i));
            System.out.print(" vs " + quarter.get(i + 1) + " |"+"\n");
            System.out.println("Match start: " + currentTimeNow.getTime());

            currentTimeNow.add(Calendar.MINUTE, matchTime);
        }

        currentTimeNow.set(Calendar.HOUR_OF_DAY,startHour);
        currentTimeNow.set(Calendar.MINUTE,0);
        currentTimeNow.add(Calendar.HOUR,24);
        // semi
        for (int i = 0; i < semi.size(); i += 2) {
            System.out.println("\n"+"Semi Finale: ");
            System.out.print("| " + semi.get(i));
            System.out.print(" vs " + semi.get(i + 1) + " |"+"\n");
            System.out.println("Match start: " + currentTimeNow.getTime());

            currentTimeNow.add(Calendar.MINUTE, matchTime);
        }

        currentTimeNow.set(Calendar.HOUR_OF_DAY,startHour);
        currentTimeNow.set(Calendar.MINUTE,0);
        currentTimeNow.add(Calendar.HOUR,24);
        // finale
        for (int i = 0; i < finale.size(); i += 2) {
            System.out.println("\n"+"The FINALE: ");
            System.out.print("| " + finale.get(i));
            System.out.print(" vs " + finale.get(i + 1) + " |"+"\n");
            System.out.println("Match start: " + currentTimeNow.getTime());

            currentTimeNow.add(Calendar.MINUTE, matchTime);
        }

    }

    // for writing inserting the team names playing
    public static void assignTeamsToMatch() {

        System.out.println("De første kampe er ikke blevet oprettet endnu.\n" +
                "Opret venligst de første kampe: ");

        startDay = Integer.parseInt(getUserInput("Hvilke dag på månenden skal de første kampe starte?: "));
        startHour = Integer.parseInt(getUserInput("Hvilken time skal de første kampe starte om dage?: "));


        // 8 loops that start in 1 for at kører igennem oprettelse af kampene
        for (int i = 1; i < 9; i++) {
            String teamNameA;
            String teamNameB;
            // print team names and accepts input for names
            System.out.println("\nKamp " + i + ":");
            teamNameA = nameChecker(getUserInput("Indtast venligst navnet på hold A: "));
            teamNameB = nameChecker(getUserInput("Indtast venligst navnet på hold B: "));

            Collections.addAll(preQuarter, teamNameA, teamNameB);
        }

    }

    // name checker compares name from input with the arraylist of names
    public static String nameChecker(String name) {
        boolean nameExists = false;
        String teamName = name;
        // loop
        for (Team t : Tournament.teams) {
            // check if team name exist
            if (t.teamName.equalsIgnoreCase(teamName)) {
                System.out.println("Navnet findes!" + "\n");
                nameExists = true;
            }
        }
        // check if team doesn't exit
        if (!nameExists) {
            teamName = getUserInput("Navnet du har angivet til holdet findes ikke " +
                    "Indtast venligst navnet på holdet: ");
            teamName = nameChecker(teamName);
        }
        return teamName;
    }

    public static String getUserInput(String msg) {
        System.out.print(msg);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().trim();
    }


}