import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateReview {

    private static AtomicInteger nextGameId = new AtomicInteger();
    private int reviewID;
    private RegisteredUser userName;
    private Release release;
    private Double rating;
    private String reviewText;
    private Date postDateTime;
    private Statement stmt;

    public CreateReview(RegisteredUser userName, Release release, Date postDateTime, double rating, String reviewText) throws SQLException {
        createBasicReview(userName, release, postDateTime);
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public CreateReview(RegisteredUser userName, Release release, Date postDateTime, double rating) throws SQLException {
        createBasicReview(userName, release, postDateTime);
        this.rating = rating;
        this.reviewText = null;
    }

    public CreateReview(RegisteredUser userName, Release release, Date postDateTime, String reviewText) throws SQLException {
        createBasicReview(userName, release, postDateTime);
        this.rating = null;
        this.reviewText = reviewText;
    }

    public CreateReview(RegisteredUser userName, Release release, Date postDateTime) throws SQLException {
        createBasicReview(userName, release, postDateTime);
        this.rating = null;
        this.reviewText = null;
    }

    private void createBasicReview(RegisteredUser userName, Release release, Date postDateTime) throws SQLException {
        reviewID = nextGameId.incrementAndGet();
        this.userName = userName;
        this.release = release;
        this.postDateTime = postDateTime;
        this.stmt = ConnectionManager.getStatement();
    }

    public int getReviewID() {
        return reviewID;
    }

    public RegisteredUser getUserName() {
        return userName;
    }

    public void setUserName(RegisteredUser userName) {
        this.userName = userName;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(Date postDateTime) {
        this.postDateTime = postDateTime;
    }
}
