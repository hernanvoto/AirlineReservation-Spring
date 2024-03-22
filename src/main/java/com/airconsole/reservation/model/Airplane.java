package com.airconsole.reservation.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airconsole.reservation.data.SeatMapHandler;

@Component
public class Airplane {

    private Seat[][] _planeSeatMap;

//	private Set<String> occupiedSeats; ccupiedSeats = new HashSet<>();
    @Autowired
    private SeatMapHandler _seatMapHandler;

    public enum PlaneClass {
        BUSINESS_CLASS, ECONOMY_CLASS,
    }

    public static final int TOTAL_PLANE_ROWS = 8;
    public static final int TOTAL_PLANE_COL = 5;

    public static final int BUSINESS_CLASS_FIRST_PLANE_ROW = 1;
    public static final int BUSINESS_CLASS_LAST_PLANE_ROW = 5;
    public static final int ECONOMY_CLASS_FIRST_PLANE_ROW = 6;

    public static final char[] PLANE_COLUMNS = {
            'A', 'B', 'C', 'D', 'E' };

    public Airplane(SeatMapHandler seatMapHandler) {

        _seatMapHandler = seatMapHandler;
        _planeSeatMap = _seatMapHandler.loadSeatMap();

        if (_planeSeatMap == null)
            _planeSeatMap = seatMapHandler.initialiseSeatMap(TOTAL_PLANE_ROWS, PLANE_COLUMNS);
    }

    public boolean isSeatAvailable(Seat selectedSeat) {

        return !_planeSeatMap[selectedSeat.getRow() - 1][selectedSeat.getColumn() - PLANE_COLUMNS[0]].isBooked();
    }

    public void bookSeat(Seat selectedSeat) {

        _planeSeatMap[selectedSeat.getRow() - 1][selectedSeat.getColumn() - PLANE_COLUMNS[0]] = selectedSeat;

        _seatMapHandler.storeSeatMap(_planeSeatMap);
    }

    public Seat getSeat(int row, char column) {

        return (_planeSeatMap[row - 1][column - PLANE_COLUMNS[0]]);
    }

    public void displayPlaneSeatMap(final PlaneClass selectedClass) {

        // print plan columns
        for (char planeColumn : PLANE_COLUMNS) {

            System.out.print("\t" + planeColumn);
        }
        System.out.println("");

        int startingPlaneRowAccordingToClass = (selectedClass == PlaneClass.BUSINESS_CLASS)
                ? BUSINESS_CLASS_FIRST_PLANE_ROW
                : ECONOMY_CLASS_FIRST_PLANE_ROW;

        // Determine the last row based on the selected class
        int lastRow = (selectedClass == PlaneClass.BUSINESS_CLASS) ? BUSINESS_CLASS_LAST_PLANE_ROW : TOTAL_PLANE_ROWS;

        // Loop through each row
        for (int row = startingPlaneRowAccordingToClass - 1; row < lastRow; row++) {

            // Print Row Numbers
            System.out.print(row + 1);

            for (int col = 0; col < PLANE_COLUMNS.length; col++) {

                // Print the current element followed by a tab
                System.out.print(
                        "\t" + (_planeSeatMap[row][col].isBooked() ? Seat.OCCUPIED_SEAT_ID : Seat.EMPTY_SEAT_ID));
            }
            // Move to the next line after printing all columns in the row
            System.out.println("");
        }
    }
}
