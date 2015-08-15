import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;

public class GetWebcamFrame{
	private static final JFrame frame = new JFrame("CANHacks 2015");

	private static PaintImage paint;
	
	public static void main(String[] args) throws InterruptedException, IOException{
		paint = new PaintImage();
		
		new GrabFrames(false, true, paint,frame).start();
		
	
	}
	
	/*public static Mat BufferedImagetoMat(BufferedImage buff)
	{
		byte[] sampimgpixels = ((DataBufferByte) buff.getRaster().getDataBuffer()).getData();
		Mat sample = new Mat(buff.getHeight(),buff.getWidth(),CvType.CV_8UC3);
		sample.put(0, 0, sampimgpixels);
		
		return sample;
	}*/	
}
