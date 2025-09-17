
class Solution {
    public boolean searchMatrix(int[][] matrix, int key) {
        int row=0,col=matrix[0].length-1;
        while(row<matrix.length && col>=0){
             if(matrix[row][col]==key){
                return true;
             }
             else if(key<matrix[row][col]){
                col--;
             }
             else{
row++;

             }
        }
        return false;
    }
}
