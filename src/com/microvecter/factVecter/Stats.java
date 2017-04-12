/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microvecter.factVecter;

import com.codename1.ui.Dialog;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;

/**
 *
 * @author sizwe.nxele
 */
public class Stats {
    private Date myAge;
    private String name,formatedMyAge,birthdayMessage,mainData,currentDateTime,gender;
    private int yearMy,monthMy,dayMy,idNumber,yearCurrent,monthCurrent,daycurrent,myCurrentAge,myCurrentMonth,myCurrentDay,daysLeft,monthsLeft;
   
    private final String empty = "";
    private final double days = 365;;
    private final int months = 12;
    private final double weeks = 52;
    private final double hours = 8765;
    private final double minutes = 525948;
    private final double seconds = 31556926;
    
    public void validating(String name,String idNumber,Date myAge)
    {
        this.name=name;
        this.myAge=myAge;
        this.idNumber=Integer.parseInt(idNumber);
        
        if(name.equals(empty) || idNumber.equals(empty))
        {
            Dialog.show("!! Wanrning ","Please Enter your name and your 5 last ID numbers","OK!!",null);
        } 
        else
        {
//            if(Integer.parseInt(idNumber)){
                getGender(this.idNumber);
                dataConvector();
//            }
//            else{Dialog.show("! Warning","Your ID Number should be numbers","OK",null);}
        }
    }
   
    public void dataConvector()
    {
        DateFormat formatDate=new SimpleDateFormat("yyyy/MM/dd");
        
        //converting the current date to String
        formatedMyAge=""+formatDate.format(myAge);
        
        int firstIndexMyAge=formatedMyAge.indexOf('/');
        int lastIndexMyAge=formatedMyAge.lastIndexOf('/');
        
        // substring my date of birth
        String myYear=formatedMyAge.substring(0,firstIndexMyAge);
        String myMoth=formatedMyAge.substring(firstIndexMyAge+1,lastIndexMyAge);
        String myDay=formatedMyAge.substring(lastIndexMyAge+1);
        
        //getting the current date
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=new Date();
        
        //converting the current date to String
        currentDateTime=""+dateFormat.format(date);
       
        //getting index of the currentDate
        int firtsIndexOfCurrent=currentDateTime.indexOf('/');
        int lastIndexOfCurrent=currentDateTime.lastIndexOf('/');
        int spaceIndex=currentDateTime.indexOf(' ');
        
        //substring the current Date
        String currentYear=currentDateTime.substring(0,firtsIndexOfCurrent);
        String currentMonth=currentDateTime.substring(firtsIndexOfCurrent+1,lastIndexOfCurrent);
        String currentDay=currentDateTime.substring(lastIndexOfCurrent+1,spaceIndex);
        currentDateTime=currentDateTime.substring(spaceIndex);
        
        //converting my date of birth to integer
        yearMy=Integer.parseInt(myYear);
        monthMy=Integer.parseInt(myMoth);
        dayMy=Integer.parseInt(myDay);
        
        //converting the current date to integer
        yearCurrent=Integer.parseInt(currentYear);
        monthCurrent=Integer.parseInt(currentMonth);
        daycurrent=Integer.parseInt(currentDay);
        
        calculateStats();
    }
    public String getGender(int idnumber)
    {
        if(idnumber >= 10000 && idnumber <= 50000){
            gender = "Male";
        }
        if(idnumber >50000 && idnumber <=99999)
        {
            gender = "Female";
        }
        
        return this.gender;
    }
   
    public void calculateStats()
    {
        if(monthMy == monthCurrent && dayMy == daycurrent)
        {
            myCurrentAge=yearCurrent-yearMy;
            birthdayMessage="Happy Birthay";
            
            mainData=birthdayMessage+" "+name+" You are now :"+myCurrentAge+" Years and "+currentDateTime+" vecter says your a :"+gender;
        }
        if(monthMy>monthCurrent)
        {
          myCurrentAge=(yearCurrent-yearMy)-1;
          myCurrentMonth=(months-monthMy)+monthCurrent;
          myCurrentDay=daycurrent;
          birthdayMessage="Your Birthday is still coming";
          
          mainData=birthdayMessage+" "+name+" You are :"+myCurrentAge+" Years "+myCurrentMonth+" Months "+myCurrentDay+" Days and "+currentDateTime+" vecter says your a :"+gender;
        }
        //if(monthMy<=monthCurrent && dayMy != daycurrent)
        if(monthMy<=monthCurrent)
        {
            if(monthMy<=monthCurrent)
            {
               birthdayMessage="Your next birthday is next year";
               myCurrentAge=yearCurrent-yearMy;
               myCurrentDay=daycurrent;
                  
                if(monthMy<monthCurrent)
                {
                    myCurrentMonth=monthCurrent-monthMy;
                    mainData=birthdayMessage+" "+name+" You are :"+myCurrentAge+" Years "+myCurrentMonth+" Months "+myCurrentDay+" Days and "+currentDateTime+" vecter says your a :"+gender;
                }
                if(monthMy==monthCurrent && dayMy<daycurrent)
                {
                    mainData=birthdayMessage+" "+name+" You are :"+myCurrentAge+" Years "+" "+myCurrentDay+" Days and "+currentDateTime+" vecter says your a :"+gender;
                }
                
            }
            if(monthMy==monthCurrent && dayMy>daycurrent)
            {
                myCurrentAge=(yearCurrent-yearMy)-1;
                myCurrentMonth=(months-monthMy)+monthCurrent;
                daysLeft=dayMy-daycurrent;
                birthdayMessage="Happy Birthday in advance";
                
                mainData=birthdayMessage+" "+name+" You are :"+myCurrentAge+" Years "+myCurrentMonth+" Months "+" and "+currentDateTime+" but you have "+daysLeft+" days left until it's your Birthday "+" vecter says your a :"+gender;
            }
        }
        display();
    }
    public void display()
    {
        Dialog.show("Vecter AI",mainData,"OK",null);
        
        myAge=null;
        name=empty; formatedMyAge=empty; birthdayMessage=empty; mainData=empty; currentDateTime=empty; gender=empty;
        yearMy=0;monthMy=0;dayMy=0;yearCurrent=0;monthCurrent=0;daycurrent=0;myCurrentAge=0;myCurrentMonth=0;myCurrentDay=0;daysLeft=0;monthsLeft=0;idNumber=0;
    }
}
