package view.funcionario;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.funcionario.Conexao;
import controller.funcionario.ControllerFuncionario;
import model.funcionario.Funcionario;

public class ViewFuncionario implements ActionListener, MouseListener {
    
    private JFrame frmGestaoDeFuncionario;
    private JTable listagem;
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtContacto;
    private JTextField txtDepartamento;
    private JTextField txtGenero;
    private JTextField txtEstadoCivil;
    private JTextField txtDiasTrabalhados;
    private JTextField txtSalarioDiario;
    
    private JButton btnAdicionar, btnListar, btnEditar, btnRemover, btnLimpar, btnNovo;
    private DefaultTableModel tableModel;
    
    public ViewFuncionario() {
        initialize();
    }
    
    private void initialize() {
        frmGestaoDeFuncionario = new JFrame();
        frmGestaoDeFuncionario.setFont(new Font("Dialog", Font.BOLD, 14));
        frmGestaoDeFuncionario.setTitle("GESTÃO DE FUNCIONÁRIOS");
        frmGestaoDeFuncionario.setBounds(100, 100, 992, 571);
        frmGestaoDeFuncionario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGestaoDeFuncionario.getContentPane().setLayout(null);
        
        // Painel de dados
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "DADOS DO FUNCIONÁRIO", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(15, 16, 654, 270);
        frmGestaoDeFuncionario.getContentPane().add(panel);
        panel.setLayout(null);
        
        // Componentes do painel de dados
        JLabel lblCodigo = new JLabel("CÓDIGO:");
        lblCodigo.setBounds(15, 30, 80, 20);
        panel.add(lblCodigo);
        
        JLabel lblNome = new JLabel("NOME:");
        lblNome.setBounds(15, 70, 80, 20);
        panel.add(lblNome);
        
        JLabel lblContacto = new JLabel("CONTATO:");
        lblContacto.setBounds(15, 110, 80, 20);
        panel.add(lblContacto);
        
        JLabel lblDepartamento = new JLabel("DEPARTAMENTO:");
        lblDepartamento.setBounds(15, 150, 100, 20);
        panel.add(lblDepartamento);
        
        JLabel lblGenero = new JLabel("GÊNERO:");
        lblGenero.setBounds(350, 30, 80, 20);
        panel.add(lblGenero);
        
        JLabel lblEstadoCivil = new JLabel("ESTADO CIVIL:");
        lblEstadoCivil.setBounds(350, 70, 100, 20);
        panel.add(lblEstadoCivil);
        
        JLabel lblDiasTrabalhados = new JLabel("DIAS TRAB.:");
        lblDiasTrabalhados.setBounds(350, 110, 100, 20);
        panel.add(lblDiasTrabalhados);
        
        JLabel lblSalarioDiario = new JLabel("SALÁRIO DIÁRIO:");
        lblSalarioDiario.setBounds(350, 150, 100, 20);
        panel.add(lblSalarioDiario);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 25, 200, 30);
        panel.add(txtCodigo);
        
        txtNome = new JTextField();
        txtNome.setBounds(120, 65, 200, 30);
        panel.add(txtNome);
        
        txtContacto = new JTextField();
        txtContacto.setBounds(120, 105, 200, 30);
        panel.add(txtContacto);
        
        txtDepartamento = new JTextField();
        txtDepartamento.setBounds(120, 145, 200, 30);
        panel.add(txtDepartamento);
        
        txtGenero = new JTextField();
        txtGenero.setBounds(460, 25, 170, 30);
        panel.add(txtGenero);
        
        txtEstadoCivil = new JTextField();
        txtEstadoCivil.setBounds(460, 65, 170, 30);
        panel.add(txtEstadoCivil);
        
        txtDiasTrabalhados = new JTextField();
        txtDiasTrabalhados.setBounds(460, 105, 170, 30);
        panel.add(txtDiasTrabalhados);
        
        txtSalarioDiario = new JTextField();
        txtSalarioDiario.setBounds(460, 145, 170, 30);
        panel.add(txtSalarioDiario);
        
        // Painel de operações
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "OPERAÇÕES", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(671, 21, 284, 265);
        frmGestaoDeFuncionario.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        btnAdicionar = new JButton("ADICIONAR");
        btnAdicionar.setBounds(53, 16, 192, 43);
        btnAdicionar.addActionListener(this);
        panel_1.add(btnAdicionar);
        
        btnListar = new JButton("LISTAR");
        btnListar.setBounds(53, 53, 192, 43);
        btnListar.addActionListener(this);
        panel_1.add(btnListar);
        
        btnEditar = new JButton("EDITAR");
        btnEditar.setBounds(53, 94, 192, 35);
        btnEditar.addActionListener(this);
        panel_1.add(btnEditar);
        
        btnRemover = new JButton("REMOVER");
        btnRemover.setBounds(53, 129, 192, 35);
        btnRemover.addActionListener(this);
        panel_1.add(btnRemover);
        
        btnLimpar = new JButton("LIMPAR");
        btnLimpar.setBounds(53, 166, 192, 35);
        btnLimpar.addActionListener(this);
        panel_1.add(btnLimpar);
        
        btnNovo = new JButton("NOVO");
        btnNovo.setBounds(53, 206, 192, 43);
        btnNovo.addActionListener(this);
        panel_1.add(btnNovo);
        
        // Painel de listagem
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "LISTAGEM DE FUNCIONÁRIOS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(15, 284, 940, 215);
        frmGestaoDeFuncionario.getContentPane().add(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane, BorderLayout.CENTER);
        
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "CÓDIGO", "NOME", "CONTATO", "DEPARTAMENTO", "GÊNERO", "ESTADO CIVIL", "DIAS TRABALHADOS", "SALÁRIO DIÁRIO"
            }
        );
        
        listagem = new JTable(tableModel);
        scrollPane.setViewportView(listagem);
        listagem.addMouseListener(this);
        
        frmGestaoDeFuncionario.setVisible(true);
    }
    
    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtContacto.setText("");
        txtDepartamento.setText("");
        txtGenero.setText("");
        txtEstadoCivil.setText("");
        txtDiasTrabalhados.setText("");
        txtSalarioDiario.setText("");
    }
    
    private void listarFuncionarios() {
        try {
            ArrayList<Funcionario> funcionarios = ControllerFuncionario.listaFuncionario();
            tableModel.setRowCount(0); // Limpa a tabela
            
            for (Funcionario f : funcionarios) {
                tableModel.addRow(new Object[] {
                    f.getCodigo(),
                    f.getNome(),
                    f.getContato(),
                    f.getDepartamento(),
                    f.getGeneroDescricao(),
                    f.getEstadoCivilDescricao(),
                    f.getDiasTrabalhados(),
                    f.getSalarioDiario()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionários: " + ex.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionar) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String nome = txtNome.getText();
                int contacto = Integer.parseInt(txtContacto.getText());
                String departamento = txtDepartamento.getText();
                char genero = txtGenero.getText().isEmpty() ? ' ' : txtGenero.getText().charAt(0);
                char estadoCivil = txtEstadoCivil.getText().isEmpty() ? ' ' : txtEstadoCivil.getText().charAt(0);
                int diasTrabalhados = Integer.parseInt(txtDiasTrabalhados.getText());
                double salarioDiario = Double.parseDouble(txtSalarioDiario.getText());
                
                ControllerFuncionario.adicionarFuncionario(codigo, nome, contacto, departamento, 
                                                         genero, estadoCivil, diasTrabalhados, salarioDiario);
                JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");
                limparCampos();
                listarFuncionarios();
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar funcionário: " + ex.getMessage());
            }
        }
        else if (e.getSource() == btnListar) {
            listarFuncionarios();
        }
        else if (e.getSource() == btnEditar) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String nome = txtNome.getText();
                int contacto = Integer.parseInt(txtContacto.getText());
                String departamento = txtDepartamento.getText();
                char genero = txtGenero.getText().isEmpty() ? ' ' : txtGenero.getText().charAt(0);
                char estadoCivil = txtEstadoCivil.getText().isEmpty() ? ' ' : txtEstadoCivil.getText().charAt(0);
                int diasTrabalhados = Integer.parseInt(txtDiasTrabalhados.getText());
                double salarioDiario = Double.parseDouble(txtSalarioDiario.getText());
                
                ControllerFuncionario.actualizarFuncionario(codigo, nome, contacto, departamento, 
                                                          genero, estadoCivil, diasTrabalhados, salarioDiario);
                JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
                limparCampos();
                listarFuncionarios();
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário: " + ex.getMessage());
            }
        }
        else if (e.getSource() == btnRemover) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                ControllerFuncionario.removerFuncionario(codigo);
                JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
                limparCampos();
                listarFuncionarios();
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao remover funcionário: " + ex.getMessage());
            }
        }
        else if (e.getSource() == btnLimpar) {
            limparCampos();
        }
        else if (e.getSource() == btnNovo) {
            limparCampos();
            txtCodigo.requestFocus();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == listagem) {
            int linha = listagem.getSelectedRow();
            if (linha >= 0) {
                txtCodigo.setText(tableModel.getValueAt(linha, 0).toString());
                txtNome.setText(tableModel.getValueAt(linha, 1).toString());
                txtContacto.setText(tableModel.getValueAt(linha, 2).toString());
                txtDepartamento.setText(tableModel.getValueAt(linha, 3).toString());
                txtGenero.setText(tableModel.getValueAt(linha, 4).toString().substring(0, 1));
                txtEstadoCivil.setText(tableModel.getValueAt(linha, 5).toString().substring(0, 1));
                txtDiasTrabalhados.setText(tableModel.getValueAt(linha, 6).toString());
                txtSalarioDiario.setText(tableModel.getValueAt(linha, 7).toString());
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}
    
    public static void main(String[] args) throws SQLException {
        new ViewFuncionario();
    }
}