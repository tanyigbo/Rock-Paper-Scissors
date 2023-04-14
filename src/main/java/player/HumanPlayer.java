package player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private final List<String> validResponses = Arrays.asList("rock", "paper", "scissor", "quit");
    private boolean givenValidResponse;
    private int usersChoice;

    @Override
    public int chooseWeapon() {
        Scanner playerIn = new Scanner(System.in);
        givenValidResponse = false;
        while (!givenValidResponse) {
            System.out.println("Type 'rock', 'paper', or 'scissors' to play.");
            System.out.println("Type 'quit' to go back to the main menu.");
            String userInput = playerIn.nextLine();
            checkResponse(userInput);
        }
        playerIn.close();
        return usersChoice;
    }
    private void checkResponse(String userInput){
        userInput = userInput.replaceAll("\\s", "").toLowerCase();
        if(validResponses.contains(userInput)){
            givenValidResponse = true;
            usersChoice = validResponses.indexOf(userInput);
        }
        else {
            System.out.println("\n Please input a valid response.");
        }
    }

}
