package ekrany;

import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dodatki.CONST;

public class Formularz extends JPanel
{
	private static final int WIDTH = 150;
	private static final int HEIGHT = 20;
	private static final int WIDTH_LABEL = 100;

	private ArrayList<JTextField> kontrolki = new ArrayList<JTextField>();
	private ArrayList<JLabel> kontrolki_label = new ArrayList<JLabel>();
	private String nazwy[] = { "Kod", "Nazwa", "Miejscowoœæ", "Placówka", "Obiekt wysy³any", "Uwagi" };

	private JButton button_potwierdz;

	public Formularz()
	{
		this.setSize(CONST.WIDTH, CONST.HEIGHT);
		this.setLayout(null);

		this.stworzKontrolki();
		this.konfigurujKontrolki();

	}

	private void stworzKontrolki()
	{
		int ile_kontrolek = nazwy.length;
		for (int i = 0; i < ile_kontrolek; i++)
		{
			JLabel k_label = new JLabel(nazwy[i]);
			k_label.setSize(new Dimension(WIDTH_LABEL, HEIGHT));

			JTextField k = new JTextField();
			k.setSize(new Dimension(WIDTH, HEIGHT));

			kontrolki.add(k);
			kontrolki_label.add(k_label);

			this.add(k_label);
			this.add(k);
		}

		button_potwierdz = new JButton("PotwierdŸ");
		button_potwierdz.setSize(WIDTH, HEIGHT);
		this.add(button_potwierdz);
	}

	private void konfigurujKontrolki()
	{
		int y = 10;
		int x = 20;

		int ile_kontrolek = kontrolki.size();
		for (int i = 0; i < ile_kontrolek; i++)
		{
			y = (i * HEIGHT) + 10;
			kontrolki_label.get(i).setLocation(x, y);
			kontrolki.get(i).setLocation(x + WIDTH_LABEL, y);

		}

		button_potwierdz.setLocation(x + WIDTH_LABEL, y + HEIGHT * 2);
	}
}
