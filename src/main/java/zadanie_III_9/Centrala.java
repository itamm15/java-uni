package zadanie_III_9;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class PlikAbonentow {
    private final File plikAbonenci = new File("abonenci");

    public PlikAbonentow() {};

    public File getPlikAbonentow() {
        return this.plikAbonenci;
    }
}

// klasa reprezentujaca jednego abonenta
class Abonent {

    public final static String[] ETYKIETY = {
            "Imie",
            "Nazwisko",
            "Numer telefonu",
            "Ilosc darmowych minut",
            "Oplata abonentowa",
            "Cena za minute w sieci",
            "Cena za minute poza siecia"
    };
    private HashMap<String,String> m_data = new HashMap<String,String>();

    public Abonent(String[] tablicaDanych) {
        for(int i=0;i<ETYKIETY.length;i++)
            m_data.put(ETYKIETY[i],tablicaDanych[i]);
    }

    // Zapisz dane w postaci CSV
    public String getWszystkieDaneDoPliku() {
        String dane = "";
        for(int i=0;i<Abonent.ETYKIETY.length;i++) {
            dane += this.get(i);
            // nie dodowaj `;` na ostatniej pozycji
            if (i != Abonent.ETYKIETY.length - 1) dane += ";";
        }

        return dane;
    }

    // funkcja zwracajaca jedna z wlasciwosci abonenta
    public String get(int numerPola) {
        return m_data.get(ETYKIETY[numerPola]);
    }

    // funkcja uaktualniajaca jedna z wlasciwosci abonenta
    public void set(int numerPola,String a_nowaWartosc) {
        m_data.put(ETYKIETY[numerPola],a_nowaWartosc);
    }

    public String toString() {
        return get(0) + " " + get(1);
    }
}

// --------------------------------------------------------------------

// klasa panelu wyswietlania/wprowadzania nowego abonenta
// panel umieścimy w oknie klasy OknoAbonenta
class PanelAbonenta extends JPanel {

    // tablica pól tekstowych do edycji danych abonenta
    private JTextField[] m_pola = new JTextField[Abonent.ETYKIETY.length];

    public PanelAbonenta(Centrala c) {
        // ustawia layout, siatka liczbaPól x 2 z odstepami po 5 w pionie i poziomie
        setLayout(new GridLayout(Abonent.ETYKIETY.length,2,5,5));

        // tworzy i dodaje do panelu pola tekstowe
        for(int i=0;i<Abonent.ETYKIETY.length;i++) {
            add(new JLabel(Abonent.ETYKIETY[i]));
            m_pola[i] = new JTextField();
            add(m_pola[i]);
        }

        // definiuje obramowanie
        TitledBorder border = new TitledBorder("Dane abonenta");
        border.setTitleColor(Color.RED);
        setBorder(border);
    }

    public String getPole(int i) { return m_pola[i].getText(); }
    public void setPole(int i, String value) { m_pola[i].setText(value); }
}
// --------------------------------------------------------------------

// klasa reprezentująca panel wyswietlający listę abonentow
class ListaAbonentow extends JPanel
{
    // kontener z listą abonentów
    private ArrayList<Abonent> m_abonenci = new ArrayList<Abonent>();
    // obiekt widoku listy
    private JList m_lista = new JList();

    public ListaAbonentow() {

        setLayout(new BorderLayout());

        // do napisania (1)
        // wyposażenie listy w obramowanie i możliwość scrollowania
        // i umieszczenie jej w panelu


        // Odczytaj dane tutaj
        odczytajAbonentowZPliku();

        JScrollPane scrollPane = new JScrollPane(m_lista);
        TitledBorder border = new TitledBorder("Dane abonenta");
        border.setTitleColor(Color.RED);
        setBorder(border);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void odczytajAbonentowZPliku() {
        try {
            File plikAbonenci = new PlikAbonentow().getPlikAbonentow();

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(plikAbonenci)));
            ArrayList<Abonent> abonenci = new ArrayList<Abonent>();
            String linia;
            while (true) {
                linia = reader.readLine();
                if (linia == null) break;
                String[] dane = linia.split(";", -1);
                Abonent abonent = new Abonent(dane);
                abonenci.add(abonent);
            }

            reader.close();
            m_abonenci.addAll(abonenci);
            m_lista.setListData(abonenci.toArray());
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(this, "Nie udalo sie znalezc pliku i wczytac abonentow!");
            System.out.println("Nie udalo sie znalezc pliku!");
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Ups, cos poszlo nie tak przy pobieraniu danych!");
            System.out.println("Nie udalo sie znalezc pliku!");
        }
    }

    public void zapiszZmiany(String[] dane, boolean isEditing) {
        try {
            if (isEditing) {
                int selectedIndex = m_lista.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(this,"Nie znaleziona abonenta do edycji!");
                    throw new Exception("Nie zaznaczono abonenta do edycji!");
                } else {
                    Abonent abonent = m_abonenci.get(selectedIndex);
                    for(int i=0;i<dane.length;i++) {
                        abonent.set(i, dane[i]);
                    }
                }
            } else {
                m_abonenci.add(new Abonent(dane));
                m_lista.setListData(m_abonenci.toArray());
            }
        } catch (Exception exception) {
            System.out.println("Cos poszlo nie tak! " + exception);
        }

        // do napisania (6.2)
        /*
         * W zależności od wybranej pozycji w liście
         * aktualizacja lub dodanie elementu do kolekcji związanej z listą.
         * Odświeżenie widoku listy (w tym wybranego elementu)
         * Należy wykorzystać metody klas ArrayList oraz Abonent.
         *
         */

    }

    public void usun(int i) {
        m_abonenci.remove(i);
        m_lista.setListData(m_abonenci.toArray());
        // do napisania (7.2)
        /*
         * Usuwa z kolekcji i odświeża listę (w tym zaznaczenie elementu)
         */

    }

    public void ustawListeDanych(ArrayList<Abonent> abonenci) {
        m_abonenci = abonenci;
        m_lista.setListData(abonenci.toArray());
    }

    // zwraca indeks elementu wybranego w liście
    public int getIndeksAbonenta() { return m_lista.getSelectedIndex(); }

    public void setIndeksAbonenta(int i) {
        if(i >= 0) m_lista.setSelectedIndex(i);
        else m_lista.clearSelection();
    }

    public Abonent getAbonent() {
        try {
            return m_abonenci.get(m_lista.getSelectedIndex());
        } catch (Exception exception) {
            System.out.println("Nie udalo sie znalezc abonenta!");
        }

        return null;
    }

    public ArrayList<Abonent> getM_abonenci() {
        return m_abonenci;
    }
}
// --------------------------------------------------------------------
// klasa reprezentująca okno dialogowe
// wewnątrz którego będzie panel edycji abonenta
class OknoAbonenta extends JDialog implements ActionListener {

    private Centrala centrala;
    private PanelAbonenta panel;
    private JButton zatwierdz;

    private boolean isEditing = false;

    public OknoAbonenta(Centrala c) {
        centrala = c;

        setSize(400, 320);
        setLocation(50, 100);
        setLayout(new FlowLayout());
        setModal(true);

        /* do napisania (4):
         * wstawić panel abonenta i przycisk "Zatwierdź"
         */

        panel = new PanelAbonenta(centrala);
        zatwierdz = new JButton("Zatwierdź");
        zatwierdz.addActionListener(this);
        add(panel);
        add(zatwierdz);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == zatwierdz) {
            zapiszZmiany();
            setVisible(false);
        }
        // póki co tylko obsługa zatwierdzenia zmian
        // (reakcja na wcisnięcie przycisku "Zatwierdź")
        /*
        do napisania obsługa zatwierdzenia zmian (6.1)
        */

    }

    // zapisuje wartości z panelu do listy abonentów
    public void zapiszZmiany() {
        String[] dane = new String[Abonent.ETYKIETY.length];
        for(int i=0; i<dane.length; i++) dane[i] = panel.getPole(i);
        ListaAbonentow abonenci = centrala.getListaAbonentow();
        abonenci.zapiszZmiany(dane, isEditing);
    }

    // ustawia w polach panelu dane podanego abonenta
    public void setEdytowany(Abonent abonent) {
        for(int i=0; i<Abonent.ETYKIETY.length; i++) panel.setPole(i,abonent.get(i));
        isEditing = true;
    }

    public void setDodawnieAbonenta() {
        for(int i=0; i<Abonent.ETYKIETY.length; i++) panel.setPole(i, "");
        isEditing = false;
    }
}

class Centrala extends JFrame implements ActionListener, KeyListener {
    File plikAbonenci = new PlikAbonentow().getPlikAbonentow();

    // kontener z listą abonentów
    private ArrayList<Abonent> m_abonenci = new ArrayList<>();
    private OknoAbonenta m_oknoAbonenta = new OknoAbonenta(this);
    private ListaAbonentow m_listaAbonentow = new ListaAbonentow();

    private JMenu program, operacje, dane;
    private JMenuBar menuBar;

    private JMenuItem zamknij, dodaj, edytuj, usun, zapiszListeAbonentow, odtworzListeAbonentow;

    public static void main(String[] args) {
        Centrala centrala = new Centrala();
        centrala.addKeyListener(centrala);
    }

    public Centrala()
    {
        super("Lista abonentow");

        JPanel glowny = new JPanel(new GridLayout(1,1,5,5));

        // do napisania (2)
        /* zbudowanie odpowiedniego menu */

        menuBar = new JMenuBar();
        program = new JMenu("Program");
        operacje = new JMenu("Operacje");
        dane = new JMenu("Dane");

        zamknij = new JMenuItem("Zamknij");
        zamknij.addActionListener(this);
        dodaj = new JMenuItem("Dodaj");
        dodaj.addActionListener(this);
        edytuj = new JMenuItem("Edytuj");
        edytuj.addActionListener(this);
        usun = new JMenuItem("Usun");
        usun.addActionListener(this);

        zapiszListeAbonentow = new JMenuItem("Zapisz liste abonentow");
        zapiszListeAbonentow.addActionListener(this);
        odtworzListeAbonentow = new JMenuItem("Odtworz liste abonentow");
        odtworzListeAbonentow.addActionListener(this);

        program.add(zamknij);

        operacje.add(dodaj);
        operacje.add(edytuj);
        operacje.add(usun);

        dane.add(zapiszListeAbonentow);
        dane.add(odtworzListeAbonentow);

        menuBar.add(program);
        menuBar.add(operacje);
        menuBar.add(dane);

        glowny.add(m_listaAbonentow, BorderLayout.CENTER);
        add(glowny);

        setJMenuBar(menuBar);
        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setFocusable(true);

        // JVM'owy hook, ktory zapisze dane do pliku `onClose` aplikacji
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            zapiszDoPlikuAbonentow();
        }));
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R) {
            odtworzAbonentowZPliku();
        }

        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W) {
            zapiszDoPlikuAbonentow();
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == zamknij) System.exit(0);
        if (source == dodaj) {
            m_oknoAbonenta.setDodawnieAbonenta();
            m_oknoAbonenta.setVisible(true);
        }
        if (source == edytuj) {
            try {
                Abonent abonent = m_listaAbonentow.getAbonent();
                m_oknoAbonenta.setEdytowany(abonent);
                m_oknoAbonenta.setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this,"Nie znaleziona abonenta do edycji!");
                System.out.println("Nie udalo sie znalezc abonenta!");
            }
        }
        if (source == usun) {
            m_listaAbonentow.usun(m_listaAbonentow.getIndeksAbonenta());
        }
        if (source == zapiszListeAbonentow) {
            zapiszDoPlikuAbonentow();
        }

        if (source == odtworzListeAbonentow) {
            odtworzAbonentowZPliku();
        }
        // do napisania obsługa menu:
        // polecenia Dodaj Abonenta (3)
        // polecenia Zamknij (8)
        // polecenia Edytuj (5)
        // polecenia Usun (7.1) oraz (7.2) implementacja metody usun() z klasy ListaAbonentow
    }

    public ListaAbonentow getListaAbonentow() {
        return m_listaAbonentow;
    }

    private void zapiszDoPlikuAbonentow() {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(plikAbonenci)));
            for(Abonent abonent : m_listaAbonentow.getM_abonenci()) {
                String dane = abonent.getWszystkieDaneDoPliku();
                writer.write(dane + "\n");
            }

            writer.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void odtworzAbonentowZPliku() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(plikAbonenci)));
            ArrayList<Abonent> abonenci = new ArrayList<Abonent>();
            String linia;
            while (true) {
                linia = reader.readLine();
                if (linia == null) break;
                String[] dane = linia.split(";", -1);
                abonenci.add(new Abonent(dane));
            }

            reader.close();

            m_abonenci.clear();
            m_abonenci.addAll(abonenci);
            m_listaAbonentow.ustawListeDanych(m_abonenci);
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(this,"Nie udalo sie znalezc pliku!");
            System.out.println("Nie udalo sie znalezc pliku!");
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this,"Nie udalo sie odczytac pliku!");
            System.out.println("Nie udalo sie odczytac pliku!");
        }
    }
}