package com.callcenter.callcenterimplementation;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Test index(@RequestBody Test test) {
        return test;
    }
}

