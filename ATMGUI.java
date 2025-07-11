import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// BankAccount class to manage balance
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}

// ATM GUI class
public class ATMGUI extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JTextArea outputArea;
    private JButton checkBalanceBtn, depositBtn, withdrawBtn, clearBtn;

    public ATMGUI() {
        account = new BankAccount(5000); // initial balance

        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen

        // Layout setup
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Enter Amount: ₹"));
        amountField = new JTextField(10);
        inputPanel.add(amountField);

        // Buttons
        checkBalanceBtn = new JButton("Check Balance");
        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        clearBtn = new JButton("Clear");

        checkBalanceBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(checkBalanceBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(clearBtn);

        // Output area
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add to frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        String inputText = amountField.getText().trim();
        double amount = 0;

        if (!inputText.isEmpty()) {
            try {
                amount = Double.parseDouble(inputText);
                if (amount < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                outputArea.setText("Please enter a valid positive number.\n");
                return;
            }
        }

        if (e.getSource() == checkBalanceBtn) {
            outputArea.setText("Your current balance is: ₹" + account.getBalance() + "\n");
        } else if (e.getSource() == depositBtn) {
            if (amount > 0) {
                account.deposit(amount);
                outputArea.setText("Deposited ₹" + amount + "\n");
            } else {
                outputArea.setText("Enter a valid amount to deposit.\n");
            }
        } else if (e.getSource() == withdrawBtn) {
            if (amount > 0) {
                if (account.withdraw(amount)) {
                    outputArea.setText("Withdrew ₹" + amount + "\n");
                } else {
                    outputArea.setText("Insufficient balance for withdrawal.\n");
                }
            } else {
                outputArea.setText("Enter a valid amount to withdraw.\n");
            }
        } else if (e.getSource() == clearBtn) {
            amountField.setText("");
            outputArea.setText("");
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMGUI());
    }
}
