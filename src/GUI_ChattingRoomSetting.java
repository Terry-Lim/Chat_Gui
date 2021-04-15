import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_ChattingRoomSetting extends JFrame {

	private JPanel contentPane;
	private JTextField tf_UpdateName;
	public static final int INIT_VAL = 5;
	private JLabel lbl_UpdateNumber;
	private JSlider sb_UpdateNumber;
	private int number; // ��� ����
	String member[] = {"111", "11", "1", "11"};
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public GUI_ChattingRoomSetting() {
		
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // ������ ������
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		
		setTitle("방 설정");
		number = 0;
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_UpdateName = new JLabel("\uBC29 \uC774\uB984 \uC218\uC815");
		lbl_UpdateName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_UpdateName.setBounds(12, 78, 85, 36);
		contentPane.add(lbl_UpdateName);
		
		JCheckBox ckb_BookMark = new JCheckBox("\uD604\uC7AC\uBC29 \uC990\uACA8 \uCC3E\uAE30");
		ckb_BookMark.setFont(new Font("Gulim", Font.BOLD, 20));
		ckb_BookMark.setBounds(128, 23, 189, 36);
		contentPane.add(ckb_BookMark);
		
		JLabel lbl_UpdateReader = new JLabel("\uBC29\uC7A5 \uC704\uC784\uD558\uAE30");
		lbl_UpdateReader.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_UpdateReader.setBounds(12, 124, 85, 36);
		contentPane.add(lbl_UpdateReader);
		
		lbl_UpdateNumber = new JLabel("\uBC29 \uC778\uC6D0\uC218 \uC124\uC815");
		lbl_UpdateNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_UpdateNumber.setBounds(12, 170, 85, 36);
		contentPane.add(lbl_UpdateNumber);
		
		tf_UpdateName = new JTextField();
		tf_UpdateName.setColumns(10);
		tf_UpdateName.setBounds(126, 78, 234, 40);
		contentPane.add(tf_UpdateName);
		
		JComboBox cb_UpdateReader = new JComboBox(member);
		cb_UpdateReader.setBounds(126, 132, 234, 30);
		contentPane.add(cb_UpdateReader);
		
		sb_UpdateNumber = new JSlider(0,10,INIT_VAL);
		sb_UpdateNumber.setMajorTickSpacing(5); //ū ���� ���� 5�� ����
		sb_UpdateNumber.setMinorTickSpacing(1); //���� ���� ���� 1�� ����
		sb_UpdateNumber.setPaintTicks(true); //������ ǥ���Ѵ�.
		sb_UpdateNumber.setPaintLabels(true); //���� ���̺�� ǥ���Ѵ�.
		sb_UpdateNumber.addChangeListener(new MyChangeListener());
		sb_UpdateNumber.setBounds(124, 170, 236, 51);
		contentPane.add(sb_UpdateNumber);
		
		JButton btn_Ok = new JButton("\uC124\uC815 \uC644\uB8CC");
		btn_Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(number);
				dispose();
			}
		});
		btn_Ok.setFont(new Font("Gulim", Font.PLAIN, 15));
		btn_Ok.setBounds(166, 251, 97, 40);
		contentPane.add(btn_Ok);
		setVisible(true);
	}

	class MyChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
        	number = sb_UpdateNumber.getValue();
        }
    }
}
