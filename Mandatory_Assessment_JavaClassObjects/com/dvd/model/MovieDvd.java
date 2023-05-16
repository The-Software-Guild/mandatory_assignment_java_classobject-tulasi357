
package com.dvd.model;

import com.dvd.dto.Dvd;

/**
 *
 * @author User
 */
//Note:This class is not being used at the moment
public class MovieDvd extends Dvd {
    //Example of inheritance - the mechanism by which one class (child/subclass) is 
    //allowed to inherit the features (fields and methods another class (parent/super/base class)
    //Here, DVD is the base class and MovieDvd is the subclass. 
    //Inheritance allows us to express an is-a relationship. TvShowDVD is a DVD, but with a few more
    //features.
    
    String genre;

    public MovieDvd(String title) {
        super(title);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}