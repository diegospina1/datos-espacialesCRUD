package com.admondb.estudiantesregistro.mapper;

import com.admondb.estudiantesregistro.dto.UbicacionDTO;
import com.admondb.estudiantesregistro.model.Ubicacion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T15:52:26-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UbicacionMapperImpl implements UbicacionMapper {

    @Override
    public UbicacionDTO toUbicacionDTO(Ubicacion ubicacion) {
        if ( ubicacion == null ) {
            return null;
        }

        Double longitud = ubicacion.getCoordenadas().getY();
        Double latitud = ubicacion.getCoordenadas().getX();

        UbicacionDTO ubicacionDTO = new UbicacionDTO( longitud, latitud );

        return ubicacionDTO;
    }
}
