package test;

import model.Member;
import model.Registry;

public class programme {

	public static void main(String[] args) {
		System.out.println("The boat club programme");
		
		Registry membersregistry = new Registry();
		
		System.out.println("Our current registered members are following:");
		for(Member m : membersregistry.getRegistry()){
		}
	}

}
