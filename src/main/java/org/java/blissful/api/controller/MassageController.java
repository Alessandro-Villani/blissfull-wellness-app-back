package org.java.blissful.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.blissful.api.dto.MassageDto;
import org.java.blissful.pojo.Massage;
import org.java.blissful.services.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class MassageController {
	
	@Autowired
	private MassageService massageService;
	
	@GetMapping("/massages")
	public ResponseEntity<List<Massage>> getAllMassage(){
		
		List<Massage> massages = massageService.findAll();
		
		return new ResponseEntity<>(massages, HttpStatus.OK);
		
	}
	
	@GetMapping("/massages/{id}")
	public ResponseEntity<Massage> getMassageById(@PathVariable long id){
		
		Optional<Massage> optMassage = massageService.findById(id);
		
		if(optMassage.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Massage massage = optMassage.get();
		
		return new ResponseEntity<>(massage, HttpStatus.OK);
		
	}
	
	@PostMapping("/massages/store")
	public ResponseEntity<Massage> storeMassage(@RequestBody MassageDto massageDto){
		
		System.out.println(massageDto.getName());
		System.out.println(massageDto.getDescription());
		System.out.println(massageDto.getPricePerHour());
		
		Massage massage = new Massage(massageDto.getName(), massageDto.getDescription(), massageDto.getPricePerHour());
		
		System.out.println(massage);
		
		massageService.save(massage);
		
		return new ResponseEntity<>(massage, HttpStatus.OK);
				
	}
	
	@PutMapping("massages/update/{id}")
	public ResponseEntity<Massage> updateMassage(@PathVariable long id, @RequestBody MassageDto massageDto){
		
		System.out.println(massageDto);
		
		Optional<Massage> optMassage = massageService.findById(id);
		
		if(optMassage.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Massage massage = optMassage.get();
		
		massage.setName(massageDto.getName());
		massage.setDescription(massageDto.getDescription());
		massage.setPricePerHour(massageDto.getPricePerHour());
		
		massageService.save(massage);
		
		return new ResponseEntity<>(massage, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/massages/delete/{id}")
	public ResponseEntity<Massage> deleteMassage(@PathVariable long id){
		
		System.out.println(id);
		
		Optional<Massage> optMassage = massageService.findById(id);
		
		if(optMassage.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Massage massage = optMassage.get();
		massageService.delete(massage);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
