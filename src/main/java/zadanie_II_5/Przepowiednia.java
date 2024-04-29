package zadanie_II_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Przepowiednia extends JFrame implements ActionListener {
    private JTextArea przepowiedniaArea;
    private JButton revealPrzepowiedniaButton, closePrzepowiedniaWindowButton;


    String przepowiednie[] = {
            "Zostaniesz programistą Javy, ale musisz się dużo uczyć",
            "Zostaniesz programistą Cobola, ale musisz się dużo uczyć",
            "Zostaniesz programistą Elixira, ale musisz się dużo uczyć",
            "Zostaniesz programistą Golanga, ale musisz się dużo uczyć",
            "Zostaniesz programistą C#, ale musisz się dużo uczyć",
            "Zostaniesz programistą Reacta, ale musisz się dużo uczyć"
    };

    public Przepowiednia(){
        super("Przepowiednia");
    }

    public void init() {
        setSize(600, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // init components
        przepowiedniaArea = new JTextArea(10, 40);
        revealPrzepowiedniaButton = new JButton("Podaj przepowiednię");
        closePrzepowiedniaWindowButton = new JButton("Zamknij");

        add(przepowiedniaArea);
        add(revealPrzepowiedniaButton);
        revealPrzepowiedniaButton.addActionListener(this);
        add(closePrzepowiedniaWindowButton);
        closePrzepowiedniaWindowButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == revealPrzepowiedniaButton) {
            // clean previous one
            przepowiedniaArea.setText("");

            // set new one
            String randomPrzepowiednia = getRandomPrzepowiednia();
            przepowiedniaArea.setText(randomPrzepowiednia);
            przepowiedniaArea.setBackground(getRandomColor());
        }

        if (source == closePrzepowiedniaWindowButton) {
            System.exit(0);
        }
    }

    public String getRandomPrzepowiednia() {
        int randomNumber = new Random().nextInt(przepowiednie.length);
        return przepowiednie[randomNumber];
    }

    public Color getRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        return new Color(r, g, b);
    }

    public static void main(String[] args) {
        Przepowiednia przepowiednia = new Przepowiednia();
        przepowiednia.init();
        przepowiednia.setVisible(true);
    }
}