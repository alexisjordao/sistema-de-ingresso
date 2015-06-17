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

import BUSINESS.Presentation;
import BUSINESS.Time;

/**
 *
 * @author LucasSMC
 */
public class TheaterPresentationStore implements IPresentationStore{
	
    private ArrayList<Presentation> cachedPresentationTable = new ArrayList<>();

	//private Presentation cachedPresentation;
	
    private static TheaterPresentationStore instance;
    
    private TheaterPresentationStore(){}
    
    public static synchronized TheaterPresentationStore getInstance()
    {
    	if(instance == null)
    		instance = new TheaterPresentationStore();
    	return instance;
    }
    
    
    public ArrayList<Presentation> loadPresentationList(String FILENAME) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(FILENAME));
        
        String sCurrentLine = new String();
		
        ArrayList<Presentation> PresentationArray = new ArrayList<>();
		Presentation tempPresentation = new Presentation();
		
		Time tempTime = new Time();
		String tempDate = "";
		String tempHour = "";
		boolean hour = false;
		
		int counter = 1;
		
		while ((sCurrentLine = br.readLine()) != null) 
		{
			if(counter%4 == 1)
				tempPresentation.setDescription(sCurrentLine);
			else if(counter%4 == 2)
				tempPresentation.setAgeRate(sCurrentLine);
			else if(counter%4 == 3){
				tempPresentation.setValue((int) Integer.parseInt(sCurrentLine));
			}
			else
			{
				for(int i = 0; i < sCurrentLine.length(); i++)
				{
					if(sCurrentLine.charAt(i) == '(')
						hour = true;
					else if(sCurrentLine.charAt(i) == ')')
					{
						tempTime.setDate(tempDate);
						tempTime.setHour(tempHour);
						tempPresentation.addTime(tempTime);
						tempTime = new Time();
						tempHour = "";
						tempDate = "";
						hour = false;
					}
					else if(sCurrentLine.charAt(i) != ' ')
					{
						if(!hour)
						{
							tempDate = tempDate + Character.toString(sCurrentLine.charAt(i));
						}
						else
						{
							if(sCurrentLine.charAt(i) != ',')
							{
								tempHour = tempHour + Character.toString(sCurrentLine.charAt(i));
							}
							else
							{
								tempTime.setDate(tempDate);
								tempTime.setHour(tempHour);
								tempPresentation.addTime(tempTime);
								tempTime = new Time();
								tempHour = "";
							}
						}
					}
				}
				
				PresentationArray.add(tempPresentation);
				tempPresentation = new Presentation();
			}
			counter++;
		}
		
		cachedPresentationTable = PresentationArray;
		
		br.close();
		
		return PresentationArray;
    }

	public ArrayList<Presentation> getCachedPresentationTable()
	{
		return this.cachedPresentationTable;
	}

	@Override
	public Presentation loadPresentationList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean savePresentationList(Presentation p) {
		// TODO Auto-generated method stub
		return false;
	}   
    
}
