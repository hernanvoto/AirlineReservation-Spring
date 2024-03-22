package com.airconsole.reservation.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.airconsole.reservation.model.Seat;

@Repository
@Scope("singleton")
public class SeatMapHandler {

    private static final String planeSeatReservationMapFilename = "planeSeatReservation.map";

    public Seat[][] loadSeatMap() {

        if (FileExistenceChecker.fileExists(planeSeatReservationMapFilename)) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(planeSeatReservationMapFilename))) {

                return (Seat[][]) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {

                System.err.println("Error loading seat map: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    public Seat[][] initialiseSeatMap(final int planeRows, final char[] planeColumns) {

        Seat[][] planeSeatMap = new Seat[planeRows][planeColumns.length];

        // Initialize all elements of the planeSeatMap
        for (int row = 0; row < planeRows; row++) {

            for (int col = 0; col < planeColumns.length; col++) {

                planeSeatMap[row][col] = new Seat(row + 1, (char) (planeColumns[0] + col));
            }
        }
        // create empty seat map
        storeSeatMap(planeSeatMap);

        return planeSeatMap;
    }

//    public Seat[][] loadSeatMap(final int planeRows, final int planeCol) {
//
//        Seat[][] planeSeatMap = new Seat[planeRows][planeCol];
//
//        if (!FileExistenceChecker.check(planeSeatReservationMapFilename)) {
//
//            // Initialize all elements of the planeSeatMap to Seat.EMPTY_SEAT_ID
//            for (int row = 0; row < planeRows; row++) {
//
//                for (int col = 0; col < planeCol; col++) {
//
//                    planeSeatMap[row][col] = new Seat(row + 1, (char) (Airplane.PLANE_COLUMNS[0] + col));
//                }
//            }
//            // create empty seat map
//            storeSeatMap(planeSeatMap);
//
//        } else { // load existing seats and bookings
//
//            try (BufferedReader reader = new BufferedReader(new FileReader(planeSeatReservationMapFilename))) {
//
//                // Read lines from the file and parse matrix elements
//                String line;
//                int row = 0;
//
//                while ((line = reader.readLine()) != null) {
//
//                    String[] elements = line.trim().split(COMMON_FILE_DELIMITER);
//
//                    for (int col = 0; col < elements.length; col++) {
//
//                        Seat currentSeat = new Seat(row, Airplane.PLANE_COLUMNS[col]);
//
//                        if (elements[col].charAt(0) == Seat.OCCUPIED_SEAT_ID) {
//
//                            currentSeat.book();
//                        }
//
//                        planeSeatMap[row][col] = currentSeat;
//                    }
//                    row++;
//                }
//
//            } catch (IOException | NumberFormatException e) {
//
//                System.out.println("Error loading planeSeatReservation file: " + e.getMessage());
//                System.exit(1);
//            }
//        }
//
//        return planeSeatMap;
//    }

    public void storeSeatMap(final Seat[][] planeSeatMap) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(planeSeatReservationMapFilename))) {

            oos.writeObject(planeSeatMap);
            System.out.println("Seat map stored successfully.");
        } catch (IOException e) {

            System.err.println("Error storing seat map: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public void storeSeatMap(final Seat[][] planeSeatMap) {
//
//        // Write the planeSeatReservationMap to a text file
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(planeSeatReservationMapFilename))) {
//
//            for (int row = 0; row < planeSeatMap.length; row++) {
//
//                for (int col = 0; col < planeSeatMap[row].length; col++) {
//
//                    Seat seat = planeSeatMap[row][col]; 
//                    if (col == planeSeatMap[row].length - 1)
//
//                        writer.write((seat.isBooked() ? Seat.OCCUPIED_SEAT_ID : Seat.EMPTY_SEAT_ID));
//
//                    else
//                        writer.write(
//                                (seat.isBooked() ? Seat.OCCUPIED_SEAT_ID : Seat.EMPTY_SEAT_ID) + COMMON_FILE_DELIMITER);
//                }
//                writer.newLine();
//            }
//
//        } catch (IOException e) {
//
//            System.err.println("Error storing data to file: " + e.getMessage());
//            System.exit(1);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private static Map<String, Passenger> loadPassangerSeatBooking() {
//
//        Map<String, Passenger> passangerSeatReservationMap = new HashMap<String, Passenger>();
//
//        if (FileExistenceChecker.check(passangerSeatReservationMapFilename)) {
//
//            try (ObjectInputStream inputStream = new ObjectInputStream(
//                    new FileInputStream(passangerSeatReservationMapFilename))) {
//
//                passangerSeatReservationMap = (Map<String, Passenger>) inputStream.readObject();
//
//            } catch (IOException | ClassNotFoundException e) {
//
//                System.err.println("Error loading data from file: " + e.getMessage());
//            }
//        } else {
//
//            System.err.println("Error loading data from file: " + passangerSeatReservationMapFilename);
//            System.exit(1);
//        }
//
//        return passangerSeatReservationMap;
//    }
}