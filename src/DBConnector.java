import java.util.ArrayList;
import java.sql.*;

public class DBConnector implements IO {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/bordfodbold";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    @Override
    public ArrayList<Team> readGameData() {
        ArrayList<Team> contents = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();


            String sql = "SELECT * FROM Teams";
            ResultSet rs = stmt.executeQuery(sql);

            String sqlPlayers = "SELECT playerName FROM Players WHERE team_id = ";
            String sqlPlayersCopy = sqlPlayers;


            int counter = 0;
            int i = 0;

            //STEP 5: Extract data from result set
            while (rs.next()) {
                if (counter == i) {
                    counter = 0;
                    sqlPlayersCopy = sqlPlayers;
                    //Retrieve by column name
                    int id = rs.getInt("id");
                    String teamName = rs.getString("teamName");
                    int teamSize = rs.getInt("teamSize");
                    int points = rs.getInt("points");
                    int goalScore = rs.getInt("goalScore");
                    ArrayList<String> players = new ArrayList<>();

                    sqlPlayersCopy += id;
                    rs = stmt.executeQuery(sqlPlayersCopy);

                    while (rs.next()) {
                        String playerName = rs.getString("playerName");
                        players.add(playerName);

                    }

                    //Display values
                    System.out.print("ID: " + id);
                    System.out.print(", team name: " + teamName);
                    System.out.print(", team size: " + teamSize);
                    System.out.print(", player names: " + players.toString());
                    System.out.println(", points: " + points);
                    System.out.println(", goalScore: "+ goalScore);

                    Team team = new Team(teamName, teamSize, players, points, goalScore);
                    contents.add(team);

                    Tournament.teams.add(team);

                    rs = stmt.executeQuery(sql);
                    i += 1;
                } else {
                    counter += 1;
                }
            }

            System.out.println(Match.preQuarter.toString());
            System.out.println(Match.quarter.toString());
            System.out.println(Match.semi.toString());
            System.out.println(Match.finale.toString());

            //STEP 6: Clean-up environment
            rs.close();
            //rsP.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        getBracketFromDB(Match.preQuarter,"PreQuarter");
        getBracketFromDB(Match.quarter,"Quarter");
        getBracketFromDB(Match.semi,"Semi");
        getBracketFromDB(Match.finale,"Finale");

        return contents;

    }


    // get bracket postionen from sql data
    public static void getBracketFromDB(ArrayList<String> teamNames, String bracket) {
        Connection conn = null;
        Statement stmt = null;

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();


            String sql = "SELECT team_Name FROM " + bracket;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String team_Name = rs.getString("team_Name");

                System.out.print("Team name in " + bracket + ": " + team_Name + "\n");

                teamNames.add(team_Name);

            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }


    @Override
    public void saveDataToDB() {
        Connection conn = null;
        ResultSet rs = null;
        // insert information into the sql and replace if duplicated is found
        String sql = "INSERT INTO Teams(id,teamName,teamSize,points,goalScore) "
                + " VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE teamName=?,teamSize=?,points=?,goalScore=?";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // for teams adding
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //STEP 2: Execute a query
            System.out.println("Creating statement...");
            //stmt = conn.createStatement();
            //ResultSet rs = stmt.executeQuery(sql);

            // for each loop for saving sql teams
            int i = 1;
            for (Team t : Tournament.teams) {
                pstmt.setInt(1, i);
                pstmt.setString(2, t.teamName);
                pstmt.setInt(3, t.playerCount);
                pstmt.setInt(4, t.getPoints());
                pstmt.setInt(5, t.getGoalScore());

                //UPDATES
                pstmt.setString(6, t.teamName);
                pstmt.setInt(7, t.playerCount);
                pstmt.setInt(8, t.getPoints());
                pstmt.setInt(9, t.getGoalScore());


                pstmt.addBatch();
                i++;
            }

            pstmt.executeBatch();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        sendBracketToDB(Match.preQuarter, "PreQuarter");
        sendBracketToDB(Match.quarter, "Quarter");
        sendBracketToDB(Match.semi, "Semi");
        sendBracketToDB(Match.finale, "Finale");

    }


    public static void sendBracketToDB(ArrayList<String> teamNames, String bracket) {
        Connection conn = null;
        ResultSet rs = null;

        String sql = "INSERT INTO " + bracket + "(id,team_Name) "
                + " VALUES(?,?)";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (String s : teamNames) {
                pstmt.setInt(1, i);
                pstmt.setString(2, s);
                //UPDATES
                //pstmt.setString(3, s);

                pstmt.addBatch();
                i++;
            }

            pstmt.executeBatch();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
/*
    with test(ID, VARIANT, SIFRANT, VALUE) as
(
        select 1, 3, 5, 50 from dual union all
        select 2, 3, 6, 49 from dual union all
        select 3, 3, 1, 68 from dual
        )
        select variant, listagg (value, ' ') within group ( order by id)
        from test
        group by variant
*/