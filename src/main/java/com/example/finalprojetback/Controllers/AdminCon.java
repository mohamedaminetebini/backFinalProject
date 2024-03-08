package com.example.finalprojetback.Controllers;


import com.example.finalprojetback.Dtos.*;
import com.example.finalprojetback.Enums.Roles;
import com.example.finalprojetback.Enums.Status;
import com.example.finalprojetback.Message;
import com.example.finalprojetback.Models.*;
import com.example.finalprojetback.RequestsDto.*;
import com.example.finalprojetback.Services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminCon {


    private final   AdminService adminService;

    @PostMapping("/category")
    @ResponseBody
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categories){
        Categories cat = Categories.builder().name(categories.getCategory()).description(categories.getDescription()).status(categories.getStatus()).build();
        if(adminService.addCategory(cat) != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message("category added successfully"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("category not added"));
    }
    @GetMapping("/category")
    @ResponseBody
    public ResponseEntity<?> getCategories(){
        List<CategoryDto> categories = adminService.getCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
    @PostMapping("changepassword")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequest passwordRequest){

        if(adminService.chnagePassword(passwordRequest.getUsername(),passwordRequest.getNewPassword(),passwordRequest.getOldPassword()) != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("password changed successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("password not changed"));

    }
    @GetMapping("/category/total")
    @ResponseBody
    public ResponseEntity<?> getCategoriesTotal(){
        Long categories = adminService.getTotalCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @DeleteMapping("/category/{id}")
    @ResponseBody
    public ResponseEntity<?> removeCategory(@PathVariable String id){
        Categories option = adminService.removeCategory(id);
        if(option != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("category removed successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("category not removed"));
    }
    @PostMapping("/book")
    @ResponseBody
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookModel){

        BookModel book = BookModel.builder().title(bookModel.getTitle()).author(bookModel.getAuthor()).isbn(bookModel.getIsbn()).description(bookModel.getDescription()).noCopies(bookModel.getNoCopies()).price(bookModel.getPrice()).build();
        if (adminService.addBook(book, bookModel.getCategory()) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message("book added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("book not added"));
    }
    @DeleteMapping("/book/{id}")
    @ResponseBody
    public ResponseEntity<?> removeBook(@PathVariable String id){
        BookModel option = adminService.removeBook(id);
        if(option != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("book removed successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("book not removed"));
    }
    @PostMapping("/issue")
    @ResponseBody
    public ResponseEntity<?> issueBook(@RequestBody IssuedBooksRequest issuedBooks){
    IssuedBooks issue = adminService.issueBook(issuedBooks);
    if(issue != null){
        return ResponseEntity.status(HttpStatus.OK).body(new Message("book issued successfully"));
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("book not issued"));

    }

    @GetMapping("/book/{id}")
    @ResponseBody
    public ResponseEntity<?> searchBook(@PathVariable String id){
        String title = adminService.searchBook(id);
        System.out.println(title);
        Map<String, String> map = Map.of("title", title);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    @PostMapping("/fine")
    @ResponseBody
    public ResponseEntity<?> addFinePerDay(@RequestBody FinePerDayRequest finePerDay){
        FinePerDay option = FinePerDay.builder().finePerDay(finePerDay.getFinePerDay()).date(new Date()).build();
        FinePerDay save = adminService.addFinePerDay(option);

            return ResponseEntity.status(HttpStatus.OK).body(new Message("fine added successfully"));


    }
    @GetMapping("/fine")
    @ResponseBody
    public ResponseEntity<?> getFinePerDay(){
        FinePerDay finePerDays = adminService.getFinePerDay();
        return ResponseEntity.status(HttpStatus.OK).body(finePerDays.getFinePerDay());
    }
    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody UserRequest user ){
        UserModel user1 = UserModel.builder().username(user.getUsername()).password(user.getPassword()).roles(Roles.ROLE_STUDENT).profileStatus(user.getStatus()).fullName(user.getFullName()).phoneNumber(user.getPhoneNumber()).joinDate(user.getJoinDate()).build();
        UserModel save = adminService.addUser(user1);
        if(save != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("user added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("user not added"));
    }
    @PutMapping("/user")
    @ResponseBody
    public ResponseEntity<?> modifyStatus(@RequestBody UserRequest user ){
        UserModel option = adminService.modifyStatus(user.getUsername());
        if(option != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("user status modified successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("user status not modified"));
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<?> getUsers(){
        Map<String, Object> users = adminService.getUsers(0,10);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    @GetMapping("/book")
    @ResponseBody
    public ResponseEntity<?> getBooks(){
        List<BookModel> books = adminService.getBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    @GetMapping("/issue")
    @ResponseBody
    public ResponseEntity<?> getIssuedBooks(){
        List<IssuedBookDto> issuedBooks = adminService.getIssuedBooks();
        return ResponseEntity.status(HttpStatus.OK).body(issuedBooks);
    }
    @GetMapping("book/total")
    @ResponseBody
    public ResponseEntity<?> getSort(){
        Long total = adminService.getTotalBooks();
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }
    @PutMapping("/issue/{id}")
    @ResponseBody
    public ResponseEntity<?> returnBook(@PathVariable String id){
        Boolean option = adminService.updateReturned(id);
        if(option ){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("book returned successfully"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("server error"));
    }

    @GetMapping("/user/total")
    @ResponseBody
    public ResponseEntity<?> getTotalUsers(){
        Long total = adminService.getTotalUsers();
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }
    @PutMapping("/issue/give/{id}")
    @ResponseBody
    public ResponseEntity<?> updateIssued(@PathVariable String id){
        Boolean option = adminService.updateIssued(id);
        if(option ){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("book issued successfully"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("server error"));
    }
    @GetMapping("/issue/not")
    @ResponseBody
    public ResponseEntity<?> getNotIssuedBooks(){
        List<IssuedBookDto> issuedBooks = adminService.getAllNotIssuedBooks();
        return ResponseEntity.status(HttpStatus.OK).body(issuedBooks);
    }
    @GetMapping("/issue/returned/total")
    @ResponseBody
    public ResponseEntity<?> getReturnedBooksTotal(){
        Long total = adminService.getTotalReturnedBooks();
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }
    @GetMapping("/issue/issued/total")
    @ResponseBody
    public ResponseEntity<?> getIssuedBooksTotal(){
        Long total = adminService.getTotalIssuedBooks();
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }

}
