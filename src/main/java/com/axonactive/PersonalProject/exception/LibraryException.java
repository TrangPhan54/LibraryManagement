package com.axonactive.PersonalProject.exception;

import org.springframework.http.HttpStatus;

public class LibraryException {
    private static final String CUSTOMER_NOT_FOUND_MSG_KEY = "CustomerNotExisted";
    private static final String CUSTOMER_NOT_FOUND_MSG = "Customer Not Found";
    private static final String AUTHOR_NOT_FOUND_MSG_KEY = "AuthorNotExisted";
    private static final String AUTHOR_NOT_FOUND_MSG = "Author Not Found";
    private static final String GENRE_NOT_FOUND_MSG_KEY = "GenreNotExisted";
    private static final String GENRE_NOT_FOUND_MSG = "Genre Not Found";
    private static final String PUBLISHINGHOUSE_NOT_FOUND_MSG_KEY = "PublishingHouseNotExisted";
    private static final String PUBLISHINGHOUSE_NOT_FOUND_MSG = "Publishing House Not Found";
    private static final String BORROW_NOTE_NOT_FOUND_MSG_KEY = "BorrowNoteNotExisted";
    private static final String BORROW_NOTE_NOT_FOUND_MSG = "Borrow Note Not Found";
    private static final String BOOK_NOT_FOUND_MSG_KEY = "BookNotExisted";
    private static final String BOOK_NOT_FOUND_MSG = "Book Not Found";
    private static final String RESPONSE_NOT_FOUND_MSG_KEY = "ResponseNotExisted";
    private static final String RESPONSE_NOT_FOUND_MSG = "ResponseNotFound";
    private static final String BOOKPUBLISH_NOT_FOUND_MSG_KEY = "BookPublishNotExisted";
    private static final String BOOKPUBLISH_NOT_FOUND_MSG = "Book Publish Not Found";

    private static final String GENREBOOK_NOT_FOUND_MSG_KEY = "GenreBookNotExisted";
    private static final String GENREBOOK_NOT_FOUND_MSG = "Genre Book Not Found";
    private static final String BORROW_NOTE_DETAIL_NOT_FOUND_MSG_KEY = "BorrowNoteDetailNotExisted";
    private static final String BORROW_NOTE_DETAIL_NOT_FOUND_MSG = " Borrow Note Detail Not Found";
    private static final String PHYSICAL_BOOK_NOT_FOUND_MSG_KEY = "PhysicalBookNotExisted";
    private static final String PHYSICAL_BOOK_NOT_FOUND_MSG = " Physical Book Not Found";

    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseException CustomerNotFound() {
        return notFound(CUSTOMER_NOT_FOUND_MSG_KEY, CUSTOMER_NOT_FOUND_MSG);
    }

    public static ResponseException AuthorNotFound() {
        return notFound(AUTHOR_NOT_FOUND_MSG_KEY, AUTHOR_NOT_FOUND_MSG);
    }

    public static ResponseException GenreNotFound() {
        return notFound(GENRE_NOT_FOUND_MSG_KEY, GENRE_NOT_FOUND_MSG);

    }

    public static ResponseException PublishingHouseNotFound() {
        return notFound(PUBLISHINGHOUSE_NOT_FOUND_MSG_KEY, PUBLISHINGHOUSE_NOT_FOUND_MSG);
    }

    public static ResponseException BorrowNoteNotFound() {
        return notFound(BORROW_NOTE_NOT_FOUND_MSG_KEY, BORROW_NOTE_NOT_FOUND_MSG);
    }

    public static ResponseException BookNotFound() {
        return notFound(BOOK_NOT_FOUND_MSG_KEY, BOOK_NOT_FOUND_MSG);
    }
    public static ResponseException ResponseNotFound (){
        return notFound(RESPONSE_NOT_FOUND_MSG_KEY,RESPONSE_NOT_FOUND_MSG);

    }
    public static ResponseException GenreBookNotFound (){
        return notFound(GENREBOOK_NOT_FOUND_MSG_KEY,GENREBOOK_NOT_FOUND_MSG);
    }
    public static ResponseException BorrowNoteDetailNotFound (){
        return notFound(BORROW_NOTE_DETAIL_NOT_FOUND_MSG_KEY,BORROW_NOTE_DETAIL_NOT_FOUND_MSG);
    }
    public static ResponseException PhysicalBookNotFound (){
        return notFound(PHYSICAL_BOOK_NOT_FOUND_MSG_KEY,PHYSICAL_BOOK_NOT_FOUND_MSG);
    }

}
