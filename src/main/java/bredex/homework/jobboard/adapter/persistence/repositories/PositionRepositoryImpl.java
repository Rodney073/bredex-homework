package bredex.homework.jobboard.adapter.persistence.repositories;

import bredex.homework.jobboard.adapter.persistence.rowmappers.PositionRowMapper;
import bredex.homework.jobboard.domain.Position;
import bredex.homework.jobboard.domain.PositionRepository;
import bredex.homework.jobboard.exceptions.NoSuchPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
class PositionRepositoryImpl implements PositionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PositionRowMapper positionRowMapper;

    /**
     * Saving new position to database
     *
     * @param position - API key to be checked
     * @return String - URL of new position
     */
    @Override
    public String createPosition(Position position) {
        //SQL query for saving new positions to database
        String sqlQuery = "insert into Positions (name, location) values (?, ?)";

        //Generating sequential client ID
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //Update database with preventing SQL injection
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery, new String[]{"positionId"});

            ps.setString(1, position.getName());
            ps.setString(2, position.getLocation());
            return ps;
        }, keyHolder);

        return position.getRequestURL() + "/" + keyHolder.getKey();
    }

    /**
     * Searching for positions by position name and location
     *
     * @param position - Position object that contains Position data (name, location)
     * @return List of Position Objects
     */
    @Override
    public List<Position> searchPositions(Position position) {
        //SQL query for searching for positions in db
        String sqlQuery = "SELECT * FROM Positions WHERE Positions.name like ? AND Positions.location =?";

        //Searching with preventing SQL injection
        PreparedStatementCreator statementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setString(1, "%" + position.getName() + "%");
            ps.setString(2, position.getLocation());
            return ps;
        };

        //Returning Position objects if there's any
        if (!jdbcTemplate.query(statementCreator, positionRowMapper).isEmpty()) {
            return jdbcTemplate.query(statementCreator, positionRowMapper);
        } else {
            throw new NoSuchPositionException("Sorry, we have no matching positions!");
        }
    }

    /**
     * Searching for position by ID
     *
     * @param id - Long type ID of position
     * @return Position Object
     */
    @Override
    public Position getPosition(Long id) {
        //SQL query for searching for position by ID
        String sqlQuery = "SELECT * FROM Positions WHERE Positions.positionId = ?";

        //Searching with preventing SQL injection
        PreparedStatementCreator statementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setLong(1, id);
            return ps;
        };

        //Returning Position object if exists
        if (!jdbcTemplate.query(statementCreator, positionRowMapper).isEmpty()) {
            return jdbcTemplate.query(statementCreator, positionRowMapper).get(0);
        } else {
            throw new NoSuchPositionException("No such Position with id: " + id);
        }
    }
}
