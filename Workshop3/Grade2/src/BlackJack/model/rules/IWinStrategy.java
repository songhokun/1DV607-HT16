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
public interface IWinStrategy {
	boolean isDealerWinner(Dealer a_dealer, Player a_player);
}
