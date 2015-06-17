/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

/**
 *
 * @author LucasSMC
 */
public class Chair {
    private boolean sold;
    private String row,column;
    private String type;
    
    public Chair()
    {
    	this.sold = false;
    	this.row = null;
    	this.column = null;
    	this.type = null;
    }
    
    public Chair(boolean sold,String row,String column,String type)
    {
    	this.sold = sold;
    	this.row = row;
    	this.column = column;
    	this.type = type;
    }
    
    
    public boolean isSold()
    {
        return this.sold;  
    }
    
    public void setSold()
    {
        this.sold = true;
    }
    
    public void setOpen()
    {
        this.sold = false;
    }
    
    public String getRow()
    {
        return this.row;
    }
    
    public void setRow(String row)
    {
        this.row = row;
    }
    
    public String getColumn()
    {
        return this.column;
    }
    
    public void setColumn(String column)
    {
        this.column = column;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
}
