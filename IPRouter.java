package com.company;

import java.util.Scanner;


/**
 *
 * @author Shiroya R.
 */
public class IProuter {
    //main method
    public static void main(String arg[])
    {
        //creating an scanner object to read inputs
        Scanner sc = new Scanner(System.in);
        //declaring variables
        int n;
        String ip;
        String s;
        System.out.print("Enter ip to search:");
        // storing the value of the value entered by user in string ip
        ip = sc.nextLine();

        System.out.print("Enter routing table size :");
        // storing the value of the value entered by user in int n
        n =sc.nextInt();

        System.out.println("Press enter----------");

        // storing the value of the value entered by user in int s
        s =sc.nextLine();

        String routing_table[]=new String[n];

        System.out.println("Enter Table contents :");
        System.out.println("Address/mask Next hop");

        //for loop to store the routing table entered by user
        for(int i=0;i<n;i++)
        {//System.out.println(i);
            routing_table[i]=sc.nextLine();

        }


        //splitting contents
        String ip_split[];
        //splitting the 32 bit ip by "."
        ip_split = ip.split("[.]");

        String rt_split[][] = new String[n][];
        // for loop to split the subnet mask by "./"
        for(int i=0;i<n;i++)
        {
            rt_split[i] = routing_table[i].split("[./ ]");
        }
        System.out.println("---------------------------");
        //for loop to split the ip entered by user and displays it
        System.out.print("IP Address: ");
        for (String e :ip_split)
        {

            System.out.print(e+" ");
        }System.out.println();
        // for loop to display the split ip of routing table
        System.out.println("Router Table: ");
        for(int i=0;i<n;i++)
        {
            for(String e : rt_split[i])
                System.out.print( e+" ");
            System.out.println();

        }
        System.out.println();

    //finding route by performing binary AND operation
        int i;
        for(i=0;i<n-1;i++)
        {
            // convert split subnet mask String to integer
            int k = Integer.parseInt(rt_split[i][4]);

            //first it reads the subnet k from the user provided ip
            int num[]={0,0,0,0};
            int l=7,c=0;

            while(k>0)
            {

                // since l starts with 7, the first if statement will be ignored the first time.
                if(l==0)
                // when l becomes 0 this if statement moves the process to next octet. There will only be maximum 4 octets in total
                {
                    //add 2^0 to the octet
                    num[c]=num[c]+1;
                    //to calculate the next octet, add 1 to c and l to 7
                    c++;
                    l=7;

                }

                else
                {
                    //l starts with 7 goes all the way down to 0 total 8 iterations, because there are 8 bits in 1 octet.
                    //until l=0, num[c] would be added to power function 2^l.
                    num[c]=num[c]+(int)Math.pow(2, l);
                    l--;
                    // when l becomes 0, it means the octet is completed.

                }
                // after every iteration, the value of k decreases by 1, when k becomes 0, the value which is stored in num[] is the bit pattern of routing table address
                k--;


            }


            int j=0;

            // a for loop that runs for split.length times which is 4 times; 4 octets
            for(j =0;j<ip_split.length;j++)
            {

                // Bit AND operation between input ip address and subnet mask
                int m = Integer.parseInt(ip_split[j])&num[j];

                //if m does not equal to the subnet mask, break!
                if(m!=Integer.parseInt(rt_split[i][j]))
                {

                    break;
                }

            }

            //Comparing network address with routing table

            //if it matches with any one, prints the route information
            if(j==ip_split.length)
            {

                System.out.println("Route to :"+rt_split[i][rt_split[i].length-2]+" "+rt_split[i][rt_split[i].length-1]);
                break;
            }

        }
        // if it matchs with none from routing table, prints the route information to default router
        if(i==n-1)
        {
            System.out.println("Route to :"+rt_split[i][rt_split[i].length-2]+" "+rt_split[i][rt_split[i].length-1]);

        }

    }

}