package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Player {
    private String playerName;
    private String relativeSaveLocation;
    private int winCount;
    private int loseCount;
    private List<List<String>> gameHistory = new ArrayList<>();

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
        this.relativeSaveLocation = name + ".txt";
    }

    public String getRelativeSaveLocation() {
        return relativeSaveLocation;
    }

    public int getWinCount() {
        return winCount;
    }

    private void addWin() {
        winCount++;
    }

    public int getLoseCount() {
        return loseCount;
    }

    private void addLose() {
        loseCount++;
    }

    public void addGameToHistory(String dateTime, String opponent, String roundResult, String weapon) {
        List<String> gameData = Arrays.asList(dateTime, opponent, roundResult, weapon);
        gameHistory.add(gameData);
        if (roundResult.equals("Win")) {
            addWin();
        } else {
            addLose();
        }
    }

    public List<List<String>> getGameHistory() {
        return gameHistory;
    }


    public abstract int chooseWeapon();
}
