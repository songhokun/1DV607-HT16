package BlackJack.model;

public class Card {

	public enum Color {Hearts, Spades, Diamonds, Clubs, Count, Hidden}
	public enum Value {Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace, Count, Hidden}
	private Color m_color;
	private Value m_value;
	private boolean m_isHidden;

	public Card(Color a_color, Value a_value) {
		m_value = a_value;
		m_color = a_color;
		m_isHidden = true;
	}

	public Color GetColor() {
		if (m_isHidden) {
			return Color.Hidden;
		}
		return m_color;
	}

	public Value GetValue() {
		if (m_isHidden) {
			return Value.Hidden;
		}
		return m_value;
	}
	public String getLink(){
		StringBuilder make = new StringBuilder("http://homepage.lnu.se/student/sl222xk/playingcards/");
		make.append(this.GetValue());
		make.append("_of_");
		make.append(this.GetColor());
		make.append(".png");
		
		return make.toString();
	}

	public void Show(boolean a_show) {
		m_isHidden = !a_show;
	}
}