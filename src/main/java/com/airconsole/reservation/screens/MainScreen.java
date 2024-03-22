package com.airconsole.reservation.screens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.airconsole.reservation.components.Menu;
import com.airconsole.reservation.components.MenuItem;

@Service
@Scope("singleton")
public class MainScreen extends Screen {

    public static final Character SEAT_CLASS_SELECTION_ID = 'R';
    public static final Character SEAT_VERIFICATION_SELECTION_ID = 'S';
    public static final Character EXIT_SELECTION_ID = 'X';

    @Autowired
    private final SeatClassSelectionScreen _seatClassSelectionScreen;
    @Autowired
    private final SeatVerificationScreen _seatVerificationScreen;

    public MainScreen(final SeatClassSelectionScreen seatClassSelectionScreen,
            final SeatVerificationScreen seatVerificationScreen) {

        _seatClassSelectionScreen = seatClassSelectionScreen;
        _seatVerificationScreen = seatVerificationScreen;

        setTitle("Airconsole");

        Menu menu = new Menu();

        menu.setTitle("Task Selection");
        menu.setPrompt("Please enter the task you want to perform: ");

        menu.addMenuItem(new MenuItem(SEAT_CLASS_SELECTION_ID, "Reservation", _seatClassSelectionScreen::execute));
        menu.addMenuItem(
                new MenuItem(SEAT_VERIFICATION_SELECTION_ID, "Seat Verification", _seatVerificationScreen::execute));
        menu.addMenuItem(new MenuItem(EXIT_SELECTION_ID, "Exit the System", () -> {

            System.exit(0);
        }));

        setMenu(menu);
    }

    @Override
    public void execute() {

        drawHeader();

        if (menu != null)
            menu.execute();
        else
            System.err.println("MainScreen menu is NULL!");
    }

    @Override
    protected void drawHeader() {

        System.out.println("\r\n*******************************");
        System.out.println("**    Welcome to " + getTitle() + "  **");
        System.out.println("*******************************");
    }
}
