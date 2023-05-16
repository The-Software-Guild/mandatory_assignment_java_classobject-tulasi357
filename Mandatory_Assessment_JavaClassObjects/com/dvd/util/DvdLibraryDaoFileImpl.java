package com.dvd.util;

import com.dvd.dao.DvdLibraryDao;
import com.dvd.dto.Dvd;
import com.dvd.exception.DvdLibraryDaoException;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author User
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    //As DvdLibraryDaoFileImpl implements DvdLibraryDao, DvdLibraryDaoFileImpl
    //must provide implementations for each of the methods defined in the 
    //DvdLibraryDao interface.
    //The DAO is responsible for the persistence and retrievel of the DVD data.
    //DAO is a part of the model of the MVC pattern.
    
    //dvdsMap map to hold data in memory
    private Map <String, Dvd> dvdsMap = new HashMap<>();  
    
    private final String LIBRARY_FILE;  //static removed 
        
    //default constructor
    public DvdLibraryDaoFileImpl() throws DvdLibraryDaoException {
        LIBRARY_FILE = "dvd_library.txt";
        deserializeLibrary();
    }
    
    //constructor to use when testing
    // required to separate the production data from the test data
    //now we can create instances of DvdLibraryDaoFileImpl that utilise another file.
    //This ensures we dont overwrite our production application data during testing.
    public DvdLibraryDaoFileImpl(String libraryTextFile) throws DvdLibraryDaoException {
        LIBRARY_FILE = libraryTextFile;
        //deserializeLibrary();
    }
    
    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        //loadLibrary reads the LIBRARY_FILE into memory. 
        //if(dvdsMap.isEmpty())
        	//deserializeLibrary();
        //New DVD is added to the dvdsMap HashMap.
        Dvd newDvd = dvdsMap.put(title, dvd);
        //writeLibrary writes all the DVDs in the DVD library out to a LIBRARY_FILE.
        serializeLibrary();
        return newDvd;
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Dvd removedDvd = dvdsMap.remove(title);
        serializeLibrary();
        return removedDvd;
    }
    
    

	/** * 
     * This code gets all of the DVD objects out of the DVD map as a collection by calling values() method. We pass that returned 
     * collection into a the constructor for a new ArrayList. As one of the constructors of ArrayList takes a collection as a parameter
     * we can effectively convert the collection of DVD objects into an ArrayList of DVD objects that we can return from the method.
     * @return As ArrayList implements the List interface,it can be treated as a list and so we can return an ArrayList.
     * @throws com.chloe.dvdlibrary.dao.DvdLibraryDaoException
     */
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException{
      // if(dvdsMap.isEmpty())
    	  // deserializeLibrary();
       return new ArrayList(dvdsMap.values());
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        return dvdsMap.get(title);
    }

    @Override
    public Dvd changeReleaseDate(String title, LocalDate releaseDate)throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Dvd dvdToEdit = dvdsMap.get(title);
        dvdToEdit.setReleaseDate(releaseDate);
        serializeLibrary();
        return dvdToEdit;
    }

    @Override
    public Dvd changeMpaaRating(String title, String mpaaRating) throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Dvd dvdToEdit = dvdsMap.get(title);
        dvdToEdit.setMpaaRating(mpaaRating);
        serializeLibrary();
        return dvdToEdit;
    }

    @Override
    public Dvd changeDirectorName(String title, String directorName) throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Dvd dvdToEdit = dvdsMap.get(title);
        dvdToEdit.setDirectorName(directorName);
        serializeLibrary();
        return dvdToEdit;
    }

    @Override
    public Dvd changeUserRating(String title, String userRating)throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Dvd dvdToEdit = dvdsMap.get(title);
        dvdToEdit.setUserRating(userRating);
        serializeLibrary();
        return dvdToEdit;
    }
    
    @Override
    public Dvd changeStudioName(String title, String studioName) throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Dvd dvdToEdit = dvdsMap.get(title);
        dvdToEdit.setStudio(studioName);
        serializeLibrary();
        return dvdToEdit;
    }
    
    @Override
    public Map<String, Dvd> getDvdsLastYears(int years) throws DvdLibraryDaoException {
        LocalDate now = LocalDate.now();
        LocalDate sinceThisDate = now.minusYears(years);
       // loadLibrary();
        Map<String, Dvd> dvdsLastYears = dvdsMap.entrySet().stream()
                .filter((dvd) -> dvd.getValue().getReleaseDate().isAfter(sinceThisDate))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return dvdsLastYears;
    }
    @Override
    public Map<String, Dvd> getDvdsByMpaaRating(String mpaaRating) throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Map<String, Dvd> dvdsMpaRating = dvdsMap
                .entrySet()
                .stream()
                .filter((dvd) -> dvd.getValue().getMpaaRating().equalsIgnoreCase(mpaaRating))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return dvdsMpaRating;
    }
    @Override
    public Map<String, Dvd> getDvdsByDirector(String directorName) throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Map<String, Dvd> dvdsByDirector = dvdsMap
                .entrySet()
                .stream()
                .filter((dvd) -> dvd.getValue().getDirectorName().equalsIgnoreCase(directorName))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return dvdsByDirector;
    }
    @Override
    public Map<String, Dvd> getDvdsByStudio(String studioName) throws DvdLibraryDaoException {
    	//if(dvdsMap.isEmpty())
    		//deserializeLibrary();
        Map<String, Dvd> dvdsByStudio = dvdsMap
                .entrySet().stream().filter((dvd) -> dvd.getValue().getStudio().equalsIgnoreCase(studioName))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return dvdsByStudio;
    }
    
    
    //FILE storage
    // Data serialization and deserialization
    /**
     * serializeLibrary organises the DVD information from an in memory object into a
     * stream, so it is in an appropriate format for writing it to permanent storage.
     * @param no parama
     * @return void 
     */
 
    private  void serializeLibrary() throws DvdLibraryDaoException {
		// TODO Auto-generated method stub
		ObjectOutputStream oos = null;
		FileOutputStream fis = null;
		File file = null;
		try {
			file = new File(LIBRARY_FILE);
			if(!file.exists()) {
				file.createNewFile();
			}
			fis = new  FileOutputStream(file);
			oos =new ObjectOutputStream(fis);
			oos.writeObject(dvdsMap);
		}
		// We are translating the IOException to an application specific exception
	    //and then simple throwing it i.e. reporting it to the code that called us.
		catch(Exception e) {
			throw new DvdLibraryDaoException("Could not save DVD data",e);
		}finally {
			try {
				if(oos != null)
					oos.close();
				if(fis != null)
					fis.close();
			} catch (IOException e) {
				
				throw new DvdLibraryDaoException("Could not save DVD data",e);
			}
		}
		
		System.out.println("Object serialized successfully");
	}
    
	private void deserializeLibrary() throws DvdLibraryDaoException {
		FileInputStream fis=null;
		ObjectInputStream ois = null;
		File file = null;
		try {
			file = new File(LIBRARY_FILE);
			if(!file.exists()) {
				//System.out.println("fILE does not contain any dvdS.");
				return;
			}
						
			Map<String,Dvd> fileDvdMap=null;
			try {
				fis=new FileInputStream(file);
				if(fis.available() ==0) {
					return;
				}
				ois = new ObjectInputStream(fis);
				
				if( (fileDvdMap= (Map<String,Dvd>)ois.readObject()) != null) {
					dvdsMap = fileDvdMap;
				}
			}
			catch(EOFException e) {
				
			}		
		}
		// We are translating the IOException to an application specific exception
		// and then simple throwing it i.e. reporting it to the code that called us.
		catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			throw new DvdLibraryDaoException("Could not read DVD data", e);
		} finally {
			try {
				if(fis != null)
					fis.close();
				if(ois != null)
					ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//throw new DvdLibraryDaoException("Could not read DVD data", e);
			}
		}
		System.out.println("Object de-serialized from file successfully");
	}
}