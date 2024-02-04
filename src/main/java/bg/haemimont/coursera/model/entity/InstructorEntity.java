package bg.haemimont.coursera.model.entity;

import bg.haemimont.coursera.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructors")
public class InstructorEntity extends BaseEntity {

    @Size(max = 100)
    @Column(nullable = false)
    private String firstName;

    @Size(max = 100)
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDateTime timeCreated;

}
