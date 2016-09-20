/**
 * 
 */
package model;

/**
 * @author songhokun
 *
 */
public class Member {
	private String name, personnumber;
	
	private int memberID;
	
	public Member(){
		memberID=this.hashCode();
	}
	
	public Member(String name, String personnumber){
		this.name=name;
		memberID=this.hashCode();
		this.personnumber=personnumber;
	}
	
	//Begin getters and setters
	public String getName() {
		return name;
	}
	public String getPersonnumber() {
		return personnumber;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
}
