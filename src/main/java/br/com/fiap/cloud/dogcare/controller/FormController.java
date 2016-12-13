package br.com.fiap.cloud.dogcare.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.cloud.dogcare.controller.dto.DogDTO;
import br.com.fiap.cloud.dogcare.controller.dto.VaccineDTO;
import br.com.fiap.cloud.dogcare.domain.Dog;
import br.com.fiap.cloud.dogcare.service.VaccineService;
import br.com.fiap.cloud.dogcare.service.exception.APIException;


@RestController
public class FormController {

	@Autowired VaccineService vaccineServiceImpl;


	@RequestMapping(value = "/dogcare", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	protected ResponseEntity<DogDTO> create(@RequestBody DogDTO dto, UriComponentsBuilder uriBuilder) throws APIException {
		
		final Dog dog = vaccineServiceImpl
				.register(dto.toDog());
		
		dto.setId(dog.getId());
		
		return ResponseEntity.ok(dto);
	}


	@RequestMapping(value = "/dogcare/{id}", method = RequestMethod.GET)
	protected ResponseEntity<DogDTO> getById(@PathVariable int id) {
		
		final Optional<Dog> opt = vaccineServiceImpl.getById(id);
		
		if(opt.isPresent()) {
			
			final Dog dog = opt.get();
			final DogDTO dto = new DogDTO();
			
			dto.setId(dog.getId());
			dto.setName(dog.getName());
			dto.setRg(dog.getName());
			dto.setVaccines(dog.getVaccines().stream().map(d -> new VaccineDTO(d.getName())).collect(Collectors.toSet()));
			
			return ResponseEntity.ok(dto);
		}

		return ResponseEntity.status(404).body(null);
	}
}
