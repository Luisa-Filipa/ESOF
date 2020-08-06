package com.esof.projeto.services.filters.explicador;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Map;

@Data
public class FilterExplicadorObject {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private String name;
    private String idioma;
    private String cadeira;
    private String ects;
    private String ano;
    private String curso;
    private LocalTime startDate;
    private LocalTime endDate;

    public FilterExplicadorObject(Map<String,String> params) {
        this.name=params.get("name");
        this.idioma=params.get("idioma");
        this.cadeira=params.get("cadeira");
        this.ects=params.get("ects");
        this.ano=params.get("ano");
        this.curso=params.get("curso");

        String startDate=params.get("startDate");
        String endDate=params.get("endDate");

        LocalTime date1=null;
        LocalTime date2=null;

        System.out.println(date2);
        try{
            date1=LocalTime.parse(startDate);

        }catch (Exception e){
            this.logger.error(e.getMessage());
        }

        try{
            date2=LocalTime.parse(endDate);
        }catch (Exception e){
            this.logger.error(e.getMessage());
        }

        this.startDate=date1;
        this.endDate=date2;
    }
}