/* SequenceTest.java
*  Driver file to test DoubleArraySeq class
*  Project #1, Data Structures
*
*
*
*  Authors: Don Craig / Joseph Corvelli / Hussein Handan
*  Date: 3/1/2018
*/


import java.util.*;     //using for scanner class  
     

class SequenceTest {


   public static void main(String[] args) {
   
      boolean done=false, valid=false;
      int menu=0, index=0;
      double newData=0;
      DoubleArraySeq clone;
      DoubleArraySeq catSequence;
      DoubleArraySeq sequenceA = new DoubleArraySeq();
      DoubleArraySeq sequenceB = new DoubleArraySeq(5);
      Scanner scan = new Scanner(System.in);
      char active = 'A';
   
      
      System.out.println("This program allows you to create sequences of double values (i.e. decimal numbers) and manipulate them.");
      System.out.println("Sequences A & B start out empty, please use the menu to add data for use.");
      System.out.println("Active sequence is A by default until you change it using the menu.");
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
                    System.out.println("Please enter which number you would like to add before");
                    newData = getNum();
                    if(active == 'A' && sequenceA.size() != 0)
                    {
                       index = search(sequenceA,newData);
                       if(index != -1)
                       {
                           System.out.println("Number found! Enter the number you would like to add before it");
                           newData = getNum();
                           sequenceA.setCurrent(index);
                           sequenceA.addBefore(newData);
                           System.out.println("Element Succesfully Added!\n" + "Sequence A: " + sequenceA);
                       }
                       else
                       {
                           System.out.println("Error! Number you wish to add before does not exist!");
                       }
                     
                    }
                    else if(active == 'B' && sequenceB.size() != 0)
                    {
                       index = search(sequenceB,newData);
                       if(index != -1)
                       {
                           System.out.println("Number found! Enter the number you would like to add before it");
                           newData = getNum();
                           sequenceB.setCurrent(index);
                           sequenceB.addBefore(newData);
                           System.out.println("Element Succesfully Added!\n" + "Sequence B: " + sequenceB);
                       }
                       else
                       {
                           System.out.println("Error! Number you wish to add before does not exist!");
                       }
                    }
                    else
                    {
                       System.out.println("Error! Current Active Sequence is empty, no element may be searched for!");
                       System.out.println("Use menu option 5 or 8 to begin to fill an empty sequence before attempting to use options 6 or 7!");         
                    }
                    break;
            case 7: System.out.println("Please enter which number you would like to add after");
                    newData = getNum();
                 
                    if(active == 'A'&& sequenceA.size() != 0)
                    {
                        index = search(sequenceA,newData);
                        if(index != -1)
                        {
                           System.out.println("Number found! Enter the number you would like to add after it");
                           newData = getNum();
                           sequenceA.setCurrent(index);
                           sequenceA.addAfter(newData);
                           System.out.println("Element Succesfully Added!\n" + "Sequence A: " + sequenceA);
                        }
                        else
                        {
                           System.out.println("Error! Number you wish to add before does not exist!");
                        }
                     
                     } 
                     else if(active == 'B' && sequenceB.size() != 0)
                     {
                        index = search(sequenceB,newData);
                     
                        if(index != -1)
                        {
                           System.out.println("Number found! Enter the number you would like to add after it");
                           newData = getNum();
                           sequenceB.setCurrent(index);
                           sequenceB.addAfter(newData);
                           System.out.println("Element Succesfully Added!\n" + "Sequence B: " + sequenceB);
                        }
                        else
                        {
                           System.out.println("Error! Number you wish to add after does not exist!");
                        }
                     }
                 
                     else
                     {
                        System.out.println("Error! Current Active Sequence is empty, no element may be searched for!");
                        System.out.println("Use menu option 5 or 8 to begin to fill an empty sequence before attempting to use options 6 or 7!");         
                      }
                   
                     break;
                 
            case 8:  newData=getNum();
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
            case 9:  sequenceA.addAll(sequenceB);
                     System.out.println("Sequence B succesfully added to the end of A!");
                     printSeq(sequenceA, 'A');
                     break;
            case 10: delAtIndex(active,sequenceA,sequenceB);
                     break;
            case 11: delFirst(active,sequenceA,sequenceB);
                     break;
            case 12: showAtIndex(active,sequenceA,sequenceB);
                     break;
            case 13: showLast(active,sequenceA,sequenceB);
                     break;
            case 14: sequenceA.trimToSize();
                     sequenceB.trimToSize();
                     System.out.println("Excess memory trimmed from both sequences.");
                     break;
            case 15: if(active == 'A' && sequenceA.size() != 0)
                     {
                        clone = sequenceA.clone();
                        System.out.println("Sequence A successfully cloned!\n" + "Clone Sequence: " + clone);
                     }
                     else if(active == 'B' && sequenceB.size() != 0)
                     {
                        clone = sequenceB.clone();
                        System.out.println("Sequence B successfully cloned! \n" + "Cloned Sequence: " + clone);
                     }
                     else
                     {
                        System.out.println("Error! Current sequence is empty, please enter data into the sequence to be cloned.");
                     }
                     break;
            case 16: if((sequenceA.size()==0) || (sequenceB.size()==0))
                     {
                        System.out.println("Cannot concatinate an empty sequence");
                     }
                     else
                     {
                        catSequence = DoubleArraySeq.concatenation(sequenceB,sequenceA);
                        System.out.print("SeqeunceB concatenated with SequenceA yields ");
                        System.out.println(catSequence);
                     }
                     break;
            case 17: done=true;
                     break;
         }
      }while(!done);
      
      System.out.println("Thank you for using our program, have a great day!");
   

   
   
   } // end main method
   
   public static void printMenu()                    // method to print menu after each operation
   {
      System.out.println();   
      System.out.println(" 1: Print the sequences of A and B");                     
      System.out.println(" 2: Report the capacity of A and B");                     
      System.out.println(" 3: Report if A and B are equal");                        
      System.out.println(" 4: Change active sequence (default is A)");              
      System.out.println(" 5: Add a number to the front of a sequence");            
      System.out.println(" 6: Add a number before a given number(user inputted)");  
      System.out.println(" 7: Add a number after a given number(user inputted)");   
      System.out.println(" 8: Add a number to the end of a sequence");              
      System.out.println(" 9: Add sequence B to end of A");                         
      System.out.println("10: Delete a number at a certain index");                 
      System.out.println("11: Delete the first number from the sequence");          
      System.out.println("12: Display a number at a certain index");                
      System.out.println("13: Display the last element in the sequence");           
      System.out.println("14: Trim extra memory from both A and B");                
      System.out.println("15: Create a clone sequence and show");                   
      System.out.println("16: Create a new sequence using concatenate of B and A and show"); 
      System.out.println("17: Quit");                                               
      
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
   
   public static int search(DoubleArraySeq seq1, double target)  // method to search through a sequence for a specific value
   {
      seq1.start();
      int result = -1;
      for(int loop = 0; loop < seq1.size(); loop++)
      {
         if(seq1.getCurrent() == target)
         {
            result = loop;
            return result;
         }
         seq1.advance();
      }
      return result;
   } 
   
   
   public static void printSeq(DoubleArraySeq seq, char active)         // prints sequence 
   {
      System.out.println();
      System.out.println("Sequence " + active + " = " + seq );
      System.out.println();
   }
   
   public static void showLast(char active, DoubleArraySeq sequenceA, DoubleArraySeq sequenceB)  // prints last element of a non-empty sequence 
   {
      if (active=='A' && sequenceA.size() !=0)
      {
         sequenceA.setCurrentLast();
         System.out.println("Last element of Sequence A is " + sequenceA.getCurrent());
      }
      else if (active=='B' && sequenceB.size() !=0)
      {
         sequenceB.setCurrentLast();
         System.out.println("Last element of Sequence B is " + sequenceB.getCurrent());
      }
      else
         System.out.println("Sequence is empty. Operation canceled.");

   }
   
   public static void delAtIndex(char active, DoubleArraySeq seqA, DoubleArraySeq seqB)  // deletes value at index entered by user (if it exists)
   {
      int target;
      System.out.println("Value at the index you choose will be deleted.");
      target=getInt();
      if (active=='A')
         if (target > (seqA.size()-1))
            System.out.println("Sequence A does not have that many elements. Operation canceled.");
         else
         {
            seqA.setCurrent(target);
            seqA.removeCurrent();
            System.out.println("Element removed.");
            printSeq(seqA,active);
         }
      else
         if (target > (seqB.size()-1))
            System.out.println("Sequence B does not have that many elements. Operation canceled.");
         else
         {
            seqB.setCurrent(target);
            seqB.removeCurrent();
            System.out.println("Element removed.");
            printSeq(seqB,active);
         }
   }
   
   public static void showAtIndex(char active, DoubleArraySeq seqA, DoubleArraySeq seqB)     // shows value at index entered by user (if it exists)
   {
   int target;
      System.out.println("Which index do you want to display?");
      target=getInt();
      if (active=='A')
         if (target > (seqA.size()-1))
            System.out.println("Sequence A does not have that many elements. Operation canceled.");
         else
         {
            seqA.setCurrent(target);
            System.out.println("Index #" + target + " of sequence A is " + seqA.getCurrent() ); 
         }
      else
         if (target > (seqB.size()-1))
            System.out.println("Sequence B does not have that many elements. Operation canceled.");
         else
         {
            seqB.setCurrent(target);
            System.out.println("Index #" + target + " of sequence A is " + seqA.getCurrent() );  
         }
   }
   
   public static void delFirst(char active, DoubleArraySeq seqA, DoubleArraySeq seqB)     // deletes first element of a non-empty sequence
   {
      if (active=='A')
         if (seqA.size() !=0)
         {
            seqA.removeFront();
            printSeq(seqA,active);
         }
         else
            System.out.println("Sequence A is already empty. Operation canceled.");
      else
         if (seqB.size() !=0)
         {
            seqB.removeFront();
            printSeq(seqB,active);
         }
         else
            System.out.println("Sequence B is already empty. Operation canceled.");
     
   }
   
} // end code
   