/**
 * 
 */
package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

/**
 * @author songhokun
 *
 */
public class BasicWinStrategy implements IWinStrategy {
	
	@Override
	public boolean isDealerWinner(Dealer a_dealer, Player a_player) {
		if (a_player.calcScore() > a_dealer.getG_maxScore()) {
			return true;
		} else if (a_dealer.calcScore() > a_dealer.getG_maxScore()) {
			return false;
		}
		return a_dealer.calcScore() >= a_player.calcScore();
	}

}
