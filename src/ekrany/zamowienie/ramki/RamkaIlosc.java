package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import org.apache.commons.lang.NumberUtils;

import dane.ZamowienieDane;
import dodatki.Tools;
import ekrany.Zamowienie;

@SuppressWarnings("deprecation")
public class RamkaIlosc
{
	public static JTextField poziom_skoku = new JTextField();
	public static JTextField ilosc_skokow = new JTextField();
			
	
	public RamkaIlosc(Zamowienie panel)
	{
		konfigurujKontrolki(panel);
	}
	private void konfigurujKontrolki(Zamowienie panel)
	{
		
		poziom_skoku.addFocusListener(zmiennaDlugoscFL(poziom_skoku, panel));
		ilosc_skokow.addFocusListener(zmiennaDlugoscFL(ilosc_skokow, panel));
		Tools.setKoloryNieaktywny(poziom_skoku);
		Tools.setKoloryNieaktywny(ilosc_skokow);
		
		panel.add(poziom_skoku);
		panel.add(ilosc_skokow);
		
	}
	private FocusListener zmiennaDlugoscFL(final JTextField text_field, final Zamowienie panel)
	{
		return new FocusListener()
		{
			
			@Override
			public void focusLost(FocusEvent e)
			{
				Tools.setKoloryNieaktywny(text_field);
				
				if (!NumberUtils.isNumber(text_field.getText()))
				{
					text_field.setText("0");
				}
				ZamowienieDane.figury.get(panel.z_x).figura.setIloscSkokow(Integer.parseInt(ilosc_skokow.getText()));
				ZamowienieDane.figury.get(panel.z_x).figura.setPoziomSkoku(Integer.parseInt(poziom_skoku.getText()));
				
			}
			
			@Override
			public void focusGained(FocusEvent e)
			{
				Tools.setKoloryAktywny(text_field);
				
			}
		};
	}
	public static void update(Zamowienie panel)
	{
		//poziom_skoku.setText(ZamowienieDane.figury.get(panel.z_x).figura.getPoziomSkoku()+"");
		//ilosc_skokow.setText(ZamowienieDane.figury.get(panel.z_x).figura.getIloscSkokow()+"");
	}
	
	public static void ramkaIlosc(Graphics g, Zamowienie panel)
	{
		int x = 10;
		int y = 430;
		g.setColor(Color.WHITE);
		g.drawRect(rescale(x),rescale(y),rescale(500),rescale(50));
		rescale();
	}
	
	
	private static int rescale(int number)
	{
		return (int)(number*Tools.scale);
	}
	public static void rescale()
	{
		int x = 20;
		int y = 445;
		Dimension size = Tools.getDimension(50, 20);
		
		poziom_skoku.setBounds(Tools.rescale(x),Tools.rescale(y), size.width, size.height);
		ilosc_skokow.setBounds(Tools.rescale(x+100),Tools.rescale(y), size.width, size.height);
		poziom_skoku.setFont(new Font("", 0, Tools.rescale(12)));
		ilosc_skokow.setFont(new Font("", 0, Tools.rescale(12)));
	}
}
