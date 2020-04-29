
package com.fms.authenticate;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import com.github.jasync.sql.db.mysql.util.URLParser;

import io.r2dbc.spi.ConnectionFactory;

@Configuration

@EnableR2dbcRepositories
public class MySQLConfiguration extends AbstractR2dbcConfiguration {

	@Override
	public ConnectionFactory connectionFactory() {
		String url = "r2dbc:mysql://root:Password_1@localhost:3306/fmsoutreach";
		return new JasyncConnectionFactory(
				new MySQLConnectionFactory(URLParser.INSTANCE.parseOrDie(url, StandardCharsets.UTF_8)));
	}

}
