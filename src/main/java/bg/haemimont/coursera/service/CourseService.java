package bg.haemimont.coursera.service;

import bg.haemimont.coursera.model.entity.CourseEntity;

public interface CourseService {


    void createCourse();

    CourseEntity findCourse(String name);
}
