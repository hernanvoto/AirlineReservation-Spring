package com.airconsole.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.airconsole.reservation.screens.MainScreen;

@Service
@Scope("singleton")
public class ReservationService implements CommandLineRunner {

	@Autowired
	private MainScreen _mainScreen;

	@Override
	public void run(String... args) throws Exception {
		// Logic to be executed during application startup
		_mainScreen.execute();
	}

	public ReservationService(MainScreen mainScreen) {
		this._mainScreen = mainScreen;
	}
}
