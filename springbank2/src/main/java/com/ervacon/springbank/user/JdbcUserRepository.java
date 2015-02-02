package com.ervacon.springbank.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository extends JdbcDaoSupport implements UserRepository {

	public void store(final User user) {
		getJdbcTemplate().execute(
				"insert into USER(id, userName, password, clientId) values (?, ?, ?, ?)",
				new PreparedStatementCallback() {
					public Object doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						ps.setLong(0, user.getId());
						ps.setString(1, user.getUserName());
						ps.setString(2, user.getPassword());
						ps.setLong(3, user.getClientId());
						return null;
					}
				});
	}
	
	public User findUser(String userName) {
		return (User)getJdbcTemplate().queryForObject(
				"select id, userName, password, clientId from USER where userName = ?",
				new Object[] { userName },
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return new User(rs.getLong(0), rs.getString(1), rs.getString(2), rs.getLong(3));
					}
				});
	}
}
