package bredex.homework.jobboard.adapter.persistance.repositories;

import bredex.homework.jobboard.adapter.persistance.rowmappers.PositionRowMapper;
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

    @Override
    public String createPosition(Position position) {
        String sqlQuery = "insert into Positions (name, location) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery, new String[]{"positionId"});

            ps.setString(1, position.getName());
            ps.setString(2, position.getLocation());
            return ps;
        }, keyHolder);

        return position.getPositionURL() + "/" + keyHolder.getKey();
    }

    @Override
    public List<Position> searchPositions(Position position) {

        String sqlQuery = "SELECT * FROM Positions WHERE Positions.name like ? AND Positions.location =?";

        PreparedStatementCreator statementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setString(1, "%" + position.getName() + "%");
            ps.setString(2, position.getLocation());
            return ps;
        };

        if (!jdbcTemplate.query(statementCreator, positionRowMapper).isEmpty()) {
            return jdbcTemplate.query(statementCreator, positionRowMapper);
        } else {
            throw new NoSuchPositionException("Sorry, we have no matching positions!");
        }
    }

    @Override
    public Position getPosition(Long id) {
        String sqlQuery = "SELECT * FROM Positions WHERE Positions.positionId = ?";

        PreparedStatementCreator statementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setLong(1, id);
            return ps;
        };

        if (!jdbcTemplate.query(statementCreator, positionRowMapper).isEmpty()) {
            return jdbcTemplate.query(statementCreator, positionRowMapper).get(0);
        } else {
            throw new NoSuchPositionException("No such Position with id: " + id);
        }
    }
}
