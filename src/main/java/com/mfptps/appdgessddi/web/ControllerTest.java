/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.service.impl.ServiceTest;
import static jdk.internal.net.http.common.Log.headers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/printTest")
public class ControllerTest {
    
     private final ServiceTest serviceTest;

    public ControllerTest(ServiceTest serviceTest) {
        this.serviceTest = serviceTest;
    }
     
    @GetMapping
    public boolean findAlltest() {
        serviceTest.print();
        return true;
    }
}
