package bredex.homework.jobboard.adapter.persistance.rowmappers;

import bredex.homework.jobboard.domain.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Client(
                rs.getString("clientKey"),
                rs.getLong("clientId"),
                rs.getString("clientName"),
                rs.getString("email")
        );
    }
}
