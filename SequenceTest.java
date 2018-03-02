/* SequenceTest.java
*  Driver file to test DoubleArraySeq class
*  Project #1, Data Structures
*
*  Allows for the storage of double values 
*  in a sequenced order, along with the
*  option to manipulate the sequences
*  and data in multiple ways.
*
*  Authors: Don Craig / Joseph Corvelli / Hussein Handan
*  Date: 3/1/2018
*/


import java.util.*;     //using for scanner class  
     

class SequenceTest {


   public static void main(String[] args) {
   
      boolean done=false, valid=false;
      int menu=0;
      double newData=0;
      DoubleArraySeq clone = new DoubleArraySeq();
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
         
         /*
            Switch statement used to implement menu choices.
         */
         switch (menu)                       
         {
            case 1: 
               printSeq(sequenceA, 'A');
               printSeq(sequenceB, 'B');
               break;
            case 2: System.out.println("SequenceA has a capacity of " + sequenceA.getCapacity() ); 
               System.out.println("SequenceB has a capacity of " + sequenceB.getCapacity() );
               break;
            case 3: 
               if (sequenceA.equals(sequenceB))
                  System.out.println("SequenceA & SequenceB are equal.");
               else
                  System.out.println("SequenceA & SequenceB are not equal.");
               break;
            case 4: 
               if (active=='A')          
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
            case 5: 
               newData=getNum();
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
               addingBefore(sequenceA,sequenceB,active); 
               break;
            case 7: 
               addingAfter(sequenceA,sequenceB,active); 
               break;
                 
            case 8:  
               newData=getNum();
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
            case 9:  
               sequenceA.addAll(sequenceB);
               System.out.println("Sequence B succesfully added to the end of A!");
               printSeq(sequenceA, 'A');
               break;
            case 10: 
               delAtIndex(active,sequenceA,sequenceB);
               break;
            case 11: 
               delFirst(active,sequenceA,sequenceB);
               break;
            case 12: 
               showAtIndex(active,sequenceA,sequenceB);
               break;
            case 13: 
               showLast(active,sequenceA,sequenceB);
               break;
            case 14: 
               sequenceA.trimToSize();
               sequenceB.trimToSize();
               System.out.println("Excess memory trimmed from both sequences.");
               break;
            case 15: 
               makeClone(sequenceA,sequenceB,clone,active);
               break;  
            case 16:
               concat(sequenceA,sequenceB); 
               break;
            case 17: done=true;
               break;
         }
      }while(!done);
      
      System.out.println("Thank you for using our program, have a great day!");
   
   
   
   
   } // end main method
   
   /* 
      Method for displaying the menu initially and then re-displaying after each menu operation
      is completed.
   */   
   public static void printMenu()                    
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
   /* 
      Method for allowing input of a double value. Error checking in place to 
      ensure that a number is entered. If an integer is entered it will be cast
      to a double value.
   */    
   public static double getNum( )           
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
   
   /* 
      Method for allowing input of an integer value. Error checking in place to 
      ensure that a positive integer is entered. 
   */    
   public static int getInt( )           
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
   /* 
      Method to search through a sequence and find a specific value entered by the user.
      If the value is found, the index of that value is returned. If not found, a -1 is returned 
      indicating an error.
   */ 
   public static int search(DoubleArraySeq seq1, double target)  
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
   
   /* 
      Method for printing a sequence to the screen.
   */    
   public static void printSeq(DoubleArraySeq seq, char active)          
   {
      System.out.println();
      System.out.println("Sequence " + active + " = " + seq );
      System.out.println();
   }
   /* 
      Method to display last element of the active sequence. If sequence is empty an error message
      is displayed.
   */    
   public static void showLast(char active, DoubleArraySeq sequenceA, DoubleArraySeq sequenceB)   
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
         System.out.println("Sequence is empty. Operation cancelled.");
   
   }
   /* 
      Method that allows user to specify an index value to delete. Method tests sequence
      to see if the index is valid and if successful, deletes the value. Updated sequence
      is then displayed. If the specified index does not exist an error is displayed informing the user.
   */    
   public static void delAtIndex(char active, DoubleArraySeq seqA, DoubleArraySeq seqB)  
   {
      int target;
      System.out.println("Value at the index you choose will be deleted.");
      target=getInt();
      if (active=='A')
         if (target > (seqA.size()-1))
            System.out.println("Sequence A does not have that many elements. Operation cancelled.");
         else
         {
            seqA.setCurrent(target);
            seqA.removeCurrent();
            System.out.println("Element removed.");
            printSeq(seqA,active);
         }
      else
         if (target > (seqB.size()-1))
            System.out.println("Sequence B does not have that many elements. Operation cancelled.");
         else
         {
            seqB.setCurrent(target);
            seqB.removeCurrent();
            System.out.println("Element removed.");
            printSeq(seqB,active);
         }
   }
   /* 
      Method that allows user to specify an index value to view. Method tests sequence
      to see if the index is valid and if successful, displays the value. If the specified
      index does not exist an error is displayed informing the user.
   */   
   public static void showAtIndex(char active, DoubleArraySeq seqA, DoubleArraySeq seqB)     
   {
      int target;
      System.out.println("Which index do you want to display?");
      target=getInt();
      if (active=='A')
         if (target > (seqA.size()-1))
            System.out.println("Sequence A does not have that many elements. Operation cancelled.");
         else
         {
            seqA.setCurrent(target);
            System.out.println("Index #" + target + " of sequence A is " + seqA.getCurrent() ); 
         }
      else
         if (target > (seqB.size()-1))
            System.out.println("Sequence B does not have that many elements. Operation cancelled.");
         else
         {
            seqB.setCurrent(target);
            System.out.println("Index #" + target + " of sequence A is " + seqA.getCurrent() );  
         }
   }
   /* 
      Method that deletes the first element of a non-empty sequence and then
      displays the updated sequence. If the sequence is empty an error message 
      is displayed and no action is taken on the sequence.
   */   
   public static void delFirst(char active, DoubleArraySeq seqA, DoubleArraySeq seqB)     
   {
      if (active=='A')
         if (seqA.size() !=0)
         {
            seqA.removeFront();
            printSeq(seqA,active);
         }
         else
            System.out.println("Sequence A is already empty. Operation cancelled.");
      else
         if (seqB.size() !=0)
         {
            seqB.removeFront();
            printSeq(seqB,active);
         }
         else
            System.out.println("Sequence B is already empty. Operation cancelled.");
     
   }
   /* 
      Method that creates and displays a clone of the active sequence if it is not empty. 
      If the sequence is empty an error message is displayed and no clone is created.
   */   
   public static void makeClone(DoubleArraySeq seqA, DoubleArraySeq seqB, DoubleArraySeq copy, char active)
   {
      if(active == 'A' && seqA.size() != 0)
      {
         copy = seqA.clone();
         System.out.println("Sequence A successfully cloned!\n" + "Clone Sequence: " + copy);
      }
      else if(active == 'B' && seqB.size() != 0)
      {
         copy = seqB.clone();
         System.out.println("Sequence B successfully cloned! \n" + "Cloned Sequence: " + copy);
      }
      else
      {
         System.out.println("Error! Current sequence is empty, please enter data into the sequence to be cloned.");
      }
                         
   }
   /* 
      Method for concatinating sequenceB and sequenceA together. Checks first to see if
      the sequences are empty, and prints an error message if they are. If they are not
      then the 2 sequences are concatanated using DoubleArraySeq.concatenation() and 
      the result is printed.
   */
   public static void concat(DoubleArraySeq seqA, DoubleArraySeq seqB)
   {
      DoubleArraySeq catSequence;
      if((seqA.size()==0) || (seqB.size()==0))
      {
         System.out.println("Cannot concatinate an empty sequence");
      }
      else
      {
         catSequence = DoubleArraySeq.concatenation(seqB,seqA);
         System.out.print("SeqeunceB concatenated with SequenceA yields ");
         System.out.println(catSequence);
      }
   }
   /*
      Method for adding an element after another. Checks to see if active sequence is empty,
      and searches for the index position in the seqeunce using search(). If sequence is empty 
      or element is not found an error is printed to the screen. If it is found, desired number 
      to add to sequence is received, and added to active sequence using addAfter().
    */
   public static void addingAfter(DoubleArraySeq seqA, DoubleArraySeq seqB, char active)
   {
      int index = 0;
      double newData=0;
      System.out.println("Please enter which number you would like to add after");
      newData = getNum();
                 
      if(active == 'A'&& seqA.size() != 0)
      {
         index = search(seqA,newData);
         if(index != -1)
         {
            System.out.println("Number found! Enter the number you would like to add after it");
            newData = getNum();
            seqA.setCurrent(index);
            seqA.addAfter(newData);
            System.out.println("Element Succesfully Added!\n" + "Sequence A: " + seqA);
         }
         else
         {
            System.out.println("Error! Number you wish to add before does not exist!");
         }
                     
      } 
      else if(active == 'B' && seqB.size() != 0)
      {
         index = search(seqB,newData);
                     
         if(index != -1)
         {
            System.out.println("Number found! Enter the number you would like to add after it");
            newData = getNum();
            seqB.setCurrent(index);
            seqB.addAfter(newData);
            System.out.println("Element Succesfully Added!\n" + "Sequence B: " + seqB);
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
   }
   /*
      Method for adding an element before another. Checks to see if active array is
      currently empty, then uses search method to find the index element is at. If 
      element is not found an error is printed to the screen. If it is addBefore is 
      used to add the desired number before the selected position 
   */
   public static void addingBefore(DoubleArraySeq sequenceA, DoubleArraySeq sequenceB, char active)
   {
      int index = 0;
      double newData=0;
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
   }
   
} // end code