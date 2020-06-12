package br.com.embracon.common.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("mapper")
public class MapperUtil extends ModelMapper {
    private ModelMapper mapper;

    public MapperUtil(){
        this.mapper = new ModelMapper();
    }
}
