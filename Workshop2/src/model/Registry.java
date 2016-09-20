/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry {
	private ArrayList<Member> memberdata = new ArrayList<Member>();
	
	public Registry(){
		
	}
	public void addMember(Member in){
		memberdata.add(in);
	}
	public void registerBoat(Member member,Boat boat){

		for(Member i : memberdata){
			if(i.equals(member)){
				i.getBoatdata().add(boat);
				return ;
			}
		}
		throw new IllegalArgumentException("The provided member is not registered in our registry");
	}
	
}
