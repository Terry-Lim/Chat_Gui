import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class GUI_ChannelSelection extends JFrame {

	private JPanel contentPane;
	private DataOutputStream dos;
	private DataInputStream dis;
	private String id;
	private String[] datas;
	private JList<String> list;
	private PrintWriter pw;
	private BufferedReader br;
	
	public GUI_ChannelSelection(DataOutputStream dos, DataInputStream dis, String id, PrintWriter pw, BufferedReader br) {
		this.dos = dos;
		this.dis = dis;
		this.id = id;
		this.pw = pw;
		this.br = br;
		
		//프레임 아이콘
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // 프레임 아이콘
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		
		setBounds(100, 100, 472, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_reset = new JButton("채널 홈(새로고침)");
		btn_reset.setBounds(6, 6, 117, 29);
		contentPane.add(btn_reset);
		btn_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChannelHomeReset();
			}
		});
		
		JButton btn_bookmark = new JButton("즐겨찾기");
		btn_bookmark.setBounds(118, 6, 117, 29);
		contentPane.add(btn_bookmark);
		btn_bookmark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton btn_findroom = new JButton("방 찾기");
		btn_findroom.setBounds(233, 6, 117, 29);
		contentPane.add(btn_findroom);
		btn_findroom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						String roomname = JOptionPane.showInputDialog("방 찾기", "방 제목을 입력하세요");
						String roomtxt = null;
						try {
							dos.writeInt(Command.FINDROOM);
							dos.writeUTF(roomname);
							int count = dis.readInt();
							if (count == 1) { 
								roomname = dis.readUTF();
								int max = dis.readInt();
								int num = dis.readInt();
								roomtxt = roomname + "                                   " +num + "/" + max;
								StringBuffer sb = new StringBuffer();
								sb.append(roomtxt);
								int n = 100 - roomtxt.getBytes().length; // roomtxt 바이트값
								for (int j = 0; j < n; j++) {
									sb.insert(30, " ");
									if (j == 59 ) {
										sb.insert(30, "  ");
									} else if (j == 57 ) {
										sb.insert(30, "  ");
									} else if (j == 55 ) {
										sb.insert(30, "  ");
									} else if (j == 53 ) {
										sb.insert(30, "  ");
									} else if (j == 51 ) {
										sb.insert(30, "  ");
									} else if (j == 49 ) {
										sb.insert(30, "  ");
									} else if (j == 47 ) {
										sb.insert(30, "  ");
									} else if (j == 45 ) {
										sb.insert(30, "  ");
									} else if (j == 43 ) {
										sb.insert(30, "  ");
									}
								}
								DefaultListModel<String> model = new DefaultListModel<>();
					 			model.addElement(roomtxt);
					 			list.setModel(model);
							} else {
								JOptionPane.showMessageDialog(null, "입력한 방이 없습니다.");
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				t.start();
			}
		});
		
		JButton btn_makeroom = new JButton("방 만들기");
		btn_makeroom.setBounds(344, 6, 117, 29);
		contentPane.add(btn_makeroom);
		btn_makeroom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new GUI_MakeRoom(dos, dis, id, pw, br);
				dispose();
			}
		});
		
		JLabel lbl_roomname = new JLabel("방 이름");
		lbl_roomname.setBounds(26, 47, 61, 16);
		contentPane.add(lbl_roomname);
		
		JLabel lbl_num = new JLabel("현재인원/ 최대인원"); 
		lbl_num.setBounds(330, 47, 120, 16);
		contentPane.add(lbl_num);
		
		datas = new String[] {"Char1","Char2","Char3","Char4","Char5","Char6","Char5","Char5","Char5","Char5","Char5","Char5","Char5","Char5","Char5"};
		list = new JList<String>();
		list.setListData(datas);  //리스트 객체의 설정과 항목들 설정

		list.setSelectionBackground(Color.YELLOW);
		list.setSelectionForeground(Color.RED);
		list.addMouseListener(new MouseListener() {
			
			private int count;

			@Override
			public void mouseReleased(MouseEvent e) {				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
				int roomNum = list.getSelectedIndex();
				String txt =(String) list.getSelectedValue();
				String[] split = txt.split("                ");
				String rn = split[0];
				try {
					dos.writeInt(Command.ENTERROOM);
					dos.writeUTF(rn);
					dos.writeUTF(id);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				new GUI_ChatRoom(dos, dis, rn, id, pw, br);
				dispose();
			}
		});
		JScrollPane scrollPane = new JScrollPane(list);	
		scrollPane.setBounds(26, 75, 417, 214);
		contentPane.add(scrollPane);
		ChannelHomeReset();
		setVisible(true);
	}
	
	public void ChannelHomeReset() {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					dos.writeInt(Command.RESETROOM);
					int roomnum = dis.readInt();
					String roomname = null;
					int max = 0;
					int num = 0;
					String roomtxt = null;
					List <String> roomnamelist = new ArrayList<>();
		 			for (int i = 1; i <= roomnum; i++) {
		 				dos.writeInt(Command.GETSELETEDROOM);
						dos.writeInt(i);
						roomname = dis.readUTF();
						max = dis.readInt();
						num = dis.readInt();
						roomtxt = roomname + "                                   " +num + "/" + max;
						StringBuffer sb = new StringBuffer();
						sb.append(roomtxt);
						int n = 100 - roomtxt.getBytes().length; // roomtxt 바이트값
						for (int j = 0; j < n; j++) {
							sb.insert(30, " ");
							if (j == 59 ) {
								sb.insert(30, "  ");
							} else if (j == 57 ) {
								sb.insert(30, "  ");
							} else if (j == 55 ) {
								sb.insert(30, "  ");
							} else if (j == 53 ) {
								sb.insert(30, "  ");
							} else if (j == 51 ) {
								sb.insert(30, "  ");
							} else if (j == 49 ) {
								sb.insert(30, "  ");
							} else if (j == 47 ) {
								sb.insert(30, "  ");
							} else if (j == 45 ) {
								sb.insert(30, "  ");
							} else if (j == 43 ) {
								sb.insert(30, "  ");
							}
						}
						roomnamelist.add(String.valueOf(sb));
					}
		 			DefaultListModel<String> model = new DefaultListModel<>();
		 			
		 			for (int i =0; i < roomnamelist.size(); i++) {
		 				model.addElement(roomnamelist.get(i));
		 			}
		 			list.setModel(model);
					//String[] datas = {"Char1","Char2","Char3"};
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}); t.start();	
	}
}
