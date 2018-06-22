package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.AdminDAO;
import DAO.DAO;

import base.BaseDAO;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Window.Type;

public class Login extends JFrame {
    
	
	private static final long serialVersionUID = 1L;
	
	
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.  启动应用    
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. 创建窗口
	 */
	public Login() {
		setResizable(false);
		setTitle("登录窗口");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setBounds(77, 48, 66, 36);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(200, 53, 139, 27);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(77, 113, 49, 41);
		contentPane.add(lblNewLabel_1);
		
		JButton login = new JButton("登录");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		login.setBounds(47, 216, 93, 29);
		contentPane.add(login);
		
		JButton exit = new JButton("退出");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exit.setBounds(317, 216, 93, 29);
		contentPane.add(exit);
		
		password = new JPasswordField();
		password.setBounds(200, 120, 139, 27);
		
		//绑定快捷键
		password.addKeyListener(new LoginListener());
		contentPane.add(password);
		
		JButton reset = new JButton("重置");
		reset.setBounds(178, 216, 105, 29);
		contentPane.add(reset);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				username.setText("");
				password.setText("");
			}
		});
	}
	
	// 当按下enter键时也可以登录，绑定快捷键
		private class LoginListener extends KeyAdapter {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					check();
				}
			}
		}

		private void check() {
			// 用管理员的身份登录系统，传的值是管理员
			// Dao层是对数据库的查询，连接，更新等，
			// 在本Dao层是个枚举类型，有两个类型，AdminDAO,StudentDao
	       //先判断是否为空
			if (username.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入用户名", "提示消息", JOptionPane.WARNING_MESSAGE);
				username.setText("");
				password.setText("");

			} else if (password.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入密码", "提示消息", JOptionPane.WARNING_MESSAGE);
				username.setText("");
				password.setText("");

			} else {//不为空时再查询
				AdminDAO adminDAO = (AdminDAO) BaseDAO.getAbilityDAO(DAO.AdminDAO);

				if (adminDAO.queryForLogin(username.getText(), String.valueOf(password.getPassword()))) {
					dispose();
					JOptionPane.showMessageDialog(null, "登录成功", "提示消息", JOptionPane.WARNING_MESSAGE);
					
					govern frame = new govern();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "账号或密码不正确", "提示消息", JOptionPane.WARNING_MESSAGE);
					username.setText("");
					password.setText("");
				}

			}
		}

}
