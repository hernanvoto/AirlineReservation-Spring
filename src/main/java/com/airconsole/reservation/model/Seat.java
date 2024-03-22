package com.airconsole.reservation.model;

import java.io.Serializable;

public class Seat implements Serializable {

    public static final char EMPTY_SEAT_ID = '-';
    public static final char OCCUPIED_SEAT_ID = 'X';

    private static final long serialVersionUID = 6287922474113391944L;

    private int _row;
    private char _column;
    private Passenger _passenger;
    private boolean _booked;

    public Seat(int seatRow, char seatColumn) {

        _row = seatRow;
        _column = seatColumn;
        _booked = false;
    }

    public int getRow() {

        return _row;
    }

    public void setRow(int row) {

        this._row = row;
    }

    public char getColumn() {

        return _column;
    }

    public void setColumn(char column) {

        this._column = column;
    }

    public void book(Passenger passenger) {

        this._booked = true;
        setPassenger(passenger);
    }

    public void unbook() {

        this._booked = false;
    }

    public boolean isBooked() {

        return _booked;
    }

    public Passenger getPassenger() {

        return _passenger;
    }

    public void setPassenger(Passenger _passenger) {

        this._passenger = _passenger;
    }
}
