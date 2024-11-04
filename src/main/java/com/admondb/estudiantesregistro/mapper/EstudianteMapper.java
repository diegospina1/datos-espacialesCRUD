package com.admondb.estudiantesregistro.mapper;

import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.model.Estudiante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {

    EstudianteMapper estudianteMapper = Mappers.getMapper(EstudianteMapper.class);

    Estudiante toEstudiante(EstudianteDTO estudianteDTO);

    EstudianteDTO toEstudianteDTO(Estudiante estudiante);
}
