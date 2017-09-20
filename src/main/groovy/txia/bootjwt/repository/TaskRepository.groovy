package txia.bootjwt.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import txia.bootjwt.domain.Task

@Repository
interface TaskRepository extends JpaRepository<Task, Long> {}
