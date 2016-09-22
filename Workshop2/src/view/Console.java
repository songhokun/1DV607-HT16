package view;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Boat;
import model.Member;

public class Console implements IView {

	private model.Registry reg = null;
	private char quitSequence = 'q';
	private Scanner scan = new Scanner(System.in);

	public Console() {
		try {
			reg = new model.Registry();
		} catch (FileNotFoundException e) {
			System.err.println("There was a problem in loading a file becuase it did not exist");
		}

	}

	@Override
	public void startProgram() {
		System.out.println("Boat club programme.");
		while (displayMainInstructions());
		scan.close();
	}

	@Override
	public boolean displayMainInstructions() {
		System.out.println("Please select following options:");
		System.out.println("1 : List all members");
		System.out.println("2 : Create a member");
		System.out.printf("%c : quit\n>", quitSequence);

		String userInput = scan.next();
		if (userInput.equals(String.valueOf(quitSequence)))
			return false;
		else {
			if (userInput.equals("1")) {
				displayMemberListType();
			} else if (userInput.equals("2")) {
				System.out.print("Name: ");
				String name = scan.next();
				System.out.print("\nPersonal Number: ");
				String personalNumber = scan.next();
				reg.createMember(name, personalNumber);
				System.out.println("Member Created!!");
			}
		}
		return true;
	}

	@Override
	public void displayMemberListType() {
		System.out.println("1 : list by compact");
		System.out.println("2 : list by verbose");
		System.out.print('>');
		int userInput = scan.nextInt();
		if (userInput == 1) {
			displayCompactList();
		} else if (userInput == 2) {
			displayVerboList();
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
	public void displayVerboList() {
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

}
