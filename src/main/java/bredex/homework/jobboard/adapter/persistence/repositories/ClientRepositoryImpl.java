package bredex.homework.jobboard.adapter.persistence.repositories;

import bredex.homework.jobboard.adapter.persistence.rowmappers.ClientRowMapper;
import bredex.homework.jobboard.domain.Client;
import bredex.homework.jobboard.domain.ClientRepository;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClientRowMapper clientRowMapper;

    /**
     * Saving new client to database
     *
     * @param client - Client object that contains client data (name, email)
     * @return String - Generated client API key (UUID format)
     */
    @Override
    public String registerClient(Client client) {

        //SQL query for saving clients to database
        String sqlQuery = "insert into Clients (clientKey, clientName, email) values (?, ?, ?)";

        //Generating sequential client ID
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //Generating unique client API key
        client.setUniqueClientKey();

        //Update database with preventing SQL injection
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery, new String[]{"clientId"});
            ps.setString(1, client.getClientKey());
            ps.setString(2, client.getClientName());
            ps.setString(3, client.getEmail());
            return ps;
        }, keyHolder);

        return client.getClientKey();
    }

    /**
     * Checking if API key already exists in database
     *
     * @param key - API key to be checked
     * @return boolean
     */
    @Override
    public boolean isKeyValid(String key) {

        //SQL query for finding row by clint API key
        String sqlQuery = "SELECT TOP 1 * FROM Clients WHERE Clients.clientKey = ?;";

        PreparedStatementCreator statementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setString(1, key);
            return ps;
        };

        //If there is result, the key is valid
        return jdbcTemplate.query(statementCreator, clientRowMapper).size() != 0;
    }
}