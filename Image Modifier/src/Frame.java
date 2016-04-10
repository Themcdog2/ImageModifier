import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame{
	//***************
	
	//SERIAL
	private static final long serialVersionUID = -77960254022306949L;
	
	//****************
	//STATIC VARIABLES
	//****************
	
	//FINALS
	public static final int DEFAULT_FRAME_WIDTH = 800;
	public static final int DEFAULT_FRAME_HEIGHT = 600;
	public static final Dimension DEFAULT_FRAME_DIMENSION = new Dimension(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
	
	//VARIABLE
	public static int FRAME_WIDTH;
	public static int FRAME_HEIGHT;
	public static int FRAME_DIMENSION;
	
	//********************
	//END STATIC VARIABLES
	//********************
	
	
	//FRAME DEFINITION
	private BorderLayout defaultLayout;
	private boolean lookAndFeelDecorated;
	private boolean glassPane;
	
	//MENU BAR
	private JMenuBar menuBar;
	private JMenuItem File;
	
	//****************
	
	//test
	
}
