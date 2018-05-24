package com.example.asus.admin;


public class Address
{
    String plotno;
    String street;
    String locality;
    String district;
    Address(String plotno, String street, String locality, String district)
    {
        this.plotno=plotno;
        this.street=street;
        this.locality=locality;
        this.district=district;
    }

    @Override
    public String toString()
    {

        return(plotno + " "+street+" "+locality+" "+district);
    }
}
