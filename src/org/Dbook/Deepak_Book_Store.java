package org.Dbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class Deepak_Book_Store {

	public static void main(String[] args )
					throws NumberFormatException, IOException, SQLException, ClassNotFoundException, ParseException {

				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				System.out.println("==============================================================================");
				System.out.println("*******  WELCOME TO DEEPAK BOOK STORES *********");
				System.out.println("==============================================================================");

				System.out.println("\t\t 1 --> CUSTOMER");
				System.out.println("\t\t 2 --> ADMIN");
				System.out.println("==============================================================================");
				
				System.out.println("Enter your choice:");
				int choice = Integer.parseInt(br.readLine());
				Connection conn=null;
				
				if (choice == 1) {
					System.out.println("==============================================================================");
					System.out.println("===========================    LOGIN DETAILS  ================================");
					System.out.println("==============================================================================");

					System.out.print("\t Enter your username:");
					String User_id1= br.readLine();
					System.out.print("\t Enter your password:");
					String Password1 = br.readLine();
					   try {
    						conn = MysqlConnection.getConnection();
    						PreparedStatement ps = conn.prepareStatement("select Password from customer  where User_id=?");
						 ps.setString(1, User_id1);
						ResultSet result = ps.executeQuery();
						String password = null;
						boolean login1 = false;
						while (result.next()) {
							password = result.getString("Password");
							login1 = true;
						}

						if (password.equals(Password1)) {
							System.out
									.println("==============================================================================");
							System.out
									.println("===========================   Login successful ================================");
							System.out
									.println("==============================================================================");
							System.out.println("========================= WELCOME " + User_id1.toUpperCase()+ " =======");
							System.out
									.println("==============================================================================");
						
							 String status = "Y";     
					do {
						System.out.println("==============================================================================");
						
					    System.out.println("\t\t  1 --> Oredr The Book");
						System.out.println("\t\t  2 --> Search Book by its Authors Name ");
						System.out.println("\t\t  3 --> Search Book by its Category ");
						System.out.println("\t\t  4 --> Change Password");
						System.out.println("\t\t  5 --> Exit/Logout");
						System.out.println("==============================================================================");

						System.out.println("Enter your choice:");
						int UserChoice = Integer.parseInt(br.readLine());
					     ResultSet res;
					    	
					boolean login;
					switch (UserChoice) {
					
					case 1:
						int Quantity=0;
						int price=0;
						int C_id=0;
				 	     
						System.out.println("*******************************************************************************");
						System.out.println("===================  Order The Book which You Want ============================");
						System.out.println("*******************************************************************************");
						
						
						System.out.println("Book_id \t\t Order_Date \t\t Author \t\t Price");
						conn = MysqlConnection.getConnection();
						 ps = conn.prepareStatement("select * from book  ");						
						  ResultSet book = ps.executeQuery();
						 while (book.next()) 
						 {
							System.out.println(book.getLong("Book_id") + "\t \t"+ book.getString("Book_title")+ "\t\t" + book.getString("Author") 
							+ "\t\t"+ book.getDouble("Price"));
						      }		
								System.out.println(" Enter the Book_id to Order :");
					              Quantity = Integer.parseInt(br.readLine());
		
								System.out.println(" Enter Quantity to the  order:");
							                      Quantity = Integer.parseInt(br.readLine());

					
							ps = conn.prepareStatement("insert into transactions values(?,?,?,?,?,)");
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							long Order_id = timestamp.getTime();

							ps.setLong(1, Order_id);
							ps.setDate(2, new Date(System.currentTimeMillis()));
							ps.setDouble(3, C_id);
							ps.setLong(4, Quantity);
							ps.setLong(5, price);

							ps.executeUpdate();
						
								 
								res = ps.executeQuery();
		                                   price=price*Quantity;

								ps = conn.prepareStatement("update orders set Price=? where C_id=?");
								ps.setDouble(1, price);
								ps.setInt(2, Quantity);
								

								if (ps.executeUpdate() > 0) {
								System.out.println(
										"==============================================================================");
								System.out.println("Your Odred is Place Succssfully");
								System.out.println("Your Odred is Place Succssfully:" +  "======" ) ;
								System.out.println("====================================================================");
							}
						 
								System.out.println("Do you want to continue?(Y/N)");
								status = br.readLine();

								if (status.equals("n") || status.equals("N")) {
									login = false;
								
						}		
								break;				
						
					
					case 2:
					{
						
						System.out.println("******************************************************************************");
						System.out.println("===================  Search Book Using Its Authors name ======================");
						System.out.println("******************************************************************************");
						

						System.out.println("Please Enter Author name");
						String Author= br.readLine();
						
						System.out.println("Book_Id \t Book_title \t Publish_Date \t Price");
						 
				    	ps = conn.prepareStatement("select * from  Book where Author=? ");
						ps.setString(1, Author);
						ResultSet Author1 = ps.executeQuery();

						while (Author1.next()) {
							System.out.println(Author1.getLong("Book_Id") + "\t"
									+ Author1.getString("Book_title")+Author1.getString("Author") 
									+ "\t" + Author1.getDate("Publish_Date") + "\t"
									+ Author1.getLong("Price"));
						
						     }			System.out.println("Do you want to continue?(Y/N)");
								status = br.readLine();

								if (status.equals("n") || status.equals("N")) {
									login = false;
								}

								break;
						
					}
					
					case 3: 
		
					
						System.out.println("*************************************************************************");
						System.out.println("===================  Search Book Using Its Category ======================");
						System.out.println("**************************************************************************");

						System.out.println("Please Enter Category name");
						String Category = br.readLine();

						
						System.out.println("Book_Id \t Book_title \t Publish_Date \t Price");
						
						 
				    	ps = conn.prepareStatement("select * from  Book where Category=? ");
						ps.setString(1, Category);
						ResultSet Category1 = ps.executeQuery();

						while (Category1.next()) {
							System.out.println(Category1.getLong("Book_Id") + "\t"
									+ Category1.getString("Book_title")+Category1.getString("Author")+Category1.getString("Author") 
									+ "\t" + Category1.getDate("Publish_Date") + "\t"
									+ Category1.getLong("Price"));
						
						     }								
						        System.out.println("Do you want to continue?(Y/N)");
								status = br.readLine();

								if (status.equals("n") || status.equals("N")) {
									login = false;
								}

						break;
                        
					
					case 4:
						int Userid=0;
						System.out.println("Enter the old password:");
						String oldPassword = br.readLine();

						System.out.println("Enter the new password:");
						String newPassword = br.readLine();

						System.out.println("Re-enter the new password:");
						String rePassword = br.readLine();

						ps = conn.prepareStatement("select * from customer where User_id=?");
						ps.setInt(1, Userid);
						res = ps.executeQuery();
						String existingPassword = null;
						while (res.next()) {
							existingPassword = res.getString("password");

						}

						if (existingPassword.equals(oldPassword)) {
							if (newPassword.equals(rePassword)) {
								ps = conn.prepareStatement("update customer set password=? where User_id=?");
								ps.setString(1, newPassword);
								ps.setInt(2, Userid);

								if (ps.executeUpdate() > 0) {
									System.out.println(
											"==============================================================================");
									System.out.println("Password changed successfully!!");
									System.out.println(
											"==============================================================================");

								} else {
									System.out.println(
											"==============================================================================");
									System.out.println("Problem in password changed!!");
									System.out.println(
											"==============================================================================");

								}

							} else {
								System.out.println(
										"==============================================================================");
								System.out.println("New password and retype password must be same!!");
								System.out.println(
										"==============================================================================");

							}
						} else {
							System.out.println(
									"==============================================================================");
							System.out.println("Old password is wrong!!");
							System.out.println(
									"==============================================================================");

						}
						System.out.println("Do you want to continue?(Y/N)");
						status = br.readLine();

						if (status.equals("n") || status.equals("N")) {
							login = false;
						}

						break;
					case 5:
						login = false;
						break;

					default:
						System.out.println("Wrong Choice!!");
						break;
						
   
                      } 
					
					} while(true);	
						}
					 else {
							System.out
									.println("==============================================================================");
							System.out
									.println("================================  Wrong password  ============================");
							System.out
									.println("==============================================================================");
						}
					} catch (Exception e) {
						System.out.println(e);
						System.out.println("==============================================================================");
						System.out.println("===========================  Wrong username/password  ========================");
						System.out.println("==============================================================================");

					}
				}else if (choice == 2) {
					    
              			System.out.println("==============================================================================");
              			System.out.println("===========================    LOGIN DETAILS  ================================");
              			System.out.println("==============================================================================");

              			System.out.print("\t Enter your username:");
              			String User_id = br.readLine();
              			System.out.print("\t Enter your password:");
              			String Password = br.readLine();
              			
            			conn = MysqlConnection.getConnection();
            			PreparedStatement ps = conn.prepareStatement("select * from admin where User_id=?");
            			ps.setString(1,User_id);
            			ResultSet result = ps.executeQuery();
            			 String status = "Y";
            			String password = null;
            			String Cuspassword = null;
            			boolean login = false;
            			while (result.next()) {
            				password = result.getString("password");
            				login = true;
            				
            				System.out.println("==============================================================================");
            				 System.out.println("*****************WELCOM ADMIN" + User_id.toUpperCase()+ "  ******************");
                   			System.out.println("==============================================================================");
                   			
            			
            				do {
            					System.out.println("==============================================================================");
            					System.out.println("\t\t  1 --> Add new User");
            					System.out.println("\t\t  2 --> Add new books");
            					System.out.println("\t\t  3 --> View Orders ");
            					System.out.println("\t\t  4 --> Remove books");
            					System.out.println("\t\t  5 --> Change Password");
            					System.out.println("\t\t  6 --> Exit/Logout");
            					System.out.println("==============================================================================");

            					System.out.println("Enter your choice:");
            					int operation = Integer.parseInt(br.readLine());
            					
            					switch (operation) {
            					case 1:		
            						System.out.println("******************************************************************************");
            						System.out.println("\t\t  1 --> Add new User");
            						System.out.println("==============================================================================");
            						
            					
            						System.out.println("Enter customer Id:");
            						String C_id = br.readLine();
            						
            							System.out.println("Enter customer's full name:");
            						String Full_name = br.readLine();

            						System.out.println("Enter customer's Address:");
            						String Address = br.readLine();

            						System.out.println("Gender:");
            						String Gender = br.readLine();

            						System.out.println("Date of Birth:(dd/MM/YYYY)");
            						String Date_Of_Birth = br.readLine();

            						System.out.println("Enter phone number:");
            						long Mobail = Long.parseLong(br.readLine());
            						
            						System.out.println("Enter email id:");
            						String Email_id = br.readLine();
            						
            						System.out.println("Enter Pincode: ");
            						String Pincode = br.readLine();
            						
            						System.out.println("Enter UserId: ");
            						String Cus_id = br.readLine();

            						System.out.println(" Enter Password: ");
            						String C_Password = br.readLine();


            						System.out.println("Re-enter account password: ");
            						String repassword = br.readLine();

            						conn = MysqlConnection.getConnection();
            				         ps = conn.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?)");
            						
            						ps.setString(1, C_id);
            						ps.setString(2, Full_name);
            						ps.setString(3, Address);
            						ps.setString(4, Gender);
            						SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
            						java.util.Date utilDate = format.parse(Date_Of_Birth);
            						java.sql.Date date = new java.sql.Date(utilDate.getTime());
            						ps.setDate(5, date);
            						ps.setLong(6, Mobail);
            						ps.setString(7, Email_id);
            						ps.setString(8, Pincode);
            						ps.setString(9, User_id);
            						ps.setString(10, Password);
            						
            						
            								     if (ps.executeUpdate() > 0) {
            									System.out.println(
            											"==============================================================================");
            									System.out.println("Account created successfully!!");
            									System.out.println(
            											"==============================================================================");
            								     }
            								 	System.out.println("Do you want to continue?(Y/N)");
            									status = br.readLine();

            									if (status.equals("n") || status.equals("N")) {
            										login = false;
            									}
            								     break;

            					    case 2:

                						System.out.println("******************************************************************************");
                						System.out.println("\t\t  1 --> Add New Book ");
                						System.out.println("==============================================================================");
            					    	
            							System.out.println("Enter Book Id :");
            		    				long Book_id = Long.parseLong(br.readLine());

            							System.out.println("Enter Book Title:");
            							String Book_title = br.readLine();

            							System.out.println("Author:");
            							String Author = br.readLine();

            							System.out.println("Date of Publish:(dd/MM/YYYY)");
            							String Publish_Date = br.readLine();

            							System.out.println("Enter Category ");
            							String Category = br.readLine();

            							System.out.println("Enter Book Price ");
            							String Price = br.readLine();


            							System.out.println("Enter Quantity:");
            							long Book_Quantity = Long.parseLong(br.readLine());


            							ps = conn.prepareStatement("insert into book values(?,?,?,?,?,?,?)");
            							ps.setLong(1, Book_id);
            							ps.setString(2, Book_title);
            							ps.setString(3, Author);
            							SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/YYYY");
            							java.util.Date utilDate1 = format1.parse(Publish_Date);
            							java.sql.Date date1 = new java.sql.Date(utilDate1.getTime());
            							ps.setDate(4, date1);
            							ps.setString(5, Category );
            							ps.setString(6, Price);
 
            							if (ps.executeUpdate() > 0) {
            								System.out.println(
            										"==============================================================================");
            								System.out.println("-----------------New Book Added successfully!!---------------------");
            								System.out.println(
            										"==============================================================================");

            							}else {
            								    System.out.println("===============================================================");
            									System.out.println(" Unable To Add New Book!!");
            									System.out.println("===============================================================");
            								}
            							break;
            					    case 3:

                						System.out.println("******************************************************************************");
                						System.out.println("============= ======   VIEW THE TOTAL ORDERS  ================= ==============");
                						System.out.println("==============================================================================");
            					    	
            					    	
            					    	
            					    	System.out.println("Book_Id \t Book_title \t Publish_Date \t Price");

            							ps = conn.prepareStatement("select * from  orders ");
            							ResultSet orders = ps.executeQuery();

            							while (orders.next()) {
            								System.out.println(orders.getLong("Order_Id") + "\t"
            										+ orders.getDate("Order Date")+orders.getLong("C_Id") 
            										+ "\t" + orders.getDate("Publish_Date") + "\t"
            										+ orders.getInt("Price")+"\t"+ orders.getLong("Quantity"));
            							
            							     }
            					    break;
            					    case 4:

                						System.out.println("******************************************************************************");
                						System.out.println("============= ======   Remove The Book From Store ============= ==============");
                						System.out.println("=============================================================================="); 	
            							
                						
                						System.out.println("Enter Book_id which  you want to remove :");
            							
            							Book_id= Long.parseLong(br.readLine());

            							ps = conn.prepareStatement("delete from book where Book_id=?");
            							ps.setLong(1, Book_id);

            							if (ps.executeUpdate() > 0) {
            								System.out.println(
            										"==============================================================================");
            								System.out.println(" Book  Removed  successfully!!");
            								System.out.println(
            										"==============================================================================");

            							} else {
            								System.out.println(
            										"==============================================================================");
            								System.out.println("Unable To Remove The Book !!");
            								System.out.println("===================================================================");

            							}

            					    break;

            					    case 5:
            					    	System.out.println("******************************************************************************");
                						System.out.println("============= ======   Change the Account Password ============= ==============");
                						System.out.println("=============================================================================="); 	
            							
            					    
            							System.out.println("Enter the old password:");
            							String oldPassword = br.readLine();

            							System.out.println("Enter the new password:");
            							String newPassword = br.readLine();

            							System.out.println("Re-enter the new password:");
            							String rePassword = br.readLine();

            							ps = conn.prepareStatement("select * from admin where User_id=?");
            							ps.setString(1, User_id);

            							result = ps.executeQuery();
            							String existingPassword = null;
            							while (result.next()) {
            								existingPassword = result.getString("password");

            							}

            							if (existingPassword.equals(oldPassword)) {
            								if (newPassword.equals(rePassword)) {
            									ps = conn.prepareStatement("update admin set password=? where User_id=?");
            									ps.setString(1, newPassword);
            									ps.setString(2, User_id);

            									if (ps.executeUpdate() > 0) {
            										System.out.println(
            												"==============================================================================");
            										System.out.println("Password changed successfully!!");
            										System.out.println(
            												"==============================================================================");

            									} else {
            										System.out.println(
            												"==============================================================================");
            										System.out.println("Problem in password changed!!");
            										System.out.println(
            												"==============================================================================");

            									}

            								} else {
            									System.out.println(
            											"==============================================================================");
            									System.out.println("New password and retype password must be same!!");
            									System.out.println(
            											"==============================================================================");

            								}
            							} else {
            								System.out.println(
            										"==============================================================================");
            								System.out.println("Old password is wrong!!");
            								System.out.println(
            										"==============================================================================");

            							}

            					    }
            					        
            					}while(true);

            			}
            			


					}
			}
		}
