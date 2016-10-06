package dane;

import javax.swing.JTextField;

public class FiguraKontrolki
{
	public JTextField kontrolki[];
	public int index;
	
	public JTextField liczba;
	public JTextField pozycja;
	public JTextField ilosc_sztuk;
	public JTextField srednica;
	public JTextField fig;
	public JTextField ilosc_paczek;
	public JTextField maszyna;
	// public JTextField mat??;
	public JTextField sworzen;
	

	public void setKontrolki()
	{
		kontrolki = new JTextField[] {liczba, pozycja, ilosc_sztuk, srednica, fig, ilosc_paczek, maszyna, sworzen };
		// kontrolki[x++] = mat??;
	}
	public void updateFromFiguraZamowienie()
	{
		FiguraZamowienie f_zam = ZamowienieDane.figury.get(index);
		pozycja.setText(f_zam.pozycja);
		ilosc_sztuk.setText(Integer.toString(f_zam.ilosc_sztuk));
		srednica.setText(Integer.toString(f_zam.srednica));
		fig.setText(Integer.toString(f_zam.fig));
		ilosc_paczek.setText(Integer.toString(f_zam.ilosc_paczek));
		maszyna.setText(Integer.toString( f_zam.maszyna));
		// ma??.setText(""+f_zam.ma??);
		sworzen.setText(Integer.toString(f_zam.sworzen));
	}
}
