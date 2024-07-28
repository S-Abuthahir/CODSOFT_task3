import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ATM implements ActionListener {
    private BankAccount account;
    JFrame main, depositFrame, withdrawFrame, balanceFrame, current;
    Font fontText = new Font("Serif", Font.BOLD, 23);
    Font fontNum = new Font(Font.MONOSPACED, Font.PLAIN, 20);

    public void deposit() {

        depositFrame = new JFrame("Deposit");
        current = depositFrame;
        JLabel lb = new JLabel("Enter the amount: ");
        lb.setBounds(50, 100, 200, 50);
        lb.setFont(fontText);
        JTextField amount = new JTextField();
        amount.setBounds(250, 100, 200, 50);
        amount.setFont(fontNum);
        JButton submit = new JButton("Submit");
        submit.setFont(fontNum);
        submit.setBackground(Color.lightGray);
        submit.setBounds(200, 200, 200, 50);
        JButton prev = new JButton("Previous page");
        prev.setFont(fontNum);
        prev.setBackground(Color.lightGray);
        prev.setBounds(150, 305, 250, 50);
        prev.addActionListener(this);
        submit.addActionListener((ActionEvent ae) -> {
            account.accountbalance += Integer.parseInt(amount.getText());
            JOptionPane.showMessageDialog(null, "Amount ₹" + amount.getText() + " deposited successfully", "Title", JOptionPane.INFORMATION_MESSAGE);
            prev.doClick();
        });

        depositFrame.add(lb);
        depositFrame.add(amount);
        depositFrame.add(submit);
        depositFrame.add(prev);
        depositFrame.setLayout(null);
        depositFrame.setSize(500, 600);
        depositFrame.setVisible(true);
        depositFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void withdraw() {

        withdrawFrame = new JFrame("Withdraw");
        current = withdrawFrame;
        JLabel lb = new JLabel("Enter the amount: ");
        lb.setBounds(50, 100, 200, 50);
        lb.setFont(fontText);
        JTextField amount = new JTextField();
        amount.setBounds(250, 100, 200, 50);
        amount.setFont(fontNum);
        JButton submit = new JButton("Submit");
        submit.setFont(fontNum);
        submit.setBackground(Color.lightGray);
        submit.setBounds(200, 200, 200, 50);
        JButton prev = new JButton("Previous page");
        prev.setBounds(150, 305, 250, 50);
        prev.setFont(fontNum);
        prev.setBackground(Color.lightGray);
        prev.addActionListener(this);
        submit.addActionListener((ActionEvent ae) -> {
            int amt = Integer.parseInt(amount.getText());
            if (account.accountbalance - amt < 0) {
                JOptionPane.showMessageDialog(null, "Insufficient balance", "Title", JOptionPane.ERROR_MESSAGE);
                amount.setText("");
            } else {
                account.accountbalance -= amt;
                JOptionPane.showMessageDialog(null, "Amount ₹" + amount.getText() + " withdrawn successfully", "Title", JOptionPane.INFORMATION_MESSAGE);
                prev.doClick();

            }

        });


        withdrawFrame.add(lb);
        withdrawFrame.add(amount);
        withdrawFrame.add(submit);
        withdrawFrame.add(prev);
        withdrawFrame.setLayout(null);
        withdrawFrame.setSize(500, 600);
        withdrawFrame.setVisible(true);
        withdrawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void checkBalance() {

        balanceFrame = new JFrame("Balance Inquiry");
        current = balanceFrame;
        JLabel show1 = new JLabel();
        show1.setFont(fontText);
        show1.setText("Balance: ");
        show1.setBounds(150, 200, 100, 50);
        JLabel show2 = new JLabel();
        show2.setFont(fontNum);
        show2.setText("$" + account.accountbalance);
        show2.setBounds(250, 200, 200, 50);
        JButton prev = new JButton("Previous page");
        prev.setBounds(150, 300, 200, 50);
        prev.setFont(fontNum);
        prev.setBackground(Color.lightGray);
        prev.addActionListener(this);
        balanceFrame.add(show1);
        balanceFrame.add(show2);
        balanceFrame.add(prev);
        balanceFrame.setLayout(null);
        balanceFrame.setSize(500, 600);
        balanceFrame.setVisible(true);
        balanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mainPage(BankAccount account) {
        main = new JFrame("ATM Interface");
        this.account = account;
        JLabel welcome = new JLabel("Welcome " + account.name + " !!!");
        welcome.setFont(fontText);
        welcome.setBounds(50, 50, 400, 50);
        JLabel accno = new JLabel("Acc. no: " + account.account_number);
        accno.setFont(fontNum);
        accno.setBounds(50, 100, 400, 50);
        JButton depositb = new JButton("Deposit");
        depositb.setFont(fontNum);
        depositb.setBounds(50, 200, 200, 50);
        depositb.setBackground(Color.lightGray);
        depositb.addActionListener(this);
        JButton withdrawb = new JButton("Withdraw");
        withdrawb.setBounds(50, 300, 200, 50);
        withdrawb.setBackground(Color.lightGray);
        withdrawb.setFont(fontNum);
        withdrawb.addActionListener(this);
        JButton balanceb = new JButton("Balance");
        balanceb.setBounds(50, 400, 200, 50);
        balanceb.setFont(fontNum);
        balanceb.setBackground(Color.lightGray);
        balanceb.addActionListener(this);
        main.add(welcome);
        main.add(accno);
        main.add(depositb);
        main.add(withdrawb);
        main.add(balanceb);
        main.setLayout(null);
        main.setSize(500, 600);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Deposit")) {
            main.setVisible(false);
            deposit();
        } else if (e.getActionCommand().equals("Withdraw")) {
            main.setVisible(false);
            withdraw();
        } else if (e.getActionCommand().equals("Balance")) {
            main.setVisible(false);
            checkBalance();
        } else if (e.getActionCommand().equals("Previous page")) {
            current.setVisible(false);
            main.setVisible(true);
        }
    }
}

class BankAccount {
    protected long account_number;
    protected int accountbalance;
    protected String name;

    public BankAccount(long account_number, int accountbalance, String name) {
        this.account_number = account_number;
        this.accountbalance = accountbalance;
        this.name = name;
    }

}

public class Task3 {
    public static void main(String[] args) {
        ATM atm = new ATM();
        BankAccount User1 = new BankAccount(863484984L, 50_000, "User1");
        atm.mainPage(User1);
    }
}
