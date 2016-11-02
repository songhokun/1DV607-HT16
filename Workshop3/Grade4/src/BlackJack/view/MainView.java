package BlackJack.view;

import java.io.IOException;
import BlackJack.view.SettingsView.Language;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainView {

	private Image backgroundImage = new Image("https://lh3.ggpht.com/zKR6s9Gaqqa4XYnscTMiaH6o1gM5mlIjueiNwBbSt8hq1dGar7zZRHT9FMWCNwNjvQ=h900");

	
	public void DisplaySettingspage(Stage stage, Label settings) {
		stage = (Stage) settings.getScene().getWindow();
		DisplayPage(stage, "Settingspage");
	}

	public void DisplayGamepage(Stage stage, Label play) {
		stage = (Stage) play.getScene().getWindow();
		DisplayPage(stage, "Gamepage");
	}

	public void SetPageLanguage(Language currentLanguage, Label play, Label settings, Label quit) {
		switch (currentLanguage) {
		case English:
			play.setText("PLAY");
			settings.setText("SETTINGS");
			quit.setText("QUIT");
			break;
		case Swedish:
			play.setText("SPEL");
			settings.setText("INSTÄLLINGAR");
			quit.setText("AVSLUTA");
			break;
		}
	}

	public void SetBackgroundImage(ImageView imageView) {
		imageView.setImage(backgroundImage);
	}

	public void DisplayMainView(Stage stage) {
		DisplayPage(stage, "Mainpage");
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