package bg.haemimont.coursera.service;

import bg.haemimont.coursera.model.entity.StudentEntity;

import java.util.List;

public interface StudentService {
    void createStudent();

    boolean existOnNot(int pin);

    StudentEntity getStudentByPin(int pin);

    List<StudentEntity> getStudentsWithEnoughCredit(int minCredit);
}
