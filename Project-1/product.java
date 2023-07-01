// importing neccesary libararies
import java.sql.*;
import java.util.*;

// main class
public class product {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "0000";

    public static void main(String[] args) {
             Scanner sc = new Scanner(System.in);

        try {
            // Establish the database connection
            Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection established :)");

        while(true){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Welcome to Shopbee");
        System.out.println("1) Login as Admin");
        System.out.println("2) Login as Guest");
        System.out.println("3) Logout");
        System.out.println("-------------------------------");
        System.out.print("Enter your choice : ");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
            LoginAsAdmin(con);
            break;
            case 2:
            LoginAsGuest(con);
            break;
            case 3:
            System.out.println("Thank you for shopping with us have a great day :)");
            return;
            default :
            System.out.println("Invalid input please try again");
        }
    }}
        catch (SQLException e) {
            System.out.println("Connection is not established please try again :(");
        }
    }


    private static void LoginAsAdmin(Connection con) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Admin Login");
        System.out.print("Username: ");
        String Username = sc.nextLine();
        System.out.print("Password: ");
        String Password = sc.nextLine();

        try{
            String query = "select * from Login where Username = ? and Password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, Username);
            statement.setString(2, Password);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                System.out.println("-------------------------------");
                System.out.println("Admin login Successful");
                while(true){
                    System.out.println("Admin Menu");
                    System.out.println("1. Add Product to Agent");
                    System.out.println("2. View Product Table Records");
                    System.out.println("3. Logout");
                    System.out.println("-------------------------------");
                    System.out.print("Enter your choice: ");
                    int choice = sc.nextInt();

                    switch (choice){
                        case 1:
                        AddProductToTable(con);
                        break;
                        case 2:
                        ViewProductToTable(con);
                        break;
                        case 3:
                        System.out.println("Admin has been logged out");
                        return;
                        default:
                        System.out.println("Invalid choice, Try again :(");
                    }
                        System.out.println("");
                }
            }
            else{
                System.out.println("Login failed, Invalid Username and Password");
            }}
            catch (SQLException e){
                System.out.println("Error during Login: "+e.getMessage());
            }
        }



    private static void LoginAsGuest(Connection con) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Guest Login");
        System.out.print("Username: ");
        String Username = scanner.nextLine();
        System.out.print("Password: ");
        String Password = scanner.nextLine();

        try{
            String query = "select * from Login where Username = ? and Password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, Username);
            statement.setString(2, Password);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                System.out.println("-------------------------------");
                System.out.println("Guest login successful :)");
                while(true){
                    System.out.println("Guest Menu");
                    System.out.println("1) Add product to the table");
                    System.out.println("2) Buy the product");
                    System.out.println("3) Logout");
                    System.out.println("-------------------------------");
                    System.out.println("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch(choice){
                        case 1:
                        AddProductToTable(con);
                        break;
                        case 2:
                        SellProduct(con);
                        break;
                        case 3:
                        System.out.println("Guest has logged out");
                        return;
                        default:
                        System.out.println("Invalid input, please try again :(");
                    }
                        System.out.println();
                }
            }
            else{
                System.out.println("Invalid login, Username and Password does not match :(");
            }
        }
        catch(SQLException e){
            System.out.println("Error during Guest login" +e.getMessage());
        }
    }


     private static void AddProductToTable(Connection con) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-------------------------------");
        System.out.println("Add Product");
        System.out.print("Enter Product Id: ");
        int Product_Id = sc.nextInt();
        System.out.print("Enter the Product name: ");
        String Product_Name = sc.nextLine();
        System.out.print("Enter product Minimum Selling Quantity: ");
        int Min_Sell_Quantity = sc.nextInt();
        System.out.print("Enter Product price: ");
        int Price = sc.nextInt();
        System.out.print("Enter Product Quantity: ");
        int Quantity  = sc.nextInt();
        System.out.println("-------------------------------");

        try{
            String query = "insert into Product(Product_ID, Product_Name, Min_Sell_Quantity, Price, Quantity) values(?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, Product_Id);
            statement.setString(2, Product_Name);
            statement.setInt(3, Min_Sell_Quantity);
            statement.setInt(4, Price);
            statement.setInt(4, Quantity);
            int RowsAffected = statement.executeUpdate();

            if(RowsAffected>0){
                System.out.println("Product updated Successfully :)");
            }
            else{
                System.out.println("Error product not updated");
            }
        }catch(SQLException e){
            System.out.println("Error adding product to Guest"+e.getMessage());
        }
        }



    private static void SellProduct(Connection con) {
        try {
            String query = "SELECT * FROM Product";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int Product_ID = resultSet.getInt("Product_ID");
                String Product_Name = resultSet.getString("Product_Name");
                int Min_Sell_Quantity= resultSet.getInt("Min_Sell_Quantity");
                double Price = resultSet.getDouble("Price");
                int Quantity = resultSet.getInt("Quantity");

                System.out.println("Product ID: " + Product_ID);
                System.out.println("Product Name: " + Product_Name);
                System.out.println("Product Minimum sell Quantity: " + Min_Sell_Quantity);
                System.out.println("Product Price: " + Price);
                System.out.println("Product Quantity: " + Quantity);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing Guest table records: " + e.getMessage());
        }
    }



    private static void ViewProductToTable(Connection con) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the Product Id");
    int Product_ID = sc.nextInt();
    System.out.println("Enter the Number of Quantity that You Want TO Buy");
    int buyQuantity = sc.nextInt();
    try {
        String query = "SELECT price FROM Product WHERE Product_ID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, Product_ID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int Price = resultSet.getInt("Price");
            System.out.println("Your Total Cost is RS." + Price * buyQuantity);
        } else {
            System.out.println("Invalid Product Id");
        }
    } catch (SQLException e) {
        System.out.println("Error during selling the product " + e.getMessage());
    }
}
    }