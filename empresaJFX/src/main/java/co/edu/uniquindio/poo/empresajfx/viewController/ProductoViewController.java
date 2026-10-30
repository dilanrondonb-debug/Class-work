package co.edu.uniquindio.poo.empresajfx.viewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.empresajfx.App;
import co.edu.uniquindio.poo.empresajfx.controller.ProductoController;
import co.edu.uniquindio.poo.empresajfx.model.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductoViewController {

    ProductoController productoController;
    ObservableList<Producto> listProductos = FXCollections.observableArrayList();
    Producto selectedProducto;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Producto> tblListProducto;

    @FXML
    private TableColumn<Producto, String> tbcCodigo;

    @FXML
    private TableColumn<Producto, String> tbcNombre;

    @FXML
    private TableColumn<Producto, String> tbcPrecio;

    private App app;

    @FXML
    void onAgregarProducto() {
        agregarProducto();
    }

    @FXML
    void onActualizarProducto() {
        actualizarProducto();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarProducto();
    }

    @FXML
    void initialize() {
        productoController = new ProductoController(App.empresa);
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerProductos();
        tblListProducto.setItems(listProductos);
        listenerSelection();
    }

    private void initDataBinding() {
        tbcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
    }

    private void obtenerProductos() {
        listProductos.addAll(productoController.obtenerListaProductos());
    }

    private void refrescarTabla() {
        listProductos.setAll(productoController.obtenerListaProductos());
        tblListProducto.refresh();
    }

    private void listenerSelection() {
        tblListProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProducto = newSelection;
            mostrarInformacionProducto(selectedProducto);
        });
    }

    private void mostrarInformacionProducto(Producto producto) {
        if (producto != null) {
            txtCodigo.setText(producto.getCodigo());
            txtNombre.setText(producto.getNombre());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
        }
    }

    private Optional<Producto> construirProducto() {
        Optional<Producto> productoConstruido = Optional.empty();
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String textoPrecio = txtPrecio.getText().trim();
        if (codigo.isEmpty() || nombre.isEmpty() || textoPrecio.isEmpty()) {
            mostrarMensaje("Todos los campos son obligatorios");
        } else {
            try {
                double precio = Double.parseDouble(textoPrecio);
                if (precio < 0) {
                    mostrarMensaje("El precio no puede ser negativo");
                } else {
                    productoConstruido = Optional.of(new Producto(codigo, nombre, precio));
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("El precio debe ser un número válido");
            }
        }
        return productoConstruido;
    }

    private void agregarProducto() {
        Optional<Producto> productoConstruido = construirProducto();
        if (productoConstruido.isPresent()) {
            String mensaje = productoController.crearProducto(productoConstruido.get());
            mostrarMensaje(mensaje);
            refrescarTabla();
            limpiarSeleccion();
        }
    }

    private void eliminarProducto() {
        if (selectedProducto == null) {
            mostrarMensaje("Seleccione un producto de la tabla");
        } else {
            String mensaje = productoController.eliminarProducto(selectedProducto.getCodigo());
            mostrarMensaje(mensaje);
            refrescarTabla();
            limpiarSeleccion();
        }
    }

    private void actualizarProducto() {
        if (selectedProducto == null) {
            mostrarMensaje("Seleccione un producto de la tabla");
        } else {
            Optional<Producto> productoConstruido = construirProducto();
            if (productoConstruido.isPresent()) {
                String mensaje = productoController.actualizarProducto(selectedProducto.getCodigo(), productoConstruido.get());
                mostrarMensaje(mensaje);
                refrescarTabla();
                limpiarSeleccion();
            }
        }
    }

    private void limpiarSeleccion() {
        tblListProducto.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void limpiarCamposProducto() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setApp(App app) {
        this.app = app;
    }
}