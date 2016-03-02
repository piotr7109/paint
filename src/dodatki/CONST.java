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

	public static JLabel getTytul(int x, int y, String tekst , Color c)
	{
		JLabel tytul = new JLabel(tekst);
		tytul.setBounds(x+5, y+5, 200, 20);
		tytul.setForeground(c);
		
		return tytul;
	}
	public static double radians(int kat)
	{

		return (kat * Math.PI / 180);
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
