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
         * @param x pozioma wsp�lrzedna badanego ziarna
         * @param y pionowa wsp�lrzedna badanego ziarna
         * @param tab obszar w jakim znajduje sie ziarno
         * @param size rozmiar obszaru
         * @return ID ziarna najczesciej wystepujacego w sasiedztwie. 0 dla braku sasiad�w
         */
        public static int calcMoore(int x, int y, int tab[][], int size) {
 
                List<Integer> colors = new LinkedList<Integer>();
                int predominant = 0;
 
                // g�ra:-------------------
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
 
                // d�l:------------------
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


        // updated by Krystian B. 28.05.2013
    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie heksagonalnym lewym z periodycznością.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcHexLP(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // góra:-------------------
        if (y == 0) {
            if (x == 0)
                colors.add(tab[size - 1][size - 1]);
            else
                colors.add(tab[x - 1][size - 1]);
            colors.add(tab[x][size - 1]);

        } else {
            if (x == 0)
                colors.add(tab[size - 1][y - 1]);
            else
                colors.add(tab[x - 1][y - 1]);
            colors.add(tab[x][y - 1]);

        }

        // dół:------------------
        if (y == size - 1) {

            colors.add(tab[x][0]);
            if (x == size - 1)
                colors.add(tab[0][0]);
            else
                colors.add(tab[x + 1][0]);
        } else {

            colors.add(tab[x][y + 1]);
            if (x == size - 1)
                colors.add(tab[0][y + 1]);
            else
                colors.add(tab[x + 1][y + 1]);
        }

        // lewo:-------------------
        if (x == 0) {
            colors.add(tab[size - 1][y]);
        } else {
            colors.add(tab[x - 1][y]);
        }

        // prawo:--------------------
        if (x == size - 1) {
            colors.add(tab[0][y]);
        } else {
            colors.add(tab[x + 1][y]);
        }

        predominant = getTheMostPopular(colors);

        return predominant;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie heksagonalnym lewym.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcHexL(int x, int y, int tab[][], int size) {

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

        }

        // dół:------------------
        if (y == size - 1) {

        } else {

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

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie heksagonalnym prawym z periodycznością.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcHexRP(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // góra:-------------------
        if (y == 0) {

            colors.add(tab[x][size - 1]);
            if (x == size - 1)
                colors.add(tab[0][size - 1]);
            else
                colors.add(tab[x + 1][size - 1]);
        } else {

            colors.add(tab[x][y - 1]);
            if (x == size - 1)
                colors.add(tab[0][y - 1]);
            else
                colors.add(tab[x + 1][y - 1]);
        }

        // dół:------------------
        if (y == size - 1) {
            if (x == 0)
                colors.add(tab[size - 1][0]);
            else
                colors.add(tab[x - 1][0]);
            colors.add(tab[x][0]);

        } else {
            if (x == 0)
                colors.add(tab[size - 1][y + 1]);
            else
                colors.add(tab[x - 1][y + 1]);
            colors.add(tab[x][y + 1]);

        }

        // lewo:-------------------
        if (x == 0) {
            colors.add(tab[size - 1][y]);
        } else {
            colors.add(tab[x - 1][y]);
        }

        // prawo:--------------------
        if (x == size - 1) {
            colors.add(tab[0][y]);
        } else {
            colors.add(tab[x + 1][y]);
        }

        predominant = getTheMostPopular(colors);

        return predominant;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie heksagonalnym prawym.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcHexR(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // góra:-------------------
        if (y == 0) {

        } else {

            colors.add(tab[x][y - 1]);
            if (x == size - 1)
                ;
            else
                colors.add(tab[x + 1][y - 1]);
        }

        // dół:------------------
        if (y == size - 1) {

        } else {
            if (x == 0)
                ;
            else
                colors.add(tab[x - 1][y + 1]);
            colors.add(tab[x][y + 1]);

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

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym lewym z periodycznością.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaLP(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // góra:-------------------
        if (y == 0) {
            if (x == 0)
                colors.add(tab[size - 1][size - 1]);
            else
                colors.add(tab[x - 1][size - 1]);
            colors.add(tab[x][size - 1]);

        } else {
            if (x == 0)
                colors.add(tab[size - 1][y - 1]);
            else
                colors.add(tab[x - 1][y - 1]);
            colors.add(tab[x][y - 1]);

        }

        // dół:------------------
        if (y == size - 1) {
            if (x == 0)
                colors.add(tab[size - 1][0]);
            else
                colors.add(tab[x - 1][0]);
            colors.add(tab[x][0]);

        } else {
            if (x == 0)
                colors.add(tab[size - 1][y + 1]);
            else
                colors.add(tab[x - 1][y + 1]);
            colors.add(tab[x][y + 1]);

        }

        // lewo:-------------------
        if (x == 0) {
            colors.add(tab[size - 1][y]);
        } else {
            colors.add(tab[x - 1][y]);
        }

        predominant = getTheMostPopular(colors);

        return predominant;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym lewym.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaL(int x, int y, int tab[][], int size) {

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

        }

        // dół:------------------
        if (y == size - 1) {

        } else {
            if (x == 0)
                ;
            else
                colors.add(tab[x - 1][y + 1]);
            colors.add(tab[x][y + 1]);

        }

        // lewo:-------------------
        if (x == 0) {
            ;
        } else {
            colors.add(tab[x - 1][y]);
        }

        predominant = getTheMostPopular(colors);

        return predominant;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym prawym z periodycznością.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaRP(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // góra:-------------------
        if (y == 0) {

            colors.add(tab[x][size - 1]);
            if (x == size - 1)
                colors.add(tab[0][size - 1]);
            else
                colors.add(tab[x + 1][size - 1]);
        } else {

            colors.add(tab[x][y - 1]);
            if (x == size - 1)
                colors.add(tab[0][y - 1]);
            else
                colors.add(tab[x + 1][y - 1]);
        }

        // dół:------------------
        if (y == size - 1) {

            colors.add(tab[x][0]);
            if (x == size - 1)
                colors.add(tab[0][0]);
            else
                colors.add(tab[x + 1][0]);
        } else {

            colors.add(tab[x][y + 1]);
            if (x == size - 1)
                colors.add(tab[0][y + 1]);
            else
                colors.add(tab[x + 1][y + 1]);
        }

        // prawo:--------------------
        if (x == size - 1) {
            colors.add(tab[0][y]);
        } else {
            colors.add(tab[x + 1][y]);
        }

        predominant = getTheMostPopular(colors);

        return predominant;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym prawym.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaR(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // góra:-------------------
        if (y == 0) {

        } else {

            colors.add(tab[x][y - 1]);
            if (x == size - 1)
                ;
            else
                colors.add(tab[x + 1][y - 1]);
        }

        // dół:------------------
        if (y == size - 1) {

        } else {

            colors.add(tab[x][y + 1]);
            if (x == size - 1)
                ;
            else
                colors.add(tab[x + 1][y + 1]);
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

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym górnym z periodycznością.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaUP(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // góra:-------------------
        if (y == 0) {
            if (x == 0)
                colors.add(tab[size - 1][size - 1]);
            else
                colors.add(tab[x - 1][size - 1]);
            colors.add(tab[x][size - 1]);
            if (x == size - 1)
                colors.add(tab[0][size - 1]);
            else
                colors.add(tab[x + 1][size - 1]);
        } else {
            if (x == 0)
                colors.add(tab[size - 1][y - 1]);
            else
                colors.add(tab[x - 1][y - 1]);
            colors.add(tab[x][y - 1]);
            if (x == size - 1)
                colors.add(tab[0][y - 1]);
            else
                colors.add(tab[x + 1][y - 1]);
        }

        // lewo:-------------------
        if (x == 0) {
            colors.add(tab[size - 1][y]);
        } else {
            colors.add(tab[x - 1][y]);
        }

        // prawo:--------------------
        if (x == size - 1) {
            colors.add(tab[0][y]);
        } else {
            colors.add(tab[x + 1][y]);
        }

        predominant = getTheMostPopular(colors);

        return predominant;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym górnym.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaU(int x, int y, int tab[][], int size) {

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

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym dolnym z periodycznością.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaBP(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // dół:------------------
        if (y == size - 1) {
            if (x == 0)
                colors.add(tab[size - 1][0]);
            else
                colors.add(tab[x - 1][0]);
            colors.add(tab[x][0]);
            if (x == size - 1)
                colors.add(tab[0][0]);
            else
                colors.add(tab[x + 1][0]);
        } else {
            if (x == 0)
                colors.add(tab[size - 1][y + 1]);
            else
                colors.add(tab[x - 1][y + 1]);
            colors.add(tab[x][y + 1]);
            if (x == size - 1)
                colors.add(tab[0][y + 1]);
            else
                colors.add(tab[x + 1][y + 1]);
        }

        // lewo:-------------------
        if (x == 0) {
            colors.add(tab[size - 1][y]);
        } else {
            colors.add(tab[x - 1][y]);
        }

        // prawo:--------------------
        if (x == size - 1) {
            colors.add(tab[0][y]);
        } else {
            colors.add(tab[x + 1][y]);
        }

        predominant = getTheMostPopular(colors);

        return predominant;
    }


    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym dolnym.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaB(int x, int y, int tab[][], int size) {

        List<Integer> colors = new LinkedList<Integer>();
        int predominant = 0;

        // dół:------------------
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

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie heksagonalnym losowym.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcHexRandom(int x, int y, int tab[][], int size) {
        Random rand = new Random();
        switch (rand.nextInt(2)) {
            case 0:
                return calcHexL(x, y, tab, size);
            case 1:
                return calcHexR(x, y, tab, size);

            default:
                break;
        }
        return 0;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie heksagonalnym losowym z periodycznością.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcHexRandomP(int x, int y, int tab[][], int size) {
        Random rand = new Random();
        switch (rand.nextInt(2)) {
            case 0:
                return calcHexLP(x, y, tab, size);
            case 1:
                return calcHexRP(x, y, tab, size);

            default:
                break;
        }
        return 0;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym losowym.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaRandom(int x, int y, int tab[][], int size) {
        Random rand = new Random();
        switch (rand.nextInt(4)) {
            case 0:
                return calcPentaB(x, y, tab, size);
            case 1:
                return calcPentaU(x, y, tab, size);
            case 2:
                return calcPentaR(x, y, tab, size);
            case 3:
                return calcPentaL(x, y, tab, size);

            default:
                break;
        }
        return 0;
    }

    /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie pentagonalnym losowym z periodycznością.
     * @param x pozioma współrzędna badanego ziarna
     * @param y pionowa współrzędna badanego ziarna
     * @param tab obszar w jakim znajduje się ziarno
     * @param size rozmiar obszaru
     * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
     */
    public static int calcPentaRandomP(int x, int y, int tab[][], int size) {
        Random rand = new Random();
        switch (rand.nextInt(4)) {
            case 0:
                return calcPentaBP(x, y, tab, size);
            case 1:
                return calcPentaUP(x, y, tab, size);
            case 2:
                return calcPentaRP(x, y, tab, size);
            case 3:
                return calcPentaLP(x, y, tab, size);

            default:
                break;
        }
        return 0;
    }
            // ---------- koniec update by Krystian B. 28.05.2013





/** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie Moor'a z periodycznością.
         * @param x pozioma współrzędna badanego ziarna
         * @param y pionowa współrzędna badanego ziarna
         * @param tab obszar w jakim znajduje się ziarno
         * @param size rozmiar obszaru
         * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
         */
        public static int calcMooreP(int x, int y, int tab[][], int size) {
 
                List<Integer> colors = new LinkedList<Integer>();
                int predominant = 0;
 
                // góra:-------------------
                if (y == 0) {
                        if (x == 0)
                                colors.add(tab[size - 1][size - 1]);
                        else
                                colors.add(tab[x - 1][size - 1]);
                        colors.add(tab[x][size - 1]);
                        if (x == size - 1)
                                colors.add(tab[0][size - 1]);
                        else
                                colors.add(tab[x + 1][size - 1]);
                } else {
                        if (x == 0)
                                colors.add(tab[size - 1][y - 1]);
                        else
                                colors.add(tab[x - 1][y - 1]);
                        colors.add(tab[x][y - 1]);
                        if (x == size - 1)
                                colors.add(tab[0][y - 1]);
                        else
                                colors.add(tab[x + 1][y - 1]);
                }
 
                // dół:------------------
                if (y == size - 1) {
                        if (x == 0)
                                colors.add(tab[size - 1][0]);
                        else
                                colors.add(tab[x - 1][0]);
                        colors.add(tab[x][0]);
                        if (x == size - 1)
                                colors.add(tab[0][0]);
                        else
                                colors.add(tab[x + 1][0]);
                } else {
                        if (x == 0)
                                colors.add(tab[size - 1][y + 1]);
                        else
                                colors.add(tab[x - 1][y + 1]);
                        colors.add(tab[x][y + 1]);
                        if (x == size - 1)
                                colors.add(tab[0][y + 1]);
                        else
                                colors.add(tab[x + 1][y + 1]);
                }
 
                // lewo:-------------------
                if (x == 0) {
                        colors.add(tab[size - 1][y]);
                } else {
                        colors.add(tab[x - 1][y]);
                }
 
                // prawo:--------------------
                if (x == size - 1) {
                        colors.add(tab[0][y]);
                } else {
                        colors.add(tab[x + 1][y]);
                }
 
                predominant = getTheMostPopular(colors);
 
                return predominant;
        }
 
        /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie von Neumana z periodycznością.
         * @param x pozioma współrzędna badanego ziarna
         * @param y pionowa współrzędna badanego ziarna
         * @param tab obszar w jakim znajduje się ziarno
         * @param size rozmiar obszaru
         * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
         */
        public static int calcVNP(int x, int y, int tab[][], int size) {
 
                List<Integer> colors = new LinkedList<Integer>();
                int predominant = 0;
 
                // góra:-------------------
                if (y == 0) {
                        colors.add(tab[x][size - 1]);
                } else {
                        colors.add(tab[x][y - 1]);
                }
 
                // dół:------------------
                if (y == size - 1) {
 
                        colors.add(tab[x][0]);
 
                } else {
 
                        colors.add(tab[x][y + 1]);
 
                }
 
                // lewo:-------------------
                if (x == 0) {
                        colors.add(tab[size - 1][y]);
                } else {
                        colors.add(tab[x - 1][y]);
                }
 
                // prawo:--------------------
                if (x == size - 1) {
                        colors.add(tab[0][y]);
                } else {
                        colors.add(tab[x + 1][y]);
                }
 
                predominant = getTheMostPopular(colors);
 
                return predominant;
        }
 
        /** Funkcja zwracająca sasieada najczęściej występującego w sąsiedztwie von Neumana.
         * @param x pozioma współrzędna badanego ziarna
         * @param y pionowa współrzędna badanego ziarna
         * @param tab obszar w jakim znajduje się ziarno
         * @param size rozmiar obszaru
         * @return ID ziarna najczęściej występującego w sąsiedztwie. 0 dla braku sąsiadów
         */
        public static int calcVN(int x, int y, int tab[][], int size) {
 
                List<Integer> colors = new LinkedList<Integer>();
                int predominant = 0;
 
                // góra:-------------------
                if (y == 0) {
                        ;
                } else {
                        colors.add(tab[x][y - 1]);
                }
 
                // dół:------------------
                if (y == size - 1) {
 
                        ;
 
                } else {
 
                        colors.add(tab[x][y + 1]);
 
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

//---------------------------------UPDATE Marcin



}






