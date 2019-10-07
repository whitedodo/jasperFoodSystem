/*
 * Project Name: Rabbit Counter
 * FileName: SingleSignOn.java
 * Created Date: 2019-09-07
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
*/
package com.counter.application.sso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.counter.application.database.*;
import com.counter.model.*;

public class MemberShip {
	
	private PreparedStatement pstmt = null;
	
	public Jasper_Member getMember(String Email, String Passwd) {
		
		ResultSet rs = null;
		Jasper_Member member = new Jasper_Member();
		
		member.setId(-1);								// Flag (state == true(-1), )
		
		// 아이디, 비밀번호 확인
		DbController dbController = new DbController();
		String query = "select id, email, passwd, locationID from jasper_member where email = ? and passwd = ?";

		Connection conn = dbController.getConn();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString( 1, Email );
			pstmt.setString( 2, Passwd );
			dbController.select(pstmt);
			rs = dbController.getResultSet();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "알림(Alert)",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		try {
			
			while(rs.next()){

				if(! rs.getString("email").equals(Email) ||
				   ! rs.getString("passwd").equals(Passwd)){

				}
				else{
					//memberID = rs.getInt("id");
					//locationID = rs.getInt("locationId");
					member.setId( rs.getInt("id") );
					member.setLocationId( rs.getInt("locationId") );
					
				}
			}
			
			pstmt.close();
			rs.close();
			conn.close();
			
			dbController.setResultSet(null);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "알림(Alert)",
					JOptionPane.INFORMATION_MESSAGE);
			
		} // end of while
		
		return member;
		
	}
	
}
