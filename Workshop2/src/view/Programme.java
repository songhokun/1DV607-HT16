package view;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Boat;
import model.Member;

public class Programme {
	private model.Registry reg = null; 
	private char quitSequence = 'q';
	
	private Scanner scan = new Scanner(System.in);
	
	public Programme() {
		try {
			 reg = new model.Registry();
		} catch (FileNotFoundException e) {
			System.err.println("There was a problem in loading a file becuase it did not exist");
		}
		
	}
	public void beginConsloe(){
		System.out.println("Boat club programme.");
		
		while(printMainOptions());
		scan.close();
	}
	private boolean printMainOptions(){
		System.out.println("Please select following options:");
		System.out.println("1 : List all members");
		System.out.printf("%c : quit\n>",quitSequence);
		
		String userInput = scan.next();
		if(userInput.equals(String.valueOf(quitSequence)))
			return false;
		else{
			if(userInput.equals("1")){
				listMembers();
			}
		}
		return true;
	}
	private void listMembers(){
		System.out.println("1 : list by compact");
		System.out.println("2 : list by verbose");
		System.out.print('>');
		int userInput = scan.nextInt();
		int i = 0;
		if(userInput==1){
			System.out.println("+ # |  name of a member  | id | # boats+");
			for(Member m : reg.getRegistry())
				System.out.printf("%4d|%20s|%4s|%8d|\n",++i,m.getName(),m.getMemberID(),m.getNumberOfBoats());
			System.out.println("+---|--------------------|----|--------+");
		}
		else if(userInput==2){
			for(Member m : reg.getRegistry()){
				System.out.printf("[%d] %s, which id is %s, and personal number is %s ", ++i,m.getName(),m.getMemberID(),m.getPersonalnumber());
				if(m.getNumberOfBoats()!=0){
					System.out.println("has following boats: ");
					for(Boat b : m.getBoatdata())
						System.out.printf("\t%.2f m length, %s\n",b.getLength(),b.getType());
				}
				else
					System.out.println("does not have any boat.");
					
			}
		}
	}
}
