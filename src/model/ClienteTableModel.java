package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private static final String [] columnNames = {"Nome", "Telefone", "Email", "Sexo", "Data de Cadastro"};//B3-Q4
	private ArrayList<Cliente> clientes;
	
	public ClienteTableModel(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return clientes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = clientes.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return cliente.getNome();
		case 1:
			return cliente.getTelefone();
		case 2:
			return cliente.getEmail();
		case 3:
			return cliente.getSexo();
		case 4:
			return cliente.getDataCadastro(); //B3-14
		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];		
	}
	
	public void addCliente(Cliente cliente) {
		this.clientes.add(cliente);
		//Notifica a interface gráfica que houve atualização
		fireTableRowsInserted(clientes.size()-1, clientes.size()-1);
	}
	
	public int buscarCliente(String nome) {
		for (int i=0; i < clientes.size(); i++) {
			if (clientes.get(i).getNome().equalsIgnoreCase(nome)) {
				return i;
			}
		}
		return -1;
	}
	
	public void removerCliente(int indice) {
		if (indice < clientes.size() && indice >= 0) {
			clientes.remove(indice);
			//Notifica a interface gráfica que houve atualização na lista
			fireTableRowsDeleted(indice, indice);
		}
	}
	
	public void limparDados() {
		clientes.clear();
		fireTableDataChanged();
	}
	
	public Cliente getCliente(int index) {
		return clientes.get(index);
	}
	
	public void atualizarTabela(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
		fireTableDataChanged();
	}
	

}
