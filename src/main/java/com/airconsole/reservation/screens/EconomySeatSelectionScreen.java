package com.airconsole.reservation.screens;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class EconomySeatSelectionScreen extends SeatSelectionScreen {

	public static final Character ECONOMY_CLASS_SELECTION_ID = 'E';

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void drawHeader() {
		// TODO Auto-generated method stub

	}

}
