import java.util.Random;

public class Driver
{
	public static void main(String[] args)
	{
		Random r = new Random();
		int[] randomNums = new int[100];
		
		for(int i = 0; i < randomNums.length; i++)
		{
			randomNums[i] = r.nextInt(100);
		}
		
		Sorting ogArray = new Sorting(randomNums);
		Sorting left = ogArray.leftArray();
		Sorting right = ogArray.rightArray();
		
		left.run();
		right.run();
		try 
		{
			left.join();
			right.join();
			/**
			for(int j: left.getArray())
			{
				System.out.println(j);
			}
			System.out.println();
			for(int m: right.getArray())
			{
				System.out.println(m);
			}
			System.out.println();
			**/
			int[] newArray = ogArray.puttingArraysBackTogether(left, right);
			/**
			for(int n: newArray)
			{
				System.out.println(n);
			}
			System.out.println();
			**/
			Driver.merge(newArray,	0, (left.getArray().length -1 ), (left.getArray().length) , newArray.length - 1);
			for(int k: newArray)
			{
				System.out.println(k);
			}
		}
		catch(Exception e)
		{
			System.err.println("For goodness ;) sake");
		}
		
		
		
		
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
			Driver.merge(ar, begin1, end1, begin2, end2);
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

