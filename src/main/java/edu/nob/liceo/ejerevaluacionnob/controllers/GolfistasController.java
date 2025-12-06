package edu.nob.liceo.ejerevaluacionnob.controllers;

import edu.nob.liceo.ejerevaluacionnob.DatosPais;
import edu.nob.liceo.ejerevaluacionnob.dao.GolfistasDAO;
import edu.nob.liceo.ejerevaluacionnob.model.Golfistas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GolfistasController implements Initializable {


    @FXML private TableView<Golfistas> tablaGolfistas;
    @FXML    private TableColumn<Golfistas, Integer> colId;
    @FXML    private TableColumn<Golfistas, String> colNombre;
    @FXML    private TableColumn<Golfistas, String> colApellido;
    @FXML    private TableColumn<Golfistas, Integer> colEdad;
    @FXML    private TableColumn<Golfistas, String> colPais;
    @FXML    private TableColumn<Golfistas, String> colTipoPalo;


    @FXML    private Label lblJugadorId;
    @FXML    private TextField tfNombre;
    @FXML    private TextField tfApellido;
    @FXML    private Slider sliderEdad;
    @FXML    private Label lblValorEdad; // El numerito al lado del slider

    @FXML    private ComboBox<String> cbPais;
    @FXML    private ComboBox<String> cbTipoPalo;


    @FXML    private Button btnAnadir;
    @FXML    private Button btnModificar;
    @FXML    private Button btnEliminar;
    @FXML    private Button btnLimpiar;

    public GolfistasController() {}

    private ObservableList<Golfistas> listaGolfistas;

    private GolfistasDAO golfistasDAO;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaGolfistas = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colTipoPalo.setCellValueFactory(new PropertyValueFactory<>("tipoPalo"));

        cbPais.setItems(FXCollections.observableArrayList(DatosPais.listapaises));
        cbTipoPalo.getItems().addAll("Driver", "Madera", "Hibrido", "Hierro", "Wedge", "Putter");


        cargarGolfistasdelaBD();

    }

    private void cargarGolfistasdelaBD() {
        listaGolfistas.clear();
        List<Golfistas> golfistasBD= golfistasDAO.getAllGolfistas();
        listaGolfistas.addAll(golfistasBD);
    }

}
