public class Sorting extends Thread
{
	private int[] array;
	
	public Sorting(int[] array)
	{
		this.array = array;
	}
	
	public int[] getArray()
	{
		return array;
	}
	public Sorting leftArray()
	{
		int[] leftArray = new int[array.length/2];
		for(int i = 0; i < leftArray.length; i++)
		{
			leftArray[i] = array[i];
		}
		return new Sorting(leftArray);
	}
	
	public Sorting rightArray()
	{
		int[] rightArray = new int[(array.length - array.length/2)];
		int count = array.length/2;
		for(int i = 0; i < rightArray.length; i++)
		{
			rightArray[i] = array[count];
			count++;
		}
		return new Sorting(rightArray);
	}
	
	public int[] puttingArraysBackTogether(Sorting leftArray, Sorting rightArray)
	{
		int[] finalArray = new int[(leftArray.getArray().length) + (rightArray.getArray().length)];
		for(int i = 0; i < leftArray.getArray().length; i++)
		{
			finalArray[i] = leftArray.getArray()[i];
		}
		int count = finalArray.length/2;
		for(int j = 0; j < rightArray.getArray().length; j++)
		{
			finalArray[count] = rightArray.getArray()[j];
			count++;
		}
		return finalArray;
	}
	
	public void run()
	{
		mergeSort(this.array,0,this.array.length-1);
	}
	static void mergeSort(int[] ar, int begin, int end)
	{

		if(begin != end)
		{
			int begin1 = begin;
			int end1 = begin + (end - begin) /2; 
			int begin2 = end1 + 1;
			int end2 = end;
			mergeSort(ar, begin1, end1);
			mergeSort(ar, begin2, end2);
			merge(ar, begin1, end1, begin2, end2);
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
