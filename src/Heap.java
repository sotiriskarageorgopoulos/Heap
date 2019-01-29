import java.lang.Math;
import java.util.Random;
public class Heap {

	Integer[] heapArr;
	int countInsertions;
	
	Heap(int maxSize){
	   heapArr = new Integer[maxSize];
	   countInsertions = 0;
	}
	
	private int leftChild(int pos) //left child of each node
	{
		return 2*pos+1;
	}
	
	
	private int rightChild(int pos) //right child of each node
	{
		return 2*pos+2;
	}
	
	private int ancestor(int pos) //ancestor of a precise node
	{
		return (int)Math.floor((pos-1)/2);
	}
	
	private int lastPosition() //return the length of array
	{
	  return countInsertions;	
	}
	private int incInsertions() //increase the length of array, when add a number
	{
		return ++countInsertions;
	}
	
	private int decInsertions() //decrease the length of array, when delete a number
	{
		return --countInsertions;
	}
	
	
    public Integer[] insert(int val) {
    	
    	int posOfInserted = lastPosition(); //store the last position
    	heapArr[posOfInserted] = val; //store the value on last position
    	incInsertions(); //increase the length of array
    	
        while(posOfInserted > 0) // while the position is positive number
        {
        	  //if the ancestor node have value smaller than child
        	  if(heapArr[ancestor(posOfInserted)] < heapArr[posOfInserted]) {
        		
        	  //swap the values	
        	  int tmp = heapArr[ancestor(posOfInserted)];
        	  heapArr[ancestor(posOfInserted)] = heapArr[posOfInserted];
        	  heapArr[posOfInserted] = tmp;
        	  
        	  posOfInserted = ancestor(posOfInserted); //store the new position
            }
        	else break;
        }
        
        if(leftChild(0) < lastPosition()) //if exists the left child of root
        {
          if((heapArr[leftChild(0)] > heapArr[0]))//if left child has bigger value than root
          {
        	//swap values
            int tmp = heapArr[0];
      	    heapArr[0] = heapArr[leftChild(0)];
      	    heapArr[leftChild(0)] = tmp;
          }
        }
        
        if(rightChild(0) < lastPosition()) //if exists the right child of root
        {
          if((heapArr[rightChild(0)] > heapArr[0])) //if right child has bigger value than root
          {
        	//swap values
        	int tmp = heapArr[0];
        	heapArr[0] = heapArr[rightChild(0)];
        	heapArr[rightChild(0)] = tmp;
          }       
        }
        
        return heapArr;
    }

    public Integer[] delete() {
    	
      int position=0;	//store the position of deleted node
    	
      if(heapArr[0] == null) return null;
      
      if(lastPosition() == 1) //if heap has only one value
      {
    	  heapArr[0] = null; //store null,because there is not heap
    	  return heapArr;
      }
      
      heapArr[0] = heapArr[lastPosition()-1]; //store on first position, the value of last position
      heapArr[lastPosition()-1] = null; //store null on last position
      decInsertions(); // decrease the positions of array
      
      while(position < lastPosition()) //while the position is not override the positions of array 
      { 
    	  if(leftChild(position) >= lastPosition()) break;   //if the position of left child is not exist,break while
    	  if(rightChild(position) >= lastPosition()) break; //if the position of right child is not exist,break while
    	 
    	  //if the left child is bigger than ancestor and the left child is bigger than right
          if(heapArr[position] < heapArr[leftChild(position)] && heapArr[leftChild(position)] > heapArr[rightChild(position)])
          {
           //swap values
    	   int tmp = heapArr[position];
    	   heapArr[position] = heapArr[leftChild(position)];
    	   heapArr[leftChild(position)] = tmp;
    	   position = leftChild(position); 
    	   
          }
          else if(heapArr[position] < heapArr[rightChild(position)] && heapArr[leftChild(position)] < heapArr[rightChild(position)])
          {
        	//swap values
        	int tmp = heapArr[position];
        	heapArr[position] = heapArr[rightChild(position)];
        	heapArr[rightChild(position)] = tmp;
        	position = rightChild(position);
          }
            
          
         if(leftChild(position) >= lastPosition()) break; //if the position of left child is not exist,break while
    	 if(rightChild(position) >= lastPosition()) break;//if the position of right child is not exist,break while
    	 
    	 //if the ancestor is bigger than right child and the ancestor is bigger than left child
         if(heapArr[position] > heapArr[rightChild(position)] && heapArr[leftChild(position)] < heapArr[position]) break;
       }
     
     return heapArr;
    }

    public void display() { //display the numbers of heap
    	for(int i=0;i<lastPosition();i++) System.out.print("["+heapArr[i]+"]");
    }

    public static void main(String args[])
    {
    	Heap h = new Heap(10); //create a heap
    	Random rnd = new Random(System.currentTimeMillis()); //generate numbers
    	
    	for(int i=0;i<h.heapArr.length;i++)System.out.print(" "+i+" "); 
    	
    	System.out.println("");
    	
    	for(int i=0;i<h.heapArr.length;i++)
    	{	
    	  int val = 0+rnd.nextInt(500);
    	  
    	  System.out.print("["+val+"]"); //the values
    	  
    	  h.insert(val); //the heap
        }
    	
    	System.out.println("");
    	h.display();
    	
    	h.delete();
    	
    	System.out.println("");
        h.display();
    }
}