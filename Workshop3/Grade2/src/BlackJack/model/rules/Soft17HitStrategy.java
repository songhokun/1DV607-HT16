package BlackJack.model.rules;

import BlackJack.model.Player;

class Soft17HitStrategy implements IHitStrategy{

	private final int g_hitLimit = 17;
	
	@Override	
	public boolean DoHit(Player a_dealer) {
		return a_dealer.CalcScore() < g_hitLimit || a_dealer.ContainsSoft17();
	}
}