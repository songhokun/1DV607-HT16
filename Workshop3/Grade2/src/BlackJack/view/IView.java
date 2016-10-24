package BlackJack.view;

import BlackJack.controller.PlayGame.Command;

public interface IView
{
  void displayWelcomeMessage();
  Command getInput();
  void displayCard(BlackJack.model.Card a_card);
  void displayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);
  void displayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);
  void displayGameOver(boolean a_dealerIsWinner);
}