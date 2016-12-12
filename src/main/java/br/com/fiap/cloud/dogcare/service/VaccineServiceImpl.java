package br.com.fiap.cloud.dogcare.service;

import static java.util.Objects.nonNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.cloud.dogcare.domain.Dog;
import br.com.fiap.cloud.dogcare.repository.VaccineRepository;
import br.com.fiap.cloud.dogcare.service.exception.DocumentConflictException;

@Service("vaccineServiceImpl") @Transactional
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private VaccineRepository vaccineRepository;
	
	VaccineServiceImpl() {
		super();
	}

	
	@Override
	public Dog register(Dog dog) throws DocumentConflictException {
		
		if(nonNull(dog.getRg())) {
			if(vaccineRepository.findByRG(dog.getRg()).isPresent()) {
				throw new DocumentConflictException();
			}
		}
		
		vaccineRepository.save(dog);
		return dog;
	}


	@Override
	public Optional<Dog> getById(int id) {		
		return Optional
				.ofNullable(vaccineRepository.findOne(id));
	}
}
