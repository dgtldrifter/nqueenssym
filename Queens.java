// javac Queens.java StdDraw.java
// java Queens 10
class Queens
{
    void draw(int[] board)
    {
        int n = board.length;
        StdDraw.setXscale(0, n+2);
        StdDraw.setYscale(0, n+2);      
        for (int x=1; x<=n; x++) {
            for (int y=1; y<=n; y++) {
                if ((y % 2) != 0) {
                    if ((x % 2) != 0)  {
                        StdDraw.setPenColor(StdDraw.GRAY);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                    else {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                }
                else {
                    if ((x % 2) != 0)  {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                    else {
                        StdDraw.setPenColor(StdDraw.GRAY);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                }
            }
        }
        StdDraw.show();
        StdDraw.pause(200);   
        StdDraw.setPenColor(StdDraw.RED);
        for (int x=0; x<n; x++) {
            StdDraw.filledCircle(x+1+0.5, board[x]+1+0.5, 0.375);
            StdDraw.show();
            StdDraw.pause(200);
        }
    }
    // add your code here
    public boolean solution(int boardSize, int[] board, int current){
    	boolean isDone=false;
    	if(boardSize == current){
    		Queens queen = new Queens();
    		queen.draw(board);
    		for(int i =0; i < boardSize; i ++){
    			System.out.print(board[i]+",");
    		}    		
    		return true;
    	}
    	else{   		
    		for(int col= 0; col <boardSize; col++){
        		board[current] = col;
        		if(isSafe(board,current)){
        			isDone =solution(boardSize, board, current+1);
        			if(isDone)
        				return true;
        		}	
        	}
    		return false;
    	}
    }    
    
    public boolean solutionSymEven(int boardSize, int[] board, int current){
    	boolean isDone=false;
    	if(boardSize/2 == current){
    		if(!isDone){
    			Queens queen = new Queens();
        		flipEven(boardSize, board, current);
        		if(allSafeInsurance(board, boardSize)){
        			System.out.println("DONE!");
        			StdDraw.pause(1000);
            		queen.draw(board);
        			return true;
    		}
    		}
    		
    	}
    	else{   		
    		for(int col= 0; col <boardSize; col++){
        		board[current] = col;
        		if(isSafe(board,current)){
        			isDone =solutionSymEven(boardSize, board, current+1);
        			if(isDone){
        				return true;
        			}
        				
        		}	
        	}
    		return false;
    	}
		return isDone;
    }    
    
    public boolean solutionSymOdd(int boardSize, int[] board, int current){
    	boolean isDone=false;
    	if((boardSize/2)+1 == current){
    		if(!isDone){
    			Queens queen = new Queens();
        		flipOdd(boardSize, board, current);
        		if(allSafeInsurance(board, boardSize)){
        			System.out.println("DONE!");
        			StdDraw.pause(1000);
            		queen.draw(board);
        			return true;
    		}
    		}
    		//return false;
    	}
    	else{   		
    		for(int col= 0; col <boardSize; col++){
        		board[current] = col;
        		if(isSafe(board,current)){
        			isDone =solutionSymOdd(boardSize, board, current+1);
        			if(isDone){
        				return true;
        			}
        				
        		}	
        	}
    		return false;
    	}
		return isDone;
    }    
  
    private boolean allSafeInsurance(int[] board, int boardSize) {
		for (int i = 0; i < boardSize; i ++){
			for (int j = 0; j < i;  j++){
				if(board[i] == board[j] || i - j == Math.abs(board[i]-board[j])){
					return false;
				}
			}
		}
		return true;
	}
	private void flipEven(int boardSize, int[] board, int current) {
		for(int i = 0; i < current; i++){
	
				int distance = Math.abs((boardSize/2) - board[i]);
				if(board[i] > boardSize/2){
					int flipped = Math.abs(boardSize / 2  - 1 - distance);
					flopEven(i, board, boardSize, flipped);
				}
				else if (board[i] <= boardSize / 2){
					int flipped = Math.abs(boardSize / 2  - 1 + distance);
					flopEven(i, board, boardSize, flipped);
				}
				
		}
		
	}
	private void flopEven(int i, int[] board, int boardSize, int flipped) {
		int distance = Math.abs((boardSize/2) - i);
		int x= boardSize/2 + distance -1;
		board[x]= flipped;
	}
	
	private void flipOdd(int boardSize, int[] board, int current) {
		for(int i = 0; i < current; i++){
	
				int distance = Math.abs((boardSize/2) - board[i]);
				if(i == boardSize/2 +1){
					int flipped = boardSize/2 +1;
					flopOdd(i, board, boardSize, flipped);
				}
				else if(board[i] > (boardSize/2)-1){
					int flipped = Math.abs((boardSize / 2)+1 - 1 - distance);
					flopOdd(i, board, boardSize, flipped);
				}
				else if (board[i] < (boardSize / 2) +1){
					int flipped = Math.abs(boardSize / 2  + distance);
					flopOdd(i, board, boardSize, flipped);
				}	
		}
		
	}
	private void flopOdd(int i, int[] board, int boardSize, int flipped) {
		int distance = Math.abs(((boardSize/2)+1) - i);
		int x= boardSize/2 + distance -1;
		board[x]= flipped;
	}
	public boolean isSafe(int[] board, int current){
    		for(int i =0; i < current; i++){			 
        		if(board[i]==board[current] ||current -i == Math.abs(board[current]-board[i]))
        			return false;
        	}
        	return true;
    }
   static public void main (String args[])
   {
       if (args.length == 1) {
           int size = Integer.parseInt(args[0]);
           System.out.println("SIZE: " + size);
           Queens queen = new Queens();
           // add your code here
           int[] board = new int[size];
           if(size % 2 == 0){
        	   queen.solutionSymEven(size, board,0);
           }
           else{
        	   queen.solutionSymOdd(size, board,0);  
           }
       }
       else
           System.out.println("Please input the size of the board ...");
   }
}
