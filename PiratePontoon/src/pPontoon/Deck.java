package pPontoon;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	//array list of cards
	private ArrayList<Card> cards;
	
	//constructor
	public Deck() {	
		this.cards = new ArrayList<Card>();
		
	}
	
	//method to create full deck
	public void createFullDeck() {
		
		//generate cards loop for every suit create a card for each value 13*4 = 52 full deck
		for(Suit cardSuit : Suit.values()) {			
			for(Value cardValue : Value.values()) {				
				//adds the card
				this.cards.add(new Card(cardSuit, cardValue));
								
			}			
		}
	}
	
	//shuffle method
	public void shuffle() {
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		
		//use random
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size();
		
		//creates 52 indices
		for(int i = 0; i < originalSize; i++) {
			//generate random index [ rand.nextInt((max - min) + 1) + min; ]
			randomCardIndex = random.nextInt((this.cards.size() -1 - 0 ) + 1 ) + 0;
			//add card to temporary deck
			tempDeck.add(this.cards.get(randomCardIndex));
			//remove from original deck
			this.cards.remove(randomCardIndex);
		}
		
		this.cards = tempDeck;
		
	}
	
	public String toString() {		
		String cardListOutput = "";			
		//fill array
		for(Card aCard : this.cards) {			
			cardListOutput += "\n " + aCard.toString();			
		}
		
		return cardListOutput;
	}
	
	public void removeCard(int i) {
		this.cards.remove(i);
	}
	
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	
	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}
	
	//draw from deck
	public void draw(Deck comingFrom) {		
		this.cards.add(comingFrom.getCard(0));
		//remove card from comingFrom deck and add to new deck
		comingFrom.removeCard(0);
		}
	
	public int deckSize() {
		return this.cards.size();
	}
	
	//when deck empty
	public void moveAllToDeck(Deck moveTo) {
		int thisDeckSize = this.cards.size();
		
		//put cards into moveTo deck
		for(int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		
		for(int i = 0; i < thisDeckSize; i++) {
			this.removeCard(0);
		}
	}
	
	//return value of cards in deck
	public int cardsValue() {
		int totalValue = 0;
		int aces = 0;
		
		//assigning cards a value
		for(Card aCard : this.cards) {
			switch(aCard.getValue()) {
			case Two: totalValue += 2; break;
			case Three: totalValue += 3; break;
			case Four: totalValue += 4; break;
			case Five: totalValue += 5; break;
			case Six: totalValue += 6; break;
			case Seven: totalValue += 7; break;
			case Eight: totalValue += 8; break;
			case Nine: totalValue += 9; break;
			case Ten: totalValue += 10; break;
			case Jack: totalValue += 10; break;
			case Queen: totalValue += 10; break;
			case King: totalValue += 10; break;
			case Ace: aces += 1; break;
			}
		}
		
		//calculate correct ace value to add either 1 or 11 to hand value
		for(int i = 0; i < aces; i++) {
			
			if(totalValue > 10) {
				totalValue += 1;
			}
			
			else {
				totalValue += 11;
			}
			
		}
		
		return totalValue;
		
	}

}
