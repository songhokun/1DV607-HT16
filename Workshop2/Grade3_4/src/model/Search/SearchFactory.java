package model.Search;

import model.Boat.BoatType;
import model.Search.SearchMode.ComplexSearchMode;
import model.Search.SearchMode.SimpleSearchMode;

public class SearchFactory {

	public ISimpleSearchStrategy getSearch(SimpleSearchMode mode, Object keyword){
		switch(mode){
			case BY_NAME:
				return new ByName((String) keyword);
			case BY_MONTH:
				return new ByMonth((int) keyword);
			case BY_BOAT_TYPE:
				return new ByBoatType((BoatType) keyword);
			case BY_AGE_EQUAL_TO:
				return new ByAgeEqualTo((int) keyword);
			case BY_AGE_GREATER_THAN:
				return new ByAgeGreaterThan((int) keyword);
			case BY_AGE_LESS_THAN:
				return new ByAgeLessThan((int) keyword);
			case BY_BOAT_LENGTH:
				return new ByBoatLength((double) keyword);
			default:
				return null;
		}
	}
	/*
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
	*/
	
	public IComplexSearchStrategy getComplex(ComplexSearchMode mode){
		if(mode == ComplexSearchMode.AND)
			return new ByAndStrategy();
		else
			return new ByOrStrategy();
	}
	public IComplexSearchStrategy getByAndStrategy() {
		return new ByAndStrategy();
	}

	public IComplexSearchStrategy getByOrStrategy() {
		return new ByOrStrategy();
	}
}