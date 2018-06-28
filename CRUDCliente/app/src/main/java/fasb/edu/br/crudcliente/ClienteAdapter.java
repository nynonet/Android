package fasb.edu.br.crudcliente;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fasb.edu.br.crudcliente.modal.Cliente;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteHolder> {

    List<Cliente> lista;

    public ClienteAdapter(List<Cliente> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ClienteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClienteHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cad_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteHolder holder, final int position) {
        holder.nome.setText( lista.get(position).getNome() );
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = activity.getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("cliente", (Cliente)lista.get(position));
                activity.finish();
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista != null? lista.size():0;
    }

    public void AdicionarCliente( Cliente cliente ){
        lista.add( cliente );
        notifyItemInserted( getItemCount() );
    }

    public void AtualizarCliente( Cliente cliente ){
        lista.set( lista.indexOf(cliente), cliente );
        notifyItemChanged( lista.indexOf(cliente) );
    }

    public Activity getActivity(View view){
        Context context = view.getContext();
        while (context instanceof ContextWrapper){
            if (context instanceof Activity){
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
}
