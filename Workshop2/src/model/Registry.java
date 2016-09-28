package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import model.Boat.BoatType;

/**
 * @author songhokun
 *
 */
public class Registry {

	private ArrayList<Member> registry = new ArrayList<Member>();
	private ReadWriteFile readWriteFile = new ReadWriteFile();
	private int maxID = 0;

	public Registry() throws FileNotFoundException,Exception {
		registry = readWriteFile.readFile();
		maxID = readWriteFile.getMaxID();
	}

	public ArrayList<Member> getRegistry() {
		return registry;
	}

	public int getMaxID() {
		return maxID;
	}

	public void setMaxID(int maxID) {
		this.maxID = maxID;
	}
	
	public void createMember(String name, String personalNumber) throws Exception {
		if(name==null || personalNumber == null)
			throw new Exception("Empty field!");
		this.registry.add(new Member(name, personalNumber, ++maxID));
		readWriteFile.writeFile(registry,maxID);
	}

	public void updateMember(Member inMember, String name, String personalNumber) throws Exception {
		boolean isChanged=false;
		if (!name.isEmpty()){
			inMember.setName(name);
			isChanged = true;
		}
		if (!personalNumber.isEmpty()){
			inMember.setPersonalnumber(personalNumber);
			isChanged = true;
		}
		if(isChanged)
			readWriteFile.writeFile(registry,maxID);
	}
	
	public void deleteMember(Member m) {
		this.registry.remove(m);
		readWriteFile.writeFile(registry,maxID);
	}
	
	public void registerBoat(Member m, double length, BoatType type) throws Exception {
		if(length<=0)
			throw new Exception("Incorrect length!");
		if(type==null)
			throw new Exception("Incorrect BoatType!");
		
		
		m.getBoatdata().add(new Boat(length, type));
		readWriteFile.writeFile(registry,maxID);
	}
	
	public void updateBoat(double length, BoatType type, Boat boat) {
		boolean isChanged=false;
		
		if (length != -1){
			boat.setLength(length);
			isChanged=true;
		}
		if (type != null){
			boat.setType(type);
			isChanged = true;
		}
		if(isChanged)
			readWriteFile.writeFile(registry,maxID);
	}
		
	public void deleteBoat(Member m, Boat boat) {
		m.getBoatdata().remove(boat);
		readWriteFile.writeFile(registry,maxID);
	}
	
	/**
	 * This method is used for console based application to hold a refernece to a member class.
	 * 
	 * @param ID
	 * @return Member if it exists
	 */
	public Member lookUpMember(int ID) {
		for (Member m : this.registry) {
			if (m.getMemberID() == ID)
				return m;
		}
		return null;
	}
}