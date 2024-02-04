package bg.haemimont.coursera.controller;

import bg.haemimont.coursera.service.CourseService;
import bg.haemimont.coursera.service.InstructorService;
import bg.haemimont.coursera.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class InputController implements CommandLineRunner {

    private final StudentService studentService;
    private final InstructorService instructorService;
    private final CourseService courseService;
    private final Scanner scanner;

    public InputController(StudentService studentService,
                           InstructorService instructorService,
                           CourseService courseService) {
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.courseService = courseService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {

        String command = "";
        String exitCommand = "$STOP";

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Welcome to Coursera CLI! \n \n For instructions start with command: $help\n");

        while (!command.equals(exitCommand)) {

            switch (command) {
                case "$help":
                    showInstructions();
                    break;
                case "CS":
                    createStudent();
                    break;
                case "CI":
                    createInstructor();
                    break;
                case "CC":
                    createCourse();
                    break;
            }

            command = scanner.nextLine();
        }

    }
    public void createStudent() {
        studentService.createStudent();
    }

    public void createInstructor() {
        instructorService.createInstructor();
    }

    private void createCourse() {
        courseService.createCourse();
    }

    private void showInstructions() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Available commands:");
        System.out.println("CS - Create Student");
        System.out.println("CI - Create Instructor");
        System.out.println("CC - Create Course");
        System.out.println("$STOP - Exit the program");
        System.out.println("-----------------------------------------------------------------------------");
    }
}
