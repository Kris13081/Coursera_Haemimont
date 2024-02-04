package bg.haemimont.coursera.service.impl;

import bg.haemimont.coursera.model.entity.InstructorEntity;
import bg.haemimont.coursera.repository.InstructorRepository;
import bg.haemimont.coursera.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    private final Scanner scanner;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
        scanner = new Scanner(System.in);
    }

    @Override
    public void createInstructor() {

        InstructorEntity instructor = new InstructorEntity();

        System.out.println("Enter instructor firstname:");
        String firstName = scanner.nextLine();
        instructor.setFirstName(firstName);

        System.out.println("Enter instructor lastname:");
        String lastName = scanner.nextLine();
        instructor.setLastName(lastName);

        instructor.setTimeCreated(LocalDateTime.now());

        instructorRepository.save(instructor);

        System.out.println("Instructor created successfully!");
    }

    @Override
    public Optional<InstructorEntity> findInstructor(int instructorId) {

        return instructorRepository.findById((long) instructorId);
    }
}
