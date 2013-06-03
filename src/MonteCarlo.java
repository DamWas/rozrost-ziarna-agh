import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
/**Klasa do obliczeñ Monte Carlo.
Usprawnienia:
- losowanie jedynie spoœród s¹siadów,
- wiêksze prawdopodobieñstwo wylosowania elementu, który czêœciej wystêpuje.
 * @author Marcin G³adosz, Mateusz Kaflowski, Krystian Bersztolc, Witold
 *         Gramatyka, Micha³ Grabarczyk
 * @version 1.0
 * @since 2013-06-02
 */
/*
*/
 
public class MonteCarlo {
       
        /**Automat na którym operujemy*/
        Automat automat;
        /**Zmienne dla ka¿dej komórki mówi¹ce czy zosta³a ona wylosowana*/
        boolean wasDrawn[][];
       
        public MonteCarlo(Automat automat) {
                this.automat = automat;
        }
       
        /**Funkcja wykonuj¹ca pojedynczy przbieg Monte Carlo - wylosowanie wszystkich komórek*/
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
 
        /**Funkcja licz¹ca energie w podanej komórce.
         * @param id ID ziarna dla którego liczymy energiê
         * @param neibs kolekcja s¹siadów ziarna
         * @return energia ziarna - liczba innych s¹siadów
         */
        private int calcEnergy(int id, List<Integer> neibs) {
                int energy = 0;
                for (Integer integer : neibs) {
                        if(integer.compareTo(id)!=0)
                                energy++;
                }
                return energy;
        }
 
        /**Funkcja ³aduj¹ca s¹siadów podanego ziarna do kolekcji.
         * @param x po³o¿enie poziome ziarna
         * @param y po³o¿enie pionowe ziarna
         * @param neibs kolekcja do której zapisane s¹ ID s¹siadów
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
 
        /**Funkcja sprawdzaj¹ca czy wszystkie ziarna z przestrzeni zosta³y wylosowane
         * @return true - wszystkie komórki wylosowanie, w przeciwnym wypadku false
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