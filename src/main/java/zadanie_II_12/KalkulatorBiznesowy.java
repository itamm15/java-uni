package zadanie_II_12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Enumeration;

public class KalkulatorBiznesowy extends JFrame implements ActionListener {
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private JLabel vatRatesLabel, netAmountLabel, grossAmountLabel, taxAmountLabel, taxAmountResultLabel;
    private JRadioButton firstVatRate, secondVatRate, thirdVatRate, fourthVatRate;
    private ButtonGroup menuItem;
    private JTextField netAmount, grossAmount;
    private JButton calculateNetButton, calculateGrossButton;

    public KalkulatorBiznesowy() { super("Kalkulator bizensowy"); }

    public void init() {
        setSize(400, 150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel flowPanel = new JPanel(new FlowLayout());
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));

        // setup components
        vatRatesLabel = new JLabel("stawki VAT");
        netAmountLabel = new JLabel("Wartosc netto");
        grossAmountLabel = new JLabel("Wartosc brutto");
        taxAmountLabel = new JLabel("Wartosc podatku");
        taxAmountResultLabel = new JLabel("");
        menuItem = new ButtonGroup();
        firstVatRate = new JRadioButton();
        secondVatRate = new JRadioButton();
        thirdVatRate = new JRadioButton();
        fourthVatRate = new JRadioButton();
        netAmount = new JTextField("");
        netAmount.setHorizontalAlignment(JTextField.RIGHT);
        grossAmount = new JTextField("");
        grossAmount.setHorizontalAlignment(JTextField.RIGHT);
        calculateNetButton = new JButton("OBLICZ");
        calculateNetButton.setForeground(new Color(255, 0, 0));
        calculateGrossButton = new JButton("OBLICZ");
        calculateGrossButton.setForeground(new Color(0, 0, 255));

        firstVatRate.setText("0%");
        firstVatRate.setSelected(true);
        firstVatRate.putClientProperty("value", 0);
        secondVatRate.setText("5%");
        secondVatRate.putClientProperty("value", 5);
        thirdVatRate.setText("8%");
        thirdVatRate.putClientProperty("value", 8);
        fourthVatRate.setText("23%");
        fourthVatRate.putClientProperty("value", 23);

        menuItem.add(firstVatRate);
        menuItem.add(secondVatRate);
        menuItem.add(thirdVatRate);
        menuItem.add(fourthVatRate);

        flowPanel.add(vatRatesLabel);
        flowPanel.add(firstVatRate);
        flowPanel.add(secondVatRate);
        flowPanel.add(thirdVatRate);
        flowPanel.add(fourthVatRate);
        flowPanel.setBackground(new Color(128, 128, 128));

        gridPanel.add(netAmountLabel);
        gridPanel.add(netAmount);
        gridPanel.add(calculateNetButton);
        calculateNetButton.addActionListener(this);

        gridPanel.add(taxAmountLabel);
        JPanel floatedRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        floatedRight.add(taxAmountResultLabel);
        gridPanel.add(floatedRight);
        gridPanel.add(new JLabel(""));

        gridPanel.add(grossAmountLabel);
        gridPanel.add(grossAmount);
        gridPanel.add(calculateGrossButton);
        calculateGrossButton.addActionListener(this);

        mainPanel.add(flowPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }
    
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Object source = actionEvent.getSource();
            if (source == calculateNetButton) calculateNet();
            if (source == calculateGrossButton) calculateGross();
        } catch (Exception exception) {
            // add error to the `tax field`
            taxAmountResultLabel.setText("Podane wartosci sa niepoprawne");
        }
    }

    private void calculateNet() {
        AbstractButton abstractButton = getSelectedTaxRate();
        int vatRate = (int) abstractButton.getClientProperty("value");
        double gross = Double.parseDouble(grossAmount.getText());
        double tax = gross * vatRate / 100;
        double net = gross - tax;

        netAmount.setText(decimalFormat.format(net));
        taxAmountResultLabel.setText(decimalFormat.format(tax));
    }

    private void calculateGross() {
        AbstractButton abstractButton = getSelectedTaxRate();
        int vatRate = (int) abstractButton.getClientProperty("value");
        double gross = Double.parseDouble(netAmount.getText());
        double tax = gross * vatRate / 100;
        double net = gross + tax;

        grossAmount.setText(decimalFormat.format(net));
        taxAmountResultLabel.setText(decimalFormat.format(tax));
    }

    private AbstractButton getSelectedTaxRate() {
        AbstractButton selectedRadioButton = new JRadioButton();
        for(Enumeration<AbstractButton> radioGroup = menuItem.getElements(); radioGroup.hasMoreElements();) {
            AbstractButton radioButton = radioGroup.nextElement();

            if(radioButton.isSelected()) {
                selectedRadioButton = radioButton;
                break;
            }
        }

        return selectedRadioButton;
    }

    public static void main(String[] args) {
        KalkulatorBiznesowy kalkulatorBiznesowy = new KalkulatorBiznesowy();
        kalkulatorBiznesowy.init();
        kalkulatorBiznesowy.setVisible(true);
    }
}
