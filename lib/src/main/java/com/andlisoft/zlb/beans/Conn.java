package com.andlisoft.zlb.beans;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conn {
	private String DbName = "";
	private String pass = "";
	private String url = "";
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public void Conn() {

	}

	public void BulidCon() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			url = "jdbc:mysql://localhost/" + DbName + "?user=root&password="
					+ pass + "&useUnicode=true&characterEncoding=gb2312";
			con = DriverManager.getConnection(url);
			stmt = (Statement) con
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
		}
	}

	public ResultSet select(String sql) {
		try {
			BulidCon();
			rs = ((java.sql.Statement) stmt).executeQuery(sql);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return rs;
	}

	public void update(String sql) {
		try {
			BulidCon();
			((java.sql.Statement) stmt).executeUpdate(sql);
		} catch (Exception e) {
		}
	}

	public void close() {
		try {
			con.close();
			((Connection) stmt).close();
		} catch (SQLException e) {
		}
	}

}
