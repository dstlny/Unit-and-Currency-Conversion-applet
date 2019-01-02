import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainPanel {
	JPanel mainPanel;
	MeasurementPanel measurementPanel;
	CurrencyPanel currencyPanel;
	private ImageIcon menu1ExitIcon, menu2AboutIcon, menu1ImportFileIcon;
	static String[] importCurrencyArray;
	static Double[] importFactorArray;
	static String[] importSymbolArray;
	static String[] currencyNamesArray;
	static String nextFileLine;
	static int i;
	static int globalErrorCount;
	static File importedFile;
	static int userFileChoice;
	static BufferedReader fileContentReader;
	static boolean importOptionChosen = false;
	static JFileChooser fileChooser = new JFileChooser("src");
	static String[] importedLinesArray;

	public MainPanel() {
		measurementPanel = new MeasurementPanel("Unit Conversion");
		currencyPanel = new CurrencyPanel("Currency Conversion");
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.add(measurementPanel);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.add(currencyPanel);
		mainPanel.setPreferredSize(new Dimension(750, 200));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	static void setupAndDrawConverter() {
		JFrame converterFrame = new JFrame("Converter");
		converterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel converter = new MainPanel();
		converterFrame.setContentPane(converter.mainPanel);
		converterFrame.setJMenuBar(converter.setupMenu());
		converterFrame.pack();
		converterFrame.setVisible(true);
		converterFrame.repaint();
		converterFrame.revalidate();
	}

	JMenuBar setupMenu() {

		/*
		 * Creating the very-top menuBar which houses menu1 & menu2, as well as all
		 * menuItems
		 */
		JMenuBar topMenuBar = new JMenuBar();

		/*
		 * Simply declaring two ImageIcons which will be used in MenuItems1/2
		 */
		menu1ExitIcon = new ImageIcon("images/exit.png");
		menu2AboutIcon = new ImageIcon("images/about.png");
		menu1ImportFileIcon = new ImageIcon("images/import.jpg");

		/*
		 * Creating menu1 and settings it's mnemonics.
		 */
		JMenu topMenu1 = new JMenu("<html><u>F</u>ile</html>");
		topMenu1.setMnemonic(KeyEvent.VK_F);

		/*
		 * Creating menu2 and settings it's mnemonics.
		 */
		JMenu topMenu2 = new JMenu("<html><u>H</u>elp</html>");
		topMenu2.setMnemonic(KeyEvent.VK_H);

		/*
		 * Adding created menus to menuBar
		 */
		topMenuBar.add(topMenu1);
		topMenuBar.add(topMenu2);

		/*
		 * Creating menuItem1 and giving it an icon which i defined above, Adding
		 * menuItem1 to the menu1 setting the mnemonic of menuItem1 adding a listener to
		 * menuItem2, which will call System.exit(0) when clicked ->> exiting program as
		 * a result
		 */
		JMenuItem topMenu1Item1 = new JMenuItem("<html><u>E</u>xit</html>");
		topMenu1Item1.setIcon(menu1ExitIcon);
		topMenu1.add(topMenu1Item1);
		topMenu1Item1.setToolTipText("Press this button to exit the application.");
		topMenu1Item1.setMnemonic(KeyEvent.VK_E);
		topMenu1Item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		/*
		 * Creating menuItem3 and giving it an icon which i defined above, Adding
		 * menuItem3 to the menu1 setting the mnemonic of menuItem3 adding a listener to
		 * menuItem3 which will simply call importFile() and set a boolean to false or
		 * true depending on user choice.;
		 */
		JMenuItem topMenu1Item2 = new JMenuItem("<html><u>L</u>oad</html>");
		topMenu1.add(topMenu1Item2);
		topMenu1Item2.setToolTipText("Press this button if you would like to load a new currency file.");
		topMenu1Item2.setIcon(menu1ImportFileIcon);
		topMenu1Item2.setMnemonic(KeyEvent.VK_L);
		topMenu1Item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userFileChoice = fileChooser.showOpenDialog(topMenu1Item2);
				if (userFileChoice == JFileChooser.APPROVE_OPTION) {
					importedFile = fileChooser.getSelectedFile();
					importOptionChosen = true;

					Currencies.i = 0;
					Currencies.k = 0;
					Currencies.j = 0;
					globalErrorCount = 0;

					importFile();

					CurrencyPanel.currencyCombo.removeAllItems();
					for (String values : Currencies.currencyName) {
						CurrencyPanel.currencyCombo.addItem(values);
					}

					ToolClass.availableCurrenciesDialog();
				} else {
					ToolClass.selectionCancelledDialog();
				}
			}
		});

		/*
		 * Creating menuItem2 and giving it an icon which i defined above, Adding
		 * menuItem2 to the menu2 setting the mnemonic of menuItem2 adding a listener to
		 * menuItem2, which will call show a pop up with some information about the
		 * program when called
		 */
		JMenuItem topMenu2Item1 = new JMenuItem("<html><u>A</u>bout</html>");
		topMenu2.add(topMenu2Item1);
		topMenu2Item1.setToolTipText("Press this button to find out more about this application.");
		topMenu2Item1.setIcon(menu2AboutIcon);
		topMenu2Item1.setMnemonic(KeyEvent.VK_A);
		topMenu2Item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Invoke aboutProgramDialog() method from ToolClass()
				 */
				ToolClass.aboutProgramDialog();
			}
		});

		return topMenuBar;
	}

	/*
	 * used to import both the initial file and file the user choses This was
	 * originally split into two methods, though through the use of a Boolean i have
	 * been able to merge those.
	 */
	public static void importFile() {

		try {
			if (!importOptionChosen) {
				fileContentReader = new BufferedReader(new FileReader("src/currency.txt"));
			} else {
				fileContentReader = new BufferedReader(new FileReader(importedFile));
			}

			ArrayList<String> tempLineStorageList = new ArrayList<>();

			while (true) {
				nextFileLine = fileContentReader.readLine();
				if (nextFileLine == null) {
					break;
				}
				tempLineStorageList.add(nextFileLine);
			}

			importCurrencyArray = new String[tempLineStorageList.size()];
			importFactorArray = new Double[tempLineStorageList.size()];
			importSymbolArray = new String[tempLineStorageList.size()];

			for (i = 0; i < tempLineStorageList.size(); i++) {

				importedLinesArray = tempLineStorageList.get(i).split("\\(|\\)?\\s*,\\s*");

				if (importedLinesArray.length != 4) {
					globalErrorCount++;
					ToolClass.lineCorruptDialog();
					continue;
				}

				currencyNamesArray = new String[tempLineStorageList.size()];

				try {
					currencyNamesArray[i] = importedLinesArray[0].trim();
				} catch (Exception e) {
					globalErrorCount++;
					ToolClass.incorrectCurrencyNameDialog();
				}

				if (currencyNamesArray[i].length() == 0 || currencyNamesArray[i] == null) {
					globalErrorCount++;
					ToolClass.incorrectCurrencyNameDialog();
					continue;
				}

				try {
					importCurrencyArray[i] = importedLinesArray[1].trim();
				} catch (Exception e) {
					globalErrorCount++;
					ToolClass.incorrectCurrencyDialog();
				}

				if (importCurrencyArray[i].length() == 0  || importCurrencyArray[i] == null) {
					globalErrorCount++;
					ToolClass.incorrectCurrencyDialog();
					continue;
				}

				try {
					importFactorArray[i] = Double.valueOf(importedLinesArray[2].trim());
				} catch (Exception e) {
					globalErrorCount++;
					ToolClass.incorrectFactorDialog();
					continue;
				}

				try {
					importSymbolArray[i] = importedLinesArray[3].trim();
				} catch (Exception e) {
					globalErrorCount++;
					ToolClass.incorrectSymbolDialog();
				}

				if (importSymbolArray[i].length() == 0  || importSymbolArray[i] == null) {
					globalErrorCount++;
					ToolClass.incorrectSymbolDialog();
					continue;
				}

				Currencies.currency(importCurrencyArray[i]);
				Currencies.factor(importFactorArray[i]);
				Currencies.symbol(importSymbolArray[i]);
			}
		} catch (FileNotFoundException e) {
			ToolClass.incorrectFilePathDialog();
		} catch (IOException e) {
			ToolClass.incorrectFilePathDialog();	
		}

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				/*
				 * calling setupAndDrawConverter() as well as importFile() - two methods of
				 * which are aptly named
				 */
				importFile();
				setupAndDrawConverter();
			}
		});
	}
}