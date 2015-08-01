import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import javax.swing.JFrame;

public class GetWebcamFrame  {
	private static final JFrame frame = new JFrame("CANHacks 2015");
	
	public static void main(String[] args) throws InterruptedException, IOException
	{
		//ValueSlider run = new ValueSlider();
		int frameCount = 0;
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		PaintImage paint = new PaintImage();
		FilterImages filter = new FilterImages();
		
		frame.setContentPane(paint);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		VideoCapture cam = new VideoCapture(0);
		cam.open(0);
		Thread.sleep(1000);
			while(cam.isOpened() || true){
				Thread.sleep(1000/60);
				frameCount++;
				Mat regular = new Mat();
				Mat HSV = new Mat();
				Mat filtered = new Mat();
				cam.read(regular);
				HSV = filter.convertToHSV(regular);
				filtered = filter.filterHSV(HSV);
				Image scaledImg = scaleImage(toBufferedImage(regular));
				paint.queueImage(scaledImg, filter.findPts(filtered));
				System.out.println("Frame "+frameCount);
				paint.setfcount(frameCount);
			}
			cam.release();
		}
	
	public static Image scaleImage(Image frame){	 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	 	int w = dim.width;
	 	int h = dim.height;
	 	frame = frame.getScaledInstance(w,h, Image.SCALE_REPLICATE);
		return frame;	
	}

	public static Image toBufferedImage(Mat m){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 )
            type = BufferedImage.TYPE_3BYTE_BGR;
        
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);  
        return image;
    }
}
