/**
 * 
 * @author TeamSharks :)
 *
 */

public class Main
{
	public static void main(String[] args)
	{
		final Model model = new Model();
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				CalendarFrame calendar = new CalendarFrame(model);
			}
		});
		
		AlarmChecker aCheck = new AlarmChecker();
		while(true)
			aCheck.check(model.eventData,model.dataBreakRef);
	}
}
