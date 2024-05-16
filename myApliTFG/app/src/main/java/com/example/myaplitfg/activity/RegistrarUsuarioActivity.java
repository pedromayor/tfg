package com.example.myaplitfg.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaplitfg.Entity.service.Cliente;
import com.example.myaplitfg.Entity.service.DocumentoAlmacenado;
import com.example.myaplitfg.Entity.service.Usuarios;

import com.example.myaplitfg.R;
import com.example.myaplitfg.viewModel.ClienteViewModel;
import com.example.myaplitfg.viewModel.DocumentoAlmacenadoViewModel;
import com.example.myaplitfg.viewModel.UsuariosViewModel;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.time.LocalDateTime;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    private File f;
    private ClienteViewModel clienteViewModel;
    private UsuariosViewModel usuarioViewModel;
    private DocumentoAlmacenadoViewModel documentoAlmacenadoViewModel;
    private Button btnSubirImagen, btnGuardarDatos;
    private CircleImageView imageUser;
    private AutoCompleteTextView  dropdownLocalidad, dropdownProvincia, dropdownPais;
    private EditText edtNameUser, edtApellidos,  edtDireccion, edtTelefono,
            edtPasswordUser, edtEmail;
    private TextInputLayout txtInputNameUser, txtInputApellidos,
            txtInputDireccion,  txtInputLocalidad, txtInputProvincia,
            txtInputPais, txtInputTelefono, txtInputEmail, txtInputPasswordUser;
    private final static int LOCATION_REQUEST_CODE = 23;

    private static final int PERMISSION_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        this.init();
        this.initViewModel();
        this.spinners();
    }

    // Método para configurar los dropdowns
    private void spinners() {

        //LISTA DE LOCALIDADES
        String[] localidades = getResources().getStringArray(R.array.Localidades);
        ArrayAdapter arrayDepartamentos = new ArrayAdapter(this, R.layout.dropdown_item, localidades);
        dropdownLocalidad.setAdapter(arrayDepartamentos);
        //LISTA DE PROVINCIAS
        String[] provincias = getResources().getStringArray(R.array.provincias);
        ArrayAdapter arrayProvincias = new ArrayAdapter(this, R.layout.dropdown_item, provincias);
        dropdownProvincia.setAdapter(arrayProvincias);
        //LISTA DE PAISES
        String[] paises = getResources().getStringArray(R.array.paises);
        ArrayAdapter arrayDistritos = new ArrayAdapter(this, R.layout.dropdown_item, paises);
        dropdownPais.setAdapter(arrayDistritos);
    }

    // Método para inicializar los ViewModel
    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.clienteViewModel = vmp.get(ClienteViewModel.class);
        this.usuarioViewModel = vmp.get(UsuariosViewModel.class);
        this.documentoAlmacenadoViewModel = vmp.get(DocumentoAlmacenadoViewModel.class);
    }

    // Método que se ejecuta cuando la actividad se inicia
    @Override
    protected void onStart() {
        super.onStart();
        // Verifica si se tienen permisos para leer el almacenamiento externo
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            // Si no se tienen permisos, se solicitan
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    LOCATION_REQUEST_CODE);
        }
    }
    // Método que maneja la respuesta de la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                // Verifica si se otorgaron los permisos
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Gracias por conceder los permisos para leer el almacenamiento, estos permisos son necesarios para poder escoger tu foto de perfil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No podemos realizar el registro si no nos concedes los permisos para leer el almacenamiento.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    // Método para inicializar los elementos de la interfaz
    private void init() {
        // Inicializa los botones, campos de texto, etc.
            btnGuardarDatos = findViewById(R.id.btnGuardarDatos);
            btnSubirImagen = findViewById(R.id.btnSubirImagen);
            imageUser = findViewById(R.id.imageUser);
            edtNameUser = findViewById(R.id.edtNameUser);
            edtApellidos = findViewById(R.id.edtApellidos);
            edtTelefono = findViewById(R.id.edtTelefono);
            edtDireccion = findViewById(R.id.edtDireccion);
            edtPasswordUser = findViewById(R.id.edtPasswordUser);
            edtEmail = findViewById(R.id.edtEmail);
            //AutoCompleteTextView
            dropdownLocalidad = findViewById(R.id.dropdownLocalidad);
            dropdownProvincia = findViewById(R.id.dropdownProvincia);
            dropdownPais = findViewById(R.id.dropdownPais);
            //TextInputLayout
            txtInputNameUser = findViewById(R.id.txtInputNameUser);
            txtInputApellidos = findViewById(R.id.txtInputApellidos);
            txtInputLocalidad = findViewById(R.id.txtInputLocalidad);
            txtInputProvincia = findViewById(R.id.txtInputProvincia);
            txtInputPais = findViewById(R.id.txtInputPais);
            txtInputTelefono = findViewById(R.id.txtInputTelefono);
            txtInputDireccion = findViewById(R.id.txtInputDireccion);
            txtInputEmail = findViewById(R.id.txtInputEmail);
            txtInputPasswordUser = findViewById(R.id.txtInputPasswordUser);
            // Configura los listeners para los botones y campos de texto
            btnSubirImagen.setOnClickListener(v -> {
                this.cargarImagen();
            });
            btnGuardarDatos.setOnClickListener(v -> {
                this.guardarDatos();
            });
        // Configura los listeners para los campos de texto para eliminar los errores al editar
            edtNameUser.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    txtInputNameUser.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            edtApellidos.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    txtInputApellidos.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            edtTelefono.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    txtInputTelefono.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            edtDireccion.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    txtInputDireccion.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            dropdownLocalidad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    txtInputLocalidad.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            dropdownProvincia.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    txtInputProvincia.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            dropdownPais.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    txtInputPais.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

    // Método para guardar los datos del usuario
    private void guardarDatos() {
        Cliente c;
        if (validar()) {
            c = new Cliente();
            try {
                c.setNombre(edtNameUser.getText().toString());
                c.setApellidos(edtApellidos.getText().toString());
                c.setLocalidad(dropdownLocalidad.getText().toString());
                c.setProvincia(dropdownProvincia.getText().toString());
                c.setPais(dropdownPais.getText().toString());
                c.setTelefono(edtTelefono.getText().toString());
                c.setDireccion(edtDireccion.getText().toString());
                c.setId(0);
                LocalDateTime ldt = LocalDateTime.now(); //Para generar el nombre al archivo en base a la fecha, hora, año
                RequestBody rb = RequestBody.create(f, MediaType.parse("multipart/form-data")), somedata; //Le estamos enviando un archivo (imagen) desde el formulario
                String filename = "" + ldt.getDayOfMonth() + (ldt.getMonthValue() + 1) +
                        ldt.getYear() + ldt.getHour()
                        + ldt.getMinute() + ldt.getSecond(); //Asignar un nombre al archivo (imagen)
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", f.getName(), rb);
                somedata = RequestBody.create("profilePh" + filename, MediaType.parse("text/plain")); //Le estamos enviando un nombre al archivo.
                this.documentoAlmacenadoViewModel.save(part, somedata).observe(this, response -> {
                    if (response.getRpta() == 1) {
                        c.setFoto(new DocumentoAlmacenado());
                        c.getFoto().setId(response.getBody().getId());//Asignamos la foto al cliente
                        this.clienteViewModel.guardarCliente(c).observe(this, cResponse -> {
                            if (cResponse.getRpta() == 1) {
                                //Toast.makeText(this, response.getMessage() + ", ahora procederemos a registrar sus credenciales.", Toast.LENGTH_SHORT).show();
                                int idc = cResponse.getBody().getId();//Obtener el id del cliente.
                                Usuarios u = new Usuarios();
                                u.setEmail(edtEmail.getText().toString());
                                u.setPassword(edtPasswordUser.getText().toString());
                                u.setCliente(new Cliente(idc));
                                this.usuarioViewModel.save(u).observe(this, uResponse -> {
                                    //Toast.makeText(this, uResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    if (uResponse.getRpta() == 1) {
                                        //Toast.makeText(this, "Sus Datos y credenciales fueron creados correctamente", Toast.LENGTH_SHORT).show();
                                        successMessage("Estupendo! " + "Su información ha sido guardada con éxito en el sistema.");
                                        //this.finish();
                                       limpiarCampos();

                                    } else {
                                        toastIncorrecto("No se ha podido guardar los datos, intentelo de nuevo o mas tarde");
                                    }
                                });
                            } else {
                                toastIncorrecto("No se ha podido guardar los datos, intentelo de nuevo cuando quiera");
                            }
                        });
                    } else {
                        toastIncorrecto("No se ha podido guardar los datos, intentelo de nuevo si le apetece");
                    }
                });
            } catch (Exception e) {
                Log.e("GuardarDatos", "Error al guardar datos: " + e.getMessage()); // Impresión de registro
                warningMessage("Se ha producido un error : " + e.getMessage()); }
        } else {
            errorMessage("Por favor, complete todos los campos del formulario");
        }
    }

    private void limpiarCampos() {
        // Limpia los campos de texto
        edtNameUser.setText("");
        edtApellidos.setText("");
        dropdownLocalidad.setText("");
        dropdownProvincia.setText("");
        dropdownPais.setText("");
        edtTelefono.setText("");
        edtDireccion.setText("");
        edtEmail.setText("");
        edtPasswordUser.setText("");

        // Restablece cualquier otro estado o selección, como los dropdowns o checkboxes si los hay
        dropdownLocalidad.setSelection(0);
        dropdownProvincia.setSelection(0);
        dropdownPais.setSelection(0);

    }


    // Método para validar los campos del formulario
    private boolean validar() {
        boolean retorno = true;
        String nombre, apellidos, telefono, direccion, correo, clave,
                 dropLocalidad, dropProvincia, dropPais;
        nombre = edtNameUser.getText().toString();
        apellidos = edtApellidos.getText().toString();
        telefono = edtTelefono.getText().toString();
        direccion = edtDireccion.getText().toString();
        correo = edtEmail.getText().toString();
        clave = edtPasswordUser.getText().toString();
        dropLocalidad = dropdownLocalidad.getText().toString();
        dropProvincia = dropdownProvincia.getText().toString();
        dropPais = dropdownPais.getText().toString();
        if (this.f == null) {
            errorMessage("debe selecionar una foto de perfil");
            retorno = false;
        }
        if (nombre.isEmpty()) {
            txtInputNameUser.setError("Ingresar nombre");
            retorno = false;
        } else {
            txtInputNameUser.setErrorEnabled(false);
        }
        if (apellidos.isEmpty()) {
            txtInputApellidos.setError("Ingresar apellidos");
            retorno = false;
        } else {
            txtInputApellidos.setErrorEnabled(false);
        }
        if (telefono.isEmpty()) {
            txtInputTelefono.setError("Ingresar número telefónico");
            retorno = false;
        } else {
            txtInputTelefono.setErrorEnabled(false);
        }
        if (direccion.isEmpty()) {
            txtInputDireccion.setError("Ingresar dirección de su casa");
            retorno = false;
        } else {
            txtInputDireccion.setErrorEnabled(false);
        }
        if (correo.isEmpty()) {
            txtInputEmail.setError("Ingresar correo electrónico");
            retorno = false;
        } else {
            txtInputEmail.setErrorEnabled(false);
        }
        if (clave.isEmpty()) {
            txtInputPasswordUser.setError("Ingresar clave para el inicio de sesión");
            retorno = false;
        } else {
            txtInputPasswordUser.setErrorEnabled(false);
        }
        if (dropLocalidad.isEmpty()) {
            txtInputLocalidad.setError("Seleccionar Localidad");
            retorno = false;
        } else {
            txtInputLocalidad.setErrorEnabled(false);
        }
        if (dropProvincia.isEmpty()) {
            txtInputProvincia.setError("Seleccionar Provincia");
            retorno = false;
        } else {
            txtInputProvincia.setErrorEnabled(false);
        }
        if (dropPais.isEmpty()) {
            txtInputPais.setError("Seleccionar Pais");
            retorno = false;
        } else {
            txtInputPais.setErrorEnabled(false);
        }
        return retorno;
    }

    public void successMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                .setContentText(message).show();
    }

    public void errorMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(message).show();
    }

    public void warningMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.WARNING_TYPE).setTitleText("Notificación del Sistema")
                .setContentText(message).setConfirmText("Ok").show();
    }
    public void toastIncorrecto(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_error, (ViewGroup) findViewById(R.id.ll_custom_toast_error));
        TextView txtMensaje = view.findViewById(R.id.txtMensajeToast2);
        txtMensaje.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    // Método para cargar una imagen desde la galería
    private void cargarImagen() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(Intent.createChooser(i, "Seleccione la Aplicación"), 10);
    }

    // Método que maneja el resultado de la selección de una imagen desde la galería

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            final String realPath = getRealPathFromURI(uri);
            this.f = new File(realPath);
            this.imageUser.setImageURI(uri);
        }
    }

    // Método para obtener la ruta real de un archivo a partir de su URI
    private String getRealPathFromURI(Uri contentUri) {
        String result;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}