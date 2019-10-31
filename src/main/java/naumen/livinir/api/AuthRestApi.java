package naumen.livinir.api;

import javax.validation.Valid;

import naumen.livinir.api.form.LoginForm;
import naumen.livinir.api.form.SignUpForm;
import naumen.livinir.api.response.JwtResponse;
import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Resident;
import naumen.livinir.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestApi
{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ResidentDao residentDao;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest)
    {
        if(residentDao.existsByLogin(signUpRequest.getLogin()))
        {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(residentDao.existsByEmail(signUpRequest.getEmail()))
        {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Resident user = new Resident();
        user.setFirstName(signUpRequest.getName());
        user.setLogin(signUpRequest.getLogin());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(signUpRequest.getRole());
        residentDao.create(user);
        return ResponseEntity.ok().body("User registered successfully!");
    }

    // Для тестов ---------------

    @GetMapping("/api/test/user")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public String userAccess()
    {
        return ">>> User Contents!";
    }

    @GetMapping("/api/test/pm")
    @PreAuthorize("hasRole('pm') or hasRole('admin')")
    public String projectManagementAccess()
    {
        return ">>> Board Management Project";
    }

    @GetMapping("/api/test/admin")
    @PreAuthorize("hasRole('admin')")
    public String adminAccess()
    {
        return ">>> Admin Contents";
    }
}