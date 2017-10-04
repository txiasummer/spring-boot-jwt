package txia.bootjwt.domain

import io.swagger.annotations.ApiModelProperty

import javax.persistence.Column
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

    @Column(name = 'username')
    @ApiModelProperty(notes='username')
    String username

    @Column(name = 'password')
    @ApiModelProperty(notes='Base64 encrypted password')
    String password
}
