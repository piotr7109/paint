package ekrany.zamowienie.walidacje;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import dodatki.Tools;

public class WalidacjaJTextField
{
	public static KeyListener walidacjaPolaLiczbowego(JTextField textField)
	{
		return new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				int key = e.getKeyCode();

				if (!(Tools.jestKodemCyfry(key) || Tools.jestKodemSterujacym(key)))
				{
					String text = textField.getText().replaceAll("[A-Za-z]", "");
					textField.setText(text);
				}

			}

			@Override
			public void keyTyped(KeyEvent e)
			{
			}

		};
	}
}
