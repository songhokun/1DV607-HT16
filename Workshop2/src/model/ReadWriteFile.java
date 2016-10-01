package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Boat.BoatType;

public class ReadWriteFile {

	private File memberDataFile = new File("Members.txt");
	private File boatDataFile = new File("Boats.txt");
	private int maxID;

	public ReadWriteFile() {

	}

	public ArrayList<Member> readFile() throws Exception {
		ArrayList<Member> toReturn = new ArrayList<Member>();
		if (!memberDataFile.exists() && !boatDataFile.exists()) {
			memberDataFile.createNewFile();
			boatDataFile.createNewFile();
			return toReturn;
		}
		Scanner scan = new Scanner(memberDataFile);
		if (scan.hasNext()) {
			this.maxID = (Integer.parseInt(scan.nextLine()));
		}

		while (scan.hasNext()) {
			String[] temp = scan.nextLine().split(";");
			toReturn.add(new Member(temp[1], temp[2], Integer.parseInt(temp[0])));
		}
		scan.close();
		scan = null;

		scan = new Scanner(boatDataFile);
		while (scan.hasNext()) {
			String[] temp = scan.nextLine().split(";");
			for (Member i : toReturn) {
				if (i.getMemberID() == Integer.parseInt(temp[2])) {
					i.getBoatList().add(new Boat(Double.parseDouble(temp[0]), BoatType.valueOf(temp[1])));
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

	public void writeFile(ArrayList<Member> registry, int maxID) {
		StringBuilder members = new StringBuilder();
		StringBuilder boats = new StringBuilder();
		members.append(maxID + "\n");

		for (int i = 0; i < registry.size(); i++) {
			members.append(registry.get(i).getMemberID() + ";");
			members.append(registry.get(i).getName() + ";");
			members.append(registry.get(i).getPersonalnumber() + "\n");

			for (int j = 0; j < registry.get(i).getBoatList().size(); j++) {
				boats.append(registry.get(i).getBoatList().get(j).getLength() + ";");
				boats.append(registry.get(i).getBoatList().get(j).getType().toString() + ";");
				boats.append(registry.get(i).getMemberID() + "\n");
			}
		}
		try {
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