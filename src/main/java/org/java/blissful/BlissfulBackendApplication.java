package org.java.blissful;

import java.time.LocalDate;

import org.java.blissful.auth.pojo.Role;
import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.RoleService;
import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.auth.services.UserService;
import org.java.blissful.pojo.Massage;
import org.java.blissful.pojo.Product;
import org.java.blissful.pojo.Review;
import org.java.blissful.services.MassageService;
import org.java.blissful.services.ProductService;
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
	
	@Autowired
	private ProductService productService;

	@Override
	public void run(String... args) throws Exception {
		
		String lorem = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque dolor repudiandae officiis delectus. Minima magni facilis commodi harum, dicta quasi neque similique ipsum, error quam, culpa ratione temporibus quidem sunt.";
		String lorem2 = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa enim iste earum neque nihil cupiditate eaque quam, sint porro inventore eveniet architecto? Consectetur quaerat odio eveniet cumque? Consequuntur, quos accusamus!";
		String profilePicBaseUrl = "images/profile_pics/";
		String productPicBaseUrl = "images/product_pics/";
		String massagePicBaseUrl = "images/massage_pics/";
		
		Role admin = new Role("admin");
		Role therapist = new Role("therapist");
		
		roleService.save(admin);
		roleService.save(therapist);
		
		final String psw = new BCryptPasswordEncoder().encode("psw");
		
		User userAdmin = new User("Marmar", psw, "Marilou", "Canete",LocalDate.parse("1986-04-04"), profilePicBaseUrl + "marmar.jpg", admin);
		User user1 = new User("Cris23", psw, "Creselda", "Canete", LocalDate.parse("1994-04-23"), profilePicBaseUrl + "chris.jpg", therapist);
		User user2 = new User("Lodilyn", psw, "Lodilyn", "Canete", LocalDate.parse("1992-01-01"), profilePicBaseUrl + "lodilyn.jpg" ,therapist);
		User user3 = new User("Ale", psw, "Alessandro", "Villani", LocalDate.parse("1989-04-23"), profilePicBaseUrl + "ale.jpeg");
		User user4 = new User("User1", psw, "User", "1", LocalDate.parse("1999-01-04"), profilePicBaseUrl + "user.jpg");
		User user5 = new User("User2", psw, "User", "2", LocalDate.parse("1999-01-04"), profilePicBaseUrl + "user.jpg");
		User user6 = new User("User3", psw, "User", "3", LocalDate.parse("1999-01-04"), profilePicBaseUrl + "user.jpg");
		User user7 = new User("Arnie", psw, "Arnie", "Sedano", LocalDate.parse("1992-01-01"),profilePicBaseUrl + "arnie.jpg", therapist);
		User user8 = new User("Ella", psw, "Ella", "Doria", LocalDate.parse("1992-01-01"),profilePicBaseUrl + "ella.jpg", therapist);
		User user9 = new User("Coleen", psw, "Coleen", "Morales", LocalDate.parse("1992-01-01"),profilePicBaseUrl + "coleen.jpg", therapist);
		
		userService.save(userAdmin);
		userService.save(user1);
		userService.save(user2);
		userService.save(user3);
		userService.save(user4);
		userService.save(user5);
		userService.save(user6);
		userService.save(user7);
		userService.save(user8);
		userService.save(user9);
		
		Massage m1 = new Massage("Swedish", "Gentle and therapeutic technique originating from Sweden. Uses flowing strokes to relieve muscle tension, improve circulation, and promote relaxation. Popular for its soothing and stress-relieving effects.", 450, massagePicBaseUrl + "swedish.jpg", "rgb(255, 251, 214)");
		Massage m2 = new Massage("Shiatsu", "Japanese technique applying pressure to specific points. Promotes relaxation, relieves tension, and improves energy flow. Uses finger, palm, and elbow techniques for a holistic healing experience.", 450, massagePicBaseUrl + "shiatsu.jpg", "rgb(243, 206, 242)");
		Massage m3 = new Massage("Pinoy hilot", "Traditional Filipino healing art using massage and herbal remedies. Targets imbalances, promotes relaxation, and restores well-being. Combines hand techniques, oils, and natural elements for a holistic experience.", 500, massagePicBaseUrl + "hilot.jpg", "rgb(185, 212, 186)");
		Massage m4 = new Massage("Thai", "Ancient Thai healing art combining acupressure, stretching, and rhythmic compressions. Promotes relaxation, relieves muscle tension, and restores balance through gentle pressure and assisted yoga-like movements.", 600, massagePicBaseUrl + "thai.jpg", "rgb(255, 205, 179)");
		
		massageService.save(m1);
		massageService.save(m2);
		massageService.save(m3);
		massageService.save(m4);
		
		Therapist t1 = new Therapist(user1, "Chris", LocalDate.now(), "Cris is a skilled massage therapist who combines various techniques with mindfulness and aromatherapy for a transformative experience that promotes inner peace and holistic healing. With a calming presence and intuitive touch, her sessions create a serene environment for relaxation and rejuvenation.", m1, m2, m3, m4);
		Therapist t2 = new Therapist(user2, "Lodilyn", LocalDate.now(), "Renowned for her expertise in deep tissue massage, Lodilyn addresses specific areas of tension and chronic pain, providing relief and improved mobility. Clients appreciate her strong hands and attentive approach, leaving them feeling revitalized and free from muscular tension.", m3, m4);
		Therapist t3 = new Therapist(user7, "Arnie", LocalDate.now(), "Arnie's healing touch incorporates Reiki and energy work, fostering relaxation, stress reduction, and overall well-being. Through her sessions, clients experience a deep sense of connection and spiritual healing, creating a unique and transformative massage experience.", m1, m2, m3, m4);
		Therapist t4 = new Therapist(user8, "Ella", LocalDate.now(), "With a gentle touch and soothing essential oils, Ella provides a serene escape, offering relaxation from daily stress. Her attention to detail and personalized approach cater to each client's relaxation needs, leaving them feeling refreshed, renewed, and emotionally uplifted after each session.", m1, m2, m4);
		Therapist t5 = new Therapist(user9, "Coleen", LocalDate.now(), "Coleen specializes in safe and supportive prenatal massage, offering relief from pregnancy discomforts and promoting relaxation for expectant mothers. With a deep understanding of pregnancy needs, her nurturing touch creates a serene and enjoyable massage experience, providing comfort and support during this transformative phase of life.", m2, m3, m4);
		
		therapistService.save(t1);
		therapistService.save(t2);
		therapistService.save(t3);
		therapistService.save(t4);
		therapistService.save(t5);
		
		/*
		Review r1 = new Review(5, lorem2, t1, user3);
		Review r2 = new Review(3, lorem2, t1, user5);
		Review r3 = new Review(4, lorem2, t1, user4);
		Review r4 = new Review(3, lorem2, t1, user6);
		
		reviewService.save(r1);
		reviewService.save(r2);
		reviewService.save(r3);
		reviewService.save(r4);
		*/
		
		Product p1 = new Product("Aromatherapy Massage Oil", "Indulge in the soothing scents and nourishing properties of our aromatherapy massage oil. Formulated to promote relaxation and enhance the massage experience.", productPicBaseUrl + "product1.jpg", 200, 10);
		Product p2 = new Product("Massage Roller Ball", "Release muscle tension and knots with our ergonomic massage roller ball. Its compact design allows for targeted self-massage anytime, anywhere.", productPicBaseUrl + "product2.jpg", 300, 6);
		Product p3 = new Product("Heated Massage Cushion", "Experience the ultimate comfort with our heated massage cushion. It provides a gentle yet invigorating massage to ease tension and promote relaxation.", productPicBaseUrl + "product3.jpg", 450, 0);
		Product p4 = new Product("Hot Stone Massage Kit",  "Create a spa-like experience with our hot stone massage kit. Smooth, heated stones soothe muscles and promote a deep sense of relaxation.", productPicBaseUrl + "product4.jpg", 150, 3);
		Product p5 = new Product("Massage Gift Set",  "Surprise someone with our indulgent massage gift set, featuring massage oils, scented candles, and other relaxation essentials.", productPicBaseUrl + "product5.jpg", 600, 8);
		Product p6 = new Product("Massage Foam Roller", "Improve flexibility and reduce muscle tightness with our high-density massage foam roller. Perfect for pre and post-workout recovery.", productPicBaseUrl + "product6.jpg", 400, 20);
		
		productService.save(p1);
		productService.save(p2);
		productService.save(p3);
		productService.save(p4);
		productService.save(p5);
		productService.save(p6);
		
		
		
	}

}
