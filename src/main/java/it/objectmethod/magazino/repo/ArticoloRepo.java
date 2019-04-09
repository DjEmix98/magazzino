package it.objectmethod.magazino.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.magazino.entity.Articolo;

@Repository
public interface ArticoloRepo extends JpaRepository<Articolo,Long> {

	
	
}
