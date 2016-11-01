package BlackJack.model.rules;

import javafx.scene.control.TextArea;

public class GameRuleVisitor implements IGameVisitor {

	private TextArea view;

	public GameRuleVisitor(TextArea view) {
		this.view = view;
	}

	@Override
	public void VistNewGameStrategy(INewGameStrategy a_newGameStrategy) {
		view.setText(a_newGameStrategy.getClass().getSimpleName() + "\n");
	}

	@Override
	public void VisitHitStrategy(IHitStrategy a_hitStrategy) {
		view.setText(view.getText() + a_hitStrategy.getClass().getSimpleName() + "\n");
	}

	@Override
	public void VisitWinStrategy(IWinStrategy a_winStrategy) {
		view.setText(view.getText() + a_winStrategy.getClass().getSimpleName());
	}
}
