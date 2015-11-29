import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AlarmChecker
{
	private GregorianCalendar currDate;
	private int frameWidth, frameHeight;
	
	public AlarmChecker()
	{
		currDate = new GregorianCalendar();
		frameWidth = 400;
		frameHeight = 200;
	}
	
	public void check(ArrayList<EventData> d, char b)
	{
		currDate = new GregorianCalendar();
		ArrayList<EventData> events = new ArrayList<EventData>();
		for(int e = 0; e < d.size(); e++)
		{
			if(d.get(e).alarm != null)
				events.add(d.get(e)); 
		}
		
		for(EventData e: events)
		{
			if(e.alarm.date[0] == currDate.get(Calendar.MONTH)+1 && e.alarm.date[1] == currDate.get(Calendar.DAY_OF_MONTH) && e.alarm.date[2] == currDate.get(Calendar.YEAR))
			{
				int hour = (e.alarm.time.meridiem.equals("AM")) ? e.alarm.time.hour : e.alarm.time.hour+12;
				if(hour == currDate.get(Calendar.HOUR_OF_DAY) && e.alarm.time.min == currDate.get(Calendar.MINUTE))
				{
					// CREATE JFRAME NOTIFICATION WINDOW
					final JFrame alert = new JFrame("ALARM: "+e.name);
					alert.setSize(400,200);
					alert.setLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2-frameWidth/2,java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2-frameHeight/2);
					alert.setLayout(new FlowLayout(FlowLayout.CENTER));
					alert.setBackground(Color.WHITE);
					
					// CREATE GUI
					String[] date = new String[]{e.date[0]+"",e.date[1]+""};
				 	date[0] = (e.date[0] < 10) ? "0"+date[0] : date[0];
				 	date[1] = (e.date[1] < 10) ? "0"+date[1] : date[1];
				 	
					JLabel alarmLabel = new JLabel("<html><div style=\"text-align: center;\">"+e.name+" at"+"<br/>"+date[0]+"/"+date[1]+"/"+e.date[2]+" "+e.time.toString()+"</html>",JLabel.CENTER);
					alarmLabel.setPreferredSize(new Dimension(frameWidth,130));
					alert.add(alarmLabel);
					
					JButton confirm = new JButton("OK");
					confirm.setPreferredSize(new Dimension(80, 28));
					confirm.addActionListener(
							new ActionListener()
							{
								public void actionPerformed(ActionEvent evt)
								{
									alert.setVisible(false);
								}
							}
					);
					alert.add(confirm);
					
					// DISPLAY WINDOW
				//	alert.pack();
					alert.setResizable(false);
					alert.setVisible(true);
					
					// DELETE ALARM
					for(EventData f: d)
					{
						if(e == f)
							f.alarm = null;
					}
					EventWriter eWriter = new EventWriter(b);
					eWriter.write(d);
				}
			}
		}
	}
}