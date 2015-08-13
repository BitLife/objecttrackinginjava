

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.opencv.core.Mat;

public class SliderUIFrame extends JFrame{
	
	private static int hminvalue;
	private static int hmaxvalue;
	private static int sminvalue;
	private static int smaxvalue;
	private static int vminvalue;
	private static int vmaxvalue;
	private JSlider hmin;
	private JSlider hmax;
	private JSlider smin;
	private JSlider smax;
	private JSlider vmin;
	private JSlider vmax;
	private JPanel sliderPanel;
	private JTextField textField;
	private ChangeListener listener;
	private Mat hsvselected;
	
	public SliderUIFrame(){
		setTitle("HSV Value Slider");
		setSize(350, 450);
		
		sliderPanel = new JPanel();
		sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		listener = new ChangeListener(){
			public void stateChanged(ChangeEvent event){
				JSlider source = (JSlider) event.getSource();
				textField.setText("" + source.getValue());
			}
		};
		
		hmin = new JSlider();
		hmin.setMaximum(500);
		hmin.setValue(0);
		hmin.setPaintTicks(true);
		hmin.setMajorTickSpacing(100);
		hmin.setMinorTickSpacing(20);
		hmin.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(hmin.getValueIsAdjusting())
					hminvalue = (hmin.getValue());
			}
		});
		addSlider(hmin, "H Min");
		
		hmax = new JSlider();
		hmax.setMaximum(500);
		hmax.setValue(500);
		hmax.setPaintTicks(true);
		hmax.setMajorTickSpacing(100);
		hmax.setMinorTickSpacing(20);
		hmax.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(hmax.getValueIsAdjusting())
					hmaxvalue = hmax.getValue();
			}
		});
		addSlider(hmax, "H Max");
		
		smin = new JSlider();
		smin.setMaximum(500);
		smin.setValue(0);
		smin.setPaintTicks(true);
		smin.setMajorTickSpacing(100);
		smin.setMinorTickSpacing(20);
		smin.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(smin.getValueIsAdjusting())
					sminvalue = smin.getValue();
			}
		});
		addSlider(smin, "S Min");
		
		smax = new JSlider();
		smax.setMaximum(500);
		smax.setValue(500);
		smax.setPaintTicks(true);
		smax.setMajorTickSpacing(100);
		smax.setMinorTickSpacing(20);
		smax.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(smax.getValueIsAdjusting())
					smaxvalue = smax.getValue();
			}
		});
		addSlider(smax, "S Max");
		
		vmin = new JSlider();
		vmin.setMaximum(500);
		vmin.setValue(0);
		vmin.setPaintTicks(true);
		vmin.setMajorTickSpacing(100);
		vmin.setMinorTickSpacing(20);
		vmin.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(vmin.getValueIsAdjusting())
					vminvalue = vmin.getValue();
			}
		});
		addSlider(vmin, "V Min");
		
		vmax = new JSlider();
		vmax.setMaximum(500);
		vmax.setValue(500);
		vmax.setPaintTicks(true);
		vmax.setMajorTickSpacing(100);
		vmax.setMinorTickSpacing(20);
		vmax.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(vmax.getValueIsAdjusting())
					vmaxvalue = vmax.getValue();
			}
		});
		addSlider(vmax, "V Max");
		
		textField = new JTextField();
		add(sliderPanel, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
	}
	
	public void addSlider(JSlider s, String name){
		s.addChangeListener(listener);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(name));
		sliderPanel.add(panel);
	}
	
	public int hminValue(){
		return hminvalue;
	}
	
	public int hmaxValue(){
		return hmaxvalue;
	}
	
	public int sminValue(){
		return sminvalue;
	}
	
	public int smaxValue(){
		return smaxvalue;
	}
	
	public int vminValue(){
		return vminvalue;
	}
	
	public int vmaxValue(){
		return vmaxvalue;
	}
}
