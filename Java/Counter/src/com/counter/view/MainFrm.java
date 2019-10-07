/*
 * Project Name: Rabbit Counter
 * FileName: Main.java
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.counter.application.database.*;
import com.counter.application.system.*;
import com.counter.controller.*;
import com.counter.model.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTable tblBag;
	private JTable tblItemList;
	private JLabel lblItemList;
	private JLabel lblID;
	private JLabel lblBarcode;
	private JPanel panel_bag;
	private JPanel panel_itemList;
	private JLabel lblitemname;
	private JTextField txtItemName;
	private JTextField txtCount;
	private JLabel lblcount;
	
	private JButton btnSearch;
	private JButton btnAdd;
	private JLabel lblBag;
	private JScrollPane scrollPane_bag;
	
	private JButton btnRemove;
	private JButton btnReset;
	private JButton btnPaid; 
	
	private JLabel lblLocationName;
	private JTextField txtSumOfMoney;														// �հ�
	private String colNamesBag[] = {"��ȣ", "�ĺ��ڵ�", "�׸��", "����", "�հ�"};					// ���̺� �÷� ����
	
	private DefaultTableModel modelBag = new DefaultTableModel(colNamesBag, 0){  			//�� ���� ���ϰ� �ϴ� �κ�
	public boolean isCellEditable(int row, int column){
		return false;
	}};		// ���̺� ������ �� ��ü ����
	
	private String colNamesItemList[] = {"��ȣ", "�ĺ��ڵ�", "�з�", "�׸��", "����", "�ܰ�", "����", "����"};				// ���̺� �÷� ����
	
	private DefaultTableModel modelItemList = new DefaultTableModel(colNamesItemList, 0){   //�� ���� ���ϰ� �ϴ� �κ�
	public boolean isCellEditable(int row, int column){
		return false;
	}};		// ���̺� ������ �� ��ü ����

	private Function function = null;

	private PreparedStatement pstmt = null;
    private Statement stmt = null;
	private DbController dbController = null;
	private InetAddress local;
	
	private int locationID;								// ��ġ ���
	private JTextField txtPrice;
	private JTextField txtTotalPrice;
	private JButton btnItemListHide;
	
	/**
	 * Create the frame.
	 */
	public MainFrm(int locationID) {
		
		setLocationID(locationID);								// ���� ID ����
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("ī���� - �ֹ�������(Counter - Order Manager)");
		setBounds(100, 100, 900, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		function = new Function();								// function()
		dbController = new DbController();						// dbController �ʱ�ȭ

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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 255, 153));
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 51, 847, 92);
		contentPane.add(panel);
		panel.setLayout(null);
				
		lblLocationName = new JLabel("������");
		lblLocationName.setFont(new Font("�������", Font.BOLD, 20));
		lblLocationName.setBounds(12, 10, 847, 31);
		contentPane.add(lblLocationName);
		
		lblID = new JLabel("New label");
		lblID.setFont(new Font("�������", Font.PLAIN, 12));
		lblID.setBounds(0, 0, 57, 15);
		panel.add(lblID);
		lblID.setVisible(false);
		
		txtBarcode = new JTextField();
		txtBarcode.setFont(new Font("�������", Font.BOLD, 17));
		txtBarcode.setBounds(150, 11, 200, 33);
		panel.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		lblBarcode = new JLabel("�ĺ��ڵ�(identity Code)");
		lblBarcode.setFont(new Font("�������", Font.PLAIN, 12));
		lblBarcode.setBounds(12, 11, 126, 33);
		panel.add(lblBarcode);
		
		lblitemname = new JLabel("�׸��(ItemName)");
		lblitemname.setFont(new Font("�������", Font.PLAIN, 12));
		lblitemname.setBounds(12, 49, 109, 33);
		panel.add(lblitemname);
		
		txtItemName = new JTextField();
		txtItemName.setBackground(Color.WHITE);
		txtItemName.setEditable(false);
		txtItemName.setFont(new Font("�������", Font.BOLD, 17));
		txtItemName.setColumns(10);
		txtItemName.setBounds(150, 47, 340, 33);
		panel.add(txtItemName);
		
		txtCount = new JTextField();
		txtCount.setBackground(Color.WHITE);
		txtCount.setEditable(false);
		txtCount.setFont(new Font("�������", Font.BOLD, 17));
		txtCount.setColumns(10);
		txtCount.setBounds(568, 47, 53, 33);
		txtCount.addKeyListener(function.checkKeyAdapter());
		panel.add(txtCount);
		
		lblcount = new JLabel("����(Count)");
		lblcount.setFont(new Font("�������", Font.PLAIN, 12));
		lblcount.setBounds(502, 49, 72, 33);
		panel.add(lblcount);
		
		btnAdd = new JButton("�߰�(Add)");
		btnAdd.addActionListener(btnFnAdd());
		btnAdd.setFont(new Font("�������", Font.PLAIN, 12));
		btnAdd.setBounds(727, 47, 108, 33);
		panel.add(btnAdd);
		
		btnSearch = new JButton("�˻�(Search)");
		btnSearch.addActionListener(btnFnSearch());
		btnSearch.setFont(new Font("�������", Font.PLAIN, 12));
		btnSearch.setBounds(362, 11, 128, 33);
		panel.add(btnSearch);
		
		JLabel lblPrice = new JLabel("�ܰ�(Price)");
		lblPrice.setFont(new Font("�������", Font.PLAIN, 12));
		lblPrice.setBounds(502, 13, 72, 33);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBackground(Color.WHITE);
		txtPrice.setEditable(false);
		txtPrice.setFont(new Font("�������", Font.BOLD, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(568, 11, 72, 33);
		panel.add(txtPrice);
		
		txtTotalPrice = new JTextField();
		txtTotalPrice.setBackground(Color.WHITE);
		txtTotalPrice.setEditable(false);
		txtTotalPrice.setFont(new Font("�������", Font.BOLD, 14));
		txtTotalPrice.setColumns(10);
		txtTotalPrice.setBounds(763, 11, 72, 33);
		panel.add(txtTotalPrice);
		
		JLabel lblTotalPrice = new JLabel("�ݾ���(Total Price)");
		lblTotalPrice.setFont(new Font("�������", Font.PLAIN, 12));
		lblTotalPrice.setBounds(659, 13, 109, 33);
		panel.add(lblTotalPrice);
		
		JButton btnMinusPrice = new JButton("-");
		btnMinusPrice.addActionListener(btnFnMinusCount());
		btnMinusPrice.setBackground(Color.ORANGE);
		btnMinusPrice.setFont(new Font("�������", Font.PLAIN, 12));
		btnMinusPrice.setBounds(627, 47, 42, 33);
		panel.add(btnMinusPrice);
		
		JButton btnPlusPrice = new JButton("+");
		btnPlusPrice.addActionListener(btnFnPlusCount());
		btnPlusPrice.setBackground(Color.ORANGE);
		btnPlusPrice.setFont(new Font("�������", Font.PLAIN, 12));
		btnPlusPrice.setBounds(673, 47, 42, 33);
		panel.add(btnPlusPrice);
		
		panel_itemList = new JPanel();
		panel_itemList.setBackground(new Color(255, 255, 204));
		panel_itemList.setBorder(new LineBorder(new Color(192, 192, 192), 3, true));
		panel_itemList.setBounds(248, 153, 611, 260);
		contentPane.add(panel_itemList);
		panel_itemList.setLayout(null);
		panel_itemList.setVisible(false);

		panel_bag = new JPanel();
		panel_bag.setBackground(new Color(204, 255, 153));
		panel_bag.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_bag.setBounds(12, 153, 847, 308);
		contentPane.add(panel_bag);
		panel_bag.setLayout(null);
		
		tblItemList = new JTable(modelItemList);
		tblItemList = resizeItemListColumnWidth(tblItemList);					// ���̺� ũ�� ����
		JScrollPane scrollPane_itemList = new JScrollPane(tblItemList);
		scrollPane_itemList.setBounds(12, 35, 587, 202);
		panel_itemList.add(scrollPane_itemList);
		tblItemList.addMouseListener(tblItemListClicked());				// ���̺� Ŭ��
		
		tblBag = new JTable(modelBag);
		tblBag = resizeBagColumnWidth(tblBag);								// ���̺� ũ�� ����
		scrollPane_bag = new JScrollPane(tblBag);
		scrollPane_bag.setBounds(12, 35, 823, 171);
		panel_bag.add(scrollPane_bag);
		
		lblItemList = new JLabel("�׸� ���(Item List)");
		lblItemList.setFont(new Font("�������", Font.PLAIN, 12));
		lblItemList.setBounds(12, 10, 107, 15);
		panel_itemList.add(lblItemList);
		
		btnItemListHide = new JButton("X");
		btnItemListHide.addActionListener(btnFnItemListHide());
		btnItemListHide.setBackground(Color.DARK_GRAY);
		btnItemListHide.setForeground(Color.WHITE);
		btnItemListHide.setFont(new Font("�������", Font.BOLD, 13));
		btnItemListHide.setBounds(555, 2, 56, 33);
		panel_itemList.add(btnItemListHide);
		
		lblBag = new JLabel("��ٱ���(Bag)");
		lblBag.setFont(new Font("�������", Font.PLAIN, 12));
		lblBag.setBounds(12, 10, 98, 15);
		panel_bag.add(lblBag);
		
		btnRemove = new JButton("����(Remove)");
		btnRemove.addActionListener(btnFnRemove());
		btnRemove.setFont(new Font("�������", Font.PLAIN, 12));
		btnRemove.setBounds(12, 264, 124, 34);
		panel_bag.add(btnRemove);
		
		btnReset = new JButton("�ʱ�ȭ(Reset)");
		btnReset.addActionListener(btnFnReset());
		btnReset.setFont(new Font("�������", Font.PLAIN, 12));
		btnReset.setBounds(149, 264, 124, 34);
		panel_bag.add(btnReset);
		
		btnPaid = new JButton("����(Payment)");
		btnPaid.addActionListener(btnFnPaid());
		btnPaid.setFont(new Font("�������", Font.PLAIN, 12));
		btnPaid.setBounds(285, 264, 124, 34);
		panel_bag.add(btnPaid);
		
		
		txtSumOfMoney = new JTextField();
		txtSumOfMoney.setBackground(new Color(127, 255, 212));
		txtSumOfMoney.setEditable(false);
		txtSumOfMoney.setFont(new Font("�������", Font.BOLD, 18));
		txtSumOfMoney.setBounds(166, 216, 124, 38);
		panel_bag.add(txtSumOfMoney);
		txtSumOfMoney.setColumns(10);
		
		JLabel lblSumOfMoney = new JLabel("�հ�(Sum of Result)");
		lblSumOfMoney.setFont(new Font("�������", Font.BOLD, 15));
		lblSumOfMoney.setBounds(12, 229, 154, 15);
		panel_bag.add(lblSumOfMoney);
		
		initLocationName(locationID);
		resetBagQuery(locationID);
	}
	
	public int getLocationID() {
		return this.locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	
	public void requiryBag() {
		selectBagQuery();
	}
	
	public ActionListener btnFnSearch() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				lblID.setText("");
				
				String barcode = txtBarcode.getText();
				panel_itemList.setVisible(true);
				selectItemQuery(barcode);
			}
		};
		
		return actionListener;
		
	}
	
	public ActionListener btnFnItemListHide() {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_itemList.setVisible(false);
			}
		};
		
		return actionListener;
	}
	
	public ActionListener btnFnMinusCount() {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( txtCount.getText().isEmpty() == false ) {
					
					int count = Integer.valueOf( txtCount.getText() );
					int price = Integer.valueOf( txtPrice.getText() );
					int opPrice = 0;
					
					if ( count == 0 ) {
						
					}else {
						count--;
						txtCount.setText( String.valueOf( count ) );
						opPrice = price * count;
						txtTotalPrice.setText( String.valueOf(opPrice) );
					} // end of if
					
				} // end of if
			}
		};
		
		return actionListener;
	}
	
	public ActionListener btnFnPlusCount() {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ( txtCount.getText().isEmpty() == false ) {
				
					int count = Integer.valueOf( txtCount.getText() );
					int price = Integer.valueOf( txtPrice.getText() );
					int opPrice = 0;
	
					count++;
					txtCount.setText( String.valueOf( count ) );
					opPrice = price * count;
					txtTotalPrice.setText( String.valueOf(opPrice) );
					
				} // end of if
				
			}
		};
		
		return actionListener;
	}
	
	public ActionListener btnFnAdd() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				int count = 0;
				
				// ���� �Է�
				if ( txtCount.getText().isEmpty() == false )
					count = Integer.valueOf(txtCount.getText());
				
				if ( txtCount.getText().isEmpty() == true ) {

					JOptionPane.showMessageDialog(null, "������ �Է��ϼ���.\n(Please enter the number.)", "�˸�(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
				
				}
				else if ( txtCount.getText().isEmpty() == false &&
						count == 0) {

					JOptionPane.showMessageDialog(null, "������ �Է��ϼ���.\n(Please enter a quantity.)", "�˸�(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else if ( txtCount.getText().isEmpty() == false && 
						count > 0) {
				
					int locationId = getLocationID();
					int menuId = Integer.valueOf( lblID.getText() );
					String menuName = txtItemName.getText();
					int cnt = Integer.valueOf( txtCount.getText() );
					int sum = 0;
					
					Jasper_food_transaction_bag bag = new Jasper_food_transaction_bag();
					
					// ��¥
					java.util.Date date=new java.util.Date();
					
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
	
					// IP�ּ� �ҷ�����
					try {
						local = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					
					bag.setLocationId(locationId);
					bag.setMenuId(menuId);
					bag.setMenuName(menuName);
					bag.setCnt(cnt);
					bag.setRegidate(sqlTime);
					bag.setIp(local.getHostAddress());
					
					if ( txtSumOfMoney.getText().isEmpty() == false )
						sum = Integer.valueOf( txtSumOfMoney.getText() );
					
					sum = sum + Integer.valueOf( txtTotalPrice.getText() );
					
					txtSumOfMoney.setText( String.valueOf( sum ) );
					
					// ���� ����
					insertQuery(bag);
					selectBagQuery();
					
					// �����
					panel_itemList.setVisible(false);
					
					// �г� �ʱ�ȭ
					txtBarcode.setText("");
					txtItemName.setText("");
					txtCount.setText("");
					txtPrice.setText("");
					txtTotalPrice.setText("");
				
				}
			}
		};
		
		return actionListener;
	}
	
	public ActionListener btnFnRemove() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = tblBag.getSelectedRow();
				int id = -1;
				int bakPrice = -1;
				int sum = 0;
				String txt = "";
				Object value;
				
				if ( row == -1 ) {
					
					JOptionPane.showMessageDialog(null, "���� �� ����ϼ���.\n(Please use after selection.)", "�˸�(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
					
				}
				else if ( row != -1 ) {
					value = tblBag.getValueAt(row, 0);
					//System.out.println(value);
					id = Integer.valueOf( value.toString() );
					
					value = tblBag.getValueAt(row, 4);
					bakPrice = Integer.valueOf( value.toString() );
					
					if ( txtSumOfMoney.getText().isEmpty() == false )
						sum = Integer.valueOf( txtSumOfMoney.getText() );
					
					sum = sum - bakPrice;
					
					txtSumOfMoney.setText( String.valueOf( sum ));
					
					System.out.println(bakPrice);
					
					removeBagQuery(id);
					selectBagQuery();
				}
			}
		};
		
		return actionListener;
		
	}
	
	public ActionListener btnFnReset() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int locationID = getLocationID();
				resetBagQuery(locationID);
				txtSumOfMoney.setText("");
			}
		};
		
		return actionListener;
		
	}
	
	public ActionListener btnFnPaid() {
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean status = true;
				int locationID = -1;
				int requestMoney = -1;
				
				if ( txtSumOfMoney.getText().isEmpty() == true ) {
					status = false;
				}
				
				if ( status == true ) {
				
					locationID = getLocationID();
					requestMoney = Integer.valueOf( txtSumOfMoney.getText() );
					
					PaymentDialog frame = new PaymentDialog(locationID);
					frame.setRequestMoney(requestMoney);
					dispose();
					frame.setVisible(true);
				
				}
				
			}
		};
		
		return actionListener;
		
	}
	
	private void initLocationName(int locationID) {
		
		StringBuilder sb = new StringBuilder();
		MariaConnector mariaConnector = new MariaConnector();
		
		String query = "";
		String locationName = "";
		
		// lblLocationName �ʱ�ȭ
		lblLocationName.setText("");
		
		// SQL ��(Statement SQL)
		query = sb.append("select * from jasper_food_location")
				  .append(" where id = '")
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
				
			while(rs.next()){
				
				locationName = sb.append( rs.getString("regional") )
							   .append( "/" )
							   .append( rs.getString("locationPoint") )
							   .append( "/" )
							   .append( rs.getString("groupName") ).toString();
				
			} // end of while
			
			lblLocationName.setText(locationName);
			
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
		
	}
	
	private void selectItemQuery(String keyword){
		
		StringBuilder sb = new StringBuilder();
		MariaConnector mariaConnector = new MariaConnector();
		
		String query = "";
		
		// JTable �ʱ�ȭ
		modelItemList.setNumRows(0);
		
		// SQL ��(Statement SQL)
		query = sb.append("select * from jasper_food_menu_view")
				  .append(" where")
				  .append(" (menuName like '%")
				  .append(keyword)
				  .append("%')")
				  .append(" OR (barcode = '") 
				  .append(keyword)
				  .append("')")
				  .append(" order by id asc").toString();
		
		//System.out.println(query);
		
		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			this.stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
				
			while(rs.next()){
				
				modelItemList.addRow(
						new Object[]{
								rs.getString("id"), rs.getString("barcode"), 
								rs.getString("categoryName"), rs.getString("menuName"),
								rs.getString("menuType"), rs.getInt("price"), 
								rs.getString("cnt"), rs.getString("status")
				});
				
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
		
	}
	
	private void insertQuery(Jasper_food_transaction_bag bag) {
		
		String query = "";
		
		query = "insert into jasper_food_transaction_bag(locationId, menuId, menuName, cnt, regidate, ip)"
					+ " VALUES(?,?,?,?,?,?)";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bag.getLocationId());
			pstmt.setInt(2, bag.getMenuId());
			pstmt.setString(3, bag.getMenuName());
			pstmt.setInt(4, bag.getCnt());
			pstmt.setTimestamp(5, bag.getRegidate());
			pstmt.setString(6, bag.getIp());
			
			int rowNum = pstmt.executeUpdate();
			
			//System.out.println("��");
			
			if ( rowNum > 0 ){
				System.out.println("���Լ���");
			}
			else{
				System.out.println("����");
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
	
	private void removeBagQuery(int id) {
		
		String query = "";
		
		query = "delete from jasper_food_transaction_bag where id = ?";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		System.out.println(query);
		
		// JTable �ʱ�ȭ
		modelBag.setNumRows(0);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			int rowNum = pstmt.executeUpdate();
			
			if ( rowNum > 0 ){
				System.out.println("��������");
			}
			else{
				System.out.println("����");
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
	
	private void resetBagQuery(int locationId) {
		
		String query = "";
		
		query = "delete from jasper_food_transaction_bag where locationId = ?";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		System.out.println(query);
		
		// JTable �ʱ�ȭ
		modelBag.setNumRows(0);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, locationId);
			
			int rowNum = pstmt.executeUpdate();
			
			if ( rowNum > 0 ){
				System.out.println("��������");
			}
			else{
				System.out.println("����");
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

	private void selectBagQuery(){
		
		StringBuilder sb = new StringBuilder();
		MariaConnector mariaConnector = new MariaConnector();
		
		String query = "";
		
		// JTable �ʱ�ȭ
		modelBag.setNumRows(0);
		
		// SQL ��(Statement SQL)
		query = sb.append("select * from jasper_food_transaction_bag_view order by id").toString();
		
		System.out.println(query);
		
		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			this.stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
				
			while(rs.next()){
				
				modelBag.addRow(
						new Object[]{
								rs.getString("id"), rs.getString("barcode"), 
								rs.getString("menuName"), rs.getString("cnt"),
								rs.getInt("opPrice")
				});
				
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
		
	}
	
	private MouseAdapter tblItemListClicked(){
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = tblItemList.getSelectedRow();
				String txt = "";
				Object value;
				
				try{
					value = tblItemList.getValueAt(row, 0);
					lblID.setText( String.valueOf(value) );
					
					value = tblItemList.getValueAt(row, 1);
					txtBarcode.setText( String.valueOf( value ) );

					value = tblItemList.getValueAt(row, 2);
					txt = String.valueOf( value );
					value = tblItemList.getValueAt(row, 3);
					txt = txt + "/" + String.valueOf( value ) ;
					value = tblItemList.getValueAt(row, 4);
					txt = txt + "/" + String.valueOf( value ) ;
					
					txtItemName.setText( txt );
					
					value = tblItemList.getValueAt(row, 5);
					txtPrice.setText( String.valueOf( value ) );
					txtCount.setText("0");
					txtTotalPrice.setText("0");

				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		};
		
		return mouseAdapter;
	}
	
	private JTable resizeBagColumnWidth(JTable table) {
		
        //JTable �� �÷� ���� ����
        table.getColumnModel().getColumn(0).setPreferredWidth(10);  //JTable �� �÷� ���� ����
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
		
		return table; 
		
	}
	

	private JTable resizeItemListColumnWidth(JTable table) {
		
        //JTable �� �÷� ���� ����
        table.getColumnModel().getColumn(0).setPreferredWidth(20);  //JTable �� �÷� ���� ����
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);  //JTable �� �÷� ���� ����
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        table.getColumnModel().getColumn(6).setPreferredWidth(20);
        table.getColumnModel().getColumn(7).setPreferredWidth(10);
        
		return table; 
		
	}
}
