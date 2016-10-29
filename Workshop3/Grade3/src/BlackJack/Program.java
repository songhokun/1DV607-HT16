package BlackJack;

import BlackJack.controller.PlayGame;
import BlackJack.model.Game;
import BlackJack.model.rules.GameFactory;
import BlackJack.model.rules.GameRuleVisitor;
import BlackJack.model.rules.IGame;
import BlackJack.model.rules.IGameFactory;
import BlackJack.model.rules.IGameVisitor;
import BlackJack.view.IView;
import BlackJack.view.SimpleView;

public class Program {

	public static void main(String[] a_args) {
		IGameFactory factory = new GameFactory();
		IGame game = factory.CreateBasicAmericanStyleWithDealerAdvantange();
		IGameVisitor visitor = new GameRuleVisitor();
		Game g = new Game(game, visitor);
		IView v = new SimpleView();// new SwedishView();
		PlayGame ctrl = new PlayGame(g, v);
		while (ctrl.Play());
	}
}