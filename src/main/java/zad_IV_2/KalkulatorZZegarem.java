package zad_IV_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

class Zegar extends JFrame implements Runnable {
	private JTextField dla_zegara;

	public Zegar(JTextField dla_zegara) {
		this.dla_zegara = dla_zegara;
	}

	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			dla_zegara.setText(date.toString());
		}
	}
}

class Kalkulator extends JFrame implements ActionListener {

	private JTextField arg1, arg2, dla_zegara;
	private JButton b1, b2, b3;
	private JLabel wynik;

	Kalkulator(String tytul) { super(tytul); }
	
	void init() {
		setSize(600, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		setLayout(new GridLayout(4,3));
		
		arg1 = new JTextField(10);
		arg1.setHorizontalAlignment(JTextField.CENTER);
				
		arg2 = new JTextField(10);
		arg2.setHorizontalAlignment(JTextField.CENTER);				

		b1 = new JButton("+");		
		b1.addActionListener(this);
		
		b2 = new JButton("-");
		b2.addActionListener(this);
		
		b3 = new JButton("*");		
		b3.addActionListener(this);
		
		wynik = new JLabel("???",JLabel.CENTER);
		wynik.setForeground(Color.red);
		
		// definuje pole, w kt�rym umie�cimy zegar
                dla_zegara = new JTextField(10);
		dla_zegara.setHorizontalAlignment(JTextField.CENTER);
                dla_zegara.setFocusable(false);
                dla_zegara.setBackground(Color.yellow);
		add(dla_zegara);
		
                add(new JPanel());
                add(new JPanel());

		add(new JLabel("Operand 1: "));
		add(new JLabel("Operand 2: "));
		add(new JLabel("Wynik operacji: ",JLabel.CENTER));
		add( arg1 );
		add( arg2 );
		add( wynik );
		add(b1);
		add(b2);
		add(b3);

        // tutaj utw�rz obiekt zegara i uruchom w�tek
        // (wcze�niej trzeba zbudowa� klas� Zegar wg tre�ci zadania)
		Zegar zegar = new Zegar(dla_zegara);
		Thread thread = new Thread(zegar);

		thread.start();
	}
	
	public void actionPerformed(ActionEvent zdarzenie) {
	// uwaga: w tym kodzie nie obs�u�y�em wyj�tk�w
        // (np. brak danych lub ich niew�a�ciwy format)
            
            double wynik=0, liczba1, liczba2;
		
	// tu nast�puje niejawna konwersja "odpakowuj�ca"
        // z typu String poprzez Double do double
            liczba1 = Double.valueOf(arg1.getText());
            liczba2 = Double.valueOf(arg2.getText());
	
            Object zrodlo = zdarzenie.getSource();
            if(zrodlo == b1) { wynik = liczba1+liczba2; }
            else
                if(zrodlo == b2) { wynik = liczba1-liczba2; }
		else
                    if(zrodlo == b3) { wynik = liczba1*liczba2; }
		
	// tu konwersja z double do String i podanie do etykiety z wynikiem operacji		
	//this.wynik.setText(Double.toString(wynik));
	// mo�na pro�ciej, tak jak poni�ej
            this.wynik.setText(""+wynik);
	}	
}


class KalkulatorZZegarem {

	public static void main(String[] args) {
		Kalkulator okno = new Kalkulator("Kalkulator z zegarkiem");
		okno.init(); //inicjujemy				
		okno.setVisible(true);
	}
}