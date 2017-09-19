package txia.bootjwt.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository extends JpaRepository<Task, Long> {
}
