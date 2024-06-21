CREATE DATABASE LibraryDB;

USE LibraryDB;

CREATE TABLE Books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(100),
    genre VARCHAR(50),
    published_date DATE,
    isbn VARCHAR(20),
    copies_available INT
);

CREATE TABLE Members (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    address VARCHAR(200),
    phone VARCHAR(15),
    email VARCHAR(100),
    membership_date DATE
);

CREATE TABLE Librarians (
    librarian_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE BorrowedBooks (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    member_id INT,
    issue_date DATE,
    return_date DATE,
    fine DECIMAL(5,2),
    FOREIGN KEY (book_id) REFERENCES Books(book_id),
    FOREIGN KEY (member_id) REFERENCES Members(member_id)
);

