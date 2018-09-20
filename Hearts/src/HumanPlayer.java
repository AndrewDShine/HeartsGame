import java.util.ArrayList;

public class HumanPlayer
	{
		private static ArrayList<Card> hand = new ArrayList<Card>();
		private String name;
		
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
		
	}
