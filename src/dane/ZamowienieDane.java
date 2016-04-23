package dane;

import java.util.ArrayList;

import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.odbiorcy.Odbiorca;


public class ZamowienieDane
{
	
	public static ArrayList<FiguraZamowienie> figury = new ArrayList<FiguraZamowienie>();
	public static ArrayList<FiguraKontrolki> f_kontrolki = new ArrayList<FiguraKontrolki>();
	
	public static ArrayList<CzescKontolki> czesc_kontrolki = new ArrayList<CzescKontolki>();
	public static ArrayList<CzescKontolki> czesc_kontrolki_figura = new ArrayList<CzescKontolki>();
	
	//klient - dane
	public static String kod;
	public static Odbiorca odbiorca;
	public static Budowa budowa;
	public static Obiekt obiekt;
	public static Element element;
	
	
	
	
}
