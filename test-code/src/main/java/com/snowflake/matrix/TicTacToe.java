package com.snowflake.matrix;

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

class TicTacToe {
    
    int[] rows;
    int[] cols;
    
    boolean[][] isDiagnol;
    boolean[][] isAntiDiagnol;
    
    int[] points = new int[]{1, -1};
       
    int diagnolCount = 0;
    int antiDiagnolCount = 0;
    
    int playerOneWinScore;
    int playerTwoWinScore;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        isDiagnol = new boolean[n][n];
        isAntiDiagnol = new boolean[n][n];
        
        playerOneWinScore = points[0] * n;
        playerTwoWinScore = points[1] * n;
        
        for(int i = 0, j = 0; i < n && j < n; i++, j++)
            isDiagnol[i][j] = true;
        
        for(int i = 0, j = n -1; i < n && j >= 0; i++, j--)
            isAntiDiagnol[i][j] = true;
                      
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        rows[row] += points[player-1];
        cols[col] += points[player-1];
        
        if(isDiagnol[row][col]) diagnolCount += points[player-1];
        if(isAntiDiagnol[row][col]) antiDiagnolCount += points[player-1];
        
        if(player == 1)
           if(rows[row] == playerOneWinScore || cols[col] == playerOneWinScore || (isDiagnol[row][col] && diagnolCount == playerOneWinScore) || (isAntiDiagnol[row][col] && antiDiagnolCount == playerOneWinScore)) return 1;
        
        if(player == 2)
            if(rows[row] == playerTwoWinScore || cols[col] == playerTwoWinScore || (isDiagnol[row][col] && diagnolCount == playerTwoWinScore) || (isAntiDiagnol[row][col] && antiDiagnolCount == playerTwoWinScore)) return 2;
        
        
        return 0;
    }
    
    
    
    private static class TicTacToe1 {
        
        
        class Player {
            //this array stores the count of this player's moves, for each row
            int[] rowSize;
    		//this array stores the count of this player's moves, for each column
            int[] colSize;
    		//this array stores the count of this player's moves, for each diagonal
            int[] diagSize;
            int n;
            
            public Player(int n) {
                this.n = n;
                rowSize = new int[n];
                colSize = new int[n];
                diagSize = new int[2];
            }
            
            public boolean move(int i, int j) {
    		    //increase moves's count for row i
                rowSize[i] ++;
    			//increase moves's count for column j
                colSize[j] ++;
                if(i == j) {
    			    //increase moves's count for diagonal 1
                    diagSize[0] ++;
                }
                if(i + j == n-1) {
    			    //increase moves's count for diagonal 2
                    diagSize[1] ++;
                }
    			//if the curr row, column or any of the 2 diagonals are completed by this player, 
    			//then this player wins
                return rowSize[i] == n || colSize[j] == n || diagSize[0] == n || diagSize[1] == n;
            }
            
        }
        
        Player player1;
        Player player2;

        /** Initialize your data structure here. */
        public TicTacToe1(int n) {
    	    //initialise player 1
            player1 = new Player(n);
    		//initialise player 2
            player2 = new Player(n);
        }
        
        /** Player {player} makes a move at ({row}, {col}).
            @param row The row of the board.
            @param col The column of the board.
            @param player The player, can be either 1 or 2.
            @return The current winning condition, can be either:
                    0: No one wins.
                    1: Player 1 wins.
                    2: Player 2 wins. */
        public int move(int row, int col, int player) {
            if(player == 1) {
                if(player1.move(row, col)) {
                    return 1;
                }
            }
            else if(player == 2) {
                if(player2.move(row,col)) {
                    return 2;
                }
            }
            
            return 0;
        }
    }

}



