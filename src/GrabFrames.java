import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;


public class GrabFrames extends Thread implements KeyListener {
	
	private int frameCount;
	private static Mat HSV;
	private static Mat regular;
	private static Mat filtered;
	private boolean switchImage;
	private boolean defaul;
	private FilterImages filter;
	private PaintImage paint;
	private static byte [] b;
	private static	int type;
	private static Dimension dim;
	private static VideoCapture cam;
	private static JFrame frame;
	public GrabFrames(boolean switchImage, boolean defaul, PaintImage paint, JFrame Fram) throws IOException{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		cam = new VideoCapture(0);
		filter = new FilterImages();
		this.switchImage = switchImage;
		this.paint = paint;
		this.defaul = defaul;
		HSV = new Mat();
		regular = new Mat();
		filtered = new Mat();
		frame = new JFrame();
		frame.setContentPane(paint);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new ValueSlider();
		
	}
	
	public void run(){
		
		
		cam.open(0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			while(cam.isOpened()){
				System.out.println(switchImage);
				try {
					Thread.sleep(1000/10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frameCount++;
				
				
				cam.read(regular);
				HSV = filter.convertToHSV(regular);
				try {
					filtered = filter.filterHSV(HSV, defaul);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Image scaledImg = scaleImage(toBufferedImage(filtered));
				if(switchImage || defaul)
					scaledImg = scaleImage(toBufferedImage(regular));
				paint.queueImage(scaledImg, filter.findPts(filtered), defaul);
				System.out.println("Frame "+frameCount);
				paint.setfcount(frameCount);
			}
			cam.release();
	}
	
	public static Image toBufferedImage(Mat m){
        type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 )
            type = BufferedImage.TYPE_3BYTE_BGR;
        
        int bufferSize = m.channels()*m.cols()*m.rows();
        b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);  
        return image;
    }
	
	public static Image scaleImage(Image frame){	 
		dim = Toolkit.getDefaultToolkit().getScreenSize();
	 	frame = frame.getScaledInstance(dim.width, dim.height, Image.SCALE_REPLICATE);
		return frame;	
	}
	
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
