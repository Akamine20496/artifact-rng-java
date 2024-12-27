import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class InstructionDialog extends JDialog {
    /**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton btnNext;
    private JButton btnBack;
    private JButton btnOk;
    private int messageIndex;
    private int cardPanelCount; 

    // Constructor for the dialog
    private InstructionDialog(Frame owner, String title, String... messages) {
        super(owner, title, true);
        
        setLookAndFeel();
        
        setSize(500, 500);
        setMinimumSize(new Dimension(400, 300)); // Set minimum size
        setLocationRelativeTo(owner);
        setIconImage(Toolkit.getDefaultToolkit().getImage(InstructionDialog.class.getResource("/assets/Amber Icon.jpg")));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        initializeUI(messages);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException 
        		| IllegalAccessException | UnsupportedLookAndFeelException e) {
            		e.printStackTrace();
        }
    }

    private void initializeUI(String... messages) {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add panels to the card layout
        for (int i = 0; i < messages.length; i++) {
            JPanel panel = createPanel(messages[i]);
            cardPanel.add(panel, "Panel " + i);
        }
        
        // store the number of components
        cardPanelCount = cardPanel.getComponentCount();

        // Create buttons
        btnNext = new JButton("Next >");
        btnBack = new JButton("< Back");
        btnOk = new JButton("OK");
        btnOk.setVisible(false);

        // Button sizes
        Dimension buttonSize = new Dimension(80, 28); // Adjust the size as needed
        btnNext.setPreferredSize(buttonSize);
        btnBack.setPreferredSize(buttonSize);
        btnOk.setPreferredSize(buttonSize);
        
        // Button action listeners
        btnNext.addActionListener(this::nextButtonActionPerformed);
        btnBack.addActionListener(this::backButtonActionPerformed);
        btnOk.addActionListener(e -> dispose());

        // Layout setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnBack);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnOk);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        getRootPane().setLayout(new BorderLayout());
        getRootPane().add(cardPanel, BorderLayout.CENTER);
        getRootPane().add(buttonPanel, BorderLayout.SOUTH);

        // Show the first slide
        cardLayout.show(cardPanel, "Panel 0");
        updateButtonVisibility();
    }

    private void nextButtonActionPerformed(ActionEvent e) {
        messageIndex++;
        if (messageIndex < cardPanelCount) {
        	updateButtonVisibility();
        	
        	if (messageIndex != cardPanelCount - 1) {
        		btnNext.requestFocus(); 
        	} else { 
        		btnOk.requestFocus();
        	}
        		
            cardLayout.show(cardPanel, "Panel " + messageIndex);
        }
    }

    private void backButtonActionPerformed(ActionEvent e) {
        if (messageIndex > 0) {
            messageIndex--;
            updateButtonVisibility();
            
            btnBack.requestFocus();
            
            cardLayout.show(cardPanel, "Panel " + messageIndex);
        }
    }

    private void updateButtonVisibility() {
    	btnBack.setVisible(messageIndex > 0);
    	btnNext.setVisible(messageIndex < cardPanelCount - 1);
        btnOk.setVisible(messageIndex == cardPanelCount - 1);
    }
    
    private JPanel createPanel(String htmlContent) {
        JPanel panel = new JPanel(new BorderLayout());
        JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
        editorPane.setFocusable(false);
        editorPane.setEditable(false);
        editorPane.setOpaque(false);
        editorPane.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(new EmptyBorder(10, 10, 0, 10));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public static void showPaginationDialog(Frame owner, String title, String... messages) {
    	InstructionDialog dialog = new InstructionDialog(owner, title, messages);
        dialog.setVisible(true);
    }

    public static void showMessageDialog(Frame owner, String title, String message) {
        showPaginationDialog(owner, title, message);
    }
}
