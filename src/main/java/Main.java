import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    Game game = new Game();
    private boolean givenValidMenuInput;
    private int validMenuResponse;
    private final List<String> opponentMenuOptions = Arrays.asList("player", "computer","quit");
    private final List<String> mainMenuOptions = Arrays.asList("play", "history", "quit");

    public static void main(String[] args) {
        Main mainGame = new Main();
        System.out.println("Welcome to Rock, Paper, Scissors!\n");
        mainGame.printOpponentMenu();
        while (mainGame.validMenuResponse != 2){
            mainGame.validMenuResponse=2;
        }
        mainGame.printMainMenu();
    }

    public void printOpponentMenu(){
        givenValidMenuInput = false;
        validMenuResponse = 2;
        while (!givenValidMenuInput) {
            System.out.println("Choose your opponent");
            System.out.println("=====");
            System.out.println("1. Type 'player' for player vs player.");
            System.out.println("2. Type 'computer' for player vs computer.");
            System.out.println("3. Type 'quit' to stop playing.\n");
            validMenuResponse = getMenuInput(opponentMenuOptions);
        }
    }

    public void selectOpponent(){
        switch (validMenuResponse){

        }
    }
    public void printMainMenu() {
        givenValidMenuInput = false;
        validMenuResponse = 2;
        while (!givenValidMenuInput) {
            System.out.println("MAIN MENU");
            System.out.println("=====");
            System.out.println("1. Type 'play' to play.");
            System.out.println("2. Type 'history' to view your game history.");
            System.out.println("3. Type 'quit' to stop playing.\n");
            validMenuResponse = getMenuInput(mainMenuOptions);
        }
    }

    public int getMenuInput(List<String> menu) {
        Scanner menuInput = new Scanner(System.in);
        String menuInputStr = menuInput.nextLine();
        menuInput.close();
        menuInputStr = menuInputStr.replaceAll("\\s", "").toLowerCase();
        if (menu.contains(menuInputStr)) {
            givenValidMenuInput = true;
            return menu.indexOf(menuInputStr);
        }
        else{
            System.out.println("Invalid input. Please provide valid response.");
            return 2;
        }
    }
}
