package view;

public class SweConsole implements IView {
  private int getInputChar() {
    try {
      int c = System.in.read();
      while (c == '\r' || c =='\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }
  
  public boolean wantsToPlay() {
    return getInputChar() != 'a';
  }
  
  public void DisplayResult(boolean a_win) {
    if (a_win) {
      System.out.println("Yay du vann! ");
    } else {
      System.out.println("�sch du f�rlorade...");
    }
  }
  
  public void DisplayWelcomeMessage() {
    System.out.println("V�lkommen till t�rningsspelet");
  }
  
  public void DisplayInstructions() {
    System.out.println("Truck en tangent f�r att spela eller 'a' f�r att avsluta");
  }
  
  public void DisplayDice(int a_faceValue) {
    System.out.println("" + a_faceValue);
  }
}