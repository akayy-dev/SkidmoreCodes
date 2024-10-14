package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseController {
	private String URL;
	private Connection conn;

	public DatabaseController(String urlString, String username, String password) {
		this.URL = urlString;

		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);

		try {
			Class.forName("org.postgresql.Driver");

			// This can throw SQL not found exception
			conn = DriverManager.getConnection(URL, props);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	/**
	 * Adds an email address to the database.
	 *
	 * @param address the email address to be added
	 * @return true if the email address was successfully added, false if an
	 *         SQLException occurred (likely indicating the email is already
	 *         registered)
	 */
	public boolean addEmail(String address) {
		String query = "INSERT INTO emails (address) VALUES (?)";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setString(1, address);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// If exception is raised the email is likely alread registered.
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkIfEmailExists(String address) {
		String query = "SELECT address FROM emails WHERE address = ?";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setString(1, address);
			ResultSet results = pst.executeQuery();

			// Results.next() returns false if there are no rows in the queries results.
			return results.next();
		} catch (SQLException e) {
			e.printStackTrace();
			// If exception is raised the email is likely alread registered.
			return false;
		}

	}

	/**
	 * Retrieves the ID associated with the given email address from the database.
	 *
	 * @param email the email address to look up.
	 * @return the ID associated with the email address if it exists, otherwise
	 *         returns 0.
	 */
	public Integer getEmailID(String email) {
		if (checkIfEmailExists(email)) {
			String query = "SELECT id FROM emails WHERE address = ?";
			try (PreparedStatement pst = conn.prepareStatement(query)) {
				pst.setString(1, email);
				ResultSet results = pst.executeQuery();
				if (results.next()) {
					return results.getInt(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}

	public void addSuggestion(String email, String content) {
		if (checkIfEmailExists(email)) {
			Integer emailID = getEmailID(email);

			String query = "INSERT INTO suggestions (content, email_id) VALUES (?, ?)";
			try (PreparedStatement pst = conn.prepareStatement(query)) {
				pst.setString(1, content);
				pst.setInt(2, emailID);
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
