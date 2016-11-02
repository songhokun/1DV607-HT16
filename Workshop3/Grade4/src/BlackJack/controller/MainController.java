package BlackJack.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import BlackJack.model.Game;
import BlackJack.model.rules.GameFactory;
import BlackJack.model.rules.IGame;
import BlackJack.model.rules.IGameFactory;
import BlackJack.model.rules.IGameVisitor;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainController implements Initializable{

	public enum Language {English, Swedish}

	public enum EnglishGameMode {
		Basic_AmericanStyleGame_With_DealerAdvantage, 
		Basic_AmericanStyleGame_With_PlayerAdvantage, 
		Soft17_AmericanStyleGame_With_DealerAdvantage, 
		Soft17_AmericanStyleGame_With_PlayerAdvantage,

		Basic_InternationalStyleGame_With_DealerAdvantage, 
		Basic_InternationalStyleGame_With_PlayerAdvantage, 
		Soft17_InternationalStyleGame_With_DealerAdvantage, 
		Soft17_InternationalStyleGame_With_PlayerAdvantage
	}
	
	// My swedish is not good. You can translate these names.
	public enum SwedishGameMode {
		Basic_Amerikansk_stil_spel_med_Givarens_fördel,
		Basic_Amerikansk_stil_spel_med_Spelarens_fördel, 
		Soft17_Amerikansk_stil_spel_med_Givarens_fördel, 
		Soft17_Amerikansk_stil_spel_med_Spelarens_fördel,

		Basic_InternationellStilSpel_Med_Givarens_fördel, 
		Basic_InternationellStilSpel_Med_Spelarens_fördel, 
		Soft17_InternationellStilSpel_Med_Givarens_fördel, 
		Soft17_InternationellStilSpel_Med_Spelarens_fördel
	}
	
	protected static Language currentLanguage = Language.English;
	private Parent root;
	protected Stage stage;
	private Scene scene;
	protected Image image;
	protected IGame igame;
	protected TextArea rules;
	protected IGameFactory factory; 
	protected IGameVisitor visitor; 
	protected static EnglishGameMode selectedGame = EnglishGameMode.Basic_AmericanStyleGame_With_DealerAdvantage;
	protected Game game;
	
	
	public MainController(){
		getGame();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	protected void GoToPage(String path) {
		try {
			root = FXMLLoader.load(getClass().getResource("/BlackJack/view/" + path + ".fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void quit() {
		System.exit(1);
	}
	
	protected void getGame(){
		factory = new GameFactory(); 
		rules = new TextArea();
		
		switch(selectedGame){
		case Basic_AmericanStyleGame_With_DealerAdvantage:
			igame = factory.CreateBasicAmericanStyleWithDealerAdvantange();
			break;
		case Basic_AmericanStyleGame_With_PlayerAdvantage:
			igame = factory.CreateBasicAmericanStyleWithPlayerAdvantange();
			break;
		case Basic_InternationalStyleGame_With_DealerAdvantage:
			igame = factory.CreateBasicInternationalStyleWithDealerAdvantange();
			break;
		case Basic_InternationalStyleGame_With_PlayerAdvantage:
			igame = factory.CreateBasicInternationalStyleWithPlayerAdvantange();
			break;
		case Soft17_AmericanStyleGame_With_DealerAdvantage:
			igame = factory.CreateSoft17AmericanStyleWithDealerAdvantange();
			break;
		case Soft17_AmericanStyleGame_With_PlayerAdvantage:
			igame = factory.CreateSoft17AmericanStyleWithPlayerAdvantange();
			break;
		case Soft17_InternationalStyleGame_With_DealerAdvantage:
			igame = factory.CreateSoft17InternationalStyleWithDealerAdvantange();
			break;
		case Soft17_InternationalStyleGame_With_PlayerAdvantage:
			igame = factory.CreateSoft17InternationalStyleWithPlayerAdvantange();
			break;
		default:
			igame = factory.CreateBasicAmericanStyleWithDealerAdvantange();
			break;
		}
		rules = new TextArea();
		visitor = new GameRuleVisitor(rules);
		igame.Accept(visitor);
		game = new Game(igame);
	}
	
	public Scene getMainScence(){
		try {
			 root = FXMLLoader.load(getClass().getResource("/BlackJack/view/Mainpage.fxml"));
			 scene = new Scene(root, 850, 700);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scene;
	}
}