// *****************************************************************************************************
// Pf Vectors
// This program uses a Vector to manipulate information about players and their stats
// Author: Julie Donaruma
// Date: 6-29-2023
// *****************************************************************************************************
package pfvectors_jdonaruma;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.text.NumberFormat;

// *****************************************************************************************************
// to do add classes (Druid, Ranger, Oracle,Assasin, Tempest, wizard, monk, warlock, barbarian, fighter, rogue )
// *****************************************************************************************************
public class Player
{
 // Define Data Items
 protected int pLevel, pAtk, pDef;
 protected String pUid, pUsername, pPass , pClass;
 protected double pSpeed, pSpd;
 public String PlayerHeading = "The Character Data Entry by Julie Donaruma";
 // Constructor
 public Player()
 { //set all variables to default variables.
  pUid = "";
  pUsername = "";
  pPass = "";
  pLevel = 1;
  pAtk = 0;
  pDef = 0;
  pSpeed = 0.0;
  pSpd = 0.00;
  pClass = "";
 } // End Constructor
  
 // Country Modification Method
    public void modifyMe(Player thisOne)
    {
        pUid = thisOne.pUid;
        pUsername = thisOne.pUsername;
        pPass = thisOne.pPass;
        pLevel = thisOne.pLevel;
        pAtk = thisOne.pAtk;
        pDef = thisOne.pDef;
        pSpeed = thisOne.pSpeed;
        pClass = thisOne.pClass;
    }//End of Modify Me Method

    public void inputData(int x)
    {
        //Declerations
        String inputUid, inputUName, inputPass, inputClass;
        int inputLevel, inputAtk, inputDef;
        double inputSpeed;

        //Ask User for data and send to the correct check to make sure was inputted in the proper format.
        inputUid = checkUid(JOptionPane.showInputDialog(null,"Please Enter User ID " + x + ":"
            ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);
        inputUName = checkUName(JOptionPane.showInputDialog(null,"Please enter Username " + x + ":"
            ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);
        inputPass = checkPass(JOptionPane.showInputDialog(null,"Please Enter Password " + x + ":"
            ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);
        inputClass = checkClass(JOptionPane.showInputDialog(null,"Please Enter Class for player " + x + ".\n Options: Druid, Ranger, Oracle, Assassin, Tempest, Wizard, Monk, Warlock, Barbarian, Fighter, Rogue."
                ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);
        inputLevel = checkLevel(JOptionPane.showInputDialog(null,"Enter level # for player " + x + ":"
            ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);
        inputAtk = checkStat(JOptionPane.showInputDialog(null,"Please enter Attack stat for player " + x + ":"
            ,PlayerHeading, JOptionPane.QUESTION_MESSAGE),1 , x);
        inputDef = checkStat(JOptionPane.showInputDialog(null,"Please enter Defense stat for player " + x + ":"
                ,PlayerHeading, JOptionPane.QUESTION_MESSAGE),2 , x);
        inputSpeed = checkSpeed(JOptionPane.showInputDialog(null,"Please enter Speed stat for player " + x + ":"
                ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);


        //Send input to group
        pUid = inputUid;
        pUsername = inputUName;
        pPass = inputPass;
        pClass = inputClass;
        pLevel = inputLevel;
        pAtk = inputAtk;
        pDef = inputDef;
        pSpeed = inputSpeed;
    } //End of Input Data Method


    public String printMe()
    { //Combine data to output into 1 String
        String printString = "Player ID: " + pUid + "-" + pUsername + "\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" +
                "Password: " + pPass + "\n" +
                "Class: " + pClass + "\n" +
                "Level: " + pLevel + "\n" +
                "Atk: " + pAtk + "\n" +
                "Def: " + pDef + "\n" +
                "Speed: " + pSpeed + "\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" ;
        return printString;
    } // End of Print Method


    private String checkClass(String classP, int x)
    {
        String classOut;
        while(!(classP.equals("Druid") || classP.equals("Ranger") || classP.equals("Oracle")|| classP.equals("Assassin")||
                classP.equals("Tempest")|| classP.equals("Wizard")|| classP.equals("Monk")||
                classP.equals("Warlock") || classP.equals("Barbarian") || classP.equals("Fighter") || classP.equals("Rogue")))
        {
           classP =  JOptionPane.showInputDialog(null, classP + " Invalid Class. Please re enter class for player " + x +
                    " from the following: \n Druid \n Ranger \n Oracle \n Assassin" +
                    "\n Tempest \n Wizard \n Monk \n Warlock \n Barbarian" +
                    "\n Fighter \n Rogue", PlayerHeading,JOptionPane.QUESTION_MESSAGE);
        }//End while loop
        classOut = classP;
        return classOut;
    }//end of checkClass
    public String checkUid(String uid , int x) //Checks given uid and makes fit criteria for system.
    {
        String uidOut;
        while(uid == null || uid.length() < 3 ) //check uid has data and is at least 3 long.
        {
            //re input if it does not meet while criteria and replaces invalid uid
            uid = JOptionPane.showInputDialog(null,"Id is too short (Must be 3+ characters) \n Please Enter User ID " + x + ":"
                    ,PlayerHeading, JOptionPane.QUESTION_MESSAGE);
        } //End while loop
        for(int i = 0; i < uid.length(); i++)  //looping to verify each character
        {
            char temp = uid.charAt(i);
            if ((!(Character.isDigit(temp))) && (!(Character.isLetter(temp)) ) ) //check uid is only letters and digits.
            {
                //ask for re input and restart the check method
                uidOut = checkUid(JOptionPane.showInputDialog(null,"Invalid ID \n Please Enter User ID " + x + ":"
                        ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);
                return uidOut;
            } //end if statement
        } // end for loop
        uidOut = uid;
        return uidOut;

    } //End of checkUid Method
    public String checkUName(String uName , int x) //Checks given Username meets requirements and outputs
    {
        String uNameOut;
        while(uName == null || uName.length() < 8  || uName.length() > 16 ) //chek uname is correct length requirements.
        {
            uName = JOptionPane.showInputDialog(null,"Username is too short/big (Must be 8 - 16 characters) \n Please Enter Username " + x + ":"
                    ,PlayerHeading, JOptionPane.QUESTION_MESSAGE);
        } //End while loop
        for(int i = 0; i < uName.length(); i++) //looping for each character
        {
            if ((!(Character.isDigit(uName.charAt(i)))) && (!(Character.isLetter(uName.charAt(i))) ) ) //check uname is letters and numbers only
            {
                uNameOut = checkUName(JOptionPane.showInputDialog(null,"Invalid Username (must use only letters and numbers) \n Please Enter Username " + x + ":"
                        ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);
                return uNameOut;

            } //End if statement
        } //End for loop
        uNameOut = uName;
        return uNameOut;

    } //End of checkUName Method
    public String checkPass(String pass , int x) //checks password meets criteria and output
    {
        String passOut;
        int count = 0;
        while(pass == null || pass.length() < 8  ) //check password is long enough
        {
            pass = JOptionPane.showInputDialog(null,"Password is too short Must be 8+  characters \n Please Enter Password " + x + ":"
                    ,PlayerHeading, JOptionPane.QUESTION_MESSAGE);
        } //End while loop
        for(int i = 0; i < pass.length() ; i++) //loop to check every char
        {
            if ((Character.isDigit(pass.charAt(i)))  ) //count how many numbers are in pass
            {
                count++;
            } //End if statement
        } //End for loop
        if(count < 2) //confirms there are at least 2 numbers or reasks to input
        {
            passOut = checkPass(JOptionPane.showInputDialog(null,"Invalid Password (must have at least 2 numbers) \n Please Enter Password " + x + ":"
                    ,PlayerHeading, JOptionPane.QUESTION_MESSAGE) , x);
            return passOut;
        } //End if statement
        passOut = pass;
        return passOut;

    }//End of checkPass Method
    public int checkLevel(String level , int x) //confirms level is in range.
    {
        int lvl = Integer.parseInt(level);
        while(lvl < 1 || lvl > 100) //asks for reinput if out of range
        {
            lvl = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid Level please enter a Level between 1 - 100. \n Please Enter Level for Player" +  x +  ":",
               PlayerHeading, JOptionPane.QUESTION_MESSAGE));
        }//End while loop
        return lvl;
    } //End of checkLevel Method

    public int checkStat(String stat, int diff , int x)  //confirm attack and defense in valid range
    {
        int  stats = Integer.parseInt(stat);
        while(stats < 10 || stats > 9999) //checks is in range and runs if not
        {
            if(diff == 1)//reinput for Attack invalid
            {
                stats = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid Attack stat please enter a number between 10 - 9999. \n Please Enter Attack for Player" +  x +  ":",
                        PlayerHeading, JOptionPane.QUESTION_MESSAGE));
            } //End if statement
            else //reinput for Defense invalid
            {
                stats = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid Defense stat please enter a number between 10 - 9999. \n Please Enter Defense for Player" +  x +  ":",
                        PlayerHeading, JOptionPane.QUESTION_MESSAGE));
            }  //End else statement
        } //End while loop
        return stats;
    } //End of checkStat Method
    public double checkSpeed(String speed, int x) //confirm speed in valid range
    {
        double speedLong , spd;
        speedLong = Double.parseDouble(speed);

        //Format speed to only have 2 digits after decimal
        NumberFormat formatter = new DecimalFormat("#0.00");
        spd = Double.parseDouble(formatter.format(speedLong));

        if (spd <= 0 || spd > 20) //checks and runs if speed out of range.
        {
            String temp;
            temp = JOptionPane.showInputDialog(null, "Invalid Speed please enter speed between (0.01  - 20) for player " + x + ".", PlayerHeading, JOptionPane.QUESTION_MESSAGE);
            spd = checkSpeed(temp ,  x);
        } //End if statement
        return(spd);

    } //End of checkSpeed Method

    public String getUid()
    {
        return pUid;
    } //End of getUid Method

    public String getUsername()
    {
        return pUsername;
    } //End of getUsername Method

    public String getPass()
    {
        return pPass;
    } //End of getPass Method

    public String getPClass()
    {
        return pClass;
    } //End of getPClass Method

    public int getLevel()
    {
        return pLevel;
    } //End of getLevel Method

    public int getAtk()
    {
        return pAtk;
    } //End of getAtk Method

    public int getDef()
    {
        return pDef;
    } //End of getDef Method

    public double getSpeed()
    {
        return pSpeed;
    } //End of getSpeed Method

    public void setSpeed(double spd) //set speed value
    {
        pSpeed = spd;
    }//end of setSpeed Method



} // End of Player
