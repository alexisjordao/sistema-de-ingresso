/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import java.util.ArrayList;

/**
 *
 * @author LucasSMC
 */
public class Presentation {
	private double value = 0;
	private String ageRate = "L";
	private String description = null;
	private ArrayList<Time> timeList = new ArrayList<>();
    
    public String getAgeRate()
    {
        return this.ageRate;
    }
    
    public void setAgeRate(String ageRate)
    {
        this.ageRate = ageRate;
    }
    
    public double getValue()
    {
        return this.value;
    }
    
    public void setValue(double value)
    {
        this.value = value;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description)
    {
    	this.description = description;
    }

	public ArrayList<Time> getTimeList() {
		return timeList;
	}

	public void setTimeList(ArrayList<Time> timeList) {
		this.timeList = timeList;
	}

	public void addTime(Time time)
	{
		this.timeList.add(time);
	}
	
}
