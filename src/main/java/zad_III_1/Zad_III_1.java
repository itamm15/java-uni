package zad_III_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Zad_III_1 {
    public static void main(String[] args) {
        FileBrowser f = new FileBrowser();
        f.init();
        f.setVisible(true);
    }
}

class FileBrowser extends JFrame implements ActionListener{
    JTextField nazwa;
    JTextArea zawartosc;
    JButton wczytaj, zapisz, zmienKodowanie;

    String kodowanie = "UTF-8";

    public FileBrowser() { super("File Browser"); }
    
    public void init()
    {
        setSize(500, 450);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        nazwa = new JTextField(20);
        zawartosc = new JTextArea(20,40);
        zapisz = new JButton("Zapisz");
        zapisz.addActionListener(this);
        wczytaj = new JButton("Wczytaj");
        wczytaj.addActionListener(this);
        zmienKodowanie = new JButton("Zmień kodowanie");
        zmienKodowanie.addActionListener(this);
        
        add(nazwa);
        add(wczytaj);
        add(zapisz);
        add(zmienKodowanie);
        add(new JScrollPane(zawartosc));                       
    }
    
    private void czytaj(File plik)
    {
        try {
            if (plik.isDirectory()) {
                wyswietlPliki(plik);
            }
            else if(!plik.exists()|| !plik.isFile())
                zawartosc.setText(plik.getName()+" - taki plik nie istnieje");
            else
            {
                // jezeli jest puste, ustaw domyslne
                if (kodowanie == "") kodowanie = "UTF-8";
                BufferedReader in = new BufferedReader
                                            (new InputStreamReader
                                                    (new FileInputStream(plik), kodowanie));
                zawartosc.setText(null);
                String linia;                
                while(true)  {
                    linia = in.readLine();
                    if(linia == null) break;
                    zawartosc.append(linia + "\n");
                }
                in.close();                
            }
        } catch(IOException ex) { System.out.println(ex); }
        catch (Exception exception) {
            System.out.println(exception);
        }
        // Zachęcam do pracy z tym przykładem:
        // (1) można użyć klasy FileReader lub Scanner
        // (2) można użyć statycznej metody readAllLines() klasy Files z pakietu java.nio.file
    }
    
    private void zapisz(File plik)
    {
        try {
            if (plik.isDirectory()) {
                wyswietlPliki(plik);
            } else {
                BufferedWriter out = new BufferedWriter
                        (new OutputStreamWriter
                                (new FileOutputStream(plik)));
                out.write(zawartosc.getText());
                out.close();
            }
        }
        catch (FileNotFoundException e) { System.out.println("Nieprawidłowa nazwa dla pliku"); }  
        catch (IOException e) { System.out.println(e); }  
        // można poeksperymentować z innymi klasami strumieni tekstowych
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nazwaPliku = nazwa.getText();
        File plik = new File(nazwaPliku);
        if (e.getSource() == wczytaj)  czytaj(plik);
        if (e.getSource() == zapisz) zapisz(plik);
        if (e.getSource() == zmienKodowanie) {
            kodowanie = JOptionPane.showInputDialog("Podaj kodowanie");
        }
    }

    private void wyswietlPliki(File plik) {
        File[] files = plik.listFiles();

        zawartosc.setText("");
        for (File file : files) {
            zawartosc.append(file + "\n");
        }
    }
}
