package zadanie_II_12;

import javax.swing.*;
import java.awt.*;

public class KalkulatorBiznesowy extends JFrame {
    private JLabel vatRatesLabel, netAmountLabel, grossAmountLabel, taxAmountLabel, taxAmountResultLabel;
    private JRadioButton firstVatRate, secondVatRate, thirdVatRate, fourthVatRate;
    private ButtonGroup menuItem;
    private JTextField netAmount, grossAmount;
    private JButton calculateNetButton, calculateGrossButton;

    public KalkulatorBiznesowy() { super("Kalkulator bizensowy"); }

    public void init() {
        setSize(500, 150);
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
        grossAmount = new JTextField("");
        calculateNetButton = new JButton("OBLICZ");
        calculateGrossButton = new JButton("OBLICZ");

        firstVatRate.setText("0%");
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

        gridPanel.add(vatRatesLabel);
        gridPanel.add(netAmount);
        gridPanel.add(calculateNetButton);
        gridPanel.add(taxAmountLabel);
        gridPanel.add(taxAmountResultLabel);
        gridPanel.add(new JLabel(""));
        gridPanel.add(grossAmountLabel);
        gridPanel.add(grossAmount);
        gridPanel.add(calculateGrossButton);

        mainPanel.add(flowPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        KalkulatorBiznesowy kalkulatorBiznesowy = new KalkulatorBiznesowy();
        kalkulatorBiznesowy.init();
        kalkulatorBiznesowy.setVisible(true);
    }
}
