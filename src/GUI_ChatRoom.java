import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;


public class GUI_ChatRoom extends JFrame {
	private static boolean check;
	
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
    private PrintWriter pw;
    private BufferedReader br;
    private String roomname;
    String id;
	private PopupMenu pm;

    
    
   
    
    GUI_ChatRoom(DataOutputStream dos, DataInputStream dis, String roomname, String id, PrintWriter pw, BufferedReader br) {
    	this.dos = dos;
    	this.dis = dis;
    	this.roomNumber = roomNumber;
    	this.id = id;
    	this.pw = pw;
    	this.br = br;
    	this.roomname = roomname;
    	ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); 
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
    	
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(700, 400));
        this.setTitle("채팅방"); 
        this.setBackground(Color.orange);

        talkPane1.setBounds(new Rectangle(15, 40, 505, 270)); 
        talkPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        talkPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtsend.setBounds(new Rectangle(15, 320, 380, 25)); 

        btn_set.setBounds(new Rectangle(15, 10, 120, 25));
        btn_set.setText("채팅방 설정");

        
        btn_ok.setBounds(new Rectangle(400, 320, 60, 25));
        btn_ok.setText("확인");
       

        btn_file.setBounds(new Rectangle(470, 320, 50, 25));
        btn_file.setText("+");
        btn_file.setFont(new Font("",Font.ITALIC,15));
        
        jLabel_boss.setBounds(new Rectangle(390, 10, 75, 20));
        jLabel_boss.setText("방장");

        jLabel_tag.setBounds(new Rectangle(200, 10, 75, 20));
        jLabel_tag.setText("방 이름");
        lblinwon_roomname.setBounds(new Rectangle(270, 10, 120, 25));
        lblinwon_roomname.setText("");
        
        lblinwon_boss.setBounds(new Rectangle(430, 10, 90, 25));
        lblinwon_boss.setText("");
        lblinwon_boss.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblinwon_boss.setHorizontalAlignment(SwingConstants.CENTER);
        lblinwon_boss.setHorizontalTextPosition(SwingConstants.CENTER);

        jLabel_guest.setBounds(new Rectangle(550, 10, 75, 20));
        jLabel_guest.setText("방 인원");

        lblinwon_guest.setBounds(new Rectangle(600, 10, 60, 25)); 
        lblinwon_guest.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblinwon_guest.setHorizontalAlignment(SwingConstants.CENTER);
        lblinwon_guest.setHorizontalTextPosition(SwingConstants.CENTER);

        btn_close.setBounds(new Rectangle(560, 320, 90, 25)); 
        btn_close.setText("나가기");

        list.setBounds(new Rectangle(540, 40, 130, 200));

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

        btn_set.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUI_ChattingRoomSetting();
            }
        });

       
        btn_ok.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btn_close.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		dos.writeInt(Command.EXITROOM);
					dos.writeUTF(roomname);
					dos.writeUTF(id);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	dispose();
            }
        });
        btn_file.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) {

            }
        });
     
        update_room();
   
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
        
        pm = new PopupMenu();
        MenuItem item1 = new MenuItem("프로필 보기");
        MenuItem item2 = new MenuItem("강퇴");
        MenuItem item3 = new MenuItem("신고하기");
        item1.setActionCommand("1");
        item2.setActionCommand("2");
        item3.setActionCommand("3");
        pm.add(item1);
        pm.add(item2);
        pm.add(item3);
        item1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Profile();
				System.out.println("클릭");
				
			}
		});
        
        update_participants();
      
        setVisible(true);
    }
    
    public void update_participants() {
    	Thread t = new Thread(new Runnable() {
	
			@Override
			public void run() {
					if (get() == true) {
						List <String> ml = new ArrayList<>();
						try {
							dos.writeInt(Command.PARTICIPANTSUPDATE);
							dos.writeUTF(roomname);
							int roomnum = 0;
							roomnum = dis.readInt();
							
							for (int i = 0; i < roomnum; i++) {
								String x = dis.readUTF();
								ml.add(x);
							} 	
							
						} catch (IOException e1) {
						   	e1.printStackTrace();
						} 
						    
						DefaultListModel<String> m = new DefaultListModel<>();
						   
						for (int i = 0; i < ml.size(); i++) {
							m.addElement(ml.get(i));
						}
						      
						list.setModel(m);
						list.add(pm);
						pm.addActionListener(new ActionListener() {
						  	
							@Override
							public void actionPerformed(ActionEvent e) {
							  	System.out.println(e.getActionCommand());
							}
						});
						list.addMouseListener(new MouseListener() {
							  	
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
							  	System.out.println(list.getSelectedIndex());
							  	pm.show(list, e.getX(), e.getY());
							}
						});
						setf();
					}
					
			}
		});
    	t.start();	
    }
    public void update_room() {
    
    	Thread t = new Thread(new Runnable() {
		
			@Override
			public void run() {
					if (get() == false) {
						try {
							dos.writeInt(Command.ROOMUPDATE);
							dos.writeUTF(roomname);
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
						sett();
					}
			}
		}); 
        t.start();
        
    }
    synchronized static public boolean get() {
		return check;
	}
	synchronized static public void setf() {
		check = false;
	}
	synchronized static public void sett() {
		check = true;
	}
	
}