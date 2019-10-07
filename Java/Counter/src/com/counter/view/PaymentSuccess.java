/*
 * Project Name: Rabbit Counter
 * FileName: PaymentSuccess.java
 * Created Date: 2019-09-03
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
*/
package com.counter.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentSuccess extends JFrame {

	private JPanel contentPane;
	private int locationID;

	/**
	 * Create the frame.
	 */
	public PaymentSuccess(int locationID) {
		
		setLocationID(locationID);
		
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("결제도우미(Payment Helper)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 520);
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
		
		JButton btnClose = new JButton("확인(OK)");
		btnClose.addActionListener(btnFnClose());
		btnClose.setBackground(new Color(245, 222, 179));
		btnClose.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnClose.setBounds(12, 347, 857, 122);
		contentPane.add(btnClose);
		
		JLabel lblTitle = new JLabel("결과(Result)");
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 15));
		lblTitle.setBounds(12, 33, 190, 15);
		contentPane.add(lblTitle);
		
		JLabel lblOrderNum = new JLabel("주문 번호(Order Num)");
		lblOrderNum.setFont(new Font("나눔고딕", Font.BOLD, 15));
		lblOrderNum.setBounds(12, 73, 644, 15);
		contentPane.add(lblOrderNum);
		
		JLabel lblOrderNumMessage = new JLabel("결제 상태(Payment status)");
		lblOrderNumMessage.setFont(new Font("나눔고딕", Font.BOLD, 30));
		lblOrderNumMessage.setBounds(12, 98, 446, 42);
		contentPane.add(lblOrderNumMessage);
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	
	public ActionListener btnFnClose() {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int locationID = getLocationID();
				MainFrm frame = new MainFrm(locationID);
				dispose();
				frame.setVisible(true);
			}
		};
		
		return actionListener;
	}
	
}
