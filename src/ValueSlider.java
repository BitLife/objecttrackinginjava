

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ValueSlider {
	public ValueSlider()
	{
		/*EventQueue.invokeLater(new Runnable(){
			public void run(){
				SliderUIFrame frame = new SliderUIFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setResizable(false);
				System.out.println(frame.hminValue());
			    System.out.println(frame.hmaxValue());
			    System.out.println(frame.sminValue());
			    System.out.println(frame.smaxValue());
			    System.out.println(frame.vminValue());
			    System.out.println(frame.vmaxValue());
			}
		});*/
		
		SliderUIFrame frame = new SliderUIFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		System.out.println(frame.hminValue());
	    System.out.println(frame.hmaxValue());
	    System.out.println(frame.sminValue());
	    System.out.println(frame.smaxValue());
	    System.out.println(frame.vminValue());
	    System.out.println(frame.vmaxValue());
	}
	/*public static void main(String[] args)
	{
		new ValueSlider();
	}*/
}
