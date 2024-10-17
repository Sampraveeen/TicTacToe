import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeSwing extends JFrame implements ActionListener {
    private char[][] board = new char[3][3];
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel;
    private JButton resetButton;
    private int xWins = 0;
    private int oWins = 0;
    private JLabel scoreLabel;

    public TicTacToeSwing() {
        setTitle("Tic Tac Toe Challenge");
        setSize(400, 500);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                gamePanel.add(buttons[i][j]);
                board[i][j] = ' ';
            }
        }
        add(gamePanel, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(3, 1));
        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusPanel.add(statusLabel);

        scoreLabel = new JLabel("Score - X: 0, O: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusPanel.add(scoreLabel);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        statusPanel.add(resetButton);

        add(statusPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clickedButton == buttons[i][j]) {
                    if (board[i][j] == ' ') {
                        board[i][j] = currentPlayer;
                        buttons[i][j].setText(String.valueOf(currentPlayer));
                        if (checkWin()) {
                            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                            if (currentPlayer == 'X') {
                                xWins++;
                            } else {
                                oWins++;
                            }
                            updateScore();
                            resetBoard();
                        } else if (isBoardFull()) {
                            JOptionPane.showMessageDialog(this, "It's a draw!");
                            resetBoard();
                        } else {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            statusLabel.setText("Player " + currentPlayer + "'s turn");
                        }
                    }
                }
            }
        }
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetBoard() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText("");
            }
        }
        statusLabel.setText("Player X's turn");
    }

    public void updateScore() {
        scoreLabel.setText("Score - X: " + xWins + ", O: " + oWins);
    }

    public static void main(String[] args) {
        new TicTacToeSwing();
    }
}
