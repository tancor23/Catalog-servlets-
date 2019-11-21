package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.BookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl extends BookDAO {
    private static final String CHECK_BOOK_BY_ID_SQL = "SELECT id,name,author,page FROM books WHERE name=? and author=?;";
    private static final String ADD_NEW_BOOK_SQL = "INSERT INTO books (`name`, `author`, `page`) VALUES (?, ?, ?);";
    private static final String UPDATE_BOOK_SQL = "UPDATE books SET name=?, author=?, page=? WHERE id=?;";
    private static final String GET_BOOK_BY_ID_SQL = "SELECT * FROM books WHERE id=?;";
    private static final String GET_ALL_BOOKS_SQL = "SELECT * FROM books;";
    private static final String DELETE_BOOK_BY_ID_SQL = "DELETE FROM books WHERE id=?;";
    private static final String SELECT_ALL_MAPPED_USER_ID_SQL = "SELECT user_id FROM user_book WHERE book_id=?;";

    public BookDAOImpl(Datasource datasource) {
        super(datasource);
    }

    @Override
    public Book getBookInDB(String name, String author) throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Book book = null;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(CHECK_BOOK_BY_ID_SQL);
            statement.setString(1, name);
            statement.setString(2, author);

            rs = statement.executeQuery();

            if (rs.next()) {
                book = new Book();
                book.setId(rs.getInt(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPage(rs.getInt(4));
            }
        } catch (Exception e) {
            throw new DAOException("getBookInDB() - SQL Error", e);
        }
        return book;
    }

    @Override
    public boolean createBook(String name, String author, int countOfPage) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(ADD_NEW_BOOK_SQL);

            statement.setString(1, name);
            statement.setString(2, author);
            statement.setInt(3, countOfPage);

            status = statement.executeUpdate();
            if (status != 1) {
                return false;
            }
        } catch (Exception e) {
            throw new DAOException("createBook() - SQL Error", e);
        }
        return true;
    }

    @Override
    public List<Long> getAllMappedUserIds(Long bookId) throws DAOException {
        List<Long> ids = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection connection = datasource.getConnection()) {
            ps = connection.prepareStatement(SELECT_ALL_MAPPED_USER_ID_SQL);
            ps.setLong(1, bookId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getLong(1));
            }
        } catch (Exception e) {
            throw new DAOException("BookDAOImpl getAllMappedUserIds() - SQL Error", e);
        }
        return ids;
    }

    @Override
    public Book create(Book book) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(ADD_NEW_BOOK_SQL);

            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPage());

            status = statement.executeUpdate();
            if (status != 1) {
                return book;
            }
        } catch (Exception e) {
            throw new DAOException("createBook() - SQL Error", e);
        }
        return book;
    }

    @Override
    public Book findById(Long id) throws DAOException {
        PreparedStatement prStatement = null;
        ResultSet rs = null;
        Book book = new Book();
        book.setId(id);

        try (Connection connection = datasource.getConnection()) {
            prStatement = connection.prepareStatement(GET_BOOK_BY_ID_SQL);
            prStatement.setLong(1, id);
            rs = prStatement.executeQuery();

            if (rs.next()) {
                book.setId(rs.getLong(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPage(rs.getInt(4));
            } else {
                book = null;
            }
        } catch (Exception e) {
            throw new DAOException("book findById() - SQL Error", e);
        }
        return book;
    }

    @Override
    public List<Book> findAll() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();

        try (Connection connection = datasource.getConnection()) {
            ps = connection.prepareStatement(GET_ALL_BOOKS_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPage(rs.getInt(4));
                books.add(book);
            }
        } catch (Exception e) {
            throw new DAOException("book findAll() - SQL Error", e);
        }

        return books;
    }

    @Override
    public void update(Book book) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(UPDATE_BOOK_SQL);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPage());
            statement.setLong(4, book.getId());

            status = statement.executeUpdate();
            if (status != 1) {
                book = null;
            }
        } catch (Exception e) {
            throw new DAOException("book update() - SQL Error", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(DELETE_BOOK_BY_ID_SQL);
            statement.setLong(1, id);
            status = statement.executeUpdate();
            if (status == 1) {
                return true;
            }
        } catch (Exception e) {
            throw new DAOException("book update() - SQL Error", e);
        }
        return false;
    }

}
