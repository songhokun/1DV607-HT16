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
		readWriteFile.readFile(this);
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
		readWriteFile.writeFile(this);
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
			readWriteFile.writeFile(this);
	}
	
	public void deleteMember(Member m) {
		this.registry.remove(m);
		readWriteFile.writeFile(this);
	}
	
	public void registerBoat(Member m, double length, BoatType type) throws Exception {
		if(m==null || length==0 || type==null)
			throw new Exception("Empty field!"); 
		
		m.getBoatdata().add(new Boat(length, type));
		readWriteFile.writeFile(this);
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
			readWriteFile.writeFile(this);
	}
		
	public void deleteBoat(Member m, Boat boat) {
		m.getBoatdata().remove(boat);
		readWriteFile.writeFile(this);
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