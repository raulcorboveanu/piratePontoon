package pPontoon;

public class Card {
	
	//every card needs a suit and a value
	private Suit suit;
	private Value value;
	
	//card constructor
	public Card(Suit suit, Value value) {	
		this.value = value;
		this.suit = suit;
	}
	
	//outputs card to user
	public String toString() {	
		return this.value.toString() + " of " + this.suit.toString();
		
	}
	
	//getValue method to check values and determine total hand value
	public Value getValue() {	
		return this.value;
	}

}
