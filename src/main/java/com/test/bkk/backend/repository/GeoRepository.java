package com.test.bkk.backend.repository;
import com.test.bkk.backend.dto.Geo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GeoRepository {

    private final JdbcTemplate jdbcTemplate;

    public GeoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Geo> geoRowMapper = (rs, rowNum) -> {
        Geo geo = new Geo();
        geo.setId(rs.getInt("id"));
        geo.setLat(rs.getString("lat"));
        geo.setLng(rs.getString("lng"));
        return geo;
    };

    public Optional<Geo> findById(int id) {
        String sql = "SELECT * FROM geo WHERE id = ?";
        List<Geo> results = jdbcTemplate.query(sql, geoRowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public int save(Geo geo) {
        String sql = "INSERT INTO geo (lat, lng) VALUES (?, ?) RETURNING id";
        return jdbcTemplate.queryForObject(sql, Integer.class, geo.getLat(), geo.getLng());
    }
}
