package model;

import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry {

	private ArrayList<Member> registry;
	private ReadWriteFile readWriteFile;
	
	public Registry() {
		registry = readWriteFile.setDataInRegistry();
		
		//readWriteFile.setDataInRegistry(this);
		
	}

	public Registry(ArrayList<Member> memberList) {
		this.registry = memberList;
	}

	public ArrayList<Member> getRegistry() {
		return registry;
	}

	public void addMember(String name, String personalnumber) {
		Member m = new Member(name, personalnumber);
		
	}
}