package zadanie_II_10_B;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MiniSlownik extends JFrame implements ActionListener {
    private JLabel inEnglish, inPolish;
    private JTextField englishWord, polishWord;
    private JButton nextWord;

    ArrayList<ParaSlow> translatedWords = new ArrayList<ParaSlow>();

    public class ParaSlow {
        private String polishWord;
        private String englishWord;

        public ParaSlow(String polishWord, String englishWord) {
            this.polishWord = polishWord;
            this.englishWord = englishWord;
        }

        public String getPolishWord() {
            return this.polishWord;
        }

        public String getEnglishWord() {
            return this.englishWord;
        }
    }

    public MiniSlownik() {super("Prezentacja słówek po angielsku");}

    public void init() {
        // fill ArrayList
        translatedWords.add(new ParaSlow("kot", "cat"));
        translatedWords.add(new ParaSlow("pies", "dog"));
        translatedWords.add(new ParaSlow("tygrys", "tiger"));
        translatedWords.add(new ParaSlow("słoń", "elephant"));
        translatedWords.add(new ParaSlow("ratel", "honey badger"));
        translatedWords.add(new ParaSlow("rekin", "shark"));

        // Set design
        setSize(500, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // components
        inEnglish = new JLabel("Po angielsku");
        inPolish = new JLabel("Po polsku");

        englishWord = new JTextField(10);
        polishWord = new JTextField(10);

        nextWord = new JButton("Pokaż następne słówko");

        add(inEnglish);
        add(englishWord);
        add(inPolish);
        add(polishWord);
        add(nextWord);
        nextWord.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ParaSlow paraSlow = getRandomParaSlow();

        englishWord.setText(paraSlow.getEnglishWord());
        polishWord.setText(paraSlow.getPolishWord());
    }

    private ParaSlow getRandomParaSlow() {
        int randomNumber = new Random().nextInt(translatedWords.toArray().length);
        return translatedWords.get(randomNumber);
    }

    public static void main(String[] args) {
        MiniSlownik miniSlownik = new MiniSlownik();
        miniSlownik.init();
        miniSlownik.setVisible(true);
    }
}
