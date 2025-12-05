package edu.nob.liceo.ejerevaluacionnob.controllers;

import edu.nob.liceo.ejerevaluacionnob.dao.UsuarioDAO;
import edu.nob.liceo.ejerevaluacionnob.dao.UsuarioDAOImpl;
import edu.nob.liceo.ejerevaluacionnob.model.Usuario;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UsuarioController implements Initializable {


    private UsuarioDAOImpl usuarioDAO;
    @FXML private TextField tfBuscar;
    @FXML private TableView<Usuario> table;
    @FXML private TableColumn<Usuario,Integer> tId;
    @FXML private TableColumn<Usuario,String> tNombre;
    @FXML private TableColumn<Usuario,String> tApellido;
    @FXML private TableColumn<Usuario,String> tNickname;
    @FXML private TableColumn<Usuario, LocalDate> tFechaNacimiento;

    private ObservableList<Usuario> listaUsuariosMaster;
    private ObservableList<Usuario> listaUsuarios;
    private FilteredList<Usuario> listaFiltradaUsuarios;
    private Usuario usuarioLogueado;

    public UsuarioController(){
        this.usuarioDAO= new UsuarioDAOImpl();
    }

    private Timeline debounce;

    private static final int DEBOUNCE_DELAY = 500;

    private Usuario usuario;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    listaUsuariosMaster = FXCollections.observableArrayList();
    listaFiltradaUsuarios = new FilteredList<>(listaUsuariosMaster, p -> true);

    listaUsuariosMaster.addAll(usuarioDAO.getAllUsuarios());

        tId.setCellValueFactory(new PropertyValueFactory<>("usuario_id"));
        tNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tNickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        tFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fecha_nacimiento"));
        table.setItems(listaFiltradaUsuarios);

        tfBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            listaFiltradaUsuarios.setPredicate(usuario -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (usuario.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (usuario.getNickname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

    }
    
    public void setUsuarioLogueado(Usuario usuario){
        this.usuarioLogueado=usuario;
    }

}
