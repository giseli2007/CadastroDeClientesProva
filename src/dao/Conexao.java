package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.GridLayout;

public class Conexao {

	private static String url = "jdbc:sqlite:clientes.db";

	public static Connection conectar() {
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(url);
		}catch(SQLException e) {
			//B2-Q1
			JDialog dialog = new JDialog((JFrame) null, "Erro", true);
			//tentei colocar pra esse null ser a TelaCadastro, mas vi que se a conexao nao der certo a TelaCadastro nao aparece(pelo menos pra mim nao aparece)
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