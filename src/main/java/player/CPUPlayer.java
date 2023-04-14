package player;

public class CPUPlayer extends Player{

    public CPUPlayer(){
        setPlayerName("CPU");
    }
    @Override
    public int chooseWeapon() {
        return (int)(Math.random()*2);
    }
}
