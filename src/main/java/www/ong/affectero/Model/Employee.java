package www.ong.affectero.Model;

import www.ong.affectero.Config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static www.ong.affectero.Config.DatabaseConnection.getConnection;
import static www.ong.affectero.Model.Location.printSqlExeption;

public class Employee {
     private Integer numEmployee;
     private  String civility;
     private  String firstname;
     private  String lastname;
     private String email;
     private  String poste;
     private  String address;


    public Employee(Integer numbEmployee, String civility, String firstname, String lastname, String email, String poste, String address) {
        this.numEmployee = numbEmployee;
        this.civility = civility;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.poste = poste;
        this.address = address;
    }

    public Integer getNumEmployee() {
        return numEmployee;
    }

    public String getAddress() {
        return address;
    }

    public String getCivility() {
        return civility;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPoste() {
        return poste;
    }

    public String getEmail() {
        return email;
    }
    public Boolean isEmpty(){
         return civility.isEmpty() && firstname.isEmpty() && lastname.isEmpty() && email.isEmpty() && poste.isEmpty() && address.isEmpty();
    }

    public static Integer createOne(String civility, String firstname, String lastname, String email, String poste, String address) throws SQLException {
        Employee employee = new Employee(0,civility,firstname,lastname,email,poste,address);
        if(!employee.isEmpty()){
            Connection connection = getConnection();
            String insertQuery = "INSERT INTO Employee (civility , firstname, lastname , email, poste , address) VALUES (?, ?, ? , ? , ? , ?)";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, civility);
                preparedStatement.setString(2, firstname);
                preparedStatement.setString(3,lastname);
                preparedStatement.setString(4,email);
                preparedStatement.setString(5,poste);
                preparedStatement.setString(6,address);
                return  preparedStatement.executeUpdate();
            } catch (SQLException e){
                printSqlExeption(e);
            }
        }

        return 0;
    }
    public static Integer deleteOne(Integer numEmployee) throws SQLException {
        Connection connection = getConnection();
        String deleteQuery = "DELETE FROM Employee WHERE numEmployee=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1,numEmployee);
            return  preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSqlExeption(e);
        }
        return 0;
    }
    public static Integer updateOne(String civility, String firstname, String lastname, String email, String poste, String address,Integer numEmployee) throws SQLException {
        Connection connection = getConnection();
        String updateQuery = "UPDATE Employee SET civility=?, firstname=? , lastname=? ,email=? , poste=? ,address=? WHERE numEmployee=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, civility);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3,lastname);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,poste);
            preparedStatement.setString(6,address);
            preparedStatement.setInt(7,numEmployee);
            return  preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSqlExeption(e);
        }
        return 0;
    }
    public  static  List<Employee> getALl() throws SQLException {
        List<Employee> list = new ArrayList<>();
        Connection connection = getConnection();
        String selectQuery = "SELECT * FROM Employee";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        while( resultSet.next()){

            Employee employee = new Employee(resultSet.getInt("numEmployee"),resultSet.getString("civility"),resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("email"),resultSet.getString("poste"),resultSet.getString("address") );
            list.add(employee);

        }
        return  list ;
    }
    public  static Employee getOne(int id) throws SQLException{
        Employee employee = null;
        Connection connection = getConnection();
        String selectQuery = "SELECT * FROM Employee WHERE numEmployee=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1,id);
        System.out.println("Here inside employee");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            employee = new Employee(resultSet.getInt("numEmployee"),resultSet.getString("civility"),resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("email"),resultSet.getString("poste"),resultSet.getString("address") );
        }
        return employee;
    }
    public static List<Employee> searchByName(String filtervalue) throws SQLException {
        List<Employee> list = new ArrayList<>();
        Connection connection = getConnection();
        String selectQuery = "SELECT * FROM Employee WHERE firstname like %?% OR lastname like %?%";
         PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
         preparedStatement.setString(1,filtervalue);
         preparedStatement.setString(2,filtervalue);
         ResultSet resultSet = preparedStatement.executeQuery();
        while( resultSet.next()){

            Employee employee = new Employee(resultSet.getInt("numEmployee"),resultSet.getString("civility"),resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("email"),resultSet.getString("poste"),resultSet.getString("address") );
            list.add(employee);

        }
        return  list ;

    }
    public static   Integer updateLocation(String newLocation,Integer numEmployee) throws SQLException {
        Connection connection = getConnection();
        String updateQuery = "UPDATE Employee SET address=? WHERE numEmployee=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1,newLocation);
            preparedStatement.setInt(2,numEmployee);
            return  preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSqlExeption(e);
        }
        return 0;
    }
}
