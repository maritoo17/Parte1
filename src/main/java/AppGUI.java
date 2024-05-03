import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class AppGUI {
    private JFrame frame;
    private DefaultListModel<String> poblacionListModel;
    private JList<String> poblacionList;
    private FileManager fileManager;
    private Experimento experimento;

    public AppGUI() {
        experimento = new Experimento();
        fileManager = new FileManager(experimento);
        prepareGUI();
    }

    private void prepareGUI() {
        frame = new JFrame("Gestor de Experimentos");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.NORTH);
        JButton openButton = new JButton("Abrir");
        JButton saveButton = new JButton("Guardar");
        JButton addButton = new JButton("Añadir Población");
        JButton deleteButton = new JButton("Eliminar Población");
        JButton detailsButton = new JButton("Detalles Población");

        panel.add(openButton);
        panel.add(saveButton);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(detailsButton);

        poblacionListModel = new DefaultListModel<>();
        poblacionList = new JList<>(poblacionListModel);
        frame.add(new JScrollPane(poblacionList), BorderLayout.CENTER);

        openButton.addActionListener(e -> openFile());
        saveButton.addActionListener(e -> saveFile());
        addButton.addActionListener(e -> addPoblacion());
        deleteButton.addActionListener(e -> deletePoblacion());
        detailsButton.addActionListener(e -> showDetails());

        frame.setVisible(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                fileManager.abrirArchivo(fileChooser.getSelectedFile().getAbsolutePath());
                experimento = fileManager.getExperimento();
                updatePoblacionList();
                JOptionPane.showMessageDialog(frame, "Experimento cargado exitosamente.");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Error al abrir el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                fileManager.guardarArchivo(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(frame, "Experimento guardado exitosamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addPoblacion() {
        String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre de la nueva población:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            try {
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingrese la cantidad de bacterias:"));
                Poblacion nuevaPoblacion = new Poblacion(nombre, cantidad);
                experimento.addPoblacion(nuevaPoblacion);
                updatePoblacionList();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número válido de bacterias.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "El nombre de la población no puede estar vacío.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePoblacion() {
        String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre de la población a borrar:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            experimento.removePoblacion(nombre);
            updatePoblacionList();
        } else {
            JOptionPane.showMessageDialog(frame, "El nombre de la población no puede estar vacío.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDetails() {
        String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre de la población para ver detalles:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            Poblacion poblacion = experimento.getPoblacion(nombre);
            if (poblacion != null) {
                JOptionPane.showMessageDialog(frame, poblacion.toString(), "Detalles de Población", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Población no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "El nombre de la población no puede estar vacío.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePoblacionList() {
        poblacionListModel.clear();
        experimento.getPoblacionesNombres().forEach(poblacionListModel::addElement);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGUI::new);
    }
}
