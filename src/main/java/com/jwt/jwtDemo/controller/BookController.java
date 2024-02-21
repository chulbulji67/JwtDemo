package com.jwt.jwtDemo.controller;

import com.jwt.jwtDemo.entity.Book;
import com.jwt.jwtDemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<?> saveAbook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(book));
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.getAllBooks());
    }
}
