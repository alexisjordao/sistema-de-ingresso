/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import java.io.IOException;
import java.util.ArrayList;





//import DATA.IPresentationStore;
import DATA.TheaterPresentationStore;

/**
 *
 * @author LucasSMC
 */
public class PresentationManager {

    public ArrayList<Presentation> getPresentationTable(String FILENAME) {
    	TheaterPresentationStore tpstore = TheaterPresentationStore.getInstance();
    	if(tpstore.getCachedPresentationTable().isEmpty()){
    		try{
    			tpstore.loadPresentationList(FILENAME);
    		}
    		catch(IOException e)
    		{
    			System.out.println("Exception thrown :" + e);
    			return null;
    		}
    	}
    	return tpstore.getCachedPresentationTable();
    }

    
	public Presentation choosePresentation(int pindex, int tindex, String FILENAME) {
		ArrayList<Presentation> table = getPresentationTable(FILENAME);
		
		Presentation tempPresentation = null;
		ArrayList<Time> tempTimeList;
		Time tempTime;
		
		try{
		tempPresentation = table.get(pindex);
		tempTimeList = tempPresentation.getTimeList();
		tempTime = tempTimeList.get(tindex);
		tempTimeList.clear();
		tempTimeList.add(tempTime);
		tempPresentation.setTimeList(tempTimeList);
		}catch(IndexOutOfBoundsException ex)
		{
			tempPresentation = null;
			System.out.println("O numero entrado não corresponde com as possibilidades. Tente novamente:");
		}
		
		return tempPresentation;
		
	}   
	
}
