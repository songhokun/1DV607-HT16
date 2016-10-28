package model.Search;

import model.Boat.BoatType;
import model.Search.SearchMode.SimpleSearchMode;

public class SearchFactory {

	public ISimpleSearchStrategy getSearchByName(String name) {
		return new ByName(name);
	}

	public ISimpleSearchStrategy getSearchByAge(int age, SimpleSearchMode mode) {
		return new ByAge(age, mode);
	}
	
	public ISimpleSearchStrategy getSearchByBoatLength(double length) {
		return new ByBoatLength(length);
	}

	public ISimpleSearchStrategy getSearchByBoatType(BoatType type) {
		return new ByBoatType(type);
	}

	public ISimpleSearchStrategy getSearchByMonth(int month) {
		return new ByMonth(month);
	}

	public IComplexSearchStrategy getByAndStrategy() {
		return new ByAndStrategy();
	}

	public IComplexSearchStrategy getByOrStrategy() {
		return new ByOrStrategy();
	}
}