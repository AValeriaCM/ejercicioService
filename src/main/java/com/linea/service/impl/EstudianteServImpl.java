package com.linea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.linea.entity.Estudiante;
import com.linea.exception.ArgumentRequiredException;
import com.linea.exception.BusinessLogicException;
import com.linea.exception.ModelNotFoundException;
import com.linea.repo.IEstudianteRepo;
import com.linea.service.IEstudianteService;

@Service
public class EstudianteServImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepo repo;

	@Override
	public List<Estudiante> retornar() {
		return repo.findAll(Sort.by(Sort.Direction.ASC, "apellido"));
	}

	@Override
	public Estudiante retornarId(Integer id) {
		Estudiante estudiante = repo.findById(id).orElseThrow(() 
				-> new ModelNotFoundException("Estudiante no encontrado"));
		return estudiante;
	}

	@Override
	public void guardar(Estudiante estudiante) {
		Estudiante est = repo.filtrarCedula(estudiante.getCedula());
		if(est==null) {
			repo.save(estudiante);
		}else {
			throw new BusinessLogicException("La cedula ingresada ya existe");
		}

	}

	@Override
	public void editar(Estudiante estudiante) {
		List<Estudiante> listaEst = repo.findAll();
		
		for (Estudiante estudiante2 : listaEst) {
			if(estudiante2.getId()!=estudiante.getId() && estudiante2.getCedula().equals(estudiante.getCedula())) {
				throw new BusinessLogicException("No tiene permisos para editar");
			}
		}
		if(estudiante.getId() == null) {
			throw new ArgumentRequiredException("Por favor ingrese ID");
		}
		this.retornarId(estudiante.getId());
		repo.save(estudiante);
	}

	@Override
	public void eliminar(Integer idEstudiante) {
		if (!repo.existsById(idEstudiante)) {
			throw new ModelNotFoundException("El estudiante que desea eliminar no existe");
		} else {
			repo.deleteById(idEstudiante);
		}

	}

	@Override
	public Estudiante filtrarCedula(String cedula) {
		if (cedula == null) {
			throw new ModelNotFoundException("Ponga una cedula");
		} else {
			Estudiante  estudi= repo.filtrarCedula(cedula);
			if(estudi==null) {
					throw new ModelNotFoundException("No existe un estudiante con esa cedula");
				}else {
					return estudi;
				}
			}
		}

	@Override
	public Page<Estudiante> listarPorNombre(int page, int size, String nombre) {
		return repo.findByNombreIgnoreCase(nombre, PageRequest.of(page, size, Sort.by("apellido").ascending()));
	}

	@Override
	public Page<Estudiante> buscarPorNombreApellido(int page, int size, String nombre, String apellido) {
		return repo.buscarPorNombreApellido(nombre, apellido, PageRequest.of(page, size, Sort.by("cedula").ascending()));
	}

	//@Override
	//public List<Estudiante> findByApellidoOrderBySeatNumberAsc(String apellido, Sort sort) {
		//return repo.findByApellidoOrderBySeatNumberAsc(apellido, sort);
	//}
	
	
	
	}
	
	

//	@Autowired
//	private EstudianteRepo estudianteRepo;
//
//	@Override
//	public List<EstudianteDTO> retornarListaEst() {
//		List<EstudianteDTO> listaEst = new ArrayList<>();
//		listaEst=estudianteRepo.consultar();
//		return listaEst;
//	}
//
//	@Override
//	public EstudianteDTO retornarEstudianteId(Long id) {
//		
//		EstudianteDTO encontrado=null;
//		List<EstudianteDTO> listaEst = new ArrayList<>();
//		listaEst=estudianteRepo.consultar();
//		for(EstudianteDTO est : listaEst) {
//			if(est.getId()==id) {
//				encontrado = new EstudianteDTO(est.getId(),est.getNombre(),est.getApellido(),est.getCarrera());
//        		break;
//			}
//			else {
//				throw new ModelNotFoundException("No existe ID");
//				}
//		}
//		
//		return encontrado;
//	}
//
//	@Override
//	public EstudianteDTO guardarEstudiante(EstudianteDTO estudiante) {
//		List<EstudianteDTO> listaEst = new ArrayList<>();
//		listaEst=estudianteRepo.consultar();
//		for(EstudianteDTO estu : listaEst) {
//			if(estu.getId() == estudiante.getId()) { 
//				throw new ModelNotFoundException("No se puede crear porque el ID ya existe");
//			}
//		}
//		estudianteRepo.crear(estudiante);
//		return estudiante;
//	}
//
//	@Override
//	public EstudianteDTO editarEstudiante(EstudianteDTO estudiante) { 
//		List<EstudianteDTO> listaEst = new ArrayList<>();
//		EstudianteDTO editado = new EstudianteDTO(0,null,null,null);
//		boolean entra=false;
//		listaEst=estudianteRepo.consultar();
//		for(EstudianteDTO estu : listaEst) {
//			if(estu.getId() == estudiante.getId()) {
//				entra=true;
//				listaEst.remove(estu);
//				break;
//			}
//		}
//		if(entra == false)
//			throw new ModelNotFoundException("No se encontro el ID para editar");
//		editado.setId(estudiante.getId());
//		editado.setNombre(estudiante.getNombre());
//		editado.setApellido(estudiante.getApellido());
//		editado.setCarrera(estudiante.getCarrera());		
//		
//		listaEst.add(editado);
//		estudianteRepo.editar(listaEst);
//		return estudiante;
//	}
//
//	@Override
//	public void eliminarEstudiante(Long id) {
//		List<EstudianteDTO> listaEst = new ArrayList<>();
//		listaEst=estudianteRepo.consultar();
//		boolean entra =false;
//		for(EstudianteDTO est : listaEst) {
//			if(est.getId() == id) {
//				entra=true;
//				listaEst.remove(est);
//				estudianteRepo.editar(listaEst);
//				break;
//			}
//		}
//		if(entra == false) {
//			throw new ModelNotFoundException("No se encontro el ID");
//		}
//	}
//
//	@Override
//	public void guardarPorBD(EstudianteDTO estudiante)throws SQLException {
//		estudianteRepo.crearBD(estudiante);
//		
//	}
//
//	@Override
//	public List<EstudianteDTO> retornarTodosBD()throws SQLException {
//		List<EstudianteDTO> listaEst = new ArrayList<>();
//		listaEst=estudianteRepo.consultar();
//		return listaEst;
//	}


