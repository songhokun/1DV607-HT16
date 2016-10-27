package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;
import BlackJack.model.Card;

class AmericanNewGameStrategy implements INewGameStrategy {

	public boolean NewGame(Dealer a_dealer, Player a_player) {
		Card c;

		c = a_dealer.getCardFromDeck(true);
		a_player.DealCard(c);

		c = a_dealer.getCardFromDeck(true);
		a_dealer.DealCard(c);

		c = a_dealer.getCardFromDeck(true);
		a_player.DealCard(c);

		c = a_dealer.getCardFromDeck(false);
		a_dealer.DealCard(c);

		return true;
	}
}