package com.airconsole.reservation.screens;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.airconsole.reservation.model.Airplane;
import com.airconsole.reservation.model.Passenger;
import com.airconsole.reservation.model.Seat;

@Service
@Scope("singleton")
public class SeatVerificationScreen extends SeatSelectionScreen {

    public SeatVerificationScreen(final Airplane airplane) {

        super(airplane, "Seat Verification");
    }

    @Override
    public void execute() {

        drawHeader();

        Seat userSelectedSeat = getSeatSelection(1 - 1, Airplane.TOTAL_PLANE_ROWS - 1);

        Seat savedSelectedSeat = _airplane.getSeat(userSelectedSeat.getRow(), userSelectedSeat.getColumn());

        if (savedSelectedSeat.isBooked()) {

            Passenger passenger = savedSelectedSeat.getPassenger();

            System.out.println("\nPassenger details");
            System.out.println("Firstname:" + passenger.getName());
            System.out.println("Lastname:" + passenger.getSurname());
            System.out.println("Passport Number:" + passenger.getPassportNumber());
        } else {

            System.out.println(
                    "\nSorry seat " + userSelectedSeat.getRow() + userSelectedSeat.getColumn() + " is not booked.");
        }
    }

    @Override
    protected void drawHeader() {

        System.out.println("\r\n*******************************");
        System.out.println("*****  " + getTitle() + "  *****");
        System.out.println("*******************************");
    }

}
