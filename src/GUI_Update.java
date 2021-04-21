import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.MaskFormatter;

public class GUI_Update extends JFrame {

	private JPanel contentPane;
	private JPasswordField pf_pw;
	private JPasswordField pf_pwcheck;
	private JTextField tf_name;
	private JTextField tf_birth;
	private JTextField tf_tell1;
	private JTextField tf_tell2;
	private JTextField tf_tell3;
	private JLabel lbl_icon;
	private DataOutputStream dos; 
	private DataInputStream dis; 
	private String id;
	private JTextArea ta_message;
	private String folderPath = "";
	

	public GUI_Update(DataOutputStream dos, DataInputStream dis, String id) {
		this.dos = dos;
		this.dis = dis;
		this.id = id;
		setResizable(false);
		setTitle("회원 정보 수정");
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // 프레임 아이콘
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		setBounds(100, 100, 530, 440);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_pw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lbl_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pw.setBounds(12, 20, 85, 36);
		contentPane.add(lbl_pw);
		
		JLabel lbl_pwcheck = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC7AC\uD655\uC778");
		lbl_pwcheck.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pwcheck.setBounds(0, 80, 97, 36);
		contentPane.add(lbl_pwcheck);
		
		pf_pw = new JPasswordField();
		pf_pw.setFont(new Font("Gulim", Font.PLAIN, 20));
		pf_pw.setBounds(109, 20, 262, 40);
		contentPane.add(pf_pw);
		
		pf_pwcheck = new JPasswordField();
		pf_pwcheck.setFont(new Font("Gulim", Font.PLAIN, 20));
		pf_pwcheck.setBounds(109, 80, 262, 40);
		contentPane.add(pf_pwcheck);
		
		JLabel lbl_name = new JLabel("\uC774\uB984");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setBounds(12, 150, 85, 36);
		contentPane.add(lbl_name);
		
		JLabel lbl_birth = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		lbl_birth.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_birth.setBounds(253, 150, 58, 36);
		contentPane.add(lbl_birth);
		
		JLabel lbl_tell = new JLabel("\uC804\uD654\uBC88\uD638");
		lbl_tell.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tell.setBounds(12, 210, 85, 36);
		contentPane.add(lbl_tell);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("-");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setBounds(178, 210, 30, 36);
		contentPane.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("-");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_1.setBounds(276, 210, 30, 36);
		contentPane.add(lblNewLabel_2_2_1_1);
		
		JButton btn_Ok = new JButton("\uC218\uC815");
		btn_Ok.setBackground(SystemColor.activeCaption);
		btn_Ok.setFont(new Font("Gulim", Font.PLAIN, 15));
		btn_Ok.setBounds(415, 349, 97, 40);
		btn_Ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dos.writeInt(Command.PROFILE_UPDATE);
					dos.writeUTF(id);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						if (pf_pw.getText().equals(pf_pwcheck.getText())) {
							String pw = pf_pw.getText();
							String name = tf_name.getText();
							String birth = tf_birth.getText();
							String tell = tf_tell1.getText() + tf_tell2.getText() + tf_tell3.getText();
							String status = ta_message.getText();
							try {
								dos.writeUTF(pw);
								dos.writeUTF(name);
								dos.writeUTF(birth);
								dos.writeUTF(tell);
								dos.writeUTF(status);
								dos.writeUTF(folderPath);
								int x = dis.readInt();
								if (x ==1) {
									JOptionPane.showMessageDialog(null, "회원정보가 수정 되었습니다.");
									dispose();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다. 확인해주세요");
						}
					}
				});
				t.start();
			}
		});
		contentPane.add(btn_Ok);
		



		
		tf_name = new JTextField();
		tf_name.setColumns(10);
		tf_name.setBounds(109, 149, 121, 40);
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
		tf_birth.setBounds(319, 150, 171, 40);
		contentPane.add(tf_birth);
		
		try {
			MaskFormatter  format_tell1 = new MaskFormatter("###");
			tf_tell1 = new JFormattedTextField(format_tell1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tf_tell1.setColumns(10);
		tf_tell1.setBounds(109, 210, 70, 40);
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
		tf_tell2.setBounds(206, 210, 70, 40);
		contentPane.add(tf_tell2);
		
		
		tf_tell3 = new JFormattedTextField(format_tell2);
		tf_tell3.setColumns(10);
		tf_tell3.setBounds(306, 210, 70, 40);
		contentPane.add(tf_tell3);
		
		ta_message = new JTextArea();
		ta_message.setBounds(109, 256, 294, 135);
		contentPane.add(ta_message);
		
		JLabel lbl_Message = new JLabel("\uC0C1\uD0DC \uBA54\uC138\uC9C0");
		lbl_Message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Message.setBounds(12, 316, 85, 36);
		contentPane.add(lbl_Message);
		
		lbl_icon = new JLabel();
		lbl_icon.setBounds(383, 20, 108, 96);
		contentPane.add(lbl_icon);
		
		JButton btn_IconUpdate = new JButton("\uD504\uB85C\uD544 \uBCC0\uACBD"); //프로필 변경 버튼
		btn_IconUpdate.setFont(new Font("Gulim", Font.PLAIN, 12));
		btn_IconUpdate.setBounds(383, 117, 107, 23);
		btn_IconUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				folderPath = "";
		        
		        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
		        chooser.setCurrentDirectory(new File("/")); // 현재 사용 디렉토리를 지정
		        chooser.setAcceptAllFileFilterUsed(true);   // Fileter 모든 파일 적용 
		        chooser.setDialogTitle("아이콘 파일 검색"); // 창의 제목
		        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드
		        
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "gif"); // filter 확장자 추가
		        chooser.setFileFilter(filter); // 파일 필터를 추가
		        
		        int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈
		        
		        if(returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭 
		            folderPath = chooser.getSelectedFile().toString();
		        }else if(returnVal == JFileChooser.CANCEL_OPTION){ // 취소를 클릭
		            folderPath = "";
		        }
		       ImageIcon icon = new ImageIcon(folderPath);
		       Image image = icon.getImage();
		       Image changedimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
		       ImageIcon chandedicon = new ImageIcon(changedimg);
		       lbl_icon.setIcon(chandedicon);
		       System.out.println(folderPath);
			}
		});
		
		// 새로운 창이 켜질때 정보를 읽어와 텍스트에 넣음
		String name = null;
		String birth = null;
		String tell = null;
		String status = null;
		String icon = null;
		try {
			dos.writeInt(Command.PROFILE_lOAD);
			dos.writeUTF(id);
			name = dis.readUTF();
			birth = dis.readUTF();
			tell = dis.readUTF();
			int check = dis.readInt();
			if (check == 1) {
				status = dis.readUTF();
				icon = dis.readUTF();
				ta_message.setText(status);
				ImageIcon icon2 = new ImageIcon(icon);
				lbl_icon.setIcon(icon2);
			} else if (check == 2) {
				status = dis.readUTF();
				ta_message.setText(status);
			} else if (check == 3) {
				icon = dis.readUTF();
				ImageIcon icon2 = new ImageIcon(icon);
				lbl_icon.setIcon(icon2);
				folderPath = icon;
			} 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		tf_name.setText(name); 
		tf_birth.setText(birth);
		tf_tell1.setText(tell.substring(0, 3));
		tf_tell2.setText(tell.substring(3, 7));
		tf_tell3.setText(tell.substring(7, 10));
		
		contentPane.add(btn_IconUpdate);
		setVisible(true);
	}
}
