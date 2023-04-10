import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;                            //Here A Random Library is imported to generate Random numbers
import java.util.concurrent.TimeUnit;               //Used to trigger a delay, giving time for competitors to check scores
// SHAHRIAR RAHMAN

public class Server {
    //Here, private Server & Socket have been initialized as NULL values
    //Port is set to 8080
    private static ServerSocket server = null;
    private static Socket socket = null;
    private static final int port = 8080;

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
        //Create IO Objects
        BufferedReader in = null;       //IN is used to display the inputs typed by the Clients in the Server's Console
        PrintWriter out = null;         //OUT is used to display the message of the Server in the Client's Console
        Scanner consoleInput = new Scanner(System.in);  //Scanner is used to  take Input via Console
        //Start Server
        try {
            System.out.println("Server Start in Progress . . . ");
            server = new ServerSocket(port);                    //Created a New Server using Port Address
            System.out.println("Server has started");}
        catch (IOException e) {
            System.out.println("Can not listen to port: " + port); System.exit(-1); }
        //Create Socket
        while(true) {
            try {
                socket = server.accept();
                System.out.println("A Client has been connected\n"); }
            catch (IOException e) {
                System.out.println("Communication Error with Client");
                        System.exit(-1);}
            try {
                in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );                                                     //Created IN object via Socket.getInputStream
                out = new PrintWriter(socket.getOutputStream(),
                        true);                                 //Created OUT object via Socket.getOutputStream
                System.out.println("Welcome to Rock, Paper & Scissor Session");
                System.out.println("Server: Please type your Name below");
                your_name = consoleInput.nextLine();
                out.println(your_name);                   //Here Server will write their Name to the Player
                opponent_name =in.readLine();
                System.out.println("Player Name: " + opponent_name);    //Takes Player Name from the Client's Console

                while( true ) {
                    try{
                        switch(RandomNumberGenerator()){
                            case 1: current_move = "Rock";break;
                            case 2: current_move = "Paper"; break;
                            case 3: current_move = "Scissor"; break;
                            default:
                        }
                        System.out.print(opponent_name+"'s Move is:  ");
                        opponent_move = in.readLine();
                        System.out.println(opponent_move);                  //Displays the Console Input from the Clients/Players in Server Console
                        System.out.println(your_name+"'s Move is: " + current_move);
                        out.println(current_move);                          //Displays the Console Input to the Clients/Players
                        if(current_move=="Rock"){
                            if (opponent_move.equals("Rock")){
                                System.out.println("Its A Draw!");
                            }
                            else if(opponent_move.equals("Paper")){
                                System.out.println(opponent_name+" Wins this Round (Client)");
                            }
                            else if(opponent_move.equals("Scissor")){
                                System.out.println(your_name+" Win this Round (Server)");
                            }
                        }
                        else if(current_move.equals("Paper")){
                            if (opponent_move.equals("Paper")){
                                System.out.println("Its A Draw!");
                            }
                            else if(opponent_move.equals("Rock")){
                                System.out.println(your_name+" Win this Round (Server)");
                            }
                            else if(opponent_move.equals("Scissor")){
                                System.out.println(opponent_name+" Wins this Round (Client)");
                            }
                        }
                        else if(current_move.equals("Scissor")){
                            if (opponent_move.equals("Scissor")){
                                System.out.println("Its A Draw!");
                            }
                            else if(opponent_move.equals("Rock")){
                                System.out.println(opponent_name+" Wins this Round (Client)");
                            }
                            else if(opponent_move.equals("Paper")){
                                System.out.println(your_name+" Win this Round (Server)");
                            }
                        }
                        int waiting_in_lobby = 10;
                        System.out.print("Round Ended, Next Match starts in ");
                        while(waiting_in_lobby!=0){
                            System.out.println(waiting_in_lobby+" seconds");
                            Thread.sleep(1000);
                            waiting_in_lobby--;
                        }
                    }
                    catch (IOException | InterruptedException e) {
                        System.out.println("Connection Lost. Waiting for Players..");
                        Thread.sleep(10000);
                        consoleInput.close(); }
                }
            }
            catch (IOException | InterruptedException x) {
                System.out.print("A Client Left");
                consoleInput.close(); }
        }  //while ends
    }
}
