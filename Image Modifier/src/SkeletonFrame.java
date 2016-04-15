import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

public class SkeletonFrame extends JFrame implements Runnable {
	// ***************

	// SERIAL
	private static final long serialVersionUID = -77960254022306949L;

	// ****************
	// STATIC VARIABLES
	// ****************

	// FINALS
	public static final int DEFAULT_FRAME_WIDTH = 800;
	public static final int DEFAULT_FRAME_HEIGHT = 600;
	public static final Dimension DEFAULT_FRAME_DIMENSION = new Dimension(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

	// VARIABLE
	public static int FRAME_WIDTH;
	public static int FRAME_HEIGHT;
	public static int FRAME_DIMENSION;

	public static boolean running;
	// ********************
	// END STATIC VARIABLES
	// ********************

	// FRAME DEFINITION
	private BorderLayout defaultLayout;
	private boolean lookAndFeelDecorated;
	private CanvasUtil CV;

	// MENU BAR
	private JMenuBar menuBar;
	private JPopupMenu fileMenu;
	private JMenu File;

	private JMenuItem addPicture;
	private JFileChooser addPictureDialouge;

	// IMAGE VARIABLES
	static ArrayList<BufferedImage> images;
	
	// COLLISION VARIBALE	
	static ArrayList<CollisionBox> collisionBoxs;
	
	//THREADING
	Thread t;

	// ****************

	public SkeletonFrame() {
		// Call the superclass
		super("Image Modifier");

		// Initalize Variables
		menuBar = new JMenuBar();
		fileMenu = new JPopupMenu();
		addPictureDialouge = new JFileChooser();
		File = new JMenu("File");
		
		images = new ArrayList<BufferedImage>();
		collisionBoxs = new ArrayList<CollisionBox>();

		addPicture = new JMenuItem("Add Picture");
		addPicture.setVisible(true);
		
		CV = new CanvasUtil();
		t = new Thread(this);

		// Action Listeners
		File.addActionListener(new ActionListener() {
			boolean isClicked = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isClicked) {
					isClicked = false;
				} else {
					isClicked = true;
				}
				addPicture.setVisible(isClicked);

			}
		});

		addPicture.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addPictureDialouge.showOpenDialog(new JFrame());
				File tempFile = addPictureDialouge.getSelectedFile();
				BufferedImage tempImage = null;
				try {
					tempImage = ImageIO.read(tempFile);
				} catch (IOException e1) {
					System.out.println("Error getting image");
					e1.printStackTrace();
				}
				images.add(tempImage);
				collisionBoxs.add(new CollisionBox(10, 10, tempImage.getWidth(), tempImage.getHeight(), tempImage));
				System.out.println("Image added");
			}
		});

		// Set the defaults
		this.setVisible(true);
		this.setSize(DEFAULT_FRAME_DIMENSION);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(defaultLayout);
		this.setDefaultLookAndFeelDecorated(lookAndFeelDecorated);

		// Add components
		// this.add(menuBar);
		this.setJMenuBar(menuBar);
		this.getJMenuBar().add(File);
		// File.add();
		File.add(addPicture);
		this.add(CV, BorderLayout.CENTER);

		
		startThread();
	}

	// ***************
	// THREADING
	// ***************
	private void startThread(){
		running = true;
		t.start();
	}
	
	private void stop() {
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void tick() {
		// TODO Auto-generated method stub
		if(CV.isMouseDown){
		for(CollisionBox e : collisionBoxs){
			if(e.isActivated()){
				  double xDistance = CV.getMouseX() - e.getX();
		          double yDistance = CV.getMouseY() - e.getY();
		          double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		          if (distance > 0.3) 
		              e.setLocation((e.getX() + xDistance * 0.2), (e.getY() + yDistance * 0.2));
		              
		          }
			}
		}
	}
		
	
	
	public static void moveObjects(CollisionBox e, int x, int y){
		
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			CV.repaint();
			frames++;

			if ((System.currentTimeMillis() - timer) > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();

	}

	

}

class CanvasUtil extends Canvas{
	
	public boolean isMouseDown = false;
	public int mouseX = 0;
	public int mouseY = 0;


	public CanvasUtil(){
		this.setVisible(true); //Finish this properly
		this.setBounds(0, 0, SkeletonFrame.DEFAULT_FRAME_WIDTH, SkeletonFrame.DEFAULT_FRAME_HEIGHT);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			
				mouseX = e.getX();
				mouseY = e.getY();
				isMouseDown = true;
				for(CollisionBox j : SkeletonFrame.collisionBoxs){
					if(j.intersects(e.getX(), e.getY(), 1, 1)){
						//(e.getX() - (j.getWidth() / 2), e.getY() - (j.getHeight() / 2));
						j.setActivated(true);
						//SkeletonFrame.moveObject(j, (int)(e.getX() - (j.getWidth() / 2)), (int)(e.getY() - (j.getHeight() / 2)));
					}
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isMouseDown){
					isMouseDown = false;
				}else{
					isMouseDown = true;
				}
				
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				
				isMouseDown = true;
				for(CollisionBox j : SkeletonFrame.collisionBoxs){
					if(j.intersects(e.getX(), e.getY(), 1, 1)){
						//(e.getX() - (j.getWidth() / 2), e.getY() - (j.getHeight() / 2));
						j.setActivated(true);
						//SkeletonFrame.moveObject(j, (int)(e.getX() - (j.getWidth() / 2)), (int)(e.getY() - (j.getHeight() / 2)));
					}
				}
				
			}
		});

	}
	
	
	@Override
	public void paint(Graphics g) {

		for(CollisionBox e : SkeletonFrame.collisionBoxs){
		g.drawImage(e.getLinkedImage(), (int)e.getX(), (int)e.getY(), null);
		}
		g.drawString("Mouse X: " + mouseX + " Mouse Y: " + mouseY, 30, 30);
	}


	public boolean isMouseDown() {
		return isMouseDown;
	}


	public void setMouseDown(boolean isMouseDown) {
		this.isMouseDown = isMouseDown;
	}


	public int getMouseX() {
		return mouseX;
	}


	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}


	public int getMouseY() {
		return mouseY;
	}


	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
	
	
}
