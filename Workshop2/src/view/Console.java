package view;

import java.io.FileNotFoundException;
import java.util.Scanner;
import model.Boat;
import model.Boat.BoatType;
import model.Member;

public class Console {

	private model.Registry reg;
	private String quitSequence = "q";
	private String backSequence = "r";
	private Scanner scan = new Scanner(System.in);

	public Console() {
		try {
			reg = new model.Registry();
		} catch (FileNotFoundException e) {
			System.out.println("There was a problem in loading a file becuase it did not exist!\n");
		}
	}
	public Console(String path) {
		try {
			reg = new model.Registry();
		} catch (FileNotFoundException e) {
			System.out.println("There was a problem in loading a file becuase it did not exist!\n");
		}
	}

	public void startProgram() {
		System.out.println("Boat club programme.");
		while (displayMainInstructions())
			;
		scan.close();
	}

	public boolean displayMainInstructions() {
		System.out.println("Please select following options:");
		System.out.println("1 : List all members");
		System.out.println("2 : Create a member");
		System.out.print(quitSequence+" : quit\n>");

		String userInput = scan.next();
		if (userInput.equals(quitSequence))
			return false;
		else {
			try {
				if (userInput.equals("1"))
					displayMemberListType();
				else if (userInput.equals("2"))
					displayAddMember();

			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		return true;
	}

	public void displayInstructionMemberSelected(Member m) throws Exception{
		clearConsole();
		
		System.out.println("Selected member "+m.getName()+";"
				+ "\nPersonal number: "+m.getPersonalnumber());
		displaySpecificMemberBoats(m);
		
		String options[] = {"","Delete the member","Update the member","Add a boat", "Update a boat", "Remove a boat"};
		
		System.out.println("\nSelect following options:");
		for(int i=1;i<options.length;i++)
			System.out.printf("%d: %s\n",i,options[i]);
		
		System.out.print(">");
		int userInput = scan.nextInt();
		
		switch(userInput){
		case 1:
			controlDeleteMember(m);
			break;
		case 2:
			displayUpdateMember(m);
			break;
		case 3:
			displayAddBoat(m);
			break;
		case 4:
			displayUpdateBoat(m);
			break;
		case 5:
			displayRemoveBoat(m);
			break;
		default:
			System.out.println("Invalid choice");
			break;
		}
		
	}
	
	public void displayUpdateMember(Member m) throws Exception {
		/*System.out.print("Enter the memeber's ID \n>");
		String input = scan.next();
		int id = Integer.parseInt(input);
		if (!reg.isMemberExist(id))
			throw new Exception("Invalid member ID");
		*/

		System.out.println("Please select following options:");
		System.out.println("1 : Update name");
		System.out.println("2 : Update personal number");
		System.out.println("3 : Update both");
		System.out.println(backSequence+" : go back");
		System.out.print(">");
		String name = "";
		String personalNumber = "";
		String input = scan.next();
		clearConsole();
		
		if (input.equals(backSequence)) {
			return;
		}
		if (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 3) {
			System.out.print("Enter name\n>");
			name = scan.next();
			while (scan.hasNext()) {
				name = name + scan.nextLine();
				break;
			}
		}
		if (Integer.parseInt(input) == 2 || Integer.parseInt(input) == 3) {
			System.out.print("Enter Personal number\n>");
			personalNumber = scan.next();
		}
		reg.updateMember(m, name, personalNumber);
		displaySuccess("Member Updated");
	}
	
	public void controlDeleteMember(Member inMember){
		try{
			reg.deleteMember(inMember);
			displaySuccess("Member Deleted!!");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	
	public void displayAddMember() {
		System.out.print("Name \n>");
		String name = scan.next();
		while (scan.hasNext()) {
			name = name + scan.nextLine();
			break;
		}
		System.out.print("Personal Number\n>");
		String personalNumber = scan.next();
		reg.createMember(name, personalNumber);
		displaySuccess("Member Created");
	}

	public void displayMemberListType() throws Exception {
		if (!(reg.getRegistry().size() > 0))
			throw new Exception("Member does not exist!\n");

		System.out.println("1 : list by compact");
		System.out.println("2 : list by verbose");
		System.out.print('>');
		String userInput = scan.next();
		
		clearConsole();
		if (Integer.parseInt(userInput) == 1) {
			displayCompactList();
		} else if (Integer.parseInt(userInput) == 2) {
			displayVerboseList();
		}
		System.out.println("\nEnter ID of a member if you want to select. Otherwise enter "+backSequence+" to return");
		System.out.print("> ");
		userInput = scan.next();
		
		if(userInput.equals(backSequence)){
			clearConsole();
		}
		else{
			Member selected = reg.lookUpMember(Integer.parseInt(userInput));
			if(selected==null)
				throw new Exception("Invalid member ID!");
				
			displayInstructionMemberSelected(selected);
		}
			
	}

	public void displayCompactList() {
		System.out.println("+ id |  name of a member  | # boats+");
		for (Member m : reg.getRegistry())
			System.out.printf("%5d|%20s|%8d|\n",m.getMemberID(), m.getName(), m.getNumberOfBoats());
		System.out.println("+----|--------------------|--------+");
	}

	public void displayVerboseList() {
		for (Member m : reg.getRegistry()) {
			System.out.printf("[%d] %s, personal number %s ", m.getMemberID(), m.getName(),
					m.getPersonalnumber());
			if (m.getNumberOfBoats() != 0) {
				System.out.println("has following boats: ");
				for (Boat b : m.getBoatdata())
					System.out.printf("\t%.2f m length, %s\n", b.getLength(), b.getType());
			} else
				System.out.println("does not have any boat.");
		}
	}

	private void displaySuccess(String message) {
		System.out.println("****** " + message + "!! *******");
	}
	private BoatType displayBoatTypes(){
		
		System.out.println("+ ID | Boat type +");
		for (BoatType b : BoatType.values())
			System.out.printf("%5d|%11s|\n", b.getCode(),b.toString());
		System.out.println("+----|-----------+");
		
		System.out.print("\nEnter Boat type ID\n>");
		int type = scan.nextInt();
		for(BoatType b : BoatType.values())
			if(b.getCode()==type)
				return b;
		
		return null;
	}
	public void displayAddBoat(Member inMember) throws Exception {
		System.out.print("Length?\n>");
		double length = scan.nextDouble();
		
		reg.registerBoat(inMember, length, displayBoatTypes());
		//inMember.registerBoat(length, displayBoatTypes());		
		displaySuccess("Boat added");
	}

	public void displayUpdateBoat(Member inMember) throws Exception {
		Boat selectedBoat = selectBoat(inMember);
		
		System.out.println("Choose one of the following options:");
		System.out.println("1: Update length");
		System.out.println("2: Update type");
		System.out.println("3: Update both");
		int choice = scan.nextInt();
		double length = -1;
		BoatType type = null;
		if (choice == 1 || choice == 3) {
			System.out.print("Length\n>");
			length = scan.nextDouble();
		}
		if (choice == 2 || choice == 3) {
			type = displayBoatTypes();
		} else if (choice < 1 || choice > 3)
			throw new Exception("Invalid option");

		reg.updateBoat(length, type, selectedBoat);
		//inMember.updateBoat(length, type, index);
		displaySuccess("Boat Updated");
	}

	public void displayRemoveBoat(Member inMember) throws Exception {
		if(inMember.getNumberOfBoats() == 0)
			throw new Exception("Member have no boat.");
		
		reg.deleteBoat(inMember, selectBoat(inMember));
		
		//inMember.deleteBoat(index);
		displaySuccess("Boat deleted");
	}
	private Boat selectBoat(Member inMember) throws Exception{
		if(inMember.getNumberOfBoats() == 0)
			throw new Exception("Member have no boat.");
		System.out.print("\nEnter Boat # to Update\n>");
		int index = scan.nextInt();
		
		return inMember.lookUpBoat(index);
	}

	// this method does not print nice output. You can fix this. I am not good
	// in this.
	public void displaySpecificMemberBoats(Member m) {
		if(m.getNumberOfBoats()>0){
			System.out.println("+ # |  Boat Type  | Length +");
			int i = 0;
			for (Boat b : m.getBoatdata())
				System.out.printf("%4d|%13s|%6.2f  |\n", ++i,b.getType(),b.getLength());
				//System.out.println(++i + "    |   " + b.getType().toString() + "         |  " + b.getLength());
			System.out.printf("+---|-------------|--------+\n");
		}
		else
			System.out.println("This member does not have any boat.");
		
	}
	private void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
}