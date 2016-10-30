package BlackJack.model.rules;

public interface IGameVisitor {

	public String VistNewGameStrategy(INewGameStrategy a_newGameStrategy);

	public String VisitHitStrategy(IHitStrategy a_hitStrategy);

	public String VisitWinStrategy(IWinStrategy a_winStrategy);
}
