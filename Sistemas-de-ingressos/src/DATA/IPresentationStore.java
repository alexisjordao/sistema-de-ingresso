/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import BUSINESS.Presentation;

/**
 *
 * @author LucasSMC
 */
public interface IPresentationStore {
    abstract public Presentation loadPresentationList();
    
    abstract public boolean savePresentationList(Presentation p);
}
