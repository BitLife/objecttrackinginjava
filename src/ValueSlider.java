

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ValueSlider {
	public ValueSlider(){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				SliderUIFrame frame = new SliderUIFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}
}
