package www.ong.affectero.Model;

import www.ong.affectero.Config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static www.ong.affectero.Config.DatabaseConnection.getConnection;

public class Location {
    private  String design ;
    private  String province;
    private  Integer locationId;
    public Location(Integer id, String design, String province){
          this.design = design;
          this.province = province;
          this.locationId = id;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public String getDesign(){
         return design;
    }
    public String getProvince(){
        return province;
    }
    public  static int create(String design, String province) throws  SQLException {
        if(!design.isEmpty() && !province.isEmpty()){
             Connection connection = getConnection();
             String insertQuery = "INSERT INTO Lieu (design , province) VALUES (?, ?)";
        try{
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             preparedStatement.setString(1, design);
             preparedStatement.setString(2,province);
             return  preparedStatement.executeUpdate();
         } catch (SQLException e){
             printSqlExeption(e);
         }
        }

        return 0;
    }

    public static List<Location> getALl() throws SQLException{
        List<Location> list = new ArrayList<>();
        Connection connection = getConnection();
        String selectQuery = "SELECT * FROM Lieu";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
         while( resultSet.next()){
             System.out.println(resultSet.getInt("idLieu"));
             Location location = new Location(resultSet.getInt("idLieu"),resultSet.getString("design"), resultSet.getString("province") );
             list.add(location);

         }
        return  list ;
    }
    public  static  int updateOne(int id, String design, String province) throws SQLException{

        Connection connection = getConnection();
        String updateQuery = "UPDATE Lieu SET province=? , design=? WHERE idLieu=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1,province);
            preparedStatement.setString(2,design);
            preparedStatement.setInt(3,id);
            return  preparedStatement.executeUpdate();
        }catch (SQLException e){
             printSqlExeption(e);
        }
       return 0;
    }
    public  static int deleteOne(int id) throws SQLException{
        Connection connection = getConnection();
        String deleteQuery = "DELETE FROM Lieu WHERE idLieu=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1,id);
            return  preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSqlExeption(e);
        }
        return 0;
    }


    private static  void printSqlExeption(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
