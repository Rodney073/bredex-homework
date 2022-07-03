package bredex.homework.jobboard.adapter.persistance.rowmappers;

import bredex.homework.jobboard.domain.Position;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PositionRowMapper implements RowMapper<Position> {

    @Override
    public Position mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Position(
                rs.getLong("positionId"),
                rs.getString("name"),
                rs.getString("location")
        );
    }
}
