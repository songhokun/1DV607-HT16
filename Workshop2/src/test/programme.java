package test;

import java.util.ArrayList;

import model.Member;
import model.Registry;

public class programme {

	public static void main(String[] args) {
		System.out.println("The boat club programme");
		
		Registry membersregistry = new Registry();
		ArrayList<Member> a = membersregistry.getRegistry();
		
		System.out.println(a.get(0));
	//	System.out.println("Our current registered members are following:");
	//	for(Member m : membersregistry.getRegistry()){
	//		System.out.println(m.getMemberID()+"\t"+m.getName()+"\t"+m.getPersonnumber());
	//	}
	}

}
