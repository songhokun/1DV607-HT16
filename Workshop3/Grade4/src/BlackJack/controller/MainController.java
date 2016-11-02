package BlackJack.controller;

import java.net.URL;
import java.util.ResourceBundle;
import BlackJack.view.GameView;
import BlackJack.view.MainView;
import BlackJack.view.SettingsView;
import BlackJack.view.SettingsView.Language;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML private Label play;
	@FXML private Label settings;
	@FXML private Label quit;
	@FXML private ImageView imageView;
	
	protected static Language currentLanguage = Language.English;
	protected Stage stage;

	protected MainView mainView;
	protected SettingsView settingsView;
	protected GameView gameView;
	
	
	public MainController(){
		mainView = new MainView();
		settingsView = new SettingsView();
		gameView = new GameView();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainView.SetPageLanguage(currentLanguage, play, settings, quit);
		mainView.SetBackgroundImage(imageView);
		play.setOnMouseClicked(e -> mainView.DisplayGamepage(stage, play));
		settings.setOnMouseClicked(e -> mainView.DisplaySettingspage(stage, settings));
		quit.setOnMouseClicked(e -> QuitGame());
		AddEffects(new Label[]{play, settings, quit});
	}

	public void Start(Stage stage) {
		this.stage = stage;
		this.stage.setResizable(false);
		mainView.DisplayMainView(this.stage);
	}
	
	protected void QuitGame(){
		System.exit(1);
	}
	
	protected void AddEffects(Label[] list){
		for(int i = 0; i < list.length; i++){
			Label temp = list[i];
			temp.setOnMouseEntered(e ->  SetHover(temp, true));
			temp.setOnMouseExited(e ->  SetHover(temp, false));
		}
	}
	
	private void SetHover(Label label, boolean isOnHover){
		if (isOnHover) {
			label.setTextFill(Color.SLATEBLUE);
			label.setFont(Font.font(label.getFont().getSize() + 3));
		} else {
			label.setTextFill(Color.RED);
			label.setFont(Font.font(label.getFont().getSize() - 3));
		}
	}
	
}