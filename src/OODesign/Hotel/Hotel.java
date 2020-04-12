package OODesign.Hotel;

import OODesign.Hotel.exception.NoEnoughRoomException;

import java.util.*;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private Map<Room, List<Reservation>> roomReservation;

    public Hotel(int roomNumber){
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        roomReservation = new HashMap<>();
        for (int i = 1 ; i <= roomNumber/2 ; i ++){
            rooms.add(new Room(RoomType.SINGLE, i));
        }
        for (int i = roomNumber/2 + 1; i <= roomNumber ; i ++){
            rooms.add(new Room(RoomType.DOUBLE, i));
        }
    }

    public Map<RoomType, Integer> handleSearchRequest(Request r){
        Map<RoomType, Integer> res = new HashMap<>();
        int singleRoomNum = 0;
        int doubleRoomNum = 0;
        for (Room room : rooms){
            if(isRequestAvailable(room, r)) {
                if (room.getRoomType().equals(RoomType.SINGLE))
                    singleRoomNum ++;
                else if (room.getRoomType().equals(RoomType.DOUBLE))
                    doubleRoomNum ++;
            }
        }
        res.put(RoomType.SINGLE, singleRoomNum);
        res.put(RoomType.DOUBLE, doubleRoomNum);
        return res;
    }

    private boolean isRequestAvailable(Room room, Request request){
        for (Reservation reservation : roomReservation.get(room)){
            if ((request.getStartDate().after(reservation.getStartDate()) && request.getStartDate().before(request.getEndDate())) ||
                    (request.getEndDate().after(reservation.getStartDate()) && request.getEndDate().before(reservation.getEndDate())))
                return false;
        }
        return true;
    }

    public Reservation makeReservation(ReservationRequest reservationRequest) throws Exception{
        Map<RoomType, Integer> need = reservationRequest.getRoomNeeded();
        int singleNeed = need.get(RoomType.SINGLE);
        int doubleNeed = need.get(RoomType.DOUBLE);
        int index = 0;
        List<Room> roomList = new ArrayList<>();
        while (singleNeed != 0 && index < rooms.size()){
            Room room = rooms.get(index);
            if (room.getRoomType().equals(RoomType.SINGLE) && isRequestAvailable(room, new Request(reservationRequest.getStartDate(), reservationRequest.getEndDate()))){
                singleNeed --;
                roomList.add(room);
            }
        }
        if (singleNeed != 0)
            throw new NoEnoughRoomException();
        index = 0;
        while (doubleNeed != 0 && index < rooms.size()){
            Room room = rooms.get(index);
            if (room.getRoomType().equals(RoomType.DOUBLE) && isRequestAvailable(room, new Request(reservationRequest.getStartDate(), reservationRequest.getEndDate()))){
                doubleNeed --;
                roomList.add(room);
            }
        }
        if (doubleNeed != 0)
            throw new NoEnoughRoomException();

        Reservation reservation = new Reservation(roomList, reservationRequest.getStartDate(), reservationRequest.getEndDate());
        reservations.add(reservation);
        for (Room room : roomList){
            List<Reservation> list = roomReservation.getOrDefault(room, new ArrayList<>());
            list.add(reservation);
            roomReservation.put(room, list);
        }
        return reservation;
    }

    public void cancelReservation(Reservation reservation){
        reservations.remove(reservation);
        for (Room room : reservation.getRooms()){
            List<Reservation> list = roomReservation.get(room);
            list.remove(reservation);
            roomReservation.put(room, list);
        }
    }
}
