import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;

/**
 * Artifact Roll Simulator from Genshin Impact
 * @author AKAMiNE
 */
public class ArtifactSimulator extends JFrame {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private Frame frameAncestor;
	private ArtifactStat artifactStat = new ArtifactStat();
	private ArtifactDisplayerPanel artifactDisplayerPanel;
	private JPanel contentPane;
	private JComboBox<String> cboArtifactPiece;
	private JPanel panelControls;
	private JButton btnGenerate;
	private JButton btnRoll;
	private JButton btnReroll;
	private JButton btnReset;
	private JButton btnLock;
	private JButton btnCustomStat;
	private JLabel lblStatus;
	private JButton btnSkip;
	private JCheckBox chkRandomStat;
	private JCheckBox chkFullUpgrade;
	private int maxUpgrade;
	private int rollCounter;
	private boolean isNewSubStat = true;
	private boolean oneTime = true;
	private boolean isLock = true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ArtifactSimulator frame = null;
				
				try {
					frame = new ArtifactSimulator();
					frame.setVisible(true);
					
					InstructionDialog.showMessageDialog(frame, frame.getTitle(), frame.displayArtifactSimulatorMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame == null ? null : frame, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ArtifactSimulator() {
		setLookAndFeel();
		
		setTitle("Artifact RNG");
		setSize(589, 389);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ArtifactSimulator.class.getResource("/assets/Amber Icon.jpg")));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		frameAncestor = (Frame) SwingUtilities.getWindowAncestor(contentPane);		
		
		artifactDisplayerPanel = new ArtifactDisplayerPanel(artifactStat);
		contentPane.add(artifactDisplayerPanel);
		
		panelControls = new JPanel();
		panelControls.setBackground(new Color(192, 192, 192));
		panelControls.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelControls.setBounds(10, 11, 312, 328);
		panelControls.setLayout(null);
		contentPane.add(panelControls);
		
		btnLock = new JButton("Lock");
		btnLock.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLock.setBounds(189, 45, 113, 30);
		btnLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isLock) {
					btnCustomStat.setEnabled(true);
					cboArtifactPiece.setEnabled(false);
					
					if (chkRandomStat.isSelected()) {
						chkRandomStat.setEnabled(false);
						chkFullUpgrade.setEnabled(false);
					} else {
						chkRandomStat.setEnabled(false);
					}
					
					isLock = false;
					btnLock.setText("Unlock");
				} else {
					btnCustomStat.setEnabled(false);
					cboArtifactPiece.setEnabled(true);			
					
					if (chkRandomStat.isSelected()) {
						chkRandomStat.setEnabled(true);
						chkFullUpgrade.setEnabled(true);
					} else {
						chkRandomStat.setEnabled(true);
					}
					
					isLock = true;
					btnLock.setText("Lock");
				}
			}
		});
		panelControls.add(btnLock);
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnGenerate.setBounds(30, 120, 113, 30);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isLock) {
			        if (chkRandomStat.isSelected()) {
			            handleRandomStatGeneration();
			        } else {
			            JOptionPane.showMessageDialog(contentPane, "Click the 'Lock' first.");
			        }
			    } else {
			        handleStatGeneration();
			    }
			}
		});
		panelControls.add(btnGenerate);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnReset.setBounds(212, 161, 90, 30);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isLock) {
					btnCustomStat.setEnabled(true);
				}
				
				if (isLock && chkRandomStat.isSelected()) {
					cboArtifactPiece.setEnabled(true);
					
					chkRandomStat.setEnabled(true);
					chkFullUpgrade.setEnabled(true);
				}
				
				lblStatus.setText("Max Upgrade : 0");
				artifactStat.resetStat();
				artifactStat.setArtifactPiece(null);
				artifactDisplayerPanel.displayStat(); // update the stat
				
				JOptionPane.showMessageDialog(contentPane, "Stat is removed.");
				
				btnLock.setEnabled(true);
				btnGenerate.setEnabled(true);
				btnSkip.setEnabled(false);
				btnRoll.setEnabled(false);
				btnReroll.setEnabled(false);
				btnReset.setEnabled(false);
				btnGenerate.requestFocus();
				rollCounter = 0;
				isNewSubStat = true;
				maxUpgrade = 0;
			}
		});
		panelControls.add(btnReset);
		btnReset.setEnabled(false);
		
		btnRoll = new JButton("Roll");
		btnRoll.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRoll.setBounds(111, 161, 90, 30);
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dialogTitle = "";
				String dialogMessage = "";
				
				btnSkip.setEnabled(false);
				
				if (maxUpgrade == 4 && isNewSubStat) {
					artifactStat.upgradeSubStatValue();
					isNewSubStat = false;
					
					dialogTitle = "New Sub-Stat";
					dialogMessage = artifactStat.getCurrentNewSubStat();
				} else if (rollCounter < maxUpgrade) {
					artifactStat.upgradeSubStatValue();
					rollCounter++;
					
					if (rollCounter == maxUpgrade) {
						btnRoll.setEnabled(false);
						btnReroll.requestFocus();
					}
					
					dialogTitle = "Sub-Stat Upgrade";
					dialogMessage = artifactStat.getCurrentUpgradedSubStat();
				}
				
				artifactDisplayerPanel.displayStat(); // update the stat
				
				JOptionPane.showMessageDialog(contentPane, dialogMessage, dialogTitle, JOptionPane.PLAIN_MESSAGE);
				
				btnReroll.setEnabled(true);
			}
		});
		panelControls.add(btnRoll);
		btnRoll.setEnabled(false);
		
		btnReroll = new JButton("Reroll");
		btnReroll.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnReroll.setBounds(170, 120, 113, 30);
		btnReroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				artifactStat.rerollStat();
				artifactDisplayerPanel.displayStat(); // update the stat
				
				btnSkip.setEnabled(true);
				btnRoll.setEnabled(true);
				btnReroll.setEnabled(false);
				btnReset.setEnabled(true);
				btnRoll.requestFocus();
				rollCounter = 0;
				isNewSubStat = true;
			}
		});
		panelControls.add(btnReroll);
		btnReroll.setEnabled(false);
		
		btnCustomStat = new JButton("Custom Stat");
		btnCustomStat.setEnabled(false);
		btnCustomStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oneTime) {
					oneTime = false;
					
					InstructionDialog.showPaginationDialog(frameAncestor, "Artifact RNG - Custom Stat", displayCustomStatMessage());
				}
				
				try {
					CustomStatDialog customStat = new CustomStatDialog(frameAncestor, artifactStat);
					customStat.setVisible(true);
					
					if (CustomStatDialog.getIsCustomStatDisplayed()) {
						maxUpgrade = artifactStat.getMaxUpgrade();
						lblStatus.setText("Max Upgrade : " + maxUpgrade);
						artifactDisplayerPanel.displayStat();
						
						JOptionPane.showMessageDialog(frameAncestor, "Custom Stat is now displayed!");
						
						btnLock.setEnabled(false);
						btnGenerate.setEnabled(false);
						btnSkip.setEnabled(true);
						btnRoll.setEnabled(true);
						btnReset.setEnabled(true);
						btnCustomStat.setEnabled(false);
						btnRoll.requestFocus();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCustomStat.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnCustomStat.setBounds(170, 243, 113, 30);
		panelControls.add(btnCustomStat);
		
		lblStatus = new JLabel("Max Upgrade : 0");
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBackground(new Color(255, 255, 255));
		lblStatus.setBounds(159, 202, 136, 30);
		panelControls.add(lblStatus);
		
		cboArtifactPiece = new JComboBox<>(new DefaultComboBoxModel<>(new Artifact().getArtifactPiece()));
		cboArtifactPiece.setSelectedIndex(0);
		cboArtifactPiece.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboArtifactPiece.setBounds(10, 45, 169, 30);
		panelControls.add(cboArtifactPiece);
		
		JLabel lblText1 = new JLabel();
		lblText1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText1.setText("ARTIFACT PIECE");
		lblText1.setBounds(20, 8, 90, 30);
		panelControls.add(lblText1);
		
		JLabel lblText2 = new JLabel();
		lblText2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText2.setText("OPERATIONS");
		lblText2.setBounds(20, 82, 90, 30);
		panelControls.add(lblText2);
		
		JLabel lblText3 = new JLabel();
		lblText3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText3.setText("STATUS");
		lblText3.setBounds(20, 202, 90, 30);
		panelControls.add(lblText3);
		
		JLabel lblText4 = new JLabel();
		lblText4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText4.setText("OTHER");
		lblText4.setBounds(20, 243, 90, 30);
		panelControls.add(lblText4);
		
		btnSkip = new JButton("Skip");
		btnSkip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] subStats = artifactStat.skipUpgradeSubStats();
				artifactDisplayerPanel.displayStat(); // update the stat
				
				String template = "";
				
				for (int index = 0; index < subStats.length; index++) {
					template += subStats[index] + "\n";
				}
				
				JOptionPane.showMessageDialog(contentPane, template, "Final Sub-Stats", JOptionPane.PLAIN_MESSAGE);
				
				btnSkip.setEnabled(false);
				btnRoll.setEnabled(false);
				btnReroll.setEnabled(true);
				btnReroll.requestFocus();
			}
		});
		btnSkip.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSkip.setEnabled(false);
		btnSkip.setBounds(10, 161, 90, 30);
		panelControls.add(btnSkip);
		
		chkRandomStat = new JCheckBox("Random Stat");
		chkRandomStat.setHorizontalAlignment(SwingConstants.CENTER);
		chkRandomStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkRandomStat.isSelected()) {
					chkFullUpgrade.setEnabled(true);
				} else {
					chkFullUpgrade.setEnabled(false);
					chkFullUpgrade.setSelected(false);
				}
			}
		});
		chkRandomStat.setFont(new Font("Segoe UI", Font.BOLD, 11));
		chkRandomStat.setBounds(30, 290, 113, 23);
		panelControls.add(chkRandomStat);
		
		chkFullUpgrade = new JCheckBox("Full Upgrade");
		chkFullUpgrade.setHorizontalAlignment(SwingConstants.CENTER);
		chkFullUpgrade.setEnabled(false);
		chkFullUpgrade.setFont(new Font("Segoe UI", Font.BOLD, 11));
		chkFullUpgrade.setBounds(170, 290, 113, 23);
		panelControls.add(chkFullUpgrade);
	}
	
	private void handleRandomStatGeneration() {
		artifactStat.setArtifactPiece(new Artifact().generateRandomPiece());
		
		generateStatAndUpdatePanel();

	    if (chkFullUpgrade.isSelected()) {
	        invokeSkipAction();
	    } else {
	    	showStatGeneratedMessage();
	    }
	}
	
	private void handleStatGeneration() {
		artifactStat.setArtifactPiece((String) cboArtifactPiece.getSelectedItem());
		generateStatAndUpdatePanel();
	    showStatGeneratedMessage();
	}
	
	private void generateStatAndUpdatePanel() {
		artifactStat.generateStat();
	    maxUpgrade = artifactStat.getMaxUpgrade();
	    lblStatus.setText("Max Upgrade : " + maxUpgrade);
	    artifactDisplayerPanel.displayStat(); // Update the stat
	    updateButtonStates();
	}
	
	private void updateButtonStates() {
		cboArtifactPiece.setEnabled(false);
	    btnGenerate.setEnabled(false);
	    btnLock.setEnabled(false);
	    btnSkip.setEnabled(true);
	    btnRoll.setEnabled(true);
	    btnReset.setEnabled(true);
	    btnCustomStat.setEnabled(false);
	    chkRandomStat.setEnabled(false);
	    chkFullUpgrade.setEnabled(false);
	}
	
	private void invokeSkipAction() {
		// Invoke btnSkip click event
	    ActionEvent event = new ActionEvent(btnSkip, ActionEvent.ACTION_PERFORMED, null);
	    btnSkip.getActionListeners()[0].actionPerformed(event);
	}
	
	private void showStatGeneratedMessage() {
		JOptionPane.showMessageDialog(contentPane, "Stat is generated.");
		
	    btnRoll.requestFocus();
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
		}
	}
	
	private String displayArtifactSimulatorMessage() {
		
		return """
				<html>
					<style>
						.container {
							font-family: 'Segoe UI';
							overflow-wrap: break-word;
						}
						
						.emphasis {
							font-weight: bold;
						}
						
						.text-center {
							text-align: center;
						}
					</style>
					<div class='container'>
						<p class='text-center'>
							This application is <span class='emphasis'>exclusive</span> only for 5 star artifact
						</p> <br>
						<p>
							<span class='emphasis'>Max Upgrade</span>: Displays the number 
							of upgrades an artifact can have.
						</p>
						<p>
							<span class='emphasis'>Lock</span>: Locks the combo box and some buttons that
							are not needed.
						</p>
						<p>
							<span class='emphasis'>Generate</span>: Displays the artifact piece selected by the 
							user and generates random main attribute (for sands, goblet, circlet piece) and sub-stats.
						</p>
						<p>
							<span class='emphasis'>Roll</span>: Upgrades a random value of a sub-stat.
						</p>
						<p>
							<span class='emphasis'>Reroll</span>: Removes the upgrades of the sub-stats.
						</p>
						<p>
							<span class='emphasis'>Reset</span>: Clears the main attribute, sub-stats, and their values.
						</p>
						<p>
							<span class='emphasis'>Custom Stat</span>: Allows you to enter your own stat.
						</p> <br>
						<p>
							If the sub-stats are 3 only, it will have 
							<span class='emphasis'>1 New Sub-Stat and 4 Upgrades</span>. 
							If the sub-stats are 4, it will have 
							<span class='emphasis'>5 Upgrades</span>.
						</p> <br>
						<p>
							<p class='emphasis'>Flags</p>
							<ul>
								<li>
									<span class='emphasis'>Random Stat</span>: Generate random artifact piece stat.
								</li>
								<li>
									<span class='emphasis'>Full Upgrade</span>: Upgrades the value to the max upgrade. 
									(Need 'Random Stat' to be selected first)
								</li>
							</ul>
						</p> <br>
						<p>
							These flags only works if the button is "Lock". Otherwise, it will not work if it's "Unlock".
						</p> <br>
						<p>
							Occasionally, it may display incorrect decimals due to rounding errors.
						</p> <br>
						<p class='text-center'>
							Click <span class='emphasis'>'OK'</span> to continue.
						</p>
					</div>
				</html>
			""";
	}
	
	private String[] displayCustomStatMessage() {
		String[] messages = new String[2];
		
		messages[0] = """
					<html>
						<style>
							.container {
								font-family: 'Segoe UI';
								overflow-wrap: break-word;
							}
							
							.emphasis {
								font-weight: bold;
							}
							
							.text-center {
								text-align: center;
							}
						</style>
						<div class='container'>
							<p>
								<span class='emphasis'>Select an artifact piece and main attribute</span>. 
								After selecting the main attribute, the sub-stats will be displayed in the list.
							</p> <br>
							<p>
								<span class='emphasis'>Adding Sub-Stat</span>
								<ul>
									<li>Click the <span class='emphasis'>'Add Sub-Stat'</span> button.</li>
									<li>
										Select the slot where you want to add the sub-stat, then 
										click <span class='emphasis'>'OK'</span>.
									</li>
								</ul>
							</p>
							<p>
								<span class='emphasis'>Removing Specific Sub-Stat</span>
								<ul>
									<li>Click the <span class='emphasis'>'Remove Sub-Stat'</span> button.</li>
									<li>
										Select the slot where you want to remove the sub-stat, then 
										click <span class='emphasis'>'OK'</span>.
									</li>
								</ul>
							</p>
							<p>
								<span class='emphasis'>Removing All Sub-Stats</span>
								<ul>
									<li>Click the <span class='emphasis'>'Remove All'</span> button.</li>
								</ul>
							</p>
							<p>
								<span class='emphasis'>Displaying Stat</span>
								<ul>
									<li>When you made up your mind, click <span class='emphasis'>'Finalize Stat'</span> to display the stat.</li>
								</ul>
							</p>
						</div>
					</html>
				""";
		
		messages[1] = """
					<html>
						<style>
							.container {
								font-family: 'Segoe UI';
								overflow-wrap: break-word;
							}
							
							.emphasis {
								font-weight: bold;
							}
							
							.text-center {
								text-align: center;
							}
						</style>
						<div class='container'>
							<p>
								You can place <span class='emphasis'>first 2 sub-stats and first 3 or 4 sub-stats</span>.
							</p> <br>
							<p>
								If you only placed first 2 sub-stats, the rest will automatically generated whether you
								will have 3 sub-stats or 4 sub-stats.
							</p> <br>
							<p>
								<span class='emphasis'>Can display stat if</span>
								<ul>
									<li>Slot 1 and Slot 2 are filled</li>
									<li>Slot 1 to 3 are filled or Slot 1 to 4 are filled</li>
								</ul>
							</p>
							<p>
								<span class='emphasis'>Cannot display stat if</span>
								<ul>
									<li>All Slots are empty</li>
									<li>Slot 1 and Slot 2 are empty but Slot 3 and Slot 4 are filled</li>
								</ul>
							</p> <br>
							<p>
								<span class='emphasis'>Defined Affix Mode</span>
								<p>
									This mode works like new gadget "Artifact Transmuter". You will have to 
									choose <span class='emphasis'>artifact piece (sands, goblet, circlet piece)</span>, 
									<span class='emphasis'>main attribute</span>, and <span class='emphasis'>2 sub-stats</span>. 
									The rest will automatically generate.
								</p>
								<p>The catch is that you can't choose initial value, it will be automically generated as well.</p>
								<p>
									Additionally, the sub-stats chosen will have at least <span class='emphasis'>2</span> rolls
									when fully upgraded. These sub-stats will share this guaranteed roll.
								</p>
							</p> <br>
							<p>
								<span class='emphasis'>TIP</span>: To quickly add a sub-stat, select it 
								and press "ENTER" instead of clicking the button.
							</p> <br>
							<p class='text-center'>
								Click <span class='emphasis'>'OK'</span> to continue.
							</p>
						</div>
					</html>
				""";
		
		return messages;
	}
}
