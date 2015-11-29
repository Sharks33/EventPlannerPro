import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

public class CalendarFrame extends JFrame
{
	private ControllerPanel controller;
	private MonthlyView view;

	/**
	 * CalendarFrame constructor initializes the model, view, and controller.
	 * Sets the window size and the layouts of its components.
	 */
	public CalendarFrame(Model m)
	{
		controller = new ControllerPanel(m);
		view = new MonthlyView(m);
		m.attach(view);

		this.setSize(1500, 700);
		add(controller, BorderLayout.NORTH);
		add(view, BorderLayout.CENTER);
		setBackground(Color.WHITE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// pack();
		setVisible(true);
	}
}
