import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
/**Klasa do obliczeñ Monte Carlo.
Usprawnienia:
- losowanie jedynie spoœrod sasiadow,
- wiêksze prawdopodobieñstwo wylosowania elementu, ktory czêœciej wystêpuje.
 * @author Marcin Gladosz, Mateusz Kaflowski, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-06-02
 */
/*
*/
 
public class MonteCarlo {
       
        /**Automat na ktorym operujemy*/
        Automat automat;
        /**Zmienne dla kazdej komorki mowiace czy zosta³a ona wylosowana*/
        boolean wasDrawn[][];
       
        public MonteCarlo(Automat automat) {
                this.automat = automat;
        }
       
        /**Funkcja wykonujaca pojedynczy przbieg Monte Carlo - wylosowanie wszystkich komorek*/
        public void oneStep(){
                wasDrawn = new boolean[automat.size][automat.size];
                for (int i = 0; i < automat.size; i++)
                        for (int j = 0; j < automat.size; j++) {
                                wasDrawn[i][j] = false;
                        }
               
                Random rand = new Random();
               
                while(!checkIfAllDrawn()){
                        int x = rand.nextInt(automat.size);
                        int y = rand.nextInt(automat.size);
                        List<Integer> neibs = new ArrayList<Integer>();
                        getNeibs(x,y,neibs);
                       
                        int old = automat.tab[x][y];
                        int oldEnergy = calcEnergy(old, neibs);
                        int neww = neibs.get(rand.nextInt(neibs.size()));
                        int newEnergy = calcEnergy(neww, neibs);
                        if(newEnergy<=oldEnergy){
                                automat.tab[x][y] = neww;
                        }
                       
                        wasDrawn[x][y] = true;
                }
        }
 
        /**Funkcja liczaca energie w podanej komorce.
         * @param id ID ziarna dla ktorego liczymy energiê
         * @param neibs kolekcja sasiadow ziarna
         * @return energia ziarna - liczba innych sasiadow
         */
        private int calcEnergy(int id, List<Integer> neibs) {
                int energy = 0;
                for (Integer integer : neibs) {
                        if(integer.compareTo(id)!=0)
                                energy++;
                }
                return energy;
        }
 
        /**Funkcja ³adujaca sasiadow podanego ziarna do kolekcji.
         * @param x po³ozenie poziome ziarna
         * @param y po³ozenie pionowe ziarna
         * @param neibs kolekcja do ktorej zapisane sa ID sasiadow
         */
        private void getNeibs(int x, int y, List<Integer> neibs) {
                for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++){
                                if(i==0 && j==0)
                                        continue;
                                if(x+i<0 || y+j<0 || x+i>=automat.size || y+j>=automat.size)
                                        continue;
 
                                neibs.add(automat.tab[x+i][y+j]);
                        }
 
        }
 
        /**Funkcja sprawdzajaca czy wszystkie ziarna z przestrzeni zosta³y wylosowane
         * @return true - wszystkie komorki wylosowanie, w przeciwnym wypadku false
         */
        private boolean checkIfAllDrawn() {
                for (int i = 0; i < automat.size; i++)
                        for (int j = 0; j < automat.size; j++) {
                                if(wasDrawn[i][j]==false)
                                        return false;
                        }
                return true;
        }
 
}