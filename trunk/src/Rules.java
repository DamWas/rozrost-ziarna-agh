import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Klasa definujaca rodzaje sasiedztwa.
 * 
 * @author Mateusz Kaflowski, Marcin Gladosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-05-24
 */

public class Rules {
	/**
	 * Funkcja zwraca najczesciej wystepujacy element z kolekcji.
	 * 
	 * @param colors
	 *            kolekcja do przeszukania
	 * @return najczesciej wystepujacy element
	 */
	private static int getTheMostPopular(List<Integer> colors) {
		Collections.sort(colors);
		int pre = 0;
		int actualColor = 0;
		int max = 0;
		int licznik = 0;
		Iterator<Integer> it = colors.iterator();
		Random rand = new Random();
		while (it.hasNext()) {
			int tmp = it.next();
			if (tmp == actualColor) {
				licznik++;
			} else {
				if (licznik >= max && actualColor != 0) {
					if (licznik == max) {
						if (rand.nextBoolean()) {
							max = licznik;
							pre = actualColor;
						}
					} else {
						max = licznik;
						pre = actualColor;
					}
				}
				licznik = 1;
				actualColor = tmp;
			}
		}
		if (licznik >= max && actualColor != 0) {
			if (licznik == max) {
				if (rand.nextBoolean()) {
					max = licznik;
					pre = actualColor;
				}
			} else {
				max = licznik;
				pre = actualColor;
			}
		}
		return pre;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * Moor'a.
	 * 
	 * @param x
	 *            pozioma wspolrzedna badanego ziarna
	 * @param y
	 *            pionowa wspolrzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcMoore(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {

		} else {
			if (x == 0)
				;
			else
				colors.add(tab[x - 1][y - 1]);
			colors.add(tab[x][y - 1]);
			if (x == size - 1)
				;
			else
				colors.add(tab[x + 1][y - 1]);
		}

		// dol:------------------
		if (y == size - 1) {

		} else {
			if (x == 0)
				;
			else
				colors.add(tab[x - 1][y + 1]);
			colors.add(tab[x][y + 1]);
			if (x == size - 1)
				;
			else
				colors.add(tab[x + 1][y + 1]);
		}

		// lewo:-------------------
		if (x == 0) {
			;
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			;
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	// updated by Krystian B. 28.05.2013
	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * heksagonalnym lewym z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcHexLP(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {
			if (x == 0)
				colors.add(tab[size - 1][size - 1]);
			else
				colors.add(tab[x - 1][size - 1]);
			colors.add(tab[x][size - 1]);

		} else {
			if (x == 0)
				colors.add(tab[size - 1][y - 1]);
			else
				colors.add(tab[x - 1][y - 1]);
			colors.add(tab[x][y - 1]);

		}

		// do³:------------------
		if (y == size - 1) {

			colors.add(tab[x][0]);
			if (x == size - 1)
				colors.add(tab[0][0]);
			else
				colors.add(tab[x + 1][0]);
		} else {

			colors.add(tab[x][y + 1]);
			if (x == size - 1)
				colors.add(tab[0][y + 1]);
			else
				colors.add(tab[x + 1][y + 1]);
		}

		// lewo:-------------------
		if (x == 0) {
			colors.add(tab[size - 1][y]);
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			colors.add(tab[0][y]);
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * heksagonalnym lewym.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcHexL(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {

		} else {
			if (x == 0)
				;
			else
				colors.add(tab[x - 1][y - 1]);
			colors.add(tab[x][y - 1]);

		}

		// do³:------------------
		if (y == size - 1) {

		} else {

			colors.add(tab[x][y + 1]);
			if (x == size - 1)
				;
			else
				colors.add(tab[x + 1][y + 1]);
		}

		// lewo:-------------------
		if (x == 0) {
			;
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			;
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * heksagonalnym prawym z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcHexRP(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {

			colors.add(tab[x][size - 1]);
			if (x == size - 1)
				colors.add(tab[0][size - 1]);
			else
				colors.add(tab[x + 1][size - 1]);
		} else {

			colors.add(tab[x][y - 1]);
			if (x == size - 1)
				colors.add(tab[0][y - 1]);
			else
				colors.add(tab[x + 1][y - 1]);
		}

		// do³:------------------
		if (y == size - 1) {
			if (x == 0)
				colors.add(tab[size - 1][0]);
			else
				colors.add(tab[x - 1][0]);
			colors.add(tab[x][0]);

		} else {
			if (x == 0)
				colors.add(tab[size - 1][y + 1]);
			else
				colors.add(tab[x - 1][y + 1]);
			colors.add(tab[x][y + 1]);

		}

		// lewo:-------------------
		if (x == 0) {
			colors.add(tab[size - 1][y]);
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			colors.add(tab[0][y]);
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * heksagonalnym prawym.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcHexR(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {

		} else {

			colors.add(tab[x][y - 1]);
			if (x == size - 1)
				;
			else
				colors.add(tab[x + 1][y - 1]);
		}

		// do³:------------------
		if (y == size - 1) {

		} else {
			if (x == 0)
				;
			else
				colors.add(tab[x - 1][y + 1]);
			colors.add(tab[x][y + 1]);

		}

		// lewo:-------------------
		if (x == 0) {
			;
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			;
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym lewym z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaLP(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {
			if (x == 0)
				colors.add(tab[size - 1][size - 1]);
			else
				colors.add(tab[x - 1][size - 1]);
			colors.add(tab[x][size - 1]);

		} else {
			if (x == 0)
				colors.add(tab[size - 1][y - 1]);
			else
				colors.add(tab[x - 1][y - 1]);
			colors.add(tab[x][y - 1]);

		}

		// do³:------------------
		if (y == size - 1) {
			if (x == 0)
				colors.add(tab[size - 1][0]);
			else
				colors.add(tab[x - 1][0]);
			colors.add(tab[x][0]);

		} else {
			if (x == 0)
				colors.add(tab[size - 1][y + 1]);
			else
				colors.add(tab[x - 1][y + 1]);
			colors.add(tab[x][y + 1]);

		}

		// lewo:-------------------
		if (x == 0) {
			colors.add(tab[size - 1][y]);
		} else {
			colors.add(tab[x - 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym lewym.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaL(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {

		} else {
			if (x == 0)
				;
			else
				colors.add(tab[x - 1][y - 1]);
			colors.add(tab[x][y - 1]);

		}

		// do³:------------------
		if (y == size - 1) {

		} else {
			if (x == 0)
				;
			else
				colors.add(tab[x - 1][y + 1]);
			colors.add(tab[x][y + 1]);

		}

		// lewo:-------------------
		if (x == 0) {
			;
		} else {
			colors.add(tab[x - 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym prawym z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaRP(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {

			colors.add(tab[x][size - 1]);
			if (x == size - 1)
				colors.add(tab[0][size - 1]);
			else
				colors.add(tab[x + 1][size - 1]);
		} else {

			colors.add(tab[x][y - 1]);
			if (x == size - 1)
				colors.add(tab[0][y - 1]);
			else
				colors.add(tab[x + 1][y - 1]);
		}

		// do³:------------------
		if (y == size - 1) {

			colors.add(tab[x][0]);
			if (x == size - 1)
				colors.add(tab[0][0]);
			else
				colors.add(tab[x + 1][0]);
		} else {

			colors.add(tab[x][y + 1]);
			if (x == size - 1)
				colors.add(tab[0][y + 1]);
			else
				colors.add(tab[x + 1][y + 1]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			colors.add(tab[0][y]);
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym prawym.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaR(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {

		} else {

			colors.add(tab[x][y - 1]);
			if (x == size - 1)
				;
			else
				colors.add(tab[x + 1][y - 1]);
		}

		// do³:------------------
		if (y == size - 1) {

		} else {

			colors.add(tab[x][y + 1]);
			if (x == size - 1)
				;
			else
				colors.add(tab[x + 1][y + 1]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			;
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym gornym z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaUP(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {
			if (x == 0)
				colors.add(tab[size - 1][size - 1]);
			else
				colors.add(tab[x - 1][size - 1]);
			colors.add(tab[x][size - 1]);
			if (x == size - 1)
				colors.add(tab[0][size - 1]);
			else
				colors.add(tab[x + 1][size - 1]);
		} else {
			if (x == 0)
				colors.add(tab[size - 1][y - 1]);
			else
				colors.add(tab[x - 1][y - 1]);
			colors.add(tab[x][y - 1]);
			if (x == size - 1)
				colors.add(tab[0][y - 1]);
			else
				colors.add(tab[x + 1][y - 1]);
		}

		// lewo:-------------------
		if (x == 0) {
			colors.add(tab[size - 1][y]);
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			colors.add(tab[0][y]);
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym gornym.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaU(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {

		} else {
			if (x == 0)
				;
			else
				colors.add(tab[x - 1][y - 1]);
			colors.add(tab[x][y - 1]);
			if (x == size - 1)
				;
			else
				colors.add(tab[x + 1][y - 1]);
		}

		// lewo:-------------------
		if (x == 0) {
			;
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			;
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym dolnym z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaBP(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// do³:------------------
		if (y == size - 1) {
			if (x == 0)
				colors.add(tab[size - 1][0]);
			else
				colors.add(tab[x - 1][0]);
			colors.add(tab[x][0]);
			if (x == size - 1)
				colors.add(tab[0][0]);
			else
				colors.add(tab[x + 1][0]);
		} else {
			if (x == 0)
				colors.add(tab[size - 1][y + 1]);
			else
				colors.add(tab[x - 1][y + 1]);
			colors.add(tab[x][y + 1]);
			if (x == size - 1)
				colors.add(tab[0][y + 1]);
			else
				colors.add(tab[x + 1][y + 1]);
		}

		// lewo:-------------------
		if (x == 0) {
			colors.add(tab[size - 1][y]);
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			colors.add(tab[0][y]);
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym dolnym.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaB(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// do³:------------------
		if (y == size - 1) {

		} else {
			if (x == 0)
				;
			else
				colors.add(tab[x - 1][y + 1]);
			colors.add(tab[x][y + 1]);
			if (x == size - 1)
				;
			else
				colors.add(tab[x + 1][y + 1]);
		}

		// lewo:-------------------
		if (x == 0) {
			;
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			;
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * heksagonalnym losowym.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcHexRandom(int x, int y, int tab[][], int size) {
		Random rand = new Random();
		switch (rand.nextInt(2)) {
		case 0:
			return calcHexL(x, y, tab, size);
		case 1:
			return calcHexR(x, y, tab, size);

		default:
			break;
		}
		return 0;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * heksagonalnym losowym z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcHexRandomP(int x, int y, int tab[][], int size) {
		Random rand = new Random();
		switch (rand.nextInt(2)) {
		case 0:
			return calcHexLP(x, y, tab, size);
		case 1:
			return calcHexRP(x, y, tab, size);

		default:
			break;
		}
		return 0;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym losowym.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaRandom(int x, int y, int tab[][], int size) {
		Random rand = new Random();
		switch (rand.nextInt(4)) {
		case 0:
			return calcPentaB(x, y, tab, size);
		case 1:
			return calcPentaU(x, y, tab, size);
		case 2:
			return calcPentaR(x, y, tab, size);
		case 3:
			return calcPentaL(x, y, tab, size);

		default:
			break;
		}
		return 0;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * pentagonalnym losowym z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcPentaRandomP(int x, int y, int tab[][], int size) {
		Random rand = new Random();
		switch (rand.nextInt(4)) {
		case 0:
			return calcPentaBP(x, y, tab, size);
		case 1:
			return calcPentaUP(x, y, tab, size);
		case 2:
			return calcPentaRP(x, y, tab, size);
		case 3:
			return calcPentaLP(x, y, tab, size);

		default:
			break;
		}
		return 0;
	}

	// ---------- koniec update by Krystian B. 28.05.2013

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie
	 * Moor'a z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcMooreP(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {
			if (x == 0)
				colors.add(tab[size - 1][size - 1]);
			else
				colors.add(tab[x - 1][size - 1]);
			colors.add(tab[x][size - 1]);
			if (x == size - 1)
				colors.add(tab[0][size - 1]);
			else
				colors.add(tab[x + 1][size - 1]);
		} else {
			if (x == 0)
				colors.add(tab[size - 1][y - 1]);
			else
				colors.add(tab[x - 1][y - 1]);
			colors.add(tab[x][y - 1]);
			if (x == size - 1)
				colors.add(tab[0][y - 1]);
			else
				colors.add(tab[x + 1][y - 1]);
		}

		// do³:------------------
		if (y == size - 1) {
			if (x == 0)
				colors.add(tab[size - 1][0]);
			else
				colors.add(tab[x - 1][0]);
			colors.add(tab[x][0]);
			if (x == size - 1)
				colors.add(tab[0][0]);
			else
				colors.add(tab[x + 1][0]);
		} else {
			if (x == 0)
				colors.add(tab[size - 1][y + 1]);
			else
				colors.add(tab[x - 1][y + 1]);
			colors.add(tab[x][y + 1]);
			if (x == size - 1)
				colors.add(tab[0][y + 1]);
			else
				colors.add(tab[x + 1][y + 1]);
		}

		// lewo:-------------------
		if (x == 0) {
			colors.add(tab[size - 1][y]);
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			colors.add(tab[0][y]);
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie von
	 * Neumana z periodycznoscia.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcVNP(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {
			colors.add(tab[x][size - 1]);
		} else {
			colors.add(tab[x][y - 1]);
		}

		// do³:------------------
		if (y == size - 1) {

			colors.add(tab[x][0]);

		} else {

			colors.add(tab[x][y + 1]);

		}

		// lewo:-------------------
		if (x == 0) {
			colors.add(tab[size - 1][y]);
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			colors.add(tab[0][y]);
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}

	/**
	 * Funkcja zwracajaca sasiada najczesciej wystepujacego w sasiedztwie von
	 * Neumana.
	 * 
	 * @param x
	 *            pozioma wspo³rzedna badanego ziarna
	 * @param y
	 *            pionowa wspo³rzedna badanego ziarna
	 * @param tab
	 *            obszar w jakim znajduje sie ziarno
	 * @param size
	 *            rozmiar obszaru
	 * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku
	 *         sasiadow
	 */
	public static int calcVN(int x, int y, int tab[][], int size) {

		List<Integer> colors = new LinkedList<Integer>();
		int predominant = 0;

		// gora:-------------------
		if (y == 0) {
			;
		} else {
			colors.add(tab[x][y - 1]);
		}

		// do³:------------------
		if (y == size - 1) {

			;

		} else {

			colors.add(tab[x][y + 1]);

		}

		// lewo:-------------------
		if (x == 0) {
			;
		} else {
			colors.add(tab[x - 1][y]);
		}

		// prawo:--------------------
		if (x == size - 1) {
			;
		} else {
			colors.add(tab[x + 1][y]);
		}

		predominant = getTheMostPopular(colors);

		return predominant;
	}


}
