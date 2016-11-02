package BlackJack.view;

import BlackJack.model.rules.IGameVisitor;
import BlackJack.model.rules.IHitStrategy;
import BlackJack.model.rules.INewGameStrategy;
import BlackJack.model.rules.IWinStrategy;

public class GameRuleVisitor implements IGameVisitor {

	private BlackJack.view.IView view;

	public GameRuleVisitor(BlackJack.view.IView view) {
		this.view = view;
	}

	/*
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
	*/
	
	@Override
	public void VisitAmericanNewGameStrategy(INewGameStrategy am_newGameStrategy) {
		view.DisplayCurrentNewGameRule("American new game strategy");
		
	}


	@Override
	public void VisitInternationalNewGameStrategy(INewGameStrategy int_newGameStrategy) {
		view.DisplayCurrentNewGameRule("International new game strategy");
		
	}


	@Override
	public void VisitBasicHitStrategy(IHitStrategy bas_hitStrategy) {
		view.DisplayCurrentNewGameRule("Basic hit strategy");
		
	}


	@Override
	public void VisitSoft17HitStrategy(IHitStrategy soft17_hitStrategy) {
		view.DisplayCurrentNewGameRule("Soft-17 hit strategy");
		
	}


	@Override
	public void VisitDealerWinStrategy(IWinStrategy deal_winStrategy) {
		view.DisplayCurrentNewGameRule("Dealer win strategy");
		
	}

	@Override
	public void VisitPlayerWinStrategy(IWinStrategy deal_winStrategy) {
		view.DisplayCurrentWinRule("Player win strategy");
		
	}
}
