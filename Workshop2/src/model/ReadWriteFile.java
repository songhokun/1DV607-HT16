package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Boat.BoatType;

public class ReadWriteFile {

	private File memberDataFile = new File("Members.txt");
	private File boatDataFile = new File("Boats.txt");
	private File authenticationFile = new File("Users.txt");
	
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

	public ArrayList<User> readFile() throws FileNotFoundException{
		Scanner scan = new Scanner(authenticationFile);
		ArrayList<User> toReturn = new ArrayList<User>();
		
		while(scan.hasNext()){
			String[] temp = scan.nextLine().split(";");
			toReturn.add(new User(temp[0],temp[1]));
		}
		scan.close();
		return toReturn;
	}
	
	public void writeFile(ArrayList<User> users) throws FileNotFoundException{
		StringBuilder sb = new StringBuilder();
		for(User  u: users){
			sb.append(u.getUsername() + ";" + u.getPassword() + "\n");
		}
		try {
			PrintWriter writer = new PrintWriter(authenticationFile.getAbsolutePath());
			authenticationFile.createNewFile();
			writer.print(sb.toString());
			writer.close();
		} catch (IOException e) {
		}	
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