package com.alexotoodev.resfulapiservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFistVersioningOfPerson(){
        return new PersonV1("Alice Ama");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersioningOfPerson(){
        return new PersonV2(new Name("kofi","manu"));
    }
    
}
