package com.admondb.estudiantesregistro.service.direccion;

import com.admondb.estudiantesregistro.dao.direccionDAO.IDireccionDAO;
import com.admondb.estudiantesregistro.dao.repository.DireccionRepository;
import com.admondb.estudiantesregistro.dto.ActualizarDatosDTO;
import com.admondb.estudiantesregistro.dto.DatosDTO;
import com.admondb.estudiantesregistro.dto.UbicacionDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDistanciaDTO;
import com.admondb.estudiantesregistro.model.CategoriaDireccion;
import com.admondb.estudiantesregistro.model.Direccion;
import com.admondb.estudiantesregistro.model.Estudiante;
import com.admondb.estudiantesregistro.model.Ubicacion;
import com.admondb.estudiantesregistro.service.estudiante.IEstudianteService;
import com.admondb.estudiantesregistro.service.ubicacion.IUbicacionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.admondb.estudiantesregistro.mapper.EstudianteMapper.estudianteMapper;
import static com.admondb.estudiantesregistro.mapper.UbicacionMapper.ubicacionMapper;

@Service
public class DireccionService implements IDireccionService{

    private final IDireccionDAO dao;
    private final IEstudianteService estudianteService;
    private final IUbicacionService ubicacionService;
    private final DireccionRepository direccionRepository;

    public DireccionService(IDireccionDAO dao, IEstudianteService serviceEstudiante, IUbicacionService ubicacionService, DireccionRepository direccionRepository) {
        this.dao = dao;
        this.estudianteService = serviceEstudiante;
        this.ubicacionService = ubicacionService;
        this.direccionRepository = direccionRepository;
    }

    @Override
    public void eliminar(String cedula) {
        Estudiante estudiante = estudianteService.verEstudiante(cedula);
        Direccion residencia = dao.verDireccionResidencia(estudiante.getId());
        Direccion trabajo = dao.verDireccionTrabajo(estudiante.getId());

        dao.eliminarDireccion(residencia);
        dao.eliminarDireccion(trabajo);
        estudianteService.eliminarEstudiante(cedula);
    }

    @Override
    public DatosDTO actualizar(ActualizarDatosDTO datos) {
        Estudiante estudiante = estudianteService.actualizarEstudiante(datos.estudiante());
        Ubicacion residencia = null;
        Ubicacion trabajo = null;

        if (datos.residencia() != null) {
            residencia = ubicacionService.crearUbicacion(datos.residencia());
        }
        if (datos.trabajo() != null) {
            trabajo = ubicacionService.crearUbicacion(datos.trabajo());
        }

        Direccion actualDirR = dao.verDireccionResidencia(estudiante.getId());
        Direccion actualDirT = dao.verDireccionTrabajo(estudiante.getId());

        actualDirR.actualizar(estudiante, residencia, CategoriaDireccion.RESIDENCIA);
        actualDirT.actualizar(estudiante, trabajo, CategoriaDireccion.TRABAJO);

        dao.actualizarDireccion(actualDirR);
        dao.actualizarDireccion(actualDirT);

        return new DatosDTO(estudianteMapper.toEstudianteDTO(estudiante),
                ubicacionMapper.toUbicacionDTO(residencia), ubicacionMapper.toUbicacionDTO(trabajo));
    }

    @Override
    public DatosDTO verPorEstudiante(String cedula) {
        Estudiante estudiante = estudianteService.verEstudiante(cedula);
        return buscarDirecciones(estudiante);
    }

    @Override
    public List<DatosDTO> verTodas() {
        List<DatosDTO> datos = new ArrayList<>();
        List<Estudiante> estudiantes = estudianteService.verEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            datos.add(buscarDirecciones(estudiante));
        }
        return datos;
    }

    @Override
    public DatosDTO crear(DatosDTO datos) {
        Estudiante estudiante = estudianteService.crearEstudiante(datos.estudiante());
        Ubicacion residencia = ubicacionService.crearUbicacion(datos.residencia());
        Ubicacion trabajo = ubicacionService.crearUbicacion(datos.trabajo());

        dao.crearDireccion(new Direccion(estudiante, residencia, CategoriaDireccion.RESIDENCIA));
        dao.crearDireccion(new Direccion(estudiante, trabajo, CategoriaDireccion.TRABAJO));

        return new DatosDTO(estudianteMapper.toEstudianteDTO(estudiante), ubicacionMapper.toUbicacionDTO(residencia),
                ubicacionMapper.toUbicacionDTO(trabajo));
    }

    public DatosDTO buscarDirecciones(Estudiante estudiante){
        Direccion dirResidencia = dao.verDireccionResidencia(estudiante.getId());
        Direccion dirTrabajo = dao.verDireccionTrabajo(estudiante.getId());

        Ubicacion residencia = (dirResidencia != null) ? dirResidencia.getUbicacion() : null;
        Ubicacion trabajo = (dirTrabajo != null) ? dirTrabajo.getUbicacion() : null;

        UbicacionDTO residenciaDTO = ubicacionMapper.toUbicacionDTO(residencia);
        UbicacionDTO trabajoDTO = ubicacionMapper.toUbicacionDTO(trabajo);

        return new DatosDTO(estudianteMapper.toEstudianteDTO(estudiante), residenciaDTO, trabajoDTO);
    }

    public List<EstudianteDistanciaDTO> obtenerEstudiantesConDistancia() {
        List<Object[]> resultados = direccionRepository.obtenerEstudiantesDistancia();
        List<EstudianteDistanciaDTO> listaDTO = new ArrayList<>();

        for (Object[] fila : resultados) {
            String nombres = (String) fila[0];
            String apellidos = (String) fila[1];
            Double distancia = ((Number) fila[2]).doubleValue();

            listaDTO.add(new EstudianteDistanciaDTO(nombres, apellidos, distancia));
        }

        return listaDTO;
    }
}
