package com.example.week02.controller;

import com.example.week02.domain.Course;
import com.example.week02.domain.CourseRepository;
import com.example.week02.models.CourseRequestDto;
import com.example.week02.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CourseController {
    private final CourseRepository courseRepository;

    @GetMapping("/api/courses")
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    private final CourseService courseService;

    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분
    @PostMapping("/api/courses")
    public Course createCourse(@RequestBody CourseRequestDto requestDto){
        // requestDto는 생성 요청
        // 강의 제몰과 튜터이름 등 정보를 가져온다

        // 저장하는 것은 Dto가 아니라 Course이다. Dto의 정보를 course에 담아야한다
        // 잠시 뒤 새로운 생성자를 만든다
        Course course = new Course(requestDto);

        // JPA를 이용하여 DB에 저장하고 그 결과 반환환
        return courseRepository.save(course);
    }

    @PutMapping("/api/courses/{id}")
    public Long updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto requestDto){
        return courseService.update(id, requestDto);
    }

    @DeleteMapping("/api/courses/{id}")
    public Long deleteCourse(@PathVariable Long id){
        courseRepository.deleteById(id);
        return id;
    }
}
