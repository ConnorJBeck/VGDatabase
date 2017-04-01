package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Exceptions.InstanceNotFoundException;
import com.vgdatabase304.Structures.Game;
import com.vgdatabase304.Structures.RegisteredUser;
import com.vgdatabase304.Structures.Review;
import com.vgdatabase304.Utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewAdaptor {

    private static Statement stmt;
    private static ResultSet rs;

    public static Review addReviewToDatabase(RegisteredUser user, Game game, String reviewText, double rating) throws SQLException {
        stmt = ConnectionManager.getStatement();
        rs = stmt.executeQuery("SELECT Max(REVIEWID) FROM REVIEW");
        int reviewID = 0;
        if (rs.next()) {
            reviewID = rs.getInt(1);
        }
        reviewID++;

        String sql = "INSERT INTO REVIEW VALUES (" +
                reviewID + ", '" +
                user.getUsername() + "', " +
                game.getGameID() + ", '" +
                reviewText + "', sysdate, " +
                rating + ")";
        stmt.executeUpdate(sql);
        return new Review(reviewID);
    }

    public static void removeReviewFromDatabase(Review review) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "DELETE FROM REVIEW WHERE " +
                "REVIEWID=" + review.getReviewID();
        stmt.executeUpdate(sql);
    }

    public static void setReviewID(Review review, int reviewID) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE REVIEW " +
                "SET REVIEWID=" + reviewID +
                " WHERE REVIEWID=" + review.getReviewID()
        );
        review.setReviewID(reviewID);
    }

    public static RegisteredUser getUsername(Review review) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT USERNAME FROM REVIEW WHERE REVIEWID=" + review.getReviewID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return new RegisteredUser(rs.getString(1));
        } else {
            throw new InstanceNotFoundException("No record found in REVIEW for " + review.getReviewID());
        }
    }

    public static void setUsername(Review review, RegisteredUser user) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE REVIEW " +
                "SET USERNAME='" + user.getUsername() +
                "' WHERE REVIEWID=" + review.getReviewID()
        );
    }

    public static Game getGame(Review review) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT GAMEID FROM REVIEW WHERE REVIEWID=" + review.getReviewID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return new Game(rs.getInt(1));
        } else {
            throw new InstanceNotFoundException("No record found in REVIEW for " + review.getReviewID());
        }
    }

    public static void setGame(Review review, Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE REVIEW " +
                "SET GAMEID='" + game.getGameID() +
                "' WHERE REVIEWID=" + review.getReviewID()
        );
    }

    public static double getRating(Review review) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT RATING FROM REVIEW WHERE REVIEWID=" + review.getReviewID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getDouble(1);
        } else {
            throw new InstanceNotFoundException("No record found in REVIEW for " + review.getReviewID());
        }
    }

    public static void setRating(Review review, double rating) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE REVIEW " +
                "SET RATING='" + rating +
                "' WHERE REVIEWID=" + review.getReviewID()
        );
    }

    public static String getReviewText(Review review) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT REVIEWTEXT FROM REVIEW WHERE REVIEWID=" + review.getReviewID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString(1);
        } else {
            throw new InstanceNotFoundException("No record found in REVIEW for " + review.getReviewID());
        }
    }

    public static void setReviewText(Review review, String reviewText) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE REVIEW " +
                "SET REVIEWTEXT='" + reviewText +
                "' WHERE REVIEWID=" + review.getReviewID()
        );
    }

    public static Timestamp getPostTimestamp(Review review) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT POSTDATETIME FROM REVIEW WHERE REVIEWID=" + review.getReviewID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getTimestamp(1);
        } else {
            throw new InstanceNotFoundException("No record found in REVIEW for " + review.getReviewID());
        }
    }

    public static void setPostTimestamp(Review review, Timestamp timestamp) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE REVIEW " +
                "SET POSTDATETIME='" + timestamp +
                "' WHERE REVIEWID=" + review.getReviewID()
        );
    }

    public static List<Review> getAllReviewsByUser(RegisteredUser user) throws SQLException {
        stmt = ConnectionManager.getStatement();
        List<Review> listOfReviews = new ArrayList<>();
        rs = stmt.executeQuery("SELECT REVIEWID FROM REVIEW WHERE USERNAME='" + user.getUsername() + "'");
        while (rs.next()) {
            listOfReviews.add(new Review(rs.getInt("REVIEWID")));
        }
        if (listOfReviews.size() > 0) {
            return listOfReviews;
        } else {
            throw new InstanceNotFoundException("No reviews found for user " + user.getUsername());
        }

    }

    public static List<Review> getAllReviewsByGame(Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        List<Review> listOfReviews = new ArrayList<>();
        rs = stmt.executeQuery("SELECT REVIEWID FROM REVIEW WHERE GAMEID=" + game.getGameID()+ "");
        while (rs.next()) {
            listOfReviews.add(new Review(rs.getInt("REVIEWID")));
        }
        if (listOfReviews.size() > 0) {
            return listOfReviews;
        } else {
            throw new InstanceNotFoundException("No reviews found for user " + GameAdaptor.getName(game));
        }

    }
}
