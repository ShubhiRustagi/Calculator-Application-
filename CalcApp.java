import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalcApp extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public CalcApp() {
        // Frame setup
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        // Text field
        textField = new JTextField();
        textField.setBounds(30, 40, 330, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setEditable(false);
        add(textField);

        // Buttons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        int x = 30, y = 100;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(x, y, 80, 60);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            add(button);

            x += 85;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 70;
            }
        }

        // Clear button
        JButton clear = new JButton("C");
        clear.setBounds(30, y, 330, 60);
        clear.setFont(new Font("Arial", Font.BOLD, 20));
        clear.addActionListener(e -> textField.setText(""));
        add(clear);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            textField.setText(textField.getText() + command);
        } 
        else if (command.charAt(0) == '+' || command.charAt(0) == '-' ||
                 command.charAt(0) == '*' || command.charAt(0) == '/') {
            num1 = Double.parseDouble(textField.getText());
            operator = command.charAt(0);
            textField.setText("");
        } 
        else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 == 0) {
                        textField.setText("Error");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }
    }

    public static void main(String[] args) {
        new CalcApp();
    }
}
