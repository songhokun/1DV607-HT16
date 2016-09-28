package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import model.Boat.BoatType;

public class ReadWriteFile {

	private File memberDataFile = new File("Members.txt");
	private File boatDataFile = new File("Boats.txt");
	
	public ReadWriteFile(){
		
	}

	public void readFile(Registry r) throws FileNotFoundException,Exception{
		Scanner scan = new Scanner(memberDataFile);
		r.setMaxID(Integer.parseInt(scan.nextLine()));
		
		while(scan.hasNext()){
			String[] temp = scan.nextLine().split(";");
			r.getRegistry().add(new Member(temp[1],temp[2],Integer.parseInt(temp[0])));
		}
		scan.close();
		scan = null;
		
		scan = new Scanner(boatDataFile);
		while(scan.hasNext()){
			String[] temp = scan.nextLine().split(";");
			for(Member i : r.getRegistry()){
				if(i.getMemberID()==Integer.parseInt(temp[2])){
					i.getBoatdata().add(new Boat(Double.parseDouble(temp[0]),BoatType.valueOf(temp[1])));
					break;
				}
				
			}
		}
		scan.close();
		scan = null;	
	}

	public void writeFile(Registry r) {
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