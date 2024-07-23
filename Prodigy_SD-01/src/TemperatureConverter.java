import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame {
    private JTextField displayField;
    private JComboBox<String> unitComboBox;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(44, 51, 61));

        // Display panel
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(54, 61, 71));
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setBackground(new Color(54, 61, 71));
        displayField.setForeground(Color.WHITE);
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(displayField, BorderLayout.CENTER);

        unitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        unitComboBox.setBackground(new Color(64, 71, 81));
        unitComboBox.setForeground(Color.WHITE);
        displayPanel.add(unitComboBox, BorderLayout.SOUTH);

        add(displayPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(new Color(44, 51, 61));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "C",
                "4", "5", "6", "←",
                "1", "2", "3", "+/-",
                "0", ".", "=", "Conv"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            if (label.equals("=")) {
                button.setBackground(new Color(255, 69, 0));
            } else if (label.equals("Conv")) {
                button.setBackground(new Color(0, 191, 255));
            } else {
                button.setBackground(new Color(64, 71, 81));
            }
            button.setForeground(Color.WHITE);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("C")) {
                displayField.setText("");
            } else if (command.equals("=")) {
                // Perform calculation (not implemented in this example)
            } else if (command.equals("Conv")) {
                convertTemperature();
            } else if (command.equals("←")) {
                String currentText = displayField.getText();
                if (!currentText.isEmpty()) {
                    displayField.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else {
                displayField.setText(displayField.getText() + command);
            }
        }
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(displayField.getText());
            String unit = (String) unitComboBox.getSelectedItem();
            double celsius, fahrenheit, kelvin;

            switch (unit) {
                case "Celsius":
                    celsius = temperature;
                    fahrenheit = celsiusToFahrenheit(celsius);
                    kelvin = celsiusToKelvin(celsius);
                    break;
                case "Fahrenheit":
                    fahrenheit = temperature;
                    celsius = fahrenheitToCelsius(fahrenheit);
                    kelvin = celsiusToKelvin(celsius);
                    break;
                case "Kelvin":
                    kelvin = temperature;
                    celsius = kelvinToCelsius(kelvin);
                    fahrenheit = celsiusToFahrenheit(celsius);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid unit");
            }

            String result = String.format("%.2f°C = %.2f°F = %.2fK", celsius, fahrenheit, kelvin);
            displayField.setText(result);
        } catch (NumberFormatException ex) {
            displayField.setText("Invalid input");
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverter().setVisible(true));
    }
}