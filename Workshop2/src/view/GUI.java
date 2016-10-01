package view;

import java.net.URL;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;

public class GUI implements Initializable, IView {

	@FXML private Text welcomeText;
	@FXML private Button compactListButton;
	@FXML private Button verboListButton;	
	@FXML private Button createMemberButton;
	
	@FXML private TableView<Member> memberTable;
	@FXML private TableColumn<Member, String> memberNameColumn;
	@FXML private TableColumn<Member, String> memberPersonalnumberColumn;
	@FXML private TableColumn<Member, String> memberIDColumn;
	@FXML private TableColumn<Member, String> memberTotalBoatsColumn;
	@FXML private TableColumn<Member, Member> memberBoatsInformationColumn;
	@FXML private TableColumn<Member, Member> memberEditColumn;
	@FXML private TableColumn<Member, Member> memberDeleteColumn;
	
	@FXML private AnchorPane boatTablePane;
	@FXML private TableView<Boat> boatTable;
	@FXML private TableColumn<Boat, String> boatLengthColumn;
	@FXML private TableColumn<Boat, String> boatTypeColumn;
	@FXML private TableColumn<Boat, Boat> boatEditColumn;
	@FXML private TableColumn<Boat, Boat> boatDeleteColumn;
	private ChoiceBox <BoatType>boatTypeChoiceBox = new ChoiceBox<BoatType>(FXCollections.observableArrayList(BoatType.values()));
	@FXML private Button addBoatButton;
	@FXML private Button closeBoatLisButton;	
	
	private TextField memberName = new TextField();
	private TextField memberPN = new TextField();
	private TextField boatLength = new TextField();

	private Registry registry;
	private int memberID;
	
	public GUI(){
		try {
			registry = new Registry();
		} catch (Exception e) {
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startProgram();
		compactListButton.setOnAction(e -> displayCompactList());
		verboListButton.setOnAction(e -> displayVerboseList());
		createMemberButton.setOnAction(e -> registerMember(memberName.getText(), memberPN.getText()));
		closeBoatLisButton.setOnAction(e -> changeView());
	}
	
	@Override
	public void startProgram() {
		displayWelcomeMessage();	
		displayMainInstructions();
	}

	@Override
	public void displayWelcomeMessage() {
		welcomeText.setVisible(true);
	}

	@Override
	public void displayMainInstructions() {
		compactListButton.setVisible(true);
		verboListButton.setVisible(true);
		createMemberButton.setVisible(true);
	}
	
	@Override
	public void displayCompactList() {
		setMemberTable(registry.getMemberList());
		memberPersonalnumberColumn.setVisible(false);
		memberBoatsInformationColumn.setVisible(false);
	}

	@Override
	public void displayVerboseList() {
		setMemberTable(registry.getMemberList());
		memberPersonalnumberColumn.setVisible(true);
		memberBoatsInformationColumn.setVisible(true);
	}

	@Override
	public void registerMember(String name, String personalnumber) {
		memberName.clear();
		memberPN.clear();
		Alert alert =  new Alert(AlertType.NONE, "Update Member");
		alert.getButtonTypes().add(new ButtonType("Save"));
		alert.getButtonTypes().add(new ButtonType("Cancel"));
		memberPN.setPromptText("YYMMDDXXXX");
		alert.getDialogPane().setContent(createDialogeBox(new Label("Name"), memberName, new Label("Personal Number"), memberPN, null));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			if(checkName(memberName) && checkPN(memberPN)){
				registry.createMember(memberName.getText(), memberPN.getText());
				displaySuccess("Member Registered !!");
				setMemberTable(registry.getMemberList());
				registry.saveRegistry();
				alert.close();
			}
			else
				displayError("INCORRECT DATA!!");
		}
		else 
			alert.close();
	}

	@Override
	public void updateMember(Member m, String name, String personalnumber) {
		memberName.setText(name);
		memberPN.setText(personalnumber);
		Alert alert =  new Alert(AlertType.NONE, "Update Member");
		alert.getButtonTypes().add(new ButtonType("Save"));
		alert.getButtonTypes().add(new ButtonType("Cancel"));
		memberPN.setPromptText("YYMMDDXXXX");
		alert.getDialogPane().setContent(createDialogeBox(new Label("Name"), memberName, new Label("Personal Number"), memberPN, null));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			if(checkName(memberName) && checkPN(memberPN)){
				registry.updateMember(m, memberName.getText(), memberPN.getText());
				setMemberTable(registry.getMemberList());
				displaySuccess("Member Updated !!");
				registry.saveRegistry();
				alert.close();
			}
			else
				displayError("INCORRECT DATA!!");
		}
		else 
			alert.close();
	}

	@Override
	public void deleteMember(Member m) {
		Alert alert =  new Alert(AlertType.CONFIRMATION, "Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.deleteMember(m);
			displaySuccess("Member Deleted !!");
			registry.saveRegistry();
			setMemberTable(registry.getMemberList());
		}
		else 
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
		memberID = m.getMemberID();
		addBoatButton.setOnAction(e -> registerBoat(m, 0, null));
	}
	
	@Override
	public void registerBoat(Member m, double boatLength, BoatType boattype) {
		this.boatLength.clear();
		boatTypeChoiceBox.getSelectionModel().select(BoatType.Sailboat);
		Alert alert =  new Alert(AlertType.NONE, "Register Boat");
		alert.getButtonTypes().add(new ButtonType("Save"));
		alert.getButtonTypes().add(new ButtonType("Cancel"));
		alert.getDialogPane().setContent(createDialogeBox(new Label("Length"), this.boatLength , new Label("Type"), null, boatTypeChoiceBox));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			if(checkLength(this.boatLength)){
				registry.registerBoat(m, Double.parseDouble(this.boatLength.getText()), boatTypeChoiceBox.getSelectionModel().getSelectedItem());
				setBoatTable(m);
				displaySuccess("Boat Registerd !!");
				registry.saveRegistry();
				alert.close();
			}
			else{
				displayError("INCORRECT DATA!!");
				alert.close();
			}
		}
		else
			alert.close();
	}

	@Override
	public void updateBoat(double length, BoatType type, Boat boat) {
		boatLength.setText("" + length);
		boatTypeChoiceBox.getSelectionModel().select(type);
		Alert alert =  new Alert(AlertType.NONE, "Update Boat");
		alert.getButtonTypes().add(new ButtonType("Save"));
		alert.getButtonTypes().add(new ButtonType("Cancel"));
		alert.getDialogPane().setContent(createDialogeBox(new Label("Length"), boatLength , new Label("Type"), null, boatTypeChoiceBox));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			if(checkLength(boatLength)){
				registry.updateBoat(Double.parseDouble(boatLength.getText()), boatTypeChoiceBox.getSelectionModel().getSelectedItem(), boat);
				setBoatTable(registry.lookUpMember(memberID));
				displaySuccess("Boat Updated !!");
				registry.saveRegistry();
				alert.close();
			}
			else{
				displayError("INCORRECT DATA!!");
				alert.close();
			}
		}
		else alert.close();
	}
	
	@Override
	public void deleteBoat(Member m, Boat b) {
		Alert alert =  new Alert(AlertType.CONFIRMATION, "Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == alert.getButtonTypes().get(0)) {
			registry.deleteBoat(m, b);
			displaySuccess("Boat Deleted !!");
			setBoatTable(m); 
			registry.saveRegistry();
		}
		else alert.close();
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
	public void quitProgram() {
		registry.saveRegistry();
	}

	/**********************FOR CREATING VIEW*****************************************************************/
	
	private void changeView() {
		boatTablePane.setVisible(false);
		compactListButton.setVisible(true);
		verboListButton.setVisible(true);
		createMemberButton.setVisible(true);
		setMemberTable(registry.getMemberList());
		memberTable.setVisible(true);
	}
	
	private GridPane createDialogeBox(Label l1, TextField first, Label l2, TextField second, ChoiceBox<BoatType> box){
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(20, 150, 10, 10));
		pane.add(l1, 0, 0);
		pane.add(first, 1, 0);
		pane.add(l2, 0, 1);
		if(second == null)
		pane.add(box, 1, 1);
		else pane.add(second, 1, 1);
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
					boatDeleteButton.setOnAction(event ->deleteBoat(member, boat));
				}
			}
		});
		boatTable.setItems(data);
	}
	
	private void setMemberTable(ArrayList<Member> m){
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
					deleteButton.setOnAction(event -> deleteMember(member));
				}
			}
		});

		memberTable.setItems(data);
		memberTable.setVisible(true);
	}
	
/******************FOR CHECKING INPUT*************************************************/	
	private boolean checkLength(TextField length){
		if (length.getText().isEmpty())
			return false;
		else {
			for (int i = 0; i < length.getText().length(); i++) {
				char c = length.getText().charAt(i);
				if (!Character.isDigit(c) && c != '.')
					return false;
			}
			if (Double.parseDouble(length.getText()) <= 0)
				return false;
		}
		return true;
	}
	private boolean checkName(TextField name){
		if(name.getText().isEmpty())
			return false;
			String s = name.getText();
			for(int i = 0; i < s.length(); i++){
				if(Character.isDigit(s.charAt(i)))
					return false;
			}
		return true;
	}
	
	private boolean checkPN(TextField name){
		if(name.getText().isEmpty() || name.getText().length() != 10)
			return false;
			String s = name.getText();
			for(int i = 0; i < s.length(); i++){
				if(Character.isLetter(s.charAt(i)))
					return false;
			}
		return true;
	}
}