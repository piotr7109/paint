package dane;

import javax.swing.JTextField;

public class FiguraKontrolki
{
	public JTextField kontrolki[];
	
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
}
