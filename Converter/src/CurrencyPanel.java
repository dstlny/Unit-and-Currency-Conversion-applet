import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CurrencyPanel extends JPanel {

	/*
	 * Declaring all variables and components to be used within the GUI
	 */
	static JTextField currencyConversionField;
	static JLabel currencyConversionLabel;
	private JLabel currencyTextfieldLabel;
	static JButton currencyConversionButton;
	static JComboBox<String> currencyCombo;
	static String currencyUserInputField;
	private double currencyValueToConvert;
	static double currencyResult;
	private String currencyTitle;
	ActionListener convertListener = new ConvertListener();
	ActionListener ButtonListener = new ButtonListener();

	/*
	 * All factors used during conversion will be replaced with an Array come
	 * Requirement 2
	 */
	static Double currencyFactor;
	static String currencySymbol;

	public CurrencyPanel(String myTitle) {
		currencyTitle = myTitle;
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(currencyTitle),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		/*
		 * Creating two listeners which will be used "convertListener" is used for
		 * conversions "convertDeleteListener" is used to set the values of
		 * conversionField, resultLabel, conversionCountLabel and all used to display
		 * details about number of conversions etc.
		 */

		/*
		 * Creating the combo
		 */
		currencyCombo = new JComboBox<String>();

		for (String value : Currencies.currencyName) {
			currencyCombo.addItem(value);
			currencyCombo.setEnabled(true);
		}
		currencyCombo.setBackground(Color.white);
		currencyCombo.setOpaque(true);

		/*
		 * Creating inputLabel and conversionCountLabel
		 */
		currencyTextfieldLabel = new JLabel("Enter value:");

		/*
		 * Creating both the convertButon and clearButton Adding listeners to both
		 * buttons which determine when they are clicked, and what action should be
		 * carried out when done so.
		 */
		currencyConversionButton = new JButton("Convert");

		/*
		 * Creating both the resultLabel and conversionField
		 */
		currencyConversionLabel = new JLabel();
		currencyConversionField = new JTextField(5);

		/*
		 * Adding all created assets to the JFrame. Adding toolTips to each of the
		 * created assets. Adding Listeners to select assets to carry out certain tasks.
		 */
		add(currencyCombo);
		currencyCombo.setToolTipText("In this Combobox please specify the conversion which you would like to be carried out.");

		add(currencyTextfieldLabel);
		add(currencyConversionField);
		currencyConversionField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currencyConversionButton.doClick();
			}
		});

		add(currencyConversionButton);
		currencyConversionButton.setToolTipText("Please click this button when you want to convert your chosen value.");
		currencyConversionButton.addActionListener(ButtonListener);
		currencyConversionButton.addActionListener(convertListener);

		add(currencyConversionLabel);

		setBackground(Color.WHITE);
	}

	public class ConvertListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			/*
			 * Trimming all leading and trailing white spaces from the text the user has
			 * input.
			 */
			currencyUserInputField = currencyConversionField.getText().trim();

			if (!currencyUserInputField.isEmpty()) {

				/*
				 * Simply trying to convert input to a double, if that fails outputting a Dialog
				 * box to users screen stating why Making sure that if the conversion fails,
				 * calCount is actually set to zero, then clicking the Clear Button to clear all
				 * values on screen
				 */
				try {
					currencyValueToConvert = Double.parseDouble(currencyUserInputField);
				} catch (NumberFormatException exception) {
					MeasurementPanel.globalClearButton.doClick();
					/*
					 * Invoke Formatexception() method from Dialog() class
					 */
					ToolClass.formatExceptionDialog();
					return;
				}

				/*
				 * This switch sets up the Conversion factors, what Currencies symbol to use and
				 * increments the globalCount
				 */
				switch (currencyCombo.getSelectedIndex()) {
				case 0:
					if (!MeasurementPanel.globalReverseCalculation.isSelected()) {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertMulti(Currencies.getFactor(), currencyValueToConvert);
						break;

					} else {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertDivi(Currencies.getFactor(), currencyValueToConvert);
						break;

					}
				case 1:
					if (!MeasurementPanel.globalReverseCalculation.isSelected()) {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertMulti(Currencies.getFactor(), currencyValueToConvert);
						break;

					} else {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertDivi(Currencies.getFactor(), currencyValueToConvert);
						break;

					}
				case 2:
					if (!MeasurementPanel.globalReverseCalculation.isSelected()) {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertMulti(Currencies.getFactor(), currencyValueToConvert);
						break;

					} else {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertDivi(Currencies.getFactor(), currencyValueToConvert);
						break;

					}
				case 3:
					if (!MeasurementPanel.globalReverseCalculation.isSelected()) {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertMulti(Currencies.getFactor(), currencyValueToConvert);
						break;

					} else {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertDivi(Currencies.getFactor(), currencyValueToConvert);
						break;

					}
				case 4:
					if (!MeasurementPanel.globalReverseCalculation.isSelected()) {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertMulti(Currencies.getFactor(), currencyValueToConvert);
						break;

					} else {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertDivi(Currencies.getFactor(), currencyValueToConvert);
						break;

					}
				case 5:
					if (!MeasurementPanel.globalReverseCalculation.isSelected()) {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertMulti(Currencies.getFactor(), currencyValueToConvert);
						break;

					} else {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertDivi(Currencies.getFactor(), currencyValueToConvert);
						break;

					}
				case 6:
					if (!MeasurementPanel.globalReverseCalculation.isSelected()) {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertMulti(Currencies.getFactor(), currencyValueToConvert);
						break;

					} else {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertDivi(Currencies.getFactor(), currencyValueToConvert);
						break;

					}
				case 7:
					if (!MeasurementPanel.globalReverseCalculation.isSelected()) {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertMulti(Currencies.getFactor(), currencyValueToConvert);
						break;

					} else {

						MeasurementPanel.globalCount++;
						MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
						ToolClass.convertDivi(Currencies.getFactor(), currencyValueToConvert);
						break;

					}
				}

				/*
				 * Formatting output to two decimal places and changing result label to reflect
				 * result of conversion
				 */
				String curNumAsString = String.format("%.2f", currencyResult);

				if (!MeasurementPanel.globalReverseCalculation.isSelected()) {
					currencyConversionLabel.setText(Currencies.getSymbol() + curNumAsString);
				} else {
					currencyConversionLabel.setText(Currencies.getDefault() + curNumAsString);
				}

			} else {

				/*
				 * Invoke emptyTextField() method from Dialog() class
				 */
				ToolClass.emptyTextFieldDialog();
			}
		}
	}

	/*
	 * Listener used for listening to button presses, etc
	 */
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		/*
		 * Using e.getSource to determine which button was pressed, doing some actions
		 * depending on which it was.
		 */
		if (e.getSource() == currencyConversionButton && !currencyUserInputField.isEmpty()) {
			MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
		} else {
			if (e.getSource().equals(MeasurementPanel.globalClearButton)) {
				currencyConversionLabel.setText("");
				currencyConversionField.setText("");
				MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
			}
		}
	}
  }
}