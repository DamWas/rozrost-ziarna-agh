import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
/**Klasa do oblicze� Monte Carlo.
Usprawnienia:
- losowanie jedynie spo�r�d s�siad�w,
- wi�ksze prawdopodobie�stwo wylosowania elementu, kt�ry cz�ciej wyst�puje.
 * @author Marcin G�adosz, Mateusz Kaflowski, Krystian Bersztolc, Witold
 *         Gramatyka, Micha� Grabarczyk
 * @version 1.0
 * @since 2013-06-02
 */
/*
*/
 
public class MonteCarlo {
       
        /**Automat na kt�rym operujemy*/
        Automat automat;
        /**Zmienne dla ka�dej kom�rki m�wi�ce czy zosta�a ona wylosowana*/
        boolean wasDrawn[][];
       
        public MonteCarlo(Automat automat) {
                this.automat = automat;
        }
       
        /**Funkcja wykonuj�ca pojedynczy przbieg Monte Carlo - wylosowanie wszystkich kom�rek*/
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
 
        /**Funkcja licz�ca energie w podanej kom�rce.
         * @param id ID ziarna dla kt�rego liczymy energi�
         * @param neibs kolekcja s�siad�w ziarna
         * @return energia ziarna - liczba innych s�siad�w
         */
        private int calcEnergy(int id, List<Integer> neibs) {
                int energy = 0;
                for (Integer integer : neibs) {
                        if(integer.compareTo(id)!=0)
                                energy++;
                }
                return energy;
        }
 
        /**Funkcja �aduj�ca s�siad�w podanego ziarna do kolekcji.
         * @param x po�o�enie poziome ziarna
         * @param y po�o�enie pionowe ziarna
         * @param neibs kolekcja do kt�rej zapisane s� ID s�siad�w
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
 
        /**Funkcja sprawdzaj�ca czy wszystkie ziarna z przestrzeni zosta�y wylosowane
         * @return true - wszystkie kom�rki wylosowanie, w przeciwnym wypadku false
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