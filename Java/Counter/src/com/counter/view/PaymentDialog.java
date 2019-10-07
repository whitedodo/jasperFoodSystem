/*
 * Project Name: Rabbit Counter
 * FileName: PaymentDialog.java
 * Created Date: 2019-09-03
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 19-09-05 / Dodo / 카드 기능 추가
 * 
*/
package com.counter.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentDialog extends JFrame {

	private int locationID;
	private int requestMoney;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PaymentDialog(int locationID) {
		
		setLocationID(locationID);
		setResizable(false);
		setTitle("결제도우미(Payment Helper)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
		
		JLabel lblTitle = new JLabel("카드를 넣어주세요.(Please insert your card.)");
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 17));
		lblTitle.setBounds(12, 30, 452, 33);
		contentPane.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(144, 238, 144));
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 75, 855, 391);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnPrevious = new JButton("이전(Previous)");
		btnPrevious.addActionListener(btnFnPrevious());
		btnPrevious.setFont(new Font("나눔고딕", Font.BOLD, 20));
		btnPrevious.setBackground(new Color(0, 206, 209));
		btnPrevious.setBounds(422, 255, 200, 126);
		panel.add(btnPrevious);
		
		JButton btnCard = new JButton("카드 결제(Card)");
		btnCard.addActionListener(btnFnPaid());
		btnCard.setBackground(new Color(0, 206, 209));
		btnCard.setFont(new Font("나눔고딕", Font.BOLD, 20));
		btnCard.setBounds(634, 255, 200, 126);
		panel.add(btnCard);
	}
	
	public int getLocationID() {
		return this.locationID;
	}
	
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	
	public int getRequestMoney() {
		return requestMoney;
	}

	public void setRequestMoney(int requestMoney) {
		this.requestMoney = requestMoney;
	}

	public ActionListener btnFnPrevious() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int locationID = getLocationID();
				dispose();
				MainFrm frame = new MainFrm(locationID);
				frame.requiryBag();
				frame.setVisible(true);
				
			}
		};
		
		return actionListener;
	}
	
	public ActionListener btnFnPaid() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int locationID = getLocationID();
				int cardCompanyCode = 11;
				String cardNumber = "2222-1111-3333-4444";
				String cardCompany = "Dodo";
				int cardYear = 2033;
				int cardMonth = 11;
				int cardSignCode = 111;
				int requestMoney = 10000;
				int responseCode = -1;
				
				PaymentProgress frame = new PaymentProgress(locationID);
				dispose();
				
				frame.setPaymentMethod(cardNumber, cardCompany, cardCompanyCode);
				frame.setPaymentIdentity(cardYear, cardMonth, cardSignCode);
				frame.paymentMethodPrivacy();
				frame.setRequestMoney(requestMoney);
				frame.paymentProcess();
				responseCode = frame.getResponseCode();
				
				frame.setVisible(true);
				frame.timerPaymentProgress(responseCode);
				
			}
		};
		
		return actionListener;
	}
	
}
