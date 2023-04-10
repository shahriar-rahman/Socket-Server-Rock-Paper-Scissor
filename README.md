# Socket-Server-Rock-Paper-Scissor
A socket server program which communicates with clients and the server

## Abstract
A socket server program named RockPaperScissorsServer is implemented, which communicates with players or clients. The Server, firstly, receives a name for the player. Let’s assume 1 is Rock, 2 is Paper and 3 is Scissors. The Server then receives any of the 3 number from the user, generate a random number between 1
to 3 itself and then apply the Rock Paper Scissors game logic (which is paper beats rock, rock beats scissors, scissors beats paper), Then it will tell the player if the player or server has won. Another client program is programmed to aloow communications with the server and is structured in such a way that  allows drop-in drop-out feature. In Layman's terms, any player can leave and another player can join at anytime.
