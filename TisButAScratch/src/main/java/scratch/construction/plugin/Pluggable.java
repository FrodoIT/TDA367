/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.construction.plugin;

import java.awt.Point;

/**
 *
 * @author Ivar
 */
public interface Pluggable<T> {
    public Point calculateNewPosition(Point playerPosition);
}
