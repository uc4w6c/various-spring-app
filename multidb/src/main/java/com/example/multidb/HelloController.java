package com.example.multidb;

import com.example.multidb.db.BookDao;
import com.example.multidb.db.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
  private UserDao userDao;
  private BookDao bookDao;

  public HelloController(UserDao userDao, BookDao bookDao) {
    this.userDao = userDao;
    this.bookDao = bookDao;
  }

  @GetMapping
  public String index(
      @RequestParam(name = "userid") String userid, @RequestParam(name = "bookid") String bookid) {
    String userName = userDao.getName(userid);
    String bookName = bookDao.getName(bookid);

    return "user: " + userid + " - " + userName + ", book:" + bookid + " - " + bookName;
  }
}
