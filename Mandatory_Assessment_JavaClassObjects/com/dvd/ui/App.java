
package com.dvd.ui;

import com.dvd.controller.DvdLibraryController;
import com.dvd.dao.DvdLibraryDao;
import com.dvd.dao.UserIO;
import com.dvd.dto.Dvd;
import com.dvd.exception.DvdLibraryDaoException;
import com.dvd.util.DvdLibraryDaoFileImpl;
import com.dvd.util.UserIOConsoleImpl;
import com.dvd.view.DvdLibraryView;

/**
 *
 * @author User
 */
public class App {
    public static void main(String[] args) {
          //Dependency injection
          //Code below ensures that the App class configures, instantiates and assembles the classes in the application.
          //An object should not be responsible for directly instantiating the
          //the implementation of any of its member fields that might have more than 
          //one implementation.
          //The App class acts as the application assembler, it chooses the implementations of the dependencies and 
          //wires them together.
          UserIO myIo = new UserIOConsoleImpl();
          DvdLibraryView myView = new DvdLibraryView(myIo);
          DvdLibraryDao myDao;
          try {
				myDao = new DvdLibraryDaoFileImpl();
				DvdLibraryController controller = new DvdLibraryController(myDao, myView);
				controller.run(); 
          } 
          catch (DvdLibraryDaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
          }
                 

       


    }
}






          
//        DvdLibraryController controller = new DvdLibraryController();
//        controller.run();
//          Dvd dvd1 = new Dvd("The Shawshank Redemption","17-Feb-1995","15", "Frank Darabont", "9.3");
//          Dvd dvd2 = new Dvd("Schlinder's List","18-Feb-1994","15", "Steven Spielberg", "9.3");
//          Dvd dvd3 = new Dvd("Forrest Gump","07-Oct-1994","12", "Robert Zemeckis", "8.8");
//          Dvd dvd4 = new Dvd("Mean Girls","18-Jun-2004","12A", "Mark Waters", "7.0");
//          Dvd dvd5 = new Dvd("Greenland","05-Feb-2021","15", "Ric Roman Waugh", "6.4");
//          Dvd dvd6 = new Dvd("The Big Short","22-Jan-2016","15", "Adam McKay", "7.8");