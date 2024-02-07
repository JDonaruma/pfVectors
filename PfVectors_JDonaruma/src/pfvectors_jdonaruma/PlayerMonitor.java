// *****************************************************************************************************
// PfVectors_JDonaruma
// This program uses a Vectors to manipulate information about players and their stats
// Author: Julie Donaruma
// Date: 7-10-2023
// *****************************************************************************************************


package pfvectors_jdonaruma;

import javax.swing.*;
import java.util.Vector;

public class PlayerMonitor
{
    //Global Declarations

    public static Vector<Player> playerList;
    static Player highestSpeed, lowestSpeed;
    static int totalLvl;
    static double totalSpd;
    final static String HEADING = "The Player Monitor by Julie Donaruma";

    public static void main(String[] args)
    {
        //declare variables
        boolean exitTime = false;
        int userOption, playerListSize;
        playerList = new Vector(0);

        //Main Operation
        while(!exitTime) //while user wishes to continue
        {
            //prompt user with menu
                // *****************************************************************************************************
                // to do ADD MODIFY DATA
                // *****************************************************************************************************
                userOption = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "\n1. Enter Player info" +
                                "\n2. Provide Summary" +
                                "\n3. Provide List" +
                                "\n4. Query a Player" +
                                "\n5. Check List Size" +
                                "\n6. Empty List" +
                                "\n7. Exit"
                                , HEADING, JOptionPane.QUESTION_MESSAGE));
                playerListSize= playerList.size();
                switch(userOption)
                {
                    case 1: inputPlayer(); //Obtain info for a new player
                        break;
                    case 2: if(playerListSize > 0) summarizePlayers(); //Analyze Player and output summary
                        break;
                    case 4: if (playerListSize > 0) queryPlayer();
                        break;
                    case 5: checkSize();
                        break;
                    case 6: if(playerListSize > 0) empty();
                        break;
                    case 7: exitTime = true;
                        break;
                    default: break;
                } //End switch
                if(userOption == 3)
                {
                    userOption = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "\n1. Provide Sorted List by Attack" +
                                    "\n2. Provide Sorted List by Defense" +
                                    "\n3. Provide Sorted List by Speed" +
                                    "\n4. Provide Unsorted List " +
                                    "\n9. Go Back"
                                    , HEADING, JOptionPane.QUESTION_MESSAGE));
                    
                    switch(userOption)
                    {
                        case 1: if(playerListSize > 0) displaySortedByAtk();
                           break;
                        case 2: if(playerListSize > 0) displaySortedByDef();
                           break;
                        case 3: if(playerListSize > 0) displaySortedBySpd();
                           break;
                        case 4: if(playerListSize > 0)
                        {
                            playerList.size();
                            Player[] thisList = playerList.toArray(new Player[playerList.size()]);
                            displayList(thisList);
                        } //End if statement
                           break;
                        default: break;
                    } //End switch
                } //End if statement

            
        } //End while loop

    } //End driver

    private static void inputPlayer() //add data for new Players
    {
        //declare local variables
        int pLimit;
        Player currentPlayer;

        pLimit = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Please Enter Number of Players to Enter for"
                        ,HEADING,JOptionPane.QUESTION_MESSAGE));

        playerList.ensureCapacity(playerList.size() + pLimit); //Ensure size of list

        //for each player create instance and input its data
        for(int x = 1; x <= pLimit; x++)
        {
            currentPlayer = new Player();
            currentPlayer.inputData(x);
            playerList.add(x-1, currentPlayer);
        } //End for loop
    } //End of inputPlayer()

    private static void summarizePlayers()
    {
        //declare local variables
        String outputS = " ";
        int thisLim;
        Player[] thisList = playerList.toArray(new Player[playerList.size()]);
        thisLim = thisList.length;

        initializeSummary();

        for(int j = 1; j<= thisLim; j++) //loop to increment speed and level
        {
            totalLvl += thisList[j-1].getLevel(); //Increment Level
            totalSpd += thisList[j-1].getSpeed(); //Increment Speed
            highLow(thisList[j-1]);
        } //End for loop

        //Calculate Average
        int averageLvl = totalLvl / thisLim;
        double averageSpeed = totalSpd / thisLim;

        //append info to output
        outputS = "Summary of The current Player Roster \n\n";
        outputS += "~~~~~~~~~~~~~~~~~~~~~\n";
        outputS += "Fastest Speed in Players: \n" + highestSpeed.printMe();
        outputS += "\n~~~~~~~~~~~~~~~~~~~~~\n";
        outputS += "Lowest Speed in Players: \n" + lowestSpeed.printMe();
        outputS += "Average Level: " + averageLvl + "\n";
        outputS += "Average Speed: " + averageSpeed + "\n";

        //display output
        JOptionPane.showMessageDialog(null, outputS, HEADING, JOptionPane.INFORMATION_MESSAGE);
    } //End of summarizePlayers()

    private static void highLow(Player thisOne) //Calculate which player has Highest and lowest speed.
    {
        if(thisOne.getSpeed() > highestSpeed.getSpeed())
        {
            highestSpeed.modifyMe(thisOne);
        } //End if statement
        if(thisOne.getSpeed() < lowestSpeed.getSpeed())
        {
            lowestSpeed.modifyMe(thisOne);
        } //End if statement
    } //End highLow()

    private static void initializeSummary() //initialize variables for Summary
    {
        totalLvl = 0;
        totalSpd = 0.0;
        highestSpeed = new Player();
        lowestSpeed = new Player();
        highestSpeed.setSpeed(0.00);
        lowestSpeed.setSpeed(20.00);
    } //End of initializeSummary()

    private static void displaySortedByAtk()
    {
        //create a dummy player to store data to swap them around
        Player dummyP = new Player();
        Player[] thisList = playerList.toArray(new Player[playerList.size()]);
        int lim = thisList.length;


        //Sort array based on Attack value
        for(int counter1 = 1; counter1 <= lim; counter1++)
        {
            for(int counter2 = counter1 + 1; counter2 <= lim; counter2++)
            {
                //If greater swap
                if(thisList[counter1-1].getAtk() >  thisList [counter2 - 1].getAtk())
                {
                    dummyP.modifyMe(thisList[counter1 - 1]);
                    thisList[counter1 - 1].modifyMe(thisList[counter2-1]);
                    thisList[counter2 - 1].modifyMe(dummyP);
                } //End if statement
            }//End counter 2 for loop
        }//End counter 1 for loop
        displayList(thisList);


    } //End of displaySortedByAtk()

    private static void displaySortedByDef()
    {
        Player dummyP = new Player();
        Player[] thisList = playerList.toArray(new Player[playerList.size()]);
        int lim = thisList.length;


        //Sort array based on Attack value
        for(int counter1 = 1; counter1 <= lim; counter1++)
        {
            for(int counter2 = counter1 + 1; counter2 <= lim; counter2++)
            {
                //If greater swap
                if(thisList[counter1-1].getDef() >  thisList [counter2 - 1].getDef())
                {
                    dummyP.modifyMe(thisList[counter1 - 1]);
                    thisList[counter1 - 1].modifyMe(thisList[counter2-1]);
                    thisList[counter2 - 1].modifyMe(dummyP);
                } //End if statement
            }//End counter 2 for loop
        }//End counter 1 for loop
        displayList(thisList);

    } //End of displaySortedByDef()
    private static void displaySortedBySpd()
    {
        Player dummyP = new Player();
        Player[] thisList = playerList.toArray(new Player[playerList.size()]);
        int lim = thisList.length;


        //Sort array based on Attack value
        for(int counter1 = 1; counter1 <= lim; counter1++)
        {
            for(int counter2 = counter1 + 1; counter2 <= lim; counter2++)
            {
                //If greater swap
                if(thisList[counter1-1].getSpeed() >  thisList [counter2 - 1].getSpeed())
                {
                    dummyP.modifyMe(thisList[counter1 - 1]);
                    thisList[counter1 - 1].modifyMe(thisList[counter2-1]);
                    thisList[counter2 - 1].modifyMe(dummyP);
                } //End if statement
            }//End counter 2 for loop
        }//End counter 1 for loop
        displayList(thisList);
        
    } //End of displaySortedBySpd()

    private static void displayList(Player[] thisList)
    {
        int thisLim = thisList.length;
        String outputS = " ";

    //append each object in this list to output
        for(int x = 1; x <= thisLim; x++)
        {
            outputS += thisList[x-1].printMe();
        } //End For Loop

        JOptionPane.showMessageDialog(null, outputS, HEADING, JOptionPane.INFORMATION_MESSAGE);
    } //End of displayList()

    private static void queryPlayer()
    {
        //Global Declarations
        String outString , searchID;
        int nextUserAction;
        Player searchPlayer, foundPlayer;
        boolean exitTime = false;
        boolean exitNow;
        int thisLim = playerList.size();

        //Prompt for which then check if in the list.
        if(thisLim > 0) //If there are Players
        {
            while(!exitTime) //While more processes required
            {
                searchID = JOptionPane.showInputDialog(null, "Specify UID of the Player to look up: ", HEADING, JOptionPane.QUESTION_MESSAGE);
                foundPlayer = new Player();

                //Search thisList for the Country Object, then return it
                exitNow = false;
                for(int x = 1; (x <= thisLim) && (!exitNow); x++)
                {
                    if(searchID.equals((playerList.get(x-1)).getUid()))
                    {
                        foundPlayer.modifyMe(((Player) playerList.get(x-1)));
                        exitNow = true;
                    } //End If Statement
                } //End For Loop

                if((!foundPlayer.getUid().equals(" ")))
                {
                    outString = foundPlayer.printMe();
                } //End If Statement
                else
                {
                    outString = "Player specified not in list.";
                }// End Else Statement
                JOptionPane.showMessageDialog(null,outString,HEADING,JOptionPane.INFORMATION_MESSAGE);

                //Check whether user wishes to continue
                nextUserAction = JOptionPane.showConfirmDialog(null,"Click Yes to query another. click No or Cancel to exit");
                if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION))
                {
                    exitTime = true;
                } //End If Statement
            }//End exitTime While
        }//End if statement
    } //End of queryPlayer()

    private static void checkSize()
    {
        JOptionPane.showMessageDialog(null, "There are " + playerList.size() + " Players", HEADING, JOptionPane.INFORMATION_MESSAGE);
    } //End of checkSize()
    private static void empty()
    {
        int nextUserAction;
        String removalPrompt = "You are about to empty the list. Click Yes to empty. Click No or Cancel to exit";
        nextUserAction = JOptionPane.showConfirmDialog(null,removalPrompt);
        if(nextUserAction == JOptionPane.YES_OPTION)
        {
            playerList.clear();
        }
    } //End of Empty()
} //END CLASS