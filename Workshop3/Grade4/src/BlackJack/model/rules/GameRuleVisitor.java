package BlackJack.model.rules;

public class GameRuleVisitor implements IGameVisitor {

	@Override
	public String VistNewGameStrategy(INewGameStrategy a_newGameStrategy) {
		return a_newGameStrategy.getClass().getSimpleName();
	}

	@Override
	public String VisitHitStrategy(IHitStrategy a_hitStrategy) {
		return a_hitStrategy.getClass().getSimpleName();
	}

	@Override
	public String VisitWinStrategy(IWinStrategy a_winStrategy) {
		return a_winStrategy.getClass().getSimpleName();
	}
}
