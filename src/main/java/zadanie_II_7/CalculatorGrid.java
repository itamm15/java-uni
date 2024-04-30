package zadanie_II_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGrid extends JFrame implements ActionListener {
    private JLabel label1, label2, label3, resultLabel;
    private JTextField firstNumber, secondNumber;
    private JButton addButton, subtractButton, multiplyButton;

    public CalculatorGrid() {
        super("Pierwszy kalkulator");
    }

    public void init() {
        setSize(550, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // components
        label1 = new JLabel("Operand 1:");
        label2 = new JLabel("Operand 2:");
        label3 = new JLabel("Wynik operacji:", SwingConstants.CENTER);
        resultLabel = new JLabel("", SwingConstants.CENTER);

        firstNumber = new JTextField(10);
        secondNumber = new JTextField(10);

        addButton = new JButton(" + ");
        subtractButton = new JButton(" - ");
        multiplyButton = new JButton(" * ");

        // Labels
        add(label1);
        add(label2);
        add(label3);

        // inputs
        add(firstNumber);
        add(secondNumber);
        add(resultLabel);

        // operation buttons
        add(addButton);
        addButton.addActionListener(this);
        add(subtractButton);
        subtractButton.addActionListener(this);
        add(multiplyButton);
        multiplyButton.addActionListener(this);
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

            resultLabel.setText(Double.toString(result));
        } catch (Exception exception) {
            resultLabel.setText("BŁĄD DANYCH");
        }
    }

    public static void main(String[] args) {
        CalculatorGrid calculatorGrid = new CalculatorGrid();
        calculatorGrid.init();
        calculatorGrid.setVisible(true);
    }
}
