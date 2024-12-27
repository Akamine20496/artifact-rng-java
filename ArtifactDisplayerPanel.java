import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class ArtifactDisplayerPanel extends JPanel {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private ArtifactStat artifactStat;
	private JPanel upperPanel;
	private JLabel lblArtifactPiece;
	private JLabel lblMainAttribute;
	private JLabel lblSlot1, lblSlot2, lblSlot3, lblSlot4;

	/**
	 * Create the panel.
	 */
	public ArtifactDisplayerPanel(ArtifactStat artifactStat) {
		this.artifactStat = artifactStat;
		
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
		upperPanel.setLayout(null);
		add(upperPanel);

		lblMainAttribute = new JLabel("None");
		lblMainAttribute.setForeground(new Color(109, 90, 16));
		lblMainAttribute.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		lblMainAttribute.setBackground(new Color(0, 0, 0));
		lblMainAttribute.setBounds(10, 70, 211, 29);
		upperPanel.add(lblMainAttribute);

		lblArtifactPiece = new JLabel("None");
		lblArtifactPiece.setForeground(new Color(255, 255, 255));
		lblArtifactPiece.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		lblArtifactPiece.setBackground(Color.BLACK);
		lblArtifactPiece.setBounds(10, 11, 211, 29);
		upperPanel.add(lblArtifactPiece);
	}

	public void displayStat() {
		String artifactPiece = artifactStat.getArtifactPiece();
		String mainAttribute = artifactStat.getMainAttribute();
		
		lblArtifactPiece.setText(artifactPiece != null ? artifactPiece : "None");
		lblMainAttribute.setText(mainAttribute != null ? mainAttribute : "None");

		JLabel[] lblSlots = { lblSlot1, lblSlot2, lblSlot3, lblSlot4 };
		
		int length = artifactStat.getArraySubStatsLength();
		
		for (int index = 0; index < length; index++) {
			lblSlots[index].setText(displaySubStat(artifactStat.getSubStatAt(index).getSubStat()));
		}
	}

	private String displayNoneSubStat() {
		return "· None";
	}

	private String displaySubStat(String subStat) {
		return String.format("· %s", subStat);
	}
}
