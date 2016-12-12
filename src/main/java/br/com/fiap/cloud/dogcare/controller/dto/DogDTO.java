package br.com.fiap.cloud.dogcare.controller.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.fiap.cloud.dogcare.domain.Dog;
import br.com.fiap.cloud.dogcare.domain.Vaccine;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode
public class DogDTO {
	
	private int id;
	
	private String name;
	
	private String rg;
	
	private Set<VaccineDTO> vaccines;
	
	
	public Dog toDog() {
		
		final Dog dog = new Dog();
		dog.setName(this.getName());
		dog.setRg(this.getRg());
		
		if(this.getVaccines() != null) {
			dog.setVaccines(this.getVaccines()
				.stream()
				.map(d -> new Vaccine(0, d.getName(), dog))
				.collect(Collectors.toList()));
		}
		
		return dog;
	}
}
