package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Player {

    private String playerName;
    private String relativeSaveLocation;
    private List<List<String>> gameHistory = new ArrayList<>();

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
        this.relativeSaveLocation = "src/main/java/resources/" + name + ".txt";
    }

    public String getRelativeSaveLocation() {
        return relativeSaveLocation;
    }

    public void importGame(List<String> game) {
        gameHistory.add(game);
    }

    public void addGameToHistory(String dateTime, String opponent, String roundResult, String weapon) {
        List<String> gameData = Arrays.asList(dateTime, opponent, roundResult, weapon);
        gameHistory.add(gameData);
    }

    public List<List<String>> getGameHistory() {
        return gameHistory;
    }

    public abstract int chooseWeapon();
}
