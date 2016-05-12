package inne;


public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		
		String a[] = new String[1000000];
		int zera = Integer.toBinaryString(a.length-1).length()-1;
		for(int i =1; i< a.length; i++)
		{
			String binary = Integer.toBinaryString(i);
			for(int j =binary.length(); j<= zera; j++)
			{
				binary ="0"+binary;
			}
			a[i] = binary;
			System.out.println(binary);
			
		}

	}

}
