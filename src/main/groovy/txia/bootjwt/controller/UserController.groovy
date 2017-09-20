package txia.bootjwt.controller

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import txia.bootjwt.domain.ApplicationUser
import txia.bootjwt.repository.ApplicationUserRepository

import javax.annotation.Resource

@RestController
@RequestMapping('users')
class UserController {
    @Resource
    ApplicationUserRepository applicationUserRepository

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder

    @PostMapping('/sign-up')
    void signUp(@RequestBody ApplicationUser user) {
        user.password = bCryptPasswordEncoder.encode(user.password) //Save user with the encrypted password
        applicationUserRepository.save(user)
    }
}
