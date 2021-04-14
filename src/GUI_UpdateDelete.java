import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GUI_UpdateDelete extends JFrame {

	private JPanel contentPane;
	private JTextField tf_id;
	private JPasswordField pf_pw;
	private JButton btn_Update;
	private JButton btn_Delete;
	private DataInputStream dis;
	private DataOutputStream dos;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public GUI_UpdateDelete(DataOutputStream dos, DataInputStream dis) {
		this.dos = dos;
		this.dis = dis;
		setResizable(false);
		setTitle("회원정보 수정 및 삭제");
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // ������ ������
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_id = new JLabel("\uC544\uC774\uB514");
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setBounds(12, 28, 85, 36);
		contentPane.add(lbl_id);
		
		tf_id = new JTextField();
		tf_id.setColumns(10);
		tf_id.setBounds(124, 28, 262, 40);
		contentPane.add(tf_id);
		
		JLabel lbl_pw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lbl_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pw.setBounds(12, 98, 85, 36);
		contentPane.add(lbl_pw);
		
		pf_pw = new JPasswordField();
		pf_pw.setFont(new Font("Gulim", Font.PLAIN, 20));
		pf_pw.setBounds(124, 94, 262, 40);
		contentPane.add(pf_pw);
		
		btn_Update = new JButton("\uD68C\uC6D0 \uC815\uBCF4 \uC218\uC815");
		btn_Update.setFont(new Font("Gulim", Font.PLAIN, 15));
		btn_Update.setBackground(SystemColor.activeCaption);
		btn_Update.setBounds(58, 172, 140, 40);
		btn_Update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							dos.writeInt(Command.CLIENT_UPDATE);
							dos.writeUTF(tf_id.getText());
							dos.writeUTF(pf_pw.getText());
							String id = tf_id.getText();
							int x = dis.readInt();
							if (x == 1) {
								new GUI_Update(dos, dis, id);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "잘못 입력하셨습니다.");
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
		contentPane.add(btn_Update);
		
		btn_Delete = new JButton("\uD68C\uC6D0 \uC815\uBCF4 \uC0AD\uC81C");
		btn_Delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							dos.writeInt(Command.CLIENT_DELETE);
							dos.writeUTF(tf_id.getText());
							dos.writeUTF(pf_pw.getText());
							int x = dis.readInt();//1
							if (x == 1) {
								int result = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까 복구 할수 없습니다.", 
										"ȸ�� ����", JOptionPane.YES_NO_OPTION);
								if (result == JOptionPane.YES_OPTION) {
									dos.writeInt(1); //2
									JOptionPane.showMessageDialog(null, "삭제되었습니다.");
								} else {
									dos.writeInt(2);
								}
							} else {
								JOptionPane.showMessageDialog(null, "잘못 입력하셨습니다.");
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
		btn_Delete.setFont(new Font("Gulim", Font.PLAIN, 15));
		btn_Delete.setBackground(SystemColor.activeCaption);
		btn_Delete.setBounds(236, 172, 140, 40);
		contentPane.add(btn_Delete);
		setVisible(true);
	}

}
