package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Graphic extends JFrame implements ActionListener{
	private RemoveMaxAndMinButton frame = new RemoveMaxAndMinButton(new JFrame(), "Simple Calculator");
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JTextField text_field = new JTextField();
	private JButton button = new JButton();
	private String[] text_button = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", "=", "+"};
	private Font font = new Font("Roboto", Font.PLAIN, 78);
	private HashMap<JButton, String> map_button = new HashMap<>();
	private String text = "0";
	Calculation calc = new Calculation();
//	private boolean isError;
	
	private void calculation() {
		calc.calculatingValue();
		this.text_field.setText(calc.getTotal());
	}
	
	public void run() {
		setFrame();
		this.frame.setVisible(true);
	}
	
	private void setPanel1() {
		this.panel1.setLayout(new BorderLayout());
		this.panel1.setBounds(20, 30, 300, 100);
		setTextField();
		this.panel1.add(this.text_field, BorderLayout.CENTER);
	}
	
	private void setPanel2() {
		this.panel2.setLayout(new GridBagLayout());
		this.panel2.setBounds(10, 100, 320, 350);
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.insets = new Insets(2, 2, 2, 2);
		setButton(constraint);
	}
	
	private void setFrame() {
		this.frame.setSize(350, 450);
		this.frame.setLayout(null);

		setPanel1();
		this.frame.add(this.panel1);
		setPanel2();
		this.frame.add(panel2);
	}
	
	private void setTextField() {
		this.text_field.setText(text);
		this.text_field.setFont(this.font);
		this.text_field.setHorizontalAlignment(SwingConstants.RIGHT);
		this.text_field.setMargin(new Insets(20, 0, 0, 0));
		this.text_field.setFocusable(false);
	}
	
	private void setButton(GridBagConstraints constraint) {
		int row = 0, col = 0;
		for(String i : text_button) {
			button = new JButton(i);
			button.setHorizontalAlignment(SwingConstants.CENTER);
			constraint.gridx = col;
			constraint.gridy = row;
			constraint.ipadx = 30;
			constraint.ipady = 30;
			this.panel2.add(button, constraint);
			this.map_button.put(button, i);
			button.addActionListener(this);
			col++;
			if(col % 4 == 0) {
				col = 0;
				row++;
			}
		}
	}
	
//	private void filterInput(HashMap<JButton, String> map_button) {
//		List<HashMap.Entry<JButton, String>> entryList = new ArrayList<>(map_button.entrySet());
//		this.isError = false;
//		for (int i = 0; i < entryList.size(); i++) {
//			if(i % 2 == 0) {
//				Map.Entry<JButton, String> entry = entryList.get(i);
//				
//				String value = entry.getValue();
//				if(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
//					System.out.println("Indeks genap " + i + " memiliki operator: " + entry.getValue());
//					map_button.clear();
//					this.isError = true;
//					break;
//				}
//			}
//		}
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		this.text = this.map_button.get(clickedButton);
//		filterInput(map_button);
//		if (this.isError) {
//	        this.text_field.setText("error");
//	        return;
//	    }
		
		calc.addToQueueValue(text);
	
		if(this.text == "=") {
			calculation();
		}else if(this.text == "C") {
			this.text_field.setText("0");
//			this.map_button.clear();
			this.calc.clearQueue();
		}else {
			this.text_field.setText(text);
		}
	}
}
