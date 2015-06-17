/*
 					* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import BUSINESS.Chair;

/**
 *
 * @author LucasSMC
 */
public class TheaterChairStore implements IChairStore{
    
	//CACHES
	private Chair cachedchair;
    private List<String> cachedRowList = new ArrayList<>();
    private HashMap<String, Double> cachedTypesValues = new HashMap<String,Double>();
    private HashMap<String,List<Chair>> cachedArea = new HashMap<String,List<Chair>>();
	
    //BUFFER FOR READ
    private BufferedReader br;
	
    //SINGLETON INSTANCE
	private static TheaterChairStore instance;
    
    private TheaterChairStore(){}
    
    public static synchronized TheaterChairStore getInstance(){
    	if(instance == null)
    		instance = new TheaterChairStore();
    	
    	return instance;
    }
    
    public HashMap<String, List<Chair>> loadPresentationArea(String FILENAME) throws IOException
    {
    	br = new BufferedReader(new FileReader(FILENAME));
    	
    	String sCurrentLine = new String();
    	sCurrentLine = br.readLine();
    	
    	//SAVE THE ARCHIVE FIRST 3 LINES
    	loadTypeValuesToCache(br,sCurrentLine);
    	
    	//Jump a line
    	sCurrentLine = br.readLine();
    	
    	String row = null;
    	List<Chair> tempChairList = new ArrayList<>();
    	Chair tempChair;
    	int column;
    	
    	//SAVES THE CHAIRS IN THE ARCHIVE INTO A HASHMAP
    	while ((sCurrentLine = br.readLine()) != null) 
		{
    		if(!sCurrentLine.equalsIgnoreCase("hspace")){
    			tempChairList = new ArrayList<>();
    			
    			column = 1;
    			row = Character.toString(sCurrentLine.charAt(0));
    			cachedRowList.add(row);
    			
    			for(int i = 1; i < sCurrentLine.length(); i++)
    			{
    				if(sCurrentLine.charAt(i) == ' '){}
    				else if(sCurrentLine.charAt(i) == 's')
    				{
    					tempChair = new Chair();
    					tempChair.setType("VESPACE");
    					tempChairList.add(tempChair);
    				}
    				else
    				{
    					if(sCurrentLine.charAt(i+1) == '0')
    						tempChair = new Chair(false, row, Integer.toString(column), Character.toString(sCurrentLine.charAt(i)));
    					else
    						tempChair = new Chair(true, row, Integer.toString(column), Character.toString(sCurrentLine.charAt(i)));
    					tempChairList.add(tempChair);
    					column++;
    					i++;
    				}
    			}
			
			}
    		else
    		{
    			tempChair = new Chair();
    			tempChair.setType("HESPACE");
    			tempChairList.add(tempChair);
    			cachedArea.remove(row);
    		}
    		
    		cachedArea.put(row, tempChairList);
		}
    	
    	return cachedArea;
    }

    public void loadTypeValuesToCache(BufferedReader br, String sCurrentLine) throws IOException
    {
    	int types = Integer.parseInt(sCurrentLine);
    	String type,value = "";
    	//List<String> typelist = new ArrayList<>();
    	
    	while(types > 0)
    	{
    		sCurrentLine = br.readLine();
    		type = Character.toString(sCurrentLine.charAt(0));
    		//typelist.add(type);
    		value = "";
    		
    		for (int i = 1; i < sCurrentLine.length(); i++)
    		{
    			if(sCurrentLine.charAt(i) != ' ')
    			{
    				value = value + Character.toString(sCurrentLine.charAt(i));
    			}
    		}
    		
    		cachedTypesValues.put(type, Double.parseDouble(value));
    		types--;
    	}
    }
    
    public void savePresentationArea(HashMap<String, List<Chair>> area)
    {
    	//NÃO IMPLEMENTADO!!
    	//OBJETIVO: SALVAR O ARQUIVO ANTERIOR EM UM ARQUIVO CHAMADO BACKUP#Nº E
    	//MODIFICAR O ARQUIVO COM AS CADEIRAS COMPRADAS.
    }
    
    public Chair getCachedChair(){
    	return cachedchair;
    }
    
    public void setCachedChair(Chair chair){
       this.cachedchair = chair;
    }
    
    public HashMap<String,List<Chair>> getcachedArea()
    {
    	return this.cachedArea;
    }

	public List<String> getcachedRowList() {
		return cachedRowList;
	}

	@Override
	public double getTypeValue(Chair chair) {
		return cachedTypesValues.get(chair.getType());
	}
}
