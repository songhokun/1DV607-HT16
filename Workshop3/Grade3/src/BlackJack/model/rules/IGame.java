package BlackJack.model.rules;

public interface IGame {

	public INewGameStrategy GetNewGameStrategy();

	public IHitStrategy GetHitStrategy();

	public IWinStrategy GetWinStrategy();
}