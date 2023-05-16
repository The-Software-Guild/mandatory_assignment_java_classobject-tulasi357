
package com.dvd.model;

import com.dvd.dto.Dvd;

/**
 *
 * @author User
 */
//Note:This class is not being used at the moment.
public class TvShowDvd extends Dvd {
    //Example of inheritance - the mechanism by which one class (child/subclass) is 
    //allowed to inherit the features (fields and methods another class (parent/super/base class)
    //Here, DVD is the base class and TvShowDvd is the subclass. 
    //Inheritance allows us to express an is-a relationship. TvShowDVD is a DVD, but with a few more
    //features.

    private int noOfSeasons;
    private int noOfEpisodes;

    public TvShowDvd(String dvdTitle) {
        super(dvdTitle);
    }

    public int getNoOfSeasons() {
        return noOfSeasons;
    }

    public void setNoOfSeasons(int noOfSeasons) {
        this.noOfSeasons = noOfSeasons;
    }

    public int getNoOfEpisodes() {
        return noOfEpisodes;
    }

    public void setNoOfEpisodes(int noOfEpisodes) {
        this.noOfEpisodes = noOfEpisodes;
    }

}