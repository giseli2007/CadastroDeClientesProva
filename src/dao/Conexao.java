package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Conexao {

	private static String url = "jdbc:sqlite:clientes.db";

	static {
		//vai rodar enquando a classe é carregada pra nao dar erro na conexao
		try {
			Connection conexao = DriverManager.getConnection(url);
			Statement stmt = conexao.createStatement();
			stmt.execute("ALTER TABLE clientes ADD COLUMN data_cadastro TEXT");
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			//faz nada se a coluna já existir
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