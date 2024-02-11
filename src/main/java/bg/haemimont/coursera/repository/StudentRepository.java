package bg.haemimont.coursera.repository;

import bg.haemimont.coursera.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    boolean existsByPin(int pin);

    Optional<StudentEntity> getStudentEntityByPin(int pin);

    @Query("SELECT DISTINCT s FROM StudentEntity s JOIN s.courses c WHERE c.timeCreated IS NOT NULL AND c.timeCreated < CURRENT_TIMESTAMP GROUP BY s.pin HAVING SUM(c.credit) > :minCredits")
    List<StudentEntity> findStudentsWithMoreThanMinCredits(@Param("minCredits") int minCredits);

}
