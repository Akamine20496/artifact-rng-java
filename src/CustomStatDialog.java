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

import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomStatDialog extends JDialog {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4532225073811566600L;
	
	private static String title = "Setup Custom Stat";
	private static boolean isCustomStatDisplayed;
	
	private Attribute attribute = Attribute.getInstance();
	private Artifact artifact = new Artifact();
	private ArtifactStat artifactStat;
	private DefaultComboBoxModel<Double> modelDefaultValue = new DefaultComboBoxModel<>(new Double[] {0.00});
	private DefaultListModel<String> modelAttributes = new DefaultListModel<>();
	private JComboBox<String> cboArtifactPiece;
	private JComboBox<String> cboMainStat;
	private JComboBox<Double> cboAttrValue1;
	private JComboBox<Double> cboAttrValue2;
	private JComboBox<Double> cboAttrValue3;
	private JComboBox<Double> cboAttrValue4;
	private JList<String> listAttributes;
	private JLabel lblListHeader;
	private JLabel lblAttr1;
	private JLabel lblAttr2;
	private JLabel lblAttr3;
	private JLabel lblAttr4;
	private JScrollPane scrollPane;
	private JButton btnAddSubStat;
	private JButton btnRemoveSubStat;
	private JButton btnRemoveAll;
	private JButton btnFinalizeStat;
	private JPanel contentPane;
	private JCheckBox chkDefinedAffixMode;
	private boolean definedAffixMode;
	
	/**
	 * Create the dialog.
	 */
	public CustomStatDialog(Frame owner, ArtifactStat artifactStat) {
		super(owner, title, true);
		
		this.artifactStat = artifactStat;
		
		setLookAndFeel();
		
		setSize(564, 340);
		setLocationRelativeTo(owner);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomStatDialog.class.getResource("/assets/Amber Icon.jpg")));
		getRootPane().setDefaultButton(btnFinalizeStat);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				isCustomStatDisplayed = false;
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(240, 240, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelSetup = new JPanel();
		panelSetup.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSetup.setBackground(new Color(192, 192, 192));
		panelSetup.setBounds(10, 11, 528, 279);
		panelSetup.setLayout(null);
		contentPane.add(panelSetup);
		
		btnFinalizeStat = new JButton("Finalize Stat");
		btnFinalizeStat.setBounds(393, 238, 125, 30);
		btnFinalizeStat.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnFinalizeStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String attribute = (String) cboMainStat.getSelectedItem();
				
				if ((isNone(lblAttr1.getText()) || isNone(lblAttr2.getText())) || 
						(!isNone(lblAttr1.getText()) && !isNone(lblAttr2.getText()) && 
								isNone(lblAttr3.getText()) && !isNone(lblAttr4.getText()))) {
					String message = """
								Can display stat if
									* Slot 1 and Slot 2 are filled
									* Slot 1 to 3 are filled or Slot 1 to 4 are filled
									
								Cannot display stat if
									* All Slots are empty
									* Slot 1 and Slot 2 are empty but Slot 3 and Slot 4 are filled
							""";
			
					JOptionPane.showMessageDialog(contentPane, message);
				} else if (lblAttr1.getText().equals(attribute) || lblAttr2.getText().equals(attribute) ||
							lblAttr3.getText().equals(attribute) || lblAttr4.getText().equals(attribute)) {
					JOptionPane.showMessageDialog(contentPane, "A sub-stat cannot be the same as the main attribute!");
				} else {
					int response = JOptionPane.showConfirmDialog(contentPane, "Finalize the stat?", "Select an option", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
                    	displayCustomStat();
                        isCustomStatDisplayed = true;
                		dispose();
					}
				}
			}
		});
		panelSetup.add(btnFinalizeStat);
		
		JLabel lblText1 = new JLabel("ARTIFACT PIECE");
		lblText1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText1.setBounds(10, 11, 110, 23);
		panelSetup.add(lblText1);
		
		JLabel lblText2 = new JLabel("MAIN ATTRIBUTE");
		lblText2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText2.setBounds(10, 63, 110, 23);
		panelSetup.add(lblText2);
		
		cboArtifactPiece = new JComboBox<>(new DefaultComboBoxModel<>(artifact.getArtifactPiece()));
		cboArtifactPiece.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboArtifactPiece.setBounds(10, 29, 238, 30);
		cboArtifactPiece.setSelectedIndex(0);
		cboArtifactPiece.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectedPiece = (String) cboArtifactPiece.getSelectedItem();
				
				cboMainStat.setModel(switch (selectedPiece) {
					case Artifact.FLOWER ->
						new DefaultComboBoxModel<>(artifact.getFlowerPiece());
					case Artifact.FEATHER ->
						new DefaultComboBoxModel<>(artifact.getFeatherPiece());
					case Artifact.SANDS ->
						new DefaultComboBoxModel<>(artifact.getSandsPiece());
					case Artifact.GOBLET ->
						new DefaultComboBoxModel<>(artifact.getGobletPiece());
					case Artifact.CIRCLET ->
						new DefaultComboBoxModel<>(artifact.getCircletPiece());
					default ->
						throw new IllegalArgumentException("Invalid artifact piece: " + selectedPiece);
				});
			}
		});
		panelSetup.add(cboArtifactPiece);
		
		cboMainStat = new JComboBox<>(new DefaultComboBoxModel<>(artifact.getFlowerPiece()));
		cboMainStat.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboMainStat.setBounds(10, 82, 238, 30);
		cboMainStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mainStat = (String) cboMainStat.getSelectedItem();

				modelAttributes.removeAllElements();

				if(attribute.isNotSpecialAttributeName(mainStat)) {
					for(String attributes : Attribute.ATTRIBUTE_NAMES) {
						if(!mainStat.equals(attributes)) {
							modelAttributes.addElement(attributes);
						}
					}
				} else {
					for(String attributes : Attribute.ATTRIBUTE_NAMES) {
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
						// Invoke the btnAddSubStat click event
						ActionEvent event = new ActionEvent(btnAddSubStat, ActionEvent.ACTION_PERFORMED, null);
						btnAddSubStat.getActionListeners()[0].actionPerformed(event);
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
					lblAttr3.setEnabled(false);
					lblAttr4.setEnabled(false);
					cboAttrValue1.setEnabled(false);
					cboAttrValue2.setEnabled(false);
					cboAttrValue3.setEnabled(false);
					cboAttrValue4.setEnabled(false);
					
					lblAttr3.setText("None");
					lblAttr4.setText("None");
					
					cboAttrValue1.setModel(modelDefaultValue);
					cboAttrValue2.setModel(modelDefaultValue);
					cboAttrValue3.setModel(modelDefaultValue);
					cboAttrValue4.setModel(modelDefaultValue);
				} else {
					definedAffixMode = false;
					lblAttr3.setEnabled(true);
					lblAttr4.setEnabled(true);
					cboAttrValue1.setEnabled(true);
					cboAttrValue2.setEnabled(true);
					cboAttrValue3.setEnabled(true);
					cboAttrValue4.setEnabled(true);
					
					if (!isNone(lblAttr1.getText())) {
						setValue(lblAttr1, cboAttrValue1);
					}
					
					if (!isNone(lblAttr2.getText())) {
						setValue(lblAttr2, cboAttrValue2);
					}
				}
			}
		});
		chkDefinedAffixMode.setFont(new Font("Segoe UI", Font.BOLD, 10));
		chkDefinedAffixMode.setBounds(393, 11, 125, 23);
		panelSetup.add(chkDefinedAffixMode);
		
		lblAttr4 = new JLabel("None");
		lblAttr4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAttr4.setBounds(258, 134, 125, 30);
		panelSetup.add(lblAttr4);
		
		lblAttr3 = new JLabel("None");
		lblAttr3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAttr3.setBounds(258, 102, 125, 30);
		panelSetup.add(lblAttr3);
		
		lblAttr2 = new JLabel("None");
		lblAttr2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAttr2.setBounds(258, 70, 125, 30);
		panelSetup.add(lblAttr2);
		
		lblAttr1 = new JLabel("None");
		lblAttr1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAttr1.setBounds(258, 38, 125, 30);
		panelSetup.add(lblAttr1);
		
		cboAttrValue1 = new JComboBox<>(modelDefaultValue);
		cboAttrValue1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboAttrValue1.setBounds(393, 38, 125, 30);
		panelSetup.add(cboAttrValue1);
		
		cboAttrValue2 = new JComboBox<>(modelDefaultValue);
		cboAttrValue2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboAttrValue2.setBounds(393, 70, 125, 30);
		panelSetup.add(cboAttrValue2);
		
		cboAttrValue3 = new JComboBox<>(modelDefaultValue);
		cboAttrValue3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboAttrValue3.setBounds(393, 102, 125, 30);
		panelSetup.add(cboAttrValue3);
		
		cboAttrValue4 = new JComboBox<>(modelDefaultValue);
		cboAttrValue4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboAttrValue4.setBounds(393, 134, 125, 30);
		panelSetup.add(cboAttrValue4);
		
		JLabel lblText4 = new JLabel("OPERATIONS");
		lblText4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText4.setBounds(258, 178, 90, 23);
		panelSetup.add(lblText4);
		
		btnAddSubStat = new JButton("Add Sub-Stat");
		btnAddSubStat.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAddSubStat.setBounds(258, 197, 125, 30);
		btnAddSubStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modelAttributes.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "List is empty!");
				} else {
					if (listAttributes.getSelectedIndex() >= 0) {
						boolean isAdded = false;
						
						do {
							String attribute = listAttributes.getSelectedValue();
							
							String dialogMessage = "";
							
							if (definedAffixMode) {
								dialogMessage = """
										<html>
											<p>Enter the number to add a sub-stat and click '<b>OK</b>'</p> <br>
											<p>Selected Sub-Stat: <b>%s</b></p> <br>
											<p>
												<b>[1]</b> Slot 1 (%s) <br>
												<b>[2]</b> Slot 2 (%s) <br>
											</p>
										</html>
										""".formatted(attribute, lblAttr1.getText(), lblAttr2.getText());
							} else {
								dialogMessage = """
										<html>
											<p>Enter the number to add a sub-stat and click '<b>OK</b>'</p> <br>
											<p>Selected Sub-Stat: <b>%s</b></p> <br>
											<p>
												<b>[1]</b> Slot 1 (%s) <br>
												<b>[2]</b> Slot 2 (%s) <br>
												<b>[3]</b> Slot 3 (%s) <br>
												<b>[4]</b> Slot 4 (%s) <br>
											</p>
										</html>
										""".formatted(attribute, lblAttr1.getText(), lblAttr2.getText(), lblAttr3.getText(), lblAttr4.getText());
							}
							
							String result = JOptionPane.showInputDialog(contentPane, dialogMessage, "Add Sub-Stat", JOptionPane.PLAIN_MESSAGE);
							
							if (result != null) {
								result = result.trim();
								
								if (result.isBlank()) {
									JOptionPane.showMessageDialog(contentPane, "Enter the slot number to add the stat!");
								} else if (attribute.equals(lblAttr1.getText()) || attribute.equals(lblAttr2.getText()) || 
										attribute.equals(lblAttr3.getText()) || attribute.equals(lblAttr4.getText())) {
									JOptionPane.showMessageDialog(contentPane, attribute + " is already been added!");
								} else {
									switch (result) {
										case "1" -> {
											addSubStat(lblAttr1, attribute, cboAttrValue1);
											isAdded = true;											
										}
										case "2" -> {											
											addSubStat(lblAttr2, attribute, cboAttrValue2);
											isAdded = true;
										}
										case "3" -> {											
											if (!definedAffixMode) {
												addSubStat(lblAttr3, attribute, cboAttrValue3);
												isAdded = true;
											} else {
												JOptionPane.showMessageDialog(contentPane, "Enter a number only 1 and 2!");
											}
										}
										case "4" -> {											
											if (!definedAffixMode) {
												addSubStat(lblAttr4, attribute, cboAttrValue4);
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
							} else { // if CANCEL button is clicked and Window is CLOSED
								break; // stops the loop
							}
						} while (!isAdded);
						listAttributes.clearSelection(); // clears the list selection
					} else {
						JOptionPane.showMessageDialog(contentPane, "Select a sub-stat to add!");
					}
				}
			}
		});
		panelSetup.add(btnAddSubStat);
		
		btnRemoveSubStat = new JButton("Remove Sub-Stat");
		btnRemoveSubStat.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnRemoveSubStat.setBounds(258, 238, 125, 30);
		btnRemoveSubStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isRemoved = false;
				
				if (isNone(lblAttr1.getText()) && isNone(lblAttr2.getText()) 
						&& isNone(lblAttr3.getText()) && isNone(lblAttr4.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Slots are empty!");
				} else {
					do {
						String dialogMessage = "";
						
						if (definedAffixMode) {
							dialogMessage = """
									<html>
										<p>Enter the number to remove a sub-stat and click '<b>OK</b>'</p> <br>
										<p>
											<b>[1]</b> Slot 1 (%s) <br>
											<b>[2]</b> Slot 2 (%s) <br>
										</p>
									</html>
									""".formatted(lblAttr1.getText(), lblAttr2.getText());
						} else {
							dialogMessage = """
									<html>
										<p>Enter the number to remove a sub-stat and click '<b>OK</b>'</p> <br>
										<p>
											<b>[1]</b> Slot 1 (%s) <br>
											<b>[2]</b> Slot 2 (%s) <br>
											<b>[3]</b> Slot 3 (%s) <br>
											<b>[4]</b> Slot 4 (%s) <br>
										</p>
									</html>
									""".formatted(lblAttr1.getText(), lblAttr2.getText(), lblAttr3.getText(), lblAttr4.getText());
						}
						
						String result = JOptionPane.showInputDialog(contentPane, dialogMessage, "Remove Sub-Stat", JOptionPane.PLAIN_MESSAGE);
						
						if (result != null) {
							result = result.trim();
							
							if (result.isBlank()) {
								JOptionPane.showMessageDialog(contentPane, "Enter the slot number to remove the stat!");
							} else {
								switch (result) {
									case "1" -> {
										removeSubStat(lblAttr1, cboAttrValue1);
										isRemoved = true;
									}
									case "2" -> {
										removeSubStat(lblAttr2, cboAttrValue2);
										isRemoved = true;
									}
									case "3" -> {
										if (!definedAffixMode) {
											removeSubStat(lblAttr3, cboAttrValue3);
											isRemoved = true;
										} else {
											JOptionPane.showMessageDialog(contentPane, "Enter a number only 1 or 2!");
										}
									}
									case "4" -> {
										if (!definedAffixMode) {
											removeSubStat(lblAttr4, cboAttrValue4);
											isRemoved = true;
										} else {
											JOptionPane.showMessageDialog(contentPane, "Enter a number only 1 or 2!");
										}
									}
									default -> {
										JOptionPane.showMessageDialog(contentPane, 
												"Enter a number only %s!".formatted(definedAffixMode ? "1 or 2" : "from 1 to 4"));
									}
								}
							}
						} else { // if CANCEL button is clicked and Window is CLOSED
							break; // stops the loop
						}
					} while (!isRemoved);
				}
			}
		});
		panelSetup.add(btnRemoveSubStat);
		
		btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRemoveAll.setBounds(393, 197, 125, 30);
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNone(lblAttr1.getText()) && isNone(lblAttr2.getText()) 
						&& isNone(lblAttr3.getText()) && isNone(lblAttr4.getText())) {
					JOptionPane.showMessageDialog(contentPane, "There are no sub-stat/s!");
				} else {
					int response = JOptionPane.showConfirmDialog(contentPane, 
							"Remove all sub-stats?", "Select an option", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						if (definedAffixMode) {							
							lblAttr1.setText("None");
							lblAttr2.setText("None");
						} else {
							lblAttr1.setText("None");
							lblAttr2.setText("None");
							lblAttr3.setText("None");
							lblAttr4.setText("None");
							
							cboAttrValue1.setModel(modelDefaultValue);
							cboAttrValue2.setModel(modelDefaultValue);
							cboAttrValue3.setModel(modelDefaultValue);
							cboAttrValue4.setModel(modelDefaultValue);
						}
						
						JOptionPane.showMessageDialog(contentPane, "Sub-stats are removed!");
					}
				}
			}
		});
		panelSetup.add(btnRemoveAll);
	}
	
	private void addSubStat(JLabel lblAttr, String selectedAttribute, JComboBox<Double> cboAttrValue) {
		lblAttr.setText(selectedAttribute);
		
		if (!definedAffixMode) {
			setValue(lblAttr, cboAttrValue);
		}
		
		JOptionPane.showMessageDialog(contentPane, selectedAttribute + " is added!");
	}
	
	private void removeSubStat(JLabel lblAttr, JComboBox<Double> cboAttrValue) {
		String temp = null;
		
		if (isNone(lblAttr.getText())) {
			JOptionPane.showMessageDialog(contentPane, "The slot is empty!");
			temp = lblAttr.getText();
		} else {
			temp = lblAttr.getText();
			lblAttr.setText("None");
			cboAttrValue.setModel(modelDefaultValue);
		}
		
		if (!temp.equals("None")) {
			JOptionPane.showMessageDialog(contentPane, temp + " is removed!");
		}
	}
	
	private void setValue(JLabel lblAttr, JComboBox<Double> cboAttrValue) throws IllegalArgumentException {
		String attributeName = lblAttr.getText();
		
		cboAttrValue.setModel(switch (attributeName) {
		    case Attribute.HP_FLAT -> 
		    	new DefaultComboBoxModel<>(attribute.getHpFlat());
		    case Attribute.ATK_FLAT -> 
		    	new DefaultComboBoxModel<>(attribute.getAtkFlat());
		    case Attribute.DEF_FLAT -> 
		    	new DefaultComboBoxModel<>(attribute.getDefFlat());
		    case Attribute.HP_PER -> 
		    	new DefaultComboBoxModel<>(attribute.getHpPer());
		    case Attribute.ATK_PER -> 
		    	new DefaultComboBoxModel<>(attribute.getAtkPer());
		    case Attribute.DEF_PER -> 
		    	new DefaultComboBoxModel<>(attribute.getDefPer());
		    case Attribute.ENERGY_RECHARGE -> 
		    	new DefaultComboBoxModel<>(attribute.getEnergyRecharge());
		    case Attribute.ELEMENTAL_MASTERY -> 
		    	new DefaultComboBoxModel<>(attribute.getElementalMastery());
		    case Attribute.CRIT_RATE -> 
		    	new DefaultComboBoxModel<>(attribute.getCritRate());
		    case Attribute.CRIT_DMG -> 
		    	new DefaultComboBoxModel<>(attribute.getCritDamage());
		    default -> 
		    	throw new IllegalArgumentException("Invalid attributeName: " + attributeName);
		});
	}
	
	public void displayCustomStat() {
		String artifactPiece = (String) cboArtifactPiece.getSelectedItem();
        String mainAttribute = (String) cboMainStat.getSelectedItem();

        String attr1 = lblAttr1.getText();
        String attr2 = lblAttr2.getText();
        String attr3 = lblAttr3.getText().equals("None") ? null : lblAttr3.getText();
        String attr4 = lblAttr4.getText().equals("None") ? null : lblAttr4.getText();

        double value1 = (double) cboAttrValue1.getSelectedItem();
        double value2 = (double) cboAttrValue2.getSelectedItem();
        double value3 = (double) cboAttrValue3.getSelectedItem();
        double value4 = (double) cboAttrValue4.getSelectedItem();

        artifactStat.setArtifactPiece(artifactPiece);
        artifactStat.setMainAttribute(mainAttribute);
        artifactStat.updateArtifactSubStats(
        		new ArtifactSubStat(attr1, value1), new ArtifactSubStat(attr2, value2),
        		new ArtifactSubStat(attr3, value3), new ArtifactSubStat(attr4, value4)
        );

        if ((attr1 != null && attr2 != null) && (attr3 == null || definedAffixMode)) {
        	artifactStat.generateDefinedAffixModeSubStats();
        } else {
            if (attr4 == null) {
            	artifactStat.setMaxUpgrade(4);
            	artifactStat.generatePreviewAttributeNameForFourthSubStat();
            } else {
            	artifactStat.setMaxUpgrade(5);
            }
        }
        
        // clear the object
        artifactStat = null;
	}
	
	public static boolean getIsCustomStatDisplayed() {
		return isCustomStatDisplayed;
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
