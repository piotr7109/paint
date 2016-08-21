package dodatki;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import dane.ZamowienieDane;

public class Tools
{
	public static final Dimension btn_size = new Dimension(100, 25);
	public static final int MAX_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int MAX_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	public static float scale = 1.0f;

	public static void makeDir(String directory)
	{
		File dir = new File(directory);
		if (!dir.exists())
		{
			try
			{
				dir.mkdir();
			}
			catch (SecurityException se)
			{
				System.err.println(se.getMessage());
			}
		}
	}

	public static JLabel getTytul(int x, int y, String tekst, Color c, double scale)
	{
		JLabel tytul = new JLabel(tekst);
		tytul.setBounds(x + 5, y + 5, 200, 20);
		tytul.setForeground(c);
		tytul.setFont(new Font("", 0, rescale(12)));

		return tytul;
	}

	public static int rescale(int number)
	{
		return (int) (number * Tools.scale);
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

	public static String nullStringToEmpty(String str)
	{
		if (str.equals("null"))
			return "";
		return str;
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

	public static String transformToZeroNumber(int liczba)
	{
		String kod = "";

		if (liczba < 10)
		{
			kod += "00";
		}
		else if (liczba < 100)
		{
			kod += "0";
		}
		else
		{
			kod += ".";
		}
		kod += liczba;
		return kod;
	}

	public static String getKodZamowienia()
	{
		String kod = "";

		kod += transformToZeroNumber(ZamowienieDane.odbiorca.getKod());
		kod += transformToZeroNumber(ZamowienieDane.budowa.getKod());
		if (ZamowienieDane.obiekt.getKod() < 10)
		{
			kod += "0";
		}
		kod += ZamowienieDane.obiekt.getKod();

		kod += "." + transformToZeroNumber(ZamowienieDane.element.getKod());

		return kod;
	}

	public static boolean jestKodemCyfry(int kod)
	{
		if ((kod >= 96 && kod <= 105) || (kod >= 48 && kod <= 57) || kod == 109 || kod == 45)
		{
			return true;
		}
		return false;
	}
	
	public static MouseListener getFocusOut(final JPanel panel) {
		return new MouseListener()
		{	
			@Override
			public void mousePressed(MouseEvent e)
			{
				panel.requestFocusInWindow();	
			}
			
			@Override
			public void mouseExited(MouseEvent e){}
			@Override
			public void mouseEntered(MouseEvent e){}
			@Override
			public void mouseClicked(MouseEvent e){}
			@Override
			public void mouseReleased(MouseEvent e)
			{}
		};
	}	
	
	public static void showSimpleMessage(String message, int type)
	{
		JOptionPane.showMessageDialog(null, message, "Coś poszło nie tak...", type);	
	}
}
