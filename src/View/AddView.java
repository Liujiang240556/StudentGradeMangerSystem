
package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO;
import DAO.StudentDAO;
import Model.Student;
import Util.Constants;

import base.BaseDAO;
import javax.swing.SwingConstants;



/**
 * 模块说明： 添加学生
 * 
 */
public class AddView extends JFrame {

	private static final long serialVersionUID = -1984182788841566838L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton addButton, exitButton;
	private JTextField name, sno, department, hometown, mark, email, tel, sex;

	public AddView() {
		setResizable(false);
		init();
	}

	private void init() {
		setTitle(Constants.ADDVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		JLabel label = new JLabel("姓名:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label);
		name = new JTextField();
		jPanelCenter.add(name);
		JLabel label_1 = new JLabel("学号:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_1);
		sno = new JTextField();
		jPanelCenter.add(sno);
		JLabel label_2 = new JLabel("性别:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_2);
		sex = new JTextField();
		jPanelCenter.add(sex);
		JLabel label_3 = new JLabel("院系:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_3);
		department = new JTextField();
		jPanelCenter.add(department);
		JLabel label_4 = new JLabel("籍贯:");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_4);
		hometown = new JTextField();
		jPanelCenter.add(hometown);
		JLabel label_5 = new JLabel("学分:");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_5);
		mark = new JTextField();
		jPanelCenter.add(mark);
		JLabel label_6 = new JLabel("电子邮件:");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_6);
		email = new JTextField();
		jPanelCenter.add(email);
		JLabel label_7 = new JLabel("联系方式:");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelCenter.add(label_7);
		tel = new JTextField();
		
		tel.addKeyListener(new AddListener());
		jPanelCenter.add(tel);

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		addButton = new JButton(Constants.ADDVIEW_ADDBUTTON);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).add(stu);
					if (isSuccess) {
						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO))
								.list(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null, "添加成功", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "重复的学号！请重新添加学生信息！", "提示消息", JOptionPane.WARNING_MESSAGE);
						name.setText("");
						sno.setText("");
						department.setText("");
						sex.setText("");
						mark.setText("");
						tel.setText("");
						email.setText("");
						hometown.setText("");
					}
				}
			}
		});
		jPanelSouth.add(addButton);
		exitButton = new JButton(Constants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		getContentPane().add(jPanelCenter, BorderLayout.CENTER);
		getContentPane().add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setVisible(true);
	}
	
	private class AddListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).add(stu);
					if (isSuccess) {
						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO))
								.list(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null, "添加成功", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "重复的学号！请重新添加学生信息！", "提示消息", JOptionPane.WARNING_MESSAGE);
						name.setText("");
						sno.setText("");
						department.setText("");
						sex.setText("");
						mark.setText("");
						tel.setText("");
						email.setText("");
						hometown.setText("");
					}
				}
			}
		}
	}
	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(sno.getText()) || "".equals(department.getText())
				|| "".equals(sex.getText()) || "".equals(mark.getText()) || "".equals(tel.getText())
				|| "".equals(email.getText()) || "".equals(hometown.getText())) {
			JOptionPane.showMessageDialog(null, "请把学生信息填写完整！", "提示消息", JOptionPane.WARNING_MESSAGE);
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildStudent(Student stu) {
		stu.setDepartment(department.getText());
		stu.setEmail(email.getText());
		stu.setHomeTown(hometown.getText());
		stu.setMark(mark.getText());
		stu.setName(name.getText());
		stu.setSno(sno.getText());
		stu.setTel(tel.getText());
		stu.setSex(sex.getText());
	}

	private void setEmpty() {
		name.setText("");
		sno.setText("");
		department.setText("");
		sex.setText("");
		email.setText("");
		hometown.setText("");
		tel.setText("");
		mark.setText("");
	}
}
