import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;

public class GetWebcamFrame implements KeyListener{
	private static final JFrame frame = new JFrame("CANHacks 2015");
	private static boolean switchImage;
	private static boolean defaul;
	private static PaintImage paint;
	
	public GetWebcamFrame(PaintImage paint) throws IOException{
		new ValueSlider();
		switchImage = false;
		defaul = true;
		frame.setContentPane(paint);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws InterruptedException, IOException{
		paint = new PaintImage();
		new GetWebcamFrame(paint);
		new GrabFrames(switchImage, defaul, paint).start();
		}
	
	/*public static Mat BufferedImagetoMat(BufferedImage buff)
	{
		byte[] sampimgpixels = ((DataBufferByte) buff.getRaster().getDataBuffer()).getData();
		Mat sample = new Mat(buff.getHeight(),buff.getWidth(),CvType.CV_8UC3);
		sample.put(0, 0, sampimgpixels);
		
		return sample;
	}*/
	
	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		int key =  e.getKeyCode();
		if(key == KeyEvent.VK_ENTER)
			switchImage = !(switchImage);
		if(key == KeyEvent.VK_SHIFT)
			defaul = !(defaul);
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	public void keyTyped(KeyEvent e) {
	}
}
