package bg.haemimont.coursera.service;

import bg.haemimont.coursera.model.entity.StudentEntity;

import java.time.LocalDate;
import java.util.List;

public interface CsvFormatterService {
    void
    createReport(List<StudentEntity> students, String minimumCreditRequired, LocalDate startingDate, String filePath);
}
