import java.util.*;


public class AiPlayer {
	/**
	 * The constructor essentially does nothing except instantiate an AiPlayer
	 * object.
	 *
	 */
	int depth;
	
	public AiPlayer(int depth){
		
		this.depth=depth;
	}

	/**
	 * This method plays a piece randomly on the board
	 * @param currentGame The GameBoard object that is currently being used to play
	 *                    the game.
	 * @return an integer indicating which column the AiPlayer would like to play
	 *         in.
	 */
	public int findBestPlay(GameBoard currentGame) {
		int playChoice = 99;		
		if (currentGame.getCurrentTurn() == 1) {
			int a;
			int value=1000000;
			for (int i = 0; i < 7; i++) {
				if (currentGame.isValidPlay(i)) {
					GameBoard presentMove = new GameBoard(currentGame.getGameBoard());
					presentMove.playPiece(i);
					a = max_Utility_Value(presentMove, depth, -1000000, 1000000);
					if ( a<value) {
						playChoice = i;
						value = a;
					}
				}
			}
		} else {
			int  a;
			int  value=-1000000;
			for (int i = 0; i < 7; i++) {
				if (currentGame.isValidPlay(i)) {
					GameBoard presentMove = new GameBoard(currentGame.getGameBoard());
					presentMove.playPiece(i);
					a = min_Utility_Value(presentMove, depth, -1000000, 1000000);
					if (a>value) {
						playChoice = i;
						value = a;
					}

				}
			}
		}

		return playChoice;
	}  


	public int max_Utility_Value(GameBoard gBoard,int depth,int alpha,int beta) {
		if((gBoard.getPieceCount()<42)&& depth >0) {
			int pruned =-100000;
			int val;
			for(int i=0;i<7;i++) {
				if(gBoard.isValidPlay(i)) {
					GameBoard presentMove1= new GameBoard(gBoard.getGameBoard());
					presentMove1.playPiece(i);
					val=min_Utility_Value(presentMove1,depth-1,alpha,beta);
					if(pruned<val) {
						pruned=val;
					}
					if(pruned>=beta) {
						return pruned;
					}
					if(pruned>alpha) {
						alpha=pruned;

					}
				}
			}
			return pruned;
		}

		else{
			
				return (gBoard.eval_func(2)*100)-(gBoard.eval_func(1)*100); 	
					
		}
	}
	public int min_Utility_Value(GameBoard gBoard,int depth,int alpha,int beta) {
		if((gBoard.getPieceCount()<42)&& depth >0) {
			int pruned1 =100000;
			int val;
			for(int i=0;i<7;i++) {
				if(gBoard.isValidPlay(i)) {
					GameBoard presentMove1= new GameBoard(gBoard.getGameBoard());
					presentMove1.playPiece(i);
					val=max_Utility_Value(presentMove1,depth-1,alpha,beta);
					if(pruned1>val) {
						pruned1=val;
					}
					if(pruned1<=alpha) {
						return pruned1;
					}
					if(pruned1<beta) {
						beta=pruned1;

					}
				}
			}
			return pruned1;
		}

		else{
		
				return (gBoard.eval_func(2)*100)-(gBoard.eval_func(1)*100); 	
					
		}
	}

}
