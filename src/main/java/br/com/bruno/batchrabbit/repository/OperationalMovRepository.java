package br.com.bruno.batchrabbit.repository;

import br.com.bruno.batchrabbit.domain.OperationalMovimentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Andrade
 * Dia: 07/10/2019
 **/
@Repository
public interface OperationalMovRepository extends JpaRepository<OperationalMovimentation, Long> {
}
