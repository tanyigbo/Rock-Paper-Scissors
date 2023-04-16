import player.*;

import java.util.Arrays;
import java.util.List;

public class Main implements PlayerChoices, ScannerInput {
    private final List<String> opponentMenuOptions = Arrays.asList("player", "computer", "quit");
    private final List<String> mainMenuOptions = Arrays.asList("play", "history", "quit");
    private Game game = new Game();
    private boolean givenValidMenuInput;
    private int validMenuResponse;

    public static void main(String[] args) {
        Main mainGame = new Main();
        mainGame.startGame();
        input.close();
    }

    public void startGame() {
        System.out.println("Welcome to Rock, Paper, Scissors!\n");
        addHumanPlayer();
        game.importGameHistory();
        printOpponentMenu();
        opponentSelected();
        while (validMenuResponse != 2) {
            printMainMenu();
            mainMenuSelected();
        }
        game.exportGameHistory();
        System.out.println("Thanks for playing.");
    }

    public void addHumanPlayer() {
        System.out.println("Hello Player " + ((int) game.getNumberOfPlayers() + 1) + ". What is your name: ");
        String playerInputStr = input.nextLine();
        playerInputStr = playerInputStr.replaceAll("\\s", "");
        playerInputStr = playerInputStr.replaceAll("\\\\", "");
        Player newPlayer = new HumanPlayer(playerInputStr);
        game.addPlayer(newPlayer);
        System.out.println("\nWelcome " + newPlayer.getPlayerName());
    }

    public void printOpponentMenu() {
        givenValidMenuInput = false;
        while (!givenValidMenuInput) {
            System.out.println("Choose your opponent");
            System.out.println("=====");
            System.out.println("1. Type 'player' for player vs player.");
            System.out.println("2. Type 'computer' for player vs computer.");
            System.out.println("3. Type 'quit' to stop playing.");
            getMenuInput(opponentMenuOptions);
        }
    }

    public void opponentSelected() {
        switch (validMenuResponse) {
            case 0:
                System.out.println("Your oppenent will be another player");
                addHumanPlayer();
                break;
            case 1:
                System.out.println("Your opponent will be a computer.");
                game.addPlayer(new CPUPlayer());
                break;
            default:
                break;
        }
    }

    public void printMainMenu() {
        givenValidMenuInput = false;
        while (!givenValidMenuInput) {
            System.out.println("MAIN MENU");
            System.out.println("=====");
            System.out.println("1. Type 'play' to play.");
            System.out.println("2. Type 'history' to view your game history.");
            System.out.println("3. Type 'quit' to stop playing.");
            getMenuInput(mainMenuOptions);
        }
    }

    public void getMenuInput(List<String> menu) {
        String menuInputStr = input.nextLine();
        menuInputStr = menuInputStr.replaceAll("\\s", "").toLowerCase();
        System.out.println("");
        if (menu.contains(menuInputStr)) {
            givenValidMenuInput = true;
            validMenuResponse = menu.indexOf(menuInputStr);
        } else {
            System.out.println("Invalid input. Please provide valid response.");
            validMenuResponse = 2;
        }
    }

    public void mainMenuSelected() {
        switch (validMenuResponse) {
            case 0:
                validMenuResponse = game.playRound();
                break;
            case 1:
                displayPlayerHistory();
                break;
            default:
                break;
        }
    }

    public void displayPlayerHistory() {
        List<List<String>> gameHistory = game.getPlayerHistory();
        System.out.println("Game History");
        if (gameHistory.size() == 0) {
            System.out.println("No games recorded\n");
        }
        else {
            for (List<String> game : gameHistory) {
                System.out.println("Timestamp: " + game.get(0));
                System.out.println("Opponnent: " + game.get(1));
                System.out.println("Result: " + game.get(2));
                System.out.println("Choice: " + game.get(3));
                System.out.println("");
            }
        }
    }
}
