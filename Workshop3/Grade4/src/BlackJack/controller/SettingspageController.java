package BlackJack.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SettingspageController extends MainController {

	@FXML private Label gameMode;
	@FXML private Label language;
	@FXML private Label english;
	@FXML private Label swedish;
	@FXML private Label back;
	@FXML private ChoiceBox<EnglishGameMode> gameModeChoices;
	@FXML ImageView imageView;

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setPageLanguage();
		
		super.image = new Image("http://www.casinoonline.se/images/blackjack.jpg");
		imageView.setImage(image);
		
		gameModeChoices.setItems(FXCollections.observableArrayList(EnglishGameMode.values()));
		gameModeChoices.setOnAction(e -> super.selectedGame = gameModeChoices.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	public void displayMainpage(){
		super.stage = (Stage) back.getScene().getWindow();
		super.GoToPage("Mainpage");
	}

	@FXML
	public void changeToEnglish() {
		currentLanguage = Language.English;
		
		language.setText("Language");
		back.setText("Back");
		gameMode.setText("Game Mode");
		english.setText("English");
		swedish.setText("Swedish");
	}

	@FXML
	public void changeToSwedish() {
		currentLanguage = Language.Swedish;
		
		language.setText("Språk");
		back.setText("Tillbacka");
		gameMode.setText("Spel Mode");
		english.setText("Engleska");
		swedish.setText("Svenska");
	}
	
	private void setPageLanguage() {
		switch (currentLanguage) {
		case English:
			changeToEnglish();
			break;
		case Swedish:
			changeToSwedish();
			break;
		}
	}
}