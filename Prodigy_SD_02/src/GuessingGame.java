import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int numberToGuess;
    private JTextField guessField;
    private JLabel messageLabel;
    private JButton guessButton;

    public GuessingGame() {
        setTitle("GUESS MY NUMBER GAME");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(new Color(48, 0, 48)); // Dark purple background

        initComponents();
        startNewGame();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("GUESS MY NUMBER GAME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.ORANGE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        messageLabel = new JLabel("GUESS A NUMBER BETWEEN 1 AND 100:");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        guessField = new JTextField(10);
        guessField.setMaximumSize(new Dimension(100, 30));
        guessField.setFont(new Font("Arial", Font.BOLD, 20));
        guessField.setHorizontalAlignment(JTextField.CENTER);

        guessButton = new JButton("GUESS");
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.setBackground(new Color(128, 0, 128)); // Purple button
        guessButton.setForeground(Color.WHITE);
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructionLabel = new JLabel("ENTER A NUMBER ABOVE AND CLICK GUESS");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        instructionLabel.setForeground(Color.ORANGE);
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel creditLabel = new JLabel("MADE BY G1.CORP");
        creditLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        creditLabel.setForeground(Color.WHITE);
        creditLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(messageLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(guessField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(guessButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(instructionLabel);
        add(Box.createVerticalGlue());
        add(creditLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        guessField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void startNewGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        messageLabel.setText("GUESS A NUMBER BETWEEN 1 AND 100:");
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess == numberToGuess) {
                messageLabel.setText("Congratulations! You guessed it!");
                guessButton.setEnabled(false);
                guessField.setEnabled(false);
            } else if (guess < numberToGuess) {
                messageLabel.setText("Too low! Try again.");
            } else {
                messageLabel.setText("Too high! Try again.");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
        guessField.setText("");
        guessField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuessingGame game = new GuessingGame();
                game.setVisible(true);
                game.setLocationRelativeTo(null); // Center the window
            }
        });
    }
}