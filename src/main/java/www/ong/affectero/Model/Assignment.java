package www.ong.affectero.Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static www.ong.affectero.Config.DatabaseConnection.getConnection;
import static www.ong.affectero.Model.Location.printSqlExeption;

public class Assignment {

     private  Integer numAffectation;
     private  Integer numEmployee;
     private  String prevLocation;
     private String  newLocation;
     private String dayOfAffectation;
     private String dayOfService;

     public Assignment(Integer numAffectation, Integer numEmployee, String prevLocation, String  newLocation, String dayOfAffectation, String dayOfService){
          this.numAffectation = numAffectation;
          this.numEmployee = numEmployee;
          this.dayOfAffectation= dayOfAffectation;
          this.dayOfService = dayOfService;
          this.newLocation = newLocation;
          this.prevLocation = prevLocation;
     }



    public Integer getNumEmployee() {
        return numEmployee;
    }

     public String getDayOfAffectation() {
          return dayOfAffectation;
     }

     public String getNewLocation() {
          return newLocation;
     }

     public Integer getNumAffectation() {
          return numAffectation;
     }

     public String getDayOfService() {
          return dayOfService;
     }

     public void setDayOfAffectation(Date dayOfAffectation) {
          this.dayOfAffectation = String.valueOf(dayOfAffectation);
     }

     public String getPrevLocation() {
          return prevLocation;
     }

     public  boolean isEmpty(){
          return  numAffectation == null && numEmployee==null && prevLocation.isEmpty() && newLocation.isEmpty() && dayOfService == null && dayOfAffectation == null;
     }

     public Integer createOne() throws SQLException {
          if(true){
               Connection connection = getConnection();
               String insertQuery = "INSERT INTO Affecter (numEmployee , prevLocation, newLocation,dateOfAffectation, dateOfService) VALUES (?,?,? ,?,?)";
               try{
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(1, numEmployee);
                    preparedStatement.setString(2,prevLocation);
                    preparedStatement.setString(3,newLocation);
                    preparedStatement.setString(4,  dayOfAffectation);
                    preparedStatement.setString(5,  dayOfService);
                    System.out.println("Here inside assignment");
                    int affectedRow = preparedStatement.executeUpdate();
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                       return  generatedKeys.getInt(1);
                    }
               } catch (SQLException e){
                   printSqlExeption(e);
               }
          }

          return 0;
     }
     public static List<Assignment> getAll() throws SQLException {
          List<Assignment> list = new ArrayList<>();
          Connection connection = getConnection();
          String selectQuery = "SELECT * FROM Affecter";
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(selectQuery);
          while( resultSet.next()){

               Assignment assignment = new Assignment(resultSet.getInt("numAffectation"), resultSet.getInt("numEmployee"),resultSet.getString("prevLocation"),resultSet.getString("newLocation"),String.valueOf(resultSet.getDate("dateOfAffectation")),String.valueOf(resultSet.getDate("dateOfService")));
               list.add(assignment);

          }
          return  list ;
     }
     public static Assignment getOne(int id) throws SQLException {
         Assignment assignment = null;
         Connection connection = getConnection();
         String selectQuery = "SELECT * FROM Affecter WHERE numAffectation=?";
         PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
         preparedStatement.setInt(1,id);
         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()){
             assignment = new Assignment(resultSet.getInt("numAffectation"),resultSet.getInt("numEmployee"),resultSet.getString("prevLocation"),resultSet.getString("newLocation"),resultSet.getString("dateOfAffectation"), resultSet.getString("dateOfService"));

         }
         return assignment;
     }
     public  Integer updateOne(int id) throws SQLException {
          Connection connection = getConnection();
          String updateQuery = "UPDATE Affecter SET  numEmployee=?,prevLocation=? , newLocation=? ,dateOfAffectation=? , dateOfService=? WHERE numAffectation";
          try{
               PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
               preparedStatement.setInt(1,numEmployee);
               preparedStatement.setString(2,prevLocation);
               preparedStatement.setString(3,newLocation);
               preparedStatement.setString(4, dayOfAffectation);
               preparedStatement.setString(5, dayOfService);
               preparedStatement.setInt(6,numAffectation);

               return  preparedStatement.executeUpdate();
          }catch (SQLException e){
               printSqlExeption(e);
          }
          return 0;
     }
     public List<ResultSet> getAllEmployeeNotAffected() throws SQLException {
          List<ResultSet>  notAffectedList = new ArrayList<>();
          Connection connection = getConnection();
          String notAffectedQuery = "SELECT  Employee.numEmployee, Employee.firstname , Employee.lastname , Employee.address, Employee.email " +
                                   "FROM Employee "+
                                    "LEFT JOIN Affecter ON Employee.numEmployee = Affecter.numAffectation " +
                                    "WHERE Affecter.numEmployee IS NULL";
          Statement statement = connection.createStatement();

          ResultSet resultSet = statement.executeQuery(notAffectedQuery);
         while(resultSet.next()){
              System.out.println(resultSet.getString("numEmployee"));
              notAffectedList.add(resultSet);
         }
         return notAffectedList;

     }
     public  static Integer deleteOne(int id) throws SQLException {
         Connection connection = getConnection();
         String deleteQuery = "DELETE FROM Affecter WHERE numAffectation=?";
         try{
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
             preparedStatement.setInt(1,id);
             return  preparedStatement.executeUpdate();
         }catch (SQLException e){
             printSqlExeption(e);
         }
         return 0;
     }
}
