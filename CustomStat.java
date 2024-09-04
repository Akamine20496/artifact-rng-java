import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutionException;

import javax.swing.JCheckBox;

public class CustomStat extends JDialog {
	private static String title = "Setup Custom Stat";
	private static boolean isDisplayed;
	
	private Artifact artifact = new Artifact();
	private ArtifactPiece objArtifactPiece;
	private DefaultComboBoxModel<Double> modelDefaultValue = new DefaultComboBoxModel<>(new Double[] {0.00});
	private DefaultListModel<String> modelAttributes = new DefaultListModel<>();
	private JComboBox<String> cboArtifactPiece;
	private JComboBox<String> cboMainStat;
	private JComboBox<Double> cboValue1;
	private JComboBox<Double> cboValue2;
	private JComboBox<Double> cboValue3;
	private JComboBox<Double> cboValue4;
	private JList<String> listAttributes;
	private JLabel lblListHeader;
	private JLabel lblAtt1;
	private JLabel lblAtt2;
	private JLabel lblAtt3;
	private JLabel lblAtt4;
	private JScrollPane scrollPane;
	private JButton btnAddSubstat;
	private JButton btnRemoveSubstat;
	private JButton btnRemoveAll;
	private JButton btnDisplay;
	private JPanel contentPane;
	private JCheckBox chkDefinedAffixMode;
	private boolean definedAffixMode;
	private Timer timer;
	
	/**
	 * Create the dialog.
	 */
	public CustomStat(Frame owner, ArtifactPiece objArtifactPiece) {
		super(owner, title, true);
		this.objArtifactPiece = objArtifactPiece;
		
		setLookAndFeel();
		
		setSize(564, 340);
		setLocationRelativeTo(owner);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomStat.class.getResource("/assets/Amber Icon.jpg")));
		getRootPane().setDefaultButton(btnDisplay);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelSetup = new JPanel();
		panelSetup.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSetup.setBackground(new Color(192, 192, 192));
		panelSetup.setBounds(10, 11, 528, 279);
		panelSetup.setLayout(null);
		contentPane.add(panelSetup);
		
		btnDisplay = new JButton("Display Stats");
		btnDisplay.setBounds(393, 238, 125, 30);
		btnDisplay.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String attribute = (String) cboMainStat.getSelectedItem();
				
				if ((isNone(lblAtt1.getText()) || isNone(lblAtt2.getText()))
						|| (!isNone(lblAtt1.getText()) && !isNone(lblAtt2.getText()) 
								&& isNone(lblAtt3.getText()) && !isNone(lblAtt4.getText()))) {
									String message = """
												Can display stats if
													* Slot 1 and Slot 2 are filled
													* Slot 1 to 3 are filled or Slot 1 to 4 are filled
													
												Cannot display stats if
													* Slot 1 and Slot 2 are empty but Slot 3 and Slot 4 are filled
											""";
							
									JOptionPane.showMessageDialog(contentPane, message);
				} else if(lblAtt1.getText().equals(attribute) || lblAtt2.getText().equals(attribute)
						|| lblAtt3.getText().equals(attribute) || lblAtt4.getText().equals(attribute)) {
							JOptionPane.showMessageDialog(contentPane, "A sub-stat cannot be the same as the main stat!");
				} else {
					int response = JOptionPane.showConfirmDialog(contentPane, 
							"Display the stats?", "Select an option", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						// Create a SwingWorker to perform asynchronous task
						SwingWorker<Void, Void> worker = new SwingWorker<>() {
		                    @Override
		                    protected Void doInBackground() throws Exception {
		                    	if (isCancelled()) {
		                    		return null;
		                    	}
		                    	
		                    	displayStats();
		                    	
		                        return null;
		                    }

		                    @Override
		                    protected void done() {
		                    	// Stop the timer when it's done
		                    	timer.stop();
		                    	
		                        try {
		                        	if (!isCancelled()) {
		                        		get();
		                        		
		                        		isDisplayed = true;
		                        		JOptionPane.showMessageDialog(contentPane, "Stats have been displayed!");
		                        		dispose();
		                        	}		                        	
		                        } catch(InterruptedException e) {
		                        	Thread.currentThread().interrupt(); // Restore interrupted status
		                        	
		                        	JOptionPane.showMessageDialog(contentPane, "The operation was interrupted.", 
		                        			"Error!", JOptionPane.ERROR_MESSAGE);
		                        } catch(ExecutionException e) {
		                        	e.getCause();
		                        	
		                        	JOptionPane.showMessageDialog(contentPane, "An error occurred while processing the stats.", 
		                        			"Error!", JOptionPane.ERROR_MESSAGE);
		                        }
		                    }
		                };

		                // Start the worker thread
		                worker.execute();

		                // Set a timeout for the worker
		                timer = new Timer(5000, e1 -> {
		                    if (!worker.isDone()) {
		                        worker.cancel(true); // Cancel the worker
		                        
		                        JOptionPane.showMessageDialog(contentPane, "The operation took too long and has been cancelled.", 
	                        			"Error!", JOptionPane.ERROR_MESSAGE);
		                    }
		                });
		                
		                // Start the timer
		                timer.start();
					}
				}
			}
		});
		panelSetup.add(btnDisplay);
		
		JLabel lblText1 = new JLabel("ARTIFACT PIECE");
		lblText1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText1.setBounds(10, 11, 90, 23);
		panelSetup.add(lblText1);
		
		JLabel lblText2 = new JLabel("MAIN STAT");
		lblText2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText2.setBounds(10, 63, 90, 23);
		panelSetup.add(lblText2);
		
		cboArtifactPiece = new JComboBox<>(new DefaultComboBoxModel<>(artifact.getPiece()));
		cboArtifactPiece.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboArtifactPiece.setBounds(10, 29, 238, 30);
		cboArtifactPiece.setSelectedIndex(0);
		cboArtifactPiece.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectedPiece = (String) cboArtifactPiece.getSelectedItem();
				
				cboMainStat.setModel(switch (selectedPiece) {
					case Artifact.FLOWER ->
						new DefaultComboBoxModel<>(artifact.getFlower());
					case Artifact.FEATHER ->
						new DefaultComboBoxModel<>(artifact.getFeather());
					case Artifact.SANDS ->
						new DefaultComboBoxModel<>(artifact.getSands());
					case Artifact.GOBLET ->
						new DefaultComboBoxModel<>(artifact.getGoblet());
					case Artifact.CIRCLET ->
						new DefaultComboBoxModel<>(artifact.getCirclet());
					default ->
						throw new IllegalArgumentException("Unexpected piece: " + selectedPiece);
				});
			}
		});
		panelSetup.add(cboArtifactPiece);
		
		cboMainStat = new JComboBox<>(new DefaultComboBoxModel<>(artifact.getFlower()));
		cboMainStat.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboMainStat.setBounds(10, 82, 238, 30);
		cboMainStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mainStat = (String) cboMainStat.getSelectedItem();

				modelAttributes.removeAllElements();

				if(artifact.isNotSpecial(mainStat)) {
					for(String attributes : Attribute.ATTRIBUTES) {
						if(!mainStat.equals(attributes)) {
							modelAttributes.addElement(attributes);
						}
					}
				} else {
					for(String attributes : Attribute.ATTRIBUTES) {
						modelAttributes.addElement(attributes);
					}
				}

				lblListHeader.setText("SUB-STAT LIST (" + mainStat + ")");
			}
		});
		panelSetup.add(cboMainStat);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 123, 238, 145);
		panelSetup.add(scrollPane);
		
		listAttributes = new JList<>(modelAttributes);
		listAttributes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(listAttributes.getSelectedIndex() >= 0) {
						btnAddSubstat.doClick();
					}
				}
			}
		});
		listAttributes.setFont(new Font("Segoe UI", Font.BOLD, 12));
		listAttributes.setVisibleRowCount(4);
		listAttributes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listAttributes);
		
		lblListHeader = new JLabel("SUB-STAT LIST");
		lblListHeader.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblListHeader.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblListHeader);
		
		JLabel lblText3 = new JLabel("SUB-STATS");
		lblText3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText3.setBounds(258, 11, 90, 23);
		panelSetup.add(lblText3);
		
		chkDefinedAffixMode = new JCheckBox("Defined Affix Mode");
		chkDefinedAffixMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkDefinedAffixMode.isSelected()) {
					definedAffixMode = true;
					lblAtt3.setEnabled(false);
					lblAtt4.setEnabled(false);
					cboValue1.setEnabled(false);
					cboValue2.setEnabled(false);
					cboValue3.setEnabled(false);
					cboValue4.setEnabled(false);
					
					lblAtt3.setText("None");
					lblAtt4.setText("None");
					
					cboValue1.setModel(modelDefaultValue);
					cboValue2.setModel(modelDefaultValue);
					cboValue3.setModel(modelDefaultValue);
					cboValue4.setModel(modelDefaultValue);
				} else {
					definedAffixMode = false;
					lblAtt3.setEnabled(true);
					lblAtt4.setEnabled(true);
					cboValue1.setEnabled(true);
					cboValue2.setEnabled(true);
					cboValue3.setEnabled(true);
					cboValue4.setEnabled(true);
					
					if (!isNone(lblAtt1.getText())) {
						setValue(lblAtt1, cboValue1);
					}
					
					if (!isNone(lblAtt2.getText())) {
						setValue(lblAtt2, cboValue2);
					}
				}
			}
		});
		chkDefinedAffixMode.setFont(new Font("Segoe UI", Font.BOLD, 10));
		chkDefinedAffixMode.setBounds(393, 11, 125, 23);
		panelSetup.add(chkDefinedAffixMode);
		
		lblAtt4 = new JLabel("None");
		lblAtt4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAtt4.setBounds(258, 134, 125, 30);
		panelSetup.add(lblAtt4);
		
		lblAtt3 = new JLabel("None");
		lblAtt3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAtt3.setBounds(258, 102, 125, 30);
		panelSetup.add(lblAtt3);
		
		lblAtt2 = new JLabel("None");
		lblAtt2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAtt2.setBounds(258, 70, 125, 30);
		panelSetup.add(lblAtt2);
		
		lblAtt1 = new JLabel("None");
		lblAtt1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAtt1.setBounds(258, 38, 125, 30);
		panelSetup.add(lblAtt1);
		
		cboValue1 = new JComboBox<>(modelDefaultValue);
		cboValue1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboValue1.setBounds(393, 38, 125, 30);
		panelSetup.add(cboValue1);
		
		cboValue2 = new JComboBox<>(modelDefaultValue);
		cboValue2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboValue2.setBounds(393, 70, 125, 30);
		panelSetup.add(cboValue2);
		
		cboValue3 = new JComboBox<>(modelDefaultValue);
		cboValue3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboValue3.setBounds(393, 102, 125, 30);
		panelSetup.add(cboValue3);
		
		cboValue4 = new JComboBox<>(modelDefaultValue);
		cboValue4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboValue4.setBounds(393, 134, 125, 30);
		panelSetup.add(cboValue4);
		
		JLabel lblText4 = new JLabel("OPERATIONS");
		lblText4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText4.setBounds(258, 178, 90, 23);
		panelSetup.add(lblText4);
		
		btnAddSubstat = new JButton("Add Sub-Stat");
		btnAddSubstat.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAddSubstat.setBounds(258, 197, 125, 30);
		btnAddSubstat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modelAttributes.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "List is empty!");
				} else {
					if(listAttributes.getSelectedIndex() >= 0) {
						boolean isAdded = false;
						
						do {
							String attribute = listAttributes.getSelectedValue();
							
							String message1 = """
									<html>
										Enter the number to add a sub-stat and click '<b>OK</b>'
										<br> <br>
										Selected Sub-Stat: <b>%s</b>
										<br> <br>
										<b>[1]</b> Slot 1 (%s) <br>
										<b>[2]</b> Slot 2 (%s) <br>
										<b>[3]</b> Slot 3 (%s) <br>
										<b>[4]</b> Slot 4 (%s) <br>
									</html>
							""".formatted(attribute, lblAtt1.getText(), lblAtt2.getText(), lblAtt3.getText(), lblAtt4.getText());
							
							String message2 = """
									<html>
									Enter the number to add a sub-stat and click '<b>OK</b>'
									<br> <br>
									Selected Sub-Stat: <b>%s</b>
									<br> <br>
									<b>[1]</b> Slot 1 (%s) <br>
									<b>[2]</b> Slot 2 (%s) <br>
								</html>
							""".formatted(attribute, lblAtt1.getText(), lblAtt2.getText());
							
							String result = JOptionPane.showInputDialog(contentPane, 
									definedAffixMode ? message2 : message1, "Add Sub-Stat", JOptionPane.PLAIN_MESSAGE);
							
							if((result != null) && (result.length() > 0)) {
								if(attribute.equals(lblAtt1.getText()) || attribute.equals(lblAtt2.getText())
										|| attribute.equals(lblAtt3.getText()) || attribute.equals(lblAtt4.getText())) {
											JOptionPane.showMessageDialog(contentPane, attribute + " is already been added!");
								} else {
									switch(result.trim()) {
										case "1" -> {
											addStat(lblAtt1, attribute, cboValue1);
											isAdded = true;											
										}
										case "2" -> {											
											addStat(lblAtt2, attribute, cboValue2);
											isAdded = true;
										}
										case "3" -> {											
											if (!definedAffixMode) {
												addStat(lblAtt3, attribute, cboValue3);
												isAdded = true;
											} else {
												JOptionPane.showMessageDialog(contentPane, "Enter a number only 1 and 2!");
											}
										}
										case "4" -> {											
											if (!definedAffixMode) {
												addStat(lblAtt4, attribute, cboValue4);
												isAdded = true;
											} else {
												JOptionPane.showMessageDialog(contentPane, "Enter a number only 1 and 2!");
											}
										}
										default -> {											
											JOptionPane.showMessageDialog(contentPane, 
													"Enter a number only %s!".formatted(definedAffixMode ? "1 to 2" : "from 1 to 4"));
										}
									}
								}
							} else if(result == null) { // if CANCEL button is clicked and Window is CLOSED
								listAttributes.clearSelection(); // clears the list selection
								break; // stops the loop
							} else if(result.isBlank()) {
								JOptionPane.showMessageDialog(contentPane, "Enter the slot number to add the stat!");
							}
						} while(!isAdded);
						listAttributes.clearSelection(); // clears the list selection
					} else {
						JOptionPane.showMessageDialog(contentPane, "Select a sub-stat to add!");
					}
				}
			}
		});
		panelSetup.add(btnAddSubstat);
		
		btnRemoveSubstat = new JButton("Remove Sub-Stat");
		btnRemoveSubstat.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnRemoveSubstat.setBounds(258, 238, 125, 30);
		btnRemoveSubstat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isRemoved = false;
				
				if (isNone(lblAtt1.getText()) && isNone(lblAtt2.getText()) 
						&& isNone(lblAtt3.getText()) && isNone(lblAtt4.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Slots are empty!");
				} else {
					do {
						String message1 = """
								<html>
									Enter the number to remove a sub-stat and click '<b>OK</b>'
									<br> <br>
									<b>[1]</b> Slot 1 (%s) <br>
									<b>[2]</b> Slot 2 (%s) <br>
									<b>[3]</b> Slot 3 (%s) <br>
									<b>[4]</b> Slot 4 (%s) <br>
								</html>
						""".formatted(lblAtt1.getText(), lblAtt2.getText(), lblAtt3.getText(), lblAtt4.getText());
						
						String message2 = """
								<html>
									Enter the number to remove a sub-stat and click '<b>OK</b>'
									<br> <br>
									<b>[1]</b> Slot 1 (%s) <br>
									<b>[2]</b> Slot 2 (%s) <br>
								</html>
						""".formatted(lblAtt1.getText(), lblAtt2.getText());
						
						String result = JOptionPane.showInputDialog(contentPane, 
								definedAffixMode ? message2 : message1, "Remove Sub-Stat", JOptionPane.PLAIN_MESSAGE);
						
						if((result != null) && (result.length() > 0)) {
							switch (result.trim()) {
								case "1" -> {
									removeStat(lblAtt1, cboValue1);
									isRemoved = true;
								}
								case "2" -> {
									removeStat(lblAtt2, cboValue2);
									isRemoved = true;
								}
								case "3" -> {
									if (!definedAffixMode) {
										removeStat(lblAtt3, cboValue3);
										isRemoved = true;
									} else {
										JOptionPane.showMessageDialog(contentPane, "Enter a number only 1 and 2!");
									}
								}
								case "4" -> {
									if (!definedAffixMode) {
										removeStat(lblAtt4, cboValue4);
										isRemoved = true;
									} else {
										JOptionPane.showMessageDialog(contentPane, "Enter a number only 1 and 2!");
									}
								}
								default -> {
									JOptionPane.showMessageDialog(contentPane, 
											"Enter a number only %s!".formatted(definedAffixMode ? "1 to 2" : "from 1 to 4"));
								}
							}
						} else if(result == null) { // if CANCEL button is clicked and Window is CLOSED
							break; // stops the loop
						} else if(result.isBlank()) {
							JOptionPane.showMessageDialog(contentPane, "Enter the slot number to remove the stat!");
						}
					} while(!isRemoved);
				}
			}
		});
		panelSetup.add(btnRemoveSubstat);
		
		btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRemoveAll.setBounds(393, 197, 125, 30);
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNone(lblAtt1.getText()) && isNone(lblAtt2.getText()) 
						&& isNone(lblAtt3.getText()) && isNone(lblAtt4.getText())) {
					JOptionPane.showMessageDialog(contentPane, "There are no sub-stats!");
				} else {
					int response = JOptionPane.showConfirmDialog(contentPane, 
							"Remove all sub-stats?", "Select an option", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						if (definedAffixMode) {							
							lblAtt1.setText("None");
							lblAtt2.setText("None");
						} else {
							lblAtt1.setText("None");
							lblAtt2.setText("None");
							lblAtt3.setText("None");
							lblAtt4.setText("None");
							
							cboValue1.setModel(modelDefaultValue);
							cboValue2.setModel(modelDefaultValue);
							cboValue3.setModel(modelDefaultValue);
							cboValue4.setModel(modelDefaultValue);
						}
						
						JOptionPane.showMessageDialog(contentPane, "Sub-stats are removed!");
					}
				}
			}
		});
		panelSetup.add(btnRemoveAll);
	}
	
	private void removeStat(JLabel lblAtt, JComboBox<Double> cboValue) {
		String temp = null;
		
		if(isNone(lblAtt.getText())) {
			JOptionPane.showMessageDialog(contentPane, "The slot is empty!");
			temp = lblAtt.getText();
		} else {
			temp = lblAtt.getText();
			lblAtt.setText("None");
			cboValue.setModel(modelDefaultValue);
		}
		
		if (!temp.equals("None")) {
			JOptionPane.showMessageDialog(contentPane, temp + " is removed!");
		}
	}
	
	private void addStat(JLabel lblAtt, String selectedAttribute, JComboBox<Double> cboValue) {
		lblAtt.setText(selectedAttribute);
		
		if (!definedAffixMode) {
			setValue(lblAtt, cboValue);
		}
		
		JOptionPane.showMessageDialog(contentPane, selectedAttribute + " is added!");
	}
	
	private void setValue(JLabel lblAtt, JComboBox<Double> cboValue) throws IllegalArgumentException {
		String attribute = lblAtt.getText();
		
		cboValue.setModel(switch (attribute) {
		    case Attribute.HP_FLAT -> 
		    	new DefaultComboBoxModel<>(artifact.getHpFlat());
		    case Attribute.ATK_FLAT -> 
		    	new DefaultComboBoxModel<>(artifact.getAtkFlat());
		    case Attribute.DEF_FLAT -> 
		    	new DefaultComboBoxModel<>(artifact.getDefFlat());
		    case Attribute.HP_PER -> 
		    	new DefaultComboBoxModel<>(artifact.getHpPer());
		    case Attribute.ATK_PER -> 
		    	new DefaultComboBoxModel<>(artifact.getAtkPer());
		    case Attribute.DEF_PER -> 
		    	new DefaultComboBoxModel<>(artifact.getDefPer());
		    case Attribute.ENERGY_RECHARGE -> 
		    	new DefaultComboBoxModel<>(artifact.getEnergyRecharge());
		    case Attribute.ELEMENTAL_MASTERY -> 
		    	new DefaultComboBoxModel<>(artifact.getElementalMastery());
		    case Attribute.CRIT_RATE -> 
		    	new DefaultComboBoxModel<>(artifact.getCritRate());
		    case Attribute.CRIT_DMG -> 
		    	new DefaultComboBoxModel<>(artifact.getCritDamage());
		    default -> 
		    	throw new IllegalArgumentException("Invalid attribute: " + attribute);
		});
	}
	
	public void displayStats() {
		String artifactPiece = (String) cboArtifactPiece.getSelectedItem();
        String mainAttribute = (String) cboMainStat.getSelectedItem();

        String att1 = lblAtt1.getText().equals("None") ? null : lblAtt1.getText();
        String att2 = lblAtt2.getText().equals("None") ? null : lblAtt2.getText();
        String att3 = lblAtt3.getText().equals("None") ? null : lblAtt3.getText();
        String att4 = lblAtt4.getText().equals("None") ? null : lblAtt4.getText();

        double value1 = (double) cboValue1.getSelectedItem();
        double value2 = (double) cboValue2.getSelectedItem();
        double value3 = (double) cboValue3.getSelectedItem();
        double value4 = (double) cboValue4.getSelectedItem();

        objArtifactPiece.setArtifactPiece(artifactPiece);
        objArtifactPiece.setMainAttribute(mainAttribute);
        objArtifactPiece.setAttribute(att1, att2, att3, att4);
        objArtifactPiece.setValue(value1, value2, value3, value4);

        if ((att1 != null && att2 != null) && att3 == null) {
            objArtifactPiece.generateRandomCustomSubStat();
        } else {
            if (att4 == null) {
                objArtifactPiece.setMaxUpgrade(4);
            } else {
                objArtifactPiece.setMaxUpgrade(5);
            }
            objArtifactPiece.generateStat();
        }
	}
	
	public static boolean getIsDisplayed() {
		return isDisplayed;
	}
	
	private boolean isNone(String text) {
		return text.equals("None");
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
		}
	}
}
