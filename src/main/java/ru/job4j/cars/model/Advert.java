package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"), nullable = false, updatable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"), nullable = false, updatable = false)
    private Car car;
    private String description;
    private byte[] photo;
    private boolean sold;

    public Advert() {

    }

    public static Advert of(User user, Car car, String description, byte[] photo) {
        Advert advert = new Advert();
        advert.setCreated(new Date(System.currentTimeMillis()));
        advert.setUser(user);
        advert.setCar(car);
        advert.setDescription(description);
        advert.setPhoto(photo);
        advert.setSold(false);
        return advert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date date) {
        this.created = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advert advert = (Advert) o;
        return id == advert.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Advert{"
                + "id=" + id
                + ", created=" + created
                + ", user=" + user
                + ", car=" + car
                + ", description='" + description + '\''
                + ", sold=" + sold
                + '}';
    }
}
