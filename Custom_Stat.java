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
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Custom_Stat extends JDialog {
	private Artifact artifact = new Artifact();
	private Artifact_Piece objArtifactPiece;
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
	private static boolean isSaved = false;
	
	/**
	 * Create the dialog.
	 */
	public Custom_Stat() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				isSaved = false;
			}
		});
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Custom_Stat.class.getResource("/assets/Amber Icon.jpg")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Setup Custom Stat");
		setModal(true);
		setSize(564, 340);
		getContentPane().setLayout(new BorderLayout(0, 0));
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
		
		btnDisplay = new JButton("Save");
		btnDisplay.setBounds(393, 238, 125, 30);
		btnDisplay.setFont(new Font("Segoe UI", Font.BOLD, 12));
		panelSetup.add(btnDisplay);
		
		JLabel lblText1 = new JLabel("ARTIFACT PIECE");
		lblText1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText1.setBounds(10, 11, 90, 23);
		panelSetup.add(lblText1);
		
		JLabel lblText2 = new JLabel("MAIN STAT");
		lblText2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText2.setBounds(10, 63, 90, 23);
		panelSetup.add(lblText2);
		
		cboMainStat = new JComboBox<>(new DefaultComboBoxModel<>(artifact.getFlower()));
		cboMainStat.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboMainStat.setBounds(10, 82, 238, 30);
		panelSetup.add(cboMainStat);
		
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
		
		cboValue4 = new JComboBox<>(modelDefaultValue);
		cboValue4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboValue4.setBounds(393, 134, 125, 30);
		panelSetup.add(cboValue4);
		
		cboValue3 = new JComboBox<>(modelDefaultValue);
		cboValue3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboValue3.setBounds(393, 102, 125, 30);
		panelSetup.add(cboValue3);
		
		cboValue2 = new JComboBox<>(modelDefaultValue);
		cboValue2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboValue2.setBounds(393, 70, 125, 30);
		panelSetup.add(cboValue2);
		
		cboValue1 = new JComboBox<>(modelDefaultValue);
		cboValue1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboValue1.setBounds(393, 38, 125, 30);
		panelSetup.add(cboValue1);
		
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
		
		lblListHeader = new JLabel("SUB STATS");
		lblListHeader.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblListHeader.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblListHeader);
		
		cboArtifactPiece = new JComboBox<>(new DefaultComboBoxModel<>(artifact.getPiece()));
		cboArtifactPiece.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cboArtifactPiece.setBounds(10, 29, 238, 30);
		cboArtifactPiece.setSelectedIndex(0);
		panelSetup.add(cboArtifactPiece);
		
		btnAddSubstat = new JButton("Add Sub-Stat");
		btnAddSubstat.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAddSubstat.setBounds(258, 197, 125, 30);
		panelSetup.add(btnAddSubstat);
		
		btnRemoveSubstat = new JButton("Remove Sub-Stat");
		btnRemoveSubstat.setBounds(258, 238, 125, 30);
		panelSetup.add(btnRemoveSubstat);
		btnRemoveSubstat.setFont(new Font("Segoe UI", Font.BOLD, 11));
		
		btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRemoveAll.setBounds(393, 197, 125, 30);
		panelSetup.add(btnRemoveAll);
		
		JLabel lblText3 = new JLabel("STAT DETAILS");
		lblText3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText3.setBounds(258, 11, 90, 23);
		panelSetup.add(lblText3);
		
		JLabel lblText4 = new JLabel("OPERATIONS");
		lblText4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblText4.setBounds(258, 178, 90, 23);
		panelSetup.add(lblText4);
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNone(lblAtt1.getText()) && isNone(lblAtt2.getText()) 
						&& isNone(lblAtt3.getText()) && isNone(lblAtt4.getText())) {
					JOptionPane.showMessageDialog(contentPane, "There are no sub-stats!");
				} else {
					lblAtt1.setText("None");
					lblAtt2.setText("None");
					lblAtt3.setText("None");
					lblAtt4.setText("None");
					
					cboValue1.setModel(modelDefaultValue);
					cboValue2.setModel(modelDefaultValue);
					cboValue3.setModel(modelDefaultValue);
					cboValue4.setModel(modelDefaultValue);
					JOptionPane.showMessageDialog(contentPane, "Sub-stats are removed!");
				}
			}
		});
		btnRemoveSubstat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isRemoved = false;
				
				if (isNone(lblAtt1.getText()) && isNone(lblAtt2.getText()) 
						&& isNone(lblAtt3.getText()) && isNone(lblAtt4.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Slots are empty!");
				} else {
					do {
								
						String message = """
								<html>
									Enter the number to remove a sub-stat and click '<b>OK</b>'
									<br> <br>
									<b>[1]</b> Slot 1 (%s) <br>
									<b>[2]</b> Slot 2 (%s) <br>
									<b>[3]</b> Slot 3 (%s) <br>
									<b>[4]</b> Slot 4 (%s) <br>
								</html>
						""".formatted(lblAtt1.getText(), lblAtt2.getText(), lblAtt3.getText(), lblAtt4.getText());
						
						String result = JOptionPane.showInputDialog(contentPane, message, "Remove Sub-Stat", JOptionPane.PLAIN_MESSAGE);
						
						if((result != null) && (result.length() > 0)) {
							switch (result) {
								case "1" -> {
									removeStat(lblAtt1, cboValue1);
									isRemoved = true;
								}
								case "2" -> {
									removeStat(lblAtt2, cboValue2);
									isRemoved = true;
								}
								case "3" -> {
									removeStat(lblAtt3, cboValue3);
									isRemoved = true;
								}
								case "4" -> {
									removeStat(lblAtt4, cboValue4);
									isRemoved = true;
								}
								default -> {
									JOptionPane.showMessageDialog(contentPane, "Enter a number only from 1 to 4!");									
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
		btnAddSubstat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modelAttributes.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "List is empty!");
				} else {
					if(listAttributes.getSelectedIndex() >= 0) {
						boolean isAdded = false;
						
						do {
							String attribute = listAttributes.getSelectedValue();
							
							String message = """
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
							
							String result = JOptionPane.showInputDialog(contentPane, message, "Add Sub-Stat", JOptionPane.PLAIN_MESSAGE);
							
							if((result != null) && (result.length() > 0)) {
								if(attribute.equals(lblAtt1.getText()) || attribute.equals(lblAtt2.getText())
										|| attribute.equals(lblAtt3.getText()) || attribute.equals(lblAtt4.getText())) {
									JOptionPane.showMessageDialog(contentPane, attribute + " is already been added!");
								} else {
									switch(result) {
										case "1" -> {
											addStat(lblAtt1, attribute, cboValue1);
											isAdded = true;											
										}
										case "2" -> {											
											addStat(lblAtt2, attribute, cboValue2);
											isAdded = true;
										}
										case "3" -> {											
											addStat(lblAtt3, attribute, cboValue3);
											isAdded = true;
										}
										case "4" -> {											
											addStat(lblAtt4, attribute, cboValue4);
											isAdded = true;
										}
										default -> {											
											JOptionPane.showMessageDialog(contentPane, "Enter a number only from 1 to 4!");
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
						JOptionPane.showMessageDialog(contentPane, "Select a substat to add!");
					}
				}
			}
		});
		cboArtifactPiece.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectedItem = (String) cboArtifactPiece.getSelectedItem();
				
				cboMainStat.setModel(switch(selectedItem) {
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
						throw new IllegalArgumentException("Unexpected item: " + selectedItem);
				});
			}
		});
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

				lblListHeader.setText("SUB STATS (" + mainStat + ")");
			}
		});
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String attribute = (String) cboMainStat.getSelectedItem();
				
				if(isNone(lblAtt1.getText()) || isNone(lblAtt2.getText()) || isNone(lblAtt3.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Add Sub-stats!");
				} else if(lblAtt1.getText().equals(attribute) || lblAtt2.getText().equals(attribute)
						|| lblAtt3.getText().equals(attribute) || lblAtt4.getText().equals(attribute)) {
					JOptionPane.showMessageDialog(contentPane, "A sub-stat cannot be the same as the main stat!");
				} else {
					isSaved = true;
					displayStats();
					dispose();
					JOptionPane.showMessageDialog(contentPane, "Stats has been displayed!");
				}
			}
		});
	}
	
	private void removeStat(JLabel lblSlot, JComboBox<Double> attributeValue) {
		String temp = null;
		
		if(isNone(lblSlot.getText())) {
			JOptionPane.showMessageDialog(contentPane, "The slot is empty!");
			temp = lblSlot.getText();
		} else {
			temp = lblSlot.getText();
			lblSlot.setText("None");
			attributeValue.setModel(modelDefaultValue);
		}
		
		if (!temp.equals("None")) {
			JOptionPane.showMessageDialog(contentPane, temp + " is removed!");
		}
	}
	
	private void addStat(JLabel lblSlot, String selectedAttribute, JComboBox<Double> attributeValue) {
		lblSlot.setText(selectedAttribute);
		setValue(lblSlot, attributeValue);
		JOptionPane.showMessageDialog(contentPane, selectedAttribute + " is added!");
	}
	
	private void setValue(JLabel lblSlot, JComboBox<Double> attributeValue) throws IllegalArgumentException {
		String attribute = lblSlot.getText();
		
		attributeValue.setModel(switch(attribute) {
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
		
		String att1 = lblAtt1.getText();
		String att2 = lblAtt2.getText();
		String att3 = lblAtt3.getText();
		String att4 = lblAtt4.getText();
		
		double value1 = (double) cboValue1.getSelectedItem();
		double value2 = (double) cboValue2.getSelectedItem();
		double value3 = (double) cboValue3.getSelectedItem();
		double value4 = (double) cboValue4.getSelectedItem();
		
		if(isNone(att4)) {
			att4 = null;
			objArtifactPiece.setMaxUpgrade(4);
		} else {
			objArtifactPiece.setMaxUpgrade(5);
		}
		
		objArtifactPiece.setArtifactPiece(artifactPiece);
		objArtifactPiece.setMainAttribute(mainAttribute);
		objArtifactPiece.setSlot(att1, att2, att3, att4);
		objArtifactPiece.setValue(value1, value2, value3, value4);
	}
	
	public static boolean isSaved() {
		return isSaved;
	}
	
	private boolean isNone(String text) {
		return text.equals("None");
	}
	
	public void setAsMemoryAddress(Artifact_Piece objArtifactPiece) {
		this.objArtifactPiece = objArtifactPiece;
	}
}
