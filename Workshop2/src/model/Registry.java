package model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry {

	private ArrayList<Member> registry;
	private WriteFile readWriteFile;
	
	public Registry() {
		//registry = readWriteFile.setDataInRegistry();
		
		//readWriteFile.setDataInRegistry(this);
		
	}

	public Registry(ArrayList<Member> memberList) {
		this.registry = memberList;
	}

	public ArrayList<Member> getRegistry() {
		return registry;
	}

	public void updateFile() throws IOException{
		readWriteFile.updateBoatFile(this);
	}
}