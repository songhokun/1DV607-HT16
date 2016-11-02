package BlackJack.view;

import BlackJack.model.rules.IGameVisitor;
import BlackJack.model.rules.IHitStrategy;
import BlackJack.model.rules.INewGameStrategy;
import BlackJack.model.rules.IWinStrategy;
import javafx.scene.control.TextArea;

public class GameRuleVisitor implements IGameVisitor {

	private TextArea view;

	public GameRuleVisitor(TextArea view) {
		this.view = view;
	}
	@Override
	public void VisitAmericanNewGameStrategy(INewGameStrategy am_newGameStrategy) {	
		view.setText("Game Strategy: American new game\n");
	}

	@Override
	public void VisitInternationalNewGameStrategy(INewGameStrategy int_newGameStrategy) {
		view.setText("Game Strategy: International new game\n");	
	}

	@Override
	public void VisitBasicHitStrategy(IHitStrategy bas_hitStrategy) {	
		view.setText(view.getText() + "Hit Strategy: Basic\n");
	}
	
	@Override
	public void VisitSoft17HitStrategy(IHitStrategy soft17_hitStrategy) {
		view.setText(view.getText() + "Hit Strategy: Soft-17\n");	
	}


	@Override
	public void VisitDealerWinStrategy(IWinStrategy deal_winStrategy) {
		view.setText(view.getText() + "Win Strategy: Dealer win");
	}

	@Override
	public void VisitPlayerWinStrategy(IWinStrategy deal_winStrategy) {
		view.setText(view.getText() + "Win Strategy: Player win");	
	}
}
