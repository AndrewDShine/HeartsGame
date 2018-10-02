import java.util.Comparator;

public class ImportanceSorter implements Comparator<Card>
	{
		public int compare (Card c1, Card c2)
		{
			if(c1.getImportance() > c2.getImportance())
				{
					return 1;
				}
			else
				{
					return -1;
				}
		}
	}
