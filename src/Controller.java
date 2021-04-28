public class Controller {
    public IO io;
    public static Ui Ui;
    public static String teams_path;

    enum Datasource {
        DATABASE,
        CSVFILE
    }
    private static Datasource src = Datasource.DATABASE;
    static DBConnector dbConnector = new DBConnector();

    public static void loadData(){
        //dbConnector.readGameData();
        Tournament.teams = dbConnector.readGameData();

    }

    public static void saveData(){
        dbConnector.saveDataToDB();
    }




}
