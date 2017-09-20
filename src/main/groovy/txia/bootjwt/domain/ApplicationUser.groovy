package txia.bootjwt.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//I would have liked to call this simply "User", but Spring Security framework has a User class... not worth the effort to deal with the confusion
@Entity
class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String username
    String password
}
