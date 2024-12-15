package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveMaxAndMinButton extends JDialog {
	public RemoveMaxAndMinButton(JFrame frame, String Title) {
		super(frame, Title);
	    addWindowListener(new WindowAdapter(){
	            public void windowClosing(WindowEvent evt){
	            	System.exit(0);
	            }
	    });
	}
}
