package io.dinesh.spring.app.schoolApplication.Helper;

import com.github.javafaker.Faker;
import io.dinesh.spring.app.schoolApplication.service.FakeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FakeDataGenerator implements FakeData {


    String firstName;

    String lastName;

    @Override
    public String getFakeFirstName() {
        Faker faker = new Faker();
        firstName = faker.name().firstName();
        return firstName;
    }

    @Override
    public String getFakeLastName() {
        Faker faker = new Faker();
        lastName = faker.name().lastName();
        return lastName;
    }

    @Override
    public String getFakeEmail() {
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com";
    }

    @Override
    public String getFakeClassSection() {
        Faker faker = new Faker();
        String[] fakeCList = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String fakeClass = fakeCList[faker.number().numberBetween(0, 9)];
        String[] fakeSList = {"A", "B", "C", "D"};
        String fakeSection = fakeSList[faker.number().numberBetween(0, 4)];
        return fakeClass+"_" + fakeSection;
    }

}
