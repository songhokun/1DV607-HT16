package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteFile {

	private File memberDataFile = new File("Members.txt");
	private File boatDataFile = new File("Boats.txt");
	//private
	
	public WriteFile() {

	}
	
	
	public ArrayList<Member> setDataInRegistry(){
		
		return null;
	}
	
	
	public void updateBoatFile(Registry r) throws IOException{
		StringBuilder make = new StringBuilder();
		for(Member i : r.getRegistry()){	
			for(Boat j : i.getBoatdata()){
				make.append(j.getLength() + ";" + j.getType().toString() + ";" + j.getOwner().getMemberID() + "\n");
			}
		}
		boatDataFile.createNewFile();
		PrintWriter printerWriter = new PrintWriter(boatDataFile);
		printerWriter.print(make.toString());
		printerWriter.close();
	}

	public void updateMemberFile(Registry r){
		//PrintWriter writer = new PrintWriter();
	//	newFile.createNewFile();
	//	PrintWriter printerWriter = new PrintWriter(membersDataFile);
	//	printerWriter.print(words.toString());
	//	printerWriter.close();
	}

}
