package org.java.blissful.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.java.blissful.api.dto.BookingDto;
import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.auth.services.UserService;
import org.java.blissful.pojo.Booking;
import org.java.blissful.pojo.Massage;
import org.java.blissful.services.BookingService;
import org.java.blissful.services.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TherapistService therapistService;
	
	@Autowired
	private MassageService massageService;
	
	@GetMapping("bookings")
	public ResponseEntity<List<Booking>> findAll(){
		
		List<Booking> bookings = bookingService.findAll();
		
		return new ResponseEntity<>(bookings, HttpStatus.OK);
		
	}
	
	@GetMapping("bookings/user/{id}")
	public ResponseEntity<List<Booking>> findByUserId(@PathVariable long id){
		
		List<Booking> userBookings = bookingService.findByUserId(id);
		
		return new ResponseEntity<>(userBookings, HttpStatus.OK);
		
	}
	
	@GetMapping("bookings/therapist/{id}")
	public ResponseEntity<List<Booking>> findByTherapistId(@PathVariable long id, @RequestParam(required = false) String date){
		
		List<Booking> therapistBookings = bookingService.findByTherapistId(id);
		
		if(!date.isEmpty()) {
			LocalDate searchDate = LocalDate.parse(date);
			therapistBookings = therapistBookings.stream().filter(b -> b.getDate().isEqual(searchDate)).toList();
		}
		
		return new ResponseEntity<>(therapistBookings, HttpStatus.OK);
		
	}
	
	@GetMapping("bookings/massage/{id}")
	public ResponseEntity<List<Booking>> findByMassageId(@PathVariable long id){
		
		List<Booking> massageBookings = bookingService.findByMassageId(id);
		
		return new ResponseEntity<>(massageBookings, HttpStatus.OK);
		
	}
	
	@PostMapping("bookings/store")
	public ResponseEntity<Booking> storeBooking(@RequestBody BookingDto bookingDto){
		
		User user = userService.findById(bookingDto.getUserId()).get();
		
		Therapist therapist = therapistService.findById(bookingDto.getTherapistId()).get();
		
		Massage massage = massageService.findById(bookingDto.getMassageId()).get();
		
		Booking booking = bookingDto.isHomeService() ?
				new Booking(LocalDate.parse(bookingDto.getDate()), bookingDto.getStartHour(), bookingDto.getEndHour(), bookingDto.getTotalHours(), bookingDto.isHomeService(), bookingDto.getPrice(), user, therapist, massage, bookingDto.getAddress())
				:
				new Booking(LocalDate.parse(bookingDto.getDate()), bookingDto.getStartHour(), bookingDto.getEndHour(), bookingDto.getTotalHours(), bookingDto.isHomeService(), bookingDto.getPrice(), user, therapist, massage);
		
		bookingService.save(booking);
		
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

}
