import java.util.ArrayList;
import java.util.Scanner;

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
			
			for(int i = 0; i < numOfPlayers; i++)
				{
					for(int j = 0; i < 13; j++)
						{
							players.get(i).addToHand(deck.get(j));
							System.out.println(deck.get(j).getRank());
							System.out.println("iteration");
						}
				}
		}

	}
