package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDAO;
import model.Cliente;
import model.ClienteTableModel;
import util.DadosMockados;
import util.Regex;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.regex.*;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;


public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textTelefone;
	private JTextField textBuscar;
	private JFormattedTextField campoDataInicio;
	private JFormattedTextField campoDataFim;
	private JTable table;
	private ClienteTableModel modelo;
	private ArrayList<Cliente> clientes;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private ClienteDAO dao;
	private TableRowSorter<ClienteTableModel> sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		dao = new ClienteDAO();
		clientes = new ArrayList<Cliente>();
		//DadosMockados.carregar(clientes);
		modelo = new ClienteTableModel(dao.listar());
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 658);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image = new ImageIcon(TelaCadastro.class.getResource("/img/cadastro.png"));
		Image imageScaled = image.getImage();
		Image novaImg = imageScaled.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(novaImg));
		lblNewLabel.setBounds(23, 35, 112, 92);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CADASTRO DE CLIENTES");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(280, 63, 209, 24);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 127, 690, 195);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textNome = new JTextField();
		textNome.setBounds(12, 29, 339, 35);
		panel.add(textNome);
		textNome.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(12, 99, 339, 35);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(393, 29, 285, 35);
		panel.add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(12, 12, 60, 17);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(12, 80, 60, 17);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setBounds(394, 12, 60, 17);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("De:");
		lblNewLabel_5.setBounds(12, 140, 60, 17);
		panel.add(lblNewLabel_5);

		campoDataInicio = criarCampoData();
		campoDataInicio.setBounds(12, 158, 140, 27);
		campoDataInicio.setColumns(10);
		panel.add(campoDataInicio);

		JLabel lblNewLabel_6 = new JLabel("Até:");
		lblNewLabel_6.setBounds(170, 140, 60, 17);
		panel.add(lblNewLabel_6);

		campoDataFim = criarCampoData();
		campoDataFim.setBounds(170, 158, 140, 27);
		campoDataFim.setColumns(10);
		panel.add(campoDataFim);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(393, 99, 285, 35);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBackground(new Color(255, 255, 255));
		rdbtnMasculino.setBounds(0, 8, 130, 25);
		panel_2.add(rdbtnMasculino);
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBackground(new Color(255, 255, 255));
		rdbtnFeminino.setBounds(155, 8, 130, 25);
		panel_2.add(rdbtnFeminino);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnFeminino);
		buttonGroup.add(rdbtnMasculino);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(23, 336, 690, 77);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		//ButtonAction action = new ButtonAction();
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = textNome.getText().toString();
				String email = textEmail.getText().toString();
				String telefone = textTelefone.getText().toString();
				String sexo = rdbtnMasculino.isSelected() ? "Masculino" : "Feminino";
				if (!Regex.validaNome(nome)) {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Nome inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!Regex.validaEmail(email)) {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Email inválido", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!Regex.validaTelefone(telefone)) {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Telefone inválido", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (nome.isBlank() || email.isBlank() || telefone.isBlank() 
						|| sexo.isBlank()) {
					JOptionPane.showMessageDialog(TelaCadastro.this, 
							"Preencha todos os campos", "Alerta", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					Cliente cliente = new Cliente(nome, telefone, email, sexo);
					modelo.addCliente(cliente);					
					dao.inserir(cliente);
					JOptionPane.showMessageDialog(TelaCadastro.this, 
							"Cliente adicionado com sucesso!", "Sucesso!", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				textNome.setText("");
				textTelefone.setText("");
				textEmail.setText("");
				buttonGroup.clearSelection();
				
			}
			
		});
		btnSalvar.setBounds(23, 25, 105, 27);
		panel_1.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indice = table.getSelectedRow();
				if (indice >= 0) {
					Cliente cliente = modelo.getCliente(indice);
					dao.excluir(cliente.getId());
					modelo.removerCliente(indice);
				}
				else {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Selecione um cliente para excluir!", 
							"Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}
		});
		btnExcluir.setBounds(153, 25, 105, 27);
		panel_1.add(btnExcluir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buscarNome = textBuscar.getText().trim();
				
				if(buscarNome.isBlank()) {
					return;
				}
				
				List<Cliente> encontrados = dao.buscarPorNome(buscarNome);
				if(encontrados.isEmpty()) {
					JOptionPane.showMessageDialog(TelaCadastro.this,
							"Nenhum cliente encontrado com esse nome.",
							"Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				modelo.atualizarTabela((ArrayList<Cliente>) encontrados);
			}
		});
		
		btnBuscar.setBounds(278, 25, 105, 27);
		panel_1.add(btnBuscar);
		
		textBuscar = new JTextField();
		textBuscar.setBounds(409, 25, 269, 27);
		panel_1.add(textBuscar);
		textBuscar.setColumns(10);

		JButton btnBuscarData = new JButton("Buscar por Data");
		btnBuscarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoInicio = campoDataInicio.getText();
				String textoFim = campoDataFim.getText();

				DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				LocalDate dataInicio;
				LocalDate dataFim;

				try {
					dataInicio = LocalDate.parse(textoInicio, formatoEntrada);
					dataFim = LocalDate.parse(textoFim, formatoEntrada);
				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(TelaCadastro.this,
							"Datas inválidas. Por favor, insira datas no formato dd/MM/yyyy.",
							"Erro",
							JOptionPane.ERROR_MESSAGE);
							return;
				}
				List<Cliente> encontrados = dao.buscarPorIntervaloDeData(dataInicio, dataFim);

				if(encontrados.isEmpty()) {
					JOptionPane.showMessageDialog(TelaCadastro.this,
							"Nenhum cliente encontrado nesse intervalo de datas.",
							"Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				modelo.atualizarTabela((ArrayList<Cliente>) encontrados);
			}
			
		});

		btnBuscarData.setBounds(330, 158, 150, 27);
		panel.add(btnBuscarData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 432, 690, 196);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		sorter = new TableRowSorter<>(modelo);
		table.setRowSorter(sorter);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 737, 23);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnNewMenu.add(mntmAbrir);
		mntmAbrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jFileChooser = new JFileChooser();
				if (jFileChooser.showOpenDialog(TelaCadastro.this) 
						== JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					carregarDados(file, modelo);
						
				}
				
			}
		});
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnNewMenu.add(mntmSalvar);
		mntmSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				if (jFileChooser.showSaveDialog(TelaCadastro.this) 
						== JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					salvarDados(file, modelo);
				}
				
			}
		});
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmSair);
		
		JMenu mnNewMenuEditar = new JMenu("Editar");
		menuBar.add(mnNewMenuEditar);
		
		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				if (linha < 0) {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Selecione um cliente para atualizar!", 
							"Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}else {
					Cliente cliente = modelo.getCliente(linha);
					TelaAtualizar dialogo = new TelaAtualizar(TelaCadastro.this, cliente);
					dialogo.setVisible(true);
					if (dialogo.getClienteEditado() != null) {
						dao.atualizar(dialogo.getClienteEditado());
						modelo.atualizarTabela(dao.listar());
					}
				}
			}
		});
		mnNewMenuEditar.add(mntmAtualizar);
		
		JMenu mnFerramentas = new JMenu("Ferramentas");
		menuBar.add(mnFerramentas);

		JMenuItem mntmImportarCsvValidado = new JMenuItem("Importar CSV Validado");
		mntmImportarCsvValidado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				if(jFileChooser.showOpenDialog(TelaCadastro.this) == JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					importarCsvValidado(file, modelo);
				}
			}
		});
		mnFerramentas.add(mntmImportarCsvValidado);

		JMenuItem mntmExportaRelatorio = new JMenuItem("Exportar Relatório");
		mntmExportaRelatorio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setSelectedFile(new File("relatorio_clientes.txt"));
				if (jFileChooser.showSaveDialog(TelaCadastro.this) == JFileChooser.APPROVE_OPTION) {
					File file = jFileChooser.getSelectedFile();
					exportarRelatorio(file, modelo);
				}
			}
		});
		mnFerramentas.add(mntmExportaRelatorio);
		
		JMenu mnNewMenu_3 = new JMenu("Sobre");
		menuBar.add(mnNewMenu_3);

}

	private void importarCsvValidado(File file, ClienteTableModel modelo) {
    int importados = 0;
    int rejeitados = 0;
    StringBuilder linhasInvalidas = new StringBuilder();

    try (FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr)) {

        String cabecalho = br.readLine(); // pula o cabeçalho

        if (cabecalho == null) {
            JOptionPane.showMessageDialog(TelaCadastro.this,
                    "O arquivo está vazio.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String linha;
        int numeroLinha = 1; // já contando o cabeçalho como linha 1

        while ((linha = br.readLine()) != null) {
            numeroLinha++;

            if (linha.isBlank()) {
                continue; // ignora linhas vazias no meio do arquivo
            }

            String[] campos = linha.split(",");

            if (campos.length != 4) {
                rejeitados++;
                linhasInvalidas.append("Linha ").append(numeroLinha)
                        .append(": número de colunas incorreto\n");
                continue;
            }

            String nome = campos[0].trim();
            String telefone = campos[1].trim();
            String email = campos[2].trim();
            String sexo = campos[3].trim();

            if (!Regex.validaNome(nome)) {
                rejeitados++;
                linhasInvalidas.append("Linha ").append(numeroLinha)
                        .append(": nome inválido (\"").append(nome).append("\")\n");
                continue;
            }

            if (!Regex.validaEmail(email)) {
                rejeitados++;
                linhasInvalidas.append("Linha ").append(numeroLinha)
                        .append(": email inválido (\"").append(email).append("\")\n");
                continue;
            }

            if (!Regex.validaTelefone(telefone)) {
                rejeitados++;
                linhasInvalidas.append("Linha ").append(numeroLinha)
                        .append(": telefone inválido (\"").append(telefone).append("\")\n");
                continue;
            }

            if (sexo.isBlank()) {
                rejeitados++;
                linhasInvalidas.append("Linha ").append(numeroLinha)
                        .append(": sexo não informado\n");
                continue;
            }

            Cliente cliente = new Cliente(nome, telefone, email, sexo);
            modelo.addCliente(cliente);
            dao.inserir(cliente);
            importados++;
        }

        if (importados == 0 && rejeitados == 0) {
            JOptionPane.showMessageDialog(TelaCadastro.this,
                    "O arquivo está vazio.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String resumo = "Registros importados: " + importados
                + "\nRegistros rejeitados: " + rejeitados;

        if (rejeitados > 0) {
            JOptionPane.showMessageDialog(TelaCadastro.this,
                    resumo + "\n\nLinhas inválidas:\n" + linhasInvalidas,
                    "Importação concluída com rejeições",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(TelaCadastro.this,
                    resumo, "Importação concluída com sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(TelaCadastro.this,
                "Erro ao importar o arquivo: " + e.getMessage(), "Erro",
                JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

	private void exportarRelatorio(File file, ClienteTableModel modelo) {
		try (FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer)) {
				
			int totalClientes = modelo.getRowCount();
			int totalMasculino = 0;
			int totalFeminino = 0;

			for(int i = 0; i < totalClientes; i++) {
				String sexo = (String) modelo.getValueAt(i, 3);
				if("Masculino".equalsIgnoreCase(sexo)) {
					totalMasculino++;
				} else if ("Feminino".equalsIgnoreCase(sexo)) {
					totalFeminino++;
				}
			}

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataGeracao = LocalDateTime.now().format(formato);

        bw.write("RELATÓRIO DE CLIENTES");
        bw.newLine();
        bw.newLine();
        bw.write("Total de clientes: " + totalClientes);
        bw.newLine();
        bw.write("Clientes masculinos: " + totalMasculino);
        bw.newLine();
        bw.write("Clientes femininos: " + totalFeminino);
        bw.newLine();
        bw.newLine();
        bw.write("--- Listagem de Clientes ---");
        bw.newLine();

        for (int i = 0; i < totalClientes; i++) {
            String nome = (String) modelo.getValueAt(i, 0);
            String telefone = (String) modelo.getValueAt(i, 1);
            String email = (String) modelo.getValueAt(i, 2);
            String sexo = (String) modelo.getValueAt(i, 3);

            bw.write("Nome: " + nome + " | Telefone: " + telefone
                    + " | Email: " + email + " | Sexo: " + sexo);
            bw.newLine();
        }

        bw.newLine();
        bw.write("Relatório gerado em: " + dataGeracao);

        JOptionPane.showMessageDialog(TelaCadastro.this,
                "Relatório exportado com sucesso!", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(TelaCadastro.this,
                "Erro ao gerar o relatório: " + e.getMessage(), "Erro",
                JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

	private JFormattedTextField criarCampoData() {
			try {
				MaskFormatter mask = new MaskFormatter("##/##/####");
				mask.setPlaceholderCharacter('_');
				return new JFormattedTextField(mask);
			} catch (ParseException e) {
				return new JFormattedTextField();
			}
		}
	private void carregarDados(File file, ClienteTableModel modelo) {
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			modelo.limparDados();
			String cabecalho = bufferedReader.readLine();
			
	        if (cabecalho == null) {
	            JOptionPane.showMessageDialog(TelaCadastro.this, "O arquivo está vazio.",
	            		"Aviso", JOptionPane.WARNING_MESSAGE);//B2-Q2
	            return;
	        }
	        
			String linha = "";
			while((linha = bufferedReader.readLine()) != null) {
				String campos [] = linha.split(",");
				if (campos.length == 4) {
					String nome = campos[0];
					String telefone = campos[1];
					String email = campos[2];
					String sexo = campos[3];
					Cliente cliente = new Cliente(nome, telefone, email, sexo);
					modelo.addCliente(cliente);
				}
			}
		}catch(IOException e) {
			JOptionPane.showMessageDialog(TelaCadastro.this, "Erro ao ler o arquivo.",
					"Erro", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
				fileReader.close();				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}		
		
	}
	
	private void salvarDados(File file, ClienteTableModel modelo) {
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("Nome,Telefone,Email,Sexo");
			bufferedWriter.newLine();
			for (int i=0; i < modelo.getRowCount(); i++) {
				String nome = (String) modelo.getValueAt(i, 0);
				String telefone = (String) modelo.getValueAt(i, 1);
				String email = (String) modelo.getValueAt(i, 2);
				String sexo = (String) modelo.getValueAt(i, 3);
				bufferedWriter.write(nome+","+telefone+","+email+","+sexo);
				bufferedWriter.newLine();
			}
		}catch(IOException e) {
			JOptionPane.showMessageDialog(TelaCadastro.this, "Erro ao exportar relatório.",
					"Erro", JOptionPane.ERROR_MESSAGE);//B2-Q2
	        e.printStackTrace();
		}finally {
			try {				
				bufferedWriter.close();
				fileWriter.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}