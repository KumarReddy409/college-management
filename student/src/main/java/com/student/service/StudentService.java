package com.student.service;

import com.student.dto.StudentDto;
import com.student.entity.Student;
import com.student.exception.StudentAPIException;
import com.student.exception.StudentRequestException;
import com.student.repository.StudentRepository;
import com.student.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author kumar
 */


@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    private final BCryptPasswordEncoder passwordEncoder;



    public StudentService(StudentRepository studentRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * will save the student object if not exists
     * @param studentDto the request object
     */
    public void createNewStudent(StudentDto studentDto) {
        String email = studentDto.getEmail();
        Student existingStudent = this.getStudentByEmail(email);
        if (null != existingStudent) {
            log.error("Exception occurred while registering student - {}",
                    email + " already taken");
            throw new StudentAPIException(email + " already taken",
                    HttpStatus.ALREADY_REPORTED);
        }
        Student student = Student
                .builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .isResetPasswordRequired(true)
                .phoneNumber(studentDto.getPhoneNumber())
                .password(passwordEncoder.encode(studentDto.getPassword()))
                .build();
        studentRepository.save(student);
    }

    /**
     * check if email and password exists if exists return id or else not found exception
     * @param email - email
     * @param password - password
     * @return - studentId
     */
    //TODO :- Need to change response if required
    public String login(String email,String password){
        Student student = this.getStudentByEmail(email);
        if(null == student){
            log.error("Exception occurred while login student - {}",
                    "Student with email " + email + " not found");
            throw new StudentAPIException("Student with email " + email + " not found",
                    HttpStatus.NOT_FOUND);
        }
       boolean passwordMatches =  passwordEncoder.matches(password,student.getPassword());
        if(!passwordMatches){
            log.error("Exception occurred while login student - {}",
                    "Password Wrong");
            throw new StudentAPIException("Password wrong",
                    HttpStatus.BAD_REQUEST);
        }
        return student.getStudentId();
    }

    /**
     * Retrieve Student by Email
     * @param email - email
     * @return - Student
     */
    private Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
