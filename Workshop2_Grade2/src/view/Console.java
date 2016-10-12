package view;

import java.text.ParseException;
import java.util.ArrayList;
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

	/**
	 * Constructor of console. Reads member data and boat file
	 */
	public Console() {
		try {
			registry = new Registry();
		} catch (Exception e) {
			displayError("THERE WAS A PROBLEM READING FILE");
			displayError("PLLEASE RESTART PROGRAMME");
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
			switch (input) {
			case ("1"):
				displayCompactList(registry.getMemberList());
				displayMemberInstructions();
				break;
			case ("2"):
				displayVerboseList(registry.getMemberList());
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
				displayError("INVALID OPTION");
				break;
			}
		}
	}

	@Override
	public void displayCompactList(ArrayList<Member> m) {
		if (m.isEmpty()) {
			displayError("LIST IS EMPTY");
			displayMainInstructions();
			return;
		}
		System.out.println("+ ID |  NAME OF A MEMBER  | TOTAL BOATS +");
		for (Member member : m)
			System.out.printf("%5d|%20s|%13d|\n", member.getMemberID(), member.getName(), member.getNumberOfBoats());
		System.out.println("+----|--------------------|-------------+");
	}

	@Override
	public void displayVerboseList(ArrayList<Member> m) {
		if (m.isEmpty()) {
			displayError("LIST IS EMPTY");
			displayMainInstructions();
			return;
		}
		for (Member member : m) {
			displaySelectedMember(member);
		}
	}

	@Override
	public void registerMember(String name, String personalnumber) {
		try {
			registry.createMember(name, personalnumber);
			displaySuccess("MEMBER CREATED SUCCESSFULLY !!");
		} catch (ParseException e) {
			displayError("INCORRECT PERSONAL NUMBER DATE FORMAT");
		}
	}

	@Override
	public void updateMember(Member m, String name, String personalnumber) {
		try{
		registry.updateMember(m, name, personalnumber);
		displaySuccess("MEMBER UPDATED SUCCESSFULLY !!");
		}catch (ParseException e) {
			displayError("INCORRECT PERSONAL NUMBER DATE FORMAT");
		}
	}

	@Override
	public void deleteMember(Member m) {
		registry.deleteMember(m);
		displaySuccess("MEMBER DELETED SUCCESSFULLY !!");
	}

	@Override
	public void displaySelectedMember(Member m) {
		System.out.println("\nMEMBER ID: " + m.getMemberID());
		System.out.printf("NAME:%s (PNR.%s) HAS %d BOATS\n", m.getName(), m.getPersonalnumber(), m.getNumberOfBoats());
		if (m.getNumberOfBoats() > 0) {
			System.out.println("+ # |  BOAT TYPE  | LENGTH (m) +");
			int i = 0;
			for (Boat b : m.getBoatList())
				System.out.printf("%4d|%13s|%6.2f      |\n", ++i, b.getType(), b.getLength());
			System.out.printf("+---|-------------|------------+\n");
		}

	}

	@Override
	public void registerBoat(Member m, double boatLength, BoatType boattype) {
		try {
			m.registerBoat(boatLength, boattype);
			displaySuccess("BOAT REGISTERD SUCCESSFULLY !!");
		} catch (Exception e) {
			displayError("UNABLE TO REGISTER A BOAT. LENGHT IS INCORRECT");
		}
	}

	@Override
	public void updateBoat(Member m, double length, BoatType type, Boat boat) {
		try {
			m.updateBoat(length, type, boat);
			displaySuccess("BOAT UPDATED SUCCESSFULLY !!");
		} catch (Exception e) {
			displayError("UNABLE TO REGISTER A BOAT. LENGHT IS INCORRECT");
		}

	}

	@Override
	public void deleteBoat(Member m, Boat b) {
		m.deleteBoat(b);
		displaySuccess("BOAT DELETED SUCCESSFULLY !!");
	}

	@Override
	public void displayError(String error) {
		System.out.println("<<< " + error + " >>>");
	}

	@Override
	public void displaySuccess(String success) {
		System.out.println("*** " + success + " ***");
	}

	@Override
	public void quitProgram() {
		registry.saveRegistry();
		scan.close();
		System.exit(1);
	}

	/***************** CONSOLE NAVIGATION *********************************/
	private void displayMemberInstructions() {
		if (registry.getMemberList().isEmpty())
			return;
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
				displayCompactList(registry.getMemberList());
				break;
			case ("2"):
				getMemberIDFromUser();
				displaySelectedMember(registry.lookUpMember(memberID));
				displayUpdateMemberInstructions();
				displayCompactList(registry.getMemberList());
				break;
			case ("3"):
				getMemberIDFromUser();
				deleteMember(registry.lookUpMember(memberID));
				displayCompactList(registry.getMemberList());
				break;
			case (returnSequence):
				displayMainInstructions();
				break;
			case (quitSequence):
				quitProgram();
				break;
			default:
				displayError("INVALID OPTION");
				break;
			}
		}
	}

	private void displayUpdateMemberInstructions() {
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

			// After each operations we display member's information again to
			// show the changes.
			switch (input) {
			case ("1"):
				getMemberNameFromUser();
				// Member is only updating its name. Thus personal number is
				// provided in ""
				updateMember(registry.lookUpMember(memberID), memberName, "");
				displaySelectedMember(registry.lookUpMember(memberID));
				break;
			case ("2"):
				getMemberPersonalnumberFromUser();
				// Member is only updating its personal number. Thus name is
				// provided in ""
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
				// The console cannot select boat without using index since we
				// do not have boat IDs.
				// Mind that array always begins with index 0. Thus index-1 is
				// required.
				deleteBoat(registry.lookUpMember(memberID),
						registry.lookUpMember(memberID).getBoatList().get((boatIndex - 1)));
				displaySelectedMember(registry.lookUpMember(memberID));
				break;
			case (returnSequence):
				displayCompactList(registry.getMemberList());
				displayMemberInstructions();
				break;
			case (quitSequence):
				quitProgram();
				break;
			default:
				displayError("INVALID OPTION");
				break;
			}
		}

	}

	private void displayUpdateBoatInstructions() {
		System.out.println("\nSELECT THE OPTION");
		System.out.println("1: UPDATE LENGTH");
		System.out.println("2: UPDATE BOAT TYPE");
		System.out.println("3: UPDATE LENGTH & BOAT TYPE");
		System.out.println(returnSequence + ": RETURN");
		System.out.print(quitSequence + ": QUIT\n>");

		input = scan.next();
		// Mind that array always begins with index 0. Thus index-1 is required.
		switch (input) {
		case ("1"):
			getBoatLengthFromUser();
			// Member is only updating its boat's length. Thus type is provided
			// in null
			updateBoat(registry.lookUpMember(memberID), boatLength, null,
					registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			break;
		case ("2"):
			getBoatTypeFromUser();
			// Member is only updating its boat's type. Thus length is provided
			// in 0
			updateBoat(registry.lookUpMember(memberID), 0, boattype,
					registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			break;
		case ("3"):
			getBoatLengthFromUser();
			getBoatTypeFromUser();
			updateBoat(registry.lookUpMember(memberID), boatLength, boattype,
					registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			break;
		case (returnSequence):
			displaySelectedMember(registry.lookUpMember(memberID));
			break;
		case (quitSequence):
			quitProgram();
			break;
		default:
			displayError("INVALID OPTION");
			break;
		}
	}

	/***************************** CONSOLE INPUT DATA METHODS ************/

	private void getMemberNameFromUser() {
		System.out.print("NAME\n>");
		input = scan.next() + scan.nextLine();
		while (!checkName(input)) {
			displayError("INCORRECT NAME!! PLEASE WRITE AGAIN (Eg: John Smith)");
			input = scan.next() + scan.nextLine();
		}
		memberName = input;
	}

	private void getMemberPersonalnumberFromUser() {
		System.out.print("PERSONAL NUMBER (YYYYMMDDXXXX)\n>");
		input = scan.next();
		while (!checkPersonalnumber(input)) {
			displayError("INCORRECT PERSONAL NUMBER!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		memberPN = input;
	}

	private void getMemberIDFromUser() {
		if (registry.getMemberList().isEmpty()) {
			displayError("LIST IS EMPTY");
			displayMemberInstructions();
		}
		System.out.print("PLEASE TYPE THE MEMBER ID\n>");
		input = scan.next();
		while (!checkMemberID(input)) {
			displayError("INVALID MEMBER ID!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		memberID = Integer.parseInt(input);
	}

	private void getBoatLengthFromUser() {
		System.out.print("LENGTH(m)\n>");
		input = scan.next();
		while (!checkBoatLength(input)) {
			displayError("INCORRECT LENGTH!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		boatLength = Double.parseDouble(input);
	}

	private void getBoatTypeFromUser() {
		// Prints out types of boats
		System.out.println("BOAT TYPE");
		System.out.println("+ ID | Boat type +");
		for (BoatType b : BoatType.values())
			System.out.printf("%5d|%11s|\n", b.getCode(), b.toString());
		System.out.println("+----|-----------+");

		// receives an input
		System.out.print("\nENTER BOAT TYPE ID\n>");
		input = scan.next();
		while (!checkBoatType(input, BoatType.values().length)) {
			displayError("INVALID BOAT TYPE ID!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		for (BoatType b : BoatType.values())
			if (b.getCode() == Integer.parseInt(input)) {
				boattype = b;
				return;
			}
	}

	private void getBoatIndexFromUser() {
		if (registry.lookUpMember(memberID).getNumberOfBoats() == 0) {
			displayError("THIS MEMBER HAVE NO BOAT CURRENTLY");
			displaySelectedMember(registry.lookUpMember(memberID));
			displayUpdateMemberInstructions();
			return;
		}
		System.out.print("PLEASE ENTER THE BOAT #\n>");
		input = scan.next();
		while (!checkBoatIndex(input, registry.lookUpMember(memberID))) {
			displayError("INVALID BOAT #!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		boatIndex = Integer.parseInt(input);
	}

	/****************** HELPER METHODS FOR CORRECT INPUT *********/
	/**
	 * 
	 * @param name
	 * @return true if name is only consists of alphabetic letters and white
	 *         spaces.
	 */
	private boolean checkName(String name) {
		boolean charexists = false;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (Character.isAlphabetic(c))
				charexists = true;
			if (!Character.isAlphabetic(c) && !Character.isWhitespace(c))
				return false;
		}
		return charexists;
	}

	/**
	 * 
	 * @param personalnumber
	 * @return true if length is correct and provided date is in valid date.
	 */
	private boolean checkPersonalnumber(String personalnumber) {
		return personalnumber.length() == 12;
	}

	/**
	 * 
	 * @param input
	 * @return true if member id exists in registry
	 */
	private boolean checkMemberID(String input) {
		try {
			if (registry.lookUpMember(Integer.parseInt(input)) == null)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param length
	 * @return true if boat length is correct data type and greater than zero
	 */
	private boolean checkBoatLength(String length) {
		try {
			Double.parseDouble(length);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param input
	 * @param size
	 * @return true if input lies within the index of boat types
	 */
	private boolean checkBoatType(String input, int size) {
		try {
			if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > size)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * 
	 * @param input
	 * @param m
	 * @return if provided boat index is valid boat index of boat list that m
	 *         has.
	 */
	private boolean checkBoatIndex(String input, Member m) {
		try {
			if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > m.getNumberOfBoats())
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}