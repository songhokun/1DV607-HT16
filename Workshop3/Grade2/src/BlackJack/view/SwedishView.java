package BlackJack.view;

import java.util.Scanner;

import BlackJack.controller.PlayGame.Command;

public class SwedishView implements IView 
    {
        public void displayWelcomeMessage()
        {
         
            for(int i = 0; i < 50; i++) {System.out.print("\n");};

            System.out.println("Hej Black Jack Vaerlden");
            System.out.println("----------------------");
            System.out.println("Skriv 'b' for att boerja spel, 'n' for nytt kort, 's' for att stanna 'a' for att avsluta\n");
        }
        public Command getInput()
        {
    		Scanner scan = new Scanner(System.in);
    		String input = scan.next();
    		
    		switch(input){
    		case "b":
    			return Command.NEWGAME;
    		case "n":
    			return Command.HIT;
    		case "s":
    			return Command.STAND;
    		case "a":
    			return Command.QUIT;
    		default:
    			break;
    		}			
    		return null;
        }
        
        
        public void displayCard(BlackJack.model.Card a_card)
        {
            if (a_card.getColor() == BlackJack.model.Card.Color.Hidden)
            {
                System.out.println("Dolt Kort");
            }
            else
            {
                String colors[] = 
                    { "Hjaerter", "Spader", "Ruter", "Klaever" };
                String values[] =  
                    { "tvaa", "tre", "fyra", "fem", "sex", "sju", "aatta", "nio", "tio", "knekt", "dam", "kung", "ess" };
                System.out.println("" + colors[a_card.getColor().ordinal()] + " " + values[a_card.getValue().ordinal()]);
            }
        }
        public void displayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            displayHand("Spelare", a_hand, a_score);
        }
        public void displayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            displayHand("Croupier", a_hand, a_score);
        }
        public void displayGameOver(boolean a_dealerIsWinner)
        {
            System.out.println("Slut: ");
            if (a_dealerIsWinner)
            {
                System.out.println("Croupiern Vann!");
            }
            else
            {
                System.out.println("Du vann!");
            }
        }

        private void displayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            System.out.println(a_name + " Har: " + a_score);
            for(BlackJack.model.Card c : a_hand)
            {
                displayCard(c);
            }
            System.out.println("Poaeng: " + a_score);
            System.out.println("");
        }
    }