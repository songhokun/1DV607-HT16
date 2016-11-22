package model.Search;

//import java.util.ArrayList;
import model.Member;

public interface ISimpleSearchStrategy {
	
	//public ArrayList<Member> simpleSearch(ArrayList<Member> list);
	public boolean isMemberSelected(Member a_m);

}
