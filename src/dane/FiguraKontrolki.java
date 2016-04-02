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
	public JTextField kolor;
	public JTextField material;
	// public JTextField mat??;
	public JTextField sworzen;
	

	public void setKontrolki()
	{
		kontrolki = new JTextField[] {liczba, pozycja, ilosc_sztuk, srednica, fig, kolor, material, sworzen };
		// kontrolki[x++] = mat??;
	}
	public void updateFromFiguraZamowienie()
	{
		FiguraZamowienie f_zam = ZamowienieDane.figury.get(index);
		pozycja.setText("" + f_zam.pozycja);
		ilosc_sztuk.setText("" + f_zam.ilosc_sztuk);
		srednica.setText("" + f_zam.srednica);
		fig.setText("" + f_zam.fig);
		kolor.setText("" + f_zam.kolor);
		material.setText("" + f_zam.material);
		// ma??.setText(""+f_zam.ma??);
		sworzen.setText("" + f_zam.sworzen);
	}
}
