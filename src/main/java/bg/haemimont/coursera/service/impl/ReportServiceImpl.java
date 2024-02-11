package bg.haemimont.coursera.service.impl;

import bg.haemimont.coursera.model.entity.StudentEntity;
import bg.haemimont.coursera.service.CsvFormatterService;
import bg.haemimont.coursera.service.ReportService;
import bg.haemimont.coursera.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReportServiceImpl implements ReportService {

    private final StudentService studentService;

    private final CsvFormatterService csvFormatterService;
    private final Scanner scanner;

    public ReportServiceImpl(StudentService studentService,
                             CsvFormatterService csvFormatterService) {
        this.studentService = studentService;
        this.csvFormatterService = csvFormatterService;
        this.scanner = new Scanner(System.in);
    }

    @Override

    public void createReport() {
        System.out.print("Enter a comma-separated list of student PINs to include in the report. Leave blank if you want to include all students with enough credits: ");

        String input = scanner.nextLine();
        List<StudentEntity> students = new ArrayList<>();
        Pattern containsOnlyDigits = Pattern.compile("^\\d+$");
        LocalDate startingDate = LocalDate.now();

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

            if (!matcher.matches()) {
                System.out.println("The input have to include only digits!");
            } else {

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
                System.out.println("Enter starting date for collecting the credit (dd:MM:YYYY): ");
                String dateInput = scanner.nextLine();
                try {
                    startingDate = LocalDate.parse(dateInput, dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter date in the format dd:MM:yyyy");
                }

                System.out.println("Chose data format for the report: csv or html (if you don't specify the type the report will be in both of them!): ");
                String reportFormat = scanner.nextLine();

                while (reportFormat.equals("csv") || reportFormat.equals("html")) {

                    if (reportFormat.equals("csv")) {
                        System.out.println("Enter exact filepath for saving the report: ");
                        String filePath = scanner.nextLine();
                        csvFormatterService.createReport (students, minimumCreditRequired, startingDate, filePath);
                    }

                    reportFormat = scanner.nextLine();
                }
            }

        }


    }

}
