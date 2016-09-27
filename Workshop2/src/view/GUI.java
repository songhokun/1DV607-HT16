package view;

import java.io.FileNotFoundException;
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
import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;

public class GUI implements Initializable {

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
	private GridPane alertDialogePane;

	private Registry registry;
	private Member presentMember;

	
	
	public GUI() {
		try {
			registry = new Registry();
		} catch (FileNotFoundException e) {
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
		closeBoatListButton.setOnAction(e -> closeBoatList());
	}

	public void displayCompactList() {
		setMemberTableView();
		boatDetailColumn.setVisible(false);
	}

	public void displayVerboList() {
		setMemberTableView();
		boatDetailColumn.setVisible(true);
	}

	public void showAddMemberNotification(Member member, AlertType type, String header, String button1Name, boolean addDialogPane) {
		Alert alert = getMemberAlertBox(member, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.createMember(memberNameField.getText(), memberPersonalNumberField.getText());
			setMemberTableView();
		} else
			alert.close();
	}

	public void showEditMemberNotification(Member member, AlertType type, String header, String button1Name, boolean addDialogPane) {
		Alert alert = getMemberAlertBox(member, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			member.setName(memberNameField.getText());
			member.setPersonalnumber(memberPersonalNumberField.getText());
			registry.updateMember(member);
			setMemberTableView();
		} else
			alert.close();
	}

	public void showDeleteMemberNotification(Member member, AlertType type, String header, String button1Name, boolean addDialogPane) {
		Alert alert = getMemberAlertBox(null, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.deleteMember(member);
			setMemberTableView();
		} else
			alert.close();
	}

	public void displayAddBoatNotification(Boat boat, AlertType type, String header, String button1Name, boolean addDialogPane) {
		boatTypeChoiceBox.getSelectionModel().select(BoatType.Motorsailer);
		Alert alert = getBoatAlertBox(boat, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			presentMember.registerBoat(Double.valueOf(boatLengthField.getText()), boatTypeChoiceBox.getSelectionModel().getSelectedItem());
			setBoatTableView(presentMember);
		} else
			alert.close();
	}

	public void displayEditBoatNotification(Boat boat, AlertType type, String header, String button1Name, boolean addDialogPane) {
		boatTypeChoiceBox.getSelectionModel().select(boat.getType());
		Alert alert = getBoatAlertBox(boat, type, header, button1Name, addDialogPane);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			boat.setLength(Double.valueOf(boatLengthField.getText()));
			boat.setType(boatTypeChoiceBox.getSelectionModel().getSelectedItem());
			presentMember.updateBoat(boat);
			setBoatTableView(presentMember);
		} else
			alert.close();
	}

	public void displayDeleteBoatNotification(Boat boat, AlertType type, String header, String button1Name, boolean addDialogPane) {
		Alert alert = getBoatAlertBox(null, type, header, button1Name, addDialogPane);	
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			presentMember.deleteBoat(boat);
			setBoatTableView(presentMember);
		} else
			alert.close();
	}
	
	public void showBoatList(Member member) {
		//setMemberTableView();
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
					boatEditButton.setOnAction(event -> displayEditBoatNotification(boat, AlertType.CONFIRMATION,
							"Edit Boat", "Save", true));
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
					boatDeleteButton.setOnAction(event -> displayDeleteBoatNotification(boat, AlertType.CONFIRMATION,
							"Are you sure?", "Yes", false));
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
					deleteButton.setOnAction(event -> showDeleteMemberNotification(member, AlertType.CONFIRMATION,
							"Are you sure?", "Yes", false));
				}
			}
		});

		memberTableView.setItems(data);
	}
}