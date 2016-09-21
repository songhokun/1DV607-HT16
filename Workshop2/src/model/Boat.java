package model;

/**
 * 
 * @author SarpreetSingh
 *
 */
public class Boat {

	private double length;
	private BoatType type;
	private Member owner;
	private int boatID;

	public enum BoatType {Sailboat, Motorsailer, Kayak, Other}

	public Boat() {

	}

	public Boat(double length, BoatType type, Member owner, int boatID) {
		this.length = length;
		this.type = type;
		this.owner = owner;
		this.boatID = boatID;
	}
	
	public Boat(double length, BoatType type) {
		this.length = length;
		this.type = type;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setType(BoatType type) {
		this.type = type;
	}

	public void setType(String type) {
		for (BoatType i : BoatType.values())
			if (i.toString().equals(type)) {
				this.type = i;
				break;
			}
	}

	public double getLength() {
		return length;
	}

	public BoatType getType() {
		return type;
	}

	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}

	public int getBoatID() {
		return boatID;
	}

	public void setBoatID(int boatID) {
		this.boatID = boatID;
	}

}