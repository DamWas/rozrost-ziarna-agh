import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
 
/**Klasa definujaca rodzaje sasiedztwa.
 * @author Mateusz Kaflowski, Marcin Gladosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-05-24
 */
 
public class Rules {
/**
 * Funkcja zwraca najczescie wystepujacy element z kolekcji.
 * @param colors Kolekcja do przeszukania
 * @return Najczesciej wystepujacy element
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
 
        /** Funkcja zwracajaca sasieada najczesciej wystepujacego w sasiedztwie Moor'a.
         * @param x pozioma wspólrzedna badanego ziarna
         * @param y pionowa wspólrzedna badanego ziarna
         * @param tab obszar w jakim znajduje sie ziarno
         * @param size rozmiar obszaru
         * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku sasiadów
         */
        public static int calcMoore(int x, int y, int tab[][], int size) {
 
                List<Integer> colors = new LinkedList<Integer>();
                int predominant = 0;
 
                // góra:-------------------
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
 
                // dól:------------------
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
}