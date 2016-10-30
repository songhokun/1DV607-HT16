package BlackJack.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import BlackJack.controller.PlayGame.GameMode;
import BlackJack.controller.PlayGame.Language;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Settingspage implements Initializable{

	@FXML private Label gameMode;
	@FXML private Label language;
	@FXML private Label back;
	@FXML private ChoiceBox<GameMode> gameModeChoices;
	@FXML private ChoiceBox<Language> languageChoices;
	@FXML ImageView imageView;
	private Image image;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		image = new Image("https://i.ytimg.com/vi/NjEikekg7a0/maxresdefault.jpg");
		imageView.setImage(image);
		
		gameModeChoices.setItems(FXCollections.observableArrayList(GameMode.values()));
		gameModeChoices.getSelectionModel().selectFirst();
		
		languageChoices.setItems(FXCollections.observableArrayList(Language.values()));
		languageChoices.getSelectionModel().selectFirst();
		
	}
	
	@FXML
	public void displayMainpage(){
		try {
			Stage stage = (Stage) back.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/BlackJack/view/Mainpage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
