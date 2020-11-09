public class Sorting extends Thread
{
	private int[] ar;
	private int begin;
	private int end;
	
	public Sorting(int[] ar, int begin, int end)
	{
		this.ar = ar;
		this.begin = begin;
		this.end = end;
	}
	
	public void run()
	{
		this.mergeSort(ar, begin, end);
	}
	
	public void mergeSort(int[] ar, int begin, int end)
	{
		if(begin != end)
		{
			int begin1 = begin;
			int end1 = begin + (end - begin) /2; 
			int begin2 = end1 + 1;
			int end2 = end;
			this.mergeSort(ar, begin1, end1);
			this.mergeSort(ar, begin2, end2);
			this.merge(ar, begin1, end1, begin2, end2);
		}
		
	}
	public void merge(int[] ar, int begin1, int end1, int begin2, int end2)
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
