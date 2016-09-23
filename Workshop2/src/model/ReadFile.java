/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Boat.BoatType;

/**
 * @author songhokun
 *
 */
public class ReadFile {
	public ReadFile(){
		
	}
	public ReadFile(Registry r) throws FileNotFoundException{
		File memberDataFile = new File("Members.txt");
		File boatDataFile = new File("Boats.txt");
		
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
	
}
