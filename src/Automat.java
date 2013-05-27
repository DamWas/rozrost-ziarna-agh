public class Automat {

	/**
	 * @author Mateusz Kaflowski, Marcin G�adosz, Krystian Bersztolc, Witold
	 *         Gramatyka, Micha� Grabarczyk
	 * @version 1.0
	 * @since 2013-05-24
	 */

	// POLA:------------------------
	/** Rozmiar obszaru rozrostu */
	public int size;
	/**
	 * Tablica posiadaj�ca informacje o rodzaju ziarna w poszczeg�lnych
	 * miejscach obszaru. 0 dla braku ziarna.
	 */
	public int tab[][];
	/**
	 * Tablica posiadaj�ca informacje o rodzaju ziarna zrekrystalizowanego w
	 * poszczeg�lnych miejscach obszaru. 0 dla braku ziarna.
	 */
	public int tabR[][];
	/**
	 * Tablica posiadaj�ca informacje ziarnach w poszczeg�lnych miejscach
	 * obszaru.
	 */
	public Cell cells[][];
	/** Zmienna okre�laj�ca periodyczno�� */
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

}
