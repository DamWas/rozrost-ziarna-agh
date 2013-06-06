/**
 * Klasa w�tku odpowiedzialnego za obliczenie rozrostu dla pojedynczego rz�du.
 * 
 * @author Mateusz Kaflowski, Marcin Gladosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-06-04
 */
public class MyThread extends Thread implements Runnable {
	
	/**szeroko��*/
	int size;
	/**numer rz�du*/
	int x;
	/**tablica do kt�rej zapisujemy wyniki*/
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
