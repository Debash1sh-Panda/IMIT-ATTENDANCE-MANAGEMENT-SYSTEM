package IMIT.MCA_ATTENDANCE;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;



public class SelectBranch extends JFrame implements ActionListener, MouseListener{

    JButton mcabutton, mbabutton, staffbutton, back;
    SelectBranch(){

        setSize(500, 500);
        setLocation(550, 200);
        setLayout(null);

        getContentPane().setBackground(new Color(29,59,85));

        // Add Design in this page

        ImageIcon imageicon = new ImageIcon(ClassLoader.getSystemResource("imgs/Branch.png"));
        Image image = imageicon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon imageicon2 = new ImageIcon(image);
        JLabel jlabel = new JLabel(imageicon2);
        jlabel.setBounds(165, 30, 150, 150);
        add(jlabel);

        mcabutton = new JButton("MCA");
        mcabutton.setBounds(165, 200, 150, 35);
        mcabutton.setBackground(new Color(222,217,238));
        mcabutton.setForeground(new Color(34, 150, 255));
        mcabutton.setBorder(new LineBorder(new Color(29,75,100)));
        mcabutton.setFont(new Font("Tahoma", Font.BOLD, 17));
        mcabutton.addActionListener(this);
        add(mcabutton);

        mbabutton = new JButton("MBA");
        mbabutton.setBounds(165, 250, 150, 35);
        mbabutton.setBackground(new Color(222,217,238));
        mbabutton.setForeground(new Color(34, 150, 255));
        mbabutton.setBorder(new LineBorder(new Color(29,75,100)));
        mbabutton.setFont(new Font("Tahoma", Font.BOLD, 17));
        mbabutton.addActionListener(this);
        add(mbabutton);

        staffbutton = new JButton("STAFF");
        staffbutton.setBounds(165, 300, 150, 35);
        staffbutton.setBackground(new Color(222,217,238));
        staffbutton.setForeground(new Color(34, 150, 255));
        staffbutton.setBorder(new LineBorder(new Color(29,75,100)));
        staffbutton.setFont(new Font("Tahoma", Font.BOLD, 17));
        staffbutton.addActionListener(this);
        add(staffbutton);

        back = new JButton("Back");
        back.setBounds(178, 400, 120, 30);
        back.setBackground(new Color(29,75,100));
        back.setForeground(new Color(34, 150, 255));
        back.setBorder(new LineBorder(new Color(133, 193, 233)));
        back.addActionListener(this);
        back.addMouseListener(this);
        add(back);

        setVisible(true);
    }

    public static void main(String[] args){
        new SelectBranch();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == mcabutton){
            setVisible(false);
            new MCALogin();
        }else if(ae.getSource() == mbabutton){
            setVisible(false);
            new MBALogin();
        }else if(ae.getSource() == staffbutton){
            setVisible(false);
            new STAFFLogin();
        }else if(ae.getSource() == back){
            setVisible(false);
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
        if (e.getSource() == back) {
            back.setBackground(new Color(140, 41, 34)); // Restore original background color on exit
            back.setForeground(new Color(255, 255, 255));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == back) {
            back.setBackground(new Color(29,75,100));
            back.setForeground(new Color(34, 150, 255));
            back.setBorder(new LineBorder(new Color(133, 193, 233)));
        }
    }
}
