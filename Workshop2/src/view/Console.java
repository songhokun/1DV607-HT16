package view;

import java.util.Scanner;
import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;

public class Console implements IView {

	private String input;
	private String memberName;
	private String memberPN;
	private int memberID = 0;
	private double boatLength = 0;
	private BoatType boattype;
	private int boatIndex = 0;
	private Scanner scan = new Scanner(System.in);
	private Registry registry;
	private final String quitSequence = "q";
	private final String returnSequence = "r";

	public Console() {
		input = "";
		memberName = "";
		memberPN = "";
		try {
			registry = new Registry();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	@Override
	public void startProgram() {
		displayWelcomeMessage();
		displayMainInstructions();
	}

	@Override
	public void displayWelcomeMessage() {
		System.out.println("***************************************");
		System.out.println("* WELCOME TO MEMBER REGISTERY PROGRAM *");
		System.out.println("***************************************\n");
	}

	@Override
	public void displayMainInstructions() {
		while (input != quitSequence) {
			System.out.println("\nSELECT THE OPTION");
			System.out.println("1: DISPLAY COMPACT LIST");
			System.out.println("2: DISPLAY VERBOSE LIST");
			System.out.println("3: CREATE A MEMBER");
			System.out.print(quitSequence + ": QUIT\n>");

			input = scan.next();
			if (registry.getMemberList().isEmpty()){
				if(input.equals("1") || input.equals("2")){
					System.out.println("NO MEMBER IN THE LIST\n");
					displayMainInstructions(); 
				}
			}
			switch (input) {
			case ("1"):
					displayCompactList();
					displayMemberInstructions();
				break;
			case ("2"):
					displayVerboseList();
					displayMemberInstructions();
				break;
			case ("3"):
				getMemberNameFromUser();
				getMemberPersonalnumberFromUser();
				registerMember(memberName, memberPN);
				break;
			case (quitSequence):
				quitProgram();
				break;
			default:
				System.out.println("INVALID OPTION");
				break;
			}
		}
	}

	@Override
	public void displayCompactList() {
		System.out.println("+ ID |  NAME OF A MEMBER  | TOTAL BOATS +");
		for (Member m : registry.getMemberList())
			System.out.printf("%5d|%20s|%13d|\n", m.getMemberID(), m.getName(), m.getNumberOfBoats());
		System.out.println("+----|--------------------|-------------+");
	}

	@Override
	public void displayVerboseList() {
		for (Member m : registry.getMemberList()) {
			System.out.println("\nMEMBER ID: " + m.getMemberID());
			System.out.println("NAME: " + m.getName());
			System.out.println("PERSONAL NUMBER: " + m.getPersonalnumber());
			if (m.getNumberOfBoats() > 0) {
				System.out.println("THIS MEMBER HAS FOLLOWING BOATS:");
				System.out.println("+ # |  BOAT TYPE  | LENGTH (m) +");
				int i = 0;
				for (Boat b : m.getBoatList())
					System.out.printf("%4d|%13s|%6.2f      |\n", ++i, b.getType(), b.getLength());
				System.out.printf("+---|-------------|------------+\n");
			} else
				System.out.println("THIS MEMBER DOES NOT HAVE ANY BOAT.");
		}
	}

	@Override
	public void registerMember(String name, String personalnumber) {
		registry.createMember(name, personalnumber);
		displaySuccess("MEMBER CREATED SUCCESSFULLY !!");
	}

	@Override
	public void updateMember(Member m, String name, String personalnumber) {
		registry.updateMember(m, name, personalnumber);
		displaySuccess("MEMBER UPDATED SUCCESSFULLY !!");
	}

	@Override
	public void deleteMember(Member m) {
		registry.deleteMember(m);
		displaySuccess("MEMBER DELETED SUCCESSFULLY !!");
	}

	@Override
	public void displaySelectedMember(Member m) {
		System.out.println("\nMEMBER ID: " + m.getMemberID());
		System.out.println("NAME: " + m.getName());
		System.out.println("PERSONAL NUMBER: " + m.getPersonalnumber());
		System.out.println("NUMBER OF BOATS: " + m.getNumberOfBoats());
		if (m.getNumberOfBoats() > 0) {
			System.out.println("+ # |  BOAT TYPE  | LENGTH (m) +");
			int i = 0;
			for (Boat b : m.getBoatList())
				System.out.printf("%4d|%13s|%6.2f      |\n", ++i, b.getType(), b.getLength());
			System.out.printf("+---|-------------|------------+\n");
		} else
			System.out.println("THIS MEMBER DOES NOT HAVE ANY BOAT.");
	}

	@Override
	public void registerBoat(Member m, double boatLength, BoatType boattype) {
		registry.registerBoat(m, boatLength, boattype);
		displaySuccess("BOAT REGISTERD SUCCESSFULLY !!");
	}

	@Override
	public void updateBoat(double length, BoatType type, Boat boat) {
		registry.updateBoat(length, type, boat);
		displaySuccess("BOAT UPDATED SUCCESSFULLY !!");
	}

	@Override
	public void deleteBoat(Member m, Boat b) {
		registry.deleteBoat(m, b);
		displaySuccess("BOAT DELETED SUCCESSFULLY !!");
	}

	@Override
	public void displayError(String error) {
		System.err.println("*** " + error + " ***\n");
	}

	@Override
	public void displaySuccess(String success) {
		System.out.println("*** " + success + " ***\n");
	}

	@Override
	public void quitProgram() {
		registry.saveRegistry();
		scan.close();
		System.exit(1);
	}

	/***************** CONSOLE NAVIGATION *********************************/
	public void displayMemberInstructions() {
		while (input != quitSequence || input != returnSequence) {
			System.out.println("\nSELECT THE OPTION");
			System.out.println("1: CREATE A MEMBER");
			System.out.println("2: UPDATE A MEMBER");
			System.out.println("3: DELETE A MEMBER");
			System.out.println(returnSequence + ": RETURN");
			System.out.print(quitSequence + ": QUIT\n>");

			input = scan.next();
			switch (input) {
			case ("1"):
				getMemberNameFromUser();
				getMemberPersonalnumberFromUser();
				registerMember(memberName, memberPN);
				displayCompactList();
				break;
			case ("2"):
				getMemberIDFromUser();
				displaySelectedMember(registry.lookUpMember(memberID));
				displayUpdateMemberInstructions();
				displayCompactList();
				break;
			case ("3"):
				getMemberIDFromUser();
				deleteMember(registry.lookUpMember(memberID));
				displayCompactList();
				break;
			case (returnSequence):
				displayMainInstructions();
				break;
			case (quitSequence):
				quitProgram();
				break;
			default:
				System.err.println("INVALID OPTION");
				break;
			}
		}
	}

	public void displayUpdateMemberInstructions() {
		while (input != quitSequence) {
			System.out.println("\nSELECT THE OPTION");
			System.out.println("1: UPDATE NAME");
			System.out.println("2: UPDATE PERSONAL NUMBER");
			System.out.println("3: UPDATE NAME & PERSONAL NUMBER");
			System.out.println("4: REGISTER A BOAT");
			System.out.println("5: UPDATE A BOAT");
			System.out.println("6: DELETE A BOAT");
			System.out.println(returnSequence + ": RETURN");
			System.out.print(quitSequence + ": QUIT \n>");
			
			input = scan.next();
			switch (input) {
			case ("1"):
				getMemberNameFromUser();
				updateMember(registry.lookUpMember(memberID), memberName, "");
				displaySelectedMember(registry.lookUpMember(memberID));
				break;
			case ("2"):
				getMemberPersonalnumberFromUser();
				updateMember(registry.lookUpMember(memberID), "", memberPN);
				displaySelectedMember(registry.lookUpMember(memberID));
				break;
			case ("3"):
				getMemberNameFromUser();
				getMemberPersonalnumberFromUser();
				updateMember(registry.lookUpMember(memberID), memberName, memberPN);
				displaySelectedMember(registry.lookUpMember(memberID));
				break;
			case ("4"):
				getBoatLengthFromUser();
				getBoatTypeFromUser();
				registerBoat(registry.lookUpMember(memberID), boatLength, boattype);
				displaySelectedMember(registry.lookUpMember(memberID));
				break;
			case ("5"):
				getBoatIndexFromUser();
				displayUpdateBoatInstructions();
				break;
			case ("6"):
				getBoatIndexFromUser();
				deleteBoat(registry.lookUpMember(memberID), registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
				displaySelectedMember(registry.lookUpMember(memberID));
				break;
			case (returnSequence):
				displayCompactList();
				displayMemberInstructions();
				break;
			case (quitSequence):
				quitProgram();
				break;
			default:
				System.out.println("INVALID OPTION!");
				break;
			}
		}

	}

	public void displayUpdateBoatInstructions() {
		System.out.println("\nSELECT THE OPTION");
		System.out.println("1: UPDATE LENGTH");
		System.out.println("2: UPDATE BOAT TYPE");
		System.out.println("3: UPDATE LENGTH & BOAT TYPE");
		System.out.println(returnSequence + ": RETURN");
		System.out.print(quitSequence + ": QUIT\n>");

		input = scan.next();
		switch (input) {
		case ("1"):
			getBoatLengthFromUser();
			updateBoat(boatLength, null, registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			displayUpdateMemberInstructions();
			break;
		case ("2"):
			getBoatTypeFromUser();
			updateBoat(0, boattype, registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			displayUpdateMemberInstructions();
			break;
		case ("3"):
			getBoatLengthFromUser();
			getBoatTypeFromUser();
			updateBoat(boatLength, boattype, registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			displayUpdateMemberInstructions();
			break;
		case (returnSequence):
			displaySelectedMember(registry.lookUpMember(memberID));
			displayUpdateMemberInstructions();
			displaySelectedMember(registry.lookUpMember(memberID));
			displayUpdateMemberInstructions();
			break;
		case (quitSequence):
			quitProgram();
			break;
		default:
			displayError("INVALID OPTION");
			break;
		}
	}

	/***************************** CONSOLE VIEW HANDLER ************/
	public void getMemberNameFromUser() {
		System.out.print("NAME (Only Letters)\n>");
		input = scan.next() + scan.nextLine();
		while (!checkName(input)) {
			displayError("INCORRECT NAME!! PLEASE WRITE AGAIN");
			input = scan.next() + scan.nextLine();
		}
		memberName = input;
	}

	public void getMemberPersonalnumberFromUser() {
		System.out.print("PERSONAL NUMBER (YYMMDDXXXX)\n>");
		input = scan.next();
		while (!checkPersonalnumber(input)) {
			displayError("INCORRECT PERSONAL NUMBER!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		memberPN = input;
	}

	public void getMemberIDFromUser() {
		System.out.print("PLEASE TYPE THE MEMBER ID\n>");
		input = scan.next();
		while (!checkMemberID(input)) {
			displayError("INVALID ID");
			input = scan.next();
		}
		memberID = Integer.parseInt(input);
	}

	public void getBoatLengthFromUser() {
		System.out.print("LENGTH(m)\n>");
		input = scan.next();
		while (!checkBoatLength(input)) {
			displayError("INCORRECT LENGTH!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		boatLength = Double.parseDouble(input);
	}

	public void getBoatTypeFromUser() {
		System.out.println("BOAT TYPE");
		System.out.println("+ ID | Boat type +");
		for (BoatType b : BoatType.values())
			System.out.printf("%5d|%11s|\n", b.getCode(), b.toString());
		System.out.println("+----|-----------+");
		System.out.print("\nENTER BOAT TYPE ID\n>");
		input = scan.next();
		while (!checkBoatType(input, BoatType.values().length)) {
			displayError("INVALID ID");
			input = scan.next();
		}
		for (BoatType b : BoatType.values())
			if (b.getCode() == Integer.parseInt(input)) {
				boattype = b;
				return;
			}
	}

	public void getBoatIndexFromUser() {
		if (registry.lookUpMember(memberID).getNumberOfBoats() == 0) {
			displayError("THIS MEMBER HAVE NO BOAT CURRENTLY!!");
			displaySelectedMember(registry.lookUpMember(memberID));
			displayUpdateMemberInstructions();
			return;
		}
		System.out.print("PLEASE ENTER THE BOAT #\n>");
		input = scan.next();
		while (!checkBoatIndex(input, registry.lookUpMember(memberID))) {
			displayError("INVALID #");
			input = scan.next();
		}
		boatIndex = Integer.parseInt(input);
	}

	/****************** HELPER METHODS FOR CORRECT INPUT *********/
	private boolean checkName(String name) {
		if (name.isEmpty())
			return false;
		for (int i = 0; i < name.length(); i++) {
			if (Character.isDigit(name.charAt(i)))
				return false;
		}
		return true;
	}

	private boolean checkPersonalnumber(String personalnumber) {
		if (personalnumber.isEmpty() || personalnumber.length() != 10)
			return false;
		else if (containsLetter(personalnumber))
			return false;
		return true;
	}

	private boolean checkMemberID(String input) {
		if (input.isEmpty() || containsLetter(input))
			return false;
		else {
			if (Integer.parseInt(input) <= 0)
				return false;

			else if (registry.lookUpMember(Integer.parseInt(input)) != null)
				return true;
		}
		return false;
	}

	private boolean checkBoatLength(String length) {
		if (length.isEmpty())
			return false;
		else {
			for (int i = 0; i < length.length(); i++) {
				char c = length.charAt(i);
				if (!Character.isDigit(c) && c != '.')
					return false;
			}
			if (Double.parseDouble(length) <= 0)
				return false;
		}
		return true;
	}

	private boolean checkBoatType(String input, int size) {
		if (input.isEmpty() || containsLetter(input))
			return false;
		if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > size)
			return false;
		return true;
	}

	private boolean checkBoatIndex(String input, Member m) {
		if (input.isEmpty() || containsLetter(input))
			return false;
		else if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > m.getBoatList().size())
			return false;
		return true;
	}

	private boolean containsLetter(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (Character.isLetter(input.charAt(i)))
				return true;
		}
		return false;
	}
}