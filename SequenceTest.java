/* SequenceTest.java
*  Driver file to test DoubleArraySeq class
*
*
*
*
*
*
*/

import java.text.*;     //using for decimal formatting class
import java.util.*;     //using for scanner class  
     

class SequenceTest {



   public static void main(String[] args) {
   
   boolean done=false, valid=false;
   int menu=0;
   double newData=0;
   DoubleArraySeq sequenceA = new DoubleArraySeq();
   DoubleArraySeq sequenceB = new DoubleArraySeq(5);
   Scanner scan = new Scanner(System.in);
   char active = 'A';
   
   do
   {
      printMenu();
      valid=false;
      while(!valid)
      {
         try
         {
            System.out.println();
            System.out.print("Choice: ");
            menu=scan.nextInt();
            if (menu<1 || menu>17) 
            {
               valid = false;
               throw new Exception("Choice must be from 1 to 17.");
            }
            else
            {
               valid = true;
            }
            
         }
         catch (InputMismatchException e) 
         {
            System.out.println("Invalid entry, must be an integer from 1 to 17.");
            scan.next();
         }
         catch (Exception e) {
            System.out.println(e.getMessage() );
         }
      }
      
      System.out.println();
      switch (menu)                       
      {
         case 1: printSeq(sequenceA, 'A');
                 printSeq(sequenceB, 'B');
                 break;
         case 2: System.out.println("SequenceA has a capacity of " + sequenceA.getCapacity() ); //print capacity of A & B
                 System.out.println("SequenceB has a capacity of " + sequenceB.getCapacity() );
                 break;
         case 3: if (sequenceA.equals(sequenceB))
                    System.out.println("SequenceA & SequenceB are equal.");
                 else
                    System.out.println("SequenceA & SequenceB are not equal.");//report if A & B are equal
                 break;
         case 4: if (active=='A')          //change active sequence
                 {
                    active='B';
                    System.out.println("Active sequence is now B");
                 }
                 else
                 {
                    active='A';
                    System.out.println("Active sequence is now A");
                 }
                 break;
         case 5: newData=getNum();
                 if (active=='A')
                 {
                    sequenceA.addFront(newData);
                    printSeq(sequenceA, 'A');
                 }
                 else
                 {
                    sequenceB.addFront(newData);
                    printSeq(sequenceB, 'B');
                 }
                 break;
         case 6:
                 break;
         case 7:
                 break;
         case 8: newData=getNum();
                 if (active=='A')
                 {
                    sequenceA.addEnd(newData);
                    printSeq(sequenceA, 'A');
                 }
                 else
                 {
                    sequenceB.addEnd(newData);
                    printSeq(sequenceB, 'B');
                 }
                 break;
         case 9: sequenceA.addAll(sequenceB);
                 printSeq(sequenceA, 'A');
                 break;
         case 10:delAtIndex(active,sequenceA,sequenceB);
                 break;
         case 11:if (active=='A')
                 {
                    sequenceA.removeFront();
                    printSeq(sequenceA, 'A');
                 }
                 else
                 {
                    sequenceB.removeFront();
                    printSeq(sequenceB, 'B');
                 }

                 break;
         case 12:showAtIndex(active,sequenceA,sequenceB);
                 break;
         case 13: showLast(active,sequenceA,sequenceB);
                  break;
         case 14: sequenceA.trimToSize();
                  sequenceB.trimToSize();
                  System.out.println("Excess memory trimmed from both sequences.");
                  break;
         case 15:
                 break;
         case 16:
                 break;
         case 17: done=true;
                  break;
      }
   }while(!done);
   

   
   
   } // end main method
   
   public static void printMenu()
   {
      System.out.println();   
      System.out.println(" 1: Print the sequences of A and B");                     //done
      System.out.println(" 2: Report the capacity of A and B");                     //done
      System.out.println(" 3: Report if A and B are equal");                        //done
      System.out.println(" 4: Change active sequence (default is A)");              //done
      System.out.println(" 5: Add a number to the front of a sequence");            //done
      System.out.println(" 6: Add a number before a given number(user inputted)");  //
      System.out.println(" 7: Add a number after a given number(user inputted)");   //
      System.out.println(" 8: Add a number to the end of a sequence");              //done
      System.out.println(" 9: Add sequence B to end of A");                         //done
      System.out.println("10: Delete a number at a certain index");                 //done
      System.out.println("11: Delete the first number from the sequence");          //partial, needs error handling
      System.out.println("12: Display a number at a certain index");                //done
      System.out.println("13: Display the last element in the sequence");           //done
      System.out.println("14: Trim extra memory from both A and B");                //done
      System.out.println("15: Create a clone sequence and show");                   //
      System.out.println("16: Create a new sequence using concatenate of B and A and show"); //
      System.out.println("17: Quit");                                               //done
      
   }
   
   public static double getNum( )           // method to get a double as input
   {
      Scanner numScan = new Scanner(System.in);
      double input=0;
      boolean valid=false;
      while(!valid)
      {
         try
         {
            System.out.println();
            System.out.print("Enter a number: ");
            input=numScan.nextDouble();
            valid=true;
         }
         catch (InputMismatchException e) 
         {
            System.out.println("Invalid entry, must be a number.");
            numScan.next();
         }
      }
      return input;
   
   }
   
   
   public static int getInt( )           // method to get an integer as input
   {
      Scanner numScan = new Scanner(System.in);
      int input=0;
      boolean valid=false;
      while(!valid)
      {
         try
         {
            System.out.println();
            System.out.print("Enter an integer: ");
            input=numScan.nextInt();
            if (input<0) 
            {
               valid = false;
               throw new Exception("Value must be positive.");
            }
            else
            {
               valid = true;
            }
            
         }
         catch (InputMismatchException e) 
         {
            System.out.println("Invalid entry, must be an integer.");
            numScan.next();
         }
         catch (Exception e) {
            System.out.println(e.getMessage() );
         }
      }
      return input;
   
   }
   
   public static void printSeq(DoubleArraySeq seq, char active)
   {
      System.out.println();
      System.out.println("Sequence" + active + " = " + seq );
      System.out.println();
   }
   
   public static void showLast(char active, DoubleArraySeq sequenceA, DoubleArraySeq sequenceB)
   {
      if (active=='A')
      {
         sequenceA.setCurrentLast();
         System.out.println("Last element of SequenceA is " + sequenceA.getCurrent());
      }
      else
      {
         sequenceB.setCurrentLast();
         System.out.println("Last element of SequenceB is " + sequenceB.getCurrent());
      }

   }
   
   public static void delAtIndex(char active, DoubleArraySeq seqA, DoubleArraySeq seqB)
   {
      int target;
      System.out.println("Value at the index you choose will be deleted.");
      target=getInt();
      if (active=='A')
         if (target > (seqA.size()-1))
            System.out.println("SequenceA does not have that many elements. Operation canceled.");
         else
         {
            seqA.setCurrent(target);
            seqA.removeCurrent();
            System.out.println("Element removed.");
            printSeq(seqA,active);
         }
      else
         if (target > (seqB.size()-1))
            System.out.println("SequenceB does not have that many elements. Operation canceled.");
         else
         {
            seqB.setCurrent(target);
            seqB.removeCurrent();
            System.out.println("Element removed.");
            printSeq(seqB,active);
         }
   }
   
   public static void showAtIndex(char active, DoubleArraySeq seqA, DoubleArraySeq seqB)
   {
   int target;
      System.out.println("Which index do you want to display?");
      target=getInt();
      if (active=='A')
         if (target > (seqA.size()-1))
            System.out.println("SequenceA does not have that many elements. Operation canceled.");
         else
         {
            seqA.setCurrent(target);
            System.out.println("Index #" + target + " of sequenceA is " + seqA.getCurrent() ); 
         }
      else
         if (target > (seqB.size()-1))
            System.out.println("SequenceB does not have that many elements. Operation canceled.");
         else
         {
            seqB.setCurrent(target);
            System.out.println("Index #" + target + " of sequenceA is " + seqA.getCurrent() );  
         }
   }
   
} // end code
   