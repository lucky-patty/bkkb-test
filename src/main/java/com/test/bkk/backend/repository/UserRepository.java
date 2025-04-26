package com.test.bkk.backend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.bkk.backend.dto.Address;
import com.test.bkk.backend.dto.Company;
import com.test.bkk.backend.dto.Geo;
import com.test.bkk.backend.dto.User;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setWebsite(rs.getString("website"));

        Address address = new Address();
        address.setId(rs.getInt("address_id"));
        user.setAddress(address);

        Company company = new Company();
        company.setId(rs.getInt("company_id"));
        user.setCompany(company);
        
        return user;
    };

    public List<User> findAll() {

        String sql = """
            SELECT 
                u.id AS user_id, u.name AS user_name, u.username, u.email, u.phone, u.website,
                a.id AS address_id, a.street, a.suite, a.city, a.zipcode,
                g.id AS geo_id, g.lat, g.lng,
                c.id AS company_id, c.name AS company_name, c.catch_phrase, c.bs
            FROM users u
            JOIN address a ON u.address_id = a.id
            JOIN geo g ON a.geo_id = g.id
            JOIN company c ON u.company_id = c.id
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // Build Geo
            Geo geo = new Geo();
            geo.setId(rs.getInt("geo_id"));
            geo.setLat(rs.getString("lat"));
            geo.setLng(rs.getString("lng"));

            // Build Address
            Address address = new Address();
            address.setId(rs.getInt("address_id"));
            address.setStreet(rs.getString("street"));
            address.setSuite(rs.getString("suite"));
            address.setCity(rs.getString("city"));
            address.setZipcode(rs.getString("zipcode"));
            address.setGeo(geo);

            // Build Company
            Company company = new Company();
            company.setId(rs.getInt("company_id"));
            company.setName(rs.getString("company_name"));
            company.setCatchPhrase(rs.getString("catch_phrase"));
            company.setBs(rs.getString("bs"));

            // Build User
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("user_name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setWebsite(rs.getString("website"));
            user.setAddress(address);
            user.setCompany(company);

            return user;
        });

    }

    public Optional<User> findById(int id) {
        String sql = """
        SELECT 
            u.id AS user_id, u.name AS user_name, u.username, u.email, u.phone, u.website,
            a.id AS address_id, a.street, a.suite, a.city, a.zipcode,
            g.id AS geo_id, g.lat, g.lng,
            c.id AS company_id, c.name AS company_name, c.catch_phrase, c.bs
        FROM users u
        JOIN address a ON u.address_id = a.id
        JOIN geo g ON a.geo_id = g.id
        JOIN company c ON u.company_id = c.id
        WHERE u.id = ?
    """;

    List<User> results = jdbcTemplate.query(sql, (rs, rowNum) -> {
        // Build Geo
        Geo geo = new Geo();
        geo.setId(rs.getInt("geo_id"));
        geo.setLat(rs.getString("lat"));
        geo.setLng(rs.getString("lng"));

        // Build Address
        Address address = new Address();
        address.setId(rs.getInt("address_id"));
        address.setStreet(rs.getString("street"));
        address.setSuite(rs.getString("suite"));
        address.setCity(rs.getString("city"));
        address.setZipcode(rs.getString("zipcode"));
        address.setGeo(geo);

        // Build Company
        Company company = new Company();
        company.setId(rs.getInt("company_id"));
        company.setName(rs.getString("company_name"));
        company.setCatchPhrase(rs.getString("catch_phrase"));
        company.setBs(rs.getString("bs"));

        // Build User
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setName(rs.getString("user_name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setWebsite(rs.getString("website"));
        user.setAddress(address);
        user.setCompany(company);

        return user;
    }, id);

    return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));

    }
    
    public int save(User user) {
        String sql = "INSERT INTO users (id, name, username, email, phone, website, address_id, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getWebsite(),
                user.getAddress().getId(),
                user.getCompany().getId());
    }

    public int update(User user) {
        String sql = "UPDATE users SET name = ?, username = ?, email = ?, phone = ?, website = ?, address_id = ?, company_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getWebsite(),
                user.getAddress().getId(),
                user.getCompany().getId(),
                user.getId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
