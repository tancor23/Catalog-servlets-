package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.exception.DAOException;
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
        Book book = null;
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(CHECK_BOOK_BY_ID_SQL)) {
            ps.setString(1, name);
            ps.setString(2, author);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = new Book();
                book.setId(rs.getInt(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPage(rs.getInt(4));
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException("BookDAOImpl getBookInDB() - SQL Error", e);
        }
        return book;
    }

    @Override
    public boolean createBook(String name, String author, int countOfPage) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(ADD_NEW_BOOK_SQL)) {
            ps.setString(1, name);
            ps.setString(2, author);
            ps.setInt(3, countOfPage);
            int status = ps.executeUpdate();
            if (status != 1) {
                return false;
            }
        } catch (Exception e) {
            throw new DAOException("BookDAOImpl createBook() - SQL Error", e);
        }
        return true;
    }

    @Override
    public List<Long> getAllMappedUserIds(Long bookId) throws DAOException {
        List<Long> ids = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_MAPPED_USER_ID_SQL)) {
            ps.setLong(1, bookId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getLong(1));
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException("BookDAOImpl getAllMappedUserIds() - SQL Error", e);
        }
        return ids;
    }

    @Override
    public Book create(final Book book) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(ADD_NEW_BOOK_SQL)) {
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPage());
            int status = ps.executeUpdate();
            if (status != 1) {
                return book;
            }
        } catch (Exception e) {
            throw new DAOException("BookDAOImpl createBook() - SQL Error", e);
        }
        return book;
    }

    @Override
    public Book findById(Long id) throws DAOException {
        Book book = new Book();
        book.setId(id);
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(GET_BOOK_BY_ID_SQL)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book.setId(rs.getLong(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPage(rs.getInt(4));
            } else {
                book = null;
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException("BookDAOImpl findById() - SQL Error", e);
        }
        return book;
    }

    @Override
    public List<Book> findAll() throws DAOException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(GET_ALL_BOOKS_SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPage(rs.getInt(4));
                books.add(book);
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException("BookDAOImpl findAll() - SQL Error", e);
        }

        return books;
    }

    @Override
    public boolean update(final Book book) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK_SQL)) {
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPage());
            ps.setLong(4, book.getId());
            int status = ps.executeUpdate();
            return status == 1;
        } catch (Exception e) {
            throw new DAOException("book update() - SQL Error", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_BOOK_BY_ID_SQL)) {
            ps.setLong(1, id);
            int status = ps.executeUpdate();
            return status == 1;
        } catch (Exception e) {
            throw new DAOException("BookDAOImpl update() - SQL Error", e);
        }
    }

}
