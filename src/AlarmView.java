/**
 * @author TeamSharks
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlarmView// extends MonthlyView
{
	final static Integer[] HOURS = new Integer[]{12,1,2,3,4,5,6,7,8,9,10,11};
	final static String[] MINUTES = new String[]{
		"00","01","02","03","04","05","06","07","08","09",
		"10","11","12","13","14","15","16","17","18","19",
		"20","21","22","23","24","25","26","27","28","29",
		"30","31","32","33","34","35","36","37","38","39",
		"40","41","42","43","44","45","46","47","48","49",
		"50","51","52","53","54","55","56","57","58","59"
		};
	final static String[] MERIDIEM = new String[]{"AM","PM"};
	
	static Alarm alarm;
	
	public AlarmView(/*Model m*/)
	{
		//super(m);
	}
	
	public static void AddAlarmGUI(Model m)
	{
		final Color babyTeal = new Color(142, 229, 238);
		Font railWayBig = new Font("Raleway-Regular", Font.PLAIN, 26);
		Font railWay = new Font("Raleway-Regular", Font.PLAIN, 12);

		java.io.InputStream is = DailyView.class
				.getResourceAsStream("Raleway-Regular.ttf");
		try
		{
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			railWay = font.deriveFont(Font.PLAIN, 12);
			railWayBig = font.deriveFont(Font.PLAIN, 26);

		} catch (FontFormatException e1)
		{
			e1.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

		// UPPER GUI
		JPanel upperPannel = new JPanel();
		upperPannel.setLayout(new GridLayout(2, 2));
		upperPannel.setBackground(Color.WHITE);

		// UPPER LEFT
		JPanel upperLeftPannel = new JPanel();
		upperLeftPannel.setBackground(Color.WHITE);
		upperLeftPannel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upperPannel.add(upperLeftPannel, BorderLayout.WEST);

		JLabel eventLabel = new JLabel(" Set Alarm");
		eventLabel.setFont(railWayBig);
		eventLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upperLeftPannel.add(eventLabel, BorderLayout.WEST);

		// UPPER RIGHT
		JPanel upperRightPannel = new JPanel();
		upperRightPannel.setBackground(Color.WHITE);
		upperRightPannel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		upperPannel.add(upperRightPannel, BorderLayout.EAST);

		final JButton removeButton = new JButton("-");
		removeButton.setFont(railWayBig);
		removeButton.setBackground(babyTeal);
		removeButton.setPreferredSize(new Dimension(80, 28));
		removeButton.setOpaque(true);
		removeButton.setBorderPainted(false);
		upperRightPannel.add(removeButton);

		removeButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent evt)
			{
				removeButton.setBackground(Color.RED);
			}// end mouse entered

			public void mouseExited(MouseEvent evt)
			{
				removeButton.setBackground(babyTeal);
			}// end mouse exited

			public void mouseClicked(MouseEvent evt)
			{
				removeButton.setBackground(Color.GREEN);
			}

			public void mousePressed(MouseEvent evt)
			{
			}

			public void mouseReleased(MouseEvent evt)
			{
			}
		}// end anonymous inner class
				);

		final JButton saveButton = new JButton("Save");
		saveButton.setFont(railWay);
		upperRightPannel.add(saveButton);

		saveButton.setBackground(babyTeal);
		saveButton.setPreferredSize(new Dimension(80, 28));
		saveButton.setOpaque(true);
		saveButton.setBorderPainted(false);

		saveButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent evt)
			{
				saveButton.setBackground(Color.CYAN);
			}// end mouse entered

			public void mouseExited(MouseEvent evt)
			{
				saveButton.setBackground(babyTeal);
			}// end mouse exited

			public void mouseClicked(MouseEvent evt)
			{
				saveButton.setBackground(Color.GREEN);
			}

			public void mousePressed(MouseEvent evt)
			{
			}

			public void mouseReleased(MouseEvent evt)
			{
			}
		}// end anonymous inner class
				);

		// UPPER BOTTOM LEFT
		JPanel upperBottomLeftPannel = new JPanel();
		upperBottomLeftPannel.setBackground(babyTeal);
		upperBottomLeftPannel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		upperPannel.add(upperBottomLeftPannel, BorderLayout.SOUTH);

		// UPPER BOTTOM RIGHT
		JPanel upperBottomRightPannel = new JPanel();
		upperBottomRightPannel.setBackground(babyTeal);
		upperBottomRightPannel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		upperPannel.add(upperBottomRightPannel, BorderLayout.SOUTH);

		// LOWER GUI
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(4, 0));
		lowerPanel.setBackground(Color.WHITE);

		JPanel extraSpacePanel1 = new JPanel();
		extraSpacePanel1.setBackground(Color.WHITE);
		extraSpacePanel1.setLayout(new FlowLayout(2, 2, 2));
		lowerPanel.add(extraSpacePanel1);

		JPanel TimeDatePanel = new JPanel();
		TimeDatePanel.setBackground(Color.WHITE);
		TimeDatePanel.setLayout(new FlowLayout(10, 10, 10));
		lowerPanel.add(TimeDatePanel);

		JLabel timeLabel = new JLabel("Time: ");
		timeLabel.setFont(railWay);
		TimeDatePanel.add(timeLabel);

		JPanel timeComboBoxes = new JPanel();
		timeComboBoxes.setBackground(Color.WHITE);
		timeComboBoxes.setLayout(new GridLayout(1, 3, 6, 0));

		final JComboBox<Integer> timeHour = new JComboBox<Integer>(
				EventView.HOURS);
		final JComboBox<String> timeMinute = new JComboBox<String>(
				EventView.MINUTES);
		final JComboBox<String> timeMeridiem = new JComboBox<String>(
				EventView.MERIDIEM);
		timeComboBoxes.add(timeHour);
		timeComboBoxes.add(timeMinute);
		timeComboBoxes.add(timeMeridiem);
		
		if(alarm != null)
		{
    		String min = alarm.time.min+"";
    		if(alarm.time.min < 10)
    			min = "0"+min;
    		
    		timeHour.setSelectedIndex(java.util.Arrays.asList(HOURS).indexOf(alarm.time.hour));
    		timeMinute.setSelectedIndex(java.util.Arrays.asList(MINUTES).indexOf(min));
    		timeMeridiem.setSelectedIndex(java.util.Arrays.asList(MERIDIEM).indexOf(alarm.time.meridiem));
    	}
		TimeDatePanel.add(timeComboBoxes);
		
		JPanel extraSpacePanel = new JPanel();
		extraSpacePanel.setBackground(Color.WHITE);
		extraSpacePanel.setLayout(new FlowLayout(10, 10, 10));
		lowerPanel.add(extraSpacePanel);

		JLabel dateLabel = new JLabel("Date: ");
		dateLabel.setFont(railWay);
		TimeDatePanel.add(dateLabel);

		final JTextField dateTextField = new JTextField(25);
		if(alarm != null)
    	{
    		String date[] = new String[]{alarm.date[0]+"",alarm.date[1]+""};
    		if(alarm.date[0] < 10)
    			date[0] = "0"+date[0];
    		if(alarm.date[1] < 10)
    			date[1] = "0"+date[1];
    		dateTextField.setText(date[0]+"/"+date[1]+"/"+alarm.date[2]);
    	}
		TimeDatePanel.add(dateTextField);

		JPanel eventCancelPanel = new JPanel();
		eventCancelPanel.setBackground(Color.WHITE);
		eventCancelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		final JButton cancelEventButton = new JButton("Cancel");
		cancelEventButton.setFont(railWay);
		eventCancelPanel.add(cancelEventButton);

		cancelEventButton.setBackground(babyTeal);
		cancelEventButton.setPreferredSize(new Dimension(80, 28));
		cancelEventButton.setOpaque(true);
		cancelEventButton.setBorderPainted(false);

		cancelEventButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent evt)
			{
				cancelEventButton.setBackground(Color.RED);
			}// end mouse entered

			public void mouseExited(MouseEvent evt)
			{
				cancelEventButton.setBackground(babyTeal);
			}// end mouse exited

			public void mouseClicked(MouseEvent evt)
			{
				cancelEventButton.setBackground(Color.CYAN);
			}

			public void mousePressed(MouseEvent evt)
			{
			}

			public void mouseReleased(MouseEvent evt)
			{
			}
		});
		
		lowerPanel.add(eventCancelPanel);
		
	 	removeButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					timeHour.setSelectedIndex(0);
					timeMinute.setSelectedIndex(0);
					timeMeridiem.setSelectedIndex(0);
					dateTextField.setText("");
					alarm = null;
				}
			}
		);
		
		// Create and set up the window.

		final JFrame frame = new JFrame("Event Planner Pro");
		frame.setSize(600, 400);
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.WHITE);

		frame.add(upperPannel, BorderLayout.NORTH);
		frame.add(lowerPanel, BorderLayout.SOUTH);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		
		
		saveButton.addActionListener(
		    	new ActionListener()
	    		{
	    			public void actionPerformed(ActionEvent evt)
	    			{
	    				int[] date;
	    				try
	    				{
	    					date = new int[]{Integer.parseInt(dateTextField.getText().substring(0,2)),Integer.parseInt(dateTextField.getText().substring(3,5)),Integer.parseInt(dateTextField.getText().substring(6))};
	    				}
	    				catch(StringIndexOutOfBoundsException e)
	    				{
	    					System.out.println("ERROR: Invalid date. Event cannot be made.");
	    					System.out.println();
	    					return;
	    				}
	    				
	    				int time = (int)timeHour.getSelectedItem()*100+Integer.parseInt((String)timeMinute.getSelectedItem());
	    				if(timeMeridiem.getSelectedItem().equals("AM") && (int)timeHour.getSelectedItem() == 12)
	    					time -= 1200;
	    				else if(timeMeridiem.getSelectedItem().equals("PM") && (int)timeHour.getSelectedItem() != 12)
	    					time += 1200;
	    				
	    				alarm = new Alarm(new int[]{date[0],date[1],date[2],time});
	    				frame.setVisible(false);
	    			}
	    		}
		    );
		
		// CLOSES THE ALARM VIEW FRAME
		cancelEventButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				frame.setVisible(false);
			}
		});
	}
}
