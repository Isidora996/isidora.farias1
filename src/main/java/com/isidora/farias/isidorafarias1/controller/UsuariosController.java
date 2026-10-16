package com.isidora.farias.isidorafarias1.controller;

import com.isidora.farias.isidorafarias1.dto.UsuarioDto;
import com.isidora.farias.isidorafarias1.model.UsuariosModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsuariosController {

    @FXML private TableView<UsuarioDto> tableUsuarios;
    @FXML private TableColumn<UsuarioDto, Integer> colId;
    @FXML private TableColumn<UsuarioDto, String> colNombre;
    @FXML private TableColumn<UsuarioDto, String> colEmail;
    @FXML private TableColumn<UsuarioDto, String> colContrasena;

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtContrasena;

    @FXML private Button btnAgregar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;

    @FXML private Label lblEstado;

    private final UsuariosModel usuariosModel = new UsuariosModel();


    private ObservableList<UsuarioDto> usuarios;


    private UsuarioDto usuarioSeleccionado;


    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContrasena.setCellValueFactory(new PropertyValueFactory<>("contrasena"));

        usuarios = FXCollections.observableArrayList();
        tableUsuarios.setItems(usuarios);

        tableUsuarios.getSelectionModel().selectedItemProperty()
                .addListener((obs, anterior, nuevo) -> seleccionarUsuario(nuevo));

        usuariosModel.agregarUsuario(new UsuarioDto(null, "isidora", "1234", "isidora@mail.cl"));
        usuariosModel.agregarUsuario(new UsuarioDto(null, "profe", "javafx", "profe@mail.cl"));

        refrescarTabla();
        limpiarFormulario();
    }

    @FXML
    public void handleAgregarUsuario(ActionEvent actionEvent) {
        try {
            validarFormulario(null);

            UsuarioDto nuevo = new UsuarioDto();
            nuevo.setNombre(txtNombre.getText().trim());
            nuevo.setEmail(txtEmail.getText().trim());
            nuevo.setContrasena(txtContrasena.getText());

            usuariosModel.agregarUsuario(nuevo);

            refrescarTabla();
            limpiarFormulario();
            mostrarEstado("Usuario \"" + nuevo.getNombre() + "\" agregado.");
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    public void handleModificarUsuario(ActionEvent actionEvent) {
        try {
            if (usuarioSeleccionado == null) {
                throw new Exception("Primero selecciona un usuario de la tabla.");
            }

            validarFormulario(usuarioSeleccionado.getId());

            UsuarioDto editado = new UsuarioDto();
            editado.setId(usuarioSeleccionado.getId());
            editado.setNombre(txtNombre.getText().trim());
            editado.setEmail(txtEmail.getText().trim());
            editado.setContrasena(txtContrasena.getText());

            usuariosModel.modificarUsuario(editado);

            refrescarTabla();
            limpiarFormulario();
            mostrarEstado("Usuario modificado correctamente.");
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    public void handleEliminarUsuario(ActionEvent actionEvent) {
        if (usuarioSeleccionado == null) {
            mostrarError("Primero selecciona un usuario de la tabla.");
            return;
        }

        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Confirmar");
        confirmar.setHeaderText("Eliminar usuario");
        confirmar.setContentText("Seguro que quieres eliminar a \""
                + usuarioSeleccionado.getNombre() + "\"?");

        confirmar.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                String nombre = usuarioSeleccionado.getNombre();
                usuariosModel.eliminarUsuario(usuarioSeleccionado);
                refrescarTabla();
                limpiarFormulario();
                mostrarEstado("Usuario \"" + nombre + "\" eliminado.");
            }
        });
    }

    @FXML
    public void handleLimpiar(ActionEvent actionEvent) {
        tableUsuarios.getSelectionModel().clearSelection();
        limpiarFormulario();
        mostrarEstado("Formulario limpio.");
    }


    private void seleccionarUsuario(UsuarioDto usuario) {
        this.usuarioSeleccionado = usuario;

        if (usuario == null) {
            limpiarFormulario();
            return;
        }

        txtId.setText(String.valueOf(usuario.getId()));
        txtNombre.setText(usuario.getNombre());
        txtEmail.setText(usuario.getEmail());
        txtContrasena.setText(usuario.getContrasena());

        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
    }

    private void refrescarTabla() {
        usuarios.setAll(usuariosModel.getUsuarios());
    }

    private void limpiarFormulario() {
        usuarioSeleccionado = null;

        txtId.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtContrasena.clear();

        btnAgregar.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);

        txtNombre.requestFocus();
    }

    private void validarFormulario(Integer idActual) throws Exception {
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String contrasena = txtContrasena.getText();

        if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }
        if (nombre.length() < 3) {
            throw new Exception("El nombre debe tener al menos 3 caracteres.");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new Exception("El email no es valido.");
        }
        if (contrasena.length() < 4) {
            throw new Exception("La contrasena debe tener al menos 4 caracteres.");
        }
        if (usuariosModel.existeNombre(nombre, idActual)) {
            throw new Exception("Ya existe un usuario con el nombre \"" + nombre + "\".");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No se pudo completar la operacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarEstado(String mensaje) {
        lblEstado.setText(mensaje + "   |   Total de usuarios: " + usuarios.size());
    }
}
