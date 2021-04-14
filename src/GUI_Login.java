import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Image;

import javax.swing.UIManager;
import java.awt.SystemColor;

public class GUI_Login extends JFrame {
	
	private DataOutputStream dos;
	private DataInputStream dis;
	JLabel imgLbl = new JLabel();
	ImageIcon saTalk = new ImageIcon("./image/satalk.png");

	private JPanel contentPane;
	private JPasswordField pf_pw;
	private JTextField tf_id;

	public GUI_Login(DataOutputStream dos, DataInputStream dis) {
		this.dos = dos;
		this.dis = dis;
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // ������ ������
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		setTitle("SATALK");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 623);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 15));
		lblNewLabel.setBounds(107, 305, 63, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 15));
		lblNewLabel_1.setBounds(107, 347, 63, 21);
		contentPane.add(lblNewLabel_1);
		
		JButton btn_login = new JButton("\uB85C\uADF8\uC778");
		btn_login.setFont(new Font("���� ���", Font.PLAIN, 12));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							dos.writeInt(Command.LOGIN);
							dos.writeUTF(tf_id.getText());
							dos.writeUTF(pf_pw.getText());
							int x = dis.readInt();
							if (x == 1) {
								JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
								new GUI_ChannelSelection(dos, dis, tf_id.getText()); // ȸ������ �Ķ���͸� �־����
								dispose();
							} else if (x == 2) {
								JOptionPane.showMessageDialog(null, "아이디를 확인해주세요.");
							} else {
								JOptionPane.showMessageDialog(null, "존재하지 않는 계정입니다.");
							}
							
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				t.start();
			}
		});
		btn_login.setBounds(94, 390, 268, 40);
		contentPane.add(btn_login);
		
		JButton btn_SignUp = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btn_SignUp.setFont(new Font("���� ���", Font.PLAIN, 12));
		btn_SignUp.setBounds(94, 440, 117, 34);
		btn_SignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						new GUI_SignUp(dos, dis); 						
					}
				});
			}
		});
		contentPane.add(btn_SignUp);
		
		JButton btn_UpdateDelete = new JButton("\uD68C\uC6D0\uC815\uBCF4 \uC218\uC815");
		btn_UpdateDelete.setFont(new Font("���� ���", Font.PLAIN, 12));
		btn_UpdateDelete.setBounds(223, 440, 139, 34);
		btn_UpdateDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_UpdateDelete(dos, dis);
			}
		});
		contentPane.add(btn_UpdateDelete);
		
		JButton btn_FindId = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		btn_FindId.setFont(new Font("���� ���", Font.PLAIN, 12));
		btn_FindId.setBounds(94, 484, 117, 34);
		btn_FindId.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_FindId(dos, dis);
				
			}
		});
		contentPane.add(btn_FindId);
		
		JButton btn_FindPw = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		btn_FindPw.setFont(new Font("���� ���", Font.PLAIN, 12));
		btn_FindPw.setBounds(223, 484, 139, 34);
		btn_FindPw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_FindPw(dos, dis);
			}
		});
		contentPane.add(btn_FindPw);
		
		pf_pw = new JPasswordField();
		pf_pw.setEchoChar('*');
		pf_pw.setBounds(182, 350, 142, 21);
		contentPane.add(pf_pw);
		
		tf_id = new JTextField();
		tf_id.setBounds(182, 308, 142, 21);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		
		imgLbl.setIcon(saTalk);
		imgLbl.setBounds(111, 32, 231, 243);
		getContentPane().add(imgLbl);
		setVisible(true);
	}
}
