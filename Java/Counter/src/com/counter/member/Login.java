/*
 * Project Name: Rabbit Counter
 * FileName: Login.java
 * Created Date: 2019-09-03
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
*/
package com.counter.member;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import com.counter.application.database.*;
import com.counter.application.sso.*;
import com.counter.model.*;
import com.counter.view.*;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane = null;
	private JTextField txtEmail = null;
	private JPasswordField txtPasswd = null;
	private PreparedStatement pstmt = null;
	private static MainFrm mainFrm = null;

	/**
	 * Create the frame.
	 */
	public Login() {
		
		setResizable(false);
		setTitle("Counter - Login(카운터 - 로그인)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 220);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/// 프레임 창 가운데 출력하기
		// 설정한 frame 사이즈 측정
		Dimension frameSize = getSize();
		// 자신의 windowscreen 사이즈 측정

		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 출력해보면 두 사이즈가 출력되는걸 확인할 수 있다.

		System.out.println(frameSize + " " + windowSize);

		// 식: 윈도우width-프레임width)/2, (윈도우height-프레임height)/2

		setLocation((windowSize.width - frameSize.width) / 2,
 					(windowSize.height - frameSize.height) / 2);
		
		JLabel lblNewLabel = new JLabel("카운터 - 로그인(Counter - Login)");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 301, 25);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 45, 410, 62);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEmail = new JLabel("이메일(E-mail)");
		lblEmail.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		lblEmail.setBounds(12, 10, 94, 15);
		panel.add(lblEmail);
		
		JLabel lblPasswd = new JLabel("비밀번호(Password)");
		lblPasswd.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		lblPasswd.setBounds(12, 35, 126, 15);
		panel.add(lblPasswd);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		txtEmail.setBounds(135, 7, 263, 21);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPasswd = new JPasswordField();
		txtPasswd.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		txtPasswd.setBounds(135, 32, 263, 21);
		panel.add(txtPasswd);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(12, 117, 410, 54);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnLogin = new JButton("로그인(L)");
		btnLogin.addActionListener( btnLoginClicked() );
		btnLogin.setBackground(new Color(72, 209, 204));
		btnLogin.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_1.add(btnLogin);
		
		JButton btnClose = new JButton("닫기(C)");
		btnClose.setBackground(new Color(72, 209, 204));
		btnClose.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_1.add(btnClose);
		
		
	}
	
	public ActionListener btnLoginClicked() {
		
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				
				boolean state = true;
				MemberShip sso = new MemberShip();
				Jasper_Member member = new Jasper_Member();
				
				int memberID = -1;
				int locationID = -1;
				
				String email = txtEmail.getText();
				String passwd = txtPasswd.getText();
				
				ResultSet rs = null;
				
				// 이메일
				if ( email.isEmpty() && state == true ){
					JOptionPane.showMessageDialog(null, "이메일을 입력하세요.\n(Please enter your email.)", "알림(Alert)",
														JOptionPane.INFORMATION_MESSAGE);
					
					state = false;
				}
				
				// 비밀번호
				if ( passwd.isEmpty() && state == true ){
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.\n(Please enter a password)", "알림(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
					
					state = false;
				}
				
				member = sso.getMember(email, passwd);
				memberID = member.getId();
				
				if ( memberID == -1 && state == true ){

					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.\n(Please enter a password)", "알림(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
					state = false;
				}
				else {
					locationID = member.getLocationId();
				}
				
				// 폼 열기
				if ( state == true ){

					dispose();
					
					// 폼 생성
					mainFrm = new MainFrm(locationID);
					mainFrm.setVisible(true);
					setVisible (false);
				}
				
			}
		};
		
		return action;
	}
	
}
