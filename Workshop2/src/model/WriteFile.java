package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {

	public WriteFile() {

	}

	public WriteFile(Registry r) {
		File memberDataFile = new File("Members.txt");
		File boatDataFile = new File("Boats.txt");
		StringBuilder members = new StringBuilder();
		StringBuilder boats = new StringBuilder();

		members.append(r.getMaxID() + "\n");
		for (int i = 0; i < r.getRegistry().size(); i++) {
			members.append(r.getRegistry().get(i).getMemberID() + ";");
			members.append(r.getRegistry().get(i).getName() + ";");
			members.append(r.getRegistry().get(i).getPersonalnumber() + "\n");

			for (int j = 0; j < r.getRegistry().get(i).getBoatdata().size(); j++) {
				boats.append(r.getRegistry().get(i).getBoatdata().get(j).getLength() + ";");
				boats.append(r.getRegistry().get(i).getBoatdata().get(j).getType().toString() + ";");
				boats.append(r.getRegistry().get(i).getMemberID() + "\n");
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