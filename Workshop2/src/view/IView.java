package view;

public interface IView {

	void startProgram();
	void quitProgram();
	void displayWelcomeMessage();
	void displayMainInstructions();
	void displayMemberInstructions();
	void displayBoatInstructions();
	void showMessageToUser(String m);
	void displayCompactList();
	void displayVerboList();
}
