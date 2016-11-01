package model.Search;

import model.Boat.BoatType;

public class SearchStrategy {
	public enum SimpleSearchMode{
		BY_NAME, BY_AGE_EQUAL_TO, BY_AGE_LESS_THAN, BY_AGE_GREATER_THAN, BY_MONTH, BY_BOAT_LENGTH, BY_BOAT_TYPE
	}
	
	public enum ComplexSearchMode{
		AND, OR
	}
	
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