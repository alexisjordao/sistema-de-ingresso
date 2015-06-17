package BUSINESS;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import DATA.TheaterChairStore;

abstract public class AreaManager {

	public HashMap<String, List<Chair>> getArea(String FILENAME) {
		TheaterChairStore tcstore = TheaterChairStore.getInstance();
		if(tcstore.getcachedArea().isEmpty()){
    		try{
    			tcstore.loadPresentationArea(FILENAME);
    		}
    		catch(IOException e)
    		{
    			System.out.println("Exception thrown :" + e);
    			return null;
    		}
    	}
    	return tcstore.getcachedArea();
	}
	
    public Chair chooseChair(String row, String column) {
    	TheaterChairStore tcstore = TheaterChairStore.getInstance();
    	HashMap<String, List<Chair>> area = tcstore.getcachedArea();
    	
    	List<Chair> chairList = area.get(row);
    	
    	try{
    		for(int i = 0; i < chairList.size(); i++)
    		{
    			if(chairList.get(i).getColumn() != null){
    				if(chairList.get(i).getColumn().equals(column))
    				{
    					chairList.get(i).setSold();
    					return chairList.get(i);
    				}
    			}
    		}
    	}catch(Exception ex){
    		return null;
    	}
		return null;
    }

	public List<String> getRowList() {
		TheaterChairStore tcstore = TheaterChairStore.getInstance();
		return tcstore.getcachedRowList();
	}
}
