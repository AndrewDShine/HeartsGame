import java.util.ArrayList;

public class HumanPlayer
	{
		private String name;
		private int score;
		private ArrayList<Card> hand;
		private ArrayList<Card> discardPile;
		
		public HumanPlayer(String n, int s, ArrayList<Card> h, ArrayList<Card> d)
		{
			name = n;
			score = s;
			hand = h;
			discardPile = d;
		}

		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public int getScore()
		{
			return score;
		}
		public void setScore(int score)
		{
			this.score = score;
		}
		public ArrayList<Card> getHand()
		{
			return hand;
		}
		public void setHand(ArrayList<Card> hand)
		{
			this.hand = hand;
		}
		public void addToHand(Card c)
		{
			hand.add(c);
		}
		public void removeFromHand(Card c)
		{
			hand.remove(c);
		}
		public ArrayList<Card> getDiscardPile() {
			return discardPile;
		}
		public void setDiscardPile(ArrayList<Card> discardPile) {
			this.discardPile = discardPile;
		}
		
		
		
	}
