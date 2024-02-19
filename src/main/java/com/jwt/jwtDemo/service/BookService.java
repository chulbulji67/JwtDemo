package com.jwt.jwtDemo.service;

import com.jwt.jwtDemo.entity.Book;
import com.jwt.jwtDemo.entity.User;
import com.jwt.jwtDemo.repo.BookRepo;
import com.jwt.jwtDemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepo userRepo;

    //Save a book

    public Book addBook(Book book){
        Set<User> users = new HashSet<>();

       for (User user : book.getUsers()){
           users.add(userRepo.findById(user.getId()).get());
       }

       book.setUsers(users);

        return bookRepo.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

}
