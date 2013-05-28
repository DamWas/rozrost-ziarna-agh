import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
 
import javax.swing.BorderFactory;
import javax.swing.JPanel;
 
/**Klasa wizualizuj�ca kolejne cykle �ycia.
 * @author Mateusz Kaflowski, Marcin G�adosz, Krystian Bersztolc, Witold
 *         Gramatyka, Micha� Grabarczyk
 * @version 1.0
 * @since 2013-05-27
 */
public class GameWindow extends JPanel {
 
        /**Automat na kt�rym dzia�amy*/
        static Automat automat;
       
        /**Szeroko�� pojedynczej kom�rki.*/
        public static final int WIDTH = 5;
        /**Op�nienie mi�dzy cyklami �ycia.*/
        public static int delay = 100;
 
        public static int thickness = 1;
        /**Kolekcja wszystkich kolor�w reprezentuj�cych rodzaje ziaren.*/
        public static ArrayList<Color> colors;
        /**Liczba wszytkich kolor�w reprezentuj�cych rodzaje ziaren.*/
        public static int colorCounter = 0;
 
        public GameWindow() {
 
                setBorder(BorderFactory.createLineBorder(Color.PINK));
 
                automat = new Automat(100);
                colors = new ArrayList<Color>();
        }
 
        public Dimension getPreferredSize() {
                return new Dimension(500, 500);
        }
 
 
}