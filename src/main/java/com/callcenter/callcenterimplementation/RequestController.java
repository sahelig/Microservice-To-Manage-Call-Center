package com.callcenter.callcenterimplementation;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


@RestController
public class RequestController {

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CallCenterImplementation> CallDistributionService(@RequestBody InputFromServiceCaller inputFromServiceCaller) {
        CallCenterImplementation impl;
        impl = inputFromServiceCaller.parseInputAndCreateCallCenterImpl();
        return new ResponseEntity<CallCenterImplementation>(impl,HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Your passed JSON isn't compatible!")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleException() {
    }
}

