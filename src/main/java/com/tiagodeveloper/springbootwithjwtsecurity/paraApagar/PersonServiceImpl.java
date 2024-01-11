package com.tiagodeveloper.springbootwithjwtsecurity.paraApagar;

import com.tiagodeveloper.springbootwithjwtsecurity.utils.Person;
import com.tiagodeveloper.springbootwithjwtsecurity.utils.SingletonClasses;

public class PersonServiceImpl implements PersonService{
    @Override
    public Person getPerson() {
        return SingletonClasses.getPerson();
    }
}
