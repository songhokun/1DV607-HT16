package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import model.Authentication;
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
	private Authentication authentication;
	private ArrayList<Member> searchedMemberList = new ArrayList<Member>();

	public Console() {
		authentication = new Authentication();
		try {
			registry = new Registry();
		} catch (Exception e) {
			displayError("WE HAD ERROR IN READING FILE");
			displayError(e.getLocalizedMessage());
			
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
			System.out.println("4: SIMPLE SEARCH A MEMBER");
			System.out.println("5: COMPLEX SEARCH A MEMBER");
	
			if (!authentication.isLoggedIn())
				System.out.println("6: LOG IN");
			System.out.print(quitSequence + ": QUIT\n>");

			input = scan.next();
			if (!authentication.isLoggedIn() && input.equals("3")) {
				displayError("ACCESS DENIED!! PLEASE LOG IN");
				continue;
			}
			try{
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
				case ("4"):
					displaySimpleSearchInstructions(true);
					break;
				case ("5"):
					displayComplexSearchInstructions();
					break;
					
				case ("6"):
					if (!authentication.isLoggedIn())
						logIn(getUsernameFromUser(), getPasswordFromUser());
					else
						displayError("INVALID OPTION");
					break;
				case (quitSequence):
					quitProgram();
					break;
				default:
					displayError("INVALID OPTION");
					break;
				}
			}
			catch(NumberFormatException e){
				displayError("ENTER NUMBER! "+e.getLocalizedMessage());
			}
			catch(ParseException e){
				displayError("ERRNEOUS INPUT! " + e.getLocalizedMessage());	
			}
		}
	}

	@Override
	public void displayCompactList(ArrayList<Member> m) {
		if (m.isEmpty()) {
			displayError("LIST IS EMPTY");
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
			return;
		}
		for (Member member : m) {
			displaySelectedMember(member);
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
		System.out.println("<<< " + error + " >>>");
	}

	@Override
	public void displaySuccess(String success) {
		System.out.println("*** " + success + " ***");
	}

	@Override
	public void logIn(String username, String password) {
		authentication.logIn(username, password);
		if (authentication.isLoggedIn())
			displaySuccess("LOG IN SUCCESSFULL");
		else
			displayError("LOG IN FAILED");
	}

	@Override
	public void simpleSearch(Object o) {
		searchedMemberList = registry.simpleSearch(o);
	}

	public void complexSearch(ArrayList<Member> previousResult, boolean isAnd) {
		searchedMemberList = registry.complexSearch(previousResult, searchedMemberList, isAnd);
	}

	@Override
	public void quitProgram() {
		registry.saveRegistry();
		scan.close();
		System.exit(1);
	}

	/***************** CONSOLE NAVIGATION *********************************/
	private void displayMemberInstructions() {
		while (input != quitSequence || input != returnSequence) {
			System.out.println("\nSELECT THE OPTION");
			System.out.println("1: CREATE A MEMBER");
			System.out.println("2: UPDATE A MEMBER");
			System.out.println("3: DELETE A MEMBER");
			if (!authentication.isLoggedIn())
				System.out.println("4: LOG IN");
			System.out.println(returnSequence + ": RETURN");
			System.out.print(quitSequence + ": QUIT\n>");

			input = scan.next();
			if (!authentication.isLoggedIn() && !input.equals(returnSequence) && !input.equals(quitSequence) && !input.equals("4")) {
				displayError("ACCESS DENIED!! PLEASE LOG IN");
				continue;
			}
			try{
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
				case ("4"):
					if (!authentication.isLoggedIn()) {
						logIn(getUsernameFromUser(), getPasswordFromUser());
						if (!searchedMemberList.isEmpty())
							displayVerboseList(searchedMemberList);
						else
							displayCompactList(registry.getMemberList());
					} else
						displayError("INVALID OPTION");
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
			catch(NumberFormatException e){
				displayError("ENTER NUMBER! "+e.getLocalizedMessage());
			}
			catch(ParseException e){
				displayError("ERRNEOUS INPUT! " + e.getLocalizedMessage());	
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
			try{
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
			catch(NumberFormatException e){
				displayError("ENTER NUMBER! "+e.getLocalizedMessage());
			}
			catch(ParseException e){
				displayError("ERRNEOUS INPUT! " + e.getLocalizedMessage());
				
			}
			catch(IndexOutOfBoundsException e){
				displayError(e.getLocalizedMessage());
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
		switch (input) {
		case ("1"):
			getBoatLengthFromUser();
			updateBoat(boatLength, null, registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			//displayUpdateMemberInstructions();
			break;
		case ("2"):
			getBoatTypeFromUser();
			updateBoat(0, boattype, registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			//displayUpdateMemberInstructions();
			break;
		case ("3"):
			getBoatLengthFromUser();
			getBoatTypeFromUser();
			updateBoat(boatLength, boattype, registry.lookUpMember(memberID).lookUpBoat(boatIndex - 1));
			displaySelectedMember(registry.lookUpMember(memberID));
			//displayUpdateMemberInstructions();
			break;
		case (returnSequence):
			displaySelectedMember(registry.lookUpMember(memberID));
			displayUpdateMemberInstructions();
			displaySelectedMember(registry.lookUpMember(memberID));
			//displayUpdateMemberInstructions();
			break;
		case (quitSequence):
			quitProgram();
			break;
		default:
			displayError("INVALID OPTION");
			break;
		}
	}
	private void displaySimpleSearchInstructions(boolean isPrintingResult){
		input = scan.next();
		try{
			while (input != quitSequence) {
				System.out.println("\nSELECT THE OPTION");
				System.out.println("1: " + SimpleSearchMode.ByName);
				System.out.println("2: " + SimpleSearchMode.OlderThanAge);
				System.out.println("3: " + SimpleSearchMode.GreaterThanBoatLength);
				System.out.println("4: " + SimpleSearchMode.ByMonth);
				System.out.println("5: " + SimpleSearchMode.ByBoatType);
				System.out.println(returnSequence+": RETURN");
				System.out.println(quitSequence+": QUIT");

				input = scan.next();

				switch (input) {
				case ("1"):
					getMemberNameFromUser();
					simpleSearch(input);
					break;
				case ("2"):
					getSearchAgeFromUser();
					simpleSearch(input);
					break;
				case ("3"):
					getBoatLengthFromUser();
					simpleSearch(input);
					break;
				case ("4"):
					getSearchMonthFromUser();
					simpleSearch(input);
					break;
				case ("5"):
					getBoatTypeFromUser();
					simpleSearch(boattype);
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

		}catch(NumberFormatException e){
			displayError("INCORRECT INPUT. ENTER NUMBER! "+e.getLocalizedMessage());
			return;
		}
		
		if(isPrintingResult){
			if (!searchedMemberList.isEmpty()) {
				displaySuccess(searchedMemberList.size() + " RESULT(S) FOUND");
				displayVerboseList(searchedMemberList);
				displayMemberInstructions();
			} else
				displayError("NO RESULT FOUND");
		}
	}

	private void displayComplexSearchInstructions() {
		ArrayList<Member> previousResult = new ArrayList<Member>(registry.getMemberList());
		boolean isAnd=true;
		
		while(true){
			
			System.out.println("PERFORMING COMPLEX SEARCH");
			displaySimpleSearchInstructions(false);
			complexSearch(previousResult, isAnd);
			previousResult = searchedMemberList;
			System.out.println("ENTER OR / AND TO OPERATE FURTHER SEARCH");
			System.out.print("ENTER ANYTHING ELSE TO FINISH SEARCH\n>");
			input = scan.next();
			
			if(input.compareToIgnoreCase("AND")==0){
				isAnd=true;
			}
			else if(input.compareToIgnoreCase("OR")==0)
				isAnd=false;
			else
				break;
					
		}
		if (!previousResult.isEmpty()) {
			displaySuccess(previousResult.size() + " RESULT(S) FOUND");
			displayVerboseList(previousResult);
		}
		else{
			displayError("NO RESULT FOUND");
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

	private void getMemberPersonalnumberFromUser() throws NumberFormatException, ParseException {
		System.out.print("PERSONAL NUMBER (YYYYMMDDXXXX)\n>");
		input = scan.next();
		checkPersonalnumber(input);
		/*
		while (!checkPersonalnumber(input)) {
			displayError("INCORRECT PERSONAL NUMBER!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		*/
		memberPN = input;
	}

	private void getMemberIDFromUser() {
		if (registry.getMemberList().isEmpty())
			throw new IndexOutOfBoundsException("List is empty!");
		
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
		System.out.println("BOAT TYPE");
		System.out.println("+ ID | Boat type +");
		
		for (BoatType b : BoatType.values())
			System.out.printf("%5d|%11s|\n", b.getCode(), b.toString());
		System.out.println("+----|-----------+");
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
			throw new IndexOutOfBoundsException("THIS MEMBER HAVE NO BOAT CURRENTLY");
			//displaySelectedMember(registry.lookUpMember(memberID));
			//displayUpdateMemberInstructions();
			//return;
		}
		System.out.print("PLEASE ENTER THE BOAT #\n>");
		input = scan.next();
		while (!checkBoatIndex(input, registry.lookUpMember(memberID))) {
			displayError("INVALID BOAT #!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		boatIndex = Integer.parseInt(input);
	}

	private String getUsernameFromUser() {
		System.out.print("USERNAME?\n>");
		input = scan.next();
		return input;
	}

	private String getPasswordFromUser() {
		System.out.print("PASSWORD?\n>");
		input = scan.next();
		return input;
	}

	private void getSearchAgeFromUser() {
		System.out.print("AGE\n>");
		input = scan.next();
		while (!checkSerachAge(input)) {
			displayError("INCORRECT AGE !! PLEASE TYPE AGAIN");
			input = scan.next();
		}
	}

	private Month getSearchMonthFromUser() {
		System.out.println("+ ID | MONTH     +");
		int i = 0;
		for (Month m : Month.values())
			System.out.printf("%5d|%11s|\n", ++i, m.toString());
		System.out.print("ENTER MONTH ID\n>");
		input = scan.next();
		while (!checkMonth(input)) {
			displayError("INVALID MONTH ID");
			input = scan.next();
		}
		return Month.of(Integer.parseInt(input));
	}

	/****************** HELPER METHODS FOR CORRECT INPUT *********/
	private boolean checkName(String name){
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

	private boolean checkPersonalnumber(String personalnumber) throws ParseException, NumberFormatException {
		if (personalnumber.length() != 12){
			throw new NumberFormatException("INCORRECT PERSONAL NUMBER FORMAT");
		}
		String pn = personalnumber.substring(0, 8);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		df.setLenient(false);
		df.parse(pn);
		
		return true;
	}

	private boolean checkMemberID(String input) {
		return registry.lookUpMember(Integer.parseInt(input)) != null;
	}

	private boolean checkBoatLength(String length) {
		if (Double.parseDouble(length) <= 0)
			return false;
		return true;
	}

	private boolean checkBoatType(String input, int size) {
		try {
			if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > size)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	private boolean checkBoatIndex(String input, Member m) {
		if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > m.getNumberOfBoats())
			throw new IndexOutOfBoundsException("INCORRECT BOAT INDEX");
	
		return true;
	}

	private boolean checkSerachAge(String age) {
		if (Integer.parseInt(age) > 0 && Integer.parseInt(age) <= 140)
			return true;
		return false;
	}

	private boolean checkMonth(String month) {
		return Month.of(Integer.parseInt(month)) != null;
	}
}