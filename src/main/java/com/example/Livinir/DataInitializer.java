package com.example.Livinir;


import com.example.Livinir.domain.User;
import com.example.Livinir.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.LinkedList;

import static com.sun.tools.javac.resources.CompilerProperties.Notes.Note;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {


    @Autowired
    UserRepo users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {



        User user = new User();
        user.setUsername("user");
        user.setPassword(this.passwordEncoder.encode("password"));
        user.setRoles(Arrays.asList("ROLE_USER"));

        //users.save(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("password"));
        admin.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));

        //users.save(admin);

    }
}

