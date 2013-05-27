
public class Automat {
	
	/**
	 * @author      Mateusz Kaflowski, Marcin G³adosz, Krystian Bersztolc, Witold Gramatyka, Micha³ Grabarczyk
	 * @version     1.0
	 * @since       2013-05-24
	 */
	
	// POLA:------------------------
		public int size;
		public int tab[][];
		public int tabR[][];
		public Cell cells[][];
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
