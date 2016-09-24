package view;

import model.Member;

public interface IView {

	void startProgram();

	boolean displayMainInstructions();

	void displayCompactList();

	void displayVerboseList();

	void displayMemberListType() throws Exception;

	void displayAddMemberDetails();

	void displayDeleteMemberDetails() throws Exception;

	void displayUpdateMemberDetails() throws Exception;

	void displaySpecificMemberBoats(Member m);

	void displayAddBoatDetails() throws Exception;

	void displayUpdateBoatDetails() throws Exception;

	void displayDeleteBoatDetails() throws Exception;

	// void displaySpecificMember(int index);
	// void displaySpecificInstruction();
}
