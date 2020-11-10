import java.util.Random;

public class Driver
{
	public static void main(String[] args)
	{
		Random r = new Random();
		int[] randomNums = new int[10];
		
		for(int i = 0; i < randomNums.length; i++)
		{
			randomNums[i] = r.nextInt(100);
		}

		Sorting sort = new Sorting(randomNums, 0, randomNums.length -1);
		sort.parallelMerge(randomNums, 0, randomNums.length -1, 2);
		
		for(int k:randomNums)
		{
			System.out.println(k);
		}
		
	}

	static void mergeSort(int[] ar, int begin, int end)
	{
		Sorting sort1;
		Sorting sort2;
		if(begin != end)
		{
			
			int begin1 = begin;
			int end1 = begin + (end - begin) /2; 
			int begin2 = end1 + 1;
			int end2 = end;
			sort1 = new Sorting(ar, begin1, end1);
			sort2 = new Sorting(ar, begin2, end2);
			sort1.start();
			sort2.start();
			try {
			sort1.join();
			sort2.join();
			Driver.merge(ar, begin1, end1, begin2, end2);
			}
			catch(Exception e)
			{
				System.err.println("Bruh you messed up");
			}
			
		}
	}

	
	static void merge(int[] ar, int begin1, int end1, int begin2, int end2)
	{
		//assuming that everything from begin1 to begin 2 are already sorted and the same from end1 and end2
		//Can't just take the length of the array that is passed in, because you could just be looking at a certain section 
		
		int[] temp = new int[(end2 - begin1) + 1];
		int pos1 = begin1;
		int pos2 = begin2;
		
		//fills up temp with a sorted list 
		for(int i = 0; i < temp.length; i++)
		{
			if(pos1 <= end1 && pos2 <= end2)
			{
				if(ar[pos1] < ar[pos2])
				{
					temp[i] = ar[pos1];
					pos1++;
				}
				else
				{
					temp[i] = ar[pos2];
					pos2++;
				}
			}
			else
			{
				//either pos1 or pos2 is off the end of their list and the other guy is the default winner
				if(pos1 > end1)
				{
					temp[i] = ar[pos2];
					pos2++;
				}
				else
				{
					temp[i] = ar[pos1];
					pos1++;
				}
			}
		}
		
		//copy temp back over ar from begin1 to end2
		int tempCounter = 0;
		for(int j = begin1; j <= end2; j++)
		{
			//arrays are passed by address so these are permanant changes
			ar[j] = temp[tempCounter];
			tempCounter++;
		}
	
	}
}

