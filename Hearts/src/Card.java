public class Card
	{
		private int rank;
		private String suit;
		private int indexOfLastPlayer;
		private boolean playable;
		
		public Card(String s, int r, int i, boolean p)
		{
			rank = r;
			suit = s;
			indexOfLastPlayer = i;
			playable = p;
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
			case 14:
				return "Ace";
			}
			return Integer.toString(rank);
		}
		public String getCardType()
		{
			return (getRankString() + " of " + getSuit());
		}
		public int getIndexOfLastPlayer()
			{
				return indexOfLastPlayer;
			}
		public void setIndexOfLastPlayer(int indexOfLastPlayer)
			{
				this.indexOfLastPlayer = indexOfLastPlayer;
			}
		public boolean isPlayable()
			{
				return playable;
			}
		public void setPlayable(boolean playable)
			{
				this.playable = playable;
			}
		
		
		
		
	}
