import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
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
	
	private JWindow hoverPopup;
	private JLabel popupLabel;

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
		
		initHoverPopup();
		addHoverListeners();
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

	private void initHoverPopup() {
		hoverPopup = new JWindow();
		
		popupLabel = new JLabel("", JLabel.CENTER);
		popupLabel.setOpaque(true);
		popupLabel.setBackground(new Color(255, 255, 225));
		// Add padding around the text inside the label
		popupLabel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 8, 5, 8)));
		popupLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		popupLabel.setHorizontalAlignment(JLabel.LEFT);
	    popupLabel.setVerticalAlignment(JLabel.TOP);
		
		hoverPopup.getContentPane().add(popupLabel);
	}

	private void addHoverListeners() {
		// Add a listener to handle mouse movement over the panel
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// Only show popup if definedAffixMode is true
	            if (!artifactStat.getDefinedAffixMode()) {
	                hoverPopup.setVisible(false);
	                
	                return; // exit early, no popup
	            }
				
				// Update the popup text dynamically
				popupLabel.setText(getPopupText());
				
				// Let label resize itself according to the content
	            popupLabel.setSize(popupLabel.getPreferredSize());

	            // Let Swing size it automatically based on preferred size + borders
	            hoverPopup.pack();

				// Get the current cursor position on screen
				Point screenPoint = e.getLocationOnScreen();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				
				int popupWidth = hoverPopup.getWidth();
				int popupHeight = hoverPopup.getHeight();
				
				// Increase the offset from the cursor so popup won't overlap the pointer
			    int offsetX = 30;
			    int offsetY = 30;

				// Default tooltip offset near the cursor
				int x = screenPoint.x + offsetX;
				int y = screenPoint.y + offsetY;

				// Adjust if tooltip would go off the right edge of the screen
				if (x + popupWidth > screenSize.width) {
					x = screenPoint.x - popupWidth - offsetX;
				}
				
				// Adjust if tooltip would go off the bottom edge of the screen
				if (y + popupHeight > screenSize.height) {
					y = screenPoint.y - popupHeight - offsetY;
				}

				// Reposition and show the tooltip
				hoverPopup.setLocation(x, y);
				hoverPopup.setVisible(true);
			}
		});

		// Hide the tooltip when the mouse leaves the panel
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				hoverPopup.setVisible(false);
			}
		});
	}

	private String getPopupText() {
		StringBuilder sb = new StringBuilder("<html>");
		
		sb.append("<b>Defined Affix Mode</b><br>");
		sb.append("These sub-stats will share a guaranteed at least <b>2</b> rolls when fully upgraded.<br><br>");
		sb.append("Chosen sub-stats:<br>");
		
		Map<String, Integer> subStatAffixCounter = artifactStat.getSubStatUpgradeCounts();
		
		for (Map.Entry<String, Integer> entry : subStatAffixCounter.entrySet()) {
			sb.append(String.format("· %s (%d)<br>", entry.getKey(), entry.getValue()));
		}
		
		sb.append("</html>");
		
		return sb.toString();
	}
}
