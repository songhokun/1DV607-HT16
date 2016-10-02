package view;

import model.Boat;
import model.Member;
import model.Boat.BoatType;

public interface IView {

	void startProgram();

	void displayWelcomeMessage();

	void displayMainInstructions();

	void displayCompactList();

	void displayVerboseList();
	
	void registerMember(String name, String personalnumber);

	void updateMember(Member m, String name, String personalnumber);

	void deleteMember(Member m);

	void displaySelectedMember(Member m);
	
	void registerBoat(Member m, double boatLength, BoatType boattype);

	void updateBoat(double length, BoatType type, Boat boat);

	void deleteBoat(Member m, Boat b);

	void displayError(String error);

	void displaySuccess(String success);
	

	void quitProgram();
}
