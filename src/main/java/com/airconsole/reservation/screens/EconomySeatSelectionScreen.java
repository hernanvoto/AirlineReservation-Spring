package com.airconsole.reservation.screens;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.airconsole.reservation.model.Airplane;
import com.airconsole.reservation.model.Passenger;
import com.airconsole.reservation.model.Seat;

@Service
@Scope("singleton")
public class EconomySeatSelectionScreen extends SeatSelectionScreen {

    public EconomySeatSelectionScreen(final Airplane airplane) {

        super(airplane, "Economy Class");
    }

    @Override
    public void execute() {

        drawHeader();

        _airplane.displayPlaneSeatMap(Airplane.PlaneClass.ECONOMY_CLASS);

        Seat selectedSeat = getSeatSelection(Airplane.ECONOMY_CLASS_FIRST_PLANE_ROW - 1, Airplane.TOTAL_PLANE_ROWS - 1);

        if (_airplane.isSeatAvailable(selectedSeat)) {

            System.out.println("\nSeat " + selectedSeat.getRow() + selectedSeat.getColumn() + " is available.");

            Passenger passenger = getPassangerInfo();
            // save seat
            selectedSeat.book(passenger);
            _airplane.bookSeat(selectedSeat);

            System.out.println(
                    "\nSeat " + selectedSeat.getRow() + selectedSeat.getColumn() + " was successfully booked!");
        } else {

            System.out
                    .println("\nSorry seat " + selectedSeat.getRow() + selectedSeat.getColumn() + " is already taken.");
        }
    }

    @Override
    protected void drawHeader() {

        System.out.println("\r\n*******************************");
        System.out.println("******* " + getTitle() + "  *******");
        System.out.println("*******************************");
    }
}
