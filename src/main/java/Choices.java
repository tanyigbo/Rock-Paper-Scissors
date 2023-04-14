import java.util.HashMap;
import java.util.Map;

public class Choices {
    private static final Map<Integer,String> CHOICES;

    static {
        CHOICES = new HashMap<>();
        CHOICES.put(0,"Rock");
        CHOICES.put(1,"Paper");
        CHOICES.put(2,"Scissors");
    }

    public String getWeapon(int choiceID){
        return CHOICES.get(choiceID);
    }
}
