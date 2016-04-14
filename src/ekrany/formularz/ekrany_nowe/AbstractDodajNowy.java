package ekrany.formularz.ekrany_nowe;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ekrany.Formularz;

public abstract class AbstractDodajNowy extends JPanel
{
	protected JTextField nazwa = new JTextField();
	protected JButton ok = new JButton("OK");
	protected JLabel kod_label = new JLabel();
	protected int kod;
	public int id_parent;
	protected JFrame frame;
	protected Formularz form;
	
	public AbstractDodajNowy(int kod, JFrame frame, int id_parent, Formularz form)
	{
		this.setPreferredSize(new Dimension(225, 75));
		this.setLayout(null);
		this.kod = kod;
		this.kod_label.setText(kod+"");
		this.id_parent = id_parent;
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
