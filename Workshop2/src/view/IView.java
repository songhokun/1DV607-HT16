package view;

import java.util.ArrayList;
import model.Boat;
import model.Boat.BoatType;
import model.Member;

public interface IView {

	void startProgram();

	void displayWelcomeMessage();

	void displayMainInstructions();

	void displayCompactList(ArrayList<Member> m);

	void displayVerboseList(ArrayList<Member> m);
	
	void registerMember(String name, String personalnumber);

	void updateMember(Member m, String name, String personalnumber);

	void deleteMember(Member m);

	void displaySelectedMember(Member m);
	
	void registerBoat(Member m, double boatLength, BoatType boattype);

	void updateBoat(double length, BoatType type, Boat boat);

	void deleteBoat(Member m, Boat b);

	void displayError(String error);

	void displaySuccess(String success);
	
	void logIn(String username, String password);
	
	void simpleSearch(Object o);
	
	void quitProgram();
}
