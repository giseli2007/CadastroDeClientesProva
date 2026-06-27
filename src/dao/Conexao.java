package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Conexao {

	private static String url = "jdbc:sqlite:clientes.db";

	static {
	    try {
	        Connection conexao = DriverManager.getConnection(url);
	        Statement stmt = conexao.createStatement();

	        try {
	        	//coloca a coluna data_cadastro no banco de dados
	            stmt.execute("ALTER TABLE clientes ADD COLUMN data_cadastro TEXT");
	        } catch (Exception e) {
	            //caso nao de pra colocar a coluna, não faz nada
	        }
	        //Correcao do B3-Q2: vai preencher as data_cadastro de todos os que estão como null no banco de dados
	        stmt.execute("UPDATE clientes SET data_cadastro = DATE('now') WHERE data_cadastro IS NULL");

	        stmt.close();
	        conexao.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public static Connection conectar() {
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(url);
		}catch(SQLException e) {
			JDialog dialog = new JDialog((JFrame) null, "Erro", true);
			dialog.setSize(850, 100);
			dialog.setResizable(true);
			dialog.add(new JLabel("Erro na conexão com o banco de dados! \n\nErro:  "+ e));
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			e.printStackTrace();
		}
		return conexao;
	}
}