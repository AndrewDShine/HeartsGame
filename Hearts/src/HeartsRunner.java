import java.util.ArrayList;
import java.util.Scanner;

public class HeartsRunner
	{
		static ArrayList<Card> deck = new ArrayList<Card>();
		
		public static void main(String[] args)
			{
				
			}
		public static void generateDeck()
		{
			for(int i = 1; i <= 13; i++)
				{
					deck.add(new Card("Hearts", i));
				}
		}

	}
