/*
 * Project Name: Rabbit Counter
 * FileName: PaymentProgress.java
 * Created Date: 2019-09-05
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.Timer;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.counter.application.database.DbController;
import com.counter.application.database.MariaConnector;
import com.counter.application.system.*;
import com.counter.model.Jasper_food_order;
import com.counter.model.Jasper_food_transaction_bag;

import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PaymentProgress extends JFrame {

	private JPanel contentPane;
	
	private int locationID;
	
	private JLabel lblCardNumber;
	private JLabel lblCardCompany;
	private JLabel lblRequestMoney;
	private JLabel lblResponse;
	private JButton btnOK;
	
	private String cardNumber;
	private String cardCompany;
	private int cardCompanyCode;
	private int cardYear;
	private int cardMonth;
	private int cardSignCode;
	private int requestMoney;
	
	private String privacyNumber1;
	private String privacyNumber2;
	private String privacyCardNumber;
	
	private int responseCode;
	
	// 타이머 숫자
	private static int n;
	
	private Function function = null;
	
	// DB
	private PreparedStatement pstmt = null;
    private Statement stmt = null;
	private DbController dbController = null;
	
	// Ip Address
	private InetAddress local;

	/**
	 * Create the frame.
	 */
	public PaymentProgress(int locationID) {
		
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
		
		function = new Function();								// function()
		dbController = new DbController();						// dbController 초기화
		setLocationID(locationID);								// locationID 입력
		
		JLabel lblTitle = new JLabel("결과(Result)");
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 15));
		lblTitle.setBounds(12, 33, 190, 15);
		contentPane.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBackground(new Color(127, 255, 212));
		panel.setBounds(12, 78, 318, 244);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("카드 식별번호(Card Identity Number)");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(12, 16, 260, 24);
		panel.add(lblNewLabel);
		
		lblCardNumber = new JLabel("New label");
		lblCardNumber.setFont(new Font("나눔고딕", Font.BOLD, 14));
		lblCardNumber.setBackground(Color.WHITE);
		lblCardNumber.setBounds(12, 56, 245, 24);
		panel.add(lblCardNumber);
		
		JLabel lblNewLabel_1 = new JLabel("카드 회사(Card Company)");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.BOLD, 14));
		lblNewLabel_1.setBounds(12, 96, 245, 15);
		panel.add(lblNewLabel_1);
		
		lblCardCompany = new JLabel("New label");
		lblCardCompany.setFont(new Font("나눔고딕", Font.BOLD, 14));
		lblCardCompany.setBackground(Color.WHITE);
		lblCardCompany.setBounds(12, 127, 245, 24);
		panel.add(lblCardCompany);
		
		JLabel lblNewLabel_2 = new JLabel("요청금액(Request Money)");
		lblNewLabel_2.setFont(new Font("나눔고딕", Font.BOLD, 14));
		lblNewLabel_2.setBounds(12, 167, 245, 15);
		panel.add(lblNewLabel_2);
		
		lblRequestMoney = new JLabel("New label");
		lblRequestMoney.setFont(new Font("나눔고딕", Font.BOLD, 14));
		lblRequestMoney.setBackground(Color.WHITE);
		lblRequestMoney.setBounds(12, 198, 245, 24);
		panel.add(lblRequestMoney);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(119, 136, 153)));
		panel_1.setBackground(new Color(152, 251, 152));
		panel_1.setBounds(355, 78, 513, 244);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("응답(Response)");
		lblNewLabel_3.setFont(new Font("나눔고딕", Font.BOLD, 14));
		lblNewLabel_3.setBounds(12, 21, 265, 15);
		panel_1.add(lblNewLabel_3);
		
		lblResponse = new JLabel("New label");
		lblResponse.setFont(new Font("나눔고딕", Font.BOLD, 14));
		lblResponse.setBackground(Color.WHITE);
		lblResponse.setBounds(12, 53, 503, 38);
		panel_1.add(lblResponse);
		
		btnOK = new JButton("클릭(O)");
		btnOK.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnOK.setBounds(327, 93, 174, 74);
		panel_1.add(btnOK);
		
	}
	
	
	public void timerPaymentProgress(int code) {
		
		ActionListener listener = timer();
		Timer t = new Timer(2000, listener);
		
		t.start();
		
		for ( int i = 0; i < 10; i++) {
			
			try {
				Thread.sleep(100);
			}
			catch(InterruptedException e) {
				System.out.println( e.getMessage() );
				
			} // end of try to catch
			
		} // end of for
		
		t.stop();
		
		// 페이지 결과 별로 이동
		actionResultPage(code);
		
	}
	
	private void actionResultPage(int code) {
		
		switch(code) {
		
			case 0:
				btnOK.addActionListener(btnFnSuccess());
				break;
				
			default:
				btnOK.addActionListener(btnFnFailover());
				break;
		
		}
		
	}
	
	public ActionListener btnFnSuccess() {
		
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int locationID = getLocationID();
				
				transactionInsert(locationID);
				MemberPointDialog frame = new MemberPointDialog(locationID);
				dispose();
				
				frame.setVisible(true);
				
			}
			
		};
		
		return actionListener;
	}

	public ActionListener btnFnFailover() {
		
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				int locationID = getLocationID();
				int requestMoney = getRequestMoney();
				
				PaymentDialog frame = new PaymentDialog(locationID);
				frame.setRequestMoney(requestMoney);
				dispose();
				
				frame.setVisible(true);
			}
			
		};
		
		return actionListener;
		
	}
	
	public ActionListener timer() {
		
		ActionListener actionListener = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				Date date = new Date();
				n++;
				
				System.out.println(n + "번째 호출:" + date.toString() );
			}
		};
		return actionListener;
	}
	
	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	
	public void setPaymentMethod(String cardNumber, String cardCompany, int cardCompanyCode) {
		this.cardNumber = cardNumber;
		this.cardCompany = cardCompany;
		this.cardCompanyCode = cardCompanyCode;
	}
	
	public void setPaymentIdentity(int cardYear, int cardMonth, int cardSignCode) {
		this.cardYear = cardYear;
		this.cardMonth = cardMonth;
		this.cardSignCode = cardSignCode;
	}
	
	public int getCardCompanyCode() {
		return cardCompanyCode;
	}

	public void setCardCompanyCode(int cardCompanyCode) {
		this.cardCompanyCode = cardCompanyCode;
	}

	public int getCardYear() {
		return cardYear;
	}

	public void setCardYear(int cardYear) {
		this.cardYear = cardYear;
	}

	public int getCardMonth() {
		return cardMonth;
	}

	public void setCardMonth(int cardMonth) {
		this.cardMonth = cardMonth;
	}

	public int getCardSignCode() {
		return cardSignCode;
	}

	public void setCardSignCode(int cardSignCode) {
		this.cardSignCode = cardSignCode;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardCompany() {
		return this.cardCompany;
	}
	
	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}
	
	public String getPrivacyNumber1() {
		return privacyNumber1;
	}

	public void setPrivacyNumber1(String privacyNumber1) {
		this.privacyNumber1 = privacyNumber1;
	}

	public String getPrivacyNumber2() {
		return privacyNumber2;
	}

	public void setPrivacyNumber2(String privacyNumber2) {
		this.privacyNumber2 = privacyNumber2;
	}

	public String getPrivacyCardNumber() {
		return privacyCardNumber;
	}

	public void setPrivacyCardNumber(String privacyCardNumber) {
		this.privacyCardNumber = privacyCardNumber;
	}
	
	public int getRequestMoney() {
		return requestMoney;
	}

	public void setRequestMoney(int requestMoney) {
		this.requestMoney = requestMoney;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void paymentMethodPrivacy() {
		
		String cardNumber = getCardNumber();
		
		String privacyNumber1 = cardNumber.substring(0, 9);
		String privacyNumber2 = cardNumber.substring(15, 19);
		
		String resultNumber = privacyNumber1 + "-****-" + privacyNumber2;
		
		setPrivacyNumber1(privacyNumber1);
		setPrivacyNumber2(privacyNumber2);
		setPrivacyCardNumber(resultNumber);
	}
	
	public void paymentProcess() {
		
		int result = -1;
		String message = "";
		
		this.lblCardCompany.setText( getCardCompany() );
		this.lblCardNumber.setText( getPrivacyCardNumber() );
		this.lblRequestMoney.setText( String.valueOf( getRequestMoney() ) );
		
		// 카드사 인증처리
		result = resultOfcardMessage();
		
		// 메시지 반환
		message = getMessage(result);
		
		this.lblResponse.setText(message);
		
		// 응답 코드 저장
		setResponseCode(result);
		
	}
	
	/*
	 * 	 Subject: (cf)카드 회사 연동 모듈 API
	 *	 Created Date: 2019-09-05
	 *	 Author: Dodo (rabbit.white@daum.net)
	 *	 Description:
	 *   
	 *
	 */
	private int resultOfcardMessage() {
		
		String cardNumber;			// 예시
		String cardCompany;
		int cardCompanyCode;
		int cardYear;
		int cardMonth;
		int cardSignCode;
		int requestMoney;
		
		int code = 0;
		
		return code;
	}

	/*
	 * 	 Subject: (cf)카드 응답 결과
	 *	 Created Date: 2019-09-05
	 *	 Author: Dodo (rabbit.white@daum.net)
	 *	 Description:
	 *   
	 *
	 */
	private String getMessage(int code) {
		
		String message = "";
		
		switch( code ) {
		
		case 0:
			message = "성공(Success)";
			break;
			
		case 1:
			message = "한도초과(Limit Excess)";
			break;
			
		case 2:
			message = "유효하지 못한 결제(Invalid payment)";
			break;
			
		case 3:
			message = "마그네틱 손상(Magnetic damage)";
			break;
			
		case 4:
			message = "";
			break;
	
		default:
			message = "";
			break;
			
		} // end of switch
		
		return message;
	}
	
	/*
	 * 	 Subject: transactionInsert(int locationID)
	 *	 Created Date: 2019-10-06
	 *	 Author: Dodo (rabbit.white@daum.net)
	 *	 Description:
	 *   
	 *
	 */
	public void transactionInsert(int locationID) {

		int count = -1;
		
		ArrayList<Jasper_food_transaction_bag> bagList = null;
		bagList = (ArrayList<Jasper_food_transaction_bag>) getTransactionBagList(locationID);
		Jasper_food_transaction_bag tmpBag = null;
		
		Jasper_food_order order = getJasperFoodOrder(locationID);	// 카운트 값 가져오기
		count = order.getCount() + 1;								// 카운트 증감
		updateCount(order);											// 카운트 업데이트
		insertOrder(order);											// 신규 주문 목록 등록
		
		Iterator<Jasper_food_transaction_bag> bagIter = bagList.iterator();
		
		// Stack 자료구조 원리 적용
		while (bagIter.hasNext()) {
			tmpBag = bagIter.next();
			// System.out.println( tmpBag.getId() ); 출력 태스트
			
			tmpBag.setOrderID(count);
			
			insertTransaction(tmpBag);
			bagIter.remove();
		}
		
		bagList.clear();
		
	}
	
	public Jasper_food_order getJasperFoodOrder(int locationID) {
		
		Jasper_food_order order = new Jasper_food_order();
		order.setId(-1);
		
		StringBuilder sb = new StringBuilder();
		MariaConnector mariaConnector = new MariaConnector();
		
		String query = "";
		
		// SQL 문(Statement SQL)
		query = sb.append("select * from jasper_food_count")
				  .append(" where locationID = ")
				  .append(locationID)
				  .append(" order by id asc").toString();
		
		//System.out.println(query);
		sb = new StringBuilder();
		
		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			this.stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				order.setId( rs.getInt("id"));
				order.setLocationID( rs.getInt("locationID"));
				order.setCount( rs.getInt("count") );
			} // end of while
			
			
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
		
		return order;
		
	}
	
	
	public void insertOrder(Jasper_food_order order) {
		
		int count = order.getCount();
		String query = "";
		
		query = "insert into jasper_food_client_order (locationID, orderID, complete, regidate, ip) " +
				"values(?, ?, ?, ?, ?)";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();

		// 날짜
		java.util.Date date=new java.util.Date();
		
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());

		// IP주소 불러오기
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, order.getLocationID());
			pstmt.setInt(2, count + 1);
			pstmt.setInt(3, 0);
			pstmt.setTimestamp(4, sqlTime);
			pstmt.setString(5, local.getHostAddress());
			
			
			int rowNum = pstmt.executeUpdate();
			
			//System.out.println("야");
			
			if ( rowNum > 0 ){
				System.out.println("삽입성공");
			}
			else{
				System.out.println("실패");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			
			try{
				
				if ( conn != null ){
					conn.close();
				}
				if ( pstmt != null ){
					pstmt.close();
				}
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
			
		} // end of try to catch finally
		
		
	}

	/*
	 * 	 Subject: updateCount(JaspserFood_order)
	 *	 Created Date: 2019-10-06
	 *	 Author: Dodo (rabbit.white@daum.net)
	 *	 Description:
	 *   
	 *
	 */
	public void updateCount(Jasper_food_order order) {
		
		int count = order.getCount();
		String query = "";
		
		query = "update jasper_food_count set count = ? "
				+ "where id = ?";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, count + 1);
			pstmt.setInt(2, order.getId());
			
			int rowNum = pstmt.executeUpdate();
			
			//System.out.println("야");
			
			if ( rowNum > 0 ){
				System.out.println("삽입성공");
			}
			else{
				System.out.println("실패");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			
			try{
				
				if ( conn != null ){
					conn.close();
				}
				if ( pstmt != null ){
					pstmt.close();
				}
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
			
		} // end of try to catch finally
		
		
	}

	/*
	 * 	 Subject: getTransactionBagList(int locationID)
	 *	 Created Date: 2019-10-06
	 *	 Author: Dodo (rabbit.white@daum.net)
	 *	 Description:
	 *   
	 *
	 */
	public Object getTransactionBagList(int locationID) {
		
		ArrayList<Jasper_food_transaction_bag> bagList = null;
		Jasper_food_transaction_bag tmpBag = null;
		StringBuilder sb = new StringBuilder();
		MariaConnector mariaConnector = new MariaConnector();
		
		String query = "";
		
		// SQL 문(Statement SQL)
		query = sb.append("select * from jasper_food_transaction_bag")
				  .append(" where locationId = '")
				  .append(locationID)
				  .append("'")
				  .append(" order by id asc").toString();
		
		//System.out.println(query);
		sb = new StringBuilder();
		
		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			this.stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
				
			bagList = new ArrayList<Jasper_food_transaction_bag>();
			
			while(rs.next()){
				
				tmpBag = new Jasper_food_transaction_bag();
				
				tmpBag.setId(rs.getInt("id"));
				tmpBag.setLocationId(rs.getInt("locationID"));
				tmpBag.setMenuId(rs.getInt("menuId"));
				tmpBag.setMenuName(rs.getString("menuName"));
				tmpBag.setCnt(rs.getInt("cnt"));
				tmpBag.setRegidate(rs.getTimestamp("regidate"));
				tmpBag.setIp(rs.getString("ip"));
				
				bagList.add(tmpBag);
				
			} // end of while
			
			
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
		
		return bagList;
		
	}
	

	/*
	 * 	 Subject: insertTransaction()
	 *	 Created Date: 2019-10-06
	 *	 Author: Dodo (rabbit.white@daum.net)
	 *	 Description:
	 *   
	 *
	 */
	public void insertTransaction(Jasper_food_transaction_bag bag) {
		
		String query = "";
		
		query = "insert into jasper_food_transaction(locationId, menuId, menuName, cnt, orderID, regidate, ip)"
					+ " VALUES(?,?,?,?,?,?,?)";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bag.getLocationId());
			pstmt.setInt(2, bag.getMenuId());
			pstmt.setString(3, bag.getMenuName());
			pstmt.setInt(4, bag.getCnt());
			pstmt.setInt(5, bag.getOrderID());
			pstmt.setTimestamp(6, bag.getRegidate());
			pstmt.setString(7, bag.getIp());
			
			int rowNum = pstmt.executeUpdate();
			
			//System.out.println("야");
			
			if ( rowNum > 0 ){
				System.out.println("삽입성공");
			}
			else{
				System.out.println("실패");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			
			try{
				
				if ( conn != null ){
					conn.close();
				}
				if ( pstmt != null ){
					pstmt.close();
				}
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
			
		} // end of try to catch finally
		
	}
	
}