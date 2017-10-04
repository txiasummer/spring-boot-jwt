package txia.bootjwt.config

import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import txia.bootjwt.domain.SecurityConstants
import txia.bootjwt.service.JwtAuthenticationFilter
import txia.bootjwt.service.JwtAuthorizationFilter

import javax.annotation.Resource

@EnableWebSecurity
class WebSecurity extends WebSecurityConfigurerAdapter {
    @Resource
    UserDetailsService userDetailsService

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder

    /**
     * Configures the security setting by:
     *  1) setting the only public endpoint to SIGN_UP_URL
     *  2) configuring CORS (Cross-Origin Resource Sharing) support through http.cors()
     *  3) adding two custom security filters (JwtAuthenticationFilter & JwtAuthorizationFilter) in the Spring Security filter chain.
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.API_DOCUMENTATION_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.API_DOCUMENTATION_URL2).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager: super.authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(super.authenticationManager()))
    }

    /**
     * Define a custom implementation of UserDetailsService to load user-specific data in the security framework.
     * Also use this method to set the encrypt method used by our application (BCryptPasswordEncoder).
     *
     * @param authManagerBuilder
     * @throws Exception
     */
    @Override
    void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }
}
