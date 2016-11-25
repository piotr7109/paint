package dodatki;
/**
 * Prosty stoper
 * 
 * @author kodatnik.blogspot.com
 */
public class Stoper {
	// pola prywatne klasy
	// czas startu stopera
	private long start;
	// czas stopu stopera
	private long stop;
	// nazwa stopera
	private String nazwa;

	// konstruktor bezparametrowy
	public Stoper() {
		// przypisujemy pusty �a�cuch do pola nazwa
		// wywo�uj�c konstruktor jednoparametrowy z naszej klasy
		this("");
	}

	// konstruktor z jednym parametrem - nazwa stopera
	public Stoper(String nazwa) {
		// przypisujemy do pola nazwa przekazany �a�cuch tekstowy
		this.nazwa = nazwa;
	}

	// metoda uruchamiana przy starcie stopera
	public void start() {
		// pobieramy aktualny czas - start stopera
		start = System.currentTimeMillis();
	}

	// metoda zatrzymuj�ca nasz stoper
	public void stop() {
		// pobieramy aktualny czas - stop stopera
		stop = System.currentTimeMillis();
	}

	// metoda zwraca w sekundach czas pomi�dzy uruchomieniem, a zatrzymaniem
	// stopera
	public double pobierzWynik() {
		// zamiana milisekund na sekundy
		return (stop - start) / 1000.0;
	}

	// przes�oni�ta metoda toString()
	public String toString() {
		// zwracamy w formie tekstowej informacje o naszym stoperze
		return nazwa + ": " + this.pobierzWynik() + " s.";
	}
}