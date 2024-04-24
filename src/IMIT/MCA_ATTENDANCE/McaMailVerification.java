package IMIT.MCA_ATTENDANCE;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class McaMailVerification extends JFrame implements ActionListener, MouseListener, KeyListener{

    JTextField tfusername, tfotp;
    JButton verify, back, get;
    String genOtp;

    McaMailVerification(){

        setSize(900, 400);
        setLocation(350, 200);
        setLayout(null);

        getContentPane().setBackground(new Color(29,59,85));

        JPanel jpanel = new JPanel();// Used for divide page into multiple parts
        jpanel.setBackground(new Color(29,75,100));
        jpanel.setBounds(0, 0, 400, 400);
        add(jpanel);

        ImageIcon imageicon = new ImageIcon(ClassLoader.getSystemResource("imgs/McaLoading.jpg"));
        Image image = imageicon.getImage().getScaledInstance(375, 375, Image.SCALE_DEFAULT);
        ImageIcon imageicon2 = new ImageIcon(image);
        JLabel jlabel = new JLabel(imageicon2);
        jlabel.setBounds(90, 120, 200, 200);
        jpanel.add(jlabel);

        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(null);
        jpanel2.setBounds(400, 30, 450, 300);
        add(jpanel2);

        //Creating Username
        JLabel username = new JLabel("Username"); // The main uses of JLabel is for creating text formate in frame
        username.setBounds(60, 20, 100, 25);
        username.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        username.setForeground(new Color(29,75,100));
        jpanel2.add(username);

        tfusername = new JTextField();
        tfusername.setBounds(60, 60, 300, 30);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        tfusername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfotp.requestFocus(); // Move focus to password field when Enter is pressed in username field
            }
        });
        jpanel2.add(tfusername);

        //Creating Password
        JLabel otp = new JLabel("Enter OTP");
        otp.setBounds(60, 110, 100, 25);
        otp.setForeground(new Color(29,75,100));
        otp.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        jpanel2.add(otp);

        tfotp = new JTextField();
        tfotp.setBounds(60, 150, 300, 30);
        tfotp.setBorder(BorderFactory.createEmptyBorder());
        tfotp.addKeyListener(this);
        jpanel2.add(tfotp);

        //Creating verify button
        verify = new JButton("Verify");
        verify.setBounds(60, 220, 130, 30);
        verify.setBackground(new Color(29,75,100));
        verify.setForeground(Color.WHITE);
        verify.setBorder(new LineBorder(new Color(29,75,100)));
        verify.addActionListener(this);
        verify.addMouseListener(this);
        jpanel2.add(verify);

        //Creating back button
        back = new JButton("Back");
        back.setBounds(230, 220, 130, 30);
        back.setBackground(new Color(29,75,100));
        back.setForeground(Color.WHITE);
        back.setBorder(new LineBorder(new Color(29,75,100)));
        back.addActionListener(this);
        back.addMouseListener(this);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jpanel2.add(back);

        get = new JButton("Get OTP");
        get.setBounds(250, 110, 90, 25);
        get.setBackground(new Color(200,200,200));
        get.setForeground(new Color(29,75,100));
        get.setBorder(new LineBorder(new Color(200,200,200)));
        get.setCursor(new Cursor(Cursor.HAND_CURSOR));
        get.addActionListener(this);
        get.addMouseListener(this);
        jpanel2.add(get);

        setVisible(true);
    }



    public static void main(String[] args){
        new McaMailVerification();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == verify){
            //Verification of the otp
            String verifiedOTP = genOtp;
            String otp = tfotp.getText();
            if(otp.equals(verifiedOTP)){
                JOptionPane.showMessageDialog(null, "Verified Successfully.");
                NewPasswordDialog NewPasswordDialog = new NewPasswordDialog(tfusername.getText());
                NewPasswordDialog.setVisible(true);

                // next class ---
            }else{
                JOptionPane.showMessageDialog(null, "Access denied.");
            }


        }else if(ae.getSource() == get){
            Conn c = new Conn();
            String email = null;
            String name = tfusername.getText();
            String query = "select email_id from staff where username = '" + name +"'";
            try{
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()) {
                    email = rs.getString("email_id");
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            OtpGenerator og = new OtpGenerator();
            genOtp = og.generateOTP();
            try {
                og.setupServerProperties();
                og.draftEmail(genOtp, email);
                og.sendEmail();         //Sending mail to the user email
            }catch(Exception e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "OTP has been sent to your Email.");
        }
        else if(ae.getSource() == back){
            setVisible(false);
            new MCALogin();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == verify) {
            verify.setBackground(new Color(40, 108, 40)); // Restore original background color on exit
            verify.setForeground(new Color(255, 255, 255));
        }
        if (e.getSource() == back) {
            back.setBackground(new Color(140, 41, 34)); // Restore original background color on exit
            back.setForeground(new Color(255, 255, 255));
        }
        if (e.getSource() == get) {
            get.setBackground(new Color(169, 150, 52)); // Restore original background color on exit
            get.setForeground(new Color(255, 255, 255));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == verify) {
            verify.setBackground(new Color(29, 75, 100)); // Restore original background color on exit
            verify.setForeground(new Color(255, 255, 255));
        }
        if (e.getSource() == back) {
            back.setBackground(new Color(29, 75, 100)); // Restore original background color on exit
            back.setForeground(new Color(255, 255, 255));
        }
        if (e.getSource() == get) {
            get.setBackground(new Color(200,200,200));
            get.setForeground(new Color(29,75,100));
            get.setBorder(new LineBorder(new Color(200,200,200)));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(e.getSource() == tfotp){
                // Perform verification when Enter is pressed
                verify.doClick(); // Simulate a button click
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}

class NewPasswordDialog extends JDialog implements ActionListener {
    JLabel userIdLabel;
    JTextField userIdField;
    JPasswordField newPasswordField;
    JPasswordField confirmPasswordField;
    JButton okButton;
    JButton cancelButton;

    NewPasswordDialog(String userId) {
        setTitle("Set New Password");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField(userId);
        newPasswordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        add(userIdLabel);
        add(userIdField);
        add(new JLabel("New Password:"));
        add(newPasswordField);
        add(new JLabel("Confirm Password:"));
        add(confirmPasswordField);
        add(okButton);
        add(cancelButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            // Handle setting new password here
            char[] newPassword = newPasswordField.getPassword();
            char[] confirmPassword = confirmPasswordField.getPassword();
            String userid = userIdField.getText();

            if (isEqual(newPassword, confirmPassword)) {

                try {
                    Conn c = new Conn();
                    String query2 = "UPDATE staff SET password = ? WHERE username = ?";

                    PreparedStatement pstmt = c.c.prepareStatement(query2);
                    pstmt.setString(1, new String(newPassword)); // Convert char[] to String
                    pstmt.setString(2, userid);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Password updated successfully!");
                        setVisible(false);
                        new MCALogin();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                }catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating password: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(this, "Passwords do not match. Please re-enter.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    private boolean isEqual ( char[] array1, char[] array2){
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
}