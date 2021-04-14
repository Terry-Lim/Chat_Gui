import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GUI_MakeRoom extends JFrame {

	private JPanel contentPane;
	private JTextField tf_UpdateName;
	public static final int INIT_VAL = 5;
	private JLabel lbl_UpdateNumber;
	private JSlider sb_UpdateNumber;
	private int number; // 사람 숫자

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GUI_MakeRoom() {
		
		ImageIcon imageIcon_frame = new ImageIcon(".\\image\\logo_frame.png"); // 프레임 아이콘
		Image image_framImage = imageIcon_frame.getImage();
		this.setIconImage(image_framImage);
		
		setTitle("\uBC29 \uB9CC\uB4E4\uAE30");
		number = 0;
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_UpdateName = new JLabel("\uBC29 \uC774\uB984 ");
		lbl_UpdateName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_UpdateName.setBounds(12, 78, 85, 36);
		contentPane.add(lbl_UpdateName);
		
		lbl_UpdateNumber = new JLabel("\uBC29 \uC778\uC6D0\uC218 ");
		lbl_UpdateNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_UpdateNumber.setBounds(12, 144, 85, 36);
		contentPane.add(lbl_UpdateNumber);
		
		tf_UpdateName = new JTextField();
		tf_UpdateName.setColumns(10);
		tf_UpdateName.setBounds(126, 78, 234, 40);
		contentPane.add(tf_UpdateName);
		
		sb_UpdateNumber = new JSlider(0,10,INIT_VAL);
		sb_UpdateNumber.setMajorTickSpacing(5); //큰 눈금 간격 5로 설정
		sb_UpdateNumber.setMinorTickSpacing(1); //작은 눈금 간격 1로 설정
		sb_UpdateNumber.setPaintTicks(true); //눈금을 표시한다.
		sb_UpdateNumber.setPaintLabels(true); //값을 레이블로 표시한다.
		sb_UpdateNumber.addChangeListener(new MyChangeListener());
		sb_UpdateNumber.setBounds(124, 141, 236, 51);
		contentPane.add(sb_UpdateNumber);
		
		JButton btn_Ok = new JButton("\uC124\uC815 \uC644\uB8CC");
		btn_Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(number);
				dispose();
			}
		});
		btn_Ok.setFont(new Font("Gulim", Font.PLAIN, 15));
		btn_Ok.setBounds(164, 202, 97, 40);
		contentPane.add(btn_Ok);
		
		JLabel lblNewLabel = new JLabel("\uBC29 \uB9CC\uB4E4\uAE30");
		lblNewLabel.setFont(new Font("Gulim", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(126, 20, 134, 36);
		contentPane.add(lblNewLabel);
		setVisible(true);
	}
	class MyChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
        	number = sb_UpdateNumber.getValue();
        }
    }
}
