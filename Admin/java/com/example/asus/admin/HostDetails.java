package com.example.asus.admin;

import java.util.ArrayList;

public class HostDetails
{
    String name;
    Address address;
    String rooms_ava;
    String contact;
    String approved;
   String village;
    HostDetails(String name,Address address,String rooms_ava,String contact,String approved,String village)
    {
        this.name = name;
        this.address = address;
        this.rooms_ava = rooms_ava;
        this.contact=contact;
        this.approved=approved;
        this.village=village;
    }

}
