import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class AppGUI {
    private JFrame frame;
    private DefaultListModel<String> listaModelo;
    private JList<String> listaPoblaciones;
    private Experimento experimento;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public AppGUI() {
        prepareGUI();
        experimento = new Experimento();
    }

    private void prepareGUI() {

        frame = new JFrame("Gestor de Experimentos con Bacterias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);

        listaModelo = new DefaultListModel<>();
        listaPoblaciones = new JList<>(listaModelo);

        int fontSize = 18;
        Font newFont = new Font("Arial", Font.PLAIN, fontSize);
        listaPoblaciones.setFont(newFont);
        frame.add(new JScrollPane(listaPoblaciones), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        JButton openButton = new JButton("Abrir");
        JButton newButton = new JButton("Nuevo Experimento");
        JButton addButton = new JButton("Añadir Población");
        JButton deleteButton = new JButton("Eliminar Población");
        JButton detailButton = new JButton("Detalles de Población");
        JButton saveButton = new JButton("Guardar");
        JButton saveAsButton = new JButton("Guardar Como");

        buttonPanel.add(openButton);
        buttonPanel.add(newButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(detailButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(saveAsButton);

        openButton.addActionListener(this::openExperiment);
        newButton.addActionListener(e -> {
            experimento = new Experimento();
            listaModelo.clear();
        });
        addButton.addActionListener(this::addPoblacion);
        deleteButton.addActionListener(this::deletePoblacion);
        detailButton.addActionListener(this::showDetails);
        saveButton.addActionListener(e -> saveExperiment(false));
        saveAsButton.addActionListener(e -> saveExperiment(true));

        frame.setVisible(true);
    }


    private void openExperiment(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                experimento = FileManager.cargarExperimento(fileChooser.getSelectedFile().getAbsolutePath());
                updateList();
                JOptionPane.showMessageDialog(frame, "Experimento cargado exitosamente.");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Error al abrir el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addPoblacion(ActionEvent e) {
        JTextField nameField = new JTextField();
        JTextField startDateField = new JTextField(sdf.format(new Date()));
        JTextField endDateField = new JTextField(sdf.format(new Date()));
        JTextField initialCountField = new JTextField();
        JTextField tempField = new JTextField();
        String[] luminosityOptions = {"Alta", "Media", "Baja"};
        JComboBox<String> luminosityBox = new JComboBox<>(luminosityOptions);
        JTextField initialFoodField = new JTextField();
        JTextField incrementDayField = new JTextField();
        JTextField incrementFoodField = new JTextField();
        JTextField finalFoodField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(nameField);
        panel.add(new JLabel("Fecha Inicio:"));
        panel.add(startDateField);
        panel.add(new JLabel("Fecha Fin:"));
        panel.add(endDateField);
        panel.add(new JLabel("Bacterias Iniciales:"));
        panel.add(initialCountField);
        panel.add(new JLabel("Temperatura:"));
        panel.add(tempField);
        panel.add(new JLabel("Luminosidad:"));
        panel.add(luminosityBox);
        panel.add(new JLabel("Comida Inicial:"));
        panel.add(initialFoodField);
        panel.add(new JLabel("Día Incremento:"));
        panel.add(incrementDayField);
        panel.add(new JLabel("Comida Incremento:"));
        panel.add(incrementFoodField);
        panel.add(new JLabel("Comida Final:"));
        panel.add(finalFoodField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Añadir Población", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = nameField.getText();
                Date startDate = sdf.parse(startDateField.getText());
                Date endDate = sdf.parse(endDateField.getText());
                int initialCount = Integer.parseInt(initialCountField.getText());
                double temperature = Double.parseDouble(tempField.getText());
                String luminosity = (String) luminosityBox.getSelectedItem();
                int initialFood = Integer.parseInt(initialFoodField.getText());
                int incrementDay = Integer.parseInt(incrementDayField.getText());
                int incrementFood = Integer.parseInt(incrementFoodField.getText());
                int finalFood = Integer.parseInt(finalFoodField.getText());

                Poblacion poblacion = new Poblacion(nombre, startDate, endDate, initialCount, temperature, luminosity,
                        initialFood, incrementDay, incrementFood, finalFood);
                experimento.addPoblacion(poblacion);
                updateList();
            } catch (ParseException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error al añadir la población: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deletePoblacion(ActionEvent e) {
        String selected = listaPoblaciones.getSelectedValue();
        if (selected != null) {
            experimento.removePoblacion(selected);
            updateList();
        } else {
            JOptionPane.showMessageDialog(frame, "Seleccione una población para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showDetails(ActionEvent e) {
        String selected = listaPoblaciones.getSelectedValue();
        if (selected != null) {
            Poblacion poblacion = experimento.getPoblaciones().stream()
                    .filter(p -> p.getNombre().equals(selected))
                    .findFirst()
                    .orElse(null);

            if (poblacion != null) {
                String details = String.format(
                        "Nombre: %s\nFecha Inicio: %s\nFecha Fin: %s\nBacterias Iniciales: %d\n" +
                                "Temperatura: %.1f\nLuminosidad: %s\nComida Inicial: %d\nDía Incremento: %d\n" +
                                "Comida Incremento: %d\nComida Final: %d",
                        poblacion.getNombre(),
                        sdf.format(poblacion.getFechaInicio()),
                        sdf.format(poblacion.getFechaFin()),
                        poblacion.getBacteriasIniciales(),
                        poblacion.getTemperatura(),
                        poblacion.getLuminosidad(),
                        poblacion.getComidaInicial(),
                        poblacion.getDiaIncremento(),
                        poblacion.getComidaIncremento(),
                        poblacion.getComidaFinal()
                );
                JOptionPane.showMessageDialog(frame, details, "Detalles de Población", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Población no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Seleccione una población para ver detalles.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void saveExperiment(boolean saveAs) {
        if (saveAs || experimento.getArchivoRuta() == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                experimento.setArchivoRuta(fileChooser.getSelectedFile().getAbsolutePath());
            } else {
                return;
            }
        }

        try {
            FileManager.guardarExperimento(experimento, experimento.getArchivoRuta());
            JOptionPane.showMessageDialog(frame, "Experimento guardado exitosamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateList() {
        listaModelo.clear();
        for (Poblacion p : experimento.getPoblaciones()) {
            listaModelo.addElement(p.getNombre());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGUI::new);
    }
}
