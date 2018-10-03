import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;

public class HeartsRunner
	{
		static ArrayList<Card> deck = new ArrayList<Card>();
		static ArrayList<Card> pool = new ArrayList<Card>();
		static ArrayList<Player> players = new ArrayList<Player>();
		static Scanner userStringPut = new Scanner(System.in);
		static Scanner userIntPut = new Scanner(System.in);
		static boolean heartsBroken = false;
		static boolean queenDanger = true;
		static int trickCounter;
		static int turnCounter;
		static String ledSuit = "Clubs";

		public static void main(String[] args)
			{
//				This block of code runs once, when the game first begins.

				System.out.println("Ready to play Hearts? It's a four-player game, so you'd better grab some friends!");
				makePlayers();
				boolean win = false;
				
				do
					{
						heartsBroken = false;
						queenDanger = true;
						trickCounter = 1;
						generateDeck();
						shuffleAndDeal(4);
						Player currentlyUp = chooseWhoGoesFirst();
						for (int j = 0; j < 13; j++)
							{
								turnCounter = 1;
								turn(currentlyUp);
								int startPlayer = players.indexOf(currentlyUp);
								for (int i = 1; i < 4; i++)
									{
										turnCounter += 1;
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
										
										turn(h);
										
										if(i == 1)
											{
												ledSuit = pool.get(0).getSuit();
											}
									}
								currentlyUp = evaluatePool();
								trickCounter += 1;
							}
						
						for(Player h: players)
							{
								if(h.getScore() >= 50)
									{
										win = true;
									}
							}
					}
				while(!win);
				chooseWinner();
				
			}
		public static void generateDeck()
		{
			for(int i = 2; i <= 14; i++)
				{
					deck.add(new Card("Hearts", i, 0, false, 999));
				}
			for(int i = 2; i <=14; i++)
				{
					deck.add(new Card("Spades", i, 0, false, 999));
				}
			for(int i = 2; i <=14; i++)
				{
					deck.add(new Card("Clubs", i, 0, true, 999));
				}
			for(int i = 2; i <=14; i++)
				{
					deck.add(new Card("Diamonds", i, 0, false, 999));
				}
		}
		public static void makePlayers()
		{
			System.out.println("How many human players will we being playing with (1-4)? I'll play any AI players.");
			int numOfPlayers = userIntPut.nextInt();
			for (int i = 0; i < numOfPlayers; i++)
				{
					System.out.println("Hi, Player "+(i+1)+"! What's your name?");
					players.add(i,new Player(userStringPut.nextLine(), 0, new ArrayList<Card>(), false));
					System.out.println("Nice to meet ya, "+players.get((i)).getName()+"!");
				}
			
			int numOfAI = 4 - numOfPlayers;
			for(int i = 0; i < numOfAI; i++)
				{
					players.add((numOfPlayers+i), new Player("Comp-"+(i+1), 0, new ArrayList<Card>(), true));
					System.out.println("Meet your new AI friend, Comp-"+(i+1)+"!");
				}
//			for(Player p: players)
//				{
//					System.out.println(p.getName());
//				}
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
							deck.remove(c);
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
				if(!h.isAI())
					{
						System.out.println("Your turn, " + h.getName() + "! What card would you like to play? The cards in your hand are:");
						for(Card ca: h.getHand())
							{
								System.out.println((h.getHand().indexOf(ca)+1)+") The "+ca.getCardType());
							}
						int cardChoice = userIntPut.nextInt();
						cardChoice -= 1;
						Card c = h.getHand().get(cardChoice);
						playCard(c, h);
						
					}
				else
					{
						System.out.println("It's "+h.getName()+"'s turn!");
						checkPlayability(h.getHand());
//						for(Card c: h.getHand())
//							{
////								if(c.isPlayable())
////									{
//										System.out.print(c.getCardType()+ " ");
////									}
//							}
//						System.out.println();
						setImportance(h);
						Collections.sort(h.getHand(), new ImportanceSorter());
						playCard(h.getHand().get(0), h);
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
			for(Player p: players)
				{
					System.out.println(p.getName() + " " + p.getScore());
				}
			return players.get(playerOfHighestCard);
			
		}
		public static void chooseWinner()
		{
			Collections.sort(players, new PlayerSorter());
			System.out.println("The game is over! The final rankings are: ");
			System.out.println("1st Place: "+players.get(0).getName());
			System.out.println("2nd Place: "+players.get(1).getName());
			System.out.println("3rd Place: "+players.get(2).getName());
			System.out.println("4th Place: "+players.get(3).getName());
		}
		public static void checkPlayability(ArrayList<Card> hand)
		{
			int numOfNeededCard = 0;
			for(Card c: hand)
				{
					if(c.getSuit().equals(ledSuit))
						{
							numOfNeededCard += 1;
						}
					c.setPlayable(false);
				}
			
			if(numOfNeededCard == 0 && trickCounter > 1)
				{
					heartsBroken = true;
				}
			
			for(Card c: hand)
				{
					if(trickCounter == 1 && pool.size() == 0)
						{
							if(c.getSuit().equals("Clubs") && c.getRank() == 2)
								{
									c.setPlayable(true);
									return;
								}
						}
					else if(pool.size() > 0)
						{
							if(numOfNeededCard == 0)
								{
									if(heartsBroken)
										{
											c.setPlayable(true);
										}
									else
										{
											if(c.getSuit().equals("Spades") || c.getSuit().equals("Diamonds") || c.getSuit().equals("Clubs"))
												{
													c.setPlayable(true);
												}
										}
								}
							else
								{
									if(c.getSuit().equals(ledSuit))
										{
											c.setPlayable(true);
										}
								}
						}
					else
						{
							if(heartsBroken)
								{
									c.setPlayable(true);
								}
							else
								{
									if(c.getSuit().equals("Spades") || c.getSuit().equals("Diamonds") || c.getSuit().equals("Clubs"))
										{
											c.setPlayable(true);
										}
								}
						}
				}
		}
		public static void setImportance(Player p)
		{
			ArrayList<Card> pH = new ArrayList<Card>(); //Playable Hand
			ArrayList<Integer> numOfSuits = new ArrayList<Integer>();
			ArrayList<Integer> sortedSuits = new ArrayList<Integer>();

			for(Card c: p.getHand())
				{
					if(c.isPlayable())
						{
							pH.add(c);
							c.setImportance(999);
						}
				}
			if(pH.size() == 0)
				{
					System.out.println("Err, no playables");
					return;
				}
			
			numOfSuits.add(0, 0);
			numOfSuits.add(1, 0);
			numOfSuits.add(2, 0);
			numOfSuits.add(3, 0);
			sortedSuits.add(0, 0);
			sortedSuits.add(1, 0);
			sortedSuits.add(2, 0);
			sortedSuits.add(3, 0);
			
			for(Card c: pH)
				{
					switch(c.getSuit())
					{
						case "Clubs":
							numOfSuits.set(0, numOfSuits.get(0)+1);
							break;
						case "Spades":
							numOfSuits.set(1, numOfSuits.get(1)+1);
							break;
						case "Diamonds":
							numOfSuits.set(2, numOfSuits.get(2)+1);
							break;
						case "Hearts":
							numOfSuits.set(3, numOfSuits.get(3)+1);
							break;
					}
				}
			String chosenSuit = null;
			for(int i = 0; i < numOfSuits.size(); i++)
				{
					sortedSuits.set(i, numOfSuits.get(i));
				}
			Collections.sort(sortedSuits);
			switch(numOfSuits.indexOf(sortedSuits.get(0)))
			{
				case 0:
					chosenSuit = "Clubs";
					break;
				case 1:
					chosenSuit = "Spades";
					break;
				case 2:
					chosenSuit = "Diamonds";
					break;
				case 3:
					chosenSuit = "Heart";
					break;
			}
			for(Card c: pH)
				{
					if(pool.size() > 0)
						{
							if(c.getSuit().equals(ledSuit))
								{
									c.setImportance(c.getRank());
								}	
							else 
								{
									if(c.getSuit().equals(chosenSuit))
										{
											c.setImportance(c.getRank());
										}
								}
						}
					else
						{
							if(c.getSuit().equals("Clubs") && c.getRank() == 2)
								{
									c.setImportance(Integer.MIN_VALUE);
								}
							else if(c.getSuit().equals(chosenSuit))
								{
									c.setImportance(c.getRank());
								}
						}
					if(queenDanger)
						{
							if(c.getSuit().equals("Spades") && c.getRank() > 12)
								{
									c.setImportance(1000);
								}
						}
				}
			
		}
		public static void playCard(Card c, Player p)
		{
			pool.add(c);
			System.out.println(p.getName() + " played the "+c.getCardType());
			c.setIndexOfLastPlayer(players.indexOf(p));
			p.removeFromHand(c);
			
			System.out.println("The pool now contains: ");
			for (Card ca: pool)
				{
					System.out.println("The "+ca.getCardType());
				}
		}
		

	}
