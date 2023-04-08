import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class Course {
   
    public double totalMark;
    public double[] parts;
    
public Course(int parts){
    this.totalMark=0;
    this.parts= new double[parts];
}

public void avg(int i){
    //returns the average mark of a part of the grade, where i is the index that indicates which part will be altered
    Scanner itemslook= new Scanner(System.in);
    System.out.println("How many items are in consideration?");
    double items=itemslook.nextDouble();
    double grades=0;
    for (int w=0; w<items; w++){
        System.out.println("What is the grade of the this item? (0-100)");
        double grade=itemslook.nextDouble();
        while (grade<0|grade>100){
            System.out.println("Value out of bounds, try again");
            grade=itemslook.nextDouble();
        }
        grades=grades+grade;
    }
    grades=grades/items;
    System.out.println("The average is "+grades);
    parts[i]=grades;
}

public void gradeforPart(int i, double x){
    //receives the uncalculated mark (x) for this part (i) and puts it in the array
    parts[i]=x;
}

public void addToTotal(int i, double n){
    //returns the mark for the part of the grade, should accept and return a value between 0 and 100
    //don't return anything, rather the number gets added to the total grade
    //i is the part of the grade, and n is the value that that part is worth
    double markConsidered;
    if (i<0|i>parts.length){
        throw new IllegalArgumentException("You do not have this many parts to your grade");
    }
    markConsidered=weightedMark(n, parts[i]);
    totalMark=totalMark+markConsidered;
}

public double weightedMark(double n, double x){
    //weighs a mark, with n as the weight of a value between 0-100 and x as the value to be changed
    if (n<1|n>100){
        throw new NumberFormatException("Value out of bounds");
    }
    double returnValue;
    n=n/100;
    returnValue=x*n;
    return returnValue;
}


public double finalGrade(double absolute, double w){
    //should accept a value between 0-100 (n)
    //returns the mark needed to achieve that number
    //w is the weight of the final
    if (absolute<0|absolute>100){
        throw new IllegalArgumentException("This is an impossible grade");
    }
    if (w<1|w>100){
        throw new NumberFormatException("Value out of bounds");
    }
    w=w/100;
    double finalweighed=absolute-totalMark;
    double finalgr=finalweighed/w;


    return finalgr;
}

public static void main(String[] args){
    System.out.println("This code will help calculate your mark for a certain course");
    System.out.println("It can also help to calculate what mark you need on your final to achieve a certain grade");
    System.out.println("Please bear in mind that any number you type in should be between 0-100 and should have no '%' attached");
    Scanner looker=new Scanner(System.in);
    System.out.println("Press 0 if you want to calculate your marks, and 1 if you want to calculate your final grade");
    int yesorno=looker.nextInt();

    while (yesorno>1|yesorno<0){
        System.out.println("This is not an accepted value. Try again");
        yesorno=looker.nextInt();
    }
    while (yesorno==1|yesorno==0){
    if (yesorno==0){
        System.out.println("How many parts to the grade are for this course? (ie: labs, finals, midterms, assignments)");
        System.out.println("This will be in your syllabus");
        int number=looker.nextInt();
        Course corpse= new Course(number);

        for (int i=0; i<number; i++){
            System.out.println("PART"+(i+1));
            System.out.println("Do you need help calculating the average for this part? Write 0 for 'no' and 1 for 'yes'");
            yesorno=looker.nextInt();
            while (yesorno>1|yesorno<0){
                System.out.println("This is not an accepted value. Try again");
                yesorno=looker.nextInt();
            }
            if (yesorno==1){
                corpse.avg(i);
            }
            else{
                System.out.println("What is the grade for this part?");
                double grade=looker.nextDouble();
                corpse.gradeforPart(i, grade);
            }
            System.out.println("What is the weight for this part?");

            //may cause exception
            double weight=looker.nextDouble();
            int argumentfixer=1;
            while (argumentfixer==1){
                try{corpse.addToTotal(i, weight); argumentfixer=0;}
                catch(NumberFormatException e){
                    System.out.println(e);
                    System.out.println("Please type another value");
                    weight=looker.nextDouble();
                    argumentfixer=1;
            }
        }
        
        }
        System.out.println("Your grade for this course is"+corpse.totalMark);
    }


    ///

    if (yesorno==1){
        System.out.println("How many parts to the grade are for this course EXCLUDING the final? (ie: labs, midterms, assignments)");
        System.out.println("This will be in your syllabus");
        int number=looker.nextInt();
        Course corpse=new Course(number);

        for (int i=0; i<number; i++){
            System.out.println("PART"+(i+1));
            System.out.println("Do you need help calculating the average for this part? Write 0 for 'no' and 1 for 'yes'");
            yesorno=looker.nextInt();
            while (yesorno>1|yesorno<0){
                System.out.println("This is not an accepted value. Try again");
                yesorno=looker.nextInt();
            }
            if (yesorno==1){
                corpse.avg(i);
            }
            else{
                System.out.println("What is the grade for this part?");
                double grade=looker.nextDouble();
                corpse.gradeforPart(i, grade);
            }
            System.out.println("What is the weight for this part?");
            double weight=looker.nextDouble();
            int argumentfixer=1;
            while (argumentfixer==1){
                try{corpse.addToTotal(i, weight); argumentfixer=0;}
                catch(NumberFormatException e){
                    System.out.println(e);
                    System.out.println("Please type another value");
                    weight=looker.nextDouble();
                    argumentfixer=1;
            }
            
        }
    
        corpse.addToTotal(i, weight);
        //System.out.println("NEXT PART");
    }
        System.out.println("What is the mark you'd like to finish the course with?");
        double absoluteMark=looker.nextDouble();
        System.out.println("What is the weight of your final?");
        
        //may throw an exception
        double weightf=looker.nextDouble();
        int argumentfixer=1;
        while(argumentfixer==1){
            try{double finalgrade=corpse.finalGrade(absoluteMark, weightf); argumentfixer=0;}
            catch(NumberFormatException e){
                    System.out.println(e);
                    System.out.println("Please type another value for final weight");
                    weightf=looker.nextDouble();
                    argumentfixer=1;
            }
            catch(IllegalArgumentException e){
                System.out.println(e);
                    System.out.println("Please type another value for the grade wanted");
                    absoluteMark=looker.nextDouble();
                    argumentfixer=1;
            }
        }
        double finalgrade=corpse.finalGrade(absoluteMark, weightf);
        if (finalgrade>100){
            System.out.println("Sorry, but your expectations are too high. It's impossible to achieve this mark");
        }
        else if (finalgrade<0){
            System.out.println("You don't need to take the final to achieve this grade. Your grade can only go up");
        }
        else{
            System.out.println("You need "+finalgrade+" to get a "+ absoluteMark + "in this course");
        }
    }
    System.out.println("Would you like to do a different course?");
    System.out.println("Press 0 for calculating marks, 1 for final grading, and 2 to kill the program");
    yesorno=looker.nextInt();
}
System.out.println("Have a great day!");
}
}
