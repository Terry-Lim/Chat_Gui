import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class GUI_Profile extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Profile frame = new GUI_Profile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_Profile() {
		setTitle("Profile");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 463);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel.setBounds(120, 124, 57, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel_1.setBounds(120, 163, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel_2.setBounds(120, 201, 69, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel_3.setBounds(120, 242, 69, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC0C1\uD0DC\uBA54\uC138\uC9C0");
		lblNewLabel_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel_4.setBounds(120, 284, 83, 15);
		contentPane.add(lblNewLabel_4);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(203, 124, 120, 21);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(203, 163, 120, 21);
		contentPane.add(textPane_1);
		
		JTextPane textPane_1_1 = new JTextPane();
		textPane_1_1.setBounds(203, 201, 120, 21);
		contentPane.add(textPane_1_1);
		
		JTextPane textPane_1_2 = new JTextPane();
		textPane_1_2.setBounds(203, 242, 120, 21);
		contentPane.add(textPane_1_2);
		
		JTextPane textPane_1_3 = new JTextPane();
		textPane_1_3.setBounds(203, 282, 120, 107);
		contentPane.add(textPane_1_3);
		
		JLabel lblNewLabel_5 = new JLabel("\uD68C\uC6D0 \uD504\uB85C\uD544");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		lblNewLabel_5.setBounds(120, 36, 203, 78);
		contentPane.add(lblNewLabel_5);
	}
}
