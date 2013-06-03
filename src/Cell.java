import java.util.Random;

/**Klasa reprezentuj�ca ziarno/kom�rk�
	 * @author Mateusz Kaflowski, Marcin G�adosz, Krystian Bersztolc, Witold
	 *         Gramatyka, Micha� Grabarczyk
	 * @version 1.0
	 * @since 2013-05-24
	 */
public class Cell {
 	/**Liczba dyslokacji w kom�rce.*/
        double disloacation;
        /**Po�o�enie kom�rki.*/
        int x, y;
        /**Zmienna m�wi�ca czy kom�rka zrekrystalizowa�a*/
        boolean recrystallized;
	
		
 	/**
	* konstruktor 
	* @pX polozenie X
	* @pY polozenie Y
	*/
        public Cell(int pX, int pY) {
                disloacation = 0;
                x = pX;
                y = pY;
                recrystallized = false;
        }

  	 /**Funkcja dodaj�ca dyslokacj� do kom�rki
         * @param pDislocation dyslokacja do dodania
         */
        public void addDislocation(double pDislocation) {
                Random rand = new Random();
                double toAdd = 0;
                if (isOnBorder())
                        toAdd = pDislocation * (rand.nextInt(110) + 70)/100;
                else
                        toAdd = pDislocation * rand.nextInt(30)/100;
                disloacation += toAdd;
                Recrystallization.dislocation -= toAdd;
        }
 
        /**Funkcja sparwdzaj�ca czy ziarno le�y na granicy*/
        public boolean isOnBorder() {
                for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++) {
                                if (x + i < 0 || x + i >= GameWindow.automat.size || y + j < 0
                                                || y + j >= GameWindow.automat.size)
                                        continue;
                                if (GameWindow.automat.tab[x][y] != GameWindow.automat.tab[x + i][y + j])
                                        return true;
 
                        }
                return false;
        }





}
