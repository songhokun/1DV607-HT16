package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry {

	private ArrayList<Member> registry = new ArrayList<Member>();
	private WriteFile readWriteFile;
	private int maxID=0;
	
	public Registry() throws FileNotFoundException {
		ReadFile read = new ReadFile(this);
		
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

	public int getMaxID() {
		return maxID;
	}
	public void setMaxID(int maxID){
		this.maxID=maxID;
	}
}