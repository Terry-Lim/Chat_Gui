import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class GUI_ChatRoom extends JFrame {

    private JTextArea txtarea = new JTextArea();
    private JScrollPane talkPane1 = new JScrollPane();
    private JTextField txtsend = new JTextField();
    private JButton btn_ok = new JButton();
    private JButton btn_set = new JButton();
    private JButton btn_file = new JButton();
    private JLabel jLabel_tag = new JLabel();
    
    private JLabel jLabel_set = new JLabel();
    private JLabel jLabel_boss = new JLabel();
    private JLabel jLabel_guest = new JLabel();
    private JLabel lblinwon_roomname = new JLabel();
    private JLabel lblinwon_boss = new JLabel();
    private JLabel lblinwon_guest = new JLabel();
    private JButton btn_close = new JButton();
    private JList <String> list = new JList(); 
    private DataOutputStream dos;
    private DataInputStream dis;
    private int roomNumber;
    
    String id;

    public void room_update() {
    	Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					dos.writeInt(Command.ROOMUPDATE);
					dos.writeInt(roomNumber);
					String roomname = dis.readUTF();
					String leader = dis.readUTF();
					int num = dis.readInt();
					int maxnum = dis.readInt();
					lblinwon_roomname.setText(roomname); 
					lblinwon_boss.setText(leader);
					String.valueOf(num);
					String.valueOf(maxnum);
					StringBuilder sb = new StringBuilder();
					sb.append(num);
					sb.append("/");
					sb.append(maxnum);
					lblinwon_guest.setText(String.valueOf(sb));

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); 
        t.start();
    }
    
    GUI_ChatRoom(DataOutputStream dos, DataInputStream dis, int roomNumber, String id) {
    	this.dos = dos;
    	this.dis = dis;
    	this.roomNumber = roomNumber;
    	this.id = id;
    	ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // �봽�젅�엫 �븘�씠肄�
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
    	
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(700, 400));
        this.setTitle("채팅방"); 
        this.setBackground(Color.orange);

        talkPane1.setBounds(new Rectangle(15, 40, 505, 270)); 
        talkPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        talkPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtsend.setBounds(new Rectangle(15, 320, 380, 25)); // ���솕 �엯�젰李�

        btn_set.setBounds(new Rectangle(15, 10, 100, 25));// 諛� �꽕�젙 踰꾪듉
        btn_set.setText("諛� �꽕�젙");

        btn_ok.setBounds(new Rectangle(400, 320, 60, 25));// 蹂대궡湲� 踰꾪듉
        btn_ok.setText("�솗�씤");


        btn_file.setBounds(new Rectangle(470, 320, 50, 25));// 蹂대궡湲� 踰꾪듉
        btn_file.setText("+");
        btn_file.setFont(new Font("",Font.ITALIC,15));
        
        jLabel_boss.setBounds(new Rectangle(390, 10, 75, 20));// 諛⑹옣 �씠由� �씪踰�
        jLabel_boss.setText("諛⑹옣");

        jLabel_tag.setBounds(new Rectangle(200, 10, 75, 20));// 諛� �씠由� �씪踰�
        jLabel_tag.setText("諛⑹씠由�");
        lblinwon_roomname.setBounds(new Rectangle(270, 10, 120, 25));// 諛⑹옣 �씠由� �쓣�슦湲�
        lblinwon_roomname.setText("roomname");
        
        lblinwon_boss.setBounds(new Rectangle(430, 10, 90, 25));// 諛⑹옣 �씠由� �쓣�슦湲�
        lblinwon_boss.setText("諛⑹옣�씠由�");
        lblinwon_boss.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblinwon_boss.setHorizontalAlignment(SwingConstants.CENTER);
        lblinwon_boss.setHorizontalTextPosition(SwingConstants.CENTER);

        jLabel_guest.setBounds(new Rectangle(550, 10, 75, 20));// 李몄뿬�옄 �씠由� �씪踰�
        jLabel_guest.setText("李몄뿬�옄");

        lblinwon_guest.setBounds(new Rectangle(600, 10, 60, 25)); //李몄뿬�옄 �닽�옄 �쓣�슦湲�
        lblinwon_guest.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblinwon_guest.setHorizontalAlignment(SwingConstants.CENTER);
        lblinwon_guest.setHorizontalTextPosition(SwingConstants.CENTER);

        btn_close.setBounds(new Rectangle(560, 320, 90, 25)); //�굹媛�湲� 踰꾪듉
        btn_close.setText("�굹媛�湲�");

        list.setBounds(new Rectangle(540, 40, 130, 200));// 李몄뿬�옄 紐낅떒

        this.getContentPane().add(list, null);
        this.getContentPane().add(btn_close, null);
        this.getContentPane().add(btn_ok, null);
        this.getContentPane().add(btn_set, null);
        this.getContentPane().add(btn_file, null);
        this.getContentPane().add(lblinwon_roomname, null);
        this.getContentPane().add(lblinwon_boss, null);
        this.getContentPane().add(lblinwon_guest, null);
        this.getContentPane().add(jLabel_boss, null);
        this.getContentPane().add(jLabel_tag, null);
        this.getContentPane().add(jLabel_guest, null);
        this.getContentPane().add(txtsend, null);
        this.getContentPane().add(talkPane1, null);
        talkPane1.getViewport().add(txtarea, null);

        btn_set.addActionListener(new ActionListener() { // �꽕�젙 踰꾪듉 �옉�룞
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUI_ChattingRoomSetting();
            }
        });

       
        btn_ok.addActionListener(new ActionListener() { //�솗�씤 踰꾪듉 �옉�룞
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btn_close.addActionListener(new ActionListener() { //�굹媛�湲� 踰꾪듉 �옉�룞
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		dos.writeInt(Command.EXITROOM);
					dos.writeInt(roomNumber);
					dos.writeUTF(id);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	dispose();
            }
        });
        btn_file.addActionListener(new ActionListener() { //�뙆�씪 踰꾪듉 �옉�룞
            @Override
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        //諛� �뾽�뜲�씠�듃
        room_update();
    // �뜲�씠�꽣踰좎씠�뒪�븯怨� �뿰寃고븷�븣 connection -> statement or prestatement
    // �떎瑜� 而댄벂�꽣�� �뿰寃고븷�븣 Serversocket Socket   .getinputstream or outputstream
        this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
            		dos.writeInt(Command.EXITROOM);
					dos.writeInt(roomNumber);
					dos.writeUTF(id);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        PopupMenu pm = new PopupMenu();
        MenuItem item1 = new MenuItem("Show profile");
        MenuItem item2 = new MenuItem("retire");
        MenuItem item3 = new MenuItem("report");
        pm.add(item1);
        pm.add(item2);
        pm.add(item3);
        List <String> ml = new ArrayList<>();
        ml.add("1111");
        ml.add("2222");
        DefaultListModel<String> m = new DefaultListModel<>();
        m.addElement(ml.get(0));
        m.addElement(ml.get(1));
        list.add(pm);
        
        list.addMouseListener(new MouseListener() {
			
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
				System.out.println(list.getSelectedIndex());
				pm.show(list, e.getX(), e.getY());
			}
		});
        setVisible(true);
    }

   
}