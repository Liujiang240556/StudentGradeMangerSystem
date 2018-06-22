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



/**
 * 模块说明： 更新学生信息
 * 
 */
public class UpdateView extends JFrame {

	private static final long serialVersionUID = 5292738820127102731L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
	private JTextField name, sno, department, hometown, mark, email, tel, sex;

	public UpdateView() {
		init();
	}

	private void init() {
		setTitle(Constants.UPDATEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		jPanelCenter.add(new JLabel(Constants.STUDENT_NAME));
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(Constants.STUDENT_SNO));
		sno = new JTextField();
		jPanelCenter.add(sno);
		jPanelCenter.add(new JLabel(Constants.STUDENT_SEX));
		sex = new JTextField();
		jPanelCenter.add(sex);
		jPanelCenter.add(new JLabel(Constants.STUDENT_DEPARTMETN));
		department = new JTextField();
		jPanelCenter.add(department);
		jPanelCenter.add(new JLabel(Constants.STUDENT_HOMETOWN));
		hometown = new JTextField();
		jPanelCenter.add(hometown);
		jPanelCenter.add(new JLabel(Constants.STUDENT_MARK));
		mark = new JTextField();
		jPanelCenter.add(mark);
		jPanelCenter.add(new JLabel(Constants.STUDENT_EMAIL));
		email = new JTextField();
		jPanelCenter.add(email);
		jPanelCenter.add(new JLabel(Constants.STUDENT_TEL));
		tel = new JTextField();
		tel.addKeyListener(new Updateistener());
		jPanelCenter.add(tel);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton(Constants.UPDATEVIEW_UPDATEBUTTON);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).update(stu);
					if (isSuccess) {
						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO))
								.list(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null, "修改成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					}
				}else {
					JOptionPane.showMessageDialog(null, "修改的学生学号不存在，请重新输入信息！", "提示消息", JOptionPane.WARNING_MESSAGE);
					setEmpty();
				}
			}
		});
		jPanelSouth.add(updateButton);
		exitButton = new JButton(Constants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(sno.getText()) || "".equals(department.getText())
				|| "".equals(sex.getText()) || "".equals(mark.getText()) || "".equals(tel.getText())
				|| "".equals(email.getText()) || "".equals(hometown.getText())) {
			JOptionPane.showMessageDialog(null, "请完整的输入要修改的学生信息！", "提示消息", JOptionPane.WARNING_MESSAGE);
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

	private class Updateistener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).update(stu);
					if (isSuccess) {
						setEmpty();
						if (govern.currPageNum < 0 || govern.currPageNum > 10) {
							govern.currPageNum = 1;
						}
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO))
								.list(govern.currPageNum);
						govern.initJTable(govern.table, result);
						JOptionPane.showMessageDialog(null, "修改成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
						dispose();
					}
				}else {
					JOptionPane.showMessageDialog(null, "修改的学生学号不存在，请重新输入信息！", "提示消息", JOptionPane.WARNING_MESSAGE);
					setEmpty();
				}
			
			}
		}
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
