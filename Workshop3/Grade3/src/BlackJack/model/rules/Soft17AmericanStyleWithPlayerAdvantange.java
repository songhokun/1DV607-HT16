package BlackJack.model.rules;

class Soft17AmericanStyleWithPlayerAdvantange implements IGame {
	
	@Override
	public INewGameStrategy GetNewGameStrategy() {
		return new AmericanNewGameStrategy();
	}

	@Override
	public IHitStrategy GetHitStrategy() {
		return new Soft17HitStrategy();
	}

	@Override
	public IWinStrategy GetWinStrategy() {
		return new PlayerWinStrategy();
	}
	
	@Override
	public void Accept(IGameVisitor a_visitor) {
		a_visitor.VistNewGameStrategy(this.GetNewGameStrategy());
		a_visitor.VisitHitStrategy(this.GetHitStrategy());
		a_visitor.VisitWinStrategy(this.GetWinStrategy());
	}
}
