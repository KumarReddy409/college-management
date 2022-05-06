package com.student.controller;

import com.student.dto.StudentDto;
import com.student.service.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public void createNewStudent(@Valid @RequestBody StudentDto studentDto) {
        studentService.createNewStudent(studentDto);
    }

    @GetMapping("/login")
    public String login(@NotNull(message = "email required")
                            @NotBlank(message = "email required")
                            @RequestParam(value = "email") String email,
                       @NotNull(message = "password required")
                            @NotBlank(message = "password required")
                        @RequestParam(value = "password") String password) {
        return studentService.login(email, password);
    }
}
