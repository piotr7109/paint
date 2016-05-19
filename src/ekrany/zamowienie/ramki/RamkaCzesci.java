package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.NumberUtils;

import dane.CzescKontolki;
import dane.ZamowienieDane;
import dodatki.Tools;
import ekrany.Zamowienie;
import modules.czesci.Czesc;

public class RamkaCzesci
{
	
	public RamkaCzesci(JPanel panel)
	{
		int x = 780;
		int y = 120;
		JLabel bok = new JLabel("Bok");
		JLabel kat = new JLabel("Kąt");

		int x_bok = x + 60;
		int x_kat = x + 150;
		bok.setBounds(rescale(x_bok), rescale(y + 5), rescale(50), rescale(20));
		kat.setBounds(rescale(x_kat), rescale(y + 5), rescale(50), rescale(20));
		bok.setForeground(Color.PINK);
		kat.setForeground(Color.PINK);
		bok.setFont(new Font("",0, rescale(12)));
		kat.setFont(new Font("",0, rescale(12)));
		panel.add(bok);
		panel.add(kat);
	}
	public static void ramkaCzesci(Graphics g)
	{
		// ramka cz�ci
		int x = 780;
		int y = 120;
		g.setColor(Color.WHITE);
		g.drawRect(rescale(x), rescale(y), rescale(210), rescale(450));

		

		////

	}

	public static void rysujKontrolki(Zamowienie panel, int index)
	{
		int x_bok = 840;
		int x_kat = 930;
		int y = 120;

		for (CzescKontolki c_kontrolki : ZamowienieDane.czesc_kontrolki)
		{
			panel.remove(c_kontrolki.bok);
			panel.remove(c_kontrolki.kat);
			panel.remove(c_kontrolki.typ);
		}

		ZamowienieDane.czesc_kontrolki.clear();
		panel.c_x = -1;
		if (panel.figura != null)
		{
			int index_czesc = 0;
			for (Czesc czesc : panel.figura.getCzesci())
			{
				y += 25;
				JTextField bok_text = new JTextField(czesc.getDlugosc() + "");
				JTextField kat_text = new JTextField(czesc.getKat() + "");
				JTextField typ_text = new JTextField(czesc.getTyp());

				bok_text.setBounds(x_bok, y, 50, 20);
				kat_text.setBounds(x_kat, y, 50, 20);

				bok_text.addFocusListener(zapiszBok(index, index_czesc, panel, bok_text));
				kat_text.addFocusListener(zapiszBok(index, index_czesc, panel, kat_text));

				bok_text.addKeyListener(panel);
				kat_text.addKeyListener(panel);

				Tools.setKoloryNieaktywny(bok_text);
				Tools.setKoloryNieaktywny(kat_text);

				panel.add(bok_text);
				panel.add(kat_text);
				panel.add(typ_text);

				CzescKontolki c_kontrolka = new CzescKontolki();
				c_kontrolka.bok = bok_text;
				c_kontrolka.kat = kat_text;
				c_kontrolka.typ = typ_text;
				c_kontrolka.set();

				ZamowienieDane.czesc_kontrolki.add(c_kontrolka);
				index_czesc++;
				panel.c_x++;
			}

		}
		rescale();
		panel.repaint();
	}

	private static FocusListener zapiszBok(final int index, final int index_czesc, final Zamowienie panel, final JTextField text_field)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent arg0)
			{
				if (!NumberUtils.isNumber(text_field.getText()))
				{
					text_field.setText("0");
				}
				Tools.setKoloryNieaktywny(text_field);
				Czesc czesc = ZamowienieDane.figury.get(index).figura.getCzesci().get(index_czesc);
				czesc.setDlugosc(Integer.parseInt(ZamowienieDane.czesc_kontrolki.get(index_czesc).bok.getText()));
				czesc.setKat(Integer.parseInt(ZamowienieDane.czesc_kontrolki.get(index_czesc).kat.getText()));
				panel.poprzedni_text_field = text_field;
				RamkaFigura.skalaReset();
				panel.repaint();
			}

			@Override
			public void focusGained(FocusEvent arg0)
			{
				
				Tools.setKoloryAktywny(text_field);
				panel.repaint();

			}
		};
	}
	
	public static void dodajCzesc(Zamowienie panel, int index)
	{
		Czesc czesc = new Czesc();
		czesc.setDlugosc(0);
		czesc.setKat(0);
		czesc.setTyp("linia");
		panel.figura.addCzesc(czesc);
		
		
		CzescKontolki c_kontrolka = new CzescKontolki();
		c_kontrolka.bok = new JTextField(czesc.getDlugosc());
		c_kontrolka.kat = new JTextField(czesc.getKat());
		c_kontrolka.typ = new JTextField(czesc.getTyp());
		c_kontrolka.set();
		ZamowienieDane.czesc_kontrolki.add(c_kontrolka);
		ZamowienieDane.czesc_kontrolki_figura.add(c_kontrolka);
		
		rysujKontrolki(panel, index);
		panel.repaint();
	}
	private static int rescale(int number)
	{
		return (int)(number*Tools.scale);
	}
	public static void rescale()
	{
		for(CzescKontolki c_kontrolki: ZamowienieDane.czesc_kontrolki)
		{
			c_kontrolki.bok.setFont(new Font("",0, rescale(12)));
			c_kontrolki.kat.setFont(new Font("",0, rescale(12)));
			c_kontrolki.bok.setBounds(rescale(c_kontrolki.bok.getX()), rescale(c_kontrolki.bok.getY()), rescale(c_kontrolki.bok.getWidth()), rescale(c_kontrolki.bok.getHeight()));
			c_kontrolki.kat.setBounds(rescale(c_kontrolki.kat.getX()), rescale(c_kontrolki.kat.getY()), rescale(c_kontrolki.kat.getWidth()), rescale(c_kontrolki.kat.getHeight()));

		}
	}
}
