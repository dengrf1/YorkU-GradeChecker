package CheckGrade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class frontend {
	public String checkNamePW(String s) {
		if (s.equals("")) {
			JOptionPane.showMessageDialog(null, "Username or password is empty");
			throw new IllegalArgumentException();
		} else {
			return s;
		}
	}

	public int checkRow(String s) {
		if (s.equals("")) {
			JOptionPane.showMessageDialog(null, "The row section is empty");
			throw new IllegalArgumentException();
		} else {
			return Integer.parseInt(s);
		}
	}

	public void createWindow() {
		Font font1 = new Font("SansSerif", Font.PLAIN, 20);
		JFrame window = new JFrame("Check your Grade");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel outer = new JPanel(new BorderLayout());
		outer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel input = new JPanel(new BorderLayout());
		JPanel button = new JPanel();
		button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JPanel namePanel = new JPanel();
		namePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JPanel pwPanel = new JPanel();
		pwPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JPanel coursePanel = new JPanel();
		coursePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JPanel rowPanel = new JPanel();
		rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JButton submit = new JButton("START");
		submit.setFont(font1);
		button.add(submit);

		input.add(namePanel, BorderLayout.NORTH);
		input.add(pwPanel, BorderLayout.CENTER);
		input.add(coursePanel, BorderLayout.SOUTH);
		outer.add(input, BorderLayout.NORTH);
		outer.add(rowPanel, BorderLayout.CENTER);
		outer.add(button, BorderLayout.SOUTH);

		JLabel name = new JLabel("USERNAME: ");
		name.setFont(font1);
		JTextField nameField = new JTextField(18);
		nameField.setFont(font1);
		namePanel.add(name);
		namePanel.add(nameField);

		JLabel pw = new JLabel("PASSWORD: ");
		pw.setFont(font1);
		JPasswordField pwField = new JPasswordField(18);
		pwField.setFont(font1);
		pwPanel.add(pw);
		pwPanel.add(pwField);

		JLabel course = new JLabel("COURSE CODE: ");
		course.setFont(font1);
		JTextField courseField = new JTextField(16);
		courseField.setFont(font1);
		coursePanel.add(course);
		coursePanel.add(courseField);

		JLabel row = new JLabel("WHICH ROW YOUR COURSE LOCATED: ");
		row.setFont(font1);
		JTextField rowField = new JTextField(2);
		rowField.setFont(font1);
		pwPanel.add(pw, BorderLayout.NORTH);
		pwPanel.add(pwField, BorderLayout.SOUTH);
		rowPanel.add(row);
		rowPanel.add(rowField);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = checkNamePW(nameField.getText());
				String password = checkNamePW(pwField.getText());
				String course = checkNamePW(courseField.getText());
				int row = checkRow(rowField.getText());
				CheckGrade myGrade = new CheckGrade(name, password, course, row);
				ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
				exec.scheduleAtFixedRate(myGrade.run, 0, 30, TimeUnit.MINUTES);
			}
		});

		window.setContentPane(outer);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public static void main(String args[]) {
		frontend myWindow = new frontend();
		myWindow.createWindow();
	}
}
