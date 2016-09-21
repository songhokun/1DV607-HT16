

import java.io.IOException;

import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;

public class Main {

	public static void main(String[] args) {
		//view.Programme p = new view.Programme();
		Registry r = new Registry();
		Member m1 = new Member("songo", "67718257");
		Member m2 = new Member("singh", "34348257");
		
		Boat b1 = new Boat(12.22, BoatType.Motorsailer);
		Boat b2 = new Boat(13.22, BoatType.Sailboat);
	
		
		m1.getBoatdata().add(b1);
		m2.getBoatdata().add(b2);
		r.getRegistry().add(m1);
		r.getRegistry().add(m2);
		
		try {
			r.updateFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

}
