package com.admondb.estudiantesregistro.mapper;

import com.admondb.estudiantesregistro.dto.UbicacionDTO;
import com.admondb.estudiantesregistro.model.Ubicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UbicacionMapper {

    UbicacionMapper ubicacionMapper = Mappers.getMapper( UbicacionMapper.class );

    @Mapping(target = "longitud", expression = "java(ubicacion.getCoordenadas().getY())")
    @Mapping(target = "latitud", expression = "java(ubicacion.getCoordenadas().getX())")
    UbicacionDTO toUbicacionDTO(Ubicacion ubicacion);


}
