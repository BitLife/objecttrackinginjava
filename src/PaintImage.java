import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintImage extends JPanel{
	private Image image;
	private double x;
	private double y;
	private double r;
	private int fcount;
	private boolean defaultmode;
	//private boolean edit;
	private boolean loading = true;
	private File in;
	private int lcounter = 1;
	private Graphics2D g2d;
	
	public PaintImage() throws IOException{
		File input = new File("resources\\img1.png");
		
		image = ImageIO.read(input);
		image = scaleImage(image);
	}
	
	public void paint(Graphics g){
		g2d =  (Graphics2D)g;
		if(lcounter == 4)
			lcounter = 1;
		else
			lcounter++;
		try {
			Thread.sleep(1000/10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		 image = scaleImage(image);
		 g2d.drawImage(image, 0, 0, null);
		 g2d.setColor(Color.GREEN);
		 g2d.drawOval((int) (x-(r/2)), (int) (y-(r/2)), (int) r, (int) r);
		 if(x != 0 && y != 0)
			 drawCrossHair(g2d,(int)x, (int)y);
		 
		 g2d.setColor(Color.BLACK);
		 g2d.setFont(new Font("Lucida Console",Font.PLAIN,30));
		 
		 if(!defaultmode)
			 g2d.drawString("Editable", 30, 50);
		 else 
			 g2d.drawString("Default", 30, 50);
		 this.removeAll();
		 
		 
		 if(loading){
			 in = new File("resources\\img" + lcounter + ".png");
			 try {
				image = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 image = scaleImage(image);
		 }
		 repaint();
	}
	
	public Image scaleImage(Image frame){	 
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	 	frame = frame.getScaledInstance(d.width, d.height, Image.SCALE_REPLICATE);
		return frame;	
	}
	
	public void queueImage(Image k, double[] ary,boolean def){
		loading = false;
		image = k;
	
		defaultmode = def;
		x = Math.round((ary[0] * 1920) / 640.0);
		y = Math.round((ary[1] * 1080) / 480.0);
		r = ary[2] * 5;
	}
	
	public void setfcount(int i){
		fcount = i;
	}
	
	public void drawCrossHair(Graphics2D g,int  x, int y){
		g.setStroke(new BasicStroke(10));
		g.fillRect(x, y, 20, 20);
		//g.fillRect(y, x, 20,20);
	}
}
