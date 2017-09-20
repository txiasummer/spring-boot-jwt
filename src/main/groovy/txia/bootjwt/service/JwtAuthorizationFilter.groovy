package txia.bootjwt.service

import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import txia.bootjwt.domain.SecurityConstants

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    JwtAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager)
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest,
                                    HttpServletResponse httpResponse,
                                    FilterChain chain) throws IOException, ServletException {
        String header = httpRequest.getHeader(SecurityConstants.HEADER_STRING)

        if (!header || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(httpRequest, httpResponse)
            return
        }

        SecurityContextHolder.context.authentication = getAuthentication(httpRequest)

        chain.doFilter(httpRequest, httpResponse)
    }

    /**
     * Reads the JWT from the Authorization header, and then uses Jwts to validate the token.
     *
     * @param httpRequest
     * @return
     */
    private static UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader(SecurityConstants.HEADER_STRING)
        if (token) {
            String user = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ''))
                    .body
                    .getSubject()

            if (user)
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>())

            return null
        }

        return null
    }
}
