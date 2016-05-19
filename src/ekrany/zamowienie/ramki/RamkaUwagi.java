package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import dane.ZamowienieDane;
import dodatki.Tools;
import ekrany.Zamowienie;

public class RamkaUwagi
{
	public static int x = 780;
	public static int y = 580;

	public static JTextArea uwagi;

	public RamkaUwagi(Zamowienie panel)
	{
		initKontrolki(panel);
	}
	
	protected void initKontrolki(Zamowienie panel)
	{
		panel.add(Tools.getTytul(rescale(x), rescale(y), "Uwagi", Color.RED, Tools.scale));
		uwagi = new JTextArea("");
		uwagi.setBounds(x + 5, y + 25, 200, 70);
		Tools.setKoloryAktywny2(uwagi);
		uwagi.addFocusListener(uwagiFocusListener(panel, uwagi));
		uwagi.addKeyListener(panel);
		uwagi.setLineWrap(true);
		panel.add(uwagi);
		rescale();
	}

	public static void ramkaUwagi(Graphics g, JPanel panel)
	{
		// ramka uwagi
		g.setColor(Color.PINK);
		g.drawRect(rescale(x), rescale(y), rescale(210), rescale(100));

	}

	public static void ramkaUwagiUpdate(Zamowienie panel)
	{
		uwagi.setText(ZamowienieDane.figury.get(panel.z_x).uwagi);
	}

	public static FocusListener uwagiFocusListener(final Zamowienie panel, final JTextComponent text_field)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent e)
			{
				ZamowienieDane.figury.get(panel.z_x).uwagi = text_field.getText();
				Tools.setKoloryAktywny2(text_field);
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				Tools.setKoloryNieaktywny2(text_field);
				text_field.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				// ramkaUwagiUpdate(panel);

			}
		};
	}
	private static int rescale(int number)
	{
		return (int)(number*Tools.scale);
	}
	public static void rescale()
	{
		uwagi.setFont(new Font("",0, rescale(12)));
		uwagi.setBounds(rescale(uwagi.getX()), rescale(uwagi.getY()), rescale(uwagi.getWidth()), rescale(uwagi.getHeight()));

	}
}
