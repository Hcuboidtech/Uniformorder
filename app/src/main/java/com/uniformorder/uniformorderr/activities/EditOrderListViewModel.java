package com.uniformorder.uniformorderr.activities;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.uniformorder.uniformorderr.model.SaveorderRequestdetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditOrderListViewModel extends ViewModel {
   public ArrayList<String> data = new ArrayList<>() ;
   public EditOrderListViewModel(){
        Log.d("Inside Edit OrderList","Inside ViewModel");
       edit_saveOrder = new ArrayList<SaveorderRequestdetails>();

   }

    int rate1to4 = 1, rate5to8 = 1, totalrate = 0;
    int total1to4 = 0, total5to8 = 0;

  public   ArrayList<SaveorderRequestdetails> edit_saveOrder = new ArrayList<>();
    int std_l = 0;
    int boys_l = 0;
   int girls_l = 0;

    public  String order_id1 ="",login_id1="",patternId1="",rate11="",rate21="",rate31="",total_amount1=""
            ,deposite1="",school_Id1="";

   public void getData(){

    }
     ArrayList<String> returnData(){

       return data;
     }
   public void savePrcebleObj(ArrayList<SaveorderRequestdetails> editcardList){
           Log.d("SAVED ->","Saved ParcebleObj");

           edit_saveOrder = editcardList;
       if (edit_saveOrder != null) {
           Log.d("SAVED ->","Nt NUll");

           for (int i = 0;  i < edit_saveOrder.size(); i++) {
               std_l =Integer.parseInt(edit_saveOrder.get(i).getStandard().toString());
               boys_l =Integer.parseInt(edit_saveOrder.get(i).getBoys().toString());
               girls_l =Integer.parseInt(edit_saveOrder.get(i).getGirls().toString());

               if (edit_saveOrder.get(i).getStandard() <= 4) {

                   int boysGrildTotal = edit_saveOrder.get(i).getBoys() + edit_saveOrder.get(i).getGirls();
                   total1to4 = total1to4 + boysGrildTotal;

                   Log.d("totall14", String.valueOf(total1to4));
               } else {

                   int boysGrildTotal = edit_saveOrder.get(i).getBoys()
                           + edit_saveOrder.get(i).getGirls();
                   total5to8 = total5to8 + boysGrildTotal;

                   Log.d("totall58", String.valueOf(total5to8));
               }


               Log.d("totall14", String.valueOf(total1to4));
               Log.d("totall58", String.valueOf(total5to8));

           }
       }
     //  return edit_saveOrder;
     }
    public void saveData(String order_id,String school_id,
                         String login_id, String pattern_id,
                         String rate1, String rate2,String rate3, String total_amount,
                         String deposite) {

         this.order_id1 = order_id;
         this.school_Id1 = school_id;
         this.login_id1 = login_id;
         this.patternId1 = pattern_id;
         this.rate11 = rate1;
         this.rate21 = rate2;
         this.rate31 = rate3;
         this.total_amount1 = total_amount;
         this.deposite1 = deposite;
        /////////////////////////////////////////////
         Log.d("order id999",this.order_id1);
        Log.d("SaveData-> orderid",order_id);
        data.add(this.order_id1);
        data.add(this.login_id1);
        data.add(this.school_Id1);
        data.add(this.patternId1);
        data.add(this.rate11);
        data.add(this.rate21);
        data.add(this.total_amount1);
        data.add(this.deposite1);
        for (int i= 0; i<data.size(); i++){
            Log.d("passing list data ",data.get(i));
        }

    Log.d("Saved Data->","Called");

    }
}