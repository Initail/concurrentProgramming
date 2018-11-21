package com.dry3.concurrentProgramming.controller;

import com.dry3.concurrentProgramming.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 17:29
 */
@RestController()
@RequestMapping("/threadLocal")
@Slf4j
public class ThreadLocalController {

    @GetMapping("/get")
    public Long get() {
        log.info(RequestHolder.get().toString());
        return RequestHolder.get();
    }
}
