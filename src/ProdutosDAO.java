/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
   
    public int cadastrarProduto (ProdutosDTO p){
          
         conn = new conectaDAO().connectDB();
         int status;
        String sql = "INSERT INTO produtos(nome,valor,status) VALUES "
                + "(?,?,?)";
        try {

            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setString(1, p.getNome());
            st.setInt(2, p.getValor());
            st.setString(3,p.getStatus());

            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
        
        
        
    }
     public int venderProduto(int id) {
         
        int status;
        conn = new conectaDAO().connectDB();

        try {
           
        prep=conn.prepareStatement("UPDATE produtos SET  status='Vendido' WHERE id=? "); 
          //PreparedStatement st = this.conn.prepareStatement(sql);
            
           
        
            prep.setInt(1,id);
        
            
            status = prep.executeUpdate();
            return status;

        } catch (SQLException ex) {
            System.out.println("Erro ao editar o filme " + ex.getErrorCode());
            return ex.getErrorCode();
        }
    }
    
   
      public List<ProdutosDTO> listagem() {
        
        String sql = "select * FROM produtos ";
      conn = new conectaDAO().connectDB();
       
        try {
            prep = conn.prepareStatement(sql);

            
            resultset = prep.executeQuery();
            
            List<ProdutosDTO> lista = new ArrayList<>();
            
            while (resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar AQUII:" + ex.getMessage());
            return null;
        }
        
    }
}


