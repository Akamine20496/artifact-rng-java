import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Artifact_Piece extends JPanel {
	private Artifact artifact = new Artifact();
	private JLabel lblArtifactPiece;
	private JLabel lblMainAttribute;
	private JLabel lblSlot1;
	private JLabel lblSlot2;
	private JLabel lblSlot3;
	private JLabel lblSlot4;
	private JPanel upperPanel;
	private String artifactPiece;
	private String mainAttribute;
	private String att1, att2, att3, att4;
	private double value1, value2, value3, value4;
	private double iValue1, iValue2, iValue3, iValue4;
	private double prevValue1, prevValue2, prevValue3, prevValue4;
	private boolean isGenerated = false;
	private boolean isMax = false;
	private int slotNumber = 0;
	private int upgradeCounter = 0;
	private int totalUpgrade = 0;
	private int maxUpgrade = 0;
	private boolean skipMode = false;
	
	/**
	 * Create the panel.
	 */
	public Artifact_Piece() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 225));
		setBounds(332, 11, 231, 328);
		setLayout(null);
		
		lblSlot1 = new JLabel("· None");
		lblSlot1.setForeground(new Color(40, 40, 40));
		lblSlot1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		lblSlot1.setBounds(10, 134, 211, 30);
		add(lblSlot1);
		
		lblSlot2 = new JLabel("· None");
		lblSlot2.setForeground(new Color(40, 40, 40));
		lblSlot2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		lblSlot2.setBounds(10, 175, 211, 30);
		add(lblSlot2);
		
		lblSlot3 = new JLabel("· None");
		lblSlot3.setForeground(new Color(40, 40, 40));
		lblSlot3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		lblSlot3.setBounds(10, 216, 211, 30);
		add(lblSlot3);
		
		lblSlot4 = new JLabel("· None");
		lblSlot4.setForeground(new Color(40, 40, 40));
		lblSlot4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		lblSlot4.setBounds(10, 257, 211, 30);
		add(lblSlot4);
		
		upperPanel = new JPanel();
		upperPanel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		upperPanel.setBackground(new Color(216, 180, 33));
		upperPanel.setBounds(0, 0, 231, 110);
		add(upperPanel);
		upperPanel.setLayout(null);
		
		lblMainAttribute = new JLabel("None");
		lblMainAttribute.setBackground(new Color(0, 0, 0));
		lblMainAttribute.setBounds(10, 70, 211, 29);
		lblMainAttribute.setForeground(new Color(109, 90, 16));
		upperPanel.add(lblMainAttribute);
		lblMainAttribute.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		
		lblArtifactPiece = new JLabel("None");
		lblArtifactPiece.setBounds(10, 11, 211, 29);
		upperPanel.add(lblArtifactPiece);
		lblArtifactPiece.setForeground(new Color(255, 255, 255));
		lblArtifactPiece.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		lblArtifactPiece.setBackground(Color.BLACK);
	}
	
	public void generateStat() {
		if(mainAttribute != null) {
			displayStat();
			return;
		}
		
		mainAttribute = artifact.generateMainAttribute(artifactPiece);
		maxUpgrade = artifact.noOfMaxUpgrade();

		do {
			att1 = artifact.generateSubAttribute(mainAttribute);
			att2 = artifact.generateSubAttribute(mainAttribute);
			att3 = artifact.generateSubAttribute(mainAttribute);
			att4 = maxUpgrade == 4 ? null : artifact.generateSubAttribute(mainAttribute);
			
			if(artifact.isUnique(att1, att2, att3, att4)) {
				iValue1 = value1 = artifact.generateValue(att1);
				iValue2 = value2 = artifact.generateValue(att2);
				iValue3 = value3 = artifact.generateValue(att3);
				iValue4 = value4 = maxUpgrade == 4 ? 0 : artifact.generateValue(att4);
				isGenerated = true;
			}
		} while(!isGenerated);

		isGenerated = false;
		displayStat();
	}
	
	private void displayStat() {
		lblArtifactPiece.setText(artifactPiece);
		lblMainAttribute.setText(mainAttribute);
		
		lblSlot1.setText(displayText(att1, value1));
		lblSlot2.setText(displayText(att2, value2));
		lblSlot3.setText(displayText(att3, value3));
		
		if(att4 != null) {
			lblSlot4.setText(displayText(att4, value4));
		}
	}
	
	public void rerollStat() {
		value1 = iValue1;
		value2 = iValue2;
		value3 = iValue3;
		value4 = iValue4;
		
		lblSlot1.setText(displayText(att1, value1));
		lblSlot2.setText(displayText(att2, value2));
		lblSlot3.setText(displayText(att3, value3));
		lblSlot4.setText(displayText(att4, value4));
		
		if(maxUpgrade == 4) {
			att4 = null;
			value4 = 0;
			lblSlot4.setText("· None");
		}
		
		slotNumber = 0;
		upgradeCounter = 0;
		totalUpgrade = 0;
		isMax = false;
	}
	
	public void upgradeValue() {
		if(att4 == null) {
			generate();
		} else {
			if(!isMax) {										// if the total upgrades of 5 or 4 is reached
				if(upgradeCounter == 0) {						// if the counter is 0, it will loop
					while(upgradeCounter == 0) {				// until the upgrade counter is not 0
						slotNumber = selectSlot();
						upgradeCounter = artifact.noOfUpgrade();
						totalUpgrade += upgradeCounter;
					}
					
					if(totalUpgrade == maxUpgrade) {			// if the total upgrade reaches 5 or 4, set the isMax to true
						isMax = true;							// and upgrade the value
						slot(slotNumber);
					} else if(totalUpgrade > maxUpgrade) {		// if the total upgrade exceeds 5 or 4, deduct it from counter
						totalUpgrade -= upgradeCounter;			// and set the counter to 0, then retry the process
						upgradeCounter = 0;
						upgradeValue();
					}else {
						slot(slotNumber);						// if the total upgrade is not 5
					}
				} else {
					slot(slotNumber);							// if the counter is not 0
				}
			} else {
				slot(slotNumber);								// if the isMax is true
			}
		}
	}
	
	private int selectSlot() {
		double slotChance = artifact.generateNumber();
		
		int[] slots = { 1, 2, 3, 4 };
		double[] probabilities = { 25.00, 25.00, 25.00, 25.00 };
		double cumulativeProbability = 0;
		
		for(int i = 0; i < slots.length; i++) {
			cumulativeProbability += probabilities[i];
			if(slotChance < cumulativeProbability) {
				return slots[i];
			}
		}
		
		// If we reach here, something went wrong, so just return the first element
		return slots[0];
	}
	
	private void slot(int slotNumber) {
		switch(slotNumber) {
			case 1 -> {
				prevValue1 = value1;
				value1 += artifact.generateValue(att1);
				displayUpgrade(lblSlot1, att1, prevValue1, value1);
			}
			case 2 -> {
				prevValue2 = value2;
				value2 += artifact.generateValue(att2);
				displayUpgrade(lblSlot2, att2, prevValue2, value2);
			}
			case 3 -> {
				prevValue3 = value3;
				value3 += artifact.generateValue(att3);
				displayUpgrade(lblSlot3, att3, prevValue3, value3);
			}
			case 4 -> {
				prevValue4 = value4;
				value4 += artifact.generateValue(att4);
				displayUpgrade(lblSlot4, att4, prevValue4, value4);
			}
		}
		upgradeCounter--;
	}
	
	private void displayUpgrade(JLabel lblSlot, String att, double prevValue, double currValue) {
		lblSlot.setText(displayText(att, currValue));
		
		if (!skipMode) {
			String message = artifact.formatText(att, prevValue, currValue);
			JOptionPane.showMessageDialog(getParent(), message, "Sub-Stat Upgrade", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private void generate() {
		do {
			att4 = artifact.generateSubAttribute(mainAttribute);
			
			if(artifact.isUnique(att1, att2, att3, att4)) {
				value4 = artifact.generateValue(att4);
				lblSlot4.setText(displayText(att4, value4));
				
				if (!skipMode) {
					String message = artifact.formatText(att4) + "     ----     " + artifact.formatValue(att4, value4);
					JOptionPane.showMessageDialog(getParent(), message, "New Sub-Stat", JOptionPane.PLAIN_MESSAGE);
				}
				
				isGenerated = true;
			}
		} while(!isGenerated);
		isGenerated = false;
	}
	
	public void resetStat() {
		lblArtifactPiece.setText("None");
		lblMainAttribute.setText("None");
		
		lblSlot1.setText("· None");
		lblSlot2.setText("· None");
		lblSlot3.setText("· None");
		lblSlot4.setText("· None");
		
		slotNumber = 0;
		upgradeCounter = 0;
		maxUpgrade = 0;
		totalUpgrade = 0;
		isMax = false;
		
		mainAttribute = null;
		
		att1 = null;
		att2 = null;
		att3 = null;
		att4 = null;
	}
	
	public void displaySkippedStats() {
		if (skipMode) {
			String s1, s2, s3, s4;
			
			for (int counter = 1; counter <= 5; counter++) {
				upgradeValue();
			}
			
			s1 = artifact.formatText(att1, iValue1, value1);
			s2 = artifact.formatText(att2, iValue2, value2);
			s3 = artifact.formatText(att3, iValue3, value3);
			
			if (maxUpgrade == 4) {
				s4 = "%s   ---------   %s".formatted(artifact.formatText(att4), artifact.formatValue(att4, value4));
			} else {
				s4 = artifact.formatText(att4, iValue4, value4);
			}
			
			JOptionPane.showMessageDialog(getParent(), "%s\n%s\n%s\n%s".formatted(s1, s2, s3, s4), "Final Stats", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(getParent(), "Skip Mode is False", "Artifact RNG", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private String displayText(String attribute, double value) {
		return String.format("· %s", artifact.formatText(attribute, value));
	}
	
	public void setArtifactPiece(String artifactPiece) {
		this.artifactPiece = artifactPiece;
	}
	
	public void setMainAttribute(String mainAttribute) {
		this.mainAttribute = mainAttribute;
	}
	
	public void setMaxUpgrade(int maxUpgrade) {
		this.maxUpgrade = maxUpgrade;
	}
	
	public void setSlot(String att1, String att2, String att3, String att4) {
		this.att1 = att1;
		this.att2 = att2;
		this.att3 = att3;
		this.att4 = att4;
	}
	
	public void setValue(double value1, double value2, double value3, double value4) {
		this.value1 = iValue1 = value1;
		this.value2 = iValue2 = value2;
		this.value3 = iValue3 = value3;
		this.value4 = iValue4 = value4;
	}
	
	public void setSkipMode(boolean skipMode) {
		this.skipMode = skipMode;
	}
	
	public String getArtifactPiece() {
		return artifactPiece;
	}
	
	public int getMaxUpgrade() {
		return maxUpgrade;
	}
}
