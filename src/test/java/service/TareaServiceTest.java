package service;

import com.tareas.gestion_tareas.dto.ActualizarTareaDTO;
import com.tareas.gestion_tareas.dto.CrearTareaDTO;
import com.tareas.gestion_tareas.dto.TareaDTO;
import com.tareas.gestion_tareas.entity.Tarea;
import com.tareas.gestion_tareas.mapper.TareaMapper;
import com.tareas.gestion_tareas.repository.TareaRepository;
import com.tareas.gestion_tareas.service.TareaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private TareaMapper tareaMapper;

    @InjectMocks
    private TareaService tareaService;

    @Test
    void testCrearTarea() {
        // Datos de entrada
        CrearTareaDTO crearTareaDTO = new CrearTareaDTO("Aprender Spring", "Estudiar tutoriales", false);
        Tarea tarea = new Tarea(null, "Aprender Spring", "Estudiar tutoriales", false);
        Tarea tareaGuardada = new Tarea(1L, "Aprender Spring", "Estudiar tutoriales", false);
        TareaDTO tareaDTO = new TareaDTO(1L, "Aprender Spring", "Estudiar tutoriales", false);


        // Simulaciones
        Mockito.when(tareaMapper.toTarea(crearTareaDTO)).thenReturn(tarea);
        Mockito.when(tareaRepository.save(tarea)).thenReturn(tareaGuardada);
        Mockito.when(tareaMapper.toTareaDTO(tareaGuardada)).thenReturn(tareaDTO);

        // Ejecución
        TareaDTO resultado = tareaService.crearTarea(crearTareaDTO);

        // Verificaciones
        assertEquals(1L, resultado.getId());
        assertEquals("Aprender Spring", resultado.getTitulo());
        assertEquals("Estudiar tutoriales", resultado.getDescripcion());
        assertEquals("pendiente", resultado.getEstado());
    }

        @Test
        void testListarTareas() {
            // Datos de prueba
            Tarea tarea1 = new Tarea(1L, "Tarea 1", "Descripción 1", false);
            Tarea tarea2 = new Tarea(2L, "Tarea 2", "Descripción 2", true);
            List<Tarea> tareas = List.of(tarea1, tarea2);

            TareaDTO tareaDTO1 = new TareaDTO(1L, "Tarea 1", "Descripción 1", false);
            TareaDTO tareaDTO2 = new TareaDTO(2L, "Tarea 2", "Descripción 2", true);

            // Simulaciones
            Mockito.when(tareaRepository.findAll()).thenReturn(tareas);
            Mockito.when(tareaMapper.toTareaDTO(tarea1)).thenReturn(tareaDTO1);
            Mockito.when(tareaMapper.toTareaDTO(tarea2)).thenReturn(tareaDTO2);

            // Ejecución
            List<TareaDTO> resultado = tareaService.listarTareas();

            // Verificaciones
            assertEquals(2, resultado.size());
            assertEquals("Tarea 1", resultado.get(0).getTitulo());
            assertEquals("Tarea 2", resultado.get(1).getTitulo());
        }

        @Test
        void testActualizarTarea() {
            // Datos de prueba
            Long id = 1L;
            ActualizarTareaDTO actualizarTareaDTO = new ActualizarTareaDTO("Tarea actualizada", "Descripción actualizada", true);
            Tarea tareaExistente = new Tarea(1L, "Tarea original", "Descripción original", false);
            Tarea tareaActualizada = new Tarea(1L, "Tarea actualizada", "Descripción actualizada", true);
            TareaDTO tareaDTO = new TareaDTO(1L, "Tarea actualizada", "Descripción actualizada", true);

            // Simulaciones
            Mockito.when(tareaRepository.findById(id)).thenReturn(Optional.of(tareaExistente));
            Mockito.doAnswer(invocation -> {
                TareaMapper mapper = (TareaMapper) invocation.getMock();
                mapper.actualizarTareaDesdeDTO(actualizarTareaDTO, tareaExistente);
                tareaExistente.setTitulo(actualizarTareaDTO.getTitulo());
                tareaExistente.setDescripcion(actualizarTareaDTO.getDescripcion());
                tareaExistente.setEstado(actualizarTareaDTO.getEstado());
                return null;
            }).when(tareaMapper).actualizarTareaDesdeDTO(Mockito.eq(actualizarTareaDTO), Mockito.eq(tareaExistente));
            Mockito.when(tareaRepository.save(tareaExistente)).thenReturn(tareaActualizada);
            Mockito.when(tareaMapper.toTareaDTO(tareaActualizada)).thenReturn(tareaDTO);

            // Ejecución
            TareaDTO resultado = tareaService.actualizarTarea(id, actualizarTareaDTO);

            // Verificaciones
            assertEquals("Tarea actualizada", resultado.getTitulo());
            assertEquals("Descripción actualizada", resultado.getDescripcion());
            assertEquals("completada", resultado.getEstado());
        }
    }




