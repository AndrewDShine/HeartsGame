import java.util.ArrayList;

public class Player
	{
		private String name;
		private int score;
		private ArrayList<Card> hand;
		private boolean isAI;
		
		public Player(String n, int s, ArrayList<Card> h, boolean ai)
		{
			name = n;
			score = s;
			hand = h;
			isAI = ai;
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
		public void addToScore(int s)
		{
			score += s;
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
		public boolean isAI()
		{
			return isAI;
		}
		public void setAI(boolean isAI)
		{
			this.isAI = isAI;
		}
		
		
	}
