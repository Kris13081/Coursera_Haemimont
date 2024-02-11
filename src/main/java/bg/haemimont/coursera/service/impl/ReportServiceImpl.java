package bg.haemimont.coursera.service.impl;

import bg.haemimont.coursera.model.entity.StudentEntity;
import bg.haemimont.coursera.service.ReportService;
import bg.haemimont.coursera.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReportServiceImpl implements ReportService {

    private final StudentService studentService;
    private final Scanner scanner;


    public ReportServiceImpl(StudentService studentService) {
        this.studentService = studentService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void createReport() {
        System.out.print("Enter a comma-separated list of student PINs to include in the report. Leave blank if you want to include all students with enough credits: ");

        String input = scanner.nextLine();
        List<StudentEntity> students = new ArrayList<>();
        Pattern containsOnlyDigits = Pattern.compile("^\\d+$");

        if (input.isEmpty()) {
            System.out.print("Enter the minimal required credit: \n");

            String minimumCreditRequired = scanner.nextLine();
            Matcher matcher = containsOnlyDigits.matcher(minimumCreditRequired);

            if (matcher.matches()) {
                int minCredit = Integer.parseInt(minimumCreditRequired);
                students = studentService.getStudentsWithEnoughCredit(minCredit);
            } else {
                System.out.println("The input have to include only digits!");
            }

        } else {
            String[] pins = input.split(",");

            for (String pin : pins) {
                Matcher matcher = containsOnlyDigits.matcher(pin);

                if (matcher.matches() && studentService.existOnNot(Integer.parseInt(pin))) {

                    students.add(studentService.getStudentByPin(Integer.parseInt(pin)));
                } else {
                    System.out.println("Student with PIN: " + pin + " doesn't exists and will be skipped in the report!");
                }
                // Logic for adding the student with the specified PIN to the report
            }
            System.out.print("Enter the minimal required credit: ");

            String minimumCreditRequired = scanner.nextLine();
            Matcher matcher = containsOnlyDigits.matcher(minimumCreditRequired);
        }
    }

}
