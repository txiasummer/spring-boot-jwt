package txia.bootjwt.domain

import io.swagger.annotations.ApiModelProperty

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes='The database-generated Task ID')
	Long id

	@Column(name = 'description')
	@ApiModelProperty(notes='Description of the Task')
	String description
}
