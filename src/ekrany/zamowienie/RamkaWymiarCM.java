package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.Zamowienie;
import modules.czesci.Czesc;

public class RamkaWymiarCM
{

	public static JLabel max_wysokosc;
	public static JLabel max_dlugosc;
	public static JLabel calk_dlugosc;
	public static JLabel ciezar;
	public static JLabel calk_ciezar;

	public static void ramkaWymiarCM(Graphics g, JPanel panel)
	{
		// ramak wymiar cm
		int x = 780;
		int y = 690;
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 210, 100);

		panel.add(CONST.getTytul(x, y, "Wymiar(cm) / Ciê¿ar(kg)", Color.RED));

		panel.add(CONST.getTytul(x, y + 15, "Max wysokoœæ:", Color.PINK));
		panel.add(CONST.getTytul(x, y + 30, "Max d³ugoœæ:", Color.PINK));
		panel.add(CONST.getTytul(x, y + 45, "Ca³k. d³ugoœæ:", Color.PINK));
		panel.add(CONST.getTytul(x, y + 60, "Ciê¿ar:", Color.PINK));
		panel.add(CONST.getTytul(x, y + 75, "Ca³k. ciê¿ar:", Color.PINK));

	}

	public static void init(JPanel panel)
	{
		int x = 900;
		int y = 690;
		max_wysokosc = CONST.getTytul(x, y + 15, ".", Color.YELLOW);
		max_dlugosc = CONST.getTytul(x, y + 30, ".", Color.YELLOW);
		calk_dlugosc = CONST.getTytul(x, y + 45, ".", Color.YELLOW);
		ciezar = CONST.getTytul(x, y + 60, ".", Color.YELLOW);
		calk_ciezar = CONST.getTytul(x, y + 75, ".", Color.YELLOW);

		panel.add(max_wysokosc);
		panel.add(max_dlugosc);
		panel.add(calk_dlugosc);
		panel.add(ciezar);
		panel.add(calk_ciezar);

	}

	public static void ustawKontrolki(int index, Zamowienie panel)
	{
		double dlugosc = 0;
		double waga_jedn = ZamowienieDane.figury.get(index).waga;
		if (panel.tryb_rzeczywisty)
		{
			dlugosc = obliczDlugoscRzeczywista(index);
		}
		else
		{
			dlugosc = obliczDlugosc(index);
		}

		calk_dlugosc.setText(CONST.round(dlugosc, 3) + "");
		ciezar.setText(CONST.round((obliczWage(dlugosc, waga_jedn) * ZamowienieDane.figury.get(index).ilosc_sztuk), 3) + "");
		calk_ciezar.setText(CONST.round(obliczCalkCiezar(index, panel.tryb_rzeczywisty), 3) + "");

	}

	private static double obliczDlugoscRzeczywista(int index)
	{
		double dlugosc = 0;

		int size = ZamowienieDane.figury.get(index).figura.getCzesci().size();
		int sworzen = ZamowienieDane.figury.get(index).sworzen / 2;
		int srednica = ZamowienieDane.figury.get(index).srednica;

		ArrayList<Czesc> czesci = cloneCzesci(index);
		System.out.println("size:"+size);
		for (int i = 0; i < size - 1; i++)
		{
			Czesc czesc = czesci.get(i);
			Czesc czesc_n = czesci.get(i + 1);

			if (czesc.getTyp().equals("okrag") || czesc_n.getTyp().equals("okrag"))
			{
				dlugosc += czesc.getDlugosc();

			}
			else
			{
				int kat = czesc_n.getKat();
				kat = 180 - Math.abs(kat);
				
				System.out.println("KAT: "+kat);
				double a = czesc.getDlugosc() - (sworzen + srednica);
				double b = 2 * Math.PI * (sworzen + srednica) * kat / 360;
				int c = czesc_n.getDlugosc() - (sworzen + srednica);
				czesc_n.setDlugosc(c);
				dlugosc += (a + b);

			}
		}

		dlugosc += czesci.get(size - 1).getDlugosc();

		return dlugosc;
	}

	private static ArrayList<Czesc> cloneCzesci(int index)
	{
		ArrayList<Czesc> czesci = new ArrayList<Czesc>();
		int size = ZamowienieDane.figury.get(index).figura.getCzesci().size();

		for (int i = 0; i < size; i++)
		{
			Czesc c = new Czesc(ZamowienieDane.figury.get(index).figura.getCzesci().get(i));
			czesci.add(c);
		}

		return czesci;
	}

	private static double obliczCalkCiezar(int index, boolean rzeczywista)
	{
		double ciezar = 0;
		double dlugosc = 0;
		for (int i = 0; i <= index; i++)
		{
			if (rzeczywista)
			{
				dlugosc = obliczDlugoscRzeczywista(i);
			}
			else
			{

				dlugosc = obliczDlugosc(i);
			}
			double waga_jedn = ZamowienieDane.figury.get(i).waga;
			ciezar += obliczWage(dlugosc, waga_jedn) * ZamowienieDane.figury.get(i).ilosc_sztuk;
		}
		return ciezar;
	}

	private static double obliczWage(double dlugosc, double waga)
	{
		return (dlugosc * waga);
	}

	private static int obliczDlugosc(int index)
	{
		int dlugosc = 0;

		for (Czesc czesc : ZamowienieDane.figury.get(index).figura.getCzesci())
		{
			dlugosc += czesc.getDlugosc();
		}

		return dlugosc;
	}
}
