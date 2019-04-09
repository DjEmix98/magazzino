package it.objectmethod.magazino.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.magazino.entity.Lotto;
@Repository
public interface LottoRepo extends JpaRepository<Lotto, Long> {

	
}
