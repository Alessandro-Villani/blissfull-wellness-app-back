package org.java.blissful.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.java.blissful.api.controller.UserController.FileUploadUtil;
import org.java.blissful.api.dto.MassageDto;
import org.java.blissful.api.dto.ProductDto;
import org.java.blissful.api.dto.TherapistDto;
import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.pojo.Massage;
import org.java.blissful.pojo.Product;
import org.java.blissful.services.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class MassageController {
	
	@Autowired
	private MassageService massageService;
	
	@Autowired
	private TherapistService therapistService;
	
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
	
	@Value("${upload.dir}")
    private String uploadDir;
	
	@PostMapping("/massages/store")
	public ResponseEntity<Massage> storeMassage(@RequestParam("file") MultipartFile file, @RequestParam("massage") String massageDtoJson){
		
		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        MassageDto massageDto = objectMapper.readValue(massageDtoJson, MassageDto.class);

	        // Esegui l'upload dell'immagine del profilo
	        String originalFileName = file.getOriginalFilename();
	        String fileExtension = FilenameUtils.getExtension(originalFileName);
	        String fileName = UUID.randomUUID().toString() + "." + fileExtension;
	        String uploadPath = uploadDir + "/massage_pics/" + fileName;
	        FileUploadUtil.saveFile(uploadPath, file);
	        String relativePath = "images/massage_pics/" + fileName;
	        
	        Massage massage = new Massage(massageDto.getName(), massageDto.getDescription(), massageDto.getPricePerHour(), relativePath, massageDto.getColor());
	        
	        List<Therapist> therapists = new ArrayList<>();
	        
	        for(long therapistId : massageDto.getTherapists()) {
	        	
	        	Therapist therapist = therapistService.findById(therapistId).get();
	        	
	        	therapist.addMassage(massage);
	        	
	        	therapistService.save(therapist);
	        	
	        	therapists.add(therapist);
	        	
	        }
	        
	        massage.setTherapists(therapists);
	        
	        System.out.println(massage.getTherapists());
			
	        massageService.save(massage);
			
			return new ResponseEntity<>(massage, HttpStatus.OK);
			
			} catch (Exception e) {
		    	e.printStackTrace();
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
				
	}
	
	@PutMapping("massages/update/{id}")
	public ResponseEntity<Massage> updateMassage(@PathVariable long id, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("massage") String massageDtoJson){
		
	try {
		Optional<Massage> optMassage = massageService.findById(id);
		
		if(optMassage.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Massage massage = optMassage.get();
		
		ObjectMapper objectMapper = new ObjectMapper();
        MassageDto massageDto = objectMapper.readValue(massageDtoJson, MassageDto.class);
		
		massage.setName(massageDto.getName());
		massage.setDescription(massageDto.getDescription());
		massage.setPricePerHour(massageDto.getPricePerHour());
		massage.setColor(massageDto.getColor());
		
		List<Therapist> therapists = new ArrayList<>();
		
		List<Long> therapistIds = new ArrayList<>();
		
		for(long therapistId : massageDto.getTherapists()) {
			
			therapistIds.add(therapistId);
			
		}
		
		List<Therapist> allTherapists = therapistService.findAll();
		
		for(Therapist therapist : allTherapists) {
			
			if(therapistIds.contains(therapist.getId()) && !therapist.getMassages().contains(massage)) {
				
				therapist.addMassage(massage);
	        	
	        	therapistService.save(therapist);
	        	
	        	therapists.add(therapist);
				
			} else if(!therapistIds.contains(therapist.getId()) && therapist.getMassages().contains(massage)) {
				
				therapist.removeMassage(massage);
				
				therapistService.save(therapist);
				
			}
			
		}
        
        massage.setTherapists(therapists);
        
        System.out.println(massage.getTherapists());
		
		if(file != null) {
			String originalFileName = file.getOriginalFilename();
	        String fileExtension = FilenameUtils.getExtension(originalFileName);
	        String fileName = UUID.randomUUID().toString() + "." + fileExtension;
	        String uploadPath = uploadDir + "/massage_pics/" + fileName;
	        FileUploadUtil.saveFile(uploadPath, file);
	        String relativePath = "images/massage_pics/" + fileName;
	        
	        massage.setImageUrl(relativePath);
		}
		
		massageService.save(massage);
		
		return new ResponseEntity<>(massage, HttpStatus.OK);
		
	} catch (Exception e) {
    	e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
		
	}
	
	@DeleteMapping("/massages/delete/{id}")
	public ResponseEntity<Massage> deleteMassage(@PathVariable long id){
		
		System.out.println(id);
		
		Optional<Massage> optMassage = massageService.findById(id);
		
		if(optMassage.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Massage massage = optMassage.get();
		
		for(Therapist therapist : massage.getTherapists()) {
			
			therapist.getMassages().remove(massage);
			
		}
		
		massageService.delete(massage);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
