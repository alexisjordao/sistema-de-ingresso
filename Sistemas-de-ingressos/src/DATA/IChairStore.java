/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import BUSINESS.Chair;

/**
 *
 * @author LucasSMC
 */
public interface IChairStore {
	abstract public HashMap<String, List<Chair>> loadPresentationArea(String FILENAME) throws IOException;

    abstract public void savePresentationArea(HashMap<String, List<Chair>> area);
    
    abstract public double getTypeValue(Chair chair);
}
