package br.com.bruno.batchrabbit.mapper;

import br.com.bruno.batchrabbit.domain.OperationalMovimentation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bruno Andrade
 * Dia: 07/10/2019
 **/
public class OperationalMovRowMapper implements RowMapper<OperationalMovimentation> {
    @Override
    public OperationalMovimentation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OperationalMovimentation operationalMovimentation = new OperationalMovimentation();
        operationalMovimentation.setCodTransaction(resultSet.getString("cod_transaction"));
        operationalMovimentation.setCodCostumer(resultSet.getString("cod_costumer"));
        operationalMovimentation.setAccountFrom(resultSet.getInt("account_from"));
        operationalMovimentation.setAccountTo(resultSet.getInt("account_to"));
        operationalMovimentation.setValue(resultSet.getBigDecimal("value"));

        return operationalMovimentation;

    }
}
