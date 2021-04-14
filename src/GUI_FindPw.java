

import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.attribute.DosFileAttributes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class GUI_FindPw extends JFrame {
	
	JLabel logo = new JLabel(new ImageIcon("./image/icon_find.png"));
	String name = "홍길동";
	String id = "abc";
	String phoneNumber = "010-0000-0000";
	boolean ischeckname;
	boolean ischeckid;
	boolean ischecktell;
	
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
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public GUI_FindPw(DataOutputStream dos, DataInputStream dis) {
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // 프레임 아이콘
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		getContentPane().setLayout(null);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		// 가로위치, 세로위치, 가로길이, 세로길이
		JTextField tf_idCheck = new JTextField();
		JTextField tf_nameCheck = new JTextField();
		JTextField tf_phoneNumCheck = new JTextField();
		JButton btn_idCheck = new JButton("확인");
		JButton btn_nameCheck = new JButton("확인");
		JButton btn_phoneNumCheck = new JButton("확인");
		JButton btn_find = new JButton("비밀번호 찾기");
		JLabel lbl_id = new JLabel("ID");
		JLabel lbl_name = new JLabel("이름");
		JLabel lbl_phoneNumber = new JLabel("전화번호");
		
		tf_idCheck.setBounds(30, 330, 300, 30);
		tf_nameCheck.setBounds(30, 380, 300, 30);
		tf_phoneNumCheck.setBounds(30, 430, 300, 30);
		btn_idCheck.setBounds(370, 330, 80, 30);
		btn_nameCheck.setBounds(370, 380, 80, 30);
		btn_phoneNumCheck.setBounds(370, 430, 80, 30);
		lbl_id.setBounds(30, 315, 300, 15);
		lbl_name.setBounds(30, 365, 300, 15);
		lbl_phoneNumber.setBounds(30, 415, 300, 15);
		btn_find.setBounds(350, 500, 120, 30);
		logo.setBounds(70, 0, 350, 350);
		
		add(logo);
		add(tf_idCheck);
		add(tf_nameCheck);
		add(tf_phoneNumCheck);
		add(btn_idCheck);
		add(btn_nameCheck);
		add(btn_phoneNumCheck);
		add(btn_find);
		add(lbl_id);
		add(lbl_name);
		add(lbl_phoneNumber);
		
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ischeckid && ischeckname && ischecktell) {
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								dos.writeInt(Command.PWFIND_PWFIND);								
								dos.writeUTF(tf_idCheck.getText());
								dos.writeUTF(tf_nameCheck.getText());
								dos.writeUTF(tf_phoneNumCheck.getText());
								String pw = dis.readUTF();
								JOptionPane.showMessageDialog(null, "비밀번호는 " + pw + "입니다.");
								dispose();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					t.start();
				} else if (ischeckid && ischeckname) {
					JOptionPane.showMessageDialog(null, "전화번호 확인을 해주세요");
				} else if (ischeckid && ischecktell) {
					JOptionPane.showMessageDialog(null, "이름 확인을 해주세요");
				} else if (ischeckname && ischecktell){
					JOptionPane.showMessageDialog(null, "아이디를 확인을 해주세요");
				} else {
					JOptionPane.showMessageDialog(null, "두개의 빈 항목 확인을 해주세요");
				}
				
			}
		});
		
		btn_idCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							dos.writeInt(Command.PWFIND_IDCHECK);
							dos.writeUTF(tf_idCheck.getText());
							int x = dis.readInt();
							if (x == 1) {
								JOptionPane.showMessageDialog(null, "아이디 확인이 되었습니다.");
								ischeckid = true;
								tf_idCheck.setEditable(false);
							} else {
								JOptionPane.showMessageDialog(null, "확인 되는 아이디이 없습니다..");
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
		
		btn_nameCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ischeckid == true) {
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								dos.writeInt(Command.PWFIND_NAMECHECK);
								dos.writeUTF(tf_idCheck.getText());
								dos.writeUTF(tf_nameCheck.getText());
								int x = dis.readInt();
								if (x == 1) {
									JOptionPane.showMessageDialog(null, "이름 확인이 되었습니다.");
									ischeckname = true;
									tf_nameCheck.setEditable(false);
								} else {
									JOptionPane.showMessageDialog(null, "확인되는 이름이 없습니다.");
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					t.start();
				} else {
					JOptionPane.showMessageDialog(null, "아이디 확인을 먼저 해주세요");
				}
			}
		});
		btn_phoneNumCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ischeckid == true && ischeckname == true) {
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								dos.writeInt(Command.PWFIND_TELLCHECK);
								dos.writeUTF(tf_idCheck.getText());
								dos.writeUTF(tf_nameCheck.getText());
								dos.writeUTF(tf_phoneNumCheck.getText());
								int x = dis.readInt();
								if (x == 1) {
									JOptionPane.showMessageDialog(null, "전화번호 확인이 되었습니다.");
									ischecktell = true;
									tf_phoneNumCheck.setEditable(false);
								} else {
									JOptionPane.showMessageDialog(null, "확인되는 번화번호가 없습니다.");
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					t.start();
				} else {
					JOptionPane.showMessageDialog(null, "아이디와 이름 확인을 먼저 해주세요");
				}
			}
		});
		
		setTitle("비밀번호찾기");
		setSize(500, 640);
		setVisible(true);
	}
	
}
