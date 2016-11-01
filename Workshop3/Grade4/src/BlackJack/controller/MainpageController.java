package BlackJack.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainpageController extends MainController {

	@FXML private Label play;
	@FXML private Label settings;
	@FXML private Label quit;
	@FXML private ImageView imageView;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getGame();
		setPageLanguage();
		 
		super.image = new Image("https://lh3.ggpht.com/zKR6s9Gaqqa4XYnscTMiaH6o1gM5mlIjueiNwBbSt8hq1dGar7zZRHT9FMWCNwNjvQ=h900");
		imageView.setImage(image);
		
		quit.setOnMouseClicked(e -> super.quit());
	}

	@FXML
	public void displaySettingspage() {
		super.stage = (Stage) settings.getScene().getWindow();
		super.GoToPage("Settingspage");
	}

	@FXML
	public void displayGamepage() {
		super.stage = (Stage) play.getScene().getWindow();
		super.GoToPage("Gamepage");
	}

	
	private void setPageLanguage() {
		switch (currentLanguage) {
		case English:
			play.setText("Play");
			settings.setText("Settings");
			quit.setText("Quit");
			break;
		case Swedish:
			play.setText("Spel");
			settings.setText("Inställingar");
			quit.setText("Avsluta");
			break;
		}
	}
}