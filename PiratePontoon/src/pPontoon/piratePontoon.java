package pPontoon;

import java.util.Scanner;

public class piratePontoon {

	public static void main(String[] args) {
		
		//welcome
		System.out.println("Welcome to Pirate Pontoon!");
		
		//create deck
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		//shuffle deck
		playingDeck.shuffle();
		
		//create player deck
		Deck playerDeck = new Deck();
		
		//create dealer deck
		Deck dealerDeck = new Deck();
		
		//player money
		double playerMoney = 100.00;
		
		//stores user input
		Scanner userInput = new Scanner(System.in);
		
		//game loop
		while(playerMoney > 0) {
			//keep playing
			System.out.println("\n\nYou have £" + playerMoney + ", how much would you like to bet?");
			double playerBet = userInput.nextDouble();
			
			if(playerBet > playerMoney) {
				System.out.println("You don't have enough for that.");
				continue;
			}
			
			boolean endRound = false;
			
			//start dealing cards
			//player 2 cards
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			//dealer 2 cards
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			//show player hand value
			while(true) {
				System.out.println("Your hand:");
				System.out.print(playerDeck.toString());
				System.out.println("\n\nYour hand is valued at: " + playerDeck.cardsValue());
				
				//display dealer hand ONLY FIRST CARD
				System.out.println("\nDealer Hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");
				
				//player decision
				Scanner hitOrStand = new Scanner(System.in);
				System.out.println("\nWould you like to Hit(H) or Stand(S)?");
				String response = hitOrStand.next();
				
				//player hits
				if(response.matches("h")){
					playerDeck.draw(playingDeck);
					System.out.println("You draw a " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//bust if over 21
					if(playerDeck.cardsValue() > 21) {
						System.out.println("You Bust. \nYour hand value is: " + playerDeck.cardsValue() + "\n");
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}				
				//player stands
				if (response.matches("s")) {
					break;
				}
			}
			//reveal dealer card
			System.out.println("Dealer Cards:\n " + dealerDeck.toString());
			//compare hand values
			if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
				System.out.println("Dealer wins!");
				playerMoney -= playerBet;
				endRound = true;
			}
			//dealer draws until 17 or higher
			while((dealerDeck.cardsValue() < 17) && endRound == false){
				dealerDeck.draw(playingDeck);
				System.out.println("\nDealer draws " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			//display dealer value
			System.out.println("\nDealer's hand value is " + dealerDeck.cardsValue());
			//did they bust?
			if((dealerDeck.cardsValue() > 21) && endRound == false) {
				System.out.println("Dealer busts! You win!");
				playerMoney += playerBet;
				endRound = true;
			}
			//is it a push?
			if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("\nPush.");
				endRound = true;
			}
			//player winning condition
			if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("\nYou win!");
				playerMoney += playerBet;
				endRound = true;
			}
			//dealer wins
			else if(endRound == false) {
				System.out.println("\nYou lost this hand.");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			//put used cards back into deck
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck);
			System.out.println("\nEnd of hand.");
			
		}
		
		System.out.println("Game over! You have no more money.");

	}

}
