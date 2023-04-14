package player;

public class CPUPlayer extends Player{

    @Override
    public int chooseWeapon() {
        return (int)(Math.random()*2);
    }
}
