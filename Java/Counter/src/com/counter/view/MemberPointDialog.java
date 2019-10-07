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

import com.counter.application.database.DbController;
import com.counter.application.database.MariaConnector;
import com.counter.application.system.Function;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MemberPointDialog extends JFrame {

	private int locationID;
	private int requestMoney;
	private JPanel contentPane;
	private JTextField txtMemberID;
	private JLabel lblMessage_1;
	private JLabel lblMessage_2;

	private Function function = null;
	private PreparedStatement pstmt = null;
    private Statement stmt = null;
	private DbController dbController = null;

	/**
	 * Create the frame.
	 */
	public MemberPointDialog(int locationID) {
		
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
		
		function = new Function();								// function()
		dbController = new DbController();						// dbController 초기화
		
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
		
		JLabel lblTitle = new JLabel("포인트 적립(Earn points)");
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 17));
		lblTitle.setBounds(12, 30, 452, 33);
		contentPane.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(144, 238, 144));
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 75, 855, 391);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnPointIncreaseOK = new JButton("적립(Point earned)");
		btnPointIncreaseOK.addActionListener(btnFnPointIncreaseOK());
		btnPointIncreaseOK.setFont(new Font("나눔고딕", Font.BOLD, 20));
		btnPointIncreaseOK.setBackground(new Color(0, 206, 209));
		btnPointIncreaseOK.setBounds(239, 305, 296, 76);
		panel.add(btnPointIncreaseOK);
		
		JButton btnSuccessOK = new JButton("미적립(No points earned)");
		btnSuccessOK.addActionListener(btnFnSuccessOK());
		btnSuccessOK.setBackground(new Color(0, 206, 209));
		btnSuccessOK.setFont(new Font("나눔고딕", Font.BOLD, 20));
		btnSuccessOK.setBounds(547, 305, 296, 76);
		panel.add(btnSuccessOK);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 310, 180);
		panel.add(panel_1);
		
		txtMemberID = new JTextField();
		txtMemberID.setColumns(10);
		txtMemberID.setBounds(12, 10, 289, 34);
		panel_1.add(txtMemberID);
		
		JButton btnSearch = new JButton("검색(Search)");
		btnSearch.addActionListener(btnFnSearch());
		btnSearch.setFont(new Font("나눔고딕", Font.BOLD, 14));
		btnSearch.setBounds(12, 47, 289, 34);
		panel_1.add(btnSearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBounds(12, 91, 289, 79);
		panel_1.add(panel_2);
		
		JLabel label = new JLabel("회원 인증(Authorize Membership)");
		label.setFont(new Font("나눔고딕", Font.BOLD, 12));
		label.setBounds(12, 10, 235, 15);
		panel_2.add(label);
		
		lblMessage_1 = new JLabel("사용자 인증을 수행하세요.");
		lblMessage_1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		lblMessage_1.setBounds(12, 35, 265, 15);
		panel_2.add(lblMessage_1);
		
		lblMessage_2 = new JLabel("Please perform user authentication.");
		lblMessage_2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		lblMessage_2.setBounds(12, 54, 265, 15);
		panel_2.add(lblMessage_2);
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

	public ActionListener btnFnSearch() {
		
		ActionListener actionListener = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				boolean result = false;
				
				String memberID = txtMemberID.getText();
				result = idSearch(memberID);
				
				if ( result ) {
					lblMessage_1.setText(memberID);
					lblMessage_2.setText("인증성공(Authentication Success)");
				}
				else {
					lblMessage_1.setText("사용자 인증을 수행하세요.");
					lblMessage_2.setText("Please perform user authentication.");
				}
				
			}
			
		};
		
		return actionListener;
	}
	
	public ActionListener btnFnPointIncreaseOK() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		};
		
		return actionListener;
	}
	
	public ActionListener btnFnSuccessOK() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int locationID = getLocationID();
				dispose();
				PaymentSuccess frame = new PaymentSuccess(locationID);
				frame.setVisible(true);
				
			}
		};
		
		return actionListener;
	}
	
	private boolean idSearch(String memberID) {
		
		boolean status = false;
		StringBuilder sb = new StringBuilder();
		MariaConnector mariaConnector = new MariaConnector();
		
		String query = "";
		
		// SQL 문(Statement SQL)
		query = sb.append("select * from jasper_member")
				.append(" where email = '")
				.append(memberID)
				.append( "'")
				.append(" order by id").toString();
		
		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			this.stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
				
			while(rs.next()){
				
				if ( memberID.equals(rs.getString("email"))) {
					status = true;
				}
				
			}
			
			if ( pstmt != null ) {
				pstmt.close();
				pstmt = null;
			}
			
			if ( stmt != null ) {
				stmt.close();
				stmt = null;
			}
			
			if ( rs != null ) {
				rs.close();
				rs = null;
			}
			
			if ( conn != null ) {
				conn.close();
				conn = null;
			}
						
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} // end of while
		
		return status;
		
	}
	
}
