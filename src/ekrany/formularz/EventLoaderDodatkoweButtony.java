package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import ekrany.Rysowanie;

public class EventLoaderDodatkoweButtony
{
	
	public static ActionListener rysujButton()
	{
		return new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = createNewWindow("Dodaj Figurę");
				
				frame.add(new Rysowanie(frame));
				frame.pack();
				
			}
		};
	}
	private static JFrame createNewWindow(String title)
	{
		JFrame frame = new JFrame(title);
		frame.setResizable(false);

		frame.setVisible(true);
		return frame;
	}
}
