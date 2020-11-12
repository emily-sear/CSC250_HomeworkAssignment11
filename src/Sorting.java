public class Sorting extends Thread
{
	private int[] array;
	private int begin;
	private int end;
	
	public Sorting(int[] array, int begin, int end)
	{
		this.array = array;
		this.begin = begin;
		this.end = end;
	}
	
	public int[] getArray()
	{
		return array;
	}

	public void run()
	{
		if(begin != end)
		{
			int begin1 = begin; 
			int end1 = (begin + end)/2;
			int begin2 = end1 + 1;
			int end2 = end;
			
			Sorting leftHalf = new Sorting(array, begin1, end1);
			Sorting rightHalf = new Sorting(array, begin2, end2);
			
			leftHalf.start();
			rightHalf.start();
			
			//we can't start the merge until the two threads above are done 
			try
			{
				leftHalf.join();
				rightHalf.join();
				
				//now both left and right halves are sorted
				int[] temp = new int[end - begin + 1];
				int pos1 = begin1;
				int pos2 = begin2;
				
				for(int i = 0; i < temp.length; i++)
				{
					if(pos1 <= end1 && pos2 <= end2)
					{
						if(array[pos1] < array[pos2])
						{
							temp[i] = array[pos1];
							pos1++;
							
						}
						else if(pos1 <= end1)
						{
							temp[i] = array[pos1];
							pos1++;
							
						}
						else
						{
							temp[i] = array[pos2];
							pos2++;
						}
					}
				}
				//copy the contents of temp back into array from begin to end 
				
				for(int tempPos = 0, arPos = begin; tempPos < temp.length; tempPos++, arPos++ )
				{
					array[arPos] = temp[tempPos];
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
		
	}
	

	
	
}
