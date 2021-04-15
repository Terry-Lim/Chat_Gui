import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI_ChannelSelection extends JFrame {

	private JPanel contentPane;
	private DataOutputStream dos;
	private DataInputStream dis;
	private String id;
	private String[] datas;
	private JList<String> list;
	
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
		 			for (int i = 0; i < roomnum; i++) {
		 				dos.writeInt(Command.GETSELETEDROOM);
						dos.writeInt(i);
						roomname = dis.readUTF();
						max = dis.readInt();
						num = dis.readInt();
						roomtxt = roomname + "                                                                                           " + num + "/" + max;
						int length = roomname.length();
						StringBuilder sb = new StringBuilder();
						sb.append(roomtxt);
						for (int j = 10; j > length; j --) {
							sb.insert(11, "   ");
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}); t.start();
		
		
	}
	public void FavoriteChannel() {
		
		setBounds(100, 100, 472, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
	}
	public void	FindChannel() {
		
		setBounds(100, 100, 472, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
	}
	public void MakingChannel() {
		
		setBounds(100, 100, 472, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
	}
	
	
	
	
	public GUI_ChannelSelection(DataOutputStream dos, DataInputStream dis, String id) {
		this.dos = dos;
		this.dis = dis;
		this.id = id;
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // 프레임 아이콘
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		
		setBounds(100, 100, 472, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("채널 홈(새로고침)");
		btnNewButton.setBounds(6, 6, 117, 29);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChannelHomeReset();
			}
		});
		
		JButton btnNewButton_1 = new JButton("즐겨찾기");
		btnNewButton_1.setBounds(118, 6, 117, 29);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FavoriteChannel();
			}
		});
		
		JButton btnNewButton_2 = new JButton("방 찾기");
		btnNewButton_2.setBounds(233, 6, 117, 29);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String roomname = JOptionPane.showInputDialog("방 찾기", "방 제목을 입력하세요");
				
			
			}
		});
		
		JButton btnNewButton_3 = new JButton("방 만들기");
		btnNewButton_3.setBounds(344, 6, 117, 29);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new GUI_MakeRoom();
			}
		});
		
		JLabel lblNewLabel = new JLabel("방 이름");
		lblNewLabel.setBounds(26, 47, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("현재인원/ 최대인원"); 
		lblNewLabel_1.setBounds(336, 34, 107, 16);
		contentPane.add(lblNewLabel_1);
		
		datas = new String[] {"Char1","Char2","Char3","Char4","Char5","Char6","Char5","Char5","Char5","Char5","Char5","Char5","Char5","Char5","Char5"};
		list = new JList<String>();
		list.setListData(datas);  //리스트 객체의 설정과 항목들 설정

		list.setSelectionBackground(Color.YELLOW);
		list.setSelectionForeground(Color.RED);
		list.addMouseListener(new MouseListener() {
			
			private int count;

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
				int roomNum = list.getSelectedIndex();
				
				try {
					dos.writeInt(Command.ENTERROOM);
					dos.writeInt(roomNum);
					dos.writeUTF(id);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				new GUI_ChatRoom(dos, dis, roomNum, id);
			}
		});
		JScrollPane scrollPane = new JScrollPane(list);	
			
		

		scrollPane.setBounds(26, 75, 417, 214);
		contentPane.add(scrollPane);
		
		
		JLabel lblNewLabel_2 = new JLabel("New label"); //수정 현재인원/ 최대인원
		lblNewLabel_2.setBounds(336, 47, 107, 16);
		contentPane.add(lblNewLabel_2);
		ChannelHomeReset();
		setVisible(true);
	
	}
}
