
package com.dvd.dao;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public interface UserIO {
    //This interface defines the set of methods that must be implemented by any class that
    //wants to directly interact wiht the UI technology.
    
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
    
    LocalDate readDate (String prompt);
}
