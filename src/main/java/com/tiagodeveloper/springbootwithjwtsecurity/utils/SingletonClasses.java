package com.tiagodeveloper.springbootwithjwtsecurity.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class SingletonClasses {

    private static Person person;
    private SingletonClasses() { }
    @Getter
    @ToString
    @EqualsAndHashCode
    private static class PersonImpl implements Person {
        private final String id;
        private final String name;
        private final int age;
        private final LocalDateTime createdAt;

        public PersonImpl(String id, String name, int age, LocalDateTime createdAt) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.createdAt = createdAt;
        }
    }

    public static Person getPerson() {
        if(Objects.isNull(person))
            return new PersonImpl(UUID.randomUUID().toString(), "Tiago", 35, LocalDateTime.now());
        return person;
    }
    public static void setPerson(Person person) {
        SingletonClasses.person = person;
    }
}
