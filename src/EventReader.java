import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class EventReader
{
	char breakRef;
	public EventReader()
	{
		
	}
	
	public void read(ArrayList<EventData> e, char b)
	{
		try
		{
			Scanner eReader = new Scanner(new File(System.getProperty("user.dir")+"/src/events.epp"));
			breakRef = eReader.next().charAt(0);
			b = breakRef;
			eReader.nextLine();
			
			while(eReader.hasNextLine())
			{
				String l = eReader.nextLine();
				Scanner lReader = new Scanner(l);
				
				int[] time = new int[]{lReader.nextInt(),lReader.nextInt(),lReader.nextInt(),lReader.nextInt()};
				LinkedList<String> info = new LinkedList<String>();
				String i = "";
				
				while(lReader.hasNext() && info.size() < 3)
				{
					String s = lReader.next();
					if(s.charAt(0) == breakRef)
					{
						info.add(i);
						i = "";
					}
					else
						i += " "+s;
				}
				
				e.add(new EventData(time,info.get(0),info.get(1),info.get(2)));
				if(lReader.hasNext())
					e.get(e.size()-1).alarm = new Alarm(new int[]{lReader.nextInt(),lReader.nextInt(),lReader.nextInt(),lReader.nextInt()});
				lReader.close();
			}
			eReader.close();
		}
		catch(java.lang.NullPointerException f)
		{
			System.out.println("File does not exist.");
			// Create new event.epp file
		}
		catch(java.io.FileNotFoundException f)
		{
			System.out.println("File does not exist.");
			// Create new event.epp file
		}
	}
}