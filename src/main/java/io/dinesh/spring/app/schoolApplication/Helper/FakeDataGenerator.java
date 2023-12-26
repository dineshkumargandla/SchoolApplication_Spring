package io.dinesh.spring.app.schoolApplication.Helper;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class FakeDataGenerator {

    Faker faker = new Faker();

    String firstName;

    String lastName;
    public String getFakeFirstName(){
        firstName = faker.name().firstName();
        return firstName;
    }

    public String getFakeLastName(){
        lastName = faker.name().lastName();
        return lastName;
    }

    public String getFakeEmail(){
        return firstName+"."+lastName+"@gmail.com";
    }
}
