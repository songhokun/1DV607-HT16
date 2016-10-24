package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame {
	public enum Command{NEWGAME,HIT,STAND,QUIT};
	
	public boolean Play(Game a_game, IView a_view) {
		a_view.displayWelcomeMessage();
	    
		a_view.displayDealerHand(a_game.getDealerHand(), a_game.getDealerScore());
		a_view.displayPlayerHand(a_game.getPlayerHand(), a_game.getPlayerScore());
	
	    if (a_game.isGameOver())
	    {
	        a_view.displayGameOver(a_game.isDealerWinner());
	    }
	
	    Command input = a_view.getInput();
	    
	    switch(input){
		    case NEWGAME:
		    	a_game.newGame();
		    	break;
		    case HIT:
		    	a_game.hit();
		    	break;
		    case STAND:
		    	a_game.stand();
		    	break;
		    case QUIT:
		    	return false;
	    }
	    return !input.equals(Command.QUIT);
	}
}