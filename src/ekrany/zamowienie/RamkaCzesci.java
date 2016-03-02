package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dane.CzescKontolki;
import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.Zamowienie;
import modules.czesci.Czesc;

public class RamkaCzesci
{
	public static void ramkaCzesci(Graphics g, JPanel panel)
	{
		// ramka czêœci
		int x = 780;
		int y = 120;
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 210, 450);

		JLabel bok = new JLabel("Bok");
		JLabel kat = new JLabel("K¹t");

		int x_bok = x + 60;
		int x_kat = x + 150;
		bok.setBounds(x_bok, y + 5, 50, 20);
		kat.setBounds(x_kat, y + 5, 50, 20);
		bok.setForeground(Color.PINK);
		kat.setForeground(Color.PINK);
		panel.add(bok);
		panel.add(kat);

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

				CONST.setKoloryNieaktywny(bok_text);
				CONST.setKoloryNieaktywny(kat_text);

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

		panel.repaint();
	}

	private static FocusListener zapiszBok(final int index, final int index_czesc, final Zamowienie panel, final JTextField text_field)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent arg0)
			{
				if (text_field.getText().equals(""))
				{
					text_field.setText("0");
				}
				CONST.setKoloryNieaktywny(text_field);
				Czesc czesc = ZamowienieDane.figury.get(index).figura.getCzesci().get(index_czesc);
				czesc.setDlugosc(Integer.parseInt(ZamowienieDane.czesc_kontrolki.get(index_czesc).bok.getText()));
				czesc.setKat(Integer.parseInt(ZamowienieDane.czesc_kontrolki.get(index_czesc).kat.getText()));
				panel.repaint();
			}

			@Override
			public void focusGained(FocusEvent arg0)
			{
				CONST.setKoloryAktywny(text_field);
				panel.repaint();

			}
		};
	}
}
