package com.airconsole.reservation.screens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.airconsole.reservation.components.Menu;
import com.airconsole.reservation.components.MenuItem;

@Service
@Scope("singleton")
public class SeatClassSelectionScreen extends Screen {

    public static final Character BUSINESS_CLASS_SELECTION_ID = 'B';
    public static final Character ECONOMY_CLASS_SELECTION_ID = 'E';
    public static final Character EXIT_SELECTION_ID = 'X';

    @Autowired
    private final EconomySeatSelectionScreen _economySeatSelectionScreen;
    @Autowired
    private final BusinessSeatSelectionScreen _businessSeatSelectionScreen;

    public SeatClassSelectionScreen(final EconomySeatSelectionScreen economySeatSelectionScreen,
            final BusinessSeatSelectionScreen businessSeatSelectionScreen) {

        _businessSeatSelectionScreen = businessSeatSelectionScreen;
        _economySeatSelectionScreen = economySeatSelectionScreen;

        setTitle("Seat Class");

        Menu menu = new Menu();

        menu.setTitle("Seat Class Selection:");
        menu.setPrompt("Please enter the seat class you want to reserve: ");

        menu.addMenuItem(
                new MenuItem(BUSINESS_CLASS_SELECTION_ID, "Business Class", _businessSeatSelectionScreen::execute));
        menu.addMenuItem(
                new MenuItem(ECONOMY_CLASS_SELECTION_ID, "Economy Class", _economySeatSelectionScreen::execute));
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
            System.err.println("SeatClassSelectionScreen menu is NULL");
    }

    @Override
    protected void drawHeader() {

        System.out.println("*******************************");
        System.out.println("********  " + getTitle() + "  *********");
        System.out.println("*******************************");
    }

}
