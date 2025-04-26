package com.test.bkk.backend.repository;


import com.test.bkk.backend.dto.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepository {

    private final JdbcTemplate jdbcTemplate;

    public CompanyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Company> companyRowMapper = (rs, rowNum) -> {
        Company company = new Company();
        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        company.setCatchPhrase(rs.getString("catch_phrase"));
        company.setBs(rs.getString("bs"));
        return company;
    };

    public Optional<Company> findById(int id) {
        String sql = "SELECT * FROM company WHERE id = ?";
        List<Company> results = jdbcTemplate.query(sql, companyRowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public int save(Company company) {
        String sql = "INSERT INTO company (name, catch_phrase, bs) VALUES (?, ?, ?) RETURNING id";
        return jdbcTemplate.queryForObject(sql, Integer.class,
            company.getName(),
            company.getCatchPhrase(),
            company.getBs()
        );
    }

}