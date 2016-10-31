package model.Search;

import model.Boat.BoatType;

public class SearchStrategy {

	public ISimpleSearchStrategy getSearchByName(String name) {
		return new ByName(name);
	}

	public ISimpleSearchStrategy getSearchByAgeEqualTo(int age) {
		return new ByAgeEqualTo(age);
	}

	public ISimpleSearchStrategy getSearchByAgeGreaterThan(int age) {
		return new ByAgeGreaterThan(age);
	}

	public ISimpleSearchStrategy getSearchByAgeLessThan(int age) {
		return new ByAgeLessThan(age);
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