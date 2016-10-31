package BlackJack.model.rules;

public class GameRuleVisitor implements IGameVisitor {

	private BlackJack.view.IView view;

	public GameRuleVisitor(BlackJack.view.IView view) {
		this.view = view;
	}

	@Override
	public void VistNewGameStrategy(INewGameStrategy a_newGameStrategy) {
		view.DisplayCurrentNewGameRule(a_newGameStrategy.getClass().getSimpleName());
	}

	@Override
	public void VisitHitStrategy(IHitStrategy a_hitStrategy) {
		view.DisplayCurrentHitRule(a_hitStrategy.getClass().getSimpleName());
	}

	@Override
	public void VisitWinStrategy(IWinStrategy a_winStrategy) {
		view.DisplayCurrentWinRule(a_winStrategy.getClass().getSimpleName());
	}
}
