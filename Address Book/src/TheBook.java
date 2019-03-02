import java.util.Scanner;

public class TheBook {
	
	public static void main(String[] args) {
		
		String addressBook[][] = new String[10][4];
		ThePen progMenu = new ThePen();
		int userChoice = 0;
		Scanner UI = new Scanner(System.in);
		
		System.out.println("\nAddress Book is created\n");
		//System.out.print(addressBook+"\n");
	
		//do {
			System.out.println("\nWhat would you like to do?\n 1) Edit Address Book\n 2) Exit");
			userChoice = UI.nextInt();
			switch(userChoice) {
			case 1: progMenu.showMenu(addressBook);break;
			case 2: System.out.println("\nGood Bye"); UI.close(); System.exit(0);
			default: System.out.println("\n Invalid Input\n");
			}
		//}while(userChoice<1 && userChoice>2);
	}
}