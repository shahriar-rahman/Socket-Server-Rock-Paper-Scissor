import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;                            //Here A Random Library is imported to generate Random numbers
//SHAHRIAR_RAHMAN

public class Client_1 {
    public static Socket socket = null;             //Socket Initialized as NULL
    public static int RandomNumberGenerator(){
        Random random = new Random();                       // An Object named Random is created
        int random_number = random.nextInt(3) +1;
        //This will generate Random integers in range 0 to 2, Then that number is added with 1 to give an integer between 1 to 3
        //This will generate Random integers in range 0 to 2, Then that number is added with 1 to give an integer between 1 to 3
        //Example - (0+1)=1 or (2+1)=3
        return random_number;
    }
    public static void main(String[] args) {
        String current_move = "";
        String opponent_move = "";
        String your_name = "";
        String opponent_name = "";
        try {
            socket = new Socket("localhost", 8080);             //Socket connected to the port where Server can listen for connections
            System.out.println("Connected to Game Server\n"
                    + "Socket: " + socket.getInetAddress() + ":" +
                    socket.getPort() + "\n" ); }
        catch (IOException e) {
            System.out.print("Connection to network can not be Etablished"); }

        BufferedReader in = null;                                        //IN is used to display the inputs typed by the Server in the Client's Console
        PrintWriter out = null;                                         //OUT is used to display the message of the Client in the Server's Console
        Scanner consoleInput = new Scanner(System.in);
        try {
            in = new BufferedReader( new InputStreamReader (socket.getInputStream() ));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Welcome to Rock, Paper & Scissor Session");
            System.out.println("Player: Please type your Name below");
            your_name = consoleInput.nextLine();
            out.println(your_name);                   //Player is prompted to type their Name to the Server
            opponent_name = in.readLine();
            System.out.println("Server: " + opponent_name);

            while(true) {
                switch(RandomNumberGenerator()){
                    case 1: current_move = "Rock";break;
                    case 2: current_move = "Paper"; break;
                    case 3: current_move = "Scissor"; break;
                    default:
                }
                System.out.println(your_name+"'s Move is: " + current_move);
                out.println(current_move);
                System.out.print(opponent_name+"'s Move is: ");
                opponent_move = in.readLine();
                System.out.println(opponent_move);
                if(current_move.equals("Rock")){
                    if (opponent_move.equals("Rock")){
                        System.out.println("Its A Draw!");
                    }
                    else if(opponent_move.equals("Paper")){
                        System.out.println(opponent_name+" Wins this Round (Server)");
                    }
                    else if(opponent_move.equals("Scissor")){
                        System.out.println(your_name+" Win this Round (Client)");
                    }
                }
                else if(current_move.equals("Paper")){
                    if (opponent_move.equals("Paper")){
                        System.out.println("Its A Draw!");
                    }
                    else if(opponent_move.equals("Rock")){
                        System.out.println(your_name+" Win this Round (Client)");
                    }
                    else if(opponent_move.equals("Scissor")){
                        System.out.println(opponent_name+" Wins this Round (Server)");
                    }
                }
                else if(current_move.equals("Scissor")){
                    if (opponent_move.equals("Scissor")){
                        System.out.println("Its A Draw!");
                    }
                    else if(opponent_move.equals("Rock")){
                        System.out.println(opponent_name+" Wins this Round (Server)");
                    }
                    else if(opponent_move.equals("Paper")){
                        System.out.println(your_name+" Win this Round (Client)");
                    }
                }
                int waiting_in_lobby = 10;
                System.out.print("Round Ended, Next Match starts in ");
                while(waiting_in_lobby!=0){
                    System.out.println(waiting_in_lobby+" seconds");
                    Thread.sleep(1000);
                    waiting_in_lobby--;
                }

                         //Used to trigger a delay, giving time for competitors to check scores
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}