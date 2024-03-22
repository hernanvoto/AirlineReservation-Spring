package com.airconsole.reservation.screens;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.airconsole.reservation.model.Airplane;
import com.airconsole.reservation.model.Passenger;
import com.airconsole.reservation.model.Seat;

@Service
@Scope("singleton")
public class BusinessSeatSelectionScreen extends SeatSelectionScreen {

    public BusinessSeatSelectionScreen(final Airplane airplane) {

        super(airplane, "Business Class");
    }

    @Override
    public void execute() {

        drawHeader();

        _airplane.displayPlaneSeatMap(Airplane.PlaneClass.BUSINESS_CLASS);

        Seat selectedSeat = getSeatSelection(Airplane.BUSINESS_CLASS_FIRST_PLANE_ROW - 1,
                Airplane.BUSINESS_CLASS_LAST_PLANE_ROW - 1);

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
        System.out.println("*******************************\r\n");
    }
}
