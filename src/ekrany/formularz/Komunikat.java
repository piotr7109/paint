package ekrany.formularz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Komunikat extends JPanel
{
	private JLabel komunikat = new JLabel();
	private JButton ok_button = new JButton("OK");
	private JFrame frame;

	public Komunikat(String komunikat, JFrame frame)
	{
		this.setLayout(null);
		this.setPreferredSize(new Dimension(50, 100));
		this.komunikat.setText(komunikat);
		this.frame = frame;
		
		init();
	}

	private void init()
	{
		komunikat.setBounds(40, 10, 75, 50);
		ok_button.setBounds(40, 50, 75, 25);
		
		
		ok_button.addActionListener(onClose());
		add(ok_button);
		add(komunikat);
	}
	
	private ActionListener onClose()
	{
		return new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				
			}
		};
	}

}
