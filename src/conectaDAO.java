
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    Connection conn = null;
    public Connection connectDB(){
       
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root","imadeveloper");
            System.out.println("Conexao concluída ");
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
    public void desconectar() {
        try {
            conn.close();
            System.out.println("Conexao com o banco de dados fechada");
        } catch (SQLException ex) {

        }
    }
}
