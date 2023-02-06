import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Random;

public class main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	
		boolean end_game = false;
		
		BookstoreBook Books[] = new BookstoreBook[100];
		LibraryBook libBooks[] = new LibraryBook[200];
		int numBooks = 0;
		int numLibBooks = 0;
		
		System.out.println("Welcome to the book program!\n");
		while (!end_game) {
			
			
			System.out.println("Would you like to create a book object?(yes/no):\n"); //scans id
			String choice = scan.nextLine();
			while (!choice.toLowerCase().equals("yes") && !choice.toLowerCase().equals("no")){
				System.out.println("I’m sorry but yeah isn’t a valid answer. Please enter either yes or no: \n");
				choice = scan.nextLine();
			}
			if (choice.toLowerCase().equals("yes")){
				System.out.println("Please enter the author, title, and the isbn of the book separated by /: \n"); //scans payrate
				String input = scan.nextLine();
				String[] inputArray = input.split("/");
				System.out.println(input);
				
				while (inputArray[0] == null && inputArray[1] == null && inputArray[2] == null) {
					System.out.println("Oops! That's not a valid entry. Please try again: ");
					input = scan.nextLine();
				}	
				
				System.out.println("Got it!");
				System.out.println("Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book):\n");
				String BorL =scan.nextLine().toLowerCase();
				while (!BorL.toLowerCase().equals("bb") && !BorL.toLowerCase().equals("lb")){
					System.out.println("Oops! That's not a valid entry. Please try again: \n");
					BorL = scan.nextLine();
				}
				if (BorL.toLowerCase().equals("bb")) {
					System.out.println("Got it!\n");
					System.out.println("\nPlease enter the list price of " + inputArray[1].toUpperCase() + " by " + inputArray[0].toUpperCase() + ":");
					double price =Double.parseDouble(scan.nextLine());
					double deductr = 0;
					
					System.out.println("Is it on sale? (y/n): \n");
					String sale =scan.nextLine().toLowerCase();
					while (!sale.toLowerCase().equals("y") && !sale.toLowerCase().equals("n")){
						System.out.println("Oops! That's not a valid entry. Please try again: \n");
						sale = scan.nextLine();
					}
					if (sale.toLowerCase().equals("y")) {
						System.out.println("Deduction percentage: \n");
						String deduct =scan.nextLine().replace("%", "");
						deductr = Double.parseDouble(deduct);
						}
					else if (sale.toLowerCase().equals("n")) { 
						deductr = 0;
					}

					System.out.println("Got it!");
					BookstoreBook b = new BookstoreBook(inputArray[0], inputArray[1], inputArray[2], price, deductr);
					Books[numBooks] = b;
					numBooks++;
					System.out.println("Here is your bookstore book information\n");
					b.printCheck();
				}	
				
				
				else if (BorL.toLowerCase().equals("lb")) { 
					System.out.println("Got it!");
					LibraryBook b = new LibraryBook(inputArray[0], inputArray[1], inputArray[2]);
					libBooks[numLibBooks] = b;
					numLibBooks++;
					System.out.println("Here is your library book information\n");
					b.printCheck();
				}
			}
			else if (choice.toLowerCase().equals("no")){
				end_game = true;
			}
		}
		System.out.println("Here are all your books...\n");
		System.out.println("Library Books" + "(" + (numLibBooks) + ")\n");
		for (int i = 0; i < numLibBooks; i++) {
			System.out.println(libBooks[i]);
		}
		System.out.println("----\n");
		
		System.out.println("Bookstore Books" + "(" + (numBooks) + ")\n");
		for (int i = 0; i < numBooks; i++) {
			System.out.println(Books[i]);//toString method
		}
		
		System.out.println("----\n");
		
		System.out.println("Take care now!\n");
		scan.close();
	}
}

abstract class Book {
	private String name, id;
	public String getName() {
	return name;
	}
	public void setName(String name) {
	this.name = name;
	}
	public String getId() {
	return id;
	}
	public void setId(String id) {
	this.id = id;
	}
	public Book(String name, String id) {
	this.name = name;
	this.id = id;
	}
	public Book() {
	name = id = "-";
	}
	abstract public void printCheck();
	}

	class BookstoreBook extends Book{
	private String Author;
	private String Title;
	private String Isbn;
	private String Price;
	private String Percent;
	private String Deduction;
	
	public BookstoreBook(String Author, String Title, String Isbn, double Price, double Percent){
		setAuthor(Author);
		setTitle(Title);
		setIsbn(Isbn);
		setPrice(Price);
		setPercent(Percent);
		setDeduction(Price,Percent);
	}
	
	public String getAuthor() {
		return Author;
	}
	public String getTitle() {
		return Title;
	}
	public String getIsbn() {
		return Isbn;
	}
	public String getPrice() {
		return Price;
	}
	public String getPercent() {
		return Percent;
	}
	public String getDeduction() {
		return Deduction;
	}
	public void setAuthor(String newAuthor) {
		this.Author = newAuthor.toUpperCase();
	}
	public void setTitle(String newTitle) {
		this.Title = newTitle.toUpperCase();
	}
	public void setIsbn(String newIsbn) {
		this.Isbn = newIsbn.toUpperCase();
	}
	public void setPrice(double newPrice) {
		this.Price = String.valueOf(newPrice);
	}
	public void setPercent(double newPercent) {
		this.Percent = String.valueOf(newPercent);
	}
	public void setDeduction(double cost, double percent) {
		DecimalFormat df = new DecimalFormat("#.00");
		String costr = df.format(cost - (cost * (percent/100)));
		this.Deduction = costr;
		
	}

	@Override
	public String toString() {
		return ("[" + getIsbn() + "-" + getTitle() + " by " + getAuthor() + ", $" + getPrice() + " listed for $" + getDeduction() + "]");
	}
	@Override
	public void printCheck () {
		System.out.println(Isbn+"-"+Title+ " by " + Author + ", " + Price + " listed for " + Deduction);
	}
}

	class LibraryBook extends Book{
	private String Author;
	private String Title;
	private String Isbn;
	private String callNum;
	
	public LibraryBook (String Author, String Title, String Isbn) {
		setAuthor(Author);
		setTitle(Title);
		setIsbn(Isbn);
		setcallNum();
}
	
	public String getAuthor() {
		return Author;
	}
	public String getTitle() {
		return Title;
	}
	public String getIsbn() {
		return Isbn;
	}
	public String getcallNum() {
		return callNum;
	}
	public void setAuthor(String newAuthor) {
		this.Author = newAuthor.toUpperCase();
	}
	public void setTitle(String newTitle) {
		this.Title = newTitle.toUpperCase();
	}
	public void setIsbn(String newIsbn) {
		this.Isbn = newIsbn.toUpperCase();
	}
	public void setcallNum() {
		Random rand = new Random();
		int upperbound = 99;
		String random = String.valueOf(rand.nextInt(upperbound));
		
		String authorName = getAuthor().substring(0,3);
		String bookIsbn = getIsbn().substring(getIsbn().length()-1);
		
		callNum = random + "." + authorName + "." + bookIsbn;	
	}
	
	@Override
	public String toString() {
		return ("[" + getIsbn() + "-" + getTitle() + " by " + getAuthor() + "-" + getcallNum() + "]\n");
	}
	@Override
	public void printCheck () {
	System.out.println(Isbn+"-"+Title+ " by " + Author + callNum);
	}
}
class Booklist {
	private Book[] list;
	final int SIZE = 100;
	
	public Booklist() {
		list = new Book[100];
		for ( int i = 0; i<SIZE; i++) {
			list[i] = null;
			}
	}
	private int search ( String id) {
		
		for ( int i = 0; i<SIZE; i++) {
			if ( list[i]!=null && id.equalsIgnoreCase(list[i].getId()))
				return i;
		}
		return -1; 
	}
	public void printCheck ( String id) {
		int index = search ( id );
		if ( index == -1 ) {
			System.out.println("Sorry " +  id +" isn't found as id");
			return ;
		}
		list[index].printCheck();
	}
}