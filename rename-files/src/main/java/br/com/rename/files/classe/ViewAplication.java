package br.com.rename.files.classe;

import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ViewAplication {

	private JFrame frmRenomeadorDeArquivos;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAplication window = new ViewAplication();
					window.frmRenomeadorDeArquivos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewAplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRenomeadorDeArquivos = new JFrame();
		frmRenomeadorDeArquivos.getContentPane().setFont(new Font("Constantia", Font.PLAIN, 11));
		frmRenomeadorDeArquivos.setTitle("Renomeador de Arquivos");
		frmRenomeadorDeArquivos.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmRenomeadorDeArquivos.setBounds(100, 100, 460, 235);
		frmRenomeadorDeArquivos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Iniciar");
		
		JLabel lblDigiteOCaminho = new JLabel("Digite o caminho da pasta");
		JLabel lblCarregando = new JLabel("");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RenameService service = new RenameService();
				String caminho = textField.getText();
				if(!caminho.isEmpty()) {
					new Thread() {
						public void run() {
							for(int i = 0;i < 101 ; i++) {
								try {
									sleep(100);
									progressBar.setValue(i);
									if(progressBar.getValue() == 1) {
										lblCarregando.setText("Renomeando Arquivos...");
									}
								}catch (InterruptedException e) {
									// TODO: handle exception
								}
							}
						}
					}.start();	
					try {
						service.renameFiles(caminho + "\\");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,"O caminho da Pasta deve ser Preenchido.");
				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(frmRenomeadorDeArquivos.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(143)
							.addComponent(lblCarregando)
							.addGap(281))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDigiteOCaminho, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblDigiteOCaminho, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCarregando)
					.addContainerGap(391, Short.MAX_VALUE))
		);
		frmRenomeadorDeArquivos.getContentPane().setLayout(groupLayout);
		
	}
}
