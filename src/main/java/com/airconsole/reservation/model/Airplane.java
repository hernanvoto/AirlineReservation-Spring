package com.airconsole.reservation.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.airconsole.reservation.controller.FileExistenceChecker;

@Component
public class Airplane {

	private char[][] planeSeatMap;

	public enum PlaneClass {
		BUSINESS_CLASS, ECONOMY_CLASS,
	}

	public static final int TOTAL_PLANE_ROWS = 8;
	public static final int TOTAL_PLANE_COL = 5;

	public static final int BUSINESS_CLASS_FIRST_PLANE_ROW = 1;
	public static final int BUSINESS_CLASS_LAST_PLANE_ROW = 5;
	public static final int ECONOMY_CLASS_FIRST_PLANE_ROW = 6;

	private final String[] _planeColumns = { "A", "B", "C", "D", "E" };

	public Airplane() {
		_loadPlaneSeatMap();
	}

	public void printReservationMap(final PlaneClass selectedClass) {

		if (planeSeatMap == null || planeSeatMap.length == 0) {
			System.err.println("Error printing matrix. There is no Airplane information");
			System.exit(1);
		}

		// print plan columns
		for (String planeColumn : _planeColumns) {
			System.out.print("\t" + planeColumn);
		}

		int startingPlaneRowAccordingToClass = (selectedClass == PlaneClass.BUSINESS_CLASS)
				? BUSINESS_CLASS_FIRST_PLANE_ROW
				: ECONOMY_CLASS_FIRST_PLANE_ROW;

		// Loop through each row
		for (int row = startingPlaneRowAccordingToClass - 1; row < TOTAL_PLANE_ROWS; row++) {

			// Determine the last row based on the selected class
			int lastRow = (selectedClass == PlaneClass.BUSINESS_CLASS) ? BUSINESS_CLASS_LAST_PLANE_ROW
					: TOTAL_PLANE_ROWS;

			// Print Row Numbers
			System.out.print(row + 1);

			for (int col = 0; col < lastRow; col++) {
				// Print the current element followed by a tab
				System.out.print("\t" + planeSeatMap[row][col]);
			}

			// Move to the next line after printing all columns in the row
			System.out.println();
		}

	}

	private static final String COMMON_FILE_DELIMITER = ",";

	private char[][] _loadPlaneSeatMap() {
		planeSeatMap = new char[TOTAL_PLANE_ROWS][TOTAL_PLANE_COL];

		if (!FileExistenceChecker.check(planeSeatReservationMapFilename)) {
			for (int row = 0; row < TOTAL_PLANE_ROWS; row++) {
				for (int seat = 0; seat < TOTAL_PLANE_COL; seat++) {
					planeSeatMap[row][seat] = Seat.EMPTY_SEAT_ID;
				}
			}
		} else { // load existing reservation

			try (BufferedReader reader = new BufferedReader(new FileReader(planeSeatReservationMapFilename))) {

				// Read lines from the file and parse matrix elements
				String line;
				int row = 0;

				while ((line = reader.readLine()) != null) {
					String[] elements = line.trim().split(COMMON_FILE_DELIMITER);

					for (int col = 0; col < elements.length; col++) {
						planeSeatMap[row][col] = elements[col].charAt(0);
					}
					row++;
				}
			} catch (IOException | NumberFormatException e) {
				System.out.println("Error loading planeSeatReservation file: " + e.getMessage());
				System.exit(1);
			}
		}

		return planeSeatMap;
	}

}
