package txia.bootjwt.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import txia.bootjwt.domain.ApplicationUser
import txia.bootjwt.repository.ApplicationUserRepository

import javax.annotation.Resource

@Service
class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    ApplicationUserRepository applicationUserRepository

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username)
        if (!applicationUser)
            throw new UsernameNotFoundException(username)

        return new User(applicationUser.username, applicationUser.password, Collections.emptyList())
    }
}