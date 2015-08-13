import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GetWebcamFrame implements KeyListener{
	private static final JFrame frame = new JFrame("CANHacks 2015");
	private static boolean switchImage;
	private static boolean defaul;
	
	public GetWebcamFrame(PaintImage paint) throws IOException{
		ValueSlider run = new ValueSlider();
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
		
		FilterImages filter = new FilterImages();
		PaintImage paint = new PaintImage();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		int frameCount = 0;
		new GetWebcamFrame(paint);
		
		/*VideoCapture cam = new VideoCapture(0);
		cam.open(0);
		Thread.sleep(1000);
			while(cam.isOpened()){
				System.out.println(switchImage);
				Thread.sleep(1000/60);
				frameCount++;
				Mat regular = new Mat();
				Mat HSV = new Mat();
				Mat filtered = new Mat();
				cam.read(regular);
				HSV = filter.convertToHSV(regular);
				filtered = filter.filterHSV(HSV, defaul);
				Image scaledImg = scaleImage(toBufferedImage(filtered));
				if(switchImage || defaul)
					scaledImg = scaleImage(toBufferedImage(regular));
				paint.queueImage(scaledImg, filter.findPts(filtered), defaul);
				System.out.println("Frame "+frameCount);
				paint.setfcount(frameCount);
			}
			cam.release();
		}*/ 
		File sampf = new File("E:\\eclipsewsj\\ObjectRecog\\penisball.jpg"); 
		BufferedImage sampimg = ImageIO.read(sampf);
		Mat sample = BufferedImagetoMat(sampimg);

		while(true)
		{
			Thread.currentThread().sleep(500);
			frameCount++;
			Mat regular = new Mat();
			Mat HSV = new Mat();
			Mat filtered = new Mat();
			regular = sample;
			HSV = filter.convertToHSV(regular);
			filtered = filter.filterHSV(HSV,defaul);
			Image scaledImg = scaleImage(toBufferedImage(filtered));
			if(switchImage)
				scaledImg = scaleImage(toBufferedImage(regular));
			paint.queueImage(scaledImg, filter.findPts(filtered),defaul);
			System.out.println("Frame "+frameCount);
			paint.setfcount(frameCount);
		}
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
	
	public static Mat BufferedImagetoMat(BufferedImage buff)
	{
		byte[] sampimgpixels = ((DataBufferByte) buff.getRaster().getDataBuffer()).getData();
		Mat sample = new Mat(buff.getHeight(),buff.getWidth(),CvType.CV_8UC3);
		sample.put(0, 0, sampimgpixels);
		
		return sample;
	}
	
	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		int key =  e.getKeyCode();
		if(key == KeyEvent.VK_ENTER)
			switchImage = !(switchImage);
		if(key == KeyEvent.VK_SHIFT)
			defaul = !(defaul);
	}

	public void keyTyped(KeyEvent e) {
	}
}
