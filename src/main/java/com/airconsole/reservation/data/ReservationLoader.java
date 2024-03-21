package com.airconsole.reservation.data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class ReservationLoader {
	private static final String planeSeatReservationMapFilename = "planeSeatReservation.map";
	private static final String passangerSeatReservationMapFilename = "passangerSeatReservation.map";

//	private static final String COMMON_FILE_DELIMITER = ",";
//
//		public static char[][] loadPlaneSeatReservations() {
//			char[][] planeSeatReservationMap = new char[TOTAL_PLANE_ROWS][TOTAL_PLANE_COL];
//			
//			if (!FileExistenceChecker.check(planeSeatReservationMapFilename)) {
//			    for (int row = 0; row < TOTAL_PLANE_ROWS; row++) {
//			        for (int seat = 0; seat < TOTAL_PLANE_COL; seat++) {
//			        	planeSeatReservationMap[row][seat] = Seat.EMPTY_SEAT_ID;  
//			        }
//			    }
//			} else { //load existing reservation
//
//				try (BufferedReader reader = new BufferedReader(new FileReader(planeSeatReservationMapFilename))) {
//					 
//		            // Read lines from the file and parse matrix elements
//		            String line;
//		            int row = 0;
//		             
//		            while ((line = reader.readLine()) != null) {
//		            	String[] elements = line.trim().split(COMMON_FILE_DELIMITER);
//		                
//		                for (int col = 0; col < elements.length; col++) {
//		                	planeSeatReservationMap[row][col] = elements[col].charAt(0);
//		                }
//		                row++;
//		            }
//				} catch (IOException | NumberFormatException e) {
//					System.out.println("Error loading planeSeatReservation file: " + e.getMessage());
//					System.exit(1);
//				}
//			}
//			
//			return planeSeatReservationMap;
//		}
}
