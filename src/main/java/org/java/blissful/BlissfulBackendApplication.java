package org.java.blissful;

import java.time.LocalDate;

import org.java.blissful.auth.services.RoleService;
import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.auth.services.UserService;
import org.java.blissful.pojo.Massage;
import org.java.blissful.pojo.Review;
import org.java.blissful.pojo.auth.Role;
import org.java.blissful.pojo.auth.Therapist;
import org.java.blissful.pojo.auth.User;
import org.java.blissful.services.MassageService;
import org.java.blissful.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlissfulBackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BlissfulBackendApplication.class, args);
	}
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TherapistService therapistService;
	
	@Autowired
	private MassageService massageService;
	
	@Autowired
	private ReviewService reviewService;

	@Override
	public void run(String... args) throws Exception {
		
		String lorem = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque dolor repudiandae officiis delectus. Minima magni facilis commodi harum, dicta quasi neque similique ipsum, error quam, culpa ratione temporibus quidem sunt.";
		String lorem2 = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa enim iste earum neque nihil cupiditate eaque quam, sint porro inventore eveniet architecto? Consectetur quaerat odio eveniet cumque? Consequuntur, quos accusamus!";
		String genericUserImg = "https://t4.ftcdn.net/jpg/00/65/77/27/360_F_65772719_A1UV5kLi5nCEWI0BNLLiFaBPEkUbv5Fv.jpg";
		
		Role therapist = new Role("therapist");
		roleService.save(therapist);
		
		final String psw = new BCryptPasswordEncoder().encode("psw");
		
		User user1 = new User("Chris", psw, LocalDate.parse("1994-04-23"), "https://scontent-fco2-1.xx.fbcdn.net/v/t39.30808-6/291803645_176188801522721_6877680634143498585_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=174925&_nc_ohc=s0JQbW3eTaIAX8BC6Z8&_nc_ht=scontent-fco2-1.xx&oh=00_AfAaRzAKV2K60Mb3DHHggB0ryQSoChRBgecCVt87cd_r7w&oe=64901CF9", therapist);
		User user2 = new User("Lodilyn", psw, LocalDate.parse("1992-01-01"), "https://scontent-fco2-1.xx.fbcdn.net/v/t39.30808-6/326989613_692930959192320_4571851918320569180_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=e3f864&_nc_ohc=OTQ73ROeG04AX-xXZxF&_nc_ht=scontent-fco2-1.xx&oh=00_AfAoAg_DOVTAwDAa2_IgRr67lvJWT4Ug-floV0JgTEaf_Q&oe=648FF16E" ,therapist);
		User user3 = new User("Alejandro", psw, LocalDate.parse("1989-04-23"), "https://scontent-fco2-1.xx.fbcdn.net/v/t1.6435-9/35078299_2444676692217039_7014293751761731584_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=174925&_nc_ohc=9nmF_JVJ29oAX-MwKbZ&_nc_ht=scontent-fco2-1.xx&oh=00_AfCD86xrOlkYhH41BwNaaBLdyrJpSyp4lyQoLsIrBybkmw&oe=64B2BA55");
		User user4 = new User("User1", psw, LocalDate.parse("1999-01-04"), genericUserImg);
		User user5 = new User("User2", psw, LocalDate.parse("1999-01-04"), genericUserImg);
		User user6 = new User("User3", psw, LocalDate.parse("1999-01-04"), genericUserImg);
		
		userService.save(user1);
		userService.save(user2);
		userService.save(user3);
		userService.save(user4);
		userService.save(user5);
		userService.save(user6);
		
		Massage m1 = new Massage("Swedish", "beginner friendly", 450);
		Massage m2 = new Massage("Shiatsu", "super good", 450);
		Massage m3 = new Massage("Pinoy hilot", "para sa pinoy", 500);
		Massage m4 = new Massage("Thai", "eww foreign massage", 600);
		
		massageService.save(m1);
		massageService.save(m2);
		massageService.save(m3);
		massageService.save(m4);
		
		Therapist t1 = new Therapist(user1, LocalDate.now(), lorem, m1, m2, m3, m4);
		Therapist t2 = new Therapist(user2, LocalDate.now(), lorem, m3, m4);
		
		therapistService.save(t1);
		therapistService.save(t2);
		
		Review r1 = new Review(5, lorem2, t1, user3);
		Review r2 = new Review(3, lorem2, t1, user5);
		Review r3 = new Review(4, lorem2, t1, user4);
		Review r4 = new Review(3, lorem2, t1, user6);
		
		reviewService.save(r1);
		reviewService.save(r2);
		reviewService.save(r3);
		reviewService.save(r4);
		
		
		
	}

}
