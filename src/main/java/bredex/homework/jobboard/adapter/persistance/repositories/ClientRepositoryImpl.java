package bredex.homework.jobboard.adapter.persistance.repositories;

import bredex.homework.jobboard.adapter.persistance.rowmappers.ClientRowMapper;
import bredex.homework.jobboard.domain.Client;
import bredex.homework.jobboard.domain.ClientRepository;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String registerClient(Client client) {

        String sqlQuery = "insert into Clients (clientKey, clientName, email) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        client.setUniqueClientKey();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery, new String[]{"clientId"});
            ps.setString(1, client.getClientKey());
            ps.setString(2, client.getClientName());
            ps.setString(3, client.getEmail());
            return ps;
        }, keyHolder);

        return client.getClientKey();
    }
}