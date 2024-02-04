package bg.haemimont.coursera.service;

import bg.haemimont.coursera.model.entity.InstructorEntity;

import java.util.Optional;

public interface InstructorService {
    void createInstructor();

    Optional<InstructorEntity> findInstructor(int instructorId);
}
