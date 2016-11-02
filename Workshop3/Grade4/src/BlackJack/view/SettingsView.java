package BlackJack.view;

import java.io.IOException;
import BlackJack.model.rules.GameFactory;
import BlackJack.model.rules.IGame;
import BlackJack.model.rules.IGameFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SettingsView {

	public enum Language {English, Swedish}
	public enum GameMode {Basic_AmericanStyleGame_With_DealerAdvantage, Basic_AmericanStyleGame_With_PlayerAdvantage, 
						  Basic_InternationalStyleGame_With_DealerAdvantage, Basic_InternationalStyleGame_With_PlayerAdvantage,
						  Soft17_AmericanStyleGame_With_DealerAdvantage, Soft17_AmericanStyleGame_With_PlayerAdvantage, 
						  Soft17_InternationalStyleGame_With_DealerAdvantage, Soft17_InternationalStyleGame_With_PlayerAdvantage
	}
	
	private Image backgroundImage = new Image("http://www.casinoonline.se/images/blackjack.jpg");
	private static GameMode gameMode = GameMode.Basic_AmericanStyleGame_With_DealerAdvantage; // default
	
	
	public void GoBack(Stage stage, Label back) {
		stage = (Stage) back.getScene().getWindow();
		DisplayPage(stage, "Mainpage");
	}

	public void ChangeToEnglish(Label language, Label back, Label gameMode, Label english, Label swedish) {
		language.setText("Language");
		back.setText("Back");
		gameMode.setText("Game Mode");
		english.setText("English");
		swedish.setText("Swedish");
	}

	public void ChangeToSwedish(Label language, Label back, Label gameMode, Label english, Label swedish) {
		language.setText("Språk");
		back.setText("Tillbacka");
		gameMode.setText("Spel Mode");
		english.setText("Engleska");
		swedish.setText("Svenska");
	}

	public void SetPageLanguage(Language currentLanguage, Label language, Label back, Label gameMode, Label english, Label swedish) {
		switch (currentLanguage) {
		case English:
			ChangeToEnglish(language, back, gameMode, english, swedish);
			break;
		case Swedish:
			ChangeToSwedish(language, back, gameMode, english, swedish);
			break;
		}
	}

	public void SetBackgroundImage(ImageView imageView) {
		imageView.setImage(backgroundImage);
	}

	public void SetAllGameModes(ChoiceBox<GameMode> gameModeChoices){
		gameModeChoices.setItems(FXCollections.observableArrayList(GameMode.values()));
		gameModeChoices.getSelectionModel().select(gameMode); // default
	}
	
	public IGame GetSelectedIGame(){
		IGame IGame = null;
		IGameFactory factory = new GameFactory();
		switch(gameMode){
		case Basic_AmericanStyleGame_With_DealerAdvantage:
			IGame = factory.CreateBasicAmericanStyleWithDealerAdvantange();
			break;
		case Basic_AmericanStyleGame_With_PlayerAdvantage:
			IGame = factory.CreateBasicAmericanStyleWithPlayerAdvantange();
			break;
		case Basic_InternationalStyleGame_With_DealerAdvantage:
			IGame = factory.CreateBasicInternationalStyleWithDealerAdvantange();
			break;
		case Basic_InternationalStyleGame_With_PlayerAdvantage:
			IGame = factory.CreateBasicInternationalStyleWithPlayerAdvantange();
			break;
		case Soft17_AmericanStyleGame_With_DealerAdvantage:
			IGame = factory.CreateSoft17AmericanStyleWithDealerAdvantange();
			break;
		case Soft17_AmericanStyleGame_With_PlayerAdvantage:
			IGame = factory.CreateSoft17AmericanStyleWithPlayerAdvantange();
			break;
		case Soft17_InternationalStyleGame_With_DealerAdvantage:
			IGame = factory.CreateSoft17InternationalStyleWithDealerAdvantange();
			break;
		case Soft17_InternationalStyleGame_With_PlayerAdvantage:
			IGame = factory.CreateSoft17InternationalStyleWithPlayerAdvantange();
			break;
		}
		return IGame;
	}
	
	public void SetGameMode(ChoiceBox<GameMode> gameModeChoices){
		gameMode = gameModeChoices.getSelectionModel().getSelectedItem();
	}
	
	private void DisplayPage(Stage stage, String path) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/BlackJack/view/" + path + ".fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}