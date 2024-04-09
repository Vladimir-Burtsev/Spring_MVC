package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("bmw", 1);
        Car car2 = new Car("lada", 2);
        Car car3 = new Car("honda", 3);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car2));

        List<User> user = userService.getUserByModelAndSeries("bmw", 1);
        for (User u : user) {
            System.out.println("id = " + u.getId());
            System.out.println("First Name = " + u.getFirstName());
            System.out.println("Last Name = " + u.getLastName());
            System.out.println("Email = " + u.getEmail());
            System.out.println("Car Series = " + u.getCar().getSeries());
            System.out.println("Car Model = " + u.getCar().getModel());
        }

        context.close();
    }
}