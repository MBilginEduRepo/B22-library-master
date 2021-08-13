package com.library.step_definitions;

import com.library.pages.BooksPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class AddBook {

    BooksPage booksPage = new BooksPage();

    @When("I click to Add Book button")
    public void i_click_to_Add_Book_button() throws InterruptedException {
        System.out.println("adding book..");
        BrowserUtils.waitFor(3);
        booksPage.addBook.click();
    }


    @When("I fill out the book info form {string} {string} {string} {string} {string} , {string} {string} {string} {string} {string}")
    public void i_fill_out_the_book_info_form(String bookName, String author, String isbn, String year, String category, String bookName2, String author2, String isbn2, String year2, String category2) {
        BrowserUtils.waitFor(1);
        booksPage.addBook(bookName).sendKeys(bookName2);
        BrowserUtils.waitFor(1);
        booksPage.addBook(author).sendKeys(author2);
        BrowserUtils.waitFor(1);
        booksPage.addBook(isbn).sendKeys(isbn2);
        BrowserUtils.waitFor(1);
        booksPage.addBook(year).sendKeys(year2);
        BrowserUtils.waitFor(1);
        booksPage.bookCategory().selectByVisibleText(category2);
        BrowserUtils.waitFor(1);
        booksPage.saveChangesButton.click();
    }


    @Then("book information must match the database for {string} {string} {string} {string} {string}")
    public void book_information_must_match_the_database_for(String bookName, String author, String isbn, String year, String category) {
/*
        BrowserUtils.waitFor(3);
        String query = "select name, isbn,year,author from books\n" +
                "where name = '"+bookName+"'";

        //since we are getting only one row, we will use getRowMap method
        Map<String, Object> dbData = DBUtils.getRowMap(query);
        //real life you dont save those info into variables
        Assert.assertEquals(booksPage.bookName.getAttribute("value"),dbData.get("name").toString());

 */

        BrowserUtils.waitFor(3);
        String query = "select name, isbn, year, author from books where isbn = '"+isbn+"' " +
                "and name = '"+bookName+"' and year = '"+year+"' and author = '"+author+"'";

        Map<String, Object> dbData2 = DBUtils.getRowMap(query);
        //real life you dont save those info into variables
        Assert.assertEquals(booksPage.bookName.getAttribute("value"),dbData2.get("name").toString());
        Assert.assertEquals(booksPage.isbn.getAttribute("value"),dbData2.get("isbn").toString());
        Assert.assertEquals(booksPage.year.getAttribute("value"),dbData2.get("year").toString());
        Assert.assertEquals(booksPage.author.getAttribute("value"),dbData2.get("author").toString());

    }
}
