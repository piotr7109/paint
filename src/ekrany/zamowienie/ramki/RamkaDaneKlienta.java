package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.Zamowienie;

public class RamkaDaneKlienta
{
	private JLabel kod, odbiorca, budowa, obiekt, element;
	private JTextField termin_dostawy, rysunek;
	private static ArrayList<JComponent> kontrolki = new ArrayList<JComponent>();

	public RamkaDaneKlienta(Zamowienie panel)
	{
		czyscKontrolki();
		initNaglowek(panel);
		initKontrolki(panel);
	}

	protected void czyscKontrolki()
	{
		kontrolki.clear();
	}

	protected void initNaglowek(Zamowienie panel)
	{
		int x = 10;
		String elem[] = { "KOD", "ODBIORCA", "BUDOWA", "OBIEKT", "ELEMENT", "TERM. DOST.", "RYSUNEK NR.", "KSIĘGOWANIE" };
		int i = 0;
		for (String _elem : elem)
		{
			JLabel element = new JLabel(_elem);
			element.setSize(rescale(200), rescale(20));
			element.setLocation(rescale(x + 10), rescale(i * 20 + 15));
			element.setForeground(Color.RED);
			element.setFont(new Font("", 0, rescale(12)));
			i++;
			if (i == 4)
			{
				x = 510;
				i = 0;
			}
			panel.add(element);

		}
	}
	
	protected void initKontrolki(Zamowienie panel)
	{
		int x = 210;
		int y = -5;
		Dimension text_size = new Dimension(100, 20);

		kod = new JLabel(CONST.getKodZamowienia());
		odbiorca = new JLabel(ZamowienieDane.odbiorca.getNazwa());
		budowa = new JLabel(ZamowienieDane.budowa.getNazwa());
		obiekt = new JLabel(ZamowienieDane.obiekt.getNazwa());
		element = new JLabel(ZamowienieDane.element.getNazwa());
		termin_dostawy = new JTextField(CONST.nullStringToEmpty(ZamowienieDane.element.getTerminDostawy()));
		rysunek = new JTextField(CONST.nullStringToEmpty(ZamowienieDane.element.getRysunek()));

		kod.setForeground(Color.WHITE);
		odbiorca.setForeground(Color.WHITE);
		budowa.setForeground(Color.WHITE);
		obiekt.setForeground(Color.WHITE);
		element.setForeground(Color.WHITE);
		termin_dostawy.setForeground(Color.WHITE);
		rysunek.setForeground(Color.WHITE);

		kod.setBounds(x, y += 20, text_size.width, text_size.height);
		odbiorca.setBounds(x, y += 20, text_size.width, text_size.height);
		budowa.setBounds(x, y += 20, text_size.width, text_size.height);
		obiekt.setBounds(x, y += 20, text_size.width, text_size.height);

		element.setBounds(x += 500, y = 10, text_size.width, text_size.height);
		termin_dostawy.setBounds(x, y += 20, text_size.width, text_size.height);
		rysunek.setBounds(x, y += 20, text_size.width, text_size.height);

		termin_dostawy.addFocusListener(zapiszDane(termin_dostawy));
		rysunek.addFocusListener(zapiszDane(rysunek));

		CONST.setKoloryNieaktywny2(termin_dostawy);
		CONST.setKoloryNieaktywny2(rysunek);

		kontrolki.add(kod);
		kontrolki.add(odbiorca);
		kontrolki.add(budowa);
		kontrolki.add(obiekt);
		kontrolki.add(element);
		kontrolki.add(termin_dostawy);
		kontrolki.add(rysunek);

		for (JComponent component : kontrolki)
		{
			panel.add(component);
		}

		rescale();
	}

	private FocusListener zapiszDane(final JTextComponent text_field)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent e)
			{
				CONST.setKoloryNieaktywny2(text_field);
				ZamowienieDane.element.setRysunek(rysunek.getText());
				ZamowienieDane.element.setTerminDostawy(termin_dostawy.getText());
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				CONST.setKoloryAktywny2(text_field);

			}
		};
	}

	

	

	public static void ramkaDaneKlienta(Graphics g, JPanel panel)
	{
		int x = 10;
		int y = 10;
		g.setColor(Color.GREEN);
		g.drawRect(x, y, ImageObserver.WIDTH - 20, 100);

		
	}

	private static int rescale(int number)
	{
		return (int) (number * CONST.scale);
	}

	// kod, odbiorca, budowa, obiekt, element;
	public static void rescale()
	{
		for (JComponent label : kontrolki)
		{
			label.setFont(new Font("", 0, rescale(12)));
			label.setBounds(rescale(label.getX()), rescale(label.getY()), rescale(label.getWidth()), rescale(label.getHeight()));
		}
	}
}
