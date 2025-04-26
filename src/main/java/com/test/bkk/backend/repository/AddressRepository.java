package com.test.bkk.backend.repository;

import com.test.bkk.backend.dto.Address;
import com.test.bkk.backend.dto.Geo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    public AddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Address> addressRowMapper = (rs, rowNum) -> {
        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setStreet(rs.getString("street"));
        address.setSuite(rs.getString("suite"));
        address.setCity(rs.getString("city"));
        address.setZipcode(rs.getString("zipcode"));

        Geo geo = new Geo();
        geo.setId(rs.getInt("geo_id"));
        address.setGeo(geo);
      
        return address;
    };

    public Optional<Address> findById(int id) {
        String sql = "SELECT * FROM address WHERE id = ?";
        List<Address> results = jdbcTemplate.query(sql, addressRowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public int save(Address address) {
        String sql = "INSERT INTO address (street, suite, city, zipcode, geo_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
        return jdbcTemplate.queryForObject(sql, Integer.class,
            address.getStreet(),
            address.getSuite(),
            address.getCity(),
            address.getZipcode(),
            address.getGeo().getId()  // Use the ID returned from GeoRepository
        );
    }


}
