package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerInputTest {
	private final PlayerInput pInput;
	
	public PlayerInputTest(){
		pInput = new PlayerInput();
	}

	@Test
	public void setMoveInputTest(){
		pInput.setMoveInput(MoveDirection.EAST);
		assertTrue(pInput.getMoveInput().equals(MoveDirection.EAST));
		pInput.setMoveInput(MoveDirection.NONE);
	}
	
	@Test
	public void setAttackInputTest(){
		pInput.setAttackInput(true);
		assertTrue(pInput.getAttackInput());
		pInput.setAttackInput(false);
	}
	
	@Test
	public void setInteractInputTest(){
		pInput.setInteractInput(true);
		assertTrue(pInput.getInteractInput());
		pInput.setInteractInput(false);
	}
	
	@Test
	public void getAttackInputTest() {
		pInput.setAttackInput(true);
		assertTrue(pInput.getAttackInput());
	}
	@Test
	public void getMoveInputTest(){
		pInput.setMoveInput(MoveDirection.EAST);
		assertTrue(pInput.getMoveInput().equals(MoveDirection.EAST));
	}
	
	@Test
	public void getInteractInputTest(){
		pInput.setInteractInput(true);
		assertTrue(pInput.getInteractInput());
	}
	
}
