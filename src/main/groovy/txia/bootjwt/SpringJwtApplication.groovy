package txia.bootjwt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class SpringJwtApplication {
	static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args)
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		new BCryptPasswordEncoder()
	}
}
//curl -i -H "Content-Type: application/json" -X POST -d '{ "username": "admin", "password": "password" }' http://localhost:8080/login