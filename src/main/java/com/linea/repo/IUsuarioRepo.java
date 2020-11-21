package com.linea.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linea.entity.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	Usuario findByNick(String nick);
	
	Usuario findByDocumento(String documento);

}
