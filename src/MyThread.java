/**
 * Klasa w¹tku odpowiedzialnego za obliczenie rozrostu dla pojedynczego rzêdu.
 * 
 * @author Mateusz Kaflowski, Marcin Gladosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-06-04
 */
public class MyThread extends Thread implements Runnable {
	
	/**szerokoœæ*/
	int size;
	/**numer rzêdu*/
	int x;
	/**tablica do której zapisujemy wyniki*/
	int tmp[][];
	public MyThread(int size, int x, int tmp[][]) {
		this.x = x;
		this.size = size;
		this.tmp = tmp;
	}
	
	@Override
	public void run() {
		for (int y = 0; y < size; y++) {
			Automat.switchNext(tmp, x, y);
		}
		super.run();
	}
	
}
