package view;
 
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Authentication;
import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;

public class GUIOld implements Initializable {

	@FXML private TableView<Member> memberTableView;
	@FXML private TableColumn<Member, String> memberNameColumn;
	@FXML private TableColumn<Member, String> memberPersonalNumberColumn;
	@FXML private TableColumn<Member, String> memberIDColumn;
	@FXML private TableColumn<Member, String> numberOfBoatsColumn;
	@FXML private TableColumn<Member, Member> boatDetailColumn;
	@FXML private TableColumn<Member, Member> memberEditColumn;
	@FXML private TableColumn<Member, Member> memberDeleteColumn;
	private TextField memberNameField;
	private TextField memberPersonalNumberField;
	
	@FXML private AnchorPane boatTableViewPane;
	@FXML private TableView<Boat> boatTableView;
	@FXML private TableColumn<Boat, String> boatLengthColumn;
	@FXML private TableColumn<Boat, String> boatTypeColumn;
	@FXML private TableColumn<Boat, Boat> boatEditColumn;
	@FXML private TableColumn<Boat, Boat> boatDeleteColumn;
	@FXML private Button addBoatButton;
	@FXML private Button closeBoatListButton;
	private TextField boatLengthField;
	private ChoiceBox <BoatType>boatTypeChoiceBox = new ChoiceBox<BoatType>(FXCollections.observableArrayList(BoatType.values()));
	
	@FXML private Button compactListButton;
	@FXML private Button verboListButton;
	@FXML private Button addMemberButton;
	@FXML private Button loginButton;
	private GridPane alertDialogePane;

	private Registry registry;
	private Member presentMember;
	private Authentication authentication;
	
	

	
	public GUIOld() {
		try {
			registry = new Registry();
			authentication = new Authentication();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		boatDetailColumn.setVisible(false);
		compactListButton.setOnAction(e -> displayCompactList());
		verboListButton.setOnAction(e -> displayVerboList());
		addMemberButton.setOnAction(e -> showAddMemberNotification(null, AlertType.INFORMATION, "New Member", "Save", true));
		addBoatButton.setOnAction(e -> displayAddBoatNotification(null, AlertType.INFORMATION, "New Boat", "Save", true));
		loginButton.setOnAction(e -> showLogIn());
		closeBoatListButton.setOnAction(e -> closeBoatList());
	}
	
	public void displayCompactList() {
		setMemberTableView();
		memberPersonalNumberColumn.setVisible(false);
		boatDetailColumn.setVisible(false);
	}

	public void displayVerboList() {
		setMemberTableView();
		memberPersonalNumberColumn.setVisible(true);
		boatDetailColumn.setVisible(true);
	}

	public void showAddMemberNotification(Member member, AlertType type, String header, String button1Name, boolean addDialogPane){
		if (!authentication.isLoggedIn()) {
			displayAlert(AlertType.ERROR, "Please Log in to use this function.");
			return;
		}
		Alert alert = getMemberAlertBox(member, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			try {
				registry.createMember(memberNameField.getText(), memberPersonalNumberField.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setMemberTableView();
		} else
			alert.close();
	}

	public void showEditMemberNotification(Member member, AlertType type, String header, String button1Name, boolean addDialogPane) {
		if (!authentication.isLoggedIn()) {
			displayAlert(AlertType.ERROR, "Please Log in to use this function.");
			return;
		}
		Alert alert = getMemberAlertBox(member, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			try {
				registry.updateMember(member, memberNameField.getText(), memberPersonalNumberField.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setMemberTableView();
		} else
			alert.close();
	}

	public void showDeleteMemberNotification(Member member, AlertType type, String header, String button1Name, boolean addDialogPane) {
		if (!authentication.isLoggedIn()) {
			displayAlert(AlertType.ERROR, "Please Log in to use this function.");
			return;
		}
		Alert alert = getMemberAlertBox(null, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.deleteMember(member);
			setMemberTableView();
		} else
			alert.close();
	}

	public void displayAddBoatNotification(Boat boat, AlertType type, String header, String button1Name, boolean addDialogPane) {
		if (!authentication.isLoggedIn()) {
			displayAlert(AlertType.ERROR, "Please Log in to use this function.");
			return;
		}
		boatTypeChoiceBox.getSelectionModel().select(BoatType.Motorsailer);
		Alert alert = getBoatAlertBox(boat, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			try {
				registry.registerBoat(presentMember, Double.valueOf(boatLengthField.getText()), boatTypeChoiceBox.getSelectionModel().getSelectedItem());
			} catch (Exception e) {
			}
			setBoatTableView(presentMember);
		} else
			alert.close();
	}

	public void displayEditBoatNotification(Boat boat, AlertType type, String header, String button1Name, boolean addDialogPane) {
		if (!authentication.isLoggedIn()) {
			displayAlert(AlertType.ERROR, "Please Log in to use this function.");
			return;
		}
		boatTypeChoiceBox.getSelectionModel().select(boat.getType());
		Alert alert = getBoatAlertBox(boat, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.updateBoat(Double.valueOf(boatLengthField.getText()),
					boatTypeChoiceBox.getSelectionModel().getSelectedItem(), boat);
			setBoatTableView(presentMember);
		} else
			alert.close();
	}

	public void displayDeleteBoatNotification(Boat boat, AlertType type, String header, String button1Name, boolean addDialogPane) {
		if (!authentication.isLoggedIn()) {
			displayAlert(AlertType.ERROR, "Please Log in to use this function.");
			return;
		}
		Alert alert = getBoatAlertBox(null, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.deleteBoat(presentMember, boat);
			setBoatTableView(presentMember);
		} else
			alert.close();
	}

	public void showBoatList(Member member) {
		setBoatTableView(member);
		memberTableView.setVisible(false);
		boatTableViewPane.setVisible(true);
		compactListButton.setVisible(false);
		verboListButton.setVisible(false);
		addMemberButton.setVisible(false);
		presentMember = member;
	}

	public void closeBoatList() {
		boatTableViewPane.setVisible(false);
		memberTableView.setVisible(true);
		compactListButton.setVisible(true);
		verboListButton.setVisible(true);
		addMemberButton.setVisible(true);
		setMemberTableView();
	}

	public void showLogIn() {
		Alert alert = getMemberAlertBox(null, AlertType.NONE, "Authentication", "Log In", true);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			authentication.logIn(memberNameField.getText(), memberPersonalNumberField.getText());
			if (!authentication.isLoggedIn()) {
				displayAlert(AlertType.ERROR, "Incorrect Username or Password!!");
			} else {
				displayAlert(AlertType.INFORMATION, "Logged In Successfully!!");
				loginButton.setText("Log Out");
				loginButton.setOnAction(e -> showLogOut());
			}
		} else
			alert.close();
	}

	private void showLogOut() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?");
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yes, no);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			authentication.logOut();
			loginButton.setText("Log In");
			loginButton.setOnAction(e -> showLogIn());
		} else
			alert.close();
	}

	private void displayAlert(AlertType type, String header) {
		Alert alert = new Alert(type, header);
		alert.show();
	}

	private Alert getBoatAlertBox(Boat boat, AlertType type, String header, String button1Name, boolean addDialogPane) {
		Alert alert = new Alert(type);
		alertDialogePane = new GridPane();
		boatLengthField = new TextField();
		alert.setHeaderText(header);
		ButtonType yes = new ButtonType(button1Name);
		ButtonType no = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yes, no);
		if (boat != null)
			boatLengthField.setText("" + boat.getLength());
		alertDialogePane.setHgap(10);
		alertDialogePane.setVgap(10);
		alertDialogePane.setPadding(new Insets(20, 150, 10, 10));
		alertDialogePane.add(new Label("Length"), 0, 0);
		alertDialogePane.add(boatLengthField, 1, 0);
		alertDialogePane.add(new Label("Boat Type"), 0, 1);
		alertDialogePane.add(boatTypeChoiceBox, 1, 1);
		if (addDialogPane)
			alert.getDialogPane().setContent(alertDialogePane);
		return alert;
	}

	private Alert getMemberAlertBox(Member member, AlertType type, String header, String button1Name, boolean addDialogPane) {
		Alert alert = new Alert(type);
		alertDialogePane = new GridPane();
		memberNameField = new TextField();
		memberPersonalNumberField = new TextField();
		alert.setHeaderText(header);
		ButtonType yes = new ButtonType(button1Name);
		ButtonType no = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yes, no);
		if (member != null) {
			memberNameField.setText(member.getName());
			memberPersonalNumberField.setText(member.getPersonalnumber());
		}
		alertDialogePane.setHgap(10);
		alertDialogePane.setVgap(10);
		alertDialogePane.setPadding(new Insets(20, 150, 10, 10));
		alertDialogePane.add(new Label("Name"), 0, 0);
		alertDialogePane.add(memberNameField, 1, 0);
		alertDialogePane.add(new Label("Personal Number"), 0, 1);
		alertDialogePane.add(memberPersonalNumberField, 1, 1);
		if (addDialogPane)
			alert.getDialogPane().setContent(alertDialogePane);
		return alert;
	}

	private void setBoatTableView(Member member) {
		final ObservableList<Boat> data = FXCollections.observableArrayList(member.getBoatdata());
		boatLengthColumn.setCellValueFactory(new PropertyValueFactory<Boat, String>("length"));
		boatTypeColumn.setCellValueFactory(new PropertyValueFactory<Boat, String>("type"));
		boatEditColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		boatEditColumn.setCellFactory(param -> new TableCell<Boat, Boat>() {
			private final Button boatEditButton = new Button("Edit");

			@Override
			protected void updateItem(Boat boat, boolean empty) {
				super.updateItem(boat, empty);
				if (boat == null) {
					setGraphic(null);
					return;
				} else {
					setGraphic(boatEditButton);
					boatEditButton.setOnAction(event -> displayEditBoatNotification(boat, AlertType.CONFIRMATION, "Edit Boat", "Save", true));
				}
			}
		});
		boatDeleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		boatDeleteColumn.setCellFactory(param -> new TableCell<Boat, Boat>() {
			private final Button boatDeleteButton = new Button("Delete");

			@Override
			protected void updateItem(Boat boat, boolean empty) {
				super.updateItem(boat, empty);
				if (boat == null) {
					setGraphic(null);
					return;
				} else {
					setGraphic(boatDeleteButton);
					boatDeleteButton.setOnAction(event -> displayDeleteBoatNotification(boat, AlertType.CONFIRMATION, "Are you sure?", "Yes", false));
				}
			}
		});
		boatTableView.setItems(data);
	}

	private void setMemberTableView() {
		final ObservableList<Member> data = FXCollections.observableArrayList(registry.getRegistry());
		memberNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		memberPersonalNumberColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("personalnumber"));
		memberIDColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("memberID"));
		boatDetailColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		boatDetailColumn.setCellFactory(param -> new TableCell<Member, Member>() {
			private final Button seeBoatsButton = new Button("See Boats");

			@Override
			protected void updateItem(Member member, boolean empty) {
				super.updateItem(member, empty);
				if (member == null) {
					setGraphic(null);
					return;
				} else {
					setGraphic(seeBoatsButton);
					seeBoatsButton.setOnAction(event -> showBoatList(member));
				}
			}
		});
		numberOfBoatsColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("numberOfBoats"));
		memberEditColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		memberEditColumn.setCellFactory(param -> new TableCell<Member, Member>() {
			private final Button editButton = new Button("Edit");

			@Override
			protected void updateItem(Member member, boolean empty) {
				super.updateItem(member, empty);
				if (member == null) {
					setGraphic(null);
					return;
				} else {
					setGraphic(editButton);
					editButton.setOnAction(
							event -> showEditMemberNotification(member, AlertType.NONE, "Edit Member", "Save", true));
				}
			}
		});
		memberDeleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		memberDeleteColumn.setCellFactory(param -> new TableCell<Member, Member>() {
			private final Button deleteButton = new Button("Delete");

			@Override
			protected void updateItem(Member member, boolean empty) {
				super.updateItem(member, empty);
				if (member == null) {
					setGraphic(null);
					return;
				} else {
					setGraphic(deleteButton);
					deleteButton.setOnAction(event -> showDeleteMemberNotification(member, AlertType.CONFIRMATION, "Are you sure?", "Yes", false));
				}
			}
		});

		memberTableView.setItems(data);
	}
}