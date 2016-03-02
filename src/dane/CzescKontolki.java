package dane;

import javax.swing.JTextField;

public class CzescKontolki
{
	public JTextField bok, kat, typ;
	public JTextField[] kontrolki;
	
	public void set()
	{
		kontrolki = new JTextField[]{bok, kat};
	}
}
