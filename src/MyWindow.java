import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mpi.MPI;
import mpi.Request;

/**
 * Klasa tworz�ca g��wne GUI.
 * 
 * @author Mateusz Kaflowski, Marcin Gladosz, Krystian Bersztolc, Witold
 *         Gramatyka, Michal Grabarczyk
 * @version 1.0
 * @since 2013-05-30
 */
public class MyWindow extends JFrame {

	private JPanel contentPane;
	public static JComboBox comboBox;
	public boolean isRun = false;
	public static Thread thread;
	public static Thread threadReclystallization;
	public static Thread threadMonteCarlo;
	private JTextField textField;
	public static JTextField tfSpace;
	public static JTextField tfRandomNumber;
	public static GameWindow gw;
	static JButton btnReclystallization;
	public static JTextField tfReclRadius;
	static JButton btnMonteCarlo;
	static JTextField tfMC;
	private JLabel lblSpace;
	private JLabel lblDelay;
	private JTextField tfDelay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MyWindow frame = new MyWindow();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			});
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public MyWindow() {
		setTitle("Grain Growth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		final GameWindow gw = new GameWindow();
		gw.setBounds(289, 42, 500, 500);
		contentPane.add(gw);

		final JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (isRun == false) {
					btnReclystallization.setEnabled(false);
					btnStart.setText("STOP");
					isRun = true;
					
					thread = new Thread(new Runnable() {

						@Override
						public void run() {
							gw.iterate();
						}
					});
					
					thread.start();
				}
			else{
				btnStart.setText("START");
				isRun = false;
				thread.stop();
				btnReclystallization.setEnabled(true);
			}
		}});
		btnStart.setBounds(92, 42, 89, 23);
		contentPane.add(btnStart);
		
		//wyb�r opcji dodawania
		String options[] = {"Moore", "von Neuman", "Hex L", "Hex R", "Penta R", "Penta L", "Penta B", "Penta U", "RANDOM", "Hex RANDOM", "Penta RANDOM"};
		comboBox = new JComboBox(options);
		comboBox.setBounds(116, 197, 124, 20);
		comboBox.setSelectedIndex(0);
		contentPane.add(comboBox);
		
	
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gw.automat.clear();
				Recrystallization.time = 0;
				gw.automat.tabR = new int[gw.automat.size][gw.automat.size];
				for (int i = 0; i < gw.automat.size; i++)
					for (int j = 0; j < gw.automat.size; j++)
						gw.automat.tabR[i][j] = 0;

				gw.automat.cells = new Cell[gw.automat.size][gw.automat.size];
				for (int i = 0; i < gw.automat.size; i++)
					for (int j = 0; j < gw.automat.size; j++)
						gw.automat.cells[i][j] = new Cell(i, j);
				gw.repaint();
			}
		});
		btnClear.setBounds(92, 91, 89, 23);
		contentPane.add(btnClear);
		
		JLabel lblAdd = new JLabel("Rule:");
		lblAdd.setBounds(77, 200, 29, 14);
		contentPane.add(lblAdd);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gw.thickness = Integer.parseInt(textField.getText());
				gw.repaint();
			}
		});
		textField.setText("1");
		textField.setBounds(121, 244, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblThickness = new JLabel("Thickness:");
		lblThickness.setBounds(39, 247, 65, 14);
		contentPane.add(lblThickness);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("is periodic");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gw.automat.isPeriodic = chckbxNewCheckBox.isSelected();
			}
		});
		chckbxNewCheckBox.setBounds(116, 143, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnGenerateWithEqual = new JButton("Generate with equal distance");
		btnGenerateWithEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gw.automat.generateWithEqualDistance(Integer.parseInt(MyWindow.tfSpace
						.getText()));
				gw.repaint();
			}
		});
		btnGenerateWithEqual.setBounds(17, 318, 223, 23);
		contentPane.add(btnGenerateWithEqual);
		
		tfSpace = new JTextField();
		tfSpace.setText("5");
		tfSpace.setColumns(10);
		tfSpace.setBounds(250, 319, 29, 20);
		contentPane.add(tfSpace);
		
		JButton btnGenerateRandom = new JButton("Generate random");
		btnGenerateRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gw.automat.generateRandom(Integer.parseInt(MyWindow.tfRandomNumber.getText()));
				gw.repaint();
			}
		});
		btnGenerateRandom.setBounds(17, 407, 223, 23);
		contentPane.add(btnGenerateRandom);
		
		tfRandomNumber = new JTextField();
		tfRandomNumber.setText("5");
		tfRandomNumber.setColumns(10);
		tfRandomNumber.setBounds(250, 408, 29, 20);
		contentPane.add(tfRandomNumber);
		
		btnReclystallization = new JButton("Reclystallization");
		btnReclystallization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isRun == false) {
					btnStart.setEnabled(false);
					isRun = true;

					threadReclystallization = new Thread(new Runnable() {

						@Override
						public void run() {
							gw.iterateReclystallization();
						}
					});

					threadReclystallization.start();
				} else {
					btnStart.setEnabled(true);
					isRun = false;
					threadReclystallization.stop();
				}
			}
		});
		btnReclystallization.setBounds(17, 453, 150, 23);
		contentPane.add(btnReclystallization);
		
		tfReclRadius = new JTextField();
		tfReclRadius.setText("0");
		tfReclRadius.setColumns(10);
		tfReclRadius.setBounds(250, 454, 29, 20);
		contentPane.add(tfReclRadius);
		
		btnMonteCarlo = new JButton("Monte Carlo");
		btnMonteCarlo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isRun == false) {
					btnStart.setEnabled(false);
					btnReclystallization.setEnabled(false);
					btnMonteCarlo.setEnabled(false);
					isRun = true;
					
					threadMonteCarlo = new Thread(new Runnable() {

						@Override
						public void run() {
							gw.iterateMonteCarlo();
						}
					});
					
					threadMonteCarlo.start();
				}
			else{
				btnStart.setEnabled(true);
				btnReclystallization.setEnabled(true);
				btnMonteCarlo.setEnabled(true);
				isRun = false;
				threadMonteCarlo.stop();
			}
			}
		});
		btnMonteCarlo.setBounds(55, 507, 150, 23);
		contentPane.add(btnMonteCarlo);
		
		tfMC = new JTextField();
		tfMC.setText("1");
		tfMC.setColumns(10);
		tfMC.setBounds(250, 508, 29, 20);
		contentPane.add(tfMC);
		
		lblSpace = new JLabel("Space:");
		lblSpace.setBounds(177, 457, 65, 14);
		contentPane.add(lblSpace);
		
		lblDelay = new JLabel("Delay:");
		lblDelay.setBounds(39, 367, 65, 14);
		contentPane.add(lblDelay);
		
		tfDelay = new JTextField();
		tfDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gw.delay = Integer.parseInt(tfDelay.getText());
			}
		});
		tfDelay.setText("100");
		tfDelay.setColumns(10);
		tfDelay.setBounds(176, 364, 29, 20);
		contentPane.add(tfDelay);
	}
}


