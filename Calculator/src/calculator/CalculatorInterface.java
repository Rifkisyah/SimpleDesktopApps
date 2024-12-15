package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.HashMap;

public class CalculatorInterface extends JFrame implements ActionListener {
    private RemoveMaxAndMinButton frame = new RemoveMaxAndMinButton(new JFrame(), "Simple Calculator");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JTextField text_field = new JTextField();
    private JTextArea log = new JTextArea();
    private JButton button = new JButton();
    private String[] text_button = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", "=", "+"};
    private Font font = new Font("Roboto", Font.PLAIN, 78);
    private HashMap<JButton, String> map_button = new HashMap<>();
    private String currentInput = ""; // Menyimpan angka yang sedang diketik
    private Calculation calc = new Calculation();
    
    private void calculation() {
        calc.calculatingValue();
        this.text_field.setText(calc.getTotal());
        // Tambahkan hasil ke log
        log.append("\nResult: " + calc.getTotal() + "\n");
    }
    
    public void run() {
        setFrame();
        this.frame.setVisible(true);
    }
    
    private void setFrame() {
        this.frame.setSize(350, 470);
        this.frame.setLayout(null);
        
        setLogPanel();
        this.frame.add(this.panel1);
        setPanel1();
        this.frame.add(this.panel1);
        setPanel2();
        this.frame.add(this.panel2);
    }

    private void setLogPanel() {
        JPanel logPanel = new JPanel();
        logPanel.setLayout(new BorderLayout());
        logPanel.setBounds(20, 40, 300, 20);
        log.setText("");  // Reset log field
        log.setFocusable(false);
        logPanel.add(log, BorderLayout.CENTER);
        this.frame.add(logPanel);
    }
    
    private void setPanel1() {
        this.panel1.setLayout(new BorderLayout());
        this.panel1.setBounds(20, 70, 300, 80);
        setTextField();
        this.panel1.add(this.text_field, BorderLayout.CENTER);
    }
    
    private void setPanel2() {
        this.panel2.setLayout(new GridBagLayout());
        this.panel2.setBounds(10, 120, 320, 350);
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(2, 2, 2, 2);
        setButton(constraint);
    }
    
    private void setTextField() {
        this.text_field.setText("0");
        this.text_field.setFont(this.font);
        this.text_field.setHorizontalAlignment(SwingConstants.RIGHT);
        this.text_field.setFocusable(false);
    }
    
    private void setButton(GridBagConstraints constraint) {
        int row = 0, col = 0;
        for (String i : text_button) {
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
            if (col % 4 == 0) {
                col = 0;
                row++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String text = this.map_button.get(clickedButton);
        
        if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
            if (!currentInput.isEmpty()) {
                calc.addToQueueValue(Integer.parseInt(currentInput)); // Tambahkan angka yang sedang diketik ke antrean
                currentInput = ""; // Reset input setelah operator
            }
            calc.addToQueueOpr(text); // Tambahkan operator ke antrean
            log.append(" " + text + " "); // Tampilkan operator di log
        } else if (text.equals("=")) {
            if (!currentInput.isEmpty()) {
                calc.addToQueueValue(Integer.parseInt(currentInput)); // Tambahkan angka terakhir ke antrean
            }
            calculation();
        } else if (text.equals("C")) {
            calc.clearQueue();
            this.text_field.setText("0");
            currentInput = ""; // Reset input saat "C" ditekan
            log.setText(""); // Kosongkan log
        } else {
            currentInput += text; // Gabungkan angka yang diketik
            this.text_field.setText(currentInput); // Tampilkan angka yang sedang diketik
            log.append(text); // Tampilkan angka di log
        }
    }
}
