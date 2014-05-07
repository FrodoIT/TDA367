/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import scratch.controller.PlayerController;

/**
 *
 * @author Ivar
 */
public interface ScratchNetwork {
    public void update();
    public void setPlayerController(PlayerController controller);
}
