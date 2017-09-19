package txia.bootjwt.task

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id

	@Column(name = 'description')
	String description

	protected Task() { }

	Task(String description) {
		this.description = description
	}
}
