package BlackJack.controller;

import java.net.URL;
import java.util.ResourceBundle;
import BlackJack.model.Card;
import BlackJack.model.IObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameController extends MainController implements IObserver{

	@FXML private Label back;
	@FXML private Label play;
	@FXML private Label stand;
	@FXML private Label hit;
	@FXML private Label quit;
	@FXML private Label player;
	@FXML private Label dealer;
	@FXML private TextArea playerScore;
	@FXML private TextArea dealerScore;
	@FXML private TextArea rules;
	@FXML private HBox playerCardDisplayer;
	@FXML private HBox dealerCardDisplayer;
	private final double imageHeight = 250.0;
	private final double imageWidth = 200.0;
	private ImageView imageView;
	
	public void initialize(URL location, ResourceBundle resources) {
		quit.setOnMouseClicked(e -> super.quit());
		this.rules.setText(super.rules.getText());
		playerScore.setText("Player Score: " + super.game.GetPlayerScore());
		dealerScore.setText("Dealer Score: " + super.game.GetDealerScore());
		play.setOnMouseClicked(e -> play());
		stand.setOnMouseClicked(e -> stand());
		hit.setOnMouseClicked(e -> hit());
		game.AddSubscribers(this);
		hit.setDisable(true);
		stand.setDisable(true);
		setPageLanguage();
	}
	
	@FXML
	public void displayMainpage() {
		super.stage = (Stage) back.getScene().getWindow();
		super.GoToPage("Mainpage");
	}


	@Override
	public void PlayerGetNewCard() {
		dealerCardDisplayer.getChildren().clear();
		playerCardDisplayer.getChildren().clear();
		for(Card c: game.GetDealerHand()){
			imageView = new ImageView(new Image(c.GetLink()));
			//imageView = new ImageView(new Image("http://opengameart.org/sites/default/files/styles/watermarked/public/king_of_hearts2.png"));
			imageView.setFitHeight(imageHeight);
			imageView.setFitWidth(imageWidth);
			imageView.setPreserveRatio(false);
			dealerCardDisplayer.getChildren().add(imageView);
		}
		
		for(Card c: game.GetPlayerHand()){
			imageView = new ImageView(new Image(c.GetLink()));
			//imageView = new ImageView(new Image("http://opengameart.org/sites/default/files/styles/watermarked/public/king_of_hearts2.png"));
			imageView.setFitHeight(imageHeight);
			imageView.setFitWidth(imageWidth);
			imageView.setPreserveRatio(false);
			playerCardDisplayer.getChildren().add(imageView);
		}
		playerScore.setText("Player Score: " + super.game.GetPlayerScore());
		dealerScore.setText("Dealer Score: " + super.game.GetDealerScore());

		if(game.IsGameOver())
			gameOver();
	}

	public void play(){
		dealerCardDisplayer.getChildren().clear();
		playerCardDisplayer.getChildren().clear();
		playerScore.setText("Score: 0");
		dealerScore.setText("Score: 0");
		super.game.NewGame();
		play.setDisable(true);
		hit.setDisable(false);
		stand.setDisable(false);
	}
	
	public void stand(){
		hit.setDisable(true);
		stand.setDisable(true);
		super.game.Stand();
		hit.setDisable(false);
		stand.setDisable(false);		
	}
	
	public void hit(){
		hit.setDisable(true);
		stand.setDisable(true);
		super.game.Hit();
		hit.setDisable(false);
		stand.setDisable(false);	
	}
	
	public void gameOver(){
			Alert alert = new Alert(AlertType.INFORMATION, "Game Over");
			if(game.IsDealerWinner())
				alert.setContentText("Dealer Won!");
			else alert.setContentText("You Won!"); 
 
			alert.show();
			play.setDisable(false);
			hit.setDisable(true);
			stand.setDisable(true);
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
	private void changeToEnglish(){
		back.setText("back");
		play.setText("play");
		stand.setText("stand");
		hit.setText("hit");
		quit.setText("quit");
		player.setText("player");
		dealer.setText("dealer");
	}
	private void changeToSwedish(){
		back.setText("tillbacka");
		play.setText("Spela");
		stand.setText("stanna");
		hit.setText("hämta");
		quit.setText("avsluta");
		player.setText("spelaren");
		dealer.setText("givaren");
	}
}