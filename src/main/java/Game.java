import player.PlayerChoices;
import player.Player;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements PlayerChoices {

    private static final List<String> playerEndGameResult = Arrays.asList("Win", "Lose", "Draw");
    boolean hasBothPlayers;
    private List<Player> players = new ArrayList<>();

    public Game() {
        hasBothPlayers = false;
    }

    public void addPlayer(Player player) {
        if (players.size() < 2) {
            players.add(player);
            checkHasTwoPlayers();
        }
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public void checkHasTwoPlayers() {
        hasBothPlayers = players.size() == 2;
    }

    public int playRound() {
        int player0Selection = players.get(0).chooseWeapon();
        int player1Selection = players.get(1).chooseWeapon();
        if (player0Selection < 3 && player1Selection < 3) {
            int roundResult = determineWinner(player0Selection, player1Selection);
            printGameResults(player0Selection, player1Selection, roundResult);
            updatePlayerRecord(player0Selection, roundResult);
            return 0;
        } else {
            return 2;
        }
    }

    /**
     * determineWinner takes the players inputs as two integer arguments
     * to determines the winner of the round by calculating the value difference
     * {0=Rock, 1=Paper, 2=Scissors}
     *
     * @param input0 : integer value for player 1's choice
     * @param input1 : integer value for player 1's choice
     * @return the winner of the round or 2 if it is a draw
     */
    public int determineWinner(int input0, int input1) {
        return switch (input0 - input1) {
            case -2, 1 -> 0;
            case -1, 2 -> 1;
            default -> 2;
        };
    }

    public void printGameResults(int input0, int input1, int winner) {
        System.out.println(players.get(0).getPlayerName() + " chose " + playerChoices.get(input0));
        System.out.println(players.get(1).getPlayerName() + " chose " + playerChoices.get(input1));
        if(winner ==2){
            System.out.println("The round ends in a draw.\n");
        }
        else{
            System.out.println(players.get(winner).getPlayerName() + " wins.\n");
        }

    }


    public void updatePlayerRecord(int player0Selection, int winner) {
        String gameTimeStamp = LocalDateTime.now().withNano(0).toString();
        players.get(0).addGameToHistory(
                gameTimeStamp,
                players.get(1).getPlayerName(),
                playerEndGameResult.get(winner),
                playerChoices.get(player0Selection));
    }

    public List<List<String>> getPlayerHistory() {
        return players.get(0).getGameHistory();
    }

    public void importGameHistory() {
        Player player = players.get(0);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    player.getRelativeSaveLocation()));
            try {
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    List<String> data = Arrays.stream(currentLine.split(" ")).toList();
                    player.importGame(data);
                    currentLine = reader.readLine();
                }
                reader.close();
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Welcome fresh meat.");
        }
    }

    public void exportGameHistory() {
        Player player = players.get(0);
        try{
            FileWriter fileWriter = new FileWriter(player.getRelativeSaveLocation(),false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(List<String> game: player.getGameHistory()){
                String gameOutput = String.join(" ", game) + "\n";
                writer.write(gameOutput);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Could not save your game history.");
        }
    }
}
