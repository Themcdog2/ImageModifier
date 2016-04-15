import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CollisionBox extends Rectangle{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 8308591092082728182L;

	//Do stuff?
	private BufferedImage linkedImage;
	private boolean activated;
	
	public CollisionBox(int x, int y, int width, int height, BufferedImage linkedImage) {
		super(x, y, width, height);
		this.setLinkedImage(linkedImage);
	}

	public BufferedImage getLinkedImage() {
		return linkedImage;
	}

	public void setLinkedImage(BufferedImage linkedImage) {
		this.linkedImage = linkedImage;
	}

	public void setLocation(double d, double e) {
		setLocation((int) d, (int) e);
		
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
