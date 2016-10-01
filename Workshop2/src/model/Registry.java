package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import model.Boat.BoatType;

/**
 * @author songhokun
 *
 */
public class Registry {

	private ArrayList<Member> registry;
	private ReadWriteFile readWriteFile;
	private int maxID = 0;

	public Registry() throws FileNotFoundException,Exception {
		readWriteFile = new ReadWriteFile();
		registry = readWriteFile.readFile();
		maxID = readWriteFile.getMaxID();
	}

	public ArrayList<Member> getRegistry() {
		return registry;
	}
	
	public void createMember(String name, String personalNumber) throws Exception {
		this.registry.add(new Member(name, personalNumber, ++maxID));
	}

	public void updateMember(Member inMember, String name, String personalNumber) throws Exception {
		if (!name.isEmpty()){
			inMember.setName(name);
		}
		if (!personalNumber.isEmpty()){
			inMember.setPersonalnumber(personalNumber);
		}
	}
	
	public void deleteMember(Member m) {
		this.registry.remove(m);
	}
	
	public void registerBoat(Member m, double length, BoatType type) throws Exception {
		if(length<=0)
			throw new Exception("Incorrect length!");
		
		m.getBoatdata().add(new Boat(length, type));
	}
	
	public void updateBoat(double length, BoatType type, Boat boat) {
		if (length != -1){
			boat.setLength(length);
		}
		if (type != null){
			boat.setType(type);
		}
	}
		
	public void deleteBoat(Member m, Boat boat) {
		m.getBoatdata().remove(boat);
	}
	
	/**
	 * This method is used for console based application to hold a reference to a member class.
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
	public void saveChanges(){
		readWriteFile.writeFile(registry,maxID);
	}
}