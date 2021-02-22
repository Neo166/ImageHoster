package ImageHoster.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity

@Table(name = "comments")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name ="text")
    private String text;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    //Mapping (Many to one) the id (primary key) of user DB table as foreign key(user_id) to comment table.
    //One user_id (foreign key) may have association to several comment id (primary key).
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //Mapping (Many to one) the id (primary key) of image DB table as foreign key(image_id) to comment table.
    //One image_id (foreign key) may have association to several comment id (primary key).
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
