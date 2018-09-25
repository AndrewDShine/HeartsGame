public class Card
	{
		private int rank;
		private String suit;
		
		public Card(String s, int r)
		{
			rank = r;
			suit = s;
		}
		
		public int getRank()
			{
				return rank;
			}
		public String getSuit()
			{
				return suit;
			}
		public String getRankString()
		{
			switch (rank)
			{
			case 1:
				return "Ace";
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				return Integer.toString(rank);
			case 11:
				return "Jack";
			case 12:
				return "Queen";
			case 13:
				return "King";
			}
			return Integer.toString(rank);
		}
		public String getCardType()
		{
			return (getRankString() + " of " + getSuit());
		}
		
		
	}
