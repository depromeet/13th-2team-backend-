package com.depromeet.oversweet;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        TestException testException = new TestException("testException");
        TestDomain testDomain = new TestDomain("testDomain");
        return testException.getMessage() + testDomain.getName();
    }
}
