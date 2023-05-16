package com.dvd.dao;

import com.dvd.dto.Dvd;
import com.dvd.exception.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DvdLibraryDao {
    //This interface defines the set of methods that must be implemented by any class
    //that wants to play the role of DAO in this application.
    
    
    /** 
     * Adds the given DVD to the DVD library and associates it with the given
     * DVD title.If there is already a DVD associated with the given title it will
     * return that DVD object, otherwise it will return null.
     * 
     * @param title with which the DVD is to be associated
     * @param dvd DVD to be added to the DVD library,
     * @return the DVD object previously associated with the given title if it
     * exists, null otherwise.
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException;
    
    /**
     * Removes from the DVD library the DVD associated with the given title.Returns the DVD object that is being removed or null if there is no DVD
     * associated with  the given title.
     * @param title title of DVD to be removed
     * @return Dvd object that was removed or null if no DVD was associated with the 
     * given DVD title
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException
     */
    Dvd removeDvd(String title) throws DvdLibraryDaoException;
    
    /**
     * Edits the DVD release date from the library the DVD associated with the given title.Returns the DVD object that is being edited or null if there is no DVD
     *  associated with the given title.
     * @param title title of DVD to be edited
     * @param releaseDate release date of DVD to be changed to
     * @return Dvd object that was edited or null if no DVD was associated with the 
     * given DVD title
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException
     */
    Dvd changeReleaseDate(String title, LocalDate releaseDate)throws DvdLibraryDaoException;
    
    
    /**
     * Edits the DVD MPAA rating date from the library the DVD associated with the given title.Returns the DVD object that is being edited or null if there is no DVD
     * associated with the given title.
     * @param title title of DVD to be edited
     * @param mpaaRating MPAA rating of DVD to be changed to    
     * @return Dvd object that was edited or null if no DVD was associated with the
     * given DVD title
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException    
     */
    Dvd changeMpaaRating(String title, String mpaaRating)throws DvdLibraryDaoException;
    
    
    /**
     * Edits the DVD MPAA rating date from the library the DVD associated with the given title.Returns the DVD object that is being edited or null if there is no DVD
     * associated with the given title.
     * @param title title of DVD to be edited
     * @param directorName director name of DVD to be changed to    
     * @return Dvd object that was edited or null if no DVD was associated with the
     * given DVD title
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException    
     */    
    Dvd changeDirectorName(String title, String directorName)throws DvdLibraryDaoException;
    
    
      /**
     * Edits the DVD MPAA rating date from the library the DVD associated with the given title.Returns the DVD object that is being edited or null if there is no DVD
     * associated with the given title.
     * @param title title of DVD to be edited
     * @param userRating user rating of DVD to be changed to    
     * @return Dvd object that was edited or null if no DVD was associated with the
     * given DVD title    
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException    
     */     
    Dvd changeUserRating(String title, String userRating)throws DvdLibraryDaoException;
    
    
      /**
     * Edits the DVD studio name from the library the DVD associated with the given title.Returns the DVD object that is being edited or null if there is no DVD
     * associated with the given title.
     * @param title title of DVD to be edited
     * @param studioName name of the studio of DVD to be changed to    
     * @return Dvd object that was edited or null if no DVD was associated with the
     * given DVD title    
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException    
     */     
    Dvd changeStudioName(String title, String studioName)throws DvdLibraryDaoException;    

    /**
     * Returns a list of all DVDs in the DVD library.
     * @return List containing all DVDs in the DVD library.
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException
     */
    List <Dvd> getAllDvds () throws DvdLibraryDaoException ;
    
    /**
     * Returns the DVD object associated with the given DVD title.Returns null if no such DVD exists.
     * @param title
     * @return Returns the DVD object associated with the given DVD title.Returns null if no such DVD exist
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException
     */
    Dvd getDvd(String title) throws DvdLibraryDaoException ;
    
    Map<String, Dvd> getDvdsLastYears(int years) throws DvdLibraryDaoException;
    
    Map<String, Dvd> getDvdsByMpaaRating(String mpaaRating) throws DvdLibraryDaoException;
    
    Map<String, Dvd> getDvdsByDirector(String directorName) throws DvdLibraryDaoException;
    
    Map<String, Dvd> getDvdsByStudio(String studioName) throws DvdLibraryDaoException;
    
    
    
}