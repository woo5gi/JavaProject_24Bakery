package com.kitri.login.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kitri.admin.project.Pro_Admin;
import com.kitri.bakery.digain.UserMain;
import com.kitri.bakery.domain.BakeryBean;
import com.kitri.login.bean.memberBean;
import com.kitri.login.db.memberDao;
import com.kitri.pointAdd.point_Add;


public class loginView extends JFrame implements ActionListener {
    public static String aaa;



    public JTextField idTf = new JTextField();
    JTextField passwdTf = new JTextField();
    JButton join = new JButton("JOIN");
    JButton ok = new JButton("LOGIN");
    JLabel notiLb = new JLabel("");
    

    
    
    private final JLabel lblNewLabel_2 = new JLabel("login");

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    loginView window = new loginView();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }

    public loginView() {
        getContentPane().setBackground(Color.WHITE);
        initialize();
    }

    private void initialize() {

        this.setBounds(100, 100, 450, 297);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("����", Font.BOLD, 12));
        lblNewLabel.setBounds(74, 98, 74, 15);
        this.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setFont(new Font("����", Font.BOLD, 12));
        lblNewLabel_1.setBounds(50, 154, 74, 15);
        this.getContentPane().add(lblNewLabel_1);

        idTf.setBounds(136, 95, 148, 21);
        this.getContentPane().add(idTf);
        idTf.setColumns(10);

        passwdTf.setBounds(136, 151, 148, 21);
        this.getContentPane().add(passwdTf);
        passwdTf.setColumns(10);
        join.setForeground(new Color(255, 255, 204));
        join.setBorderPainted(false);
        join.setBackground(new Color(102, 51, 0));
        join.setFont(new Font("���� ���", Font.PLAIN, 12));

        join.setBounds(296, 94, 97, 23);
        this.getContentPane().add(join);
        ok.setForeground(new Color(255, 255, 204));
        ok.setBorderPainted(false);
        ok.setBackground(new Color(102, 51, 0));
        ok.setFont(new Font("���� ���", Font.PLAIN, 12));

        
        ok.setBounds(296, 150, 97, 23);
        this.getContentPane().add(ok);
        lblNewLabel_2.setFont(new Font("���� ���", Font.BOLD, 24));
        lblNewLabel_2.setBounds(168, 25, 92, 43);
        
        getContentPane().add(lblNewLabel_2);
        
        
        notiLb.setBounds(168, 198, 162, 15);
        getContentPane().add(notiLb);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\KITRI\\Desktop\\Bakery\\121212121.jpg"));
        lblNewLabel_3.setBounds(0, 0, 434, 259);
        getContentPane().add(lblNewLabel_3);
        join.addActionListener(this);
        ok.addActionListener(this);
        passwdTf.addActionListener(this);

        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();
    
        
        if (ob == join) {
            //ȸ������ ��ư ������ joinView�� �Ѿ
            joinView jv = new joinView();
            jv.setVisible(true);
            this.dispose();
            } else if (ob == ok || ob == passwdTf) {
            //ok��ư ������ ���̵�� ��� �����ؼ� �α��� ���� Ȥ�� ���� ������
            memberDao md = new memberDao();
            String no = idTf.getText();
            String pwd = passwdTf.getText();
            memberBean no2 = md.getEachInfo2(no);
            if(no.equals("admin")){
                if(pwd.equals("1234")) {
                    Pro_Admin pa = new Pro_Admin();
                    pa.setVisible(true);
                    this.dispose();
                }else {notiLb.setText("��й�ȣ�� Ʋ���ϴ�");}
            }else {
            if (no.equals(no2.getMember_id())) {
                //DB�� �ؽ�Ʈ�ʵ� ���̵� ���� = >������ ��� Ȯ��
                if (pwd.equals(no2.getPassword())) {
                    notiLb.setText("�α��μ���");
                    BakeryBean.member_id = idTf.getText();
                    UserMain um = new UserMain();
                    memberBean mb = new memberBean();
                    mb = idGet();
                    aaa = idTf.getText();
                    System.out.println(mb.setMember_id(idTf.getText()));
                    
                    this.dispose();
                    um.setVisible(true);
                } else {
                    notiLb.setText("��й�ȣ�� Ʋ���ϴ�");
                    
                }
            } else {
                //������ ���̵� ���Ʋ�� ���
                notiLb.setText("���̵� or ��� Ʋ��");
            }
        }//��ư elseif ��������
//        }else if(ob == pointBt) {
//            memberBean mb = new memberBean();
//            point_Add pa = new point_Add();
//            mb = idGet();
//            aaa = idTf.getText();
//            pa.setVisible(true);
//            this.dispose();
//        }
        
            }
    }// actionPerformed() ��������
    
    public memberBean idGet() {
        memberBean mb = new memberBean();
        mb.setMember_id(idTf.getText());
        return mb;
    }
}//loginView ��������