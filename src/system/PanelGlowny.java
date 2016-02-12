package system;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dodatki.Luk;
import ekrany.Formularz;
import ekrany.Rysowanie;
import ekrany.WyswietlFigure;
import modules.czesci.Czesc;
import modules.figury.Figura;

public class PanelGlowny extends JPanel
{

	private JTabbedPane panel;
	Rysowanie rys;
	Formularz form;
	WyswietlFigure wyswietlanie_figur;

	public PanelGlowny()
	{
		super(new GridLayout(1, 1));

		panel = new JTabbedPane();

		EkranFormularz();
		EkranRysowanie();
		EkranWyswietlFigure();

		this.add(panel);

		panel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	private static void createAndShowGUI()
	{
		// Create and set up the window.
		JFrame frame = new JFrame("Projekt Blacha");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new PanelGlowny(), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private void EkranWyswietlFigure()
	{
		wyswietlanie_figur = new WyswietlFigure();
		panel.addTab("Wyœwietlanie figur", wyswietlanie_figur);
		EkranWyswietlFigureEvent();
		panel.setSelectedComponent(wyswietlanie_figur);
	}
	
	private void EkranWyswietlFigureEvent()
	{
		wyswietlanie_figur.odswiez.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panel.remove(wyswietlanie_figur);
				EkranWyswietlFigure();
				
			}
		});
	}
	
	private void EkranFormularz()
	{
		form = new Formularz();
		panel.addTab("Formularz", form);
	}

	private void EkranRysowanie()
	{
		rys = new Rysowanie(this);
		EkranRysowanieEvent();
		panel.addTab("Rysowanie", rys);
		
	}

	private void EkranRysowanieEvent()
	{
		rys.reset.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent evt)
			{
				panel.remove(rys);
				EkranRysowanie();
			}
		});

	}

	public void EkranRysowanieZapiszElement()
	{
		Figura fig = new Figura();
		int id_figury = fig.insert();
		int size = rys._dlugosci.size();
		for (int i = 0; i < size; i++)
		{
			Czesc czesc = new Czesc();
			czesc.setDlugosc(rys._dlugosci.get(i));
			czesc.setKat(rys._katy.get(i));
			czesc.setIdFigury(id_figury);
			czesc.setTyp(rys._typy_figur.get(i));
			czesc.insert();
		}

		//panel.remove(rys);
		//EkranRysowanie();
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
}
