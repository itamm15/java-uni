package zad_V_10;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class WatekKlienta extends Thread {
    private Socket socket;
    private static ArrayList<WatekKlienta> klienci = new ArrayList<>();

    private BufferedReader in = null;
    private PrintWriter out = null;

    public WatekKlienta(Socket socket) {
        this.socket = socket;
        System.out.println("Sędzia uruchomiony");
    }

    @Override
    public void run() {
        klienci.add(this);
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            while (true) {
                System.out.println("Podaj liczbę w przedziale od 0-20");
                Scanner scanner = new Scanner(System.in);
                int podanaLiczba = scanner.nextInt();

                if (podanaLiczba >= 0 && podanaLiczba <= 20) {
                    out.println(podanaLiczba);
                    System.out.println("Ocena zaakceptowana. Teraz musisz czekać na oceny innych sędziów..");
                    String podsumowanie = in.readLine();
                    System.out.println(podsumowanie);
                    System.exit(0);
                } else {
                    System.out.println("Podana liczba nie mieści się w przedziale 0-20. Spróbuj ponownie.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            klienci.remove(this);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<WatekKlienta> getKlienci() {
        return klienci;
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2020);
            WatekKlienta watekKlienta = new WatekKlienta(socket);
            watekKlienta.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static void setKlienci(ArrayList<WatekKlienta> klienci) {
        WatekKlienta.klienci = klienci;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
}
