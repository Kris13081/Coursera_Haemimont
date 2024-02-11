package bg.haemimont.coursera.service.impl;

import bg.haemimont.coursera.model.entity.CourseEntity;
import bg.haemimont.coursera.model.entity.InstructorEntity;
import bg.haemimont.coursera.repository.CourseRepository;
import bg.haemimont.coursera.service.CourseService;
import bg.haemimont.coursera.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final Scanner scanner;

    private final InstructorService instructorService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             InstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
        scanner = new Scanner(System.in);
    }


    @Override
    public void createCourse() {

        CourseEntity course = new CourseEntity();

        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        course.setName(name);

        System.out.print("Enter Instructor ID: ");
        int instructorId = Integer.parseInt(scanner.nextLine());

        Optional<InstructorEntity> instructor = instructorService.findInstructor(instructorId);

        if (instructor.isPresent()) {
            course.setInstructorId(instructorId);
        } else {
            throw new IllegalArgumentException("Instructor with ID " + instructorId + " does not exist");
        }

        System.out.print("Enter total course time: ");
        int totalCourseTime = Integer.parseInt(scanner.nextLine());
        course.setTotalTime(totalCourseTime);

        System.out.println("Enter course credit: ");
        int courseCredit = Integer.parseInt(scanner.nextLine());
        course.setCredit(courseCredit);

        course.setTimeCreated(LocalDateTime.now());

        courseRepository.save(course);

        System.out.println("Course created successfully");
    }

    @Override
    public CourseEntity findCourse(String name) {
        Optional<CourseEntity> optionalCourse = courseRepository.findByName(name);

        return optionalCourse.orElse(null);
    }

}
