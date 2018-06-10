package dhbw.softwareengineering.bad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainPWGenerator implements ActionListener {

	JFrame frame = new JFrame("Password Generator");
	JButton generate = new JButton("Generate");
	JCheckBox specialCharsBox = new JCheckBox("Use special characters?", true);
	JCheckBox lowerCaseBox = new JCheckBox("Use lower case letters?", true);
	JCheckBox upperCaseBox = new JCheckBox("Use upper case letters?", true);
	JCheckBox digitsBox = new JCheckBox("Use digits?", true);
	JTextField lengthField = new JTextField("8");
	JLabel lengthLbl = new JLabel("Length:");
	JTextArea generatedPassword = new JTextArea();
	int digits[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	char lowerCaseLetters[] = new char[29];
	char upperCaseLetters[] = new char[29];
	char specialChars[] = new char[29];

	public MainPWGenerator() {
		setupFrame();
		addGUI();
		initLetterLists();
		initSpecialCharsList();
	}

	private void setupFrame() {
		frame.setSize(300, 450);
		frame.setLayout(null);
		frame.setVisible(true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - frame.getWidth()) / 2;
		int y = (d.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addGUI() {
		specialCharsBox.setBounds(50, 10, 200, 20);
		frame.add(specialCharsBox);
		lowerCaseBox.setBounds(50, 40, 200, 20);
		frame.add(lowerCaseBox);
		upperCaseBox.setBounds(50, 70, 200, 20);
		frame.add(upperCaseBox);
		digitsBox.setBounds(50, 100, 200, 20);
		frame.add(digitsBox);

		lengthLbl.setBounds(50, 145, 50, 30);
		frame.add(lengthLbl);
		lengthField.setBounds(100, 145, 150, 30);
		frame.add(lengthField);

		generate.setBounds(100, 200, 100, 50);
		generate.addActionListener(this);
		frame.add(generate);

		generatedPassword.setBounds(50, 280, 200, 100);
		generatedPassword.setBackground(Color.BLACK);
		generatedPassword.setFont(new Font("Consolas", Font.PLAIN, 14));
		generatedPassword.setForeground(Color.WHITE);
		generatedPassword.setBorder(BorderFactory.createLineBorder(Color.black));
		generatedPassword.setLineWrap(true);
		frame.add(generatedPassword);
	}

	private void initLetterLists() {
		String alphabet = "abcdefghijklmnopqrstuvwxyzäöü";
		lowerCaseLetters = alphabet.toLowerCase().toCharArray();
		upperCaseLetters = alphabet.toUpperCase().toCharArray();
	}

	private void initSpecialCharsList() {
		String specials = "!\"§$" + '%' + "&/()={[]}+*~'#-_.:,;@<>|";
		System.out.println(specials.length());
		specialChars = specials.toCharArray();
	}

	public static void main(String[] args) {
		System.out.println("Application launching ...");
		new MainPWGenerator();
		System.out.println("Application launched.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == generate) {
			String str = lengthField.getText();
			try {
				if (str.length() == 0) {
					throw new IllegalArgumentException("No value entered for 'length'.");
				}
				int length = Integer.valueOf(str);
				generateAndShowPassword(length);
			} catch (NumberFormatException nfe) {
				generatedPassword.setText("Error. Please only enter numbers in the \"length\" field.");
			} catch (IllegalArgumentException iae) {
				generatedPassword.setText("Enter a value for 'length'");
			}
		}

	}

	private void generateAndShowPassword(int length) {
		String result = new String();
		if (digitsBox.isSelected() || lowerCaseBox.isSelected() || upperCaseBox.isSelected()
				|| specialCharsBox.isSelected()) {
			for (int i = 0; i < length; i++) {
				ArrayList<Character> chars = new ArrayList<Character>();
				ArrayList<char[]> list = new ArrayList<char[]>();
				if (digitsBox.isSelected()) {
					char[] dig = new char[digits.length];
					for (int j = 0; j < digits.length; j++) {
						dig[j] = Integer.toString(digits[j]).charAt(0);
					}
					list.add(dig);
					for (int c : digits) {
						chars.add(Integer.toString(c).charAt(0));
					}
				}
				if (lowerCaseBox.isSelected()) {
					list.add(lowerCaseLetters);
					for (char c : lowerCaseLetters) {
						chars.add(c);
					}
				}
				if (upperCaseBox.isSelected()) {
					list.add(upperCaseLetters);
					for (char c : upperCaseLetters) {
						chars.add(c);
					}
				}
				if (specialCharsBox.isSelected()) {
					list.add(specialChars);
					for (char c : specialChars) {
						chars.add(c);
					}
				}
				int type = (int) Math.round((Math.random() * (list.size() - 1)));
				// System.out.println(list.get(1));
				char[] c = list.get(type);
				result += c[(int) Math.round((Math.random() * (c.length - 1)))];
				// res += chooseRandom(chars.toArray());
			}
			generatedPassword.setText(result);
		} else {
			generatedPassword.setText("Error: tick at least one set of chars");
		}
	}

	public static Object chooseRandom(Object... object) {
		int result = (int) Math.round((Math.random() * (object.length - 1)));
		return object[result];
	}

}
