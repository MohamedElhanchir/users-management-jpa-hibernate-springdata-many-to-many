package ma.enset.jpa;

import ma.enset.jpa.entities.Role;
import ma.enset.jpa.entities.User;
import ma.enset.jpa.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner start(IUserService userService) {
		return args -> {
			User user = new User();
			user.setUsername("admin");
			user.setPassword("1234");
			userService.saveUser(user);

			user = new User();
			user.setUsername("user");
			user.setPassword("1234");
			userService.saveUser(user);

			Stream.of("ADMIN", "USER","STUDENT").forEach(r -> {
				Role role = new Role();
				role.setRoleName(r);
				userService.saveRole(role);
			});

			userService.addRoleToUser("admin", "ADMIN");
			userService.addRoleToUser("admin", "USER");

			userService.addRoleToUser("user", "USER");
			userService.addRoleToUser("user", "STUDENT");

			try {
				User user1 = userService.authenticate("admin", "1234");

				System.out.println(user1.getUserId());
				System.out.println(user1.getUsername());
				user1.getRoles().forEach(r -> {
					System.out.println(r.getRoleName());
				});
			} catch (Exception e) {
				e.printStackTrace();
			}


		};
	}
}
