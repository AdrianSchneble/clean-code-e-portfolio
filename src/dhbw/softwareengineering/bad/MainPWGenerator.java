package dhbw.softwareengineering.bad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == generate) {
			String lengthInputByUser = lengthField.getText();
			try {
				if (lengthInputByUser.length() == 0) {
					throw new IllegalArgumentException("No value entered for 'length'.");
				}
				generateAndShowPassword(Integer.valueOf(lengthInputByUser));
			} catch (NumberFormatException nfe) {
				generatedPassword.setText("Error. Please only enter numbers in the \"length\" field.");
			} catch (IllegalArgumentException iae) {
				generatedPassword.setText("Enter a value for 'length'");
			}
		}
	}

	private void generateAndShowPassword(int length) {
		String result = new String();
		if (anyCharacterSetIsSelected()) {
			result = generatePassword(length, result);
			generatedPassword.setText(result);
		} else {
			generatedPassword.setText("Error: tick at least one set of chars");
		}
	}

	private boolean anyCharacterSetIsSelected() {
		return digitsBox.isSelected() || lowerCaseBox.isSelected() || upperCaseBox.isSelected()
				|| specialCharsBox.isSelected();
	}

	private String generatePassword(int length, String result) {
		ArrayList<List<Character>> list = getSelectedLists();
		for (int i = 0; i < length; i++) {
			result += selectRandomChar(list);
		}
		return result;
	}

	private ArrayList<List<Character>> getSelectedLists() {
		ArrayList<List<Character>> list = new ArrayList<>();
		if (digitsBox.isSelected()) {
			list.add(listFromIntArray(digits));
		}
		if (lowerCaseBox.isSelected()) {
			list.add(listFromCharArray(lowerCaseLetters));
		}
		if (upperCaseBox.isSelected()) {
			list.add(listFromCharArray(upperCaseLetters));
		}
		if (specialCharsBox.isSelected()) {
			list.add(listFromCharArray(specialChars));
		}
		return list;
	}

	private Character selectRandomChar(ArrayList<List<Character>> list) {
		int type = (int) Math.round((Math.random() * (list.size() - 1)));
		List<Character> c = list.get(type);
		Character randomChar = c.get((int) Math.round((Math.random() * (c.size() - 1))));
		return randomChar;
	}

	private List<Character> listFromIntArray(int[] array) {
		List<Character> list = new ArrayList<>();
		for (int i : array) {
			// (char)i returns the ASCII char with value i, not the char 'i'
			list.add((char) (i + '0'));
		}
		return list;
	}

	private List<Character> listFromCharArray(char[] array) {
		List<Character> list = new ArrayList<>();
		for (char c : array) {
			list.add(c);
		}
		return list;
	}

}
