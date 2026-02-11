package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "courses")
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String name;
    private String zoom;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Course> courses;

    public void addCourse(Course course) {
        if(course !=null){
            if(courses == null) {
                courses = new HashSet<>();
            }
            courses.add(course);
            course.setTeacher(this);
        }
    }
    public void removeCourse(Course course) {
        courses.remove(course);
        course.setTeacher(null);
    }

}
