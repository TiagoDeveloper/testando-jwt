package com.tiagodeveloper.springbootwithjwtsecurity.paraApagar;

import com.tiagodeveloper.springbootwithjwtsecurity.utils.Person;
import com.tiagodeveloper.springbootwithjwtsecurity.utils.SingletonClasses;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    private static PersonService service = new PersonServiceImpl();

    public static void main(String[] args) {

//        SingletonClasses.setPerson(
//                new Person() {
//                    @Override
//                    public String getId() {
//                        return "Mude";
//                    }
//
//                    @Override
//                    public String getName() {
//                        return "Pollyana";
//                    }
//
//                    @Override
//                    public int getAge() {
//                        return 26;
//                    }
//                }
//        );
//        var person = service.getPerson();
//        System.out.println(person);
        var year = 2024;
        var now = LocalDate.of(year, 1, 1);
        var weekend = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        List<LocalDate> holiday = List.of(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 25));
        System.out.println(Year.of(now.getYear()).length());

        var workDays = Stream.iterate(now, e -> e.getYear() == year, e -> e.plusDays(1L))
                .filter(e -> !holiday.contains(e) && !weekend.contains(e.getDayOfWeek()))
                .toList();
        System.out.println(workDays.getFirst());
        System.out.println(workDays.getLast());
        System.out.println(workDays.size());
    }
}
