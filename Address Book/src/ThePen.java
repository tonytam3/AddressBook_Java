import java.util.Scanner;

public class ThePen {
	boolean progExit = false;
	Scanner userInput = new Scanner(System.in);
	private int arrayColumn = 0;
	
	
	public void showMenu(String arr[][]) {
		String arr1[][]=arr;
		int Response = 0;
		do {
			System.out.println(" 1) Add Contact\n 2) Edit Contact\n 3) Display Contacts\n 4) Modify Search\n 5) Exit");
			Response = userInput.nextInt();
			switch(Response) {
			case 1: arr1=addContact(arr1);break;
			case 2: editContact(arr1);break;
			case 3: DisplayContact(arr1);break;
			case 4: modifySort();break;
			case 5: exit();break;
			default: System.out.println("\n Invalid Input please try again.\n ");
			}
		}while(Response < 5 || Response >1);
		
	}
	
	//------------------Below Here is Menu Functions------------------------------------------------------------------------------------------------------------------
	private String[][] addContact(String currentArray[][]) { //ask the user for name and number to add and initiates a test for available space
		String tempArr[][] = new String[1][4];
		int n = 0;
		do {
			switch(n) {
			case 0:
				System.out.println("What is the first name?");
				String FirstName = userInput.next();
				tempArr[0][0]= FirstName;
				n++;
			case 1:
				System.out.println("What is the last name?");
				String LastName = userInput.next();
				tempArr[0][1]= LastName;
				n++;	
			case 2:
				System.out.println("What is the person's phone number?");
				String Number = String.valueOf(userInput.next());
				tempArr[0][2]= Number;
				n++;
			case 3:
				System.out.println("What is the person's Address?");
				userInput.nextLine();
				String Address = userInput.nextLine();
				tempArr[0][3] = Address;
				n++;
			}
		}while(4>n);
		
		String arr[][]=bookTest(currentArray,tempArr);

		return arr;

	}
	
	private void editContact(String currentArray[][]) {
		System.out.println("\nWhich contact to do you wish to edit? Enter( First or Last Name)\n");
		
		String Response = userInput.nextLine();
		
		Integer report []= searchContact(Response, currentArray);
		
		for (int i= 0; i< report.length; i++) {
			System.out.print("\n "+report[i]+":"+currentArray[report[i]][1]+"\n");
		}
		boolean wait = false;
		do {
			System.out.println("\nSelect from search result. (integer only)\n");
			
			int userChoice = userInput.nextInt();
			
			for (int i= 0; i< report.length; i++) {
				
				if (userChoice == report[i]) {
					System.out.println("\n Which part of the contact do you wish to change?\n 1) first name\n 2)last name\n 3)phone number\n");
					int arrayNumber = userInput.nextInt()-1;
					System.out.println("\nEnter the change\n");
					currentArray[report[i]][arrayNumber] = userInput.nextLine();
					wait = true;
				}
				else {
					System.out.println("\n Input out of range");
				}
			}
		}while(wait == false);	
	}
	
	private void DisplayContact(String currentArray[][]) {
		for (int i = 0; i<currentArray.length; i++) {
			if (currentArray[i][0] != null) {
				System.out.println("\n First Name: "+currentArray[i][0]+" Last Name: "+currentArray[i][1]+" PhoneNumber: "+currentArray[i][2]+" Address: "+currentArray[i][3]);
			}
		}
		System.out.println("\n End of List");
		
	}
	
	private void modifySort() {
		System.out.println("\nHow would you like to sort your contacts?(Integer Only)\n 1)First Name\n 2)Last Name");
		int Response = userInput.nextInt();
		do {
			switch(Response) {
			case 1: arrayColumn = 0;break;
			case 2: arrayColumn = 1;break;
			default: System.out.println("Invalid Input");break;
			}
		}while(Response<0 && Response>1);
	}
	
	private void exit() {
		System.out.println("Goodbye");
		System.exit(0);
	}
	//-----------Below Here are functions the menu Functions would call-------------------------------------------------------------------------------------------------------------
	private String[][] bookTest(String arr[][],String arr1[][]) { // This Function is for testing if there's enough empty spaces for additional entries

		if (arr[arr.length-1][0]!=null&&arr[arr.length][0]==null) {arr=increaseArraySize(arr);};
		
		boolean entryAdd = false;
		
		for (int a = 0; a<arr.length-1; a++) {
			if (arr[a][0] != null && arr[a+1][0] == null) { //If the array does have 2 available rows function increaseArraySize() is call upon	
				if(entryAdd == false) {
					arr[a+1][0]= arr1[0][0];
					arr[a+1][1]= arr1[0][1];
					arr[a+1][2]= arr1[0][2];
					arr[a+1][3]= arr1[0][3];
					entryAdd = true;
				}
			}
			else if (arr[a][0] == null && arr[a+1][0] == null){ // if there's two available row, contents from second input array is added to main array 
				if(entryAdd == false) {
					arr[a][0]= arr1[0][0];
					arr[a][1]= arr1[0][1];
					arr[a][2]= arr1[0][2];
					arr[a][3]= arr1[0][3];
					entryAdd = true;
				}
			}
		}
		
		
		selectSortNames(arr,arrayColumn); //resorts names alphabetically
		return arr;	
	}
	
	private static String[][] increaseArraySize(String arr[][]){
		String tempArr [][] = new String [arr.length][3];
		
		for (int b = 0; b<arr.length; b++) { // copies the original array to a tempArray
			tempArr[b]= arr [b];
		}
		
		String arr1[][] = new String[arr.length*2][3];//doubles the rows of array
		
		System.out.print(arr1.length);
		
		for (int b = 0; b<arr.length; b++) { //copies the contents from tempArray to new array
			arr1[b] = tempArr [b];
		}
		
		arr=arr1;
		
		return arr;
	}
	
	private void selectSortNames(String currentArray[][],int arrayColumn) {
		for (int i=0; i<currentArray.length-1;i++) {
			int minIndex = i;

			if (currentArray[i][arrayColumn] != null) {
				for (int j=i+1; j<currentArray.length; j++) {
					
					if(currentArray[j][arrayColumn]!=null) {
						
						if(currentArray[j][arrayColumn].compareTo(currentArray[minIndex][arrayColumn]) < 0) {
						minIndex = j;
						}
						
					}
				}
			}
			swapArray(currentArray,i,minIndex);
		}
	}
	
	private void swapArray(String currentArray[][], int old, int nu) {
		String tempArray [][] = new String[1][4];
		tempArray [0] = currentArray[old];
		currentArray[old] = currentArray[nu];
		currentArray[nu] = tempArray [0];
		
	}
	
	
	
	private Integer[] searchContact(String Response, String currentArray[][]) { //creates a 1D array to denotes all possible index and matches user string input
		Integer searchArray [] = new Integer[10];
		for(int i = 0; i< currentArray.length; i++) {
			if (Response == currentArray[i][0] && Response == currentArray[i][1]) {
				searchReturnArray(searchArray, i);
			}
			else if (Response == currentArray[i][0] || Response == currentArray[i][1]) {
				searchReturnArray(searchArray, i);
			}	
		}
		return searchArray;
		
	}
	
	private void searchReturnArray(Integer arr[], int i) {
		for (int a = 0; a<arr.length; a++) {
			if (arr[a] == null  && arr[a+1] == null) {
				arr[a] = i;
			}
			else if (arr[a] != null  && arr[a+1] == null) {
				increaseArraySize_1D(arr);
				arr[a+1] = i;
			}
		}
	}
	
	private static Integer[] increaseArraySize_1D(Integer arr[]){//doubles size of searchArray
		Integer tempArr [] = new Integer [arr.length];
		for (int b = 0; b<arr.length; b++) { // copies the original array to a tempArray
			tempArr[b]= arr [b];
		}
		
		Integer arr1[] = new Integer[arr.length*2];
		
		for (int b = 0; b<arr.length; b++) { //copies the contents from tempArray to new array
			arr1[b] = tempArr [b];
		}	
		return arr1;
	}
	
}