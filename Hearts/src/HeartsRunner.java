import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class HeartsRunner
	{
		static ArrayList<Card> deck = new ArrayList<Card>();
		static ArrayList<Card> pool = new ArrayList<Card>();
		static ArrayList<Player> players = new ArrayList<Player>();
		static Scanner userStringPut = new Scanner(System.in);
		static Scanner userIntPut = new Scanner(System.in);
		
		public static void main(String[] args)
			{
//				This block of code runs once, when the game first begins.
				generateDeck();
				System.out.println("Ready to play Hearts? It's a four-player game, so you'd better grab some friends!");
				makePlayers(4);
				boolean win = false;
				do
					{
						shuffleAndDeal(4);
						Player currentlyUp = chooseWhoGoesFirst();
						for (int j = 0; j < 13; j++)
							{
								turn(currentlyUp);
								int startPlayer = players.indexOf(currentlyUp);
								for (int i = 1; i < 4; i++)
									{
										Player h = players.get(0);
										switch (startPlayer + 1)
										{
										case 1:
										case 2:
										case 3:
											h = players.get(startPlayer + 1);
											startPlayer += 1;
											break;
										case 4:
											h = players.get(0);
											startPlayer = 0;
											break;
									
										}
										if (h.getHand().size() > 0)
											{
												turn(h);
											}
									}
								currentlyUp = evaluatePool();
							}
						
						for(Player h: players)
							{
								if(h.getScore() == 50)
									{
										win = true;
									}
							}
					}
				while(!win);
				
			}
		public static void generateDeck()
		{
			for(int i = 2; i <= 14; i++)
				{
					deck.add(new Card("Hearts", i, 0));
				}
			for(int i = 2; i <=14; i++)
				{
					deck.add(new Card("Spades", i, 0));
				}
			for(int i = 2; i <=14; i++)
				{
					deck.add(new Card("Clubs", i, 0));
				}
			for(int i = 2; i <=14; i++)
				{
					deck.add(new Card("Diamonds", i, 0));
				}
		}
		public static void makePlayers(int numOfPlayers)
		{
			for (int i = 0; i < numOfPlayers; i++)
				{
					System.out.println("Hi, Player "+(i+1)+"! What's your name?");
					players.add(i,new Player(userStringPut.nextLine(), 0, new ArrayList<Card>()));
					System.out.println("Nice to meet ya, "+players.get((i)).getName()+"!");
				}
		}
		public static void shuffleAndDeal(int numOfPlayers)
		{
			Collections.shuffle(deck);
			for(int j = 0; j < numOfPlayers; j++)
				{
					Player p = players.get(j);
					for(int i = 12; i >= 0; i--)
						{
							int rand = (int)(Math.random()*i);
							Card c = deck.get(rand);
							p.addToHand(c);
//							System.out.println(c.getSuit() + " " + c.getRank() + " " + p.getName());
						}
				}
		}
		public static Player chooseWhoGoesFirst()
		{
			int starter = 0;
			for(Player h: players)
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
		public static void turn(Player h)
			{
				System.out.println("Your turn, " + h.getName() + "! What card would you like to play? The cards in your hand are:");
				for(int i = 0; i < h.getHand().size(); i++)
					{
						System.out.println((i+1)+") The "+h.getHand().get(i).getRankString()+" of "+h.getHand().get(i).getSuit());
					}
				int cardChoice = userIntPut.nextInt();
				cardChoice -= 1;
				Card c = h.getHand().get(cardChoice);
				
				pool.add(c);
				System.out.println(h.getName() + " played the "+c.getCardType());
				c.setIndexOfLastPlayer(players.indexOf(h));
				h.removeFromHand(h.getHand().remove(cardChoice));
				System.out.println("The pool now contains: ");
				for (Card ca: pool)
				{
					System.out.println("The "+ca.getCardType()/*+ ", played by " +ca.getIndexOfLastPlayer()*/);
				}
			}
		public static Player evaluatePool()
		{
			String suitLed = pool.get(0).getSuit();
			int playerOfHighestCard = 0;
			int points = 0;
			
			for (Card c: pool)
				{
					if (c.getSuit().equals(suitLed) && c.getRank() > playerOfHighestCard)
						{
							playerOfHighestCard = c.getIndexOfLastPlayer();
						}
				}
			for (Card c: pool)
				{
					if (c.getSuit().equals("Hearts"))
						{
							points += 1;
						}
					else if (c.getSuit().equals("Spades") && c.getRank() == 12)
						{
							points += 13;
						}
				}
			System.out.println(players.get(playerOfHighestCard).getName() + " takes the pool, and with it " + points + " points!");
			players.get(playerOfHighestCard).addToScore(points);
			pool.clear();
			return players.get(playerOfHighestCard);
			
		}
		public static void chooseWinner()
		{
			Collections.sort(players, new PlayerSorter());
			System.out.println("The game is over! The final rankings are: ");
			System.out.println("1st Place: "+players.get(0));
			System.out.println("2nd Place: "+players.get(1));
			System.out.println("3rd Place: "+players.get(2));
			System.out.println("4th Place: "+players.get(3));
		}
		

	}
