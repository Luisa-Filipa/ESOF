package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.filters.AndFilter;
import com.esof.projeto.services.filters.FilterI;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilterExplicadorServices {

    public Set<Explicador> filter(Set<Explicador> explicadores, FilterExplicadorObject filterExplicadorObject) {
        FilterI<Explicador> explicadorFilterByName=new ExplicadorFilterByName(filterExplicadorObject.getName());
        FilterI<Explicador> explicadorFilterStartDate=new ExplicadorFilterByStartData(filterExplicadorObject.getStartDate());
        FilterI<Explicador> explicadorFilterEndDate=new ExplicadorFilterByEndData(filterExplicadorObject.getEndDate());
        FilterI<Explicador> explicadorFilterByIdioma=new ExplicadorFilterByIdioma(filterExplicadorObject.getIdioma());
        FilterI<Explicador> explicadorFilterByCadeira=new ExplicadorFilterByCadeira(filterExplicadorObject.getCadeira());

        FilterI<Explicador> explicadorByNameANDByStartDate=new AndFilter<>(explicadorFilterByName,explicadorFilterStartDate);
        FilterI<Explicador> explicador=new AndFilter<>(explicadorFilterByIdioma,explicadorByNameANDByStartDate);
        FilterI<Explicador> explicador1=new AndFilter<>(explicadorFilterByCadeira,explicador);
        FilterI<Explicador> explicadorByNameANDByStartDateANDByEndDate=new AndFilter<>(explicador1,explicadorFilterEndDate);

        return new AndFilter<>(explicadorByNameANDByStartDate,explicadorByNameANDByStartDateANDByEndDate).filter(explicadores);
    }
}