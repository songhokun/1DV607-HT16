package BlackJack.model.rules;

class Soft17InternationalStyleWithPlayerAdvantange implements IGame {

	@Override
	public INewGameStrategy GetNewGameStrategy() {
		return new InternationalNewGameStrategy();
	}

	@Override
	public IHitStrategy GetHitStrategy() {
		return new Soft17HitStrategy();
	}
	
	@Override
	public IWinStrategy GetWinStrategy() {
		return new PlayerWinStrategy();
	}
}
