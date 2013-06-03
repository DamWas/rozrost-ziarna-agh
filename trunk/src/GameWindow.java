import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
 
import javax.swing.BorderFactory;
import javax.swing.JPanel;
 
/**Klasa wizualizujaca kolejne cykle zycia.
 * @author Mateusz Kaflowski, Marcin G³adosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-05-27
 */
public class GameWindow extends JPanel {
 
        /**Automat na którym dzia³amy*/
        static Automat automat;
       
        /**Szerokoœæ pojedynczej komórki.*/
        public static final int WIDTH = 5;
        /**OpóŸnienie miêdzy cyklami zycia.*/
        public static int delay = 100;
 
        public static int thickness = 1;
        /**Kolekcja wszystkich kolorów reprezentujacych rodzaje ziaren.*/
        public static ArrayList<Color> colors;
        /**Liczba wszytkich kolorów reprezentujacych rodzaje ziaren.*/
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
    	 * Funkcja generujaca kolejny cykl zycia ziaren.
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

    	/**
    	 * Funkcja generujaca podana liczbe wykonan Monte Carlo.
    	 */
		public void iterateMonteCarlo() {
			MonteCarlo mc = new MonteCarlo(automat);
			int toDo = Integer.parseInt(MyWindow.tfMC.getText());
			while(toDo>0){
				mc.oneStep();
				repaint();
				toDo--;
			}
			MyWindow.btnMonteCarlo.setEnabled(true);
			MyWindow.btnReclystallization.setEnabled(true);
		}

		/**
    	 * Funkcja generujaca kolejny cykl rekrystalizacji.
    	 */
		public void iterateReclystallization() {
			while (true) {
				Recrystallization.calcDislocation();
				Recrystallization.putDislocation();
				Recrystallization.updateState();
				repaint();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

/**
         * Funkcja czyszczaca obszar.
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