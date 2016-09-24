package view;

public interface IView {

	void startProgram();
	boolean displayMainInstructions();
	void displayCompactList();
	void displayVerboseList(); 
	void displayMemberListType() throws Exception; 
	void displayAddMemberDetails();
	void displayDeleteMemberDetails() throws Exception;
	void displayUpdateMemberDetails() throws Exception;
	//void displaySpecificMember(int index); 
	//void displaySpecificInstruction();
}
