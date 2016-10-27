package BlackJack.model.rules;

import BlackJack.model.Player;

class Soft17HitStrategy extends BasicHitStrategy implements IHitStrategy{

	@Override	
	public boolean DoHit(Player a_dealer) {
		return super.DoHit(a_dealer) || a_dealer.ContainsSoft17();
	}
}