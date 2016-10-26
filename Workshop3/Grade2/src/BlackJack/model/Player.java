package BlackJack.model;

import java.util.List;

import BlackJack.model.rules.IHitStrategy;
import BlackJack.model.rules.INewGameStrategy;
import BlackJack.model.rules.IWinStrategy;
import BlackJack.model.rules.RulesFactory;

import java.util.LinkedList;

public class Player {

	private List<Card> m_hand;
	protected final int g_maxScore = 21;
	protected INewGameStrategy m_newGameRule;
	protected IHitStrategy m_hitRule;
	protected IWinStrategy m_winRule;
	
	public Player(RulesFactory a_rulesFactory) {

		m_newGameRule = a_rulesFactory.getNewGameRule();
		m_hitRule = a_rulesFactory.getHitRule();
		m_winRule = a_rulesFactory.getWinRule();
		m_hand = new LinkedList<Card>();
		
	}

	public void dealCard(Card a_addToHand) {
		m_hand.add(a_addToHand);
	}

	public Iterable<Card> getHand() {
		return m_hand;
	}

	public void clearHand() {
		m_hand.clear();
	}

	public void showHand() {
		for (Card c : m_hand) {
			c.show(true);
		}
	}
	public int getG_maxScore(){
		return g_maxScore;
	}
	public int calcScore(){
		return m_newGameRule.calcScore(this);
	}
	/*
	public int calcScore() {
		// the number of scores is dependent on the number of scorable values
		// as it seems there is no way to do this check at compile time in java
		// ?!
		// cardScores[13] = {...};
		int cardScores[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";

		int score = 0;

		for (Card c : getHand()) {
			if (c.getValue() != Card.Value.Hidden) {
				score += cardScores[c.getValue().ordinal()];
			}
		}

		if (score > g_maxScore) {
			for (Card c : getHand()) {
				if (c.getValue() == Card.Value.Ace && score > g_maxScore) {
					score -= 10;
				}
			}
		}
		return score;
	}
	*/
}