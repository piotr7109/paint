package dodatki;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CONST
{
	public static final Dimension btn_size = new Dimension(100, 25);
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;

	public static JLabel getTytul(int x, int y, String tekst, Color c)
	{
		JLabel tytul = new JLabel(tekst);
		tytul.setBounds(x + 5, y + 5, 200, 20);
		tytul.setForeground(c);

		return tytul;
	}

	public static double radians(int kat)
	{

		return (kat * Math.PI / 180);
	}

	public static double round(double value, int places)
	{
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static void setKoloryAktywny(JTextField text_field)
	{
		text_field.setBorder(BorderFactory.createEmptyBorder());
		text_field.setForeground(Color.RED);
		text_field.setBackground(Color.YELLOW);
	}

	public static void setKoloryNieaktywny(JTextField text_field)
	{
		text_field.setBorder(BorderFactory.createEmptyBorder());
		text_field.setForeground(Color.GREEN);
		text_field.setBackground(Color.GRAY);
	}
}
