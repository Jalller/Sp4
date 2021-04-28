// register, tournament tree,

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Tournament {

    public static ArrayList<Team> teams = new ArrayList<>();

    public Tournament(){
        readFileData();
    }

    // reads the data file and prints
    public static ArrayList<Team> readFileData(){
        int counter = 0;

        File file = new File("data.txt");
        String [] teamLine;
        ArrayList<String> names;
        Scanner scan = null;
        try{
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        // once for each group
        if(scan != null) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                if (counter > 0) {
                    names = new ArrayList<>();
                    // check for blank line
                    if (line.isEmpty()) continue;
                    teamLine = line.split(":");
                    for (int i = 0; i < Integer.parseInt(teamLine[1]); i++) {
                        names.add(teamLine[i + 2]);
                    }

                    // to ad from data.tekst
                    //teams.add(new Team(teamLine[0], Integer.parseInt(teamLine[1]), names, Integer.parseInt(teamLine[teamLine.length-2]), Integer.parseInt(teamLine[teamLine.length-1])));
                } else {
                    teamLine = line.split(":");
                    Match.startHour = Integer.parseInt(teamLine[0]);
                    Match.startDay = Integer.parseInt(teamLine[1]);
                }
                counter ++;
            }
        }
        return teams;
    }
}
