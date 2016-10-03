package ekrany.optymalizacja;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dane.FiguraZamowienie;
import dodatki.MathHelper;
import dodatki.Tools;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.elementy.figury.Figura;
import modules.zamowienie.elementy.figury.FiguraLista;
import modules.zamowienie.elementy.figury.czesci.Czesc;
import optymalizacja.Evolution;
import pdf.OptymalizacjaPdf;

public class OpymalizacjaFrame extends JPanel
{
	private static final long serialVersionUID = 6507901662599568412L;
	private Element element;
	public JSlider grandIterations = new JSlider();
	public JSlider iterations = new JSlider();
	public JSlider numOfSuspects = new JSlider();
	public JTextField grandIterationsLabel = new JTextField("Ilość prób (duży wpływ na jakość)");
	public JTextField iterationsLabel = new JTextField("Wewnętrzna lość iteracji (średni wpływ na jakość)");
	public JTextField numOfSuspectsLabel = new JTextField("Ilość osobników (duży wpływ na jakość)");
	public JTextField grandIterationsValue = new JTextField(grandIterations.getValue() + "");
	public JTextField iterationsValue = new JTextField(iterations.getValue() + "");
	public JTextField numOfSuspectsValue = new JTextField(numOfSuspects.getValue() + "");
	private HashMap<Integer, ArrayList<FiguraZamowienie>> figury;
	private TreeSet<Integer> srednice = new TreeSet<Integer>();

	private JTextField wymiary_label = new JTextField("Długości");
	private JList<Integer> wymiary;
	private DefaultListModel<Integer> wymiary_model = new DefaultListModel<Integer>();
	private JTextField number_input = new JTextField("");
	private JButton dodaj_button = new JButton("Dodaj");

	private JButton optymalizuj_button = new JButton("Optymalizuj");

	public OpymalizacjaFrame(Element element)
	{
		this.setPreferredSize(Tools.getDimension(600, 400));
		this.setLayout(null);
		this.element = element;
		this.figury = getFilteredFigury(getFigury());

		initComponents();
		initParametryComponents();
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

	private void initParametryComponents()
	{
		setJSlider(grandIterations, grandIterationsValue);
		setJSlider(iterations, iterationsValue);
		setJSlider(numOfSuspects, numOfSuspectsValue);

		Tools.converToDisabledJTextField(grandIterationsLabel);
		Tools.converToDisabledJTextField(iterationsLabel);
		Tools.converToDisabledJTextField(numOfSuspectsLabel);
		Tools.converToDisabledJTextField(grandIterationsValue);
		Tools.converToDisabledJTextField(iterationsValue);
		Tools.converToDisabledJTextField(numOfSuspectsValue);

		this.add(grandIterations);
		this.add(iterations);
		this.add(numOfSuspects);
		this.add(grandIterationsLabel);
		this.add(iterationsLabel);
		this.add(numOfSuspectsLabel);
		this.add(grandIterationsValue);
		this.add(iterationsValue);
		this.add(numOfSuspectsValue);
	}

	private void setJSlider(JSlider slider, JTextField field)
	{
		slider.setMaximum(500);
		slider.setMinimum(1);
		slider.setValue(50);

		slider.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e)
			{
				field.setText(slider.getValue() + "");

			}
		});
	}

	private void setInitialElementsInList()
	{
		wymiary_model.addElement(1200);
		wymiary_model.addElement(1400);
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		resize();
		resizeParametry();
	}

	private HashMap<Integer, ArrayList<FiguraZamowienie>> getFilteredFigury(ArrayList<FiguraZamowienie> _figury)
	{
		HashMap<Integer, ArrayList<FiguraZamowienie>> hashFigury = new HashMap<Integer, ArrayList<FiguraZamowienie>>();
		Set<Integer> srednice = new TreeSet<Integer>();
		for (FiguraZamowienie fig_zam : _figury)
		{
			srednice.add(fig_zam.srednica);
		}

		for (Integer sr : srednice)
		{
			ArrayList<FiguraZamowienie> fig = new ArrayList<FiguraZamowienie>();

			for (FiguraZamowienie _fig : _figury)
			{
				if (_fig.srednica == sr && doOptymalizacji(_fig.maszyna))
				{
					fig.add(new FiguraZamowienie(_fig));
				}
			}

			if (fig.size() > 0)
			{
				hashFigury.put(sr, fig);
				this.srednice.add(sr);
			}
		}

		return hashFigury;
	}

	int[] doOptymalizacji = { 1, 2, 4, 8 };

	private boolean doOptymalizacji(int maszyna)
	{
		for (int item : doOptymalizacji)
		{
			if (item == maszyna)
			{
				return true;
			}
		}
		return false;
	}

	private ArrayList<FiguraZamowienie> getFigury()
	{
		ArrayList<FiguraZamowienie> figury_zamowienie = new ArrayList<FiguraZamowienie>();

		FiguraLista figury_lista = new FiguraLista(element.getId());
		ArrayList<Object> objects_figury = figury_lista.getList();
		int fig_size = objects_figury.size();

		for (int i = 0; i < fig_size; i++)
		{
			Figura fig_temp = (Figura) objects_figury.get(i);
			fig_temp.setCzesci();
			fig_temp.setCzesciAtrapy();

			FiguraZamowienie fig_zam = new FiguraZamowienie();
			modules.figury.Figura fig = new modules.figury.Figura();

			fig.setKod(fig_temp.getKod());

			for (Czesc czesc_temp : fig_temp.getCzesci())
			{
				modules.czesci.Czesc czesc = new modules.czesci.Czesc();
				czesc.setDlugosc(czesc_temp.getDlugosc());
				czesc.setKat(czesc_temp.getKat());
				czesc.setTyp(czesc_temp.getTyp());
				fig.addCzesc(czesc);
			}
			fig_zam.srednica = fig_temp.getSrednica();
			fig_zam.sworzen = fig_temp.getSworzen();
			fig_zam.maszyna = fig_temp.getMaszyna();
			fig_zam.ilosc_sztuk = fig_temp.getSztuk();
			fig_zam.uwagi = fig_temp.getUwagi();
			fig_zam.pozycja = fig_temp.getPozycja();
			fig_zam.figura = fig;
			figury_zamowienie.add(fig_zam);
		}
		return figury_zamowienie;
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

	protected void resizeParametry()
	{
		int x = 250;
		int y = 25;
		int width = 500;

		grandIterations.setSize(Tools.getDimension(width / 2, 25));
		grandIterationsLabel.setSize(Tools.getDimension(width / 2, 25));
		grandIterationsValue.setSize(Tools.getDimension(30, 25));
		iterations.setSize(Tools.getDimension(width / 2, 25));
		iterationsLabel.setSize(Tools.getDimension(width / 2, 25));
		iterationsValue.setSize(Tools.getDimension(30, 25));
		numOfSuspects.setSize(Tools.getDimension(width / 2, 25));
		numOfSuspectsLabel.setSize(Tools.getDimension(width / 2, 25));
		numOfSuspectsValue.setSize(Tools.getDimension(30, 25));

		grandIterations.setLocation(Tools.getPoint(x, y += 25));
		grandIterationsValue.setLocation(Tools.getPoint(x - 30, y));
		grandIterationsLabel.setLocation(Tools.getPoint(x, y += 25));

		iterations.setLocation(Tools.getPoint(x, y += 25));
		iterationsValue.setLocation(Tools.getPoint(x - 30, y));
		iterationsLabel.setLocation(Tools.getPoint(x, y += 25));
		numOfSuspects.setLocation(Tools.getPoint(x, y += 25));
		numOfSuspectsValue.setLocation(Tools.getPoint(x - 30, y));
		numOfSuspectsLabel.setLocation(Tools.getPoint(x, y += 25));
	}
	
	private void optymalizuj()
	{
		ProgressFrame pFrame = null;
		for (int sr : srednice)
		{
			Evolution evo = new Evolution(getDlugosci(figury.get(sr), sr), getMax());
			pFrame = new ProgressFrame();
			evo.setParameters(grandIterations.getValue(), iterations.getValue(), numOfSuspects.getValue(), pFrame);
			evo.run();
			pFrame.dispose();
			System.out.println("Średnica:" + sr + ", odpad:" + evo.waste);
			new OptymalizacjaPdf(new Date().getTime()+"", sr, evo.sol, figury.get(sr) ).drukuj();
		}
	}

	private int[] getDlugosci(ArrayList<FiguraZamowienie> fig_zam, int srednica)
	{
		ArrayList<FiguraZamowienie> newFigZam = new ArrayList<FiguraZamowienie>();
		
		ArrayList<Integer> _dlugosci = new ArrayList<Integer>();
		for (FiguraZamowienie _figZam : fig_zam)
		{
			int dlugosc = (int) Math.ceil(MathHelper.obliczDlugoscRzeczywista(_figZam));
			for(int j = 0; j < _figZam.ilosc_sztuk; j++) 
			{
				_dlugosci.add(dlugosc);
				newFigZam.add(_figZam);
			}
		}
		int size = _dlugosci.size();
		int[] dlugosci = new int[size];
		for(int i = 0; i < size; i++) 
		{
			dlugosci[i] = _dlugosci.get(i);
		}
		
		figury.put(srednica, newFigZam);

		return dlugosci;
	}

	private Integer[] getMax()
	{
		int size = wymiary_model.size();
		Integer[] max = new Integer[size];

		for (int i = 0; i < size; i++)
		{
			max[i] = wymiary_model.get(i);
		}

		return max;
	}

}