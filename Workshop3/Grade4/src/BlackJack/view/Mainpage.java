package BlackJack.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Mainpage implements Initializable {

	@FXML private Label play;
	@FXML private Label settings;
	@FXML private Label quit;
	@FXML ImageView imageView;
	private Image image;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		image = new Image("https://lh3.ggpht.com/zKR6s9Gaqqa4XYnscTMiaH6o1gM5mlIjueiNwBbSt8hq1dGar7zZRHT9FMWCNwNjvQ=h900");
		imageView.setImage(image);
	}
	
	@FXML 
	public void displaySettingspage() {
		try {
			Stage stage = (Stage) settings.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/BlackJack/view/Settingspage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void quit() {
		System.exit(1);
	}
}