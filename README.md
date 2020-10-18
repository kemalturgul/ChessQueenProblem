# ChessQueenProblem
This is a project about chess queen (Vezir) problem solution

#USAGE
1. Clone project into your workspace.
2. Go to the project directory and run <pre>>mvn clean install</pre>
3. When build is success, you should see a ChessQueenProblem.jar under target directory of the project
4. run as below:
<pre>
>java -jar ChessQueenProblem.jar n r c k barriers

Input parameters: n r c k barriers
Input Parameters definitions:
    n: One side length of the chess board for nxn (n>=2)
    r: Horizontal (Row) position of the queen on the chess board (r<=n)
    c: Vertical (Column) position of the queen on the chess board (c<=n)
    k: Number of the barriers (k>=0) 
    barriers: List of the barrier pair as row and column i.e 3 5 4 2
    Sample inputs:
    8 4 3  Here: n=8 and position of the queen is row=4 and column=3 (4,3) and no input for barriers
    6 4 3 1 5 6  Here:n=6 r=4 c=3 k=1 and barrier point is row=5 and column=6 (5,6)
    5 2 4 2 3 2 4 1 Here:n=5 r=2 c=4 k=2 and barrier points are (3,2)(4,1)
</pre>	

5. Samples output:
  <pre>
  0 1 1 1 x
  0 x v 1 1
  0 1 1 1 0
  1 0 x 0 1
  0 0 0 0 0
  
  Here;
  0:empty positions
  1:Positions that chess queen can go
  v:Position of the queen on the chess board
  x:Position of the barriers on the chess board
  </pre>


