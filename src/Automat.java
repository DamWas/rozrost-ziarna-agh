import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import mpi.MPI;
import mpi.Request;

/**
 * Klasa definiujaca najwazniejsze elementy automatu komorkowego.
 * 
 * @author Mateusz Kaflowski, Marcin Gladosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-05-24
 */

public class Automat {

	// POLA:------------------------
	/** Rozmiar obszaru rozrostu. */
	public static int size;
	/**
	 * Tablica posiadajaca informacje o rodzaju ziarna w poszczegolnych
	 * miejscach obszaru. 0 dla braku ziarna.
	 */
	public static int tab[][];
	/**
	 * Tablica posiadajaca informacje o rodzaju ziarna zrekrystalizowanego w
	 * poszczegolnych miejscach obszaru. 0 dla braku ziarna.
	 */
	public int tabR[][];
	/**
	 * Tablica posiadajaca informacje ziarnach w poszczegolnych miejscach
	 * obszaru.
	 */
	public Cell cells[][];
	/** Zmienna okreslajaca periodycznosc. */
	public static boolean isPeriodic = false;

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

	/** Funkcja generujaca nastepny cykl zycia. */
	public void genNext() {
		int rank = MPI.COMM_WORLD.Rank();
		System.out.println("jestem proces : "+rank); // najstarsi gorale nie wiedza czemu bez tej linijki sypie bledami
		
		int[] msgTo1	= {1};
		int[] msgTo2	= {2};
		int[] msgTo3	= {3};
		
		int tmp[][] = new int[size][size]; // tego nie powinno tu byc
		
		for (int x = 0; x < size ; x++) // powinno byc x+=3 zamiast x++ i x < size -3 zamiast x < size
			for (int y = 0; y < size; y++) { // po y powinno w ogole nie iterowac, wysyla wiadomosc 
											 //	procesu ktory wiersz ma przerobic i on sobie iteruje po y
				
				msgTo1[0]=x;		// pierwszy proces dostaje x'owy wiersz
				msgTo2[0]=x+1;		// drugi dostaje nast wiersz
				msgTo3[0]=x+2;		// trzeci dostaje kolejny
				
				
				Request sReq1 = 	MPI.COMM_WORLD.Isend(msgTo1, 0, msgTo1.length, MPI.INT, 1, 99);
				Request sReq2 = 	MPI.COMM_WORLD.Isend(msgTo2, 0, msgTo2.length, MPI.INT, 2, 99);
				Request sReq3 = 	MPI.COMM_WORLD.Isend(msgTo3, 0, msgTo3.length, MPI.INT, 3, 99);
			
				
				switchNext(tmp,x,y); // tego nie powinno tu byc
			}
		tab = tmp; // i tego tez nie 
	}
	
	

	/**Funkcja pomocnicza stosujaca wybrane sasiedztwo.
	 * @param tmp przestrzen wynikowa
	 * @param x polozenie poziome
	 * @param y polozenie pionowe
	 */
	public static void switchNext(int[][] tmp, int x, int y) {
		
		int selected = MyWindow.comboBox.getSelectedIndex();
		//int selected = 1;
		if (selected == 8) {
			Random rand = new Random();
			selected = rand.nextInt(8);
		}

		if (tab[x][y] == 0)
			switch (selected) {
			case 0:
				if (isPeriodic)
					tmp[x][y] = Rules.calcMooreP(x, y, tab, size);
				else{
					tmp[x][y] = Rules.calcMoore(x, y, tab, size);
					
				}
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



	/** Funkcja generujaca nastepny cykl zycia dla rekrystalizacji. */
	public void genNextR() {
                int tmp[][] = new int[size][size];
 
                for (int x = 0; x < size; x++)
                        for (int y = 0; y < size; y++) {
                                int selected = MyWindow.comboBox.getSelectedIndex();
                                if (selected == 8) {
                                        Random rand = new Random();
                                        selected = rand.nextInt(8);
                                }
 
                                if (tabR[x][y] == 0) {
                                        switch (selected) {
                                        case 0:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcMooreP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcMoore(x, y, tabR, size);
                                                break;
 
                                        case 1:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcVNP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcVN(x, y, tabR, size);
                                                break;
 
                                        case 2:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcHexLP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcHexL(x, y, tabR, size);
                                                break;
 
                                        case 3:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcHexRP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcHexR(x, y, tabR, size);
                                                break;
 
                                        case 4:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcPentaLP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcPentaL(x, y, tabR, size);
                                                break;
 
                                        case 5:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcPentaRP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcPentaR(x, y, tabR, size);
                                                break;
 
                                        case 6:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcPentaUP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcPentaU(x, y, tabR, size);
                                                break;
 
                                        case 7:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcPentaBP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcPentaB(x, y, tabR, size);
                                                break;
 
                                        case 9:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcHexRandomP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcHexRandom(x, y, tabR, size);
                                                break;
                                        case 10:
                                                if (isPeriodic)
                                                        tmp[x][y] = Rules.calcPentaRandomP(x, y, tabR, size);
                                                else
                                                        tmp[x][y] = Rules.calcPentaRandom(x, y, tabR, size);
                                                break;

 
                                        default:
                                                break;
 
                                        }
                                        if (tmp[x][y] != 0 && !cells[x][y].recrystallized) {
                                                cells[x][y].recrystallized = true;
                                                cells[x][y].dislocation = 0;
                                        }
                                } else
                                        tmp[x][y] = tabR[x][y];
                        }
 
                tabR = tmp;
        }
	/** Funkcja czyszczaca obszar. */
	public void clear() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				tab[i][j] = 0;
	}

	/**
	 * Funkcja ustawiajaca ziarna w podanym obszarze.
	 * 
	 * @param color
	 *            rodzaj ziarna
	 * @param x
	 *            wspolrzedna pozioma
	 * @param y
	 *            wspolrzedna pionowa
	 */
	public void setState(int color, int x, int y) {
		tab[x][y] = color;
	}

	/**
	 * Funkcja generujaca podana ilosc roznych ziaren w losowych miejscach
	 * obszaru.
	 * 
	 * @param number
	 *            liczba ziaren do wygenerowania
	 */
	public void generateRandom(int number) {
		Random rand = new Random();
		int tmpx = 0, tmpy = 0;
		tmpx = rand.nextInt(size);
		tmpy = rand.nextInt(size);
		for (int i = 0; i < number; i++) {
			// ograniczenie liczby prob losowania zeby program sie nie zawiesil
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

	/**Funkcja generujaca podana ilosc roznych ziaren w rownych odstepach.
         * @param distance dystans miedzy kolejnymi ziarnami
         */
        public void generateWithEqualDistance(int distance) {
                Random rand = new Random();
                for (int x = 0; x < size; x += distance)
                        for (int y = 0; y < size; y += distance) {
                                GameWindow.colorCounter++;
                                GameWindow.colors.add(new Color(rand.nextInt(255), rand
                                                .nextInt(255), rand.nextInt(255)));
                                setState(GameWindow.colorCounter, x, y);
                        }
        }

	/**
	 * Funkcja rysujaca uklad powierzchni.
	 * 
	 * @param g
	 *            obiekt graficzny na ktorym rysujemy
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
