package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Cliente;

public class ClienteDAO {
	
	public void inserir(Cliente cliente) {
		String sql = "INSERT INTO clientes "
				+ "(nome, telefone, email, sexo, data_cadastro) VALUES (?,?,?,?,?)";	
		
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSexo());
			stmt.setString(5, cliente.getDataCadastro());//B3-Q3
			stmt.execute();
			
			stmt.close();
			conexao.close();
			
		}catch(SQLException e) {
			//B2-Q1
			JDialog dialog = new JDialog((JFrame) null, "Erro", true);
			dialog.setSize(850, 100);
			dialog.setResizable(true);
			dialog.add(new JLabel("Erro na conexão com o banco de dados! \n\nErro:  "+ e));
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		String sql = "DELETE FROM clientes WHERE id=?";
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			
			stmt.close();
			conexao.close();
			
		}catch(SQLException e) {
			//B2-Q1
			JDialog dialog = new JDialog((JFrame) null, "Erro", true);
			dialog.setSize(850, 100);
			dialog.setResizable(true);
			dialog.add(new JLabel("Erro na conexão com o banco de dados! \n\nErro:  "+ e));
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cliente> listar(){
		String sql = "SELECT * FROM clientes";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String telefone = resultSet.getString("telefone");
				String email = resultSet.getString("email");
				String sexo = resultSet.getString("sexo");
				String dataCadastro = resultSet.getString("data_cadastro");//B3-Q3
				Cliente cliente = new Cliente(id, nome, telefone, email, sexo, dataCadastro);
				clientes.add(cliente);
			}
		}catch(SQLException e) {
			//B2-Q1
			JDialog dialog = new JDialog((JFrame) null, "Erro", true);
			dialog.setSize(850, 100);
			dialog.setResizable(true);
			dialog.add(new JLabel("Erro na conexão com o banco de dados! \n\nErro:  "+ e));
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			e.printStackTrace();
		}
		return clientes;
	}
	
	public void atualizar(Cliente cliente) {
		String sql = "UPDATE clientes SET nome=?, "
				+ "telefone=?, email=?, sexo=?, data_cadastro=? WHERE id=?";
		try {
			Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSexo());
			stmt.setString(5, cliente.getDataCadastro());//B3-Q3
			stmt.setInt(6, cliente.getId());
			stmt.executeUpdate();
			stmt.close();
			conexao.close();
		}catch(SQLException e) {
			//B2-Q1
			JDialog dialog = new JDialog((JFrame) null, "Erro", true);
			dialog.setSize(850, 100);
			dialog.setResizable(true);
			dialog.add(new JLabel("Erro na conexão com o banco de dados! \n\nErro:  "+ e));
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			e.printStackTrace();
		}
	}
	
	public List<Cliente> buscarPorNome(String nome) {
		String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
		List<Cliente> clientes = new ArrayList<>();
		
		try(Connection conexao = Conexao.conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql)) {
			
			stmt.setString(1, "%" + nome + "%");
			
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String nomeCliente = rs.getString("nome");
					String telefone = rs.getString("telefone");
					String email = rs.getString("email");
					String sexo = rs.getString("sexo");
					
					clientes.add(new Cliente(id, nomeCliente, telefone, email, sexo));
				}
			}
		} catch (SQLException e) {
			//B2-Q1
			JDialog dialog = new JDialog((JFrame) null, "Erro", true);
			dialog.setSize(850, 100);
			dialog.setResizable(true);
			dialog.add(new JLabel("Erro na conexão com o banco de dados! \n\nErro:  "+ e));
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			e.printStackTrace();
		}
		return clientes;
	}

	public List<Cliente> buscarPorIntervaloDeData(LocalDate dataInicio, LocalDate dataFim) {
    String sql = "SELECT * FROM clientes WHERE data_cadastro BETWEEN ? AND ?";
    List<Cliente> clientes = new ArrayList<>();

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, dataInicio.toString()); // já sai como yyyy-MM-dd
        stmt.setString(2, dataFim.toString());

        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String sexo = resultSet.getString("sexo");
                String dataCadastro = resultSet.getString("data_cadastro");

                Cliente cliente = new Cliente(id, nome, telefone, email, sexo);
                cliente.setDataCadastro(dataCadastro);
                clientes.add(cliente);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return clientes;
}
}
