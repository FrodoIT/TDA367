/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package construction;

import java.awt.Point;
import model.IMap;

/**
 *
 * @author Ivar
 */
public class SlickMap implements IMap{

    public SlickMap(){
        
    }
    
    public boolean isColliding(Point point) {
        return false;
    }

    public int getHeight() {
        return 512;
    }

    public int getWidth() {
        return 512;
    }
    
}
