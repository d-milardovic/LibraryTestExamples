package com.appLabIT.onlineLibrary;

import com.appLabIT.onlineLibrary.helper.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;
import java.util.stream.*;

import static com.appLabIT.onlineLibrary.helper.DefaultData.*;
import static com.appLabIT.onlineLibrary.helper.JsonMapper.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void borrow_all_classics_total_3_books_should_be_allowed() throws Exception {
        var data = new Data(setupUsers(), setupBooks());

        var markus = data.markus();

        markus
                .want(data.all_classic_books())
                .expect(status().isOk())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
        markus.returnAllBooks();
    }

    @Test
    public void borrow_the_same_books_twice_and_return_in_between_should_be_allowed() throws Exception {
        var data = new Data(setupUsers(), setupBooks());

        var markus = data.markus();

        markus
                .want(data.all_classic_books())
                .expect(status().isOk())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
        markus.returnAllBooks();

        markus
                .want(data.all_classic_books())
                .expect(status().isOk())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
        markus.returnAllBooks();
    }

    @Test
    public void borrow_all_classics_total_3_books_then_borrow_another_5_books_should_not_be_allowed() throws Exception {
        var data = new Data(setupUsers(), setupBooks());

        var markus = data.markus();

        // Borrow all classics (3), then without returning,
        // borrow all brandon sanderson books (3) and all dennis taylor books (2),
        // total 8 books should not be allowed
        markus
                .want(data.all_classic_books())
                .expect(status().isOk())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
        markus
                .want(data.all_brandon_sanderson_books())
                .expect(status().isOk())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
        markus
                .want(data.all_dennis_taylor_books())
                .expect(status().isBadRequest())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
        markus.returnAllBooks();
    }

    @Test
    public void borrow_all_harry_potter_books_total_7_books_should_not_be_allowed() throws Exception {
        var data = new Data(setupUsers(), setupBooks());

        var tea = data.tea();

        // Borrow all harry potter books (7) should not be allowed
        tea.want(data.all_harry_potter_books())
                .expect(status().isBadRequest())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
        tea.returnAllBooks();
    }

    @Test
    public void borrow_all_new_books_total_3_should_not_be_allowed() throws Exception {
        var data = new Data(setupUsers(), setupBooks());

        var domagoj = data.domagoj();

        // Borrow all new books (3) should not be allowed
        domagoj.want(data.all_new_books())
                .expect(status().isBadRequest())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
        domagoj.returnAllBooks();
    }

    @Test
    public void borrow_all_lord_of_the_rings_book_total_3_should_be_allowed() throws Exception {
        var data = new Data(setupUsers(), setupBooks());

        var domagoj = data.domagoj();

        // borrow all lord of the rings books (3) should be allowed
        domagoj.want(data.all_lord_of_the_rings_books())
                .expect(status().isOk())
                .expect(content().contentType(MediaType.APPLICATION_JSON))
                .rent();
    }

    private List<Response.Book> setupBooks() throws Exception {
        List<Response.Book> addedBooks = new ArrayList<>();
        for (Request.Book book : BOOKS) {
            addedBooks.add(setupBook(book));
        }
        return addedBooks;
    }

    private Response.Book setupBook(Request.Book book) throws Exception {
        var response = this.mockMvc.perform(
                        post("/library/addBook")
                                .content(JsonMapper.toJson(book))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        return JsonMapper.toBook(response.getContentAsString());
    }

    private List<Response.User> setupUsers() throws Exception {
        List<Response.User> addedUsers = new ArrayList<>();
        for (Request.User user : USERS) {
            addedUsers.add(setupUser(user));
        }
        return addedUsers;
    }

    private Response.User setupUser(Request.User user) throws Exception {
        var response = this.mockMvc.perform(
                        post("/library/addUser")
                                .content(JsonMapper.toJson(user))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        return JsonMapper.toUser(response.getContentAsString());
    }

    private class Data {
        private final List<Response.User> users;
        private final List<Response.Book> books;

        private Data(List<Response.User> users, List<Response.Book> books) {
            this.users = users;
            this.books = books;
        }

        private List<Integer> all_classic_books() {
            return books
                    .stream()
                    .filter(book -> book.bookType.equalsIgnoreCase("classic"))
                    .map(book -> book.id)
                    .collect(Collectors.toList());
        }

        private List<Integer> all_lord_of_the_rings_books() {
            return books
                    .stream()
                    .filter(book -> book.author.equalsIgnoreCase("J. R. R. Tolkien"))
                    .map(book -> book.id)
                    .collect(Collectors.toList());
        }

        private List<Integer> all_harry_potter_books() {
            return books
                    .stream()
                    .filter(book -> book.author.equalsIgnoreCase("J. K. Rowling"))
                    .map(book -> book.id)
                    .collect(Collectors.toList());
        }

        private List<Integer> all_dennis_taylor_books() {
            return books
                    .stream()
                    .filter(book -> book.author.equalsIgnoreCase("Dennis E. Taylor"))
                    .map(book -> book.id)
                    .collect(Collectors.toList());
        }

        private List<Integer> all_brandon_sanderson_books() {
            return books
                    .stream()
                    .filter(book -> book.author.equalsIgnoreCase("Brandon Sanderson"))
                    .map(book -> book.id)
                    .collect(Collectors.toList());
        }

        private List<Integer> all_new_books() {
            return books
                    .stream()
                    .filter(book -> book.bookType.equalsIgnoreCase("new"))
                    .map(book -> book.id)
                    .collect(Collectors.toList());
        }

        private UserAction markus() {
            return new UserAction(users
                    .stream()
                    .filter(u -> u.name.equalsIgnoreCase("Markus"))
                    .findFirst()
                    .get().id);
        }

        private UserAction tea() {
            return new UserAction(users
                    .stream()
                    .filter(u -> u.name.equalsIgnoreCase("Tea"))
                    .findFirst()
                    .get().id);
        }

        private UserAction domagoj() {
            return new UserAction(users
                    .stream()
                    .filter(u -> u.name.equalsIgnoreCase("Domagoj"))
                    .findFirst()
                    .get().id);
        }

    }

    public class UserAction {
        private int userId;
        private List<Integer> bookIds = new ArrayList<>();
        private List<ResultMatcher> matchers = new ArrayList<>();

        public UserAction(int userId) {
            this.userId = userId;
        }

        public UserAction want(List<Integer> bookIds) {
            this.bookIds.addAll(bookIds);
            return this;
        }

        public UserAction expect(ResultMatcher matcher) {
            this.matchers.add(matcher);
            return this;
        }

        public void returnAllBooks() throws Exception {
            var response = mockMvc.perform(
                            get("/library/allRents/{userId}", userId)
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();

            var rentIds = getRentIds(response.getContentAsString());
            var json = toJson(rentIds);
            mockMvc.perform(
                            put("/library/rentBack")
                                    .content(json)
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();
/*
            for (Integer rentId : rentIds) {
                mockMvc.perform(
                                delete("/library/deleteRent/{rentId}", rentId)
                        )
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }*/
        }

        public void rent() throws Exception {
            var json = toJson(bookIds);
            mockMvc.perform(
                            post("/library/rentBook/user/{userId}", userId)
                                    .content(json)
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpectAll(matchers.toArray(ResultMatcher[]::new));

            bookIds = new ArrayList<>();
            matchers = new ArrayList<>();
        }
    }

}
