package bg.haemimont.coursera.model.entity;

import bg.haemimont.coursera.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "courses")
public class CourseEntity extends BaseEntity {

    @Size(max = 150)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int instructorId;

    @Column(columnDefinition = "TINYINT")
    private int totalTime;

    @Column(columnDefinition = "TINYINT")
    private int credit;

    @Column(nullable = false)
    private LocalDateTime timeCreated;

}
