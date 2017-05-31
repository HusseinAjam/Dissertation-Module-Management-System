import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SchedulingProcess {
	
	private String[][] schema ;
	private int roomNumber ;
	private int fullBlocksSize ;
	private int preparationSize;
	private int slotSize;
	private HashMap<String, Integer> SVList;
	private HashMap<String, Integer> DMList;
	private String [] staff;
 	public SchedulingProcess (int roomNumber,int slotSize , int preparationSize, int fullBlocksSize, String staff[])
	{
	
		this. roomNumber = roomNumber;
		this.fullBlocksSize = fullBlocksSize;
		this.slotSize = slotSize;	
		this.preparationSize = preparationSize;
 		this.SVList = new HashMap<String, Integer>();
 		this.DMList = new HashMap<String, Integer>();
 		this.staff = staff;
 		for(int i = 0 ; i < staff.length; i++)
 		{
 			String x = staff[i];
 			SVList.put(x, 0);
 			DMList.put(x, 0);
 		}

 		
		schema = new String [roomNumber][fullBlocksSize];
		// Initialize the array
		for(int i = 0 ; i < roomNumber ; i++)
		{
			for (int j = 0 ; j < fullBlocksSize ; j++)
			{
				schema[i][j] = "-";
			}
		}
	}
	
	public void addSV (String SV , int frequent, int rest)
	{
		int breakPoint = rest;
		for(int i = 0 ; i < roomNumber ; i++)
		{
			if(frequent == 0)
				break;
			
			for (int j = 0 ; j < fullBlocksSize ; j++)
			{
				if(schema[i][j].equals("-") && ((preparationSize + slotSize) <= (fullBlocksSize - j)))
				{
					if(schema[i][j + preparationSize + slotSize - 1].equals("-"))
					{
						boolean temp = true;
						for(int m = 0 ; m < roomNumber ; m++)
						{
							for(int n = j+preparationSize ; n < j+preparationSize+slotSize ; n++)
							{
								String temp0 = schema[m][n];
								if(SV.equals(temp0.charAt(0)+""))   
									temp = false;
							}
						}
						System.out.println(temp);
						if(temp)
						{
							if(breakPoint == 0)
							{
								breakPoint = rest;
								continue;
							}
							else
							{
							 int tempi = i;
							 int tempj = j;
							 
							for(int m = 0 ; m < roomNumber ; m++)
							{
								for(int n = j+preparationSize ; n < j+preparationSize+slotSize ; n++)
								{
									if(j < tempj)
									{
										tempi = m;
										tempj = j;
									}
								}
							}
							
							for(int m = tempj ; m < j + preparationSize + slotSize ; m++)
							{
								schema[tempi][m] = SV + "$";
							}
							frequent --;
							breakPoint--;
							int x = SVList.get(SV);
							x++;
							SVList.put(SV, x);
							break ;
							}
						}
					}
				}
			}
		}
	}
	
	public void addDM (String DM , int frequent, int rest)
	{
		int breakPoint = rest;
		for(int i = 0 ; i < roomNumber ; i++)
		{
			if(frequent == 0)
				break;
			
			for (int j = 0 ; j < fullBlocksSize ; j++)
			{
				String temp0 = schema[i][j];
				if(!schema[i][j].equals("-") && !DM.equals(temp0.charAt(0)) && ((preparationSize + slotSize) <= (fullBlocksSize - j)))
				{
					boolean checker = true;
					String temp = schema[i][j];
					for(int s = j ; s < j+preparationSize+slotSize ; s++)    // Check for full size slot and preparation
					{
						if(!schema[i][s].equals(temp))
						{
							checker = false;
							break;
						}
					}
					
					for(int m = 0 ; m < roomNumber ; m++)					// Check for time clash
					{
						for(int n = j+preparationSize ; n < j+preparationSize+slotSize ; n++)
						{
							String temp2 = schema[m][n];
							if(DM.equals(temp2.charAt(0)+""))
							{
								checker = false;
								break;
							}
						}
					}
					if(checker)
					{
						if(breakPoint == 0)
						{
							breakPoint = rest;
							continue;
						}
						else
						{
							int x = DMList.get(DM);
							if (x < frequent)
							{
								for(int m = j+preparationSize ; m < j + preparationSize + slotSize ; m++)
								{
									schema[i][m] = DM + "*";
								}
								frequent --;
								breakPoint--;
								x++;
								DMList.put(DM, x);
								break ;
							}
						}
					}	
				}
			}
		}
	}
	
	public void print ()
	{
		//Test print
		for(int i = 0 ; i < roomNumber ; i++)
		{
			for (int j = 0 ; j < fullBlocksSize ; j++)
			{
				System.out.print(schema[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public String[][] finalSchema ()
	{
		return schema;
	}
	
	public String[][] getList ()
	{
		String[][] array = new String[staff.length][3];
		
		for(int i = 0 ; i< staff.length ; i++)
		    for(int j = 0 ;j< 3 ; j++)
		    	array[i][j] = "0";
		
		Set set2 = SVList.entrySet();
		Iterator iterator2 = set2.iterator();
		int counter = 0;
	      while(iterator2.hasNext()) {
	          Map.Entry mentry2 = (Map.Entry)iterator2.next();
	          array[counter][0] = (String) mentry2.getKey();
	          array[counter][1] = mentry2.getValue()+"";
	          array[counter][2] = DMList.get(array[counter][0])+"";
	          counter ++;
	       }  
		 return array;
	}
}
