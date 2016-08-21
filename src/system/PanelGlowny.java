package system;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ekrany.Formularz;
import ekrany.WyswietlFigure;
import ekrany.Zamowienie;

public class PanelGlowny extends JPanel
{

	private JTabbedPane panel;
	Formularz form;
	WyswietlFigure wyswietlanie_figur;
	Zamowienie zamowienie;

	public PanelGlowny()
	{
		super(new GridLayout(1, 1));

		panel = new JTabbedPane();
		// EkranRysowanie();
		EkranFormularz();

		// EkranWyswietlFigure();

		// EkranZamowienie();
		panel.setSelectedComponent(form);
		this.add(panel);

		panel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	private void EkranFormularz()
	{
		form = new Formularz();
		panel.addTab("Formularz", form);
	}

	public static void main(String[] args)
	{

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				// Turn off metal's use of bold fonts
				configureUIElements();
				createAndShowGUI();
			}
		});
	}
	
	private static void configureUIElements()
	{
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		
		//JoptionPane translation
		UIManager.put("OptionPane.yesButtonText", "Tak");
		UIManager.put("OptionPane.noButtonText", "Nie");
	}

	private static void createAndShowGUI()
	{
		// Create and set up the window.
		JFrame frame = new JFrame("Maximus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		// Add content to the window.
		frame.add(new PanelGlowny(), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
