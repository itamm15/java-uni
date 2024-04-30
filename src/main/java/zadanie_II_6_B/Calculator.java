package zadanie_II_6_B;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JLabel label1, label2, label3, label4;
    private JTextField firstNumber, secondNumber;
    private JButton addButton, subtractButton, multiplyButton;

    public Calculator() {
        super("My first calculator");
    }

    public void init() {
        setSize(550, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // components
        label1 = new JLabel("Operand 1:");
        label2 = new JLabel("Operand 2:");
        label3 = new JLabel("Przyciski operacji:");
        label4 = new JLabel("Wynik operacji: ???");

        firstNumber = new JTextField(10);
        secondNumber = new JTextField(10);

        addButton = new JButton(" + ");
        subtractButton = new JButton(" - ");
        multiplyButton = new JButton(" * ");

        add(label1);
        add(firstNumber);
        add(label2);
        add(secondNumber);
        add(label3);
        add(addButton);
        addButton.addActionListener(this);
        add(subtractButton);
        subtractButton.addActionListener(this);
        add(multiplyButton);
        multiplyButton.addActionListener(this);
        add(label4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double result = 0;
            double firstInput = Double.parseDouble(firstNumber.getText());
            double secondInput = Double.parseDouble(secondNumber.getText());

            Object source = e.getSource();
            if (source == addButton) result = firstInput + secondInput;
            if (source == subtractButton) result = firstInput - secondInput;
            if (source == multiplyButton) result = firstInput * secondInput;

            label4.setText("Wynik operacji: " + result);
        } catch (Exception exception) {
            label4.setText("Wynik operacji: BŁĄD DANYCH");
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.init();
        calculator.setVisible(true);
    }
}
