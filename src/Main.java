public class Main {

    public static void main(String[] args) {
        Controller.loadData();
        Ui.startOrContinueGame();
        Ui ui = new Ui();

        System.out.println(Match.preQuarter.toString());
        System.out.println(Match.quarter.toString());
        System.out.println(Match.semi.toString());
        System.out.println(Match.finale.toString());

    }
}

