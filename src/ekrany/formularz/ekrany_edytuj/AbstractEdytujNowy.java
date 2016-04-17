package ekrany.formularz.ekrany_edytuj;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ekrany.Formularz;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.budowy.Budowa;

public abstract class AbstractEdytujNowy extends JPanel
{
	protected JTextField nazwa = new JTextField();
	protected JButton ok = new JButton("OK");
	protected JLabel kod_label = new JLabel();
	protected ZamowienieCore item;
	protected JFrame frame;
	protected Formularz form;
	
	public AbstractEdytujNowy(JFrame frame, Formularz form, ZamowienieCore item)
	{
		this.setPreferredSize(new Dimension(225, 75));
		this.setLayout(null);
		this.kod_label.setText(item.getKod()+"");
		this.item = item;

		this.frame = frame;
		this.form = form;
		
		this.dodajKontrolki();
	}
	
	
	protected void dodajKontrolki()
	{
		nazwa.setBounds(25,25,50,25);
		kod_label.setBounds(75, 25, 50, 25);
		ok.setBounds(125,25,75,25);
		
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
