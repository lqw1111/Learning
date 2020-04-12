package OODesign.Restaurant;

import OODesign.Restaurant.exception.NoTableException;
import OODesign.Restaurant.exception.NoTableForReservationException;
import sun.tools.jconsole.Tab;

import java.util.*;

public class Restaurant {
    private List<Table> tables;
    private List<Meal> menu;
    private Map<Table, Order> orders;
    private Map<Table, List<TimeSlot>> reservations;

    public Restaurant(int tableSize, int tableNum, int mealNum){
        tables = new ArrayList<>();
        menu = new ArrayList<>();
        orders = new HashMap<>();
        reservations = new HashMap<>();

        for (int i = 0 ; i < tableNum ; i++){
            tables.add(new Table(tableSize));
        }
        for (int i = 0 ; i < mealNum ; i ++){
            menu.add(new Meal("Meal" + i, 9.99f));
        }
    }

    public Table findTable(Party party) throws Exception{
        for (Table t :
                tables) {
            if (party.getSize() < t.getCapacity() && t.isAvailable()){
                return t;
            }
        }
        throw new NoTableException();
    }

    public void TakeOrder(Order order, Table table){
        table.setAvailable(false);
        orders.put(table, order);
    }

    public float checkout(Table table, Order order){
        float cost = 0.0f;
        for (Meal m : order.getMeals()){
            cost += m.getPrice();
        }
        orders.remove(table, order);
        table.setAvailable(true);
        return cost;
    }

    public Reservation findTableForReservation(Party party, TimeSlot timeSlot) throws Exception{
        for (Table table : tables){
            if (canReservation(table, timeSlot)){

                Reservation reservation = new Reservation();
                reservation.setTable(table);
                reservation.setTimeSlot(timeSlot);

                return reservation;
            }
        }
        throw new NoTableForReservationException();
    }

    private boolean canReservation(Table table, TimeSlot timeSlot){
        List<TimeSlot> reservation = reservations.get(table);
        Collections.sort(reservation, (r1,r2) -> {
            return r1.getStartTime() - r2.getStartTime();
        });
        List<TimeSlot> freeTime = getFreeTime(reservation);
        for (TimeSlot t : freeTime){
            if (t.cover(timeSlot))
                return true;
        }
        return false;
    }

    private List<TimeSlot> getFreeTime(List<TimeSlot> timeSlots){
        List<TimeSlot> res = new ArrayList<>();
        if (timeSlots.size() <= 0)
            return res;
        int maxEnd = 0;
        for (TimeSlot timeSlot : timeSlots){
            if (timeSlot.getStartTime() > maxEnd){
                res.add(new TimeSlot(maxEnd, timeSlot.getStartTime()));
            }
            if (timeSlot.getEndTime() > maxEnd){
                maxEnd = timeSlot.getEndTime();
            }
        }
        if (maxEnd < 24){
            res.add(new TimeSlot(maxEnd, 24));
        }
        return res;
    }

    public void confirmReservation(Reservation reservation){
        reservations.get(reservation.getTable()).add(reservation.getTimeSlot());
    }

    public void cancelReservation(Reservation reservation){
        reservations.get(reservation.getTable()).remove(reservation.getTimeSlot());
    }

}
