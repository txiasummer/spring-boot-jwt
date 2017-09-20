package txia.bootjwt.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import txia.bootjwt.domain.ApplicationUser
import txia.bootjwt.domain.SecurityConstants

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager

    /**
     * Parse the user's credentials and issue them to the AuthenticationManager
     *
     * @param httpRequest
     * @param httpResult
     * @return
     * @throws AuthenticationException
     */
    @Override
    Authentication attemptAuthentication(HttpServletRequest httpRequest,
                                         HttpServletResponse httpResult) throws AuthenticationException {
        try {
            ApplicationUser applicationUser = new ObjectMapper().readValue(httpRequest.inputStream, ApplicationUser)

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(applicationUser.username, applicationUser.password, []))
        } catch (IOException e) {
            throw new RuntimeException(e)
        }
    }

    /**
     * Gets called upon successful authentication, and generates a JWT, which gets added to the HttpResult header
     *
     * @param httpRequest
     * @param httpResult
     * @param filterChain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest httpRequest,
                                            HttpServletResponse httpResult,
                                            FilterChain filterChain,
                                            Authentication auth) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject((auth.principal as User).username)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET) //TODO: use RS256 signature algorithm instead
                .compact()

        httpResult.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token)
    }
}
