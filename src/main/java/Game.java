import player.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private static final List<String> player0GameOptions = Arrays.asList("Win", "Lose");
    private static final List<String> player1GameOptions = Arrays.asList("Lose", "Win");
    boolean hasBothPlayers;
    private List<Player> players = new ArrayList<>();
    private final Choices playerWeaponOptions = new Choices();

    public Game() {
        hasBothPlayers = false;
    }

    public void addPlayer(Player player) {
        if (players.size() < 2) {
            players.add(player);
            checkHasTwoPlayers();
        }
    }

    public Player getPlayer(int playerID) {
        if (playerID > -1 && playerID < players.size()) {
            return players.get(playerID);
        }
        return null;
    }

    public int getNumberOfPlayers(){
        return players.size();
    }

    public void checkHasTwoPlayers() {
        hasBothPlayers = players.size() == 2;
    }

//    public void checkPlayerNameCollision() {
//        if (players.get(0).getPlayerName().equals(players.get(1).getPlayerName())) {
//            hasBothPlayers = false;
//            players.remove(1);
//        }
//    }

    public int playRound() {
        int player0Choice = players.get(0).chooseWeapon();
        int player1Choice = players.get(1).chooseWeapon();
        if (player0Choice < 3 && player1Choice < 3) {
            int roundResult = determineWinner(player0Choice, player1Choice);
            updatePlayerRecord(player0Choice, player1Choice, roundResult);
            return 1;
        } else {
            return 0;
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

    public void updatePlayerRecord(int player0Choice, int player1Choice, int winner) {
        String gameTimeStamp = LocalDateTime.now().toString();
        players.get(0).addGameToHistory(
                gameTimeStamp,
                players.get(1).getPlayerName(),
                player0GameOptions.get(winner),
                playerWeaponOptions.getWeapon(player0Choice));
        players.get(1).addGameToHistory(
                gameTimeStamp,
                players.get(0).getPlayerName(),
                player1GameOptions.get(winner),
                playerWeaponOptions.getWeapon(player1Choice));
    }
}
