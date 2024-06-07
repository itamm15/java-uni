package zad_V_10;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static final int serverPort = 2020;
    public ServerSocket serverSocket;

    public static double avg = 0;
    public static int liczbaOcen = 0;

    public Server() {
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Serwer uruchomiony");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runForClient() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Sędzia się połączył, jest ich " + (WatekKlienta.getKlienci().size() + 1));
                new WatekKlienta(socket).start();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                String line = in.readLine();
                avg += Integer.parseInt(line);
                liczbaOcen++;
                System.out.println("Liczba ocen = " + liczbaOcen);
                if (liczbaOcen == 3) {
                    ArrayList<WatekKlienta> klienci = WatekKlienta.getKlienci();

                    for (WatekKlienta klient : klienci) {
                        klient.getOut().println("Mamy wyniki! Srednia punktow wynosi " + avg/3);
                    }
                    System.out.println("Mamy wyniki! Srednia punktow wynosi " + avg/3);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Server().runForClient();
    }
}
