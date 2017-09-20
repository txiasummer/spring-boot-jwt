package txia.bootjwt.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import txia.bootjwt.domain.ApplicationUser

@Repository
interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>{
    ApplicationUser findByUsername(String username)
}