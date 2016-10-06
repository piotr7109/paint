package dodatki;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;

import javax.swing.JTextField;

import dane.ZamowienieDane;

public class FocusListeners
{
	public static HashMap<Integer, Integer> sred_sworzen = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Double> sred_waga = new HashMap<Integer, Double>();
	public static HashMap<Integer, String> maszyny = new HashMap<Integer, String>();
	public static HashMap<Integer, String> maszyny_kodowo = new HashMap<Integer, String>();

	public static void setSredSworzen()
	{
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		map.put(0, 0);
		map.put(6, 32);
		map.put(8, 32);
		map.put(10, 40);
		map.put(12, 60);
		map.put(14, 70);
		map.put(16, 80);
		map.put(18, 90);
		map.put(20, 100);
		map.put(22, 154);
		map.put(25, 175);
		map.put(28, 196);
		map.put(32, 288);

		HashMap<Integer, Double> map2 = new HashMap<Integer, Double>();

		map2.put(0, 0.0);
		map2.put(6, 0.222);
		map2.put(8, 0.395);
		map2.put(10, 0.617);
		map2.put(12, 0.888);
		map2.put(14, 1.21);
		map2.put(16, 1.58);
		map2.put(18, 2.0);
		map2.put(20, 2.47);
		map2.put(22, 2.98);
		map2.put(25, 3.85);
		map2.put(28, 4.83);
		map2.put(32, 6.31);

		HashMap<Integer, String> map3 = new HashMap<Integer, String>();
		map3.put(1, "Magazyn");
		map3.put(2, "Norzyce ręczne");
		map3.put(3, "Giętarka ręczna");
		map3.put(4, "SuperFlex");
		map3.put(5, "Format12");
		map3.put(6, "MiniSyntax");
		map3.put(7, "Format16");
		map3.put(8, "CS40");

		HashMap<Integer, String> map4 = new HashMap<Integer, String>();
		map4.put(1, "Magazyn");
		map4.put(2, "Norzyce ręczne");
		map4.put(3, "Giętarka ręczna");
		map4.put(4, "SuperFlex");
		map4.put(5, "Format12");
		map4.put(6, "MiniSyntax");
		map4.put(7, "Format16");
		map4.put(8, "CS40");

		sred_sworzen = map;
		sred_waga = map2;
		maszyny = map3;
		maszyny_kodowo = map4;
	}

	public static FocusListener srednicaFocusListener(final int index, final JTextField srednica_text, final JTextField sworzen_text)
	{
		setSredSworzen();
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent e)
			{
				initSrednicaSworzen(index, srednica_text, sworzen_text);
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				// TODO Auto-generated method stub

			}
		};
	}

	public static void initSrednicaSworzen(final int index, final JTextField srednica_text, final JTextField sworzen_text)
	{
		int srednica = Integer.parseInt(srednica_text.getText());
		int sworzen = 0;
		double waga = 0;

		try
		{
			sworzen = sred_sworzen.get(srednica);
			waga = sred_waga.get(srednica);
		}
		catch (NullPointerException exception)
		{
			sworzen = 32;
			waga = 0.222;
		}

		sworzen_text.setText(Integer.toString(sworzen));
		ZamowienieDane.figury.get(index).sworzen = sworzen;
		ZamowienieDane.figury.get(index).waga = waga;
	}
}