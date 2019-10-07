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
		setTitle("���������(Payment Helper)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 520);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/// ������ â ��� ����ϱ�
		// ������ frame ������ ����
		Dimension frameSize = getSize();
		// �ڽ��� windowscreen ������ ����

		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		// ����غ��� �� ����� ��µǴ°� Ȯ���� �� �ִ�.

		System.out.println(frameSize + " " + windowSize);

		// ��: ������width-������width)/2, (������height-������height)/2

		setLocation((windowSize.width - frameSize.width) / 2,
 					(windowSize.height - frameSize.height) / 2);
		
		JButton btnClose = new JButton("Ȯ��(OK)");
		btnClose.addActionListener(btnFnClose());
		btnClose.setBackground(new Color(245, 222, 179));
		btnClose.setFont(new Font("�������", Font.BOLD, 15));
		btnClose.setBounds(12, 347, 857, 122);
		contentPane.add(btnClose);
		
		JLabel lblTitle = new JLabel("���(Result)");
		lblTitle.setFont(new Font("�������", Font.BOLD, 15));
		lblTitle.setBounds(12, 33, 190, 15);
		contentPane.add(lblTitle);
		
		JLabel lblOrderNum = new JLabel("�ֹ� ��ȣ(Order Num)");
		lblOrderNum.setFont(new Font("�������", Font.BOLD, 15));
		lblOrderNum.setBounds(12, 73, 644, 15);
		contentPane.add(lblOrderNum);
		
		JLabel lblOrderNumMessage = new JLabel("���� ����(Payment status)");
		lblOrderNumMessage.setFont(new Font("�������", Font.BOLD, 30));
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
