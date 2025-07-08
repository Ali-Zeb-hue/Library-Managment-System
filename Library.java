import java.sql.*;

public class Library {
    private Connection connection;

    public Library() {
        connection = DatabaseConnection.getConnection();
        if (connection == null) {
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (id, title, author, publisher, year, isbn, copies) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, book.getBookDetails()[0].toString());
            stmt.setString(2, book.getBookDetails()[1].toString());
            stmt.setString(3, book.getBookDetails()[2].toString());
            stmt.setString(4, book.getBookDetails()[3].toString());
            stmt.setString(5, book.getBookDetails()[4].toString());
            stmt.setString(6, book.getBookDetails()[5].toString());
            stmt.setString(7, book.getBookDetails()[6].toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public java.util.List<Book> getBooks() { // Changed return type to List for flexibility
        java.util.List<Book> books = new java.util.ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getString("year"),
                    rs.getString("isbn"),
                    rs.getString("copies")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book findBookById(String bookID) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bookID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("isbn"),
                        rs.getString("copies")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteBook(String bookID) {
        String sql = "DELETE FROM books WHERE id = ?"; // Corrected typo
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bookID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, year = ?, isbn = ?, copies = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, book.getBookDetails()[1].toString());
            stmt.setString(2, book.getBookDetails()[2].toString());
            stmt.setString(3, book.getBookDetails()[3].toString());
            stmt.setString(4, book.getBookDetails()[4].toString());
            stmt.setString(5, book.getBookDetails()[5].toString());
            stmt.setString(6, book.getBookDetails()[6].toString());
            stmt.setString(7, book.getBookDetails()[0].toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}