package zadanie_II_16;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

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
     
     
     // dane do testów
        m_abonenci.add(new Abonent(new String[]{"Jan", "Kowalski","111","222","333","444","555"}));
        m_abonenci.add(new Abonent(new String[]{"Agata", "Kowalska","1111","2222","3333","4444","5555"}));
        m_lista.setListData(m_abonenci.toArray());

        JScrollPane scrollPane = new JScrollPane(m_lista);
        TitledBorder border = new TitledBorder("Dane abonenta");
        border.setTitleColor(Color.RED);
        setBorder(border);

        add(scrollPane, BorderLayout.CENTER);
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

class Centrala extends JFrame implements ActionListener {

    // kontener z listą abonentów
    private ArrayList<Abonent> m_abonenci = new ArrayList<>();
    private OknoAbonenta m_oknoAbonenta = new OknoAbonenta(this);
    private ListaAbonentow m_listaAbonentow = new ListaAbonentow();

    private JMenu program, operacje;
    private JMenuBar menuBar;

    private JMenuItem zamknij, dodaj, edytuj, usun;
    
    public Centrala()
    {
        super("Lista abonentow");        

        JPanel glowny = new JPanel(new GridLayout(1,1,5,5));

        // do napisania (2)
        /* zbudowanie odpowiedniego menu */

        menuBar = new JMenuBar();
        program = new JMenu("Program");
        operacje = new JMenu("Operacje");

        zamknij = new JMenuItem("Zamknij");
        zamknij.addActionListener(this);
        dodaj = new JMenuItem("Dodaj");
        dodaj.addActionListener(this);
        edytuj = new JMenuItem("Edytuj");
        edytuj.addActionListener(this);
        usun = new JMenuItem("Usun");
        usun.addActionListener(this);

        program.add(zamknij);
        operacje.add(dodaj);
        operacje.add(edytuj);
        operacje.add(usun);

        menuBar.add(program);
        menuBar.add(operacje);

        glowny.add(m_listaAbonentow, BorderLayout.CENTER);
        add(glowny);

        setJMenuBar(menuBar);
        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

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
        // do napisania obsługa menu:
        // polecenia Dodaj Abonenta (3)
        // polecenia Zamknij (8)
        // polecenia Edytuj (5)
        // polecenia Usun (7.1) oraz (7.2) implementacja metody usun() z klasy ListaAbonentow
    }

    public ListaAbonentow getListaAbonentow() {
        return m_listaAbonentow;
    }    
    
    public static void main(String[] args)
    {
        new Centrala();
    }        
    
}