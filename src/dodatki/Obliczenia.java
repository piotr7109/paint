package dodatki;

import java.awt.Point;
import java.util.ArrayList;

import dane.ZamowienieDane;
import modules.czesci.Czesc;
import modules.figury.Figura;

public class Obliczenia {

	public static int getDlugosc(Point a, Point b) {
		return (int) Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
	}

	/**
	 * Funnkcja liczy k�t pomi�dzy punktami a i c wyko�ystuj�c punkt �rodkowy b
	 * 
	 * @param a
	 *            - punkt start
	 * @param b
	 *            - punkt wsp�lny
	 * @param c
	 *            - punkt koniec
	 * @return k�t
	 */
	public static int getKat(Point a, Point b, Point c) {
		double kat1 = Math.atan2(c.y - b.y, c.x - b.x);
		double kat2 = Math.atan2(a.y - b.y, a.x - b.x);

		double kat;
		kat = Math.toDegrees(kat1 - kat2);
		if (kat < 0)
			kat = kat + 360;
		return (int) (kat);
	}

	public static int obliczDlugosc(Figura fig) {
		if (fig == null) {
			return 0;
		}

		int dlugosc = 0;

		for (Czesc czesc : fig.getCzesci()) {
			dlugosc += czesc.getDlugosc();
		}

		return dlugosc;
	}

	public static int obliczDlugosc(modules.zamowienie.elementy.figury.Figura fig) {
		int dlugosc = 0;

		for (modules.zamowienie.elementy.figury.czesci.Czesc czesc : fig.getCzesci()) {
			dlugosc += czesc.getDlugosc();
		}

		return dlugosc;
	}

	public static double obliczDlugoscRzeczywista(int index) {
		double dlugosc = 0;

		int size = ZamowienieDane.figury.get(index).figura.getCzesci().size();
		int sworzen = ZamowienieDane.figury.get(index).sworzen / 2;
		int srednica = ZamowienieDane.figury.get(index).srednica;

		ArrayList<Czesc> czesci = cloneCzesci(index);
		for (int i = 0; i < size - 1; i++) {
			Czesc czesc = czesci.get(i);
			Czesc czesc_n = czesci.get(i + 1);

			if (czesc.getTyp().equals("okrag") || czesc_n.getTyp().equals("okrag")) {
				dlugosc += czesc.getDlugosc();

			}
			else {
				int kat = czesc_n.getKat();
				kat = Math.abs(kat);

				double a = czesc.getDlugosc();
				double b = 2 * Math.PI * ((sworzen + srednica) / 10) * kat / 360 / 2;
				// int c = czesc_n.getDlugosc();
				dlugosc += (a - b);

			}
		}

		dlugosc += czesci.get(size - 1).getDlugosc();

		return dlugosc;
	}

	private static ArrayList<Czesc> cloneCzesci(int index) {
		ArrayList<Czesc> czesci = new ArrayList<Czesc>();
		int size = ZamowienieDane.figury.get(index).figura.getCzesci().size();

		for (int i = 0; i < size; i++) {
			Czesc c = new Czesc(ZamowienieDane.figury.get(index).figura.getCzesci().get(i));
			czesci.add(c);
		}

		return czesci;
	}
}
