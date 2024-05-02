package zadanie_I_33;

import java.util.Scanner;

public class Wektor {
    int wymiar;
    double wspolrzedne[];

    public Wektor(int wymiar, double[] wspolrzedne) {
        this.wymiar = wymiar;
        this.wspolrzedne = new double[wymiar]; // Arrays.copyOf(wspolrzedne, wspolrzedne.length);
        for(int i = 0; i<wspolrzedne.length; i++) {
            this.wspolrzedne[i] = wspolrzedne[i];
        }
    }

    public Wektor(double[] wspolrzedne) {
        this(wspolrzedne.length, wspolrzedne);
    }

    public Wektor(int wymiar) {
        this.wymiar = wymiar;
        wspolrzedne = new double[wymiar];
        Scanner scanner = new Scanner(System.in);
        for(int iterator = 0; iterator < wymiar; iterator++) {
            System.out.print("Pass the value for vector: " + iterator);

            wspolrzedne[iterator] = scanner.nextDouble();
        }
    }

    public double[] getWspolrzedne() {
        return this.wspolrzedne;
    }
}
