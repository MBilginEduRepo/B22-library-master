Feature: adding book

  @smoke @db
  Scenario Outline: Verify book information with db
    Given I am on the login page
    And I login to application as a librarian
    And I navigate to "Books" page
    When I click to Add Book button
    Given I fill out the book info form "Book Name" "Author" "ISBN" "Year" "Book Category" , "<Book Name>" "<Author>" "<ISBN>" "<Year>" "<Book Category>"
    Then book information must match the database for "<Book Name>" "<Author>" "<ISBN>" "<Year>" "<Book Category>"
    Examples:
      | Book Name            | Author         | ISBN      | Year | Book Category |
      | Kurk Mantolu Madonna | Sabahattin Ali | 123438987 | 1900 | Drama         |

