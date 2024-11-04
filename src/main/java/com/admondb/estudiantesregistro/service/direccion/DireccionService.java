package com.admondb.estudiantesregistro.service.direccion;

import com.admondb.estudiantesregistro.dao.UbicacionDAO;
import com.admondb.estudiantesregistro.dao.direccionDAO.IDireccionDAO;
import com.admondb.estudiantesregistro.dto.ActualizarDatosDTO;
import com.admondb.estudiantesregistro.dto.DatosDTO;
import com.admondb.estudiantesregistro.dto.UbicacionDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.model.CategoriaDireccion;
import com.admondb.estudiantesregistro.model.Direccion;
import com.admondb.estudiantesregistro.model.Estudiante;
import com.admondb.estudiantesregistro.model.Ubicacion;
import com.admondb.estudiantesregistro.service.estudiante.IEstudianteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DireccionService implements IDireccionService{

    private final IDireccionDAO dao;
    private final IEstudianteService serviceEstudiante;
    private final UbicacionDAO serviceUbicacion;

    public DireccionService(IDireccionDAO dao, IEstudianteService serviceEstudiante, UbicacionDAO serviceUbicacion) {
        this.dao = dao;
        this.serviceEstudiante = serviceEstudiante;
        this.serviceUbicacion = serviceUbicacion;
    }


    @Override
    public void eliminar(String cedula) {
        EstudianteDTO estudiante = serviceEstudiante.verEstudiante(cedula);
        Direccion residencia = dao.verDireccionResidencia(estudiante.id());
        Direccion trabajo = dao.verDireccionTrabajo(estudiante.id());

        dao.eliminarDireccion(residencia);
        dao.eliminarDireccion(trabajo);
        serviceEstudiante.eliminarEstudiante(cedula);
    }

    @Override
    public DatosDTO actualizar(ActualizarDatosDTO datos) {
        EstudianteDTO estudiante = serviceEstudiante.actualizarEstudiante(datos.estudiante());
        Ubicacion residencia = null;
        Ubicacion trabajo = null;
        UbicacionDTO residenciaDTO = null;
        UbicacionDTO trabajoDTO = null;

        if (datos.residencia() != null) {
            residenciaDTO = serviceUbicacion.crear(datos.residencia());
            residencia = serviceUbicacion.buscarPorId(residenciaDTO.id());
        }
        if (datos.trabajo() != null) {
            trabajoDTO = serviceUbicacion.crear(datos.trabajo());
            trabajo = serviceUbicacion.buscarPorId(trabajoDTO.id());
        }

        Direccion actualDirR = dao.verDireccionResidencia(estudiante.id());
        Direccion actualDirT = dao.verDireccionTrabajo(estudiante.id());

        actualDirR.actualizar(serviceEstudiante.buscarEstudiantePorCedula(estudiante.cedula()), residencia, CategoriaDireccion.RESIDENCIA);
        actualDirT.actualizar(serviceEstudiante.buscarEstudiantePorCedula(estudiante.cedula()), trabajo, CategoriaDireccion.TRABAJO);

        dao.actualizarDireccion(actualDirR);
        dao.actualizarDireccion(actualDirT);
        return new DatosDTO(estudiante, residenciaDTO, trabajoDTO);
    }

    @Override
    public DatosDTO verPorEstudiante(String cedula) {
        EstudianteDTO estudiante = serviceEstudiante.verEstudiante(cedula);
        return buscarDirecciones(estudiante);
    }

    @Override
    public List<DatosDTO> verTodas() {
        List<DatosDTO> datos = new ArrayList<>();
        List<EstudianteDTO> estudiantes = serviceEstudiante.verEstudiantes();

        for (EstudianteDTO estudiante : estudiantes) {
            datos.add(buscarDirecciones(estudiante));
        }

        return datos;
    }

    @Override
    public DatosDTO crear(DatosDTO datos) {
        EstudianteDTO estudianteDTO = serviceEstudiante.crearEstudiante(datos.estudiante());
        UbicacionDTO residenciaDTO = serviceUbicacion.crear(datos.residencia());
        UbicacionDTO trabajoDTO = serviceUbicacion.crear(datos.trabajo());

        Estudiante estudiante = serviceEstudiante.buscarEstudiantePorCedula(estudianteDTO.cedula());

        Direccion residenciaAsociacion = new Direccion(estudiante,
                serviceUbicacion.buscarPorId(residenciaDTO.id()), CategoriaDireccion.RESIDENCIA);
        Direccion trabajoAsociacion = new Direccion(estudiante,
                serviceUbicacion.buscarPorId(trabajoDTO.id()), CategoriaDireccion.TRABAJO);

        dao.crearDireccion(residenciaAsociacion);
        dao.crearDireccion(trabajoAsociacion);

        return new DatosDTO(estudianteDTO, residenciaDTO, trabajoDTO);
    }

    public DatosDTO buscarDirecciones(EstudianteDTO estudiante){
        Ubicacion residencia = dao.verDireccionResidencia(estudiante.id()).getUbicacion();
        Ubicacion trabajo = dao.verDireccionTrabajo(estudiante.id()).getUbicacion();

        UbicacionDTO residenciaDTO = new UbicacionDTO(residencia.getId(), residencia.getCoordenadas().getY(), residencia.getCoordenadas().getX());
        UbicacionDTO trabajoDTO = new UbicacionDTO(residencia.getId(), trabajo.getCoordenadas().getY(), residencia.getCoordenadas().getX());

        return new DatosDTO(estudiante, residenciaDTO, trabajoDTO);
    }
}
