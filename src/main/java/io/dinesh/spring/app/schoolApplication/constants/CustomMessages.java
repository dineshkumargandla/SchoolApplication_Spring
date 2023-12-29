package io.dinesh.spring.app.schoolApplication.constants;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomMessages {
   public final String RESTORE_STUDENT = "Student details are restored Successfully with id : ";
   public  final String StudentNotFound = "Student Not Found with with id: ";

   public final String DUMMY_STUDENT_CREATION = "Number of Dummy Students created are ";
   public final String DELETE_STUDENT_MESSAGE = "Student details with is deleted bearing id :";
}
