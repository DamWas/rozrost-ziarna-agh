import java.awt.Color;
import java.util.Random;
 
/**
 * Klasa odpowiedzialna za rekrystalizacje i zazadzajaca nia.
 * 
 * @author Mateusz Kaflowski, Marcin Goadosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michao Grabarczyk
 * @version 1.0
 * @since 2013-05-30
 */
public class Recrystallization {
 
        /**Aktualny czas*/
        static double time = 0;
        static final double A = 86710969050178.5f;
        static final double B = 9.41f;
        /**Krok czasowy*/
        static final double delaTime = 0.001f;
        /**Dyslokacja w danym czasie*/
        static double dislocation = 0;
        /**Dyslokacja do dodania dla jednej komorki*/
        static double deltaDis = 0;
        /**Wartoœæ krytyczna dyslokacji*/
        static double disMax = 421584014.2f;
 
 
        /**Funkcja liczaca dyslokacjê w danym momencie*/
        public static void calcDislocation() {
                double tmp1 = (A / B)
                                + (1 - (A / B) * Math.pow(Math.E, (-B * (time - 0.001f))));
                if (time - 0.001f < 0)
                        tmp1 = 0;
                double tmp2 = (A / B) + (1 - (A / B) * Math.pow(Math.E, (-B * time)));
                dislocation = tmp2 - tmp1;
                deltaDis = dislocation
                                / (GameWindow.automat.size * GameWindow.automat.size);
                System.out.print(deltaDis + "\t" + dislocation + "\t" + tmp2);
                time += delaTime;
        }
 
        /**Funkcja dodajaca dyslokacje do wszystkich komorek*/
        public static void putDislocation() {
                for (int i = 0; i < GameWindow.automat.size; i++)
                        for (int j = 0; j < GameWindow.automat.size; j++) {
                                GameWindow.automat.cells[i][j].addDislocation(deltaDis);
                        }
                // rozrzucenie pozosta³ej dyslokacji
                if (dislocation > 0) {
                        deltaDis = dislocation
                                        / (GameWindow.automat.size * GameWindow.automat.size);
                        for (int i = 0; i < GameWindow.automat.size; i++)
                                for (int j = 0; j < GameWindow.automat.size; j++) {
                                        GameWindow.automat.cells[i][j].dislocation += deltaDis;
                                }
                }
        }
 
        /**Sprawdzanie czy komorka zrekrystalizowa³a i rozrost zrekrystalizowanych ziaren*/
        public static void updateState() {
                GameWindow.automat.genNextR();
                for (int i = 0; i < GameWindow.automat.size; i++)
                        for (int j = 0; j < GameWindow.automat.size; j++) {
                                if (GameWindow.automat.cells[i][j].dislocation >= disMax
                                                && GameWindow.automat.cells[i][j].isOnBorder()
                                                && !GameWindow.automat.cells[i][j].recrystallized) {
 
                                        // ograniczenie promieniem
                                        int radius = Integer.parseInt(MyWindow.tfReclRadius.getText());
                                        boolean skip = false;
                                        for (int i2 = i - radius; i2 <= i + radius; i2++)
                                                for (int j2 = j - radius; j2 <= j + radius; j2++) {
                                                        if (i2 < 0 || j2 < 0
                                                                        || i2 >= MyWindow.gw.automat.size
                                                                        || j2 >= MyWindow.gw.automat.size)
                                                                continue;
                                                        if(i2==i && j2==j)
                                                                continue;
                                                        if (MyWindow.gw.automat.tabR[i2][j2] != 0) {
                                                                skip = true;
                                                                break;
                                                        }
                                                }
 
                                        if (skip)
                                                continue;
                                        // ------------------------------
                                        Random rand = new Random();
                                        GameWindow.automat.cells[i][j].recrystallized = true;
                                        GameWindow.automat.cells[i][j].dislocation = 0;
                                        GameWindow.colorCounter++;
                                        GameWindow.colors.add(new Color(rand.nextInt(255), rand
                                                        .nextInt(255), rand.nextInt(255)));
                                        GameWindow.automat.setState(GameWindow.colorCounter, i, j);
                                        GameWindow.automat.tabR[i][j] = GameWindow.colorCounter;
                                        GameWindow.automat.cells[i][j].recrystallized = true;
                                        GameWindow.automat.cells[i][j].dislocation = 0;
                                }
                                if (GameWindow.automat.tabR[i][j] != 0) {
                                        GameWindow.automat.tab[i][j] = GameWindow.automat.tabR[i][j];
                                }
                        }
 
        }
 
}