package model;

import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry {

	private ArrayList<Member> registry;

	public Registry() {
		registry = new ArrayList<Member>();
	}

	public Registry(ArrayList<Member> memberList) {
		this.registry = memberList;
	}

	public ArrayList<Member> getRegistry() {
		return registry;
	}

	public void setRegistry(ArrayList<Member> registry) {
		this.registry = registry;
	}
}