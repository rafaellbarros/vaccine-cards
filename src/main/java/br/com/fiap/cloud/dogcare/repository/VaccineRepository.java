package br.com.fiap.cloud.dogcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.cloud.dogcare.domain.Dog;

@Repository
public interface VaccineRepository extends JpaRepository<Dog, Integer> {
	
	@Query("FROM Dog dog WHERE dog.rg = ?1")
	Optional<Dog> findByRG(String rg);
	
	@Query("FROM Dog dog LEFT JOIN dog.vaccines WHERE dog.id = ?1")
	Dog findByDogId(int id);
}
