package BlackJack.model;

import BlackJack.model.rules.*;

public class Dealer extends Player {

	private Deck m_deck;
	
	public Dealer(RulesFactory a_rulesFactory) {
		super(a_rulesFactory);
		/*
		 * for(Card c : m_deck.GetCards()) { c.Show(true); System.out.println(""
		 * + c.GetValue() + " of " + c.GetColor()); }
		 */
	}

	public boolean newGame(Player a_player) {
		if (m_deck == null || isGameOver()) {
			m_deck = new Deck();
			clearHand();
			a_player.clearHand();
			return m_newGameRule.newGame(m_deck, this, a_player);
		}
		return false;
	}

	public boolean hit(Player a_player) {
		if (m_deck != null && m_newGameRule.calcScore(a_player) < g_maxScore && !isGameOver()) {
			Card c;
			c = m_deck.getCard();
			c.show(true);
			a_player.dealCard(c);
			return true;
		}
		return false;
	}

	public boolean stand(Player a_player) {
		if (m_deck != null) {
			showHand();

			for (Card c : getHand()) {
				c.show(true);
			}

			while (m_hitRule.doHit(this)) {
				Card c = m_deck.getCard();
				c.show(true);
				dealCard(c);
			}
			return true;
		}
		return false;
	}
	public boolean isDealerWinner(Player a_player) {
		/*
		if (a_player.calcScore() > g_maxScore) {
			return true;
		} else if (calcScore() > g_maxScore) {
			return false;
		}
		return calcScore() >= a_player.calcScore();
		*/
		return m_winRule.isDealerWinner(this, a_player);
	}

	public boolean isGameOver() {
		if (m_deck != null && m_hitRule.doHit(this) != true) {
			return true;
		}
		return false;
	}
}