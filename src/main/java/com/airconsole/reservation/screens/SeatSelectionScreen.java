package com.airconsole.reservation.screens;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.airconsole.reservation.model.Airplane;
import com.airconsole.reservation.model.Passenger;
import com.airconsole.reservation.model.Seat;

public abstract class SeatSelectionScreen extends Screen {

    private Scanner _scanner = new Scanner(System.in);

    @Autowired
    protected final Airplane _airplane;

    public SeatSelectionScreen(final Airplane airplane, final String title) {

        _airplane = airplane;
        setTitle(title);
    }

    protected Seat getSeatSelection(final int firstRow, final int lastRow) {

        Integer planeRow = null;
        Character planeColumn = null;
        boolean validUserSelection = false;

        /** ROW */
        while (!validUserSelection) {

            System.out.print("\nPlease enter the row number: ");

            String userInput = _scanner.nextLine();

            // Check if userInputChar is a digit
            if (userInput.matches("\\d+")) {

                planeRow = Integer.parseInt(userInput);

                if (planeRow >= firstRow && planeRow <= lastRow) {

                    validUserSelection = true;
                } else {

                    System.out.println("Invalid Class Entry!");
                }
            } else {

                System.out.println("Invalid Class Entry!");
            }
        }

        validUserSelection = false;

        /** Column */
        while (!validUserSelection) {

            System.out.print("Please enter the seat letter: ");
            String colInput = _scanner.nextLine();

            if (colInput.matches("[a-eA-E]")) {

                planeColumn = Character.toUpperCase(colInput.charAt(0));
                validUserSelection = true;

            } else {

                System.out.println("Invalid Entry! valid range (A-E)");
            }
        }

        return new Seat(planeRow, planeColumn);
    }

    protected Passenger getPassangerInfo() {

        /** get and save Passenger */
        Passenger passenger = new Passenger();

        boolean validUserSelection = false;

        while (!validUserSelection) {

            System.out.print("\nPlease enter the passenger's firstname:");
            String userInput = _scanner.nextLine();

            // Check if userInputChar is not empty
            if (!userInput.isEmpty()) {

                passenger.setName(userInput);
                validUserSelection = true;
            } else {

                System.out.println("Invalid Entry! Please enter a firstname ");
            }
        }

        validUserSelection = false;

        while (!validUserSelection) {

            System.out.print("\nPlease enter the passenger's lastname:");
            String userInput = _scanner.nextLine();

            // Check if userInputChar is not empty
            if (!userInput.isEmpty()) {

                passenger.setSurname(userInput);
                validUserSelection = true;
            } else {

                System.out.println("Invalid Entry! Please enter a lastname");
            }
        }

        validUserSelection = false;

        while (!validUserSelection) {

            System.out.print("\nPlease enter the passenger's passport number:");
            String userInput = _scanner.nextLine();

            // Check if userInputChar is not empty
            if (!userInput.isEmpty()) {

                passenger.setPassportNumber(userInput);
                validUserSelection = true;
            } else {

                System.out.println("Invalid Entry! Please enter a passport number");
            }
        }

        return passenger;
    }
}
