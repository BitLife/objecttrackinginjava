import java.io.IOException;
import javax.swing.JFrame;

public class GetWebcamFrame{
	private static final JFrame frame = new JFrame("CANHacks 2015");

	private static PaintImage paint;
	private static GrabFrames fram;
	public static void main(String[] args) throws InterruptedException, IOException{
		paint = new PaintImage();
		
		fram = new GrabFrames(false, true, paint,frame);
		fram.start();
	}
}
