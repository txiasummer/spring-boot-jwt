package txia.bootjwt.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class AppConfig {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        new BCryptPasswordEncoder()
    }

    /**
     * For CORS support, we permit requests from any source (/**).
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration('/**', new CorsConfiguration().applyPermitDefaultValues()) //allow requests from any source

        return source
    }
}
