package player;

public class HumanPlayer extends Player implements PlayerChoices,ScannerInput {

    private boolean givenValidResponse;
    private int usersChoice;

    public HumanPlayer(String name){
        setPlayerName(name);
    }

    @Override
    public int chooseWeapon() {
        givenValidResponse = false;
        while (!givenValidResponse) {
            System.out.println("Type 'rock', 'paper', or 'scissors' to play.");
            System.out.println("Type 'quit' to go back to the main menu.");
            String userInput = input.nextLine();
            checkResponse(userInput);
        }
        return usersChoice;
    }

    private void checkResponse(String userInput){
        userInput = userInput.replaceAll("\\s", "").toLowerCase();
        if(userInput.equals("quit")){
            givenValidResponse = true;
            usersChoice = 9;
        }
        else if(playerChoices.contains(userInput)){
            givenValidResponse = true;
            usersChoice = playerChoices.indexOf(userInput);
        }
        else {
            System.out.println("\nPlease input a valid response.\n");
        }
    }

}
