package com.example.nathanmorgenstern.contactlistapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by nathanmorgenstern on 10/12/17.
 */

public class Contact {

    private String name;
    private String number;
    private String relationship;
    private int _id;

    public Contact(){}

    public Contact(String name, String number, String relationship){
        this.name = name;
        this.number = number;
        this.relationship = relationship;
    }

    public String getName(){
        return name;
    }

    public String getRelationship(){return relationship;}

    public void setRelationship(String r){
        this.relationship = r;
    }
    public void setName(String name){this.name = name;}

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){this.number = number;}

    public int get_id(){ return _id;}

    public void setID(int _id){this._id = _id;}

}
