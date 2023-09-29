package com.example.registrationlogin.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.imageio.spi.RegisterableService;

@RestController
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @GetMapping(path = "/hello")
    public String hello(){
        return "Hello";
    }
    @GetMapping(path = "/sec")
    public String secure(){
        return "Secure Hello";
    }
    @PostMapping(path = "api/v1/registration")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
