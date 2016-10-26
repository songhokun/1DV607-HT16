package BlackJack.model.rules;

import BlackJack.model.Deck;
import BlackJack.model.Dealer;
import BlackJack.model.Player;
import BlackJack.model.Card;

class AmericanNewGameStrategy implements INewGameStrategy {

	public boolean newGame(Deck a_deck, Dealer a_dealer, Player a_player) {
		Card c;

		c = a_deck.getCard();
		c.show(true);
		a_player.dealCard(c);

		c = a_deck.getCard();
		c.show(true);
		a_dealer.dealCard(c);

		c = a_deck.getCard();
		c.show(true);
		a_player.dealCard(c);

		c = a_deck.getCard();
		c.show(false);
		a_dealer.dealCard(c);

		return true;
	}
	public int calcScore(Player a_player){
		int cardScores[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";

		int score = 0;

		for (Card c : a_player.getHand()) {
			if (c.getValue() != Card.Value.Hidden) {
				score += cardScores[c.getValue().ordinal()];
			}
		}

		if (score > a_player.getG_maxScore()) {
			for (Card c : a_player.getHand()) {
				if (c.getValue() == Card.Value.Ace && score > a_player.getG_maxScore()) {
					score -= 10;
				}
			}
		}
		return score;
	}
}