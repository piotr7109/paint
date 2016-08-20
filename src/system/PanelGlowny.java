package system;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ekrany.Formularz;
import ekrany.Rysowanie;
import ekrany.WyswietlFigure;
import ekrany.Zamowienie;
import modules.czesci.Czesc;
import modules.figury.Figura;

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
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI()
	{
		// Create and set up the window.
		JFrame frame = new JFrame("Projekt Blacha");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		// Add content to the window.
		frame.add(new PanelGlowny(), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
