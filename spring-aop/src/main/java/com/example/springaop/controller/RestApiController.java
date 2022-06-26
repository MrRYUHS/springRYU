package com.example.springaop.controller;

import com.example.springaop.annotation.Decode;
import com.example.springaop.annotation.Timer;
import com.example.springaop.dto.User;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        return id+" "+name;
    }
    @PostMapping("/post")
    public User post(@RequestBody User user){
        return user;
    }
    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        //db logic
        Thread.sleep(1000 * 2);
    }
    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("put");
        System.out.println(user);
        return user;
    }
}