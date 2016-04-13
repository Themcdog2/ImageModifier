import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class SkeletonFrame extends JFrame{
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
	
	
	//MENU BAR
	private JMenuBar menuBar;
	private JPopupMenu fileMenu;
	private JMenuItem File;
	
	private JMenuItem addPicture;
	
	//****************
	
	
	
	
	
	public SkeletonFrame(){
		//Call the superclass
 		super("Image Modifier");
		
		//Initalize Variables
		menuBar = new JMenuBar();
		fileMenu = new JPopupMenu();
	    File = new JMenuItem("File");
	    
	    addPicture = new JMenuItem("Add Picture");
	    addPicture.setVisible(true);
	    
	    
	    
	    //Action Listeners
	    File.addActionListener(new ActionListener() {
			boolean isClicked = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isClicked){
					isClicked = false;
				}else{
					isClicked = true;
				}
				addPicture.setVisible(isClicked);
				
			}
		});
		
		
		
		
		
		
		
		//Set the defaults
	    this.setVisible(true);
		this.setSize(DEFAULT_FRAME_DIMENSION);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(defaultLayout);
		this.setDefaultLookAndFeelDecorated(lookAndFeelDecorated);
		
		//Add components
		//this.add(menuBar);
		this.setJMenuBar(menuBar);
		this.getJMenuBar().add(File);
	//	File.add();
	//	File.add(fileMenu);
		
	}
	
	
	
}
