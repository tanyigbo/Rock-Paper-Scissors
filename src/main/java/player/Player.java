package player;

import java.util.List;

public abstract class Player {
    private String playerName;
    private int winCount;
    private int loseCount;
    private List<List<String>> gameHistory;

    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String name){
        this.playerName = name;
    }
    public int getWinCount(){
        return winCount;
    }
    public void addWin(){
        winCount++;
    }
    public int getLoseCount(){
        return loseCount;
    }
    public void addLose(){
        loseCount++;
    }

    public abstract int chooseWeapon();
}
