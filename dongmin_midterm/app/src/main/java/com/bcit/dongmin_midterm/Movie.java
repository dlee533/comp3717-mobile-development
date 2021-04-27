package com.bcit.dongmin_midterm;

public class Movie {
    private int _id;
    private String _name;
    private int _year;
    private String _description;
    private int _imageResourceId;

    public static final Movie[] movies = {
            new Movie(1, "Avengers", 1999, "The adventures of the Marvel Comics Universe's greatest general membership superhero team.", R.drawable.avengers),
            new Movie(2, "Frozen", 2013, "When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister Anna teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.", R.drawable.frozen_),
            new Movie(3, "Gladiator", 2000, "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.", R.drawable.gladiator),
    };

    // Each country has a name, description and an image resource
    private Movie(int id, String name, int year, String description, int imageResourceId) {
        _id = id;
        _name = name;
        _year = year;
        _description = description;
        _imageResourceId = imageResourceId;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public int getYear() {
        return _year;
    }

    public String getDescription() {
        return _description;
    }

    public int getImageResourceId() {
        return _imageResourceId;
    }
}
