package ekrany.optymalizacja;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dodatki.Tools;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.elementy.figury.Figura;

public class OpymalizacjaFrame extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6507901662599568412L;
	private Element element;
	private JFrame frame;
	private ArrayList<Figura> figury;

	private JTextField wymiary_label = new JTextField("Długości");
	private JList<Integer> wymiary;
	private DefaultListModel<Integer> wymiary_model = new DefaultListModel<Integer>();
	private JTextField number_input = new JTextField("");
	private JButton dodaj_button = new JButton("Dodaj");

	private JButton optymalizuj_button = new JButton("Optymalizuj");

	public OpymalizacjaFrame(Element element, JFrame frame)
	{
		this.setPreferredSize(Tools.getDimension(400, 400));
		this.setLayout(null);

		this.frame = frame;
		this.element = element;
		this.figury = getFilteredFigury();

		initComponents();
	}

	private void initComponents()
	{
		wymiary = new JList<Integer>(wymiary_model);
		setInitialElementsInList();
		wymiary.setBackground(Color.LIGHT_GRAY);
		Tools.converToDisabledJTextField(wymiary_label);

		dodaj_button.addActionListener(dodajButtonAL());
		
		optymalizuj_button.setBackground(Color.DARK_GRAY);
		optymalizuj_button.setForeground(Color.YELLOW);
		optymalizuj_button.addActionListener(optymalizujButtonAL());

		add(number_input);
		add(dodaj_button);
		add(wymiary);
		add(wymiary_label);

		add(optymalizuj_button);
	}

	private void setInitialElementsInList()
	{
		wymiary_model.addElement(12);
		wymiary_model.addElement(14);
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		resize();
	}

	
	private ArrayList<Figura> getFilteredFigury()
	{
		// TODO
		return null;
	}
	
	private ActionListener optymalizujButtonAL()
	{
		return new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				optymalizuj();
				
			}
		};
	}
	
	private void optymalizuj()
	{
		//TODO
	}

	private ActionListener dodajButtonAL()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int wymiar = 0;
				try
				{
					wymiar = Integer.parseInt(number_input.getText());
					dodajNowyWymiar(wymiar);
				}
				catch (NumberFormatException error)
				{
					Tools.showSimpleMessage("To nie jest liczba!", JOptionPane.ERROR_MESSAGE);
				}
				finally
				{
					number_input.setText("");
				}
			}
		};
	}

	private void dodajNowyWymiar(int wymiar)
	{
		wymiary_model.addElement(wymiar);
	}

	protected void resize()
	{
		int x = 50;
		int width = 150;

		wymiary_label.setSize(Tools.getDimension(width, 25));
		wymiary_label.setLocation(Tools.getPoint(x, 25));

		wymiary.setSize(Tools.getDimension(width, 150));
		wymiary.setLocation(Tools.getPoint(x, 50));

		number_input.setSize(Tools.getDimension(width / 2, 25));
		number_input.setLocation(Tools.getPoint(x, 200));

		dodaj_button.setSize(Tools.getDimension(width / 2, 25));
		dodaj_button.setLocation(Tools.getPoint(x + width / 2, 200));

		optymalizuj_button.setSize(Tools.getDimension(width, 50));
		optymalizuj_button.setLocation(Tools.getPoint(x, 300));

		for (Component comp : this.getComponents())
		{
			((JComponent) comp).setFont(new Font("", 0, Tools.rescale(12)));
		}

	}
}
