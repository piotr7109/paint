package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import dane.ZamowienieDane;
import dodatki.CONST;
import dodatki.FocusListeners;
import ekrany.Zamowienie;

public class RamkaUwagi
{
	public static int x = 780;
	public static int y = 580;

	public static JTextArea uwagi;

	public RamkaUwagi(Zamowienie panel)
	{
		uwagi = new JTextArea("");
		uwagi.setBounds(x + 5, y + 25, 200, 70);
		CONST.setKoloryAktywny2(uwagi);
		uwagi.addFocusListener(uwagiFocusListener(panel, uwagi));
		uwagi.addKeyListener(panel);
		uwagi.setLineWrap(true);
		panel.add(uwagi);
	}

	public static void ramkaUwagi(Graphics g, JPanel panel)
	{
		// ramka uwagi
		g.setColor(Color.PINK);
		g.drawRect(x, y, 210, 100);

		panel.add(CONST.getTytul(x, y, "Uwagi", Color.RED));
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
				CONST.setKoloryAktywny2(text_field);
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				CONST.setKoloryNieaktywny2(text_field);
				text_field.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				// ramkaUwagiUpdate(panel);

			}
		};
	}
}
