//package com.student.service;
//
//import com.student.dto.StudentDto;
//import com.student.entity.Student;
//
//import com.student.exception.StudentAPIException;
//import com.student.exception.StudentRequestException;
//import com.student.repository.StudentRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//
//@ExtendWith(MockitoExtension.class)
//class StudentServiceTest {
//
//    @Mock
//    private StudentRepository studentRepository;
//
//    @Mock
//    private Argon2PasswordEncoder passwordEncoder;
//
//    private StudentService studentService;
//
//
//    @BeforeEach
//    void setUp(){
//        studentService = new StudentService(studentRepository,passwordEncoder);
//    }
//
//
//    @Test
//    void canStudentSaveWhenEmailMissing() {
//
//           try {
//               //given
//               StudentDto studentDto = StudentDto
//                       .builder()
//                       .firstName("Mahi")
//                       .lastName("MSD")
//                       .password("1234567890")
//                       .build();
//
//               //when
//               studentService.createNewStudent(studentDto);
//
//               //then
//               Mockito.verify(studentRepository, Mockito.never()).save(Mockito.any());
//           }catch (StudentRequestException e){
//               Assertions.assertEquals("Email or Password missing",e.getMessage());
//           }
//
//
//    }
//
//    @Test
//    void canStudentSaveWhenEmailExists() {
//
//        try {
//            //given
//            StudentDto studentDto = StudentDto
//                    .builder()
//                    .firstName("Mahi")
//                    .lastName("MSD")
//                    .password("1234567890")
//                    .build();
//
//            //when
//            given(studentRepository.findByEmail(student.getEmail()))
//                    .willReturn(student);
//            studentService.save(student);
//
//            //then
//            Mockito.verify(studentRepository, Mockito.never()).save(student);
//        }catch (StudentAPIException e){
//            Assertions.assertEquals("msd@gmail.com already taken",e.getMessage());
//        }
//
//    }
//
//    @Test
//    void canStudentSave() {
//
//        //given
//        Student student = Student
//                .builder()
//                .firstName("Mahi")
//                .lastName("MSD")
//                .email("msd@gmail.com")
//                .isResetPasswordRequired(false)
//                .password("1234567890")
//                .build();
//
//        //when
//        given(studentRepository.findByEmail(student.getEmail()))
//                .willReturn(null);
//        studentService.save(student);
//
//        //then
//        Mockito.verify(studentRepository,Mockito.atMostOnce()).save(student);
//
//    }
//
//    @Test
//    void canStudentLoginWhenEmailMissing(){
//
//        try {
//            //given
//            String email = "";
//            String password = "1234567890";
//
//            //when
//            studentService.login(email, password);
//
//            //then
//        }catch (StudentRequestException e){
//            Assertions.assertEquals("Email or Password missing",e.getMessage());
//        }
//    }
//
//    @Test
//    void canStudentLoginWhenPasswordWrong(){
//
//        try {
//            //given
//            String email = "john@gmail.com";
//            String password = "1234567890";
//
//            //when
//            studentService.login(email, password);
//
//            //then
//        }catch (StudentRequestException e){
//            Assertions.assertEquals("Email or Password missing",e.getMessage());
//        }
//    }
//}