import player.CPUPlayer;
import player.HumanPlayer;
import player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final List<String> opponentMenuOptions = Arrays.asList("player", "computer", "quit");
    private final List<String> mainMenuOptions = Arrays.asList("play", "history", "quit");
    private final Scanner input = new Scanner(System.in);
    private Game game = new Game();
    private boolean givenValidMenuInput;
    private int validMenuResponse;

    public static void main(String[] args) {
        Main mainGame = new Main();
        mainGame.playGame();
    }

    public void playGame() {
        System.out.println("Welcome to Rock, Paper, Scissors!\n");
        addHumanPlayer();
        printOpponentMenu();
        selectedOpponent();
        while (validMenuResponse != 2) {
            printMainMenu();

        }
    }

    public void addHumanPlayer() {
        System.out.println("Hello Player " + ((int) game.getNumberOfPlayers() + 1) + ". What is your name: ");
        String playerInputStr = input.nextLine();
        playerInputStr = playerInputStr.replaceAll("\\s", "");
        playerInputStr = playerInputStr.replaceAll("\\\\", "");
        Player newPlayer = new HumanPlayer();
        newPlayer.setPlayerName(playerInputStr);
        game.addPlayer(newPlayer);
    }

    public void printOpponentMenu() {
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

    public void selectedOpponent() {
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
        String menuInputStr = input.nextLine();
        menuInputStr = menuInputStr.replaceAll("\\s", "").toLowerCase();
        System.out.println("\n\n");
        if (menu.contains(menuInputStr)) {
            givenValidMenuInput = true;
            return menu.indexOf(menuInputStr);
        } else {
            System.out.println("Invalid input. Please provide valid response.");
            return 2;
        }
    }
}
