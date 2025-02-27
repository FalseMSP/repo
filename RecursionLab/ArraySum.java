public class ArraySum {
	public ArraySum() {
		
	}
	
	public int sumOfArray(Integer[] myArray, int index) {
		if (index == -1) return 0;
		return sumOfArray(myArray,index-1) + myArray[index];
	}
}
