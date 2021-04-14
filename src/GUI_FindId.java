

import java.awt.Image;
import java.awt.SystemColor;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

class GUI_FindId extends JFrame {
	JLabel logo = new JLabel(new ImageIcon("./image/icon_find.png"));

	DataOutputStream dos;
	DataInputStream dis;
	boolean ischeckname;
	boolean ischecktell;
	String name;
	String tell;
	
	public static String phonenumbercheck(String src) {
	    if (src == null) {
	      return "";
	    }
	    if (src.length() == 8) {
	      return src.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
	    } else if (src.length() == 12) {
	      return src.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
	    }
	    return src.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
	  }
	
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public GUI_FindId(DataOutputStream dos, DataInputStream dis) {
		this.dos = dos;
		this.dis = dis;
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // 프레임 아이콘
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		getContentPane().setLayout(null);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		// 가로위치, 세로위치, 가로길이, 세로길이
		JTextField tf_nameCheck = new JTextField();
		JTextField tf_phoneNumCheck = new JTextField();
		JButton btn_nameCheck = new JButton("확인1");
		JButton btn_phoneNumCheck = new JButton("확인2");
		JButton btn_find = new JButton("아이디 찾기");
		JLabel lbl_name = new JLabel("이름");
		JLabel lbl_phoneNumber = new JLabel("전화번호");
		
		tf_nameCheck.setBounds(30, 380, 300, 30);
		tf_phoneNumCheck.setBounds(30, 430, 300, 30);
		btn_nameCheck.setBounds(370, 380, 80, 30);
		btn_phoneNumCheck.setBounds(370, 430, 80, 30);
		lbl_name.setBounds(30, 365, 300, 15);
		lbl_phoneNumber.setBounds(30, 415, 300, 15);
		btn_find.setBounds(360, 500, 100, 30);
		logo.setBounds(70, 30, 350, 350);
		
		add(logo);
		add(tf_nameCheck);
		add(tf_phoneNumCheck);
		add(btn_nameCheck);
		add(btn_phoneNumCheck);
		add(btn_find);
		add(lbl_name);
		add(lbl_phoneNumber);
		
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ischeckname && ischecktell) {
					Thread t = new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								dos.writeInt(Command.IDFIND_IDFIND);
								dos.writeUTF(name);
								dos.writeUTF(tell);
								String id = dis.readUTF();
								JOptionPane.showMessageDialog(null, "아이디는 " + id + "입니다.");
								dispose();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					t.start();
				} else if (ischeckname) {
					JOptionPane.showMessageDialog(null, "전화번호 확인을 해주세요");
				} else if (ischecktell) {
					JOptionPane.showMessageDialog(null, "이름 확인을 해주세요");
				} else {
					JOptionPane.showMessageDialog(null, "이름과 전화번호 확인을 해주세요");
				}
				
			}
		});
		
		btn_nameCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							dos.writeInt(Command.IDFIND_NAMECHECK);
							dos.writeUTF(tf_nameCheck.getText());
							int x = dis.readInt();
							if (x == 1) {
								JOptionPane.showMessageDialog(null, "이름 확인이 되었습니다.");
								ischeckname = true;
								name = tf_nameCheck.getText();
								tf_nameCheck.setEditable(false);
							} else {
								JOptionPane.showMessageDialog(null, "확인 되는 이름이 없습니다..");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				t.start();
			}
		});
		
		btn_phoneNumCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ischeckname == true) {
					Thread t = new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								dos.writeInt(Command.IDFIND_TELLCHECK);
								dos.writeUTF(name);
								dos.writeUTF(tf_phoneNumCheck.getText());
								int x = dis.readInt();
								if (x == 1) {
									JOptionPane.showMessageDialog(null, "전화번호 확인이 되었습니다.");
									ischecktell = true;
									tell = tf_phoneNumCheck.getText();
									tf_phoneNumCheck.setEditable(false);
								}
								else {
									JOptionPane.showMessageDialog(null, "확인 되는 전화번호가 없습니다.");
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					t.start();
				} else {
					JOptionPane.showMessageDialog(null, "이름 확인을 먼저 해주세요");
				}
				
			}
		});

		setTitle("아이디찾기");
		setSize(500, 640);
		setVisible(true);
	}
	
}

