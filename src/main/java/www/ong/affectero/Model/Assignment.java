package www.ong.affectero.Model;

import javafx.fxml.FXML;

import java.sql.*;
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
     private Date dayOfAffectation;
     private Date dayOfService;

     public Assignment(Integer numAffectation,Integer numEmployee,String prevLocation,String  newLocation, Date dayOfAffectation,Date dayOfService){
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

     public Date getDayOfAffectation() {
          return dayOfAffectation;
     }

     public String getNewLocation() {
          return newLocation;
     }

     public Integer getNumAffectation() {
          return numAffectation;
     }

     public Date getDayOfService() {
          return dayOfService;
     }

     public void setDayOfAffectation(Date dayOfAffectation) {
          this.dayOfAffectation = dayOfAffectation;
     }

     public String getPrevLocation() {
          return prevLocation;
     }

     public  Integer createOne() throws SQLException {
          if(getNumEmployee() != null  && !getPrevLocation().isEmpty() && !getNewLocation().isEmpty() && getDayOfAffectation() != null && getDayOfService()!= null){
               Connection connection = getConnection();
               String insertQuery = "INSERT INTO Affecter (numEmployee , prevLocation, newLocation,dayOfAffectation, dayOfService) VALUES (?,?,? ,?,?)";
               try{
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, numEmployee);
                    preparedStatement.setString(2,prevLocation);
                    preparedStatement.setString(3,newLocation);
                    preparedStatement.setDate(4, (java.sql.Date) dayOfAffectation);
                    preparedStatement.setDate(5, (java.sql.Date) dayOfService);
                    return  preparedStatement.executeUpdate();
               } catch (SQLException e){
                    printSqlExeption(e);
               }
          }

          return 0;
     }
     public List<Assignment> getAll() throws SQLException {
          List<Assignment> list = new ArrayList<>();
          Connection connection = getConnection();
          String selectQuery = "SELECT * FROM Affecter";
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(selectQuery);
          while( resultSet.next()){

               Assignment assignment = new Assignment(resultSet.getInt("numAffectation"), resultSet.getInt("numEmployee"),resultSet.getString("prevLocation"),resultSet.getString("newLocation"),resultSet.getDate("dateOfAffectation"),resultSet.getDate("dateOfService"));
               list.add(assignment);

          }
          return  list ;
     }
     public  Integer updateOne(int id) throws SQLException {
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
