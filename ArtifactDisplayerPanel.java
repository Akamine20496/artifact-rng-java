import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class ArtifactDisplayerPanel extends JPanel {
	private Artifact artifact = new Artifact();
	private JPanel upperPanel;
	private JLabel lblArtifactPiece;
	private JLabel lblMainAttribute;
	private String artifactPiece;
	private String mainAttribute;
	private JLabel lblSlot1, lblSlot2, lblSlot3, lblSlot4;
	private String att1, att2, att3, att4;
	private double value1, value2, value3, value4;
	private double iValue1, iValue2, iValue3, iValue4;
	private double prevValue1, prevValue2, prevValue3, prevValue4;
	private boolean isMax;
	private int slotNumber;
	private int upgradeCounter;
	private int totalUpgrade;
	private int maxUpgrade;
	private boolean skipMode;

	/**
	 * Create the panel.
	 */
	public ArtifactDisplayerPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 225));
		setBounds(332, 11, 231, 328);
		setLayout(null);

		lblSlot1 = new JLabel(displayNoneSubStat());
		lblSlot1.setForeground(new Color(40, 40, 40));
		lblSlot1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		lblSlot1.setBounds(10, 134, 211, 30);
		add(lblSlot1);

		lblSlot2 = new JLabel(displayNoneSubStat());
		lblSlot2.setForeground(new Color(40, 40, 40));
		lblSlot2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		lblSlot2.setBounds(10, 175, 211, 30);
		add(lblSlot2);

		lblSlot3 = new JLabel(displayNoneSubStat());
		lblSlot3.setForeground(new Color(40, 40, 40));
		lblSlot3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		lblSlot3.setBounds(10, 216, 211, 30);
		add(lblSlot3);

		lblSlot4 = new JLabel(displayNoneSubStat());
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

	public void generateStats() {
		if (mainAttribute != null) {
			displayStats();
			return;
		}
		
		mainAttribute = artifact.generateMainAttribute(artifactPiece);
		maxUpgrade = artifact.generateMaxUpgrade();

		att1 = artifact.generateSubAttribute(mainAttribute);
		att2 = artifact.generateSubAttribute(mainAttribute, att1);
		att3 = artifact.generateSubAttribute(mainAttribute, att1, att2);
		att4 = maxUpgrade == 4 ? null : artifact.generateSubAttribute(mainAttribute, att1, att2, att3);

		iValue1 = value1 = artifact.generateValue(att1);
		iValue2 = value2 = artifact.generateValue(att2);
		iValue3 = value3 = artifact.generateValue(att3);
		iValue4 = value4 = maxUpgrade == 4 ? 0 : artifact.generateValue(att4);
		
		displayStats();
	}

	public void generateRandomCustomSubStats() {
		maxUpgrade = artifact.generateMaxUpgrade();
		
		att3 = artifact.generateSubAttribute(mainAttribute, att1, att2);
		att4 = maxUpgrade == 4 ? null : artifact.generateSubAttribute(mainAttribute, att1, att2, att3);

		if (iValue1 == 0 && iValue2 == 0) {
			iValue1 = value1 = artifact.generateValue(att1);
			iValue2 = value2 = artifact.generateValue(att2);
		}

		iValue3 = value3 = artifact.generateValue(att3);
		iValue4 = value4 = maxUpgrade == 4 ? 0 : artifact.generateValue(att4);
		
		displayStats();
	}

	private void displayStats() {
		lblArtifactPiece.setText(artifactPiece);
		lblMainAttribute.setText(mainAttribute);

		lblSlot1.setText(displayText(att1, value1));
		lblSlot2.setText(displayText(att2, value2));
		lblSlot3.setText(displayText(att3, value3));
		lblSlot4.setText(maxUpgrade != 4 ? displayText(att4, value4) : displayNoneSubStat());
	}

	public void rerollStat() {
		if (maxUpgrade == 4) {
			att4 = null;
		}

		value1 = iValue1;
		value2 = iValue2;
		value3 = iValue3;
		value4 = iValue4;

		lblSlot1.setText(displayText(att1, value1));
		lblSlot2.setText(displayText(att2, value2));
		lblSlot3.setText(displayText(att3, value3));
		lblSlot4.setText(maxUpgrade != 4 ? displayText(att4, value4) : displayNoneSubStat());

		slotNumber = 0;
		upgradeCounter = 0;
		totalUpgrade = 0;
		isMax = false;
	}

	private void generateFourthSubStat() {
		att4 = artifact.generateSubAttribute(mainAttribute, att1, att2, att3);
		
		value4 = artifact.generateValue(att4);
		
		lblSlot4.setText(displayText(att4, value4));
		
		if (!skipMode) {
			String message = artifact.formatText(att4) + "     ----     " + artifact.formatValue(att4, value4);
			JOptionPane.showMessageDialog(getParent(), message, "New Sub-Stat", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void upgradeSubStatValue() {
		if (att4 == null) {
			generateFourthSubStat();
		} else {
			if (!isMax) { 													// if the total upgrades of 5 or 4 is reached
				if (upgradeCounter == 0) { 									// if the counter is 0, it will loop
					while (upgradeCounter == 0) { 							// until the upgrade counter is not 0
						slotNumber = artifact.generateRandomSlot();
						upgradeCounter = artifact.generateNoOfUpgrade();
						totalUpgrade += upgradeCounter;
					}

					if (totalUpgrade == maxUpgrade) { 						// if the total upgrade reaches 5 or 4, set the isMax to true
						isMax = true; 										// and upgrade the value
						selectSlot(slotNumber);
					} else if (totalUpgrade > maxUpgrade) { 				// if the total upgrade exceeds 5 or 4, deduct it from counter
						totalUpgrade -= upgradeCounter; 					// and set the counter to 0, then retry the process
						upgradeCounter = 0;
						upgradeSubStatValue();
					} else {
						selectSlot(slotNumber); 							// if the total upgrade is not 5
					}
				} else {
					selectSlot(slotNumber); 								// if the counter is not 0
				}
			} else {
				selectSlot(slotNumber); 									// if the isMax is true
			}
		}
	}

	private void selectSlot(int slotNumber) {
		switch (slotNumber) {
			case 1 -> {
				prevValue1 = value1;
				value1 += artifact.generateValue(att1);
				displaySubStatUpgrade(lblSlot1, att1, prevValue1, value1);
			}
			case 2 -> {
				prevValue2 = value2;
				value2 += artifact.generateValue(att2);
				displaySubStatUpgrade(lblSlot2, att2, prevValue2, value2);
			}
			case 3 -> {
				prevValue3 = value3;
				value3 += artifact.generateValue(att3);
				displaySubStatUpgrade(lblSlot3, att3, prevValue3, value3);
			}
			case 4 -> {
				prevValue4 = value4;
				value4 += artifact.generateValue(att4);
				displaySubStatUpgrade(lblSlot4, att4, prevValue4, value4);
			}
		}
		
		upgradeCounter--;
	}

	private void displaySubStatUpgrade(JLabel lblSlot, String att, double prevValue, double currValue) {
		lblSlot.setText(displayText(att, currValue));

		if (!skipMode) {
			String message = artifact.formatText(att, prevValue, currValue);
			JOptionPane.showMessageDialog(getParent(), message, "Sub-Stat Upgrade", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void resetStats() {
		lblArtifactPiece.setText("None");
		lblMainAttribute.setText("None");

		lblSlot1.setText(displayNoneSubStat());
		lblSlot2.setText(displayNoneSubStat());
		lblSlot3.setText(displayNoneSubStat());
		lblSlot4.setText(displayNoneSubStat());

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

		iValue1 = value1 = 0;
		iValue2 = value2 = 0;
		iValue3 = value3 = 0;
		iValue4 = value4 = 0;
	}

	public void displaySkippedStats() {
		if (skipMode) {
			String s1, s2, s3, s4;

			for (int counter = 1; counter <= 5; counter++) {
				upgradeSubStatValue();
			}

			s1 = artifact.formatText(att1, iValue1, value1);
			s2 = artifact.formatText(att2, iValue2, value2);
			s3 = artifact.formatText(att3, iValue3, value3);
			s4 = maxUpgrade != 4 ? artifact.formatText(att4, iValue4, value4)
					: "%s   ---------   %s".formatted(artifact.formatText(att4), artifact.formatValue(att4, value4));

			JOptionPane.showMessageDialog(getParent(), "%s\n%s\n%s\n%s".formatted(s1, s2, s3, s4), "Final Stats",
					JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(getParent(), "Skip Mode is False", "Artifact RNG", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private String displayNoneSubStat() {
		return "· None";
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

	public void setAttribute(String att1, String att2, String att3, String att4) {
		this.att1 = att1;
		this.att2 = att2;
		this.att3 = att3;
		this.att4 = att4;
	}

	public void setValue(double value1, double value2, double value3, double value4) {
		iValue1 = this.value1 = value1;
		iValue2 = this.value2 = value2;
		iValue3 = this.value3 = value3;
		iValue4 = this.value4 = value4;
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

	@Override
	public String toString() {
		return """
			ArtifactPiece {
				artifactPiece	= '%s'
				mainAttribute	= '%s'
				maxUpgrade		= '%d'
				att1 = '%s' [iValue1 = %.2f, value1 = %.2f]
				att2 = '%s' [iValue2 = %.2f, value2 = %.2f]
				att3 = '%s' [iValue4 = %.2f, value3 = %.2f]
				att4 = '%s' [iValue4 = %.2f, value4 = %.2f]
			}
		""".formatted(
				artifactPiece, 
				mainAttribute, 
				maxUpgrade, 
				att1, iValue1, value1, 
				att2, iValue2, value2,
				att3, iValue3, value3, 
				att4, iValue4, value4
		);
	}
}
