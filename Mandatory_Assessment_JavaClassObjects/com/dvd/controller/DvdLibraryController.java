
package com.dvd.controller;

import com.dvd.dao.DvdLibraryDao;
import com.dvd.exception.*;
import com.dvd.view.DvdLibraryView;
import com.dvd.dto.Dvd;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class DvdLibraryController {
    //The controller orchestrates the work, it does not do any of the work itself.
    
    //With dependency injection:
    private DvdLibraryView view;
    private DvdLibraryDao dao;
    //Without dependency injection:
//    private DvdLibraryView view = new DvdLibraryView(); //Removed this hard-coded reference so that its dependency is injected into the constructor
//    private DvdLibraryDao dao = new DvdLibraryDaoFileImpl(); //Removed this hard-coded reference so that its dependency is injected into the constructor

 //Dependency injection
    //The controller object should not be responsible for directly instantiating the
    //the implementation of any of its member fields that might have more than one
    // implementation(view and dao).
    
    //This is to ensure that the controller and view have no idea which implementation of DAO or 
    //UserIO they are using, ensuring the components are loosely coupled.
    
    
    //Constructor to initialise the dao and view fields
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        //Controller = "brains of the operation". Knows what needs to be done, when it needs
        //to be done and what needs to be done, and what component can do it.
        //It acts as a general contractor, directoring work but never doing the work itself.
        //The controller can talk with the DAO and the view.
        this.dao = dao;
        this.view = view;
    }
    

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                        case 1:
                            createDvd();
                            break;
                        case 2:
                            removeDvd();
                            break;
                        case 3:
                            editDvd();
                            break;
                        case 4:
                            listDvds();
                            break;
                        case 5:
                            getDvd();
                            break;
                        case 6:
                            findDvds();
                            break;
                        case 7:
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                }
            }
            //io.print("exit");
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    private void createDvd()throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(),newDvd);
        view.displayCreateSuccessBanner();
    }
    private void listDvds() throws DvdLibraryDaoException {
       
        List <Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    private void getDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }
    private void removeDvd() throws DvdLibraryDaoException  {
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }
    private void exitMessage() {
        view.displayExitBanner();
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    private void editDvd() throws DvdLibraryDaoException  {
        view.displayEditDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd dvdToEdit = dao.getDvd(title);
        if (dvdToEdit==null) {
            view.displayNullDvd();
        } else {
            view.displayDvd(dvdToEdit);
            int editMenuSelection = 0;
            boolean keepGoing = true;
            while (keepGoing) {
                editMenuSelection = view.printEditMenuAndGetSelection();

                switch (editMenuSelection){
                    case 1:
                        editReleaseDate(title);
                        break;
                    case 2:
                        editMpaaRating(title);
                        break;
                    case 3:
                        editDirectorName(title);
                        break;
                    case 4:
                        editUserRating(title);
                        break;
                    case 5:
                        editStudioName(title);
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (keepGoing == false) {
                    break;
            } 
        }
        }
    }
     

    private void editReleaseDate(String title) throws DvdLibraryDaoException {
        view.displayEditReleaseDateBanner();
        LocalDate newReleaseDate = view.getReleaseDate();
        Dvd editedDvd = dao.changeReleaseDate(title, newReleaseDate);
        view.displayEditResult();
    }
    private void editMpaaRating(String title) throws DvdLibraryDaoException {
        //view.displayEditMpaaRatingBanner();
        String newMpaaRating = view.getMpaaRating();
        Dvd editedDvd = dao.changeMpaaRating(title, newMpaaRating);
        view.displayEditResult();
    }
    private void editDirectorName(String title) throws DvdLibraryDaoException {
        //view.displayEditDirectorNameBanner();
        String newDirectorName = view.getDirectorName();
        Dvd editedDvd = dao.changeDirectorName(title, newDirectorName);
        view.displayEditResult();
    }
    private void editUserRating(String title) throws DvdLibraryDaoException {
        String newUserRating = view.getUserRating();
        Dvd editedDvd = dao.changeUserRating(title, newUserRating);
        view.displayEditResult();
    }
    private void editStudioName(String title) throws DvdLibraryDaoException {
        String newStudioName = view.getStudioName();
        Dvd editedDvd = dao.changeStudioName(title, newStudioName);
        view.displayEditResult();
    }
    
    private void findDvds() throws DvdLibraryDaoException {
        view.displayFindDvdsBanner();
            int findDvdsSelection = 0;
            boolean keepGoing = true;
            while (keepGoing) {
                findDvdsSelection = view.printFindMenuAndGetSelection();
                switch (findDvdsSelection){
                    case 1:
                        findMoviesLastNYears();
                        break;
                    case 2:
                        findMoviesByMpaaRating();
                        break;
                    case 3:
                        findMoviesByDirector();
                        break;
                    case 4:
                        findMoviesByStudio();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
//                if (keepGoing == false) {
//                    break;
            } 
        }
    private void findMoviesLastNYears() throws DvdLibraryDaoException {
        int n = view.getNYears();
        Map<String, Dvd> filteredDvds = dao.getDvdsLastYears(n);
        view.displayDvds(filteredDvds);
    }
    
    private void findMoviesByMpaaRating() throws DvdLibraryDaoException {
        String mpaaRating = view.getMpaaRating();
        Map<String, Dvd> filteredDvds = dao.getDvdsByMpaaRating(mpaaRating);
        view.displayDvds(filteredDvds);
    }
    
    private void findMoviesByDirector() throws DvdLibraryDaoException {
        String director = view.getDirectorName();
        Map<String, Dvd> filteredDvds = dao.getDvdsByDirector(director);
        view.displayDvds(filteredDvds);
    }
    private void findMoviesByStudio() throws DvdLibraryDaoException {
        String studio = view.getStudioName();
        Map<String, Dvd> filteredDvds = dao.getDvdsByStudio(studio);
        view.displayDvds(filteredDvds);
    }
 
    }


