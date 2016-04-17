package dodatki;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class CONST
{
	public static final Dimension btn_size = new Dimension(100, 25);
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;

	public static JLabel getTytul(int x, int y, String tekst, Color c, double scale)
	{
		JLabel tytul = new JLabel(tekst);
		tytul.setBounds(x + 5, y + 5, 200, 20);
		tytul.setForeground(c);
		tytul.setFont(new Font("",0, rescale(12,scale)));

		return tytul;
	}
	private static int rescale(int number, double scale)
	{
		return (int)(number*scale);
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

	public static void setKoloryAktywny(JTextComponent text_field)
	{
		text_field.setBorder(BorderFactory.createEmptyBorder());
		text_field.setForeground(Color.RED);
		text_field.setBackground(Color.YELLOW);
	}

	public static void setKoloryNieaktywny(JTextComponent text_field)
	{
		text_field.setBorder(BorderFactory.createEmptyBorder());
		text_field.setForeground(Color.GREEN);
		text_field.setBackground(Color.GRAY);
	}
	
	public static void setKoloryAktywny2(JTextComponent text_field)
	{
		text_field.setBorder(BorderFactory.createEmptyBorder());
		text_field.setForeground(Color.RED);
		text_field.setOpaque(false);
	}
	public static void setKoloryNieaktywny2(JTextComponent text_field)
	{
		text_field.setBorder(BorderFactory.createEmptyBorder());
		text_field.setForeground(Color.YELLOW);
		text_field.setOpaque(false);
	}
}
