import java.util.ArrayList;

public class HumanPlayer
	{
		private static ArrayList<Card> hand = new ArrayList<Card>();
		private static String name;
		
		public HumanPlayer(String n)
		{
			name = n;

		}

		public static ArrayList<Card> getHand()
			{
				return hand;
			}
		public static Card getCard(int i)
		{
			return hand.get(i);
		}
		public String getName()
			{
				return name;
			}
		public static void addToHand(Card c)
		{
			hand.add(c);
		}
		public static int sizeOfHand()
		{
			return hand.size();
		}
		public static Card turn()
		{
			System.out.println("Your turn, " + name + "! What card would you like to play? The cards in your hand are:");
			for(int i = 0; i < hand.size(); i++)
				{
					System.out.println((i+1)+") The "+hand.get(i).getRank()+" of "+hand.get(i).getSuit());
				}
			return hand.get(0);
		}
		
	}
