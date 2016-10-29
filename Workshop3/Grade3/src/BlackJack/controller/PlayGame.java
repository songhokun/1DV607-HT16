package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;
import BlackJack.model.IObserver;

public class PlayGame implements IObserver {

	public enum Command{PLAY, HIT, STAND, QUIT, INVALID}
	private Game a_game;
	private IView a_view;

	
	public PlayGame(Game a_game, IView a_view) {
		this.a_game = a_game;
		this.a_view = a_view;
		Subscribe(this);
	}

	public boolean Play() {
		this.a_view.DisplayWelcomeMessage();
		Command input = a_view.GetInput();
		switch (input) {
		case PLAY:
			a_game.NewGame();
			break;
		case HIT:
			a_game.Hit();
			break;
		case STAND:
			a_game.Stand();
			break;
		default:
			break;
		}
		return input != Command.QUIT;
	}

	@Override
	public void PlayerGetNewCard() {
		try {
			Thread.sleep(2000);
			a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
			Thread.sleep(2000);
			a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
			Thread.sleep(1000);
			
			if (a_game.IsGameOver()) {
				a_view.DisplayGameOver(a_game.IsDealerWinner());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void Subscribe(IObserver a_subscriber) {
		this.a_game.AddSubscribers(a_subscriber);
	}
}