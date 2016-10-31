package model.Search;

public class SearchMode {

	public enum SimpleSearchMode{
		BY_NAME, BY_AGE_EQUAL_TO, BY_AGE_LESS_THAN, BY_AGE_GREATER_THAN, BY_MONTH, BY_BOAT_LENGTH, BY_BOAT_TYPE
	}
	
	public enum ComplexSearchMode{
		AND, OR
	}
}
