package com.example.myaplitfg.activity.ui.Armario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplitfg.Entity.service.Usuarios;
import com.example.myaplitfg.R;
import com.example.myaplitfg.adapter.AtuendosAdapter;
import com.example.myaplitfg.communication.Communication;
import com.example.myaplitfg.utils.DateSerializer;
import com.example.myaplitfg.utils.TimeSerializer;
import com.example.myaplitfg.viewModel.PrendasViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MisPrendasFragment extends Fragment implements Communication {

    // Objeto para solicitar permisos en tiempo de ejecución
    private ActivityResultLauncher<String> perReqLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
        if (result) {
            successMessage("Gracias por concedernos el permiso, oprime el boton nuevamente");
        } else {
            errorMessage("Permiso denegado, no podemos continuar");
        }
    });

    // ViewModel para manejar la lógica de los pedidos
    private PrendasViewModel prendasViewModel;

    // RecyclerView para mostrar la lista de compras
    private RecyclerView rcvPrendas;


    // Método para inflar el diseño del fragmento
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mis_prendas, container, false);
    }

    // Método llamado después de que la vista haya sido creada
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view); // Inicializa las vistas
        initViewModel(); // Inicializa el ViewModel
        initAdapter(); // Inicializa el adaptador
        loadData(); // Carga los datos de los pedidos
    }

    // Inicializa las vistas
    private void init(View v) {
        rcvPrendas = v.findViewById(R.id.rcvMisPrendas);
    }

    // Inicializa el ViewModel
    private void initViewModel() {
        prendasViewModel = new ViewModelProvider(this).get(PrendasViewModel.class);
    }

    // Inicializa el adaptador
    private void initAdapter() {

            }

    // Carga los datos de los pedidos
    private void loadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        final Gson g = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();
        String usuarioJson = sp.getString("UsuarioJson", null);
        if (usuarioJson != null) {
            final Usuarios u = g.fromJson(usuarioJson, Usuarios.class);
            this.prendasViewModel.listarPrendasPorCategoria(u.getCliente().getId()).observe(getViewLifecycleOwner(), response -> {

            });
        }
    }

     // Método para mostrar un mensaje de éxito
    public void successMessage(String message) {
        new SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                .setContentText(message).show();
    }

    // Método para mostrar un mensaje de error
    public void errorMessage(String message) {
        new SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...!")
                .setContentText(message).show();
    }

    @Override
    public void showDetails(Intent i) {

    }

    @Override
    public void exportInvoice(int idCli, int idOrden, String fileName) {

    }
}
