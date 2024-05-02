package zadanie_I_34;

import zadanie_I_33.Wektor;

public class Wektor2D extends Wektor {
    public Wektor2D(double[] wspolrzedne) {
        super(wspolrzedne);
    }

    public double dlugosc() {
        double[] wspolrzedne = this.getWspolrzedne();
        double x = wspolrzedne[0];
        double y = wspolrzedne[1];

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static void main(String args[]) {
        double[] wspolrzedne = {1, 2};
        Wektor2D wektor2D = new Wektor2D(wspolrzedne);
        double dlugosc = wektor2D.dlugosc();
        System.out.println("Dlugosc wektora wynosi: " + dlugosc);
    }
}
