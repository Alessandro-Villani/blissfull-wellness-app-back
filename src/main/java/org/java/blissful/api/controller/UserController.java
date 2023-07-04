package org.java.blissful.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.java.blissful.api.dto.UserDto;
import org.java.blissful.auth.pojo.Role;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/users")
	public List<User> getAllUserNonTherapists(@RequestParam(required = false) String username){
		
		List<User> users = username != null ? userService.findByUsernameContaining(username) : userService.findAll();
		
		users = users.stream()
		        .filter(user -> user.getRoles().stream()
		        .noneMatch(role -> role.getName().equals("therapist") || role.getName().equals("admin")))
		        .collect(Collectors.toList());
		
		return users;
		
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id){
		
		User user = userService.findById(id).get();
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> logIn(@RequestBody UserDto userDto){
		
		
		System.out.println(userDto.getPassword());
		
		User user = (User) userService.loadUserByUsername(userDto.getUsername());
		
		System.out.println(user.getPassword());
		
		System.out.println(passwordEncoder.matches(userDto.getPassword(), user.getPassword()));
		
		if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
			
			return new ResponseEntity<>(user, HttpStatus.OK);
			
		}
		
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
	}
	
	@Value("${upload.dir}")
    private String uploadDir;
	
	/*@PostMapping("/signin")
	public ResponseEntity<User> signIn(@RequestBody UserDto userDto){
		
		final String password = new BCryptPasswordEncoder().encode(userDto.getPassword());
		
		User user = new User(userDto.getUsername(), password, userDto.getFirstName(), userDto.getLastName(), userDto.getDateOfBirth(), userDto.getProfilePic());
		
		userService.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
		
	}*/
	
	@PostMapping("/signin")
	public ResponseEntity<User> signIn(@RequestParam("file") MultipartFile file, @RequestParam("user") String userDtoJson) {
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        UserDto userDto = objectMapper.readValue(userDtoJson, UserDto.class);

	        // Esegui l'upload dell'immagine del profilo
	        String originalFileName = file.getOriginalFilename();
	        String fileExtension = FilenameUtils.getExtension(originalFileName);
	        String fileName = UUID.randomUUID().toString() + "." + fileExtension;
	        String uploadPath = uploadDir + "/profile_pics/" + fileName;
	        FileUploadUtil.saveFile(uploadPath, file);
	        String relativePath = "images/profile_pics/" + fileName;

	        // Creazione dell'utente con l'immagine del profilo
	        final String password = new BCryptPasswordEncoder().encode(userDto.getPassword());
	        User user = new User(userDto.getUsername(), password, userDto.getFirstName(), userDto.getLastName(), userDto.getDateOfBirth(), relativePath);

	        // Salva l'utente nel database
	        userService.save(user);

	        return new ResponseEntity<>(user, HttpStatus.OK);
	    } catch (Exception e) {
	        // Gestione degli errori
	    	e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	public class FileUploadUtil {

	    public static void saveFile(String uploadPath, MultipartFile file) throws IOException {
	        File directory = new File(uploadPath).getParentFile();
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	        file.transferTo(new File(uploadPath));
	    }
	}
	
	
}
