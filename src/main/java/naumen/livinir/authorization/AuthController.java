package naumen.livinir.authorization;

import naumen.livinir.api.request.AuthenticationRequest;
import naumen.livinir.authorization.repo.ResidentRepo;
import naumen.livinir.entity.Resident;
import naumen.livinir.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    ResidentRepo residentRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody AuthenticationRequest data)
    {
        try
        {
            String username = "user";
            String password = "password";
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.
                    createToken(
                            username, this.residentRepo.findByUsername(username).
                                    orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"))
                                    .getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        }
        catch (AuthenticationException e)
        {
            e.printStackTrace();
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/signUp")
    public Resident signUp(@RequestBody Resident user)
    {
        user.setRoles(new ArrayList<String>(){{add("ROLE_USER");}});

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        residentRepo.saveAndFlush(user);
        return user;
    }
}

