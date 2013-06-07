/**
 * Klasa w¹tku odpowiedzialnego za obliczenie rozrostu dla pojedynczego rzêdu.
 * 
 * @author Mateusz Kaflowski, Marcin Gladosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-06-04
 */
public class MyThread extends Thread implements Runnable {

	/** szerokoœæ */
	int size;
	/** numer rzêdu */
	int x;
	/** tablica do której zapisujemy wyniki */
	int tmp[][];
	int rowStart;
	int rowEnd;

	public MyThread(int size, int x, int tmp[][], int start, int end) {
		this.x = x;
		this.size = size;
		this.tmp = tmp;
		this.rowStart = start;
		this.rowEnd = end;
	}

	@Override
	public void run() {
		for (int i = rowStart; i < rowEnd; i++)
			for (int y = 0; y < size; y++) {
				Automat.switchNext(tmp, i, y);
			}
		super.run();
	}

}
