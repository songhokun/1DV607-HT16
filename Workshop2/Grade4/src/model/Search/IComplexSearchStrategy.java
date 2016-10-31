package model.Search;

import java.util.ArrayList;
import model.Member;

public interface IComplexSearchStrategy {

	public ArrayList<Member> complexSearch(ArrayList<Member> firstList, ArrayList<Member> secondList);
}
