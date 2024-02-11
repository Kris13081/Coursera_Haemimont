package bg.haemimont.coursera.service.impl;

import bg.haemimont.coursera.model.entity.CourseEntity;
import bg.haemimont.coursera.model.entity.StudentEntity;
import bg.haemimont.coursera.repository.StudentRepository;
import bg.haemimont.coursera.service.CourseService;
import bg.haemimont.coursera.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final CourseService courseService;

    private final Scanner scanner;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void createStudent() {

        StudentEntity student = new StudentEntity();

        System.out.print("Enter student firstname: ");

        String firstName = scanner.nextLine();
        student.setFirstName(firstName);

        System.out.print("Enter student lastname: ");

        String lastName = scanner.nextLine();
        student.setLastName(lastName);

        student.setTimeCreated(LocalDateTime.now());

        System.out.print("Enter courses names separated by comma: ");

        String coursesLineInput = scanner.nextLine();
        List<String> coursesNames = courseNamesExtractor(coursesLineInput);
        List<CourseEntity> courseEntities = new ArrayList<>();

        coursesNames.forEach(course -> {

            if (courseService.findCourse(course) == null) {
                throw new RuntimeException("Course not found: " + course);
            } else {
                courseEntities.add(courseService.findCourse(course));
            }
                });

        student.setCourses(courseEntities);

        studentRepository.save(student);

        System.out.println("Student created successfully");

    }

    @Override
    public boolean existOnNot(int pin) {

        return studentRepository.existsByPin(pin);
    }

    @Override
    public StudentEntity getStudentByPin(int pin) {

        Optional<StudentEntity> optionalStudent =  studentRepository.getStudentEntityByPin(pin);

        return optionalStudent.orElse(null);
    }

    @Override
    public List<StudentEntity> getStudentsWithEnoughCredit(int minCredit) {

        return studentRepository.findStudentsWithMoreThanMinCredits(minCredit);
    }

    @Override
    public int getTotalCreditForStudent(StudentEntity student) {

        return student.getCourses().stream()
                .mapToInt(CourseEntity::getCredit)
                .sum();

    }

    private List<String> courseNamesExtractor(String coursesLineInput) {
        return Arrays.stream(coursesLineInput.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }


}
