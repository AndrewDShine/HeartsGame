import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class HeartsRunner
	{
		static ArrayList<Card> deck = new ArrayList<Card>();
		static ArrayList<Card> pool = new ArrayList<Card>();
		static ArrayList<Card> discardPile = new ArrayList<Card>();
		static ArrayList<HumanPlayer> players = new ArrayList<HumanPlayer>();
		static Scanner userStringPut = new Scanner(System.in);
		
		public static void main(String[] args)
			{
				generateDeck();
				System.out.println("Ready to play Hearts? It's a four-player game, so you'd better grab some friends!");
				makePlayers(4);
				shuffleAndDeal(4);
			}
		public static void generateDeck()
		{
			for(int i = 1; i <= 13; i++)
				{
					deck.add(new Card("Hearts", i));
				}
			for(int i = 1; i <=13; i++)
				{
					deck.add(new Card("Spades", i));
				}
			for(int i = 1; i <=13; i++)
				{
					deck.add(new Card("Clubs", i));
				}
			for(int i = 1; i <=13; i++)
				{
					deck.add(new Card("Diamonds", i));
				}
		}
		public static void makePlayers(int numOfPlayers)
		{
			for (int i = 1; i <= numOfPlayers; i++)
				{
					System.out.println("Hi, Player "+i+"! What's your name?");
					players.add(new HumanPlayer(userStringPut.nextLine()));
					System.out.println("Nice to meet ya, "+players.get((i-1)).getName()+"!");
					System.out.print("\n");
				}
		}
		public static void shuffleAndDeal(int numOfPlayers)
		{
			Collections.shuffle(deck);
			for(int j = 0; j < numOfPlayers; j++)
				{
					for(int i = 12; i >= 0; i--)
						{
							int rand = (int)(Math.random()*i);
							HumanPlayer p = players.get(j);
							Card c = deck.get(rand);
							p.addToHand(c);
							deck.remove(c);
							System.out.println(c.getSuit() + " " + c.getRank() + " " + p.getName());
						}
				}
		}

	}
