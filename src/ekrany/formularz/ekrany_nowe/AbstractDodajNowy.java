package ekrany.formularz.ekrany_nowe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dodatki.Tools;
import ekrany.Formularz;

public abstract class AbstractDodajNowy extends JPanel
{
	protected JTextField nazwa = new JTextField();
	protected JButton ok = new JButton("OK");
	protected JTextField kod_label = new JTextField();
	protected int kod;
	public int id_parent;
	protected JFrame frame;
	protected Formularz form;

	public AbstractDodajNowy(int kod, JFrame frame, int id_parent, Formularz form)
	{
		this.setPreferredSize(new Dimension(Tools.rescale(400), Tools.rescale(100)));
		this.setLayout(null);
		this.kod = kod;
		this.kod_label.setText(kod + "");
		this.id_parent = id_parent;
		this.frame = frame;
		this.form = form;

		this.dodajKontrolki();
	}

	protected void dodajKontrolki()
	{
		int x = 25;
		int y = Tools.rescale(25);
		Dimension size = new Dimension(Tools.rescale(100), Tools.rescale(25));
		nazwa.setBounds(Tools.rescale(x), y, size.width, size.height);
		kod_label.setBounds(Tools.rescale(x += 100), y, size.width, size.height);
		ok.setBounds(Tools.rescale(x += 100), y, size.width, size.height);

		nazwa.setFont(new Font("", 0, Tools.rescale(12)));
		kod_label.setFont(new Font("", 0, Tools.rescale(12)));
		ok.setFont(new Font("", 0, Tools.rescale(12)));

		ok.addActionListener(this.onClose());

		add(nazwa);
		add(kod_label);
		add(ok);
	}

	protected ActionListener onClose()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				zapiszDane();
				frame.dispose();

			}
		};
	}

	protected void zapiszDane()
	{

	}
}
