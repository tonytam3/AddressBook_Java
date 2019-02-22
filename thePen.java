import java.util.Scanner;
import java.util.ArrayList;;

public class thePen {
	boolean progExit = false;
	Scanner userInput = new Scanner(System.in);
	int arrayColumn = 1;
	
	public void showMenu(String arr[][]) {
		System.out.println(" 1) Add Contact\n 2) Edit Contact\n 3) Search Contacts\n 4) Exit\n");

		int Response = 0;
		do {
			Response = userInput.nextInt();
			switch(Response) {
			case 1: addContact(arr);break;
			case 2: editContact(arr);break;
			case 3: searchContact(arr);break;
			case 4: modifySort();break;
			case 5: exit();break;
			default: System.out.println("\n Invalid Input please try again.\n ");
			}
		}while(Response < 5 || Response >1);
		
	}
	
	private void addContact(String currentArray[][]) { //ask the user for name and number to add and initiates a test for available space
		String tempArr[][] = new String[1][3];
		
		System.out.println("\nWhat is the first name?\n");
		tempArr[0][0]= userInput.nextLine();
		System.out.println("\nWhat is the last name?\n");
		tempArr[0][1] = userInput.nextLine();
		System.out.println("\nWhat is the person's phone number?");
		tempArr[0][2] = userInput.nextLine();
		
		bookTest(currentArray,tempArr);
	}
	
	private void bookTest(String arr[][],String arr1[][]) { // This Function is for testing if there's enough empty spaces for additional entries
		for (int a = 0; a<arr.length; a++) {
			if (arr[a] != null && arr[a+1] == null) { //If the array does have 2 available rows function increaseArraySize() is call upon
				
				arr = increaseArraySize(arr); //using this function increases the size of the array by 1					
				
				arr[a][0]= arr1[0][0];
				arr[a][1]= arr1[0][1];
				arr[a][2]= arr1[0][2];
			}
			else if (arr[a] == null && arr[a+1] == null){ // if there's two available row, contents from second input array is added to main array 
				arr[a][0]= arr1[0][0];
				arr[a][1]= arr1[0][1];
				arr[a][2]= arr1[0][2];
			}
		}
		
		selectSortNames(arr); //resorts names alphabetically
			
	}
	
	private static String[][] increaseArraySize(String arr[][]){
		String tempArr [][] = new String [arr.length][3];
		for (int b = 0; b<arr.length; b++) { // copies the original array to a tempArray
			tempArr[b]= arr [b];
			//tempArr[b][1] = arr [b][1];
			//tempArr[b][2] = arr [b][2];
		}
		
		String arr1[][] = new String[arr.length+1][3];
		
		for (int b = 0; b<arr.length; b++) { //copies the contents from tempArray to new array
			arr1[b] = tempArr [b];
			//arr[b][1] = tempArr [b][1];
			//arr[b][2] = tempArr [b][2];
		}	
		return arr1;
	}
	
	private void selectSortNames(String currentArray[][],int arrayColumn) {
		for (int i=0; i<currentArray.length-1;i++) {
			int minIndex = i;
			for (int j=i+1; j<currentArray.length; j++) {
				if(currentArray[j][arrayColumn].compareTo(currentArray[minIndex][arrayColumn]) < 0) {
					minIndex = j;
				}
			}
			swapArray(currentArray,i,minIndex);
		}
	}
	
	private void swapArray(String currentArray[][], int old, int nu) {
		String tempArray [][] = new String[1][3];
		tempArray [old] = currentArray[old];
		currentArray[old] = currentArray[nu];
		currentArray[nu] = tempArray [old];
		
	}
	
	private void editContact(String currentArray[][]) {
		System.out.println("Which contact to do you wish to edit?");
		String Response = userInput.nextLine();
		searchContact(Response, currentArray);
	}
	
	private int searchContact(String Response, String currentArray[][]) {
		for(int i = 0; i< currentArray.length; i++) {
			if (Response == currentArray[i][0]) {
				
			}
			
		}
	}
	
}