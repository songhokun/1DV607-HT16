package model;

public class Boat {

	private double length;
	private BoatType type;
	public enum BoatType {Sailboat(1), Motorsailer(2), Kayak(3), Other(4);
	private int selectCode;
	private BoatType(int in) {
		this.selectCode = in;
	}
	public int getCode() {
		return this.selectCode;
		}
	}

	
	public Boat() {

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

	public double getLength() {
		return length;
	}

	public BoatType getType() {
		return type;
	}
}