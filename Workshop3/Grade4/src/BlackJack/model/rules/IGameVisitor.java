package BlackJack.model.rules;

public interface IGameVisitor {

	public void VistNewGameStrategy(INewGameStrategy a_newGameStrategy);

	public void VisitHitStrategy(IHitStrategy a_hitStrategy);

	public void VisitWinStrategy(IWinStrategy a_winStrategy);
}
