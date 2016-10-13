package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Boat.BoatType;

public class ReadWriteFile {

	//Files name for saving data
	private File memberDataFile = new File("Members.txt");
	private File boatDataFile = new File("Boats.txt");
	private int maxID;

	public ReadWriteFile() {

	}
	/**
	 * Reads file
	 * @return member list;
	 * @throws Exception
	 */
	public ArrayList<Member> readFile() throws Exception {
		ArrayList<Member> toReturn = new ArrayList<Member>();

		//Create a new file if either of one file does not exist.
		if (!memberDataFile.exists() || !boatDataFile.exists()) {
			memberDataFile.createNewFile();
			boatDataFile.createNewFile();
			return toReturn;
		}

		//if file exists
		Scanner scan = new Scanner(memberDataFile);
		if (scan.hasNext()) {
			this.maxID = (Integer.parseInt(scan.nextLine()));
		}
		/*
		 * Member data is stored in following format: memberID;name;personalnumber
		 * e.g. 2;Sarpreet Singh;201601310271
		 *
		 * Thus, fields are divided using ';'
		 */
		while (scan.hasNext()) {
			String[] temp = scan.nextLine().split(";");
			toReturn.add(new Member(temp[1], temp[2], Integer.parseInt(temp[0])));
		}
		scan.close();

		scan = new Scanner(boatDataFile);
		/*
		 * Boat data is stored in following format: length;Type;owner's member id
		 * e.g. 15.0;Kayak;1
		 */
		while (scan.hasNext()) {
			String[] temp = scan.nextLine().split(";");
			for (Member i : toReturn) {
				if (i.getMemberID() == Integer.parseInt(temp[2])) {
					i.registerBoat(Double.parseDouble(temp[0]), BoatType.valueOf(temp[1]));
					break;
				}
			}
		}
		scan.close();
		return toReturn;
	}

	public int getMaxID() {
		return maxID;
	}
	/**
		 * Writes file
		 * @param memberlist.
		 * @param maxID
		 */
		public void writeFile(ArrayList<Member> memberlist, int maxID) {
			StringBuilder members = new StringBuilder();
			StringBuilder boats = new StringBuilder();

			//First line of member files begins always with maxID
			members.append(maxID + "\n");
			//Saves a member's data in one line
			for (int i = 0; i < memberlist.size(); i++) {
				members.append(memberlist.get(i).getMemberID() + ";");
				members.append(memberlist.get(i).getName() + ";");
				members.append(memberlist.get(i).getPersonalnumber() + "\n");
				//Meanwhile creates boats information a member has for a separate file 'boats.txt'
				for (int j = 0; j < memberlist.get(i).getBoatList().size(); j++) {
					boats.append(memberlist.get(i).getBoatList().get(j).getLength() + ";");
					boats.append(memberlist.get(i).getBoatList().get(j).getType().toString() + ";");
					boats.append(memberlist.get(i).getMemberID() + "\n");
				}
			}
			try {
				//Write the file
				PrintWriter writer = new PrintWriter(memberDataFile.getAbsolutePath());
				memberDataFile.createNewFile();
				writer.print(members.toString());
				writer.close();
				writer = new PrintWriter(boatDataFile.getAbsolutePath());
				boatDataFile.createNewFile();
				writer.print(boats.toString());
				writer.close();
			} catch (IOException e) {
			}
		}
}
