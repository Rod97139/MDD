package com.openclassrooms.mddapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
}
