import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;

public class ReshapeConfigDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JCheckBox chkSlot1;
	private JCheckBox chkSlot2;
	private JCheckBox chkSlot3;
	private JCheckBox chkSlot4;
	
	private static String title = "Reshape Configuration";
	
	private ArtifactStat artifactStat;
	private JComboBox<Integer> cbGuaranteedRolls;

	/**
	 * Create the dialog.
	 */
	public ReshapeConfigDialog(Frame owner, ArtifactStat artifactStat) {
		super(owner, title, true);
		
		this.artifactStat = artifactStat;
		
		setLookAndFeel();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(305, 300);
		setLocationRelativeTo(owner);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ArtifactSimulator.class.getResource("/assets/Amber Icon.jpg")));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		chkSlot1 = new JCheckBox(this.artifactStat.getSubStatAt(0).getSubStat());
		chkSlot1.setBackground(new Color(204, 204, 204));
		chkSlot1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		chkSlot1.setBounds(58, 110, 180, 23);
		attachCheckboxListener(chkSlot1);
		contentPanel.add(chkSlot1);
		
		chkSlot2 = new JCheckBox(this.artifactStat.getSubStatAt(1).getSubStat());
		chkSlot2.setBackground(new Color(204, 204, 204));
		chkSlot2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		chkSlot2.setBounds(58, 136, 180, 23);
		attachCheckboxListener(chkSlot2);
		contentPanel.add(chkSlot2);
		
		chkSlot3 = new JCheckBox(this.artifactStat.getSubStatAt(2).getSubStat());
		chkSlot3.setBackground(new Color(204, 204, 204));
		chkSlot3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		chkSlot3.setBounds(58, 162, 180, 23);
		attachCheckboxListener(chkSlot3);
		contentPanel.add(chkSlot3);
		
		chkSlot4 = new JCheckBox(this.artifactStat.getSubStatAt(3).getSubStat());
		chkSlot4.setBackground(new Color(204, 204, 204));
		chkSlot4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		chkSlot4.setBounds(58, 188, 180, 23);
		attachCheckboxListener(chkSlot4);
		contentPanel.add(chkSlot4);
		
		cbGuaranteedRolls = new JComboBox<>(new DefaultComboBoxModel<>(new Integer[] { 2, 3, 4 }));
		cbGuaranteedRolls.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cbGuaranteedRolls.setSelectedIndex(0);
		cbGuaranteedRolls.setBounds(130, 11, 50, 23);
		contentPanel.add(cbGuaranteedRolls);
		
		JLabel lblGuaranteedRolls = new JLabel("Guaranteed Rolls");
		lblGuaranteedRolls.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblGuaranteedRolls.setBounds(20, 11, 110, 23);
		contentPanel.add(lblGuaranteedRolls);
		
		JTextPane txtpnInstruction = new JTextPane();
		txtpnInstruction.setFocusable(false);
		txtpnInstruction.setEditable(false);
		txtpnInstruction.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtpnInstruction.setText("Select 2 sub-stats that will receive guaranteed enhancements.");
		txtpnInstruction.setBounds(20, 45, 248, 48);
		contentPanel.add(txtpnInstruction);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnReshape = new JButton("Reshape");
				btnReshape.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				btnReshape.addActionListener(new ActionListener() {		
					public void actionPerformed(ActionEvent e) {
						List<Integer> selectedSlots = getCheckedIndexes();
						
						if (selectedSlots.size() == 2) {
							// using stream(), combine the selected index along with its attribute name
							// and store it as map
							Map<String, Integer> slotMap = selectedSlots.stream()
								    .collect(Collectors.toMap(
								        i -> ReshapeConfigDialog.this.artifactStat.getSubStatAt(i).getAttributeName(), // key: attribute name
								        i -> 0                                                // value: upgrade counter
								    ));
							
							ReshapeResultDialog reshapeResult = new ReshapeResultDialog(
									owner,
									ReshapeConfigDialog.this.artifactStat,
									new ReshapeConfig(slotMap, (int) cbGuaranteedRolls.getSelectedItem())
							);
							
							dispose();
							
							reshapeResult.setVisible(true);		
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Select exactly 2 slots!");
						}
					}
				});
				btnReshape.setActionCommand("OK");
				buttonPane.add(btnReshape);
				getRootPane().setDefaultButton(btnReshape);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
	
	private List<Integer> getCheckedIndexes() {
		List<Integer> checkedIndexes = new ArrayList<>();
		
		if (chkSlot1.isSelected()) checkedIndexes.add(0);
		if (chkSlot2.isSelected()) checkedIndexes.add(1);
		if (chkSlot3.isSelected()) checkedIndexes.add(2);
		if (chkSlot4.isSelected()) checkedIndexes.add(3);
		
		return checkedIndexes;
	}
	
	private void updateCheckboxStates() {
		int selectedCount = getCheckedIndexes().size();
		boolean shouldDisable = selectedCount >= 2;

		JCheckBox[] checkboxes = { chkSlot1, chkSlot2, chkSlot3, chkSlot4 };
		
		for (JCheckBox checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.setEnabled(!shouldDisable);
			}
		}
	}
	
	private void attachCheckboxListener(JCheckBox checkbox) {
		checkbox.addItemListener(e -> updateCheckboxStates());
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
