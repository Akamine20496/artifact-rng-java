import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReshapeResultDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static String title = "Reshape Configuration";
	
	private ArtifactStat artifactStat;
	private ReshapeConfig reshapeConfig;

	private JLabel lblSlot1Before;
	private JLabel lblSlot2Before;
	private JLabel lblSlot3Before;
	private JLabel lblSlot4Before;

	private JLabel lblSlot1After;
	private JLabel lblSlot2After;
	private JLabel lblSlot3After;
	private JLabel lblSlot4After;
	private JLabel lblGuaranteedRolls;

	/**
	 * Create the dialog.
	 */
	public ReshapeResultDialog(Frame owner, ArtifactStat artifactStat, ReshapeConfig reshapeConfig) {
		super(owner, title, true);
		getContentPane().setFont(new Font("Segoe UI", Font.BOLD, 11));
		getContentPane().setBackground(new Color(192, 192, 192));
		
		this.artifactStat = artifactStat;
		this.reshapeConfig = reshapeConfig;
		
		setTitle("Reshape Result");
		
		setLookAndFeel();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(431, 295);
		setLocationRelativeTo(owner);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ArtifactSimulator.class.getResource("/assets/Amber Icon.jpg")));
		getContentPane().setLayout(null);
		
		JPanel panelBeforeStat = new JPanel();
		panelBeforeStat.setBackground(new Color(255, 255, 225));
		panelBeforeStat.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelBeforeStat.setBounds(10, 42, 182, 155);
		getContentPane().add(panelBeforeStat);
		panelBeforeStat.setLayout(null);
		
		lblSlot1Before = new JLabel("");
		lblSlot1Before.setForeground(new Color(40, 40, 40));
		lblSlot1Before.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSlot1Before.setBounds(10, 11, 162, 26);
		panelBeforeStat.add(lblSlot1Before);
		
		lblSlot2Before = new JLabel("");
		lblSlot2Before.setForeground(new Color(40, 40, 40));
		lblSlot2Before.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSlot2Before.setBounds(10, 46, 162, 26);
		panelBeforeStat.add(lblSlot2Before);
		
		lblSlot3Before = new JLabel("");
		lblSlot3Before.setForeground(new Color(40, 40, 40));
		lblSlot3Before.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSlot3Before.setBounds(10, 82, 162, 26);
		panelBeforeStat.add(lblSlot3Before);
		
		lblSlot4Before = new JLabel("");
		lblSlot4Before.setForeground(new Color(40, 40, 40));
		lblSlot4Before.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSlot4Before.setBounds(10, 118, 162, 26);
		panelBeforeStat.add(lblSlot4Before);
		
		JPanel panelAfterStat = new JPanel();
		panelAfterStat.setBackground(new Color(255, 255, 225));
		panelAfterStat.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelAfterStat.setBounds(223, 42, 182, 155);
		getContentPane().add(panelAfterStat);
		panelAfterStat.setLayout(null);
		
		lblSlot1After = new JLabel("");
		lblSlot1After.setForeground(new Color(40, 40, 40));
		lblSlot1After.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSlot1After.setBounds(10, 11, 162, 26);
		panelAfterStat.add(lblSlot1After);
		
		lblSlot2After = new JLabel("");
		lblSlot2After.setForeground(new Color(40, 40, 40));
		lblSlot2After.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSlot2After.setBounds(10, 46, 162, 26);
		panelAfterStat.add(lblSlot2After);
		
		lblSlot3After = new JLabel("");
		lblSlot3After.setForeground(new Color(40, 40, 40));
		lblSlot3After.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSlot3After.setBounds(10, 82, 162, 26);
		panelAfterStat.add(lblSlot3After);
		
		lblSlot4After = new JLabel("");
		lblSlot4After.setForeground(new Color(40, 40, 40));
		lblSlot4After.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSlot4After.setBounds(10, 118, 162, 26);
		panelAfterStat.add(lblSlot4After);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnClose.setBounds(316, 216, 89, 29);
		getContentPane().add(btnClose);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAfterStat();
			}
		});
		btnRedo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRedo.setBounds(217, 216, 89, 29);
		getContentPane().add(btnRedo);
		
		JLabel lblIndicator = new JLabel(">");
		lblIndicator.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicator.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		lblIndicator.setBounds(192, 95, 30, 40);
		getContentPane().add(lblIndicator);
		
		JLabel lblBefore = new JLabel("Before");
		lblBefore.setHorizontalAlignment(SwingConstants.CENTER);
		lblBefore.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblBefore.setBounds(61, 7, 83, 29);
		getContentPane().add(lblBefore);
		
		JLabel lblAfter = new JLabel("After");
		lblAfter.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAfter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAfter.setBounds(274, 7, 83, 29);
		getContentPane().add(lblAfter);
		
		lblGuaranteedRolls = new JLabel("Guaranteed Rolls: " + this.reshapeConfig.guaranteedRollLimit());
		lblGuaranteedRolls.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblGuaranteedRolls.setBounds(10, 231, 125, 14);
		getContentPane().add(lblGuaranteedRolls);
		
		displayBeforeStat();
		displayAfterStat();
		applyHighlightToSelectedSlot();
	}
	
	private void displayBeforeStat() {
		lblSlot1Before.setText(artifactStat.getSubStatAt(0).getSubStat());
		lblSlot2Before.setText(artifactStat.getSubStatAt(1).getSubStat());
		lblSlot3Before.setText(artifactStat.getSubStatAt(2).getSubStat());
		lblSlot4Before.setText(artifactStat.getSubStatAt(3).getSubStat());
	}
	
	private void displayAfterStat() {
		try {
			var subStat1 = artifactStat.getSubStatAt(0);
			var subStat2 = artifactStat.getSubStatAt(1);
			var subStat3 = artifactStat.getSubStatAt(2);
			var subStat4 = artifactStat.getSubStatAt(3);
			
			// process the stat w/ slot selected and no. of guaranteed rolls
			ArtifactStat reshapedArtifactStat = new ArtifactStat(
					artifactStat.getArtifactPiece(),
					artifactStat.getMainAttribute(),
					new ArtifactSubStat(subStat1.getAttributeName(), subStat1.getInitialAttributeValue()), 
					new ArtifactSubStat(subStat2.getAttributeName(), subStat2.getInitialAttributeValue()),
					new ArtifactSubStat(subStat3.getAttributeName(), subStat3.getInitialAttributeValue()), 
					new ArtifactSubStat(subStat4.getAttributeName(), subStat4.getInitialAttributeValue())
			);
			
			reshapedArtifactStat.setMaxUpgrade(artifactStat.getMaxUpgrade());
			reshapedArtifactStat.setReshapeConfig(reshapeConfig);
			
			// upgrade the value based on the max upgrade
			for (int i = 0; i < reshapedArtifactStat.getMaxUpgrade(); i++) {
				reshapedArtifactStat.upgradeSubStatValue();
			}
			
			// display the processed stat
			lblSlot1After.setText(reshapedArtifactStat.getSubStatAt(0).getSubStat());
			lblSlot2After.setText(reshapedArtifactStat.getSubStatAt(1).getSubStat());
			lblSlot3After.setText(reshapedArtifactStat.getSubStatAt(2).getSubStat());
			lblSlot4After.setText(reshapedArtifactStat.getSubStatAt(3).getSubStat());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), "Something went wrong!");
			e.printStackTrace();
		}
	}
	
	private void applyHighlightToSelectedSlot() {
		if (reshapeConfig != null) {
			JLabel[] beforeStatLabels = { lblSlot1Before, lblSlot2Before, lblSlot3Before, lblSlot4Before };
			JLabel[] afterStatLabels = { lblSlot1After, lblSlot2After, lblSlot3After, lblSlot4After };
			
			for (int index = 0; index < 4; index++) {
				var subStat = artifactStat.getSubStatAt(index);
				
	            if (reshapeConfig.subStatUpgradeCounts().containsKey(subStat.getAttributeName())) {
	            	highlightLabel(beforeStatLabels[index]);
	            	highlightLabel(afterStatLabels[index]);
				}
			}
		}
	}
	
	private void highlightLabel(JLabel label) {
		label.setBackground(new Color(249, 249, 92));
		label.setOpaque(true);
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
