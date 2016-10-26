package BlackJack.model.rules;

import BlackJack.model.Player;

public interface IWinStrategy {

	boolean GetWinner(Player a_player, Player a_dealer, int g_maxScore);
}
