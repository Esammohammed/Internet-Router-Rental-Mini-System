


package research;
import java.util.ArrayList;
import java.time.LocalDate;import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * 
 * Internet Router Rental Mini System
 * @author essam mohamed
 */
public class Research {
    Scanner in = new Scanner(System.in);
static ArrayList <String>issue_feedback= new ArrayList<>();

 public static class router {
    private  int serialnum ;
    /**
     * to make serial number  unique , unique serial number  is static when taking object it increment by one
     * and save it in serial number and so on ex the first router serial number 1000 , second 1001 and so on  
     */
    static int uniqeserialnum=1000;
    private double price_per_day;
     /**
      * some setter and getter methods
      *  
      */
        public int getSerialnum() {
            return serialnum;
        }

        public static int getUniqeserialnum() {
            return uniqeserialnum;
        }

        public double getPrice_per_day() {
            return price_per_day;
        }

        public String getModel() {
            return model;
        }

        public int getNum_of_ports() {
            return num_of_ports;
        }

        public void setSerialnum(int serialnum) {
            this.serialnum = serialnum;
        }

        public static void setUniqeserialnum(int uniqeserialnum) {
            router.uniqeserialnum = uniqeserialnum;
        }

        public void setPrice_per_day(double price_per_day) {
            this.price_per_day = price_per_day;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void setNum_of_ports(int num_of_ports) {
            this.num_of_ports = num_of_ports;
        }
    private String model ;
     private int num_of_ports ;
/**
 * 
 * @param model model of router
 * @param num_of_ports number port of router 
 * @param price_per_day router price per day 
 * 
 */
        public router( String model, int num_of_ports,double price_per_day) {
            this.serialnum =  uniqeserialnum;
            uniqeserialnum +=1;
            this.model = model;
            this.num_of_ports = num_of_ports ;
            this.price_per_day=price_per_day;
           
        }
 }
 
 public static class invoice{
     private double renting_fees;
     private int router_ser_num ;
     private int reservation_num;
     /**
      * display information about specific reservation like 
      * renting fees, router serial number and reservation number 
      */
     /**
      *single responsibility principle 
      * this function for display invoice only
      * so i can use it in display all customer information 
      */
        void display_invoice (){
            System.out.println(
               "\ninvoice\nreservation number\t"+reservation_num+"\n"
          
                  + "reservation price\t" +renting_fees +"\n" +
                  "router serial number\t"+router_ser_num);
        }
        /**
         * when create reservation it create it's own invoice 
         */
        public invoice(double renting_fees, int router_ser_num, int reservation_num) {
            this.renting_fees = renting_fees;
            this.router_ser_num = router_ser_num;
            this.reservation_num = reservation_num;
        }

        public invoice() {
        }

        public void setRouter_ser_num(int router_ser_num) {
            this.router_ser_num = router_ser_num;
        }

        public void setReservation_num(int reservation_num) {
            this.reservation_num = reservation_num;
        }

        public int getRouter_ser_num() {
            return router_ser_num;
        }

        public int getReservation_num() {
            return reservation_num;
        }
     
        
        public void setRenting_fees(double renting_fees) {
            this.renting_fees = renting_fees;
        }

        public double getRenting_fees() {
            return renting_fees;
        }
         } 
 public static class date {
/**
 * this class for date and contain the day and month  
 */
     public int day ;
    public int month ;

        public date(int day, int month) {
            this.day = day;
            this.month = month;
        }

        public date() {
        }

      
     
 }
 public static class reservation {
    public static int unique=1;
    /**
     * to make reservation number unique , so unique is static when creating object the unique 
     * increment by one  ex the first reservation is 1 , 2, 3 and so on
     */
    private int  unique_reservation_num =1;
    date end_date = new date ();
    int duration;
    date start_date = new date ();
    String type ;
    date reservation_date = new date();
    router ro ;
    invoice inv = new invoice() ;
   
        public int getUnique_reservation_num() {
            return unique_reservation_num;
        }

        public static void setUnique(int unique) {
            reservation.unique = unique;
        }

        public reservation() {
            unique_reservation_num=unique;
            unique++;
        }
    
      /**
       * 
       * @param day save reservation day on it 
       * @param month save reservation month on it 
       * @param type type of reservation (daily , monthly, weekly )
       * @param ro 
       */
        public reservation(int day , int month , String type,router ro) {
            start_date = new date(day,month);
            unique_reservation_num=unique;
            this.ro=ro;
            this.type = type;
            unique++;
            /**
             * 
             * set reservation date from the laptop time 
             */
            LocalDate currentdate = LocalDate.now(); 
            
         reservation_date.month=currentdate.getMonthValue();
         reservation_date.day=currentdate.getDayOfMonth();
        
         calculate_end_date (type);
        }
        /**
         * this method to calculate end date and end date is start date plus duration
         * for example if start date 20/6 and the user rent a router for a week
         * so end date 27/6 and so one 
         * @param type (daily , monthly , weekly)
         */
         public void calculate_end_date (String type){
             int num = 0;
        if (type.equals("daily"))
            num=1;
        else if (type.equals("weekly"))
            num=7;
        else if (type.equals("monthly"))
            num =30;
        
        end_date.day=(start_date.day+num)%30;
        end_date.month=start_date.month+(start_date.day+num)/30;
                
        }

        
      
        

        public date getStart_date() {
            return start_date;
        }

        public String getType() {
            return type;
        }

        public void setStart_date(date start_date) {
            this.start_date = start_date;
        }

        public void setType(String type) {
            this.type = type;
        }

        /**
         * calculate how many days in the duration 
         * @param type
         * @return 
         */
         public int calc_duration(String type){
            int number = 0 ;
       if (type.equals("daily"))
           number = 1;
       else if (type.equals("weekly"))
           number =7;
       else if (type.equals("monthly"))
         number = 30;  
            return number ;
        }
    } 
public static abstract class customer {
    double total_price ;
    Scanner in = new Scanner (System.in);
    /**
     * if user want to make many reservation so arraylist for many reservation
     * so any reservation i can edit it from this arraylist 
     */
    ArrayList<reservation>reservations = new ArrayList<>();
    final String name; 
        public customer(String name ) {
            this.name = name;
        }
        /**
         * 
         * @param day the start day that user wants to rent a router on it 
         * @param month the start month that user wants to rent a router on it 
         * @param type daily , weekly , monthly (duration of rent) 
         * @param ro the router that the user wants to rent it  
         * @param routers  array list of router
         */
   void rent_router( int day , int month,
        String type , router ro , ArrayList<router>routers ){
     try{
         /**
          * save reservation information in reservation and invoice object 
          */
       reservation re= new reservation(day ,month,type ,ro) ;
       re.inv.setReservation_num(re.getUnique_reservation_num());
       re.inv.setRouter_ser_num(ro.serialnum);
       re.inv.setRenting_fees(ro.getPrice_per_day()*re.calc_duration(type));
      /**
         * when user rent router it will save in invoice if he want to cancel rent and return it
       */
        re.ro=ro;
       reservations.add(re);
       /**
        * make router unavailable and delete it from router arraylist 
        * and return it if user cancel rent 
        */
       routers.remove(ro);
      /**
       this is abstract method and overrided in foreigner and resident class 
       */
       discount_on_renting_fees(re);
       System.out.println("rented successfully"); 
       /**
        * the price for all reservation 
        */
       total_price+=re.inv.renting_fees;
     }
     catch (Exception ex ){
         System.out.println("enter day and month integer ");
     }
       
   }
   /**
    * abstract method if customer is resident he has discount 
    * @param re 
    */
   abstract void discount_on_renting_fees(reservation re);  
   /**
    * cancel rent and reservation
    * @param reservation_number specific reservation number from reservation arraylist   
    * @param routers 
    */
   public void cancel_rent(int reservation_number,ArrayList<router>routers ){
      try{
        
            for (int i = 0; i <reservations.size(); i++) {
                /**
                 * search for a specific reservation on reservations arraylist 
                 * to cancel it 
                 */
           if (reservation_number==reservations.get(i).unique_reservation_num){
               /**
                * check if there is Only two days left or not ?
                */
              if ((reservations.get(i).start_date.month-
                  reservations.get(i).reservation_date.month)>=1||
               (reservations.get(i).start_date.day-reservations.get(i).reservation_date.day)>=2){ 
              /**
               * 
               * making router available in routers arraylist  so any customer can take it again
               */
               routers.add(reservations.get(i).ro);
               total_price-=reservations.get(i).inv.renting_fees;
               reservations.remove(i);
               System.out.println("deleted successfully");
               break;
           }
       }
           
            else{
           throw new Exception ("you can't cancel rent before two days");
           
       }
       }
      }
      catch (NullPointerException d){
                    System.out.println("you must rent a router first");

      }
      catch (Exception e)
      {
          System.out.println("you can't cancel rent before two days");
      }      
    } 
   /**
    * this function to extend reservation 
    * @param days how many days user wants
    * @param reservation_number 
    */
   public void Extend_renting_duration(int days ,int reservation_number ){
     try {
         /**
           * 
           * if user didn't rent any router ,the compiler makes an exception  
           */
         if (reservations.size()==0)
             
             throw new Exception ();
     
       for (int i = 0; i < reservations.size(); i++) {
          /**
                 * search for a specific reservation on reservations arraylist 
                 * to Extend its renting duration 
                 */
           if (reservation_number==reservations.get(i).getUnique_reservation_num()){
              
             reservations.get(i).duration+=days;
             reservations.get(i).end_date.day=(reservations.get(i).end_date.day+days)%30;
             reservations.get(i).end_date.month+=(reservations.get(i).end_date.day+days)/30;
             
             System.out.println("extended successfully"+
                     "end day " + reservations.get(i).end_date.day +
                     "\nend month "+reservations.get(i).end_date.month ) ;
             
               break;
           }
       }
     }
     catch (NullPointerException n){
        System.out.println("make a reservation first");
    } 
     catch (Exception d){
         System.out.println("you must rent a router first");
         
     }
    
   }
   /**
    * if user want to change router in specific reservation
    * @param new_router new router index 
    * @param old_id_router old router
    * @param routers 
    */
   public void change_router_model(router new_router , int old_id_router,ArrayList<router>routers  ){
      try{
          /**
           * 
           * if user didn't rent any router ,the compiler makes an exception  
           */
          if (reservations.size()==0)
              throw new Exception ();
       for (int i = 0; i < reservations.size(); i++) {
          
           /** search for a specific reservation on reservations arraylist 
                 * to change router model on it 
                 * */
           if (old_id_router==reservations.get(i).ro.getSerialnum())
               reservations.get(i).ro=new_router;
       }
      }
      catch(Exception haha){
          System.out.println("you must rent router first ");
      }
   } 
   
   /**
            * display all information about customer for example reservations and invoices 
            */
   public void display_customer (){
       try {
           /**
           * 
           * if user didn't rent any router ,the compiler makes an exception  
           */
           if (reservations.size()==0)
               
           throw new NullPointerException ();
           
       System.out.println("customer name : " + name);
       System.out.println("the customer made "+ reservations.size() + " reservation /s ");
       for (int i = 0; i < reservations.size(); i++) {
           
           System.out.println(  "\treservation "+(i+1));
           System.out.println(
            "start date \t "+reservations.get(i).start_date.day + " / "+reservations.get(i).start_date.month+"\n"+
             "end date \t "+ reservations.get(i).end_date.day + " / " +reservations.get(i).end_date.month+"\n" +
              "reservation date \t "+reservations.get(i).reservation_date.day+ " / "+reservations.get(i).reservation_date.month
          +"\nrouter model \t"+reservations.get(i).ro.getModel()
           );
           reservations.get(i).inv.display_invoice();
           System.out.println("\ntotal price \t"+ total_price); 
          
           if (i!=reservations.size()-1)
                   System.out.println("-----");
       }
                  System.out.println("-----------------------------------------------------------");
       }
       catch(NullPointerException n ){
           System.out.println("you must make a reservation first ");
       }
       
   }
   /**
    * make feed back and store it in static feedback  arraylist 
    */
   public void feedback(){
       try{
           if (reservations.size()==0){
               throw new Exception ();
           }
       System.out.println("enter your feedback\n");
       String s ;
       s=in.next();
       issue_feedback.add(s);
       System.out.println("your feedback has been submitted");
       }
       catch(Exception e ){
           System.out.println("you must rent a router first");
       }
   }
   /**
    * resident class extends from customer class
    * if the user is resident ,he has discount 25%
    */
}
public static class resident extends customer{

        public resident(String name) {
            super(name);
            System.out.println("resident customer");
        }

       

        @Override
        void discount_on_renting_fees(reservation re) {
                re.inv.renting_fees -= (re.inv.renting_fees*.25);
        }
    
}
/**
 * foreigner class extends from customer class 
 * if the user is foreigner ,he hasn't any discount 
 */
public static class foreigners extends customer{

        public foreigners(String name) {
            super(name);
            System.out.println("foreigner customer ");
        }

        

        @Override
        void discount_on_renting_fees(reservation re) {
         //  foreigners doesn't have discount on their renting fees 
        }
    
}
/**
 * single responsibility principle 
 * *solid principle*
 * this class for only data , the program reads it 
 */
public static class data {
/**
 * small date for resident people if user name in this arraylist 
 * so he is a resident and has 25% discount  
 * @param resident_names 
 */
 static void resident_data (ArrayList<String>resident_names){
    resident_names.add("ali");
    resident_names.add("mohamed");
    resident_names.add("ahmed");
    resident_names.add("nehal");
    resident_names.add("shaimaa");
    resident_names.add("omr");
    resident_names.add("asmaa");
    resident_names.add("esraa");
    resident_names.add("yasmeen");
}
/**
 * date for router .. model , num of port etc...
 * @param routers 
 */
 static void routers_data (ArrayList<router>routers){
    routers.add(new router("EeroHome ",5,25));
    routers.add(new router("Netgear_AC1750",4,50));
    routers.add(new router("sdfAmpliFi_HD",6,100.5));
    routers.add(new router("Netgear Nighthawk",5,100));
    routers.add(new router("Securifi Almond",5,115));
    routers.add(new router("ASUS RT-AC5300",5,120));
    routers.add(new router("D-Link AC3200 ",5,130));
    routers.add(new router("ASUS RT-AC5300",5,120));
    routers.add(new router("Securifi Almond250",5,152));

    routers.add(new router("ASUS RT-AC5715",5,50));
    routers.add(new router("ASUS RT-AC5356",5,1000));

}
}
/**
 * display only available router if user take router it remove automatically from arraylist 
 * @param routers 
 */
static void diplay_available_routers (ArrayList<router>routers){
    for (int i = 0; i < routers.size(); i++) {
        System.out.println(i+1+ " router serial number \t"+routers.get(i).serialnum
        +"\n"+ "model\t" + routers.get(i).model+"\n"
        + "number of port\t"+routers.get(i).num_of_ports);
    }
}
    public static void main(String[] args) {
                System.out.println("---------------------------------------------------------"
                        + "\n\ttest cases (hardcoded)\n---------------------------------------------------------");

        ArrayList<customer> customers=new ArrayList<>();
        ArrayList<String>resident_names=new ArrayList<>();        
        ArrayList<router>routers=new ArrayList<>();
        data.routers_data(routers);
        data.resident_data (resident_names);
       
        Scanner in = new Scanner (System.in);
        /**
         * different customer
         */
        customer cus1;
        customer cus2;
        customer cus3;
        System.out.println("enter your name to check if you are resident or foreignet");
        String name1 = "ahmed";
        
        if (resident_names.contains(name1)){
            
            cus1 = new resident(name1);
        }
        else {
            cus1= new foreigners(name1);
       }
        
    cus2= new foreigners("yasmeen");
    cus3= new resident ("mohamed");
    /** Generate different invoices and link them to different customers.*/

    System.out.println("making different invoices ");
    cus1.rent_router(30, 6, "weekly",routers.get(1), routers);
    cus2.rent_router(30, 6, "daily",routers.get(2), routers);
    cus3.rent_router(30, 6, "monthly",routers.get(0), routers);
    System.out.println("---------------------------");
    System.out.println("invoice for customer 1 " );
   cus1.reservations.get(0).inv.display_invoice();
        System.out.println("---------------------------");
   System.out.println("cusomer 2  " );
   cus2.reservations.get(0).inv.display_invoice();
           System.out.println("---------------------------");

   System.out.println("cusomer 3 " );
   cus3.reservations.get(0).inv.display_invoice();
          System.out.println("--------------------------------------------");

/** one customer with multiple invoices.(make many reservations)*/
System.out.println("make many reservations and invoices");
    customer cus4 = new resident ("nehal");
    cus4.rent_router(30, 6, "daily", routers.get(1), routers);
    cus4.rent_router(30, 6, "weekly", routers.get(2), routers);
    cus4.rent_router(12, 6, "monthly", routers.get(0), routers);
    /** print multiple invoices and total price */
    
        System.out.println("print all invoices for only one customer");
        cus4.display_customer();
     /** test all function of customer*/
          System.out.println("--------------------------------------------");
      
    customer cus5 = new foreigners("toni");
    /**1st function rent router */
  
    cus5.rent_router(15, 6, "daily",  routers.get(0), routers);
    /**2nd function cancel rent*/
     cus5.cancel_rent(cus5.reservations.get(0).unique_reservation_num, routers);
     System.out.println(cus5.reservations.size());// 0 because i cancel the only reservation
     /**3rd function extend reservation */
        System.out.println("extend duration to 6 day ");
     cus5.rent_router(15, 6 , "daily", routers.get(1), routers);
     
     cus5.Extend_renting_duration(6, cus5.reservations.get(0).unique_reservation_num);
  /**start date 15/6 i extend reservation 6 day so end date 22 instead of 16 */

      /**4th function change router model */
     System.out.println("change router model");
    cus5.change_router_model(routers.get(0), 0, routers); // pass new router  in router array list , and 
      /**the router id which user wants to change it , and arraylist of routers*/
      /**5th function feedback */
    issue_feedback.add("router is very good");
    System.out.println(cus5.reservations.get(0).ro.model);
        customers.add(cus1);
        customers.add(cus2);
        customers.add(cus3);
        customers.add(cus4);
        customers.add(cus5);
        for (int i = 0; i < customers.size(); i++) {
            customers.get(i).display_customer();
        }
    
        System.out.println("---------------------------------------------------------\n\tnormal program (user input)\n---------------------------------------------------------");
       routers.clear();
        data.routers_data(routers);
        System.out.println("\twelcome");
            System.out.println("enter your name to "
                        + "check if you are resident or foreignet");
                String name ;
                name= in.next();
                 customer cus ;
            
                if (resident_names.contains(name)){
            
                        cus = new resident(name);
                    }
                    else {
                        cus= new foreigners(name);
                   }
            
        while (true){
            
            
            System.out.println(
                      "1- rent router                  2- cancel rent \n"
                    + "3- extend duration              4- change router model\n"
                    + "5-display customer information  6-display available routers\n"
                              + "7-feedback ");
            int a; a=in.nextInt();
            int num1,num2,num3 ;
            if (a==1){
                int s_day , s_month,index;
                String namee ;
                try{
                System.out.println("enter start day");
                s_day=in.nextInt();
                System.out.println("enter start month");
                s_month=in.nextInt();
              LocalDate currentdate = LocalDate.now(); 
            
         currentdate.getMonthValue();
         currentdate.getDayOfMonth();
         if ( currentdate.getMonthValue()>s_month|| 
                ( currentdate.getDayOfMonth() >s_day && currentdate.getMonthValue()==s_month))
             throw new Exception();
                System.out.println("enter type of rent (daily,weekly,monthly");
                namee= in.next();
                System.out.println("enter router index");
                index = in.nextInt();
                 cus.rent_router(s_day, s_month, namee,routers.get(index-1) , routers);
                }
                 catch(InputMismatchException ex ){
                    System.out.println("enter day and month in integer");
                }
                catch (Exception ex ){
                    System.out.println("you cant enter previos date");
                }
               
               
            }
            else if (a==2){
                try{
                System.out.println("enter reservation number");
                num1= in.nextInt();
                cus.cancel_rent(num1, routers);
                }
                catch(InputMismatchException ex ){
                    System.out.println("you must enter an integer");
                }
            }
            else if (a==3)
            {
                try{
                System.out.println("how many days do you want ?");
                num1= in.nextInt();
                System.out.println("enter reservation num ");
                num2=in.nextInt();
                cus.Extend_renting_duration(num1, num2);
                }
                catch(InputMismatchException ex ){
                    System.out.println("you must enter an integer");
                }
                
            }
            else if (a==4){
                try{
             System.out.println("enter router index");
             num1=in.nextInt();
                System.out.println("enter old router id ");
             num2=in.nextInt();   
             cus.change_router_model(routers.get(num1-1), num2, routers);
                }
                catch(InputMismatchException ex ){
                    System.out.println("you must enter an integer");
                }
             
            }
            else if (a==5){
                cus.display_customer();
            }
            else if (a==6)
            {
                diplay_available_routers(routers);
            }
            else if (a==7){
                cus.feedback();
            }
            
            
        }
    }
    
    
}
