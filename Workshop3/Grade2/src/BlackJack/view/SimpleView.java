package BlackJack.view;

import java.util.Scanner;

import BlackJack.controller.PlayGame.Command;

public class SimpleView implements IView 
{
	public void displayWelcomeMessage()
	{
		for(int i = 0; i < 50; i++) {System.out.print("\n");}; 
		System.out.println("Hello Black Jack World");
		System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
	
	}
	public Command getInput()
    {
		Scanner scan = new Scanner(System.in);
		String input = scan.next();
		
		switch(input){
		case "p":
			return Command.NEWGAME;
		case "h":
			return Command.HIT;
		case "s":
			return Command.STAND;
		case "q":
			return Command.QUIT;
		default:
			break;
		}			
		return null;
    }
	
	public void displayCard(BlackJack.model.Card a_card)
	{
		System.out.println("" + a_card.getValue() + " of " + a_card.getColor());
	}

    public void displayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
    	displayHand("Player", a_hand, a_score);
    }

    public void displayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        displayHand("Dealer", a_hand, a_score);
    }

    private void displayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        System.out.println(a_name + " Has: ");
        for(BlackJack.model.Card c : a_hand)
        {
            displayCard(c);
        }
        System.out.println("Score: " + a_score);
        System.out.println("");
    }

    public void displayGameOver(boolean a_dealerIsWinner)
    {
        System.out.println("GameOver: ");
        if (a_dealerIsWinner)
        {
            System.out.println("Dealer Won!");
        }
        else
        {
            System.out.println("You Won!");
        }
        
    }
}