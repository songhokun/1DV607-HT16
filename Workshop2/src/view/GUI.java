package view;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Authentication;
import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;

public class GUI implements Initializable, IView {

	@FXML private Text welcomeText;
	@FXML private Button compactListButton;
	@FXML private Button verboListButton;	
	@FXML private Button createMemberButton;
	@FXML private Button logInButton;	
	
	@FXML private TableView<Member> memberTable;
	@FXML private TableColumn<Member, String> memberNameColumn;
	@FXML private TableColumn<Member, String> memberPersonalnumberColumn;
	@FXML private TableColumn<Member, String> memberIDColumn;
	@FXML private TableColumn<Member, String> memberTotalBoatsColumn;
	@FXML private TableColumn<Member, Member> memberBoatsInformationColumn;
	@FXML private TableColumn<Member, Member> memberEditColumn;
	@FXML private TableColumn<Member, Member> memberDeleteColumn;
	private TextField memberName = new TextField();
	private TextField memberPN = new TextField();
	
	@FXML private AnchorPane boatTablePane;
	@FXML private TableView<Boat> boatTable;
	@FXML private TableColumn<Boat, String> boatLengthColumn;
	@FXML private TableColumn<Boat, String> boatTypeColumn;
	@FXML private TableColumn<Boat, Boat> boatEditColumn;
	@FXML private TableColumn<Boat, Boat> boatDeleteColumn;
	private ChoiceBox <BoatType>boatTypeChoiceBox = new ChoiceBox<BoatType>(FXCollections.observableArrayList(BoatType.values()));
	@FXML private Button addBoatButton;
	@FXML private Button closeBoatListButton;	
	private TextField boatLength = new TextField();
	
	private Registry registry;
	private Authentication authentication;


	public GUI() {
		authentication = new Authentication();
		try {
			registry = new Registry();
		} catch (Exception e) {
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startProgram();
		memberPN.setPromptText("YYYYMMDDXXXX");
		memberName.setPromptText("Eg: John Smith");
		boatLength.setPromptText("Eg: 14.65");
		compactListButton.setOnAction(e -> displayCompactList(registry.getMemberList()));
		verboListButton.setOnAction(e -> displayVerboseList(registry.getMemberList()));
		createMemberButton.setDisable(!authentication.isLoggedIn());
		createMemberButton.setOnAction(e -> registerMember(memberName.getText(), memberPN.getText()));
		closeBoatListButton.setOnAction(e -> changeView());
		logInButton.setOnAction(e -> logIn("", ""));
	}

	@Override
	public void startProgram() {
		displayWelcomeMessage();
		displayMainInstructions();
	}

	@Override
	public void displayWelcomeMessage() {
		welcomeText.setText("** WELCOME TO MEMBER REGISTERY PROGRAM **");
	}

	@Override
	public void displayMainInstructions() {
		compactListButton.setVisible(true);
		verboListButton.setVisible(true);
		createMemberButton.setVisible(true);
	}

	@Override
	public void displayCompactList(ArrayList<Member> m) {
		setMemberTable(registry.getMemberList());
		memberPersonalnumberColumn.setVisible(false);
		memberBoatsInformationColumn.setVisible(false);
	}

	@Override
	public void displayVerboseList(ArrayList<Member> m) {
		setMemberTable(registry.getMemberList());
		memberPersonalnumberColumn.setVisible(true);
		memberBoatsInformationColumn.setVisible(true);
	}

	@Override
	public void registerMember(String name, String personalnumber) {
		memberName.clear();
		memberPN.clear();
		Alert alert = new Alert(AlertType.NONE);
		alert.setHeaderText("Update Member");
		alert.getButtonTypes().add(new ButtonType("Save"));
		alert.getButtonTypes().add(ButtonType.CANCEL);
		alert.getDialogPane().setContent(createDialogeBox(new Label("Name*"), memberName, new Label("Personal Number*"), memberPN, null));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			if (checkName(memberName) && checkPN(memberPN)) {
				registry.createMember(memberName.getText(), memberPN.getText());
				displaySuccess("Member Registered !!");
				registry.saveRegistry();
				setMemberTable(registry.getMemberList());
			} else {
				if (memberName.getText().isEmpty() || memberPN.getText().isEmpty())
					displayError("Please fill all the required(*) fields.");
				else if (!checkName(memberName))
					displayError("Please fill the correct name. Eg: John Smith!!");
				else
					displayError("Please fill the correct personal number. Eg: YYMMDDXXXX!!");
			}
		} else
			alert.close();
	}

	@Override
	public void updateMember(Member m, String name, String personalnumber) {
		memberName.setText(name);
		memberPN.setText(personalnumber);
		Alert alert = new Alert(AlertType.NONE);
		alert.setHeaderText("Update Member");
		alert.getButtonTypes().add(new ButtonType("Save"));
		alert.getButtonTypes().add(ButtonType.CANCEL);
		alert.getDialogPane().setContent(createDialogeBox(new Label("Name*"), memberName, new Label("Personal Number*"), memberPN, null));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			if (checkName(memberName) && checkPN(memberPN)) {
				registry.updateMember(m, memberName.getText(), memberPN.getText());
				displaySuccess("Member Updated !!");
				registry.saveRegistry();
				setMemberTable(registry.getMemberList());
			} else {
				if (memberName.getText().isEmpty() || memberPN.getText().isEmpty())
					displayError("Please fill all the required(*) fields.");
				else if (!checkName(memberName))
					displayError("Please fill the correct name. Eg: John Smith!!");
				else
					displayError("Please fill the correct personal number. Eg: YYMMDDXXXX!!");
			}
		} else
			alert.close();
	}

	@Override
	public void deleteMember(Member m) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.deleteMember(m);
			displaySuccess("Member Deleted !!");
			registry.saveRegistry();
			setMemberTable(registry.getMemberList());
		} else
			alert.close();
	}

	@Override
	public void displaySelectedMember(Member m) {
		memberTable.setVisible(false);
		compactListButton.setVisible(false);
		verboListButton.setVisible(false);
		createMemberButton.setVisible(false);
		setBoatTable(m);
		boatTablePane.setVisible(true);
		addBoatButton.setDisable(!authentication.isLoggedIn());
		addBoatButton.setOnAction(e -> registerBoat(m, 0, null));
	}

	@Override
	public void registerBoat(Member m, double boatLength, BoatType boattype) {
		this.boatLength.clear();
		boatTypeChoiceBox.getSelectionModel().select(BoatType.Sailboat);
		Alert alert = new Alert(AlertType.NONE);
		alert.setHeaderText("Register Boat");
		alert.getButtonTypes().add(new ButtonType("Save"));
		alert.getButtonTypes().add(ButtonType.CANCEL);
		alert.getDialogPane().setContent(createDialogeBox(new Label("Length(m)*"), this.boatLength, new Label("Type*"), null, boatTypeChoiceBox));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			if (checkLength(this.boatLength)) {
				registry.registerBoat(m, Double.parseDouble(this.boatLength.getText()), boatTypeChoiceBox.getSelectionModel().getSelectedItem());
				displaySuccess("Boat Registerd !!");
				registry.saveRegistry();
				setBoatTable(m);
			} else {
				if (this.boatLength.getText().isEmpty())
					displayError("Length field is empty!!");
				else
					displayError("Please fill the correct boat length. Eg: 14.23");
			}
		} else
			alert.close();
	}

	@Override
	public void updateBoat(double length, BoatType type, Boat boat) {
		boatLength.setText("" + length);
		boatTypeChoiceBox.getSelectionModel().select(type);
		Alert alert = new Alert(AlertType.NONE);
		alert.setHeaderText("Update Boat");
		alert.getButtonTypes().add(new ButtonType("Save"));
		alert.getButtonTypes().add(ButtonType.CANCEL);
		alert.getDialogPane().setContent(createDialogeBox(new Label("Length(m)"), boatLength, new Label("Type"), null, boatTypeChoiceBox));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			if (checkLength(boatLength)) {
				registry.updateBoat(Double.parseDouble(boatLength.getText()), boatTypeChoiceBox.getSelectionModel().getSelectedItem(), boat);
				displaySuccess("Boat Updated !!");
				registry.saveRegistry();
				boatTable.refresh();
			} else {
				if (this.boatLength.getText().isEmpty())
					displayError("Length field is empty!!");
				else
					displayError("Please fill the correct boat length. Eg: 14.23");
			}
		} else
			alert.close();
	}

	@Override
	public void deleteBoat(Member m, Boat b) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.deleteBoat(m, b);
			displaySuccess("Boat Deleted !!");
			registry.saveRegistry();
			setBoatTable(m);
		} else
			alert.close();
	}

	@Override
	public void displayError(String error) {
		Alert alert = new Alert(AlertType.ERROR, error);
		alert.show();
	}

	@Override
	public void displaySuccess(String success) {
		Alert alert = new Alert(AlertType.INFORMATION, success);
		alert.show();
	}

	@Override
	public void logIn(String username, String password) {
		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();
		Alert alert = new Alert(AlertType.NONE);
		alert.setHeaderText("Log In");
		alert.getButtonTypes().add(new ButtonType("Login"));
		alert.getButtonTypes().add(ButtonType.CANCEL);
		alert.getDialogPane().setContent(createDialogeBox(new Label("Username"), usernameField, new Label("Password"), passwordField, null));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) { // index 0 is login button
			authentication.logIn(usernameField.getText(), passwordField.getText());
			if (authentication.isLoggedIn()) {
				displaySuccess("Logged in successfully!!");
				memberTable.refresh();
				boatTable.refresh();
				createMemberButton.setDisable(false);
				addBoatButton.setDisable(false);
				logInButton.setDisable(true);
			} else
				displayError("Please check the username and password!!");
		} else
			alert.close();
	}

	@Override
	public void simpleSearch(Object o){
		
	}
	
	@Override
	public void quitProgram() {
		registry.saveRegistry();
	}

	/********************************** FOR CREATING VIEW ************************/
	private void changeView() {
		boatTablePane.setVisible(false);
		compactListButton.setVisible(true);
		verboListButton.setVisible(true);
		createMemberButton.setVisible(true);
		setMemberTable(registry.getMemberList());
		memberTable.setVisible(true);
	}

	private GridPane createDialogeBox(Label l1, TextField first, Label l2, TextField second, ChoiceBox<BoatType> box) {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(20, 150, 10, 10));
		pane.add(l1, 0, 0);
		pane.add(first, 1, 0);
		pane.add(l2, 0, 1);
		if (second == null)
			pane.add(box, 1, 1);
		else
			pane.add(second, 1, 1);
		return pane;
	}

	private void setBoatTable(Member member) {
		ObservableList<Boat> data = FXCollections.observableArrayList(member.getBoatList());
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
					boatEditButton.setDisable(!authentication.isLoggedIn());
					boatEditButton.setOnAction(event -> updateBoat(boat.getLength(), boat.getType(), boat));
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
					boatDeleteButton.setDisable(!authentication.isLoggedIn());
					boatDeleteButton.setOnAction(event -> deleteBoat(member, boat));
				}
			}
		});
		boatTable.setItems(data);
	}

	private void setMemberTable(ArrayList<Member> m) {
		ObservableList<Member> data = FXCollections.observableArrayList(m);
		memberNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		memberPersonalnumberColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("personalnumber"));
		memberIDColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("memberID"));
		memberTotalBoatsColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("numberOfBoats"));
		memberBoatsInformationColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		memberBoatsInformationColumn.setCellFactory(param -> new TableCell<Member, Member>() {
			private final Button viewBoats = new Button("View Boats");

			@Override
			protected void updateItem(Member member, boolean empty) {
				super.updateItem(member, empty);
				if (member == null) {
					setGraphic(null);
					return;
				} else {
					setGraphic(viewBoats);
					viewBoats.setOnAction(event -> displaySelectedMember(member));
				}
			}
		});
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
					editButton.setDisable(!authentication.isLoggedIn());
					editButton.setOnAction(e -> updateMember(member, member.getName(), member.getPersonalnumber()));
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
					deleteButton.setDisable(!authentication.isLoggedIn());
					deleteButton.setOnAction(event -> deleteMember(member));
				}
			}
		});

		memberTable.setItems(data);
		memberTable.setVisible(true);
	}

	/*************************** FOR CHECKING INPUT *************************************************/
	private boolean checkLength(TextField length) {
		try {
			if (Double.parseDouble(length.getText()) <= 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean checkName(TextField name) {
		if (name.getText().isEmpty())
			return false;
		boolean charexists = false;
		for (int i = 0; i < name.getText().length(); i++) {
			char c = name.getText().charAt(i);
			if (Character.isAlphabetic(c))
				charexists = true;
			if (!Character.isAlphabetic(c) && !Character.isWhitespace(c))
				return false;
		}
		return charexists;
	}

	private boolean checkPN(TextField personalnumber) {
		if (personalnumber.getText().length() != 12 || personalnumber.getText().isEmpty())
			return false;
		try {
			String pn = personalnumber.getText().substring(0, 8);
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			df.setLenient(false);
			df.parse(pn);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}