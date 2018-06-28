package fasb.edu.br.crudcliente;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ClienteHolder extends RecyclerView.ViewHolder {

    public TextView nome;
    public ImageButton btnEdit;
    public ImageButton btnDel;


    public ClienteHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.lab_nome);
        btnEdit = (ImageButton) itemView.findViewById(R.id.btn_edit);
        btnDel = (ImageButton) itemView.findViewById(R.id.btn_delet);
    }
}
