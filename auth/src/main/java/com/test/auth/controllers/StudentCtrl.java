package com.test.auth.controllers;

import com.test.auth.models.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentCtrl {
    private List<Student> students = List.of(
        new Student(1L, "Alice", 100),
        new Student(2L, "Bob", 90),
        new Student(3L, "Charlie", 80)
    );
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody  Student student) {
        System.out.println("Adding student: " + student);
        // students.add(student);
        return  student;
    }
}
