import java.awt.Color;
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
	
	/**Funkcja czyszczaca obszar*/
        public void clear() {
                for (int i = 0; i < size; i++)
                        for (int j = 0; j < size; j++)
                                tab[i][j] = 0;
        }
 
        /**Funkcja ustawiajaca ziarna w podanym obszarze
         * @param color rodzaj ziarna
         * @param x wspólrzedna pozioma
         * @param y wspólrzedna pionowa
         */
        public void setState(int color, int x, int y) {
                tab[x][y] = color;
        }

/**Funkcja generuj¹ca podan¹ iloœæ ró¿nych ziaren w losowych miejscach obszaru
         * @param number liczba ziaren do wygenerowania
         */
        public void generateRandom(int number) {
                Random rand = new Random();
                int tmpx = 0, tmpy = 0;
                tmpx = rand.nextInt(size);
                tmpy = rand.nextInt(size);
                for (int i = 0; i < number; i++) {
                        // ograniczenie liczby prób losowania ¿eby program siê nie zawiesi³
                        // przy braku wolnych miejsc
                        int trials = 0;
                        while (tab[tmpx][tmpy] != 0 && trials < 100) {
                                tmpx = rand.nextInt(size);
                                tmpy = rand.nextInt(size);
                                trials++;
                        }
                        if (tab[tmpx][tmpy] == 0) {
                                GameWindow.colorCounter++;
                                GameWindow.colors.add(new Color(rand.nextInt(255), rand
                                                .nextInt(255), rand.nextInt(255)));
                                setState(GameWindow.colorCounter, tmpx, tmpy);
                        }
                }
        }

/**Funkcja rysuj¹ca uk³ad powierzchni.
         *@param g obiekt graficzny na którym rysujemy
         */
        public void printTab(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 500, 500);
                g.setColor(new Color(255, 110, 180));
                for (int x = 0; x < size; x++) {
                        for (int y = 0; y < size; y++) {
                                if (tab[x][y] != 0) {
                                        g.setColor(GameWindow.colors.get(tab[x][y] - 1));
                                        g.fillOval(x * GameWindow.WIDTH, y * GameWindow.WIDTH,
                                                        GameWindow.WIDTH * GameWindow.thickness,
                                                        GameWindow.WIDTH * GameWindow.thickness);
                                }
                        }
                }
        }



}
