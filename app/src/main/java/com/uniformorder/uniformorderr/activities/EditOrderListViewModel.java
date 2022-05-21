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

    public  String order_id1 ="",login_id1="",patternId1="",rate11="",rate21="",total_amount1=""
            ,deposite1="",school_Id1="";
  //  String[] boys,standard,girls;

   public void getData(){

    }
//   public void saveData(String order_idl,String login_id,
//                  String school_id,String patternId,
//                  String rate1,String rate2,
//                  String total_amount,String deposite){
//
////        order_id, login_id, school_id,
////        pattern_id, rate1, rate2, total_amount,
////        deposite, standards[], boys[], girls[]
//        HashMap editOrder = new HashMap<String,String>();
//           this.order_id = order_id;
//           this.login_id =login_id;
//           this.patternId = patternId;
//           this.rate1 = rate1;
//           this.rate2 = rate2;
//           this.total_amount = total_amount;
//           this.school_Id = school_id;
//           this.deposite = deposite;
////           this.standard = standard;
////           this.boys = boys;
////           this.girls = girls;
////         //  editOrder.put("order_Id",order_id);
//    }
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
                         String rate1, String rate2, String total_amount,
                         String deposite) {

         this.order_id1 = order_id; //44
         this.school_Id1 = school_id; //22
         this.login_id1 = login_id; //22
         this.patternId1 = pattern_id; //11
         this.rate11 = rate1; //12.00
                // 12.00
         this.rate21 = rate2;
         this.total_amount1 = total_amount; //24.00
         this.deposite1 = deposite; //2.00

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

//for (int i = 0; i < Constants.editcardList.size(); i++) {
//             std_l.add(Constants.editcardList.get(i).getStandard());
//             boys_l.add(Constants.editcardList.get(i).getBoys());
//             girls_l.add(Constants.editcardList.get(i).getGirls());
//             Log.d("Girls ->>>VM", String.valueOf(girls_l.get(i)));
//
//             if (Constants.editcardList.get(i).getStandard() <= 4) {
//
//                 int boysGrildTotal = Constants.editcardList.get(i).getBoys() + Constants.editcardList.get(i).getGirls();
//                 total1to4 = total1to4 + boysGrildTotal;
//
//                 Log.d("totall14", String.valueOf(total1to4));
//             } else {
//
//                 int boysGrildTotal = Constants.editcardList.get(i).getBoys() + Constants.editcardList.get(i).getGirls();
//                 total5to8 = total5to8 + boysGrildTotal;
//
//                 Log.d("totall58", String.valueOf(total5to8));
//             }
//
//
//             Log.d("totall14", String.valueOf(total1to4));
//             Log.d("totall58", String.valueOf(total5to8));
//
//         }