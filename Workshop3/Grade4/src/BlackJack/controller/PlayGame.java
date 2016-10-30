package BlackJack.controller;

public class PlayGame {

	public enum Language {English, Swedish}

	public enum GameMode {
		Basic_AmericanStyleGame_With_DealerAdvantage, 
		Basic_AmericanStyleGame_With_PlayerAdvantage, 
		Soft17_AmericanStyleGame_With_DealerAdvantage, 
		Soft17_AmericanStyleGame_With_PlayerAdvantage,

		Basic_InternationalStyleGame_With_DealerAdvantage, 
		Basic_InternationalStyleGame_With_PlayerAdvantage, 
		Soft17_InternationalStyleGame_With_DealerAdvantage, 
		Soft17_InternationalStyleGame_With_PlayerAdvantage
	}
}
