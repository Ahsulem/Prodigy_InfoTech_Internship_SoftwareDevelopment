import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuSolver extends JFrame {
    private JTextField[][] cells;
    private JButton solveButton, clearButton, exitButton;

    public SudokuSolver() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 600);

        initComponents();
    }

    private void initComponents() {
        JPanel gridPanel = new JPanel(new GridLayout(9, 9, 1, 1));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gridPanel.setBackground(Color.BLACK);

        cells = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                cells[i][j].setForeground(Color.BLUE);
                if ((i / 3 + j / 3) % 2 == 0) {
                    cells[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    cells[i][j].setBackground(Color.WHITE);
                }
                gridPanel.add(cells[i][j]);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear grid");
        exitButton = new JButton("Exit");

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        solveButton.addActionListener(e -> solveSudoku());
        clearButton.addActionListener(e -> clearGrid());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void solveSudoku() {
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String cellValue = cells[i][j].getText();
                if (!cellValue.isEmpty()) {
                    try {
                        board[i][j] = Integer.parseInt(cellValue);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers only.");
                        return;
                    }
                }
            }
        }

        if (solve(board)) {
            updateGrid(board);
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists for this Sudoku puzzle.");
        }
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check column
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private void updateGrid(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }

    private void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuSolver solver = new SudokuSolver();
            solver.setVisible(true);
        });
    }
}