import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.SwingConstants;
// 수정 
// 수정 2
public class GUI_Profile extends JFrame {
	
	private DataOutputStream dos;
	private DataInputStream dis;
	
	JLabel textPane;
	JLabel textPane_1;
	JLabel textPane_1_1;
	JLabel textPane_1_2;
	JLabel textPane_1_3;
	JLabel memberimage;
	ImageIcon image = null;

	private JPanel contentPane;


	public GUI_Profile(String id, DataOutputStream dos, DataInputStream dis) {
		this.dos = dos;
		this.dis = dis;
		setTitle("Profile");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 463);
		profileview(id);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 15));
		lblNewLabel.setBounds(120, 190, 57, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 15));
		lblNewLabel_1.setBounds(120, 220, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		lblNewLabel_2.setFont(new Font("���� ���", Font.BOLD, 15));
		lblNewLabel_2.setBounds(120, 250, 69, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_3.setFont(new Font("���� ���", Font.BOLD, 15));
		lblNewLabel_3.setBounds(120, 280, 69, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC0C1\uD0DC\uBA54\uC138\uC9C0");
		lblNewLabel_4.setFont(new Font("���� ���", Font.BOLD, 15));
		lblNewLabel_4.setBounds(120, 310, 83, 15);
		contentPane.add(lblNewLabel_4);
		
		textPane = new JLabel();
		textPane.setBounds(203, 190, 120, 21);
		contentPane.add(textPane);
		
		textPane_1 = new JLabel();
		textPane_1.setBounds(203, 220, 120, 21);
		contentPane.add(textPane_1);
		
		textPane_1_1 = new JLabel();
		textPane_1_1.setBounds(203, 250, 120, 21);
		contentPane.add(textPane_1_1);
		
		textPane_1_2 = new JLabel();
		textPane_1_2.setBounds(203, 280, 120, 21);
		contentPane.add(textPane_1_2);
		
		textPane_1_3 = new JLabel();
		textPane_1_3.setBounds(203, 310, 120, 107);
		contentPane.add(textPane_1_3);
		
		
		
		
//		JLabel lblNewLabel_5 = new JLabel("\uD68C\uC6D0 \uD504\uB85C\uD544111"); // 아이콘
//		lblNewLabel_5.setForeground(Color.BLACK);
//		lblNewLabel_5.setBackground(Color.WHITE);
//		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_5.setFont(new Font("���� ���", Font.BOLD, 25));
//		lblNewLabel_5.setBounds(120, 36, 203, 78);
//		contentPane.add(lblNewLabel_5);
		setVisible(true);
	}
	
	public void profileview(String memberid) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					dos.writeInt(Command.PROFILEVIEW);
					dos.writeUTF(memberid); 
					String name = null;
					String tell = null;
					String birthdate = null;
					String status = null;
					String icon = null;
					
					name = dis.readUTF(); 
					tell = dis.readUTF(); 
					birthdate = dis.readUTF(); 
					status = dis.readUTF(); 
					icon = dis.readUTF();
					
					textPane.setText(memberid);
					textPane_1.setText(name); 
					textPane_1_1.setText(tell);
					textPane_1_2.setText(birthdate); 
					textPane_1_3.setText(status);
					image = new ImageIcon(icon);
					Image image1 = image.getImage();
					Image changedimg = image1.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
				    ImageIcon chandedicon = new ImageIcon(changedimg);
					memberimage = new JLabel(chandedicon, JLabel.CENTER);
					memberimage.setBounds(175, 50, chandedicon.getIconWidth(), chandedicon.getIconHeight());
					memberimage.setVerticalTextPosition(JLabel.CENTER);
					memberimage.setHorizontalTextPosition(JLabel.RIGHT);
					contentPane.add(memberimage);

					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}); thread.start();
	}
}
