package com.project.back_app.controller;

import com.project.back_app.model.Course;
import com.project.back_app.repository.CourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourceRepository courseRepository;
    @GetMapping("")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getUserById(@PathVariable("id") Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Course> createUser(@RequestBody Course user) {
        Course savedUser = courseRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateUser(@PathVariable("id") Long id, @RequestBody Course user) {
        Course currentUser = courseRepository.findById(id).orElse(null);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setTitle(user.getTitle());
        currentUser.setPrice(user.getPrice());
        currentUser.setImage(user.getImage());
        Course updatedUser = courseRepository.save(currentUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        Course user = courseRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
