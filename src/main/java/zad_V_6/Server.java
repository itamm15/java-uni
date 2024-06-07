package zad_V_6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    public static final int serverPort = 2020;
    private int wylosowanLiczba;
    ServerSocket s;

    /* Konstruktor próbuje utworzyć gniazdo */
    Server() {
        try {
            s = new ServerSocket(serverPort);
            Random random = new Random();
            wylosowanLiczba = random.nextInt(101);
            System.out.println(wylosowanLiczba);
            System.out.println("Serwer uruchomiony");
        } catch (Exception e) {
            System.out.println("Nie można utworzyć gniazda");
            System.exit(1);
        }
    }

    void dzialaj() {
        Socket socket;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            /* Czekaj, aż klient się połączy */
            socket = s.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()),
                    true);

            /* 2. Działanie klienta */
            out.println("Zgadnij liczbę!");
            String line;
            while (true) {
                line = in.readLine();
                if (line == null) {
                    System.out.println("Koniec pracy serwera");
                    break;
                }


                try {
                    if (Integer.parseInt(line) > wylosowanLiczba) {
                        out.println("Za dużo!");
                    } else if (Integer.parseInt(line) < wylosowanLiczba ){
                        out.println("Za mało!");
                    } else if (Integer.parseInt(line) == wylosowanLiczba ) {
                        out.println("Zgadłes!");
                        socket.close();
                    }
                } catch (Exception e) {
                    out.println("Cos poszło nie tak podczas odczytywania liczbym spróbuj ponowanie!");
                }
            }            

        } catch (Exception e) {
            System.out.println("Problem w komunikacji z klientem");
        } finally {
            try {/* zamykanie połączenia */
                in.close();
                out.close();
                s.close();
            } catch (Exception e) {
                System.out.println("Problem z zamykaniem połączenia");
            }
        }
    }//koniec funkcji dzialaj()

    public static void main(String args[]) {
        Server server = new Server();
        server.dzialaj();        
    }
}
