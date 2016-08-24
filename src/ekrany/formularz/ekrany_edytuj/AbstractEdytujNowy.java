package ekrany.formularz.ekrany_edytuj;

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
import modules.zamowienie.ZamowienieCore;

public abstract class AbstractEdytujNowy extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 871047994768615105L;
	protected JTextField nazwa = new JTextField();
	protected JButton ok = new JButton("OK");
	protected JLabel kod_label = new JLabel();
	protected ZamowienieCore item;
	protected JFrame frame;
	protected Formularz form;

	public AbstractEdytujNowy(JFrame frame, Formularz form, ZamowienieCore item)
	{
		this.setPreferredSize(Tools.getDimension(400, 100));
		this.setLayout(null);

		this.item = item;

		this.frame = frame;
		this.form = form;

		this.dodajKontrolki();
	}

	protected void dodajKontrolki()
	{
		int x = 25;
		int y = Tools.rescale(25);
		Dimension size = Tools.getDimension(100, 25);
		nazwa.setBounds(Tools.rescale(x), y, size.width, size.height);
		kod_label.setBounds(Tools.rescale(x += 100), y, size.width, size.height);
		ok.setBounds(Tools.rescale(x += 100), y, size.width, size.height);

		kod_label.setText(item.getKod() + "");
		nazwa.setText(item.getNazwa());
		
		nazwa.setFont(new Font("",0,Tools.rescale(12)));
		kod_label.setFont(new Font("",0,Tools.rescale(12)));
		ok.setFont(new Font("",0,Tools.rescale(12)));

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
