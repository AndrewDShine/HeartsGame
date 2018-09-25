import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class HeartsRunner
	{
		static ArrayList<Card> deck = new ArrayList<Card>();
		static ArrayList<Card> pool = new ArrayList<Card>();
		static ArrayList<HumanPlayer> players = new ArrayList<HumanPlayer>();
		static Scanner userStringPut = new Scanner(System.in);
		static Scanner userIntPut = new Scanner(System.in);
		
		public static void main(String[] args)
			{
				generateDeck();
				System.out.println("Ready to play Hearts? It's a four-player game, so you'd better grab some friends!");
				makePlayers(4);
				shuffleAndDeal(4);
//				System.out.println(chooseWhoGoesFirst().getName());
				HumanPlayer currentlyUp = chooseWhoGoesFirst();
				turn(currentlyUp);
				int startPlayer = players.indexOf(currentlyUp);
				for (int i = 1; i < 4; i++)
				{
					HumanPlayer h = players.get(0);
					switch (startPlayer + 1)
					{
						case 1:
						case 2:
						case 3:
//							System.out.println("cahnged it");
							h = players.get(startPlayer + 1);
							startPlayer += 1;
							break;
						case 4:
//							System.out.println("changed too");
							h = players.get(0);
							startPlayer = 0;
							break;
							
					}
					if (h.getHand().size() > 0)
					{
						turn(h);
					}
				}
				evaluatePool();
				

				
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
			for (int i = 0; i < numOfPlayers; i++)
				{
					System.out.println("Hi, Player "+(i+1)+"! What's your name?");
					players.add(i,new HumanPlayer(userStringPut.nextLine(), 0, new ArrayList<Card>(), new ArrayList<Card>()));
					System.out.println("Nice to meet ya, "+players.get((i)).getName()+"!");
				}
		}
		public static void shuffleAndDeal(int numOfPlayers)
		{
			Collections.shuffle(deck);
			for(int j = 0; j < numOfPlayers; j++)
				{
					HumanPlayer p = players.get(j);
					for(int i = 12; i >= 0; i--)
						{
							int rand = (int)(Math.random()*i);
							Card c = deck.get(rand);
							p.addToHand(c);
							deck.remove(c);
//							System.out.println(c.getSuit() + " " + c.getRank() + " " + p.getName());
						}
				}
		}
		public static HumanPlayer chooseWhoGoesFirst()
		{
			int starter = 0;
			for(HumanPlayer h: players)
				{
					ArrayList<Card> playersHand = h.getHand();
					for(Card c: playersHand)
						{
							if(c.getSuit().equals("Clubs") && c.getRank() == 2)
								{
									starter = players.indexOf(h);
								}
						}
				}
			return players.get(starter);
		}
		public static void turn(HumanPlayer h)
			{
				System.out.println("Your turn, " + h.getName() + "! What card would you like to play? The cards in your hand are:");
				for(int i = 0; i < h.getHand().size(); i++)
					{
						System.out.println((i+1)+") The "+h.getHand().get(i).getRankString()+" of "+h.getHand().get(i).getSuit());
					}
				int cardChoice = userIntPut.nextInt();
				cardChoice -= 1;
				pool.add(h.getHand().get(cardChoice));
				System.out.println(h.getName() + " played the "+h.getHand().get(cardChoice).getRankString()+" of "+h.getHand().get(cardChoice).getSuit());
				h.removeFromHand(h.getHand().remove(cardChoice));
				System.out.println("The pool now contains: ");
				for (Card c: pool)
				{
					System.out.println("The "+c.getCardType());
				}
			}
		public static void evaluatePool()
		{
			String suitLed = pool.get(0).getSuit();
			int highestCard = 0;
			for (Card c: pool)
			{
				if (c.getSuit().equals(suitLed) && c.getRank() > highestCard)
				{
					
				}
			}
		}
		

	}
