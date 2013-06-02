import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
 
import javax.swing.BorderFactory;
import javax.swing.JPanel;
 
/**Klasa wizualizuj¹ca kolejne cykle ¿ycia.
 * @author Mateusz Kaflowski, Marcin G³adosz, Krystian Bersztolc, Witold
 *         Gramatyka, Micha³ Grabarczyk
 * @version 1.0
 * @since 2013-05-27
 */
public class GameWindow extends JPanel {
 
        /**Automat na którym dzia³amy*/
        static Automat automat;
       
        /**Szerokoœæ pojedynczej komórki.*/
        public static final int WIDTH = 5;
        /**OpóŸnienie miêdzy cyklami ¿ycia.*/
        public static int delay = 100;
 
        public static int thickness = 1;
        /**Kolekcja wszystkich kolorów reprezentuj¹cych rodzaje ziaren.*/
        public static ArrayList<Color> colors;
        /**Liczba wszytkich kolorów reprezentuj¹cych rodzaje ziaren.*/
        public static int colorCounter = 0;
 
        public GameWindow() {
 
                setBorder(BorderFactory.createLineBorder(Color.PINK));
 
                automat = new Automat(100);
                colors = new ArrayList<Color>();
                
                final Random rand = new Random();
                addMouseListener(new MouseAdapter() {
        			public void mousePressed(MouseEvent e) {
        				colorCounter++;
        				colors.add(new Color(rand.nextInt(255), rand.nextInt(255), rand
        						.nextInt(255)));
        				automat.setState(colorCounter, e.getX() / WIDTH, e.getY() / WIDTH);
        				repaint();
        			}
        		});

		addMouseMotionListener(new MouseAdapter() {
                        public void mouseDragged(MouseEvent e) {
                                automat.setState(colorCounter, e.getX() / WIDTH, e.getY() / WIDTH);
                                repaint();
                        }
                });
        }
 
        public Dimension getPreferredSize() {
                return new Dimension(500, 500);
        }

        /**
    	 * Funkcja generuj¹ca kolejny cykl ¿ycia ziaren.
    	 */
    	public void iterate() {
    		while (true) {
    			automat.genNext();
    			repaint();
    			try {
    				Thread.sleep(delay);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    	}

		public void iterateMonteCarlo() {
			// TODO Auto-generated method stub
			
		}

		public void iterateReclystallization() {
			// TODO Auto-generated method stub
			
		}

/**
         * Funkcja czyszcz¹ca obszar.
         */
        public void clear() {
                automat.clear();
                repaint();
        }
 
        @Override
        synchronized protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                automat.printTab(g);
        }
 
 
}