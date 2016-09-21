package test;

import java.util.ArrayList;
import model.Boat.BoatType;

import model.Boat;
import model.Member;
import model.Registry;

public class programme {

	public static void main(String[] args) {
		System.out.println("The boat club programme");
		
		Registry membersregistry = new Registry();
		
	
		System.out.println("Our current registered members are following:");
		for(Member m : membersregistry.getRegistry()){
			System.out.println(m.getMemberID()+"\t"+m.getName()+"\t"+m.getPersonalnumber());
			for(Boat b: m.getBoatdata())
				System.out.println("\t"+b.getBoatID()+"\t"+b.getType().toString()+"\t"+b.getLength());
		}
	}

}
