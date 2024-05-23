package zad_III_10;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


class Notepad extends JFrame
{
  private String filename;
  private JMenuItem open = new JMenuItem("Open");
  private JMenuItem save = new JMenuItem("Save");
  private JMenuItem rename = new JMenuItem("Rename");
  private JMenuItem createEmptyFile = new JMenuItem("Create empty file");
  private JTextArea text = new JTextArea();
  private JScrollPane pScroll = new JScrollPane(
      text, 
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );
  
// -------------------------------------------
  // klasa obslugi przycisku open
  class OpenFile implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JFileChooser fc = new JFileChooser(".");
      int val = fc.showOpenDialog(null);
      if(val == JFileChooser.APPROVE_OPTION) 
      {
        filename = fc.getSelectedFile().getPath();
        readFile();
      }
    }
  }

// -------------------------------------------
  // klasa obslugi przycisku save
  class SaveFile implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      saveFile();
    }
  }

// -------------------------------------------


  class RenameFile implements ActionListener {
    public void actionPerformed(ActionEvent actionEvent) {
      renameFile();
    }
  }

  class CreateEmptyFile implements ActionListener {
    public void actionPerformed(ActionEvent actionEvent) {
      createNewEmptyFile();
    }
  }

  private void readFile()
  {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

      // wyczysc `textArea`
      text.setText("");

      String line;
      while (true) {
        line = reader.readLine();
        if (line == null) break;
        text.append(line + "\n");
      }

      reader.close();
    } catch (FileNotFoundException exception) {
      JOptionPane.showMessageDialog(this, "Nie udalo sie znalezc pliku!");
      System.out.println("Nie znaleziono pliku!");
    } catch (IOException exception) {
      JOptionPane.showMessageDialog(this, "Wystapil problem podczas odczytu z pliku!");
      System.out.println("Nie udalo sie odczytac pliku!");
    }
  }

  private void saveFile()
  {
    try {
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));

      String kontent = text.getText();
      writer.write(kontent);

      writer.close();
    } catch (FileNotFoundException exception) {
      JOptionPane.showMessageDialog(this, "Nie udalo sie znalezc pliku!");
      System.out.println("Nie znaleziono pliku!");
    } catch (IOException exception) {
      JOptionPane.showMessageDialog(this, "Wystapil problem podczas odczytu z pliku!");
      System.out.println("Nie udalo sie odczytac pliku!");
    }
  }

  private void renameFile() {
    try {
      String newFilename = JOptionPane.showInputDialog(this, "Zmien nazwe pliku");
      File file = new File(filename);
      File newFile = new File(newFilename);

      file.renameTo(newFile);
    } catch (Exception exception) {
      System.out.println("Oupsi, something went wrong!");
    }
  }

  private void createNewEmptyFile() {
    try {
      String newFileName = JOptionPane.showInputDialog(this, "Podaj nowa nazwe pliku");

      // zapisz poprzedni plik
      saveFile();

      // utworz nowy plik
      new File(newFileName).createNewFile();

      // ustaw nowy plik aktualnym
      filename = newFileName;

      // wyczysc okno
      text.setText("");
    } catch (Exception exception) {
      System.out.println("Oupsi, something went wrong!");
    }
  }

// -------------------------------------------

  // konstruktor okna
  public Notepad()
  {
    super("Prosty notatnik");

    // tworzymy menu okna
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menu.add(open);
    menu.add(save);
    menu.add(rename);
    menu.add(createEmptyFile);
    menuBar.add(menu);
    this.setJMenuBar(menuBar);
    
    this.getContentPane().add(pScroll);
    text.setLineWrap(true);

    open.addActionListener(new OpenFile());
    save.addActionListener(new SaveFile());
    rename.addActionListener(new RenameFile());
    createEmptyFile.addActionListener(new CreateEmptyFile());

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500,500);
    setVisible(true);
  }
  
// -------------------------------------------

  public static void main(String[] args)
  {
    new Notepad();
  }
}
