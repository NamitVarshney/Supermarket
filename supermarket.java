import java.util.*; // Importing the package containing the Scanner class

/* Driver class that is responsible for the main functionality */

public class Supermarket
{
public static void main(String[] args) // Main method
{

    Scanner sc = new Scanner(System.in);
    Products obj = new Products();
    Customers std = new Customers();
    int choice;
    int s;
    try // Try block of exception handling
    {
    do // Start of 'do' block
    {
		System.out.println(" _____________________________________________ ");
		System.out.println("|                                             |");
		System.out.println("|                                             |");
		System.out.println("|      Welcome to All In One Supermarket      |");
		System.out.println("|                                             |");
		System.out.println("|_____________________________________________|\n\n");
		
		
	/* Interface menu */	
     
	 System.out.println("Enter 1 to Add new Product.");
     System.out.println("Enter 2 to Upgrade Quantity of a Product.");
     System.out.println("Enter 3 to Search a Product.");
     System.out.println("Enter 4 to Show All Products.");
     System.out.println("Enter 5 to Register Customer.");                 
     System.out.println("Enter 6 to Show All Registered Customers.");     
     System.out.println("Enter 7 to Check out  ");
     System.out.println("Enter 8 to Return Product ");
     System.out.println("Enter 9 to Exit");
        choice = sc.nextInt();
    
		/* Multiple-choice selection using a switch statement */
	
        switch(choice)
        {
             case 1:
                Product b = new Product();
                obj.addProduct(b);
                break;

            case 2:
                obj.upgrade();
                break;

            case 3:
                System.out.println("Enter Serial Number of Product");
                s = sc.nextInt();
                
                obj.searchno();
                
            
                break;

            case 4:               
                obj.showAllProducts();
                break;
            case 5:                                      
                Customer sm = new Customer();
                std.addCustomer(sm);
                break;
            case 6:                                      
                std.showAllCustomers();
                break;
            case 7:
                std.checkOutProduct(obj);
                break;
            case 8:
                std.returnProduct(obj);
                break;
                case 9:
                System.exit(0);
            default:
                System.out.println("wrong choice");

        }

    }
    while (true);
}
catch(Exception e) // Catch block of exception handling
{
    System.out.println("Error");
}
}
}

abstract class variable // Abstract class that holds the instance variables
{
 int serialno;
 String productname;
 int MRP;
 int productqty;
}
 class Product extends variable // Class 'Product', which represents the actual product
 {

 
 int productqtycopy;

Scanner sc = new Scanner(System.in);

 /* Default-constructor of class 'Product' */
 
 Product(){
    System.out.println("Enter Product Name:");
    this.productname = sc.nextLine();
    System.out.println("Enter MRP of Product"); 
    this.MRP = sc.nextInt();
    System.out.println("Enter Serial no. of Product");
    this.serialno = sc.nextInt();
    System.out.println("Enter Quantity");
    this.productqty = sc.nextInt();
    productqtycopy = this.productqty;
}
}

/* Class 'Products', which holds an array of 'Product' objects, and methods to deal with them */

class Products
{

Product bk[] = new Product[10];    // Creation of an array of 'Product' objects
static int c;  // A static instance variable which keeps a count of the number of objects created  

Scanner sc = new Scanner(System.in);




 int compareProduct(Product b1, Product b2){          // Function to compare two products

    if (b1.productname.equalsIgnoreCase(b2.productname)){      // equalsIgnoreCase is a member of the String class

        System.out.println("Product Already Exists.");
        return 0;

    }
    if (b1.serialno==b2.serialno){

        System.out.println("Product Already Exists.");
        return 0;
    }
    return 1;
}

void addProduct(Product b){ // A method to add a product to the catalog

    for (int i=0; i<c; i++){

        if (this.compareProduct(b, this.bk[i]) == 0)
            return;

    }

    if (c<10){

        bk[c] = b;
        c++;

    }
    else{

        System.out.println("No Space");

    }

}

public void searchno(){ // A method to search for a product from the catalog
    int sNo;
    System.out.println("Enter Serial No of Product:");
    sNo = sc.nextInt();
    int flag = 0;
    for (int i=0; i<c; i++){

        if (sNo == bk[i].serialno)
        {
            System.out.println("found");
            System.out.println(bk[i].serialno + "\t\t" + bk[i].productname + "\t\t" + bk[i].MRP + "\t\t" + bk[i].productqty);
            flag++;
            return;
        }

    }
    if (flag == 0)
        System.out.println("No Product for Serial No " + sNo + " Found.");
}


public void showAllProducts(){ // A method to display a list of all products from the catalog

    System.out.println("books");
    System.out.println("SERIAL NO       PRODUCT NAME       MRP       PRODUCT QTY");
    System.out.println("-------------------------------------------------------------------");
    for (int i=0; i<c; i++){

        System.out.println(bk[i].serialno + "\t\t" + bk[i].productname + "\t\t" + bk[i].MRP + "\t\t" + bk[i].productqty);


    }

}

public void upgrade() // A method to add a product to the catalog
{
    int sNo;
    System.out.println("Enter Serial No of Product");
     sNo = sc.nextInt();
    for (int i=0; i<c; i++){

        if (sNo == bk[i].serialno){

            System.out.println("Enter No of Products to be Added:");
            int add = sc.nextInt();
            bk[i].productqty += add;
            bk[i].productqtycopy += add;
            return;

        }

    }

}

public int isAvailable(int sNo){ // A method to check whether a given product is available or not

    



    for (int i=0; i<c; i++){

        if (sNo == bk[i].serialno){
            if(bk[i].productqtycopy > 0){

                System.out.println("Product is Available.");
                return i;

            }
            System.out.println("Product is Unavailable");
            return -1;

        }

    }
    return -1;


}

public Product checkOutProduct(){ // The method called when the customer wants to check-out of the supermarket

    System.out.println("Enter Serial No of Product to be Checked Out.");
    int sNo = sc.nextInt();

    int productIndex =isAvailable(sNo);

    if (productIndex!=-1){
        bk[productIndex].productqty--;

        return bk[productIndex];
    }

    return null;

}

public void returnProduct(Product b){ // A method to return a product to the supermarket

    for (int i=0; i<c; i++){

        if (b.equals(bk[i]) ){

            bk[i].productqty++;
            return;

        }

    }

}

}

/* Class 'Customer', which represents the actual customer */

class Customer {                          

/* Instance variables of the class 'Customer' */

String customerName;
String phoneNum;

Product orderedProducts[] = new Product[60]; // Creation of an array of Product objects
public int productsCount = 0; // To keep a count of the products

Scanner sc = new Scanner(System.in);

/* Default-constrcutor of class 'Customer' */


Customer(){

    System.out.println("Enter Customer Name:");
    this.customerName = sc.nextLine();	

    System.out.println("Enter Registered Phone Number:");
    this.phoneNum = sc.nextLine();
}
}

/* Class 'Customers', which holds an array of 'Customer' objects, and methods to deal with them */

 class Customers {

Scanner sc = new Scanner(System.in);

Customer theCustomers[] = new Customer[50]; // Creation of an array 'Customer' objects
public static int count = 0;

public void addCustomer(Customer s){ // A method to add a customer to the Customer type array

    for (int i=0; i<count; i++){

        if(s.phoneNum.equalsIgnoreCase(theCustomers[i].phoneNum)){

            System.out.println("Customer of Reg Phone Number " + s.phoneNum + " is Already Registered.");
            return;
        }

    }

    if (count<50){

        theCustomers[count] = s;
        count++;

    }

}
public void showAllCustomers(){ // A method to display a list of all customers

    System.out.println("Customer Name\t\tReg Phone Number");
    System.out.println("-----------------------------------------------");
    for (int i=0; i<count; i++){

        System.out.println(theCustomers[i].customerName + "\t\t         " + theCustomers[i].phoneNum);

    }


}


public int isCustomer(){ // A method to check whether a given customer has already been registered or not

    System.out.println("Enter Reg Phone Number:");
    String phoneNum = sc.nextLine();

    for (int i=0; i<count; i++){

        if (theCustomers[i].phoneNum.equalsIgnoreCase(phoneNum)){

            return i;

        }

    }
    System.out.println("Customer is not Registered.");
    System.out.println("Get Registered First.");


    return -1;


}

 void checkOutProduct(Products product){ // A method that is called when the customer wants to check out
    int customerIndex =this.isCustomer();

    if (customerIndex!=-1){
  

        product.showAllProducts();
        Product b = product.checkOutProduct();
        System.out.println("checking out");
        if (b!= null){

            if (theCustomers[customerIndex].productsCount<=60){
                System.out.println("adding Product");
				
				System.out.println("**************************INVOICE****************************");
					
				
				
				
				
				
				
                theCustomers[customerIndex].orderedProducts[theCustomers[customerIndex].productsCount] = b;
                theCustomers[customerIndex].productsCount++;
                
                return;

            }
            else {

                System.out.println("Customers can't order more than 60 Products at a time");
                return;

            }
        }
        System.out.println("Product is not Available.");

    }

}


public void returnProduct(Products product){ // A method that is called when a product is to be returned

    int customerIndex = this.isCustomer();
    if (customerIndex != -1){
        System.out.println("S.No\t\t\tProduct Name\t\t\tMRP");
        Customer s = theCustomers[customerIndex];
        for (int i=0; i<s.productsCount; i++){

            System.out.println(s.orderedProducts[i].serialno+ "\t\t\t" + s.orderedProducts[i].productname + "\t\t\t"+
                    s.orderedProducts[i].MRP);

        }
        System.out.println("Enter Serial Number of Product to be Checked In:");
        int sNo = sc.nextInt();
        for (int i=0; i<s.productsCount; i++){

            if (sNo == s.orderedProducts[i].serialno){

                product.returnProduct(s.orderedProducts[i]);
                s.orderedProducts[i]=null;
                return;

            }


        }
        System.out.println("Product of Serial No "+sNo+"not Found");

    }




}

}

