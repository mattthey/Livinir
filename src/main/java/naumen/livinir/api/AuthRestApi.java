package naumen.livinir.api;

import javax.servlet.http.HttpServletRequest;

import naumen.livinir.api.response.JwtResponse;
import naumen.livinir.dao.ResidentDao;
import naumen.livinir.service.JwtServiceImpl;
import naumen.livinir.service.ResidentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestApi
{
    private static final Logger LOG = LoggerFactory.getLogger(AuthRestApi.class);

    @Autowired
    ResidentDao residentDao;

    @Autowired
    ResidentServiceImpl residentService;

    @Autowired
    JwtServiceImpl jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws IOException
    {
        Map<String, String> requestBody = HttpHelper.getRequestJSONContent(request);
        jwtProvider.validateJwtToken(requestBody.get("accessToken"));
        return null;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request) throws IOException
    {
        Map<String, String> requestBody = HttpHelper.getRequestJSONContent(request);
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtProvider.generateJwtAccessToken(email);
        String refreshToken = jwtProvider.generateJwtRefreshToken(email);
        return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(HttpServletRequest request) throws IOException, ParseException
    {
        Map<String, String> requestBody = HttpHelper.getRequestJSONContent(request);
        String password = requestBody.get("password");
        String email = requestBody.get("email");
        if(email == null || password == null || residentDao.existsByEmail(email))
        {
            return new ResponseEntity<String>("Fail -> Email is already taken or password is empty!",
                    HttpStatus.BAD_REQUEST);
        }
        residentService.createResident(requestBody);
        LOG.info("Success registrer new user {}", email);
        return ResponseEntity.ok().body("Resident registered successfully!");
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) throws IOException
    {
        Map<String, String> requestBody = HttpHelper.getRequestJSONContent(request);
        String email = requestBody.get("email");
        LOG.info("User {} logout", email);
    }
}