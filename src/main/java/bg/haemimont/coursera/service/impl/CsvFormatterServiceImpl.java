package bg.haemimont.coursera.service.impl;

import bg.haemimont.coursera.model.entity.StudentEntity;
import bg.haemimont.coursera.model.entity.CourseEntity; // Assuming you have a CourseEntity class
import bg.haemimont.coursera.service.CourseService;
import bg.haemimont.coursera.service.CsvFormatterService;
import bg.haemimont.coursera.service.StudentService;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CsvFormatterServiceImpl implements CsvFormatterService {

    private final StudentService studentService;
    private final CourseService courseService;
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE_CHARACTER = '"';
    private static final char DEFAULT_ESCAPE_CHARACTER = '\\';
    private static final String DEFAULT_LINE_END = "\n";

    public CsvFormatterServiceImpl(StudentService studentService,
                                   CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public void createReport(List<StudentEntity> students, String minimumCreditRequired, LocalDate startingDate, String filePath) {
        try (CSVWriter writer = createCSVWriter(filePath)) {
            for (StudentEntity student : students) {
                List<CourseEntity> courses = student.getCourses(); // Adjust this based on your StudentEntity structure

                writeStudentData(writer, student, courses);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        }
    }

    private CSVWriter createCSVWriter(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

        // Customize the CSV writer configuration as needed
        CSVWriterBuilder builder = new CSVWriterBuilder(fileWriter)
                .withSeparator(DEFAULT_SEPARATOR)
                .withQuoteChar(DEFAULT_QUOTE_CHARACTER)
                .withEscapeChar(DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(DEFAULT_LINE_END);

        return (CSVWriter) builder.build();
    }

    private void writeStudentData(CSVWriter csvWriter, StudentEntity student, List<CourseEntity> courses) {
        // Writing student information
        String[] studentInfo = {student.getFirstName(), String.valueOf(studentService.getTotalCreditForStudent(student))};
        csvWriter.writeNext(studentInfo);

        // Writing course information for the student
        for (CourseEntity course : courses) {
            String[] courseInfo = {
                    course.getName(),
                    String.valueOf(course.getTotalTime()),
                    String.valueOf(course.getCredit()),
                    courseService.getInstructorName(course.getInstructorId())
            };
            csvWriter.writeNext(courseInfo);
        }
    }
}
