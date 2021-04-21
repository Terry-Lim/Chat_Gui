import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class GUI_SignUp extends JFrame {

	private JPanel contentPane;
	private JPasswordField tf_pw;
	private JPasswordField tf_pwcheck;
	private JTextField tf_id;
	private JTextField tf_name;
	private JTextField tf_birth;
	private JTextField tf_tell1;
	private JTextField tf_tell2;
	private JTextField tf_tell3;
	private DataOutputStream dos;
	private DataInputStream dis;
	private boolean isOkIdchek;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GUI_SignUp(DataOutputStream dos, DataInputStream dis, PrintWriter pw, BufferedReader br) {
		this.dos = dos;
		this.dis = dis;
		setResizable(false);
		setTitle("회원가입");
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // ������ ������
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		setBounds(100, 100, 530, 440);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_checkid = new JButton("\uC911\uBCF5\uD655\uC778");
		btn_checkid.setBackground(SystemColor.activeCaption);
		btn_checkid.setFont(new Font("Gulim", Font.PLAIN, 15));
		btn_checkid.setBounds(394, 35, 97, 40);
		btn_checkid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							dos.writeInt(Command.SIGNUP_IDCHECK);
							dos.writeUTF(tf_id.getText());
							int result = dis.readInt();
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "가입 가능한 아이디입니다.");
								isOkIdchek = true;
								tf_id.setEnabled(false);
							} else if (result == 2) {
								JOptionPane.showMessageDialog(null, "이미 가입된 아이디입니다.");
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				t.start();
			}
		});
		contentPane.add(btn_checkid);
		
		JLabel lbl_id = new JLabel("\uC544\uC774\uB514");
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setBounds(12, 35, 85, 36);
		contentPane.add(lbl_id);
		
		JLabel lbl_pw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lbl_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pw.setBounds(12, 100, 85, 36);
		contentPane.add(lbl_pw);
		
		JLabel lbl_pwcheck = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC7AC\uD655\uC778");
		lbl_pwcheck.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pwcheck.setBounds(12, 165, 97, 36);
		contentPane.add(lbl_pwcheck);
		
		tf_pw = new JPasswordField();
		tf_pw.setFont(new Font("Gulim", Font.PLAIN, 20));
		tf_pw.setBounds(120, 100, 262, 40);
		contentPane.add(tf_pw);
		
		tf_pwcheck = new JPasswordField();
		tf_pwcheck.setFont(new Font("Gulim", Font.PLAIN, 20));
		tf_pwcheck.setBounds(120, 165, 262, 40);
		contentPane.add(tf_pwcheck);
		
		JLabel lbl_name = new JLabel("\uC774\uB984");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setBounds(12, 225, 85, 36);
		contentPane.add(lbl_name);
		
		JLabel lbl_birth = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		lbl_birth.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_birth.setBounds(253, 225, 58, 36);
		contentPane.add(lbl_birth);
		
		JLabel lbl_tell = new JLabel("\uC804\uD654\uBC88\uD638");
		lbl_tell.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tell.setBounds(12, 290, 85, 36);
		contentPane.add(lbl_tell);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("-");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setBounds(190, 291, 30, 36);
		contentPane.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("-");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_1.setBounds(289, 291, 30, 36);
		contentPane.add(lblNewLabel_2_2_1_1);
		
		JButton btn_Ok = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btn_Ok.setBackground(SystemColor.activeCaption);
		btn_Ok.setFont(new Font("Gulim", Font.PLAIN, 15));
		btn_Ok.setBounds(394, 338, 97, 40);
		btn_Ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isOkIdchek) {
					if (tf_pw.getText().equals(tf_pwcheck.getText())) {
						Thread t = new Thread(new Runnable() {
							@Override
							public void run() {
								try {
									dos.writeInt(Command.SIGNUP_OK);
									dos.writeUTF(tf_id.getText());
									dos.writeUTF(tf_pw.getText());
									dos.writeUTF(tf_name.getText());
									dos.writeUTF(tf_birth.getText());
									dos.writeUTF(tf_tell1.getText());
									dos.writeUTF(tf_tell2.getText());
									dos.writeUTF(tf_tell3.getText());
									new GUI_Login(dos, dis, pw, br); 
									dispose();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
						t.start();
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요");
				}
			}
		});
		contentPane.add(btn_Ok);
		
		tf_id = new JTextField();
		tf_id.setBounds(120, 36, 262, 40);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		



		
		tf_name = new JTextField();
		tf_name.setColumns(10);
		tf_name.setBounds(120, 225, 121, 40);
		contentPane.add(tf_name);
		
		MaskFormatter format_birth;
		try {
			format_birth = new MaskFormatter("####-##-##");
			tf_birth = new JFormattedTextField(format_birth);
			tf_birth.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tf_birth.setColumns(10);
		tf_birth.setBounds(319, 225, 171, 40);
		contentPane.add(tf_birth);
		
		try {
			MaskFormatter  format_tell1 = new MaskFormatter("###");
			tf_tell1 = new JFormattedTextField(format_tell1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tf_tell1.setColumns(10);
		tf_tell1.setBounds(120, 290, 70, 40);
		contentPane.add(tf_tell1);
		MaskFormatter  format_tell2 = null;
		try {
			format_tell2 = new MaskFormatter("####");
			tf_tell2 = new JFormattedTextField(format_tell2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tf_tell2.setColumns(10);
		tf_tell2.setBounds(217, 290, 70, 40);
		contentPane.add(tf_tell2);
		
		
		tf_tell3 = new JFormattedTextField(format_tell2);
		tf_tell3.setColumns(10);
		tf_tell3.setBounds(319, 290, 70, 40);
		contentPane.add(tf_tell3);
		setVisible(true);
	}
}
