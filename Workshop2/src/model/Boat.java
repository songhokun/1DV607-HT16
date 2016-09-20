package model;

/**
 * 
 * @author SarpreetSingh
 *
 */
public class Boat {

	private double length;
	private Type type;

	public enum Type {
		Sailboat, Motorsailer, Kayak, Other
	};

	public Boat() {

	}

	public Boat(double length, Type type) {
		this.length = length;
		this.type = type;

	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public double getLength() {
		return length;
	}

	public Type getType() {
		return type;
	}
}