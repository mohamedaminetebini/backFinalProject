package com.example.finalprojetback.Services;


import com.example.finalprojetback.Dtos.CategoryDto;
import com.example.finalprojetback.Dtos.IssuedBookDto;
import com.example.finalprojetback.Dtos.UserDto;
import com.example.finalprojetback.Enums.Status;
import com.example.finalprojetback.Models.*;
import com.example.finalprojetback.Reposetories.*;
import com.example.finalprojetback.RequestsDto.IssuedBooksRequest;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AdminService {

    private final UserRpo userRpo;
    private final CategoriesRpo categoriesRpo;
    private final IssuedBooksRepo issuedBooksRepo;
    private final BookRepo bookRepo;
    private final FinePerDayRepo finePerDayRepo;
    private  final PasswordEncoder passwordEncoder;
    public Categories addCategory(Categories categories) {
        Categories option = categoriesRpo.save(categories);
        if (option.getId() != null) {
            return option;
        }
        return null;
    }

    public Categories removeCategory(String id) {
        Categories option = categoriesRpo.findById(id).orElse(null);
        if (option != null) {
            categoriesRpo.deleteById(id);
            return option;
        }
        return null;
    }

    public BookModel addBook(BookModel bookModel, String category) {
        Categories cat = categoriesRpo.findById(category).orElse(null);
        if (cat != null) {
            bookModel.setCategory(cat);
        }
        BookModel option = bookRepo.save(bookModel);
        if (option.getId() != null) {
            return option;
        }
        return null;


    }

    public BookModel removeBook(String id) {
        BookModel option = bookRepo.findById(id).orElse(null);
        if (option != null) {
            bookRepo.deleteById(id);
            return option;
        }
        return null;
    }

    public IssuedBooks issueBook(IssuedBooksRequest issuedBooks) {
        System.out.println(issuedBooks.getBook());
        System.out.println(issuedBooks.getUser());
        BookModel book = bookRepo.findById(issuedBooks.getBook()).orElse(null);
        if (book != null) {
            UserModel user = userRpo.findById(issuedBooks.getUser()).orElse(null);
            if (user != null) {
                IssuedBooks option = new IssuedBooks();
                option.setBook(book);
                option.setUser(user);
                option.setIssuedDate(new Date().toString());
                option.setDueDate(issuedBooks.getDueDate().toString());
                option.setReturned(false);
                option.setIssued(true);
                IssuedBooks save = issuedBooksRepo.save(option);
                if (save.getId() != null) {
                    return save;
                } else {
                    return null;
                }

            }
        }
        return null;
    }

    public FinePerDay addFinePerDay(FinePerDay finePerDay) {
        FinePerDay option = finePerDayRepo.save(finePerDay);
        if (option.getId() != null) {
            return option;
        }
        return null;
    }

    public UserModel modifyStatus(String username) {
        UserModel user = userRpo.findByUsername(username).orElse(null);
        if (user != null) {
            if (user.getProfileStatus().equals(Status.ACTIVE)) {
                user.setProfileStatus(Status.INACTIVE);
            } else if (user.getProfileStatus().equals(Status.INACTIVE)) {
                user.setProfileStatus(Status.ACTIVE);
            }
            userRpo.save(user);
            return user;
        }
        return null;
    }

    public FinePerDay getFinePerDay() {
        List<FinePerDay> finePerDays = finePerDayRepo.findAll(getSort());

        return finePerDays.get(finePerDays.size()-1);

    }

    private Sort getSort() {
        Sort sort = Sort.by("date");
        sort.descending();
        return sort;
    }

    public UserModel addUser(UserModel userModel) {
        UserModel option = userRpo.save(userModel);
        if (option.getId() != null) {
            return option;
        }
        return null;
    }

    public List<BookModel> getBooks() {
        return bookRepo.findAll();
    }

    public List<IssuedBookDto> getIssuedBooks() {
        List<IssuedBooks> issuedBooks = issuedBooksRepo.findAll();
        List<IssuedBookDto> issuedBookDtos = new ArrayList<>();

        for (IssuedBooks issuedBook : issuedBooks) {
            IssuedBookDto issuedBookDto = new IssuedBookDto();
            issuedBookDto.setIssuedBookId(issuedBook.getId());
            issuedBookDto.setIssuedDate(issuedBook.getIssuedDate());
            issuedBookDto.setDueDate(issuedBook.getDueDate());
            issuedBookDto.setIssued(issuedBook.getIssued());

            BookModel book = issuedBook.getBook();
            issuedBookDto.setBookTitle(book.getTitle());
            issuedBookDto.setBookAuthor(book.getAuthor());
            issuedBookDto.setBookIsbn(book.getIsbn());
            issuedBookDto.setBookDescription(book.getDescription());
            issuedBookDto.setBookNoCopies(book.getNoCopies());
            issuedBookDto.setReturned(issuedBook.getReturned());

            UserModel user = issuedBook.getUser();
            issuedBookDto.setUsername(user.getUsername());
            issuedBookDto.setFullName(user.getFullName());
            issuedBookDto.setPhoneNumber(user.getPhoneNumber());


            issuedBookDtos.add(issuedBookDto);
        }

        return issuedBookDtos;
    }

    public long getTotalBooks() {
        return bookRepo.count();
    }

    public List<CategoryDto> getCategories() {
        List<Categories> categories = categoriesRpo.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Categories category : categories) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            categoryDto.setCode(category.getId());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    public String searchBook(String id) {
        BookModel book = bookRepo.findById(id).orElse(null);
        if (book != null) {
            return book.getTitle();
        }
        return null;
    }

    public Map<String, Object> getUsers(
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        List<UserModel> users = userRpo.findAll(pageable).getContent();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserModel user : users) {
            UserDto userDto = new UserDto();
            userDto.setFullName(user.getFullName());
            userDto.setUsername(user.getUsername());
            userDto.setStatus(user.getProfileStatus());
            userDto.setJoinDate(user.getJoinDate());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDtos.add(userDto);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("users", userDtos);
        map.put("currentPage", userRpo.findAll(pageable).getNumber());
        map.put("next", userRpo.findAll(pageable).hasNext());
        map.put("totalPages", userRpo.findAll(pageable).getTotalPages());
        return map;
    }

    public Boolean updateReturned(String id) {
        IssuedBooks issuedBooks = issuedBooksRepo.findById(id).orElse(null);
        if (issuedBooks != null) {
            issuedBooks.setReturned(true);
            issuedBooksRepo.save(issuedBooks);
            return true;
        }
        return false;
    }

    public Boolean updateIssued(String id) {
        IssuedBooks issuedBooks = issuedBooksRepo.findById(id).orElse(null);
        if (issuedBooks != null) {
            issuedBooks.setIssuedDate(LocalDateTime.now().toString());
            issuedBooks.setReturned(false);
            issuedBooks.setIssued(true);
            issuedBooksRepo.save(issuedBooks);
            return true;
        }
        return false;
    }

    public List<IssuedBookDto> getAllNotIssuedBooks() {
        List<IssuedBooks> issuedBooks = issuedBooksRepo.findNotIssuedBooks();
        List<IssuedBookDto> issuedBookDtos = new ArrayList<>();

        for (IssuedBooks issuedBook : issuedBooks) {
            IssuedBookDto issuedBookDto = new IssuedBookDto();
            issuedBookDto.setIssuedBookId(issuedBook.getId());
            issuedBookDto.setIssuedDate(issuedBook.getIssuedDate());
            issuedBookDto.setDueDate(issuedBook.getDueDate());
            issuedBookDto.setIssued(issuedBook.getIssued());
            BookModel book = issuedBook.getBook();
            issuedBookDto.setBookTitle(book.getTitle());
            issuedBookDto.setBookAuthor(book.getAuthor());
            issuedBookDto.setBookIsbn(book.getIsbn());
            issuedBookDto.setBookDescription(book.getDescription());
            issuedBookDto.setBookNoCopies(book.getNoCopies());
            issuedBookDto.setReturned(issuedBook.getReturned());
            UserModel user = issuedBook.getUser();
            issuedBookDto.setUsername(user.getUsername());
            issuedBookDto.setFullName(user.getFullName());
            issuedBookDto.setPhoneNumber(user.getPhoneNumber());
            issuedBookDtos.add(issuedBookDto);
        }
        return issuedBookDtos;

    }

    public long getTotalUsers() {
        return userRpo.count();
    }
    public Long getTotalReturnedBooks(){
        return issuedBooksRepo.countByReturnedTrue();
    }
    public Long getTotalIssuedBooks(){
        return issuedBooksRepo.countByIssuedTrue();
    }

    public Long getTotalCategories(){
        return categoriesRpo.count();
    }
    public Boolean chnagePassword(String username, String oldpassword, String newPassword) {
        UserModel user = userRpo.findByUsername(username).orElse(null);
        String encodedPassword = passwordEncoder.encode(oldpassword);
        if (user != null && user.getPassword().equals(encodedPassword)) {
            user.setPassword(newPassword);
            userRpo.save(user);
            return true;
        }
        return false;
    }

}