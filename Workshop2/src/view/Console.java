package view;

import java.io.FileNotFoundException;
import java.util.Scanner;
import model.Boat;
import model.Boat.BoatType;
import model.Member;

public class Console implements IView {

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

	@Override
	public void startProgram() {
		System.out.println("Boat club programme.");
		while (displayMainInstructions())
			;
		scan.close();
	}

	@Override
	public boolean displayMainInstructions() {
		System.out.println("Please select following options:");
		System.out.println("1 : List all members");
		System.out.println("2 : Create a member");
		/*System.out.println("3 : Delete a member");
		System.out.println("4 : Update a member");
		System.out.println("5 : Add a boat");
		System.out.println("6 : Update a boat");
		System.out.println("7 : Remove a boat");*/
		System.out.print(quitSequence+" : quit\n>");

		String userInput = scan.next();
		if (userInput.equals(quitSequence))
			return false;
		else {
			try {
				if (userInput.equals("1"))
					displayMemberListType();
				else if (userInput.equals("2"))
					displayAddMemberDetails();
				/*else if (userInput.equals("3"))
					displayDeleteMemberDetails();
				else if (userInput.equals("4"))
					displayUpdateMemberDetails();
				else if (userInput.equals("5"))
					displayAddBoatDetails();
				else if (userInput.equals("6"))
					displayUpdateBoatDetails();
				else if (userInput.equals("7"))
					displayDeleteBoatDetails();*/
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		return true;
	}

	public void displayInstructionMemberSelected(Member m){
		clearConsole();
		
		System.out.println("Member "+m.getName()+" is selected. This member has following boats: ");
		displaySpecificMemberBoats(m);
		System.out.println("\nSelect following options:");
		System.out.println("1 : Delete a member");
		System.out.println("2 : Update a member");
		System.out.println("3 : Add a boat");
		System.out.println("4 : Update a boat");
		System.out.println("5 : Remove a boat");
		
		
	}
	@Override
	public void displayUpdateMemberDetails() throws Exception {
		System.out.print("Enter the memeber's ID \n>");
		String input = scan.next();
		int id = Integer.parseInt(input);
		if (!reg.isMemberExist(id))
			throw new Exception("Invalid member ID");

		System.out.println("Please select following options:");
		System.out.println("1 : Update name");
		System.out.println("2 : Update personal number");
		System.out.println("3 : Update both");
		System.out.println(backSequence+" : go back");
		System.out.print(">");
		String name = "";
		String personalNumber = "";
		input = scan.next();
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
		reg.updateMember(id, name, personalNumber);
		displaySuccess("Member Updated");
	}
	@Override
	public void displayDeleteMemberDetails() throws Exception {
		System.out.print("Enter the memebr's ID \n>");
		int input = scan.nextInt();
		if (!reg.isMemberExist(input)) {
			throw new Exception("Invalid member ID");
		}
		reg.deleteMember(input);
		displaySuccess("Member Deleted");
	}

	@Override
	public void displayAddMemberDetails() {
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

	@Override
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
			if (!reg.isMemberExist(Integer.parseInt(userInput))) {
				throw new Exception("Invalid member ID");
			}
			displayInstructionMemberSelected(reg.lookUpMember(Integer.parseInt(userInput)));
		}
			
	}

	@Override
	public void displayCompactList() {
		int i = 0;
		System.out.println("+ # |  name of a member  | id | # boats+");
		for (Member m : reg.getRegistry())
			System.out.printf("%4d|%20s|%4s|%8d|\n", ++i, m.getName(), m.getMemberID(), m.getNumberOfBoats());
		System.out.println("+---|--------------------|----|--------+");
	}

	@Override
	public void displayVerboseList() {
		int i = 0;
		for (Member m : reg.getRegistry()) {
			System.out.printf("[%d] %s, which id is %s, and personal number is %s ", ++i, m.getName(), m.getMemberID(),
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

	public void displayAddBoatDetails() throws Exception {
		displayCompactList();
		System.out.print("Enter the memebr's ID \n>");
		int input = scan.nextInt();
		if (!reg.isMemberExist(input)) {
			throw new Exception("Invalid member ID");
		}
		System.out.print("Length\n>");
		double length = scan.nextDouble();
		int i = 0;
		System.out.println("ID  |  Boat type\n____|______________");
		for (BoatType b : BoatType.values()) {
			System.out.println(++i + "   |  " + b.toString());
		}
		System.out.print("\nEnter Boat type ID\n>");
		int type = scan.nextInt();
		reg.lookUpMember(input).registerBoat(length, type);
		displaySuccess("Boat added");
	}

	public void displayUpdateBoatDetails() throws Exception {
		displayCompactList();
		System.out.print("Enter the memebr's ID \n>");
		int input = scan.nextInt();
		if (!reg.isMemberExist(input)) {
			throw new Exception("Invalid member ID");
		}
		if(reg.lookUpMember(input).getNumberOfBoats() == 0)
			throw new Exception("Member have no boat.");
		displaySpecificMemberBoats(reg.lookUpMember(input));
		System.out.print("\nEnter Boat # to update\n>");
		int index = scan.nextInt();
		if (index <= 0 || index > reg.lookUpMember(input).getNumberOfBoats())
			throw new Exception("Invalid Boat #");

		System.out.println("1: Update length");
		System.out.println("2: Update type");
		System.out.println("3: Update both");
		int choice = scan.nextInt();
		double length = -1;
		int type = -1;
		if (choice == 1 || choice == 3) {
			System.out.print("Length\n>");
			length = scan.nextDouble();
		}
		if (choice == 2 || choice == 3) {
			int i = 0;
			System.out.println("ID  |  Boat type\n____|______________");
			for (BoatType b : BoatType.values()) {
				System.out.println(++i + "   |  " + b.toString());
			}
			System.out.print("\nEnter Boat type ID\n>");
			type = scan.nextInt();
		} else if (choice < 1 || choice > 3)
			throw new Exception("Invalid option");
		reg.lookUpMember(input).updateBoat(length, type, index);
		displaySuccess("Boat Updated");
	}

	public void displayDeleteBoatDetails() throws Exception {
		displayCompactList();
		System.out.print("Enter the memebr's ID \n>");
		int input = scan.nextInt();
		if (!reg.isMemberExist(input)) {
			throw new Exception("Invalid member ID");
		}
		if(reg.lookUpMember(input).getNumberOfBoats() == 0)
			throw new Exception("Member have no boat.");
		displaySpecificMemberBoats(reg.lookUpMember(input));
		System.out.print("\nEnter Boat # to delete\n>");
		int index = scan.nextInt();
		if (index <= 0 || index > reg.lookUpMember(input).getNumberOfBoats())
			throw new Exception("Invalid Boat #");
		reg.lookUpMember(input).deleteBoat(index);
		displaySuccess("Boat deleted");
	}

	// this method does not print nice output. You can fix this. I am not good
	// in this.
	public void displaySpecificMemberBoats(Member m) {
		System.out.println("#    |   Boat type        |  Length"
				+ "\n_____|____________________|_______________");
		int i = 0;
		for (Boat b : m.getBoatdata())
			System.out.println(++i + "    |   " + b.getType().toString() + "         |  " + b.getLength());
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