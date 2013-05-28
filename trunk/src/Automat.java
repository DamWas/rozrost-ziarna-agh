import java.util.Random;

/**Klasa definuj¹ca najwa¿niejsze elementy automtu komórkowego.
	 * @author Mateusz Kaflowski, Marcin G³adosz, Krystian Bersztolc, Witold
	 *         Gramatyka, Micha³ Grabarczyk
	 * @version 1.0
	 * @since 2013-05-24
	 */

public class Automat {

	// POLA:------------------------
	/** Rozmiar obszaru rozrostu. */
	public int size;
	/**
	 * Tablica posiadaj¹ca informacje o rodzaju ziarna w poszczególnych
	 * miejscach obszaru. 0 dla braku ziarna.
	 */
	public int tab[][];
	/**
	 * Tablica posiadaj¹ca informacje o rodzaju ziarna zrekrystalizowanego w
	 * poszczególnych miejscach obszaru. 0 dla braku ziarna.
	 */
	public int tabR[][];
	/**
	 * Tablica posiadaj¹ca informacje ziarnach w poszczególnych miejscach
	 * obszaru.
	 */
	public Cell cells[][];
	/** Zmienna okreœlaj¹ca periodycznoœæ. */
	public boolean isPeriodic = false;

	// KONSTRUKTORY:----------------
	public Automat(int size) {
		this.size = size;
		tab = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				tab[i][j] = 0;

		tabR = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				tabR[i][j] = 0;

		cells = new Cell[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				cells[i][j] = new Cell(i, j);

	}
	
	/**Funkcja generuj¹ca nastêpny cykl ¿ycia.*/
	public void genNext() {
		int tmp[][] = new int[size][size];

		for (int x = 0; x < size; x++)
			for (int y = 0; y < size; y++) {
				int selected = 0; //TODO: wybor opcji z jakiegoœ comboBoxa
				if (selected == 8) {
					Random rand = new Random();
					selected = rand.nextInt(8);
				}

				if (tab[x][y] == 0)
					switch (selected) {
					case 0:
						if (isPeriodic)
							tmp[x][y] = Rules.calcMooreP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcMoore(x, y, tab, size);
						break;

					case 1:
						if (isPeriodic)
							tmp[x][y] = Rules.calcVNP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcVN(x, y, tab, size);
						break;

					case 2:
						if (isPeriodic)
							tmp[x][y] = Rules.calcHexLP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcHexL(x, y, tab, size);
						break;

					case 3:
						if (isPeriodic)
							tmp[x][y] = Rules.calcHexRP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcHexR(x, y, tab, size);
						break;

					case 4:
						if (isPeriodic)
							tmp[x][y] = Rules.calcPentaLP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcPentaL(x, y, tab, size);
						break;

					case 5:
						if (isPeriodic)
							tmp[x][y] = Rules.calcPentaRP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcPentaR(x, y, tab, size);
						break;

					case 6:
						if (isPeriodic)
							tmp[x][y] = Rules.calcPentaUP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcPentaU(x, y, tab, size);
						break;

					case 7:
						if (isPeriodic)
							tmp[x][y] = Rules.calcPentaBP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcPentaB(x, y, tab, size);
						break;

					case 9:
						if (isPeriodic)
							tmp[x][y] = Rules.calcHexRandomP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcHexRandom(x, y, tab, size);
						break;
					case 10:
						if (isPeriodic)
							tmp[x][y] = Rules.calcPentaRandomP(x, y, tab, size);
						else
							tmp[x][y] = Rules.calcPentaRandom(x, y, tab, size);
						break;

					default:
						break;
					}
				else
					tmp[x][y] = tab[x][y];
			}

		tab = tmp;
	}

}
