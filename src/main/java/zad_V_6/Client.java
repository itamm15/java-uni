package zad_V_6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;

    /* Konstruktor próbuje połączyć się z serwerem */
    Client() {
        try {
            socket = new Socket("localhost", 2020);
            System.out.println("Klient połączony");
        } 
        catch (IOException e) {
            System.out.println("Uruchom serwer");
            System.exit(1);
        }
    }

    void dzialaj() throws Exception {
        // 1. Otworzenie strumienia (wejściowego lub wyjściowego) i
        // skojarzenie go z gniazdem
        
        Scanner klawiatura = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(
                                new OutputStreamWriter(socket.getOutputStream()),
                                    true);

        System.out.println(in.readLine());
        /* 2. Działanie klienta */
        while(true){
            if (socket.isClosed()) break;
            String line = klawiatura.nextLine();
            if(line.equalsIgnoreCase("q")){
                System.out.println("Koniec pracy klienta");
                break;
            }

            out.println(line);
            System.out.println(in.readLine());
        }
        
        /* 3. Czynności po zakończeniu współpracy z serwerem */
        klawiatura.close();
        out.close();
        in.close();
        socket.close();
    }//koniec funkcji dzialaj()

    public static void main(String args[]) throws Exception {
        Client client = new Client();
        client.dzialaj();
    }
}
