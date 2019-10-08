package br.com.bruno.batchrabbit.processor;

import br.com.bruno.batchrabbit.domain.OperationalMovimentation;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author Bruno Andrade
 * Dia: 07/10/2019
 **/
public class OperationalMovProcessor implements ItemProcessor<OperationalMovimentation, OperationalMovimentation> {

    @Override
    public OperationalMovimentation process(OperationalMovimentation operationalMovimentation) throws Exception {
        return operationalMovimentation;
    }
}
