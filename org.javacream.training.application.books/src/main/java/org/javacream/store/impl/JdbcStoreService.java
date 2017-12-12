package org.javacream.store.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

@Service
public class JdbcStoreService implements StoreService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int getStock(final String category, final String item) {
		return jdbcTemplate.execute(" select stock from stock where category=? and item=?",
				new PreparedStatementCallback<Integer>() {

					@Override
					public Integer doInPreparedStatement(PreparedStatement preparedStatement)
							throws SQLException, DataAccessException {
						preparedStatement.setString(1, category);
						preparedStatement.setString(2, item);
						ResultSet rs = preparedStatement.executeQuery();
						if (rs.next()) {
							return rs.getInt(1);
						} else {
							return 0;
						}
					}
				});
	}

}
