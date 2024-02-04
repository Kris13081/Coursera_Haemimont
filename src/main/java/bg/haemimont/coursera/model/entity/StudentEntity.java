package bg.haemimont.coursera.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pin;

    @Size(max = 50)
    @Column(nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDateTime timeCreated;

    @ManyToMany
    @JoinTable(
            name = "students_courses_xref",
            joinColumns = @JoinColumn(name = "student_pin"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<CourseEntity> courses;
}
