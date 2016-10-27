package BlackJack.model.rules;

import BlackJack.model.Player;

class BasicWinStrategy implements IWinStrategy {

	public boolean GetWinner(Player a_player, Player a_dealer, int g_maxScore) {
		if (a_player.CalcScore() > g_maxScore) {
			return true;
		} else if (a_dealer.CalcScore() > g_maxScore) {
			return false;
		}
		return a_dealer.CalcScore() >= a_player.CalcScore();
	}
}