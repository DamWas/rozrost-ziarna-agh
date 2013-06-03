import java.util.Random;

/**
 * Klasa reprezentujaca ziarno/komorke.½
 * 
 * @author Mateusz Kaflowski, Marcin Gladosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-05-24
 */
public class Cell {
	/** Liczba dyslokacji w komorce */
	public double dislocation;
	/** Polozenie komorki */
	int x, y;
	/** Zmienna mowiaca czy komorka zrekrystalizowala */
	boolean recrystallized;

	/**
	 * konstruktor
	 * 
	 * @pX polozenie X
	 * @pY polozenie Y
	 */
	public Cell(int pX, int pY) {
		dislocation = 0;
		x = pX;
		y = pY;
		recrystallized = false;
	}

	/**
	 * Funkcja dodajaca dyslokacje do komorki
	 * 
	 * @param pDislocation
	 *            dyslokacja do dodania
	 */
	public void addDislocation(double pDislocation) {
		Random rand = new Random();
		double toAdd = 0;
		if (isOnBorder())
			toAdd = pDislocation * (rand.nextInt(110) + 70) / 100;
		else
			toAdd = pDislocation * rand.nextInt(30) / 100;
		dislocation += toAdd;
		Recrystallization.dislocation -= toAdd;
	}

	/** Funkcja sprawdzajaca czy ziarno lezy na granicy */
	public boolean isOnBorder() {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				if (x + i < 0 || x + i >= GameWindow.automat.size || y + j < 0
						|| y + j >= GameWindow.automat.size)
					continue;
				if (GameWindow.automat.tab[x][y] != GameWindow.automat.tab[x
						+ i][y + j])
					return true;

			}
		return false;
	}

}
