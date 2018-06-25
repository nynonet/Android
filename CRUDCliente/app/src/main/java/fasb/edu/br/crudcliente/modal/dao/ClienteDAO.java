package fasb.edu.br.crudcliente.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fasb.edu.br.crudcliente.modal.Cliente;

public class ClienteDAO {
    private String TABELA = "clientes";
    private dbGateway gw;

    /**
     * Função responsável por criar a tabela no SQLite
     * @return SQL de criação da tabela.
     */
    public static final String CreateTable() {
        return "CREATE TABLE clientes (\n" +
                "\tID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tnome varchar(120) not null,\n" +
                "\tuf char(2),\n" +
                "\tstatus smallint\n" +
                ");";
    }

    /**
     * Função responsável por atualizar a tabela no SQLite
     * @return SQL de atualização conforme versão da tabela.
     */
    public static String UpdateTable(int version) {
        switch (version) {
            case 1:
                return "";
            default:
                return "";
        }
    }

    public ClienteDAO(Context context) {
        gw = dbGateway.getInstace(context);
    }

    public boolean Insert(Cliente c) {

        ContentValues cv = new ContentValues();
        cv.put( "nome", c.getNome() );
        cv.put( "uf", c.getUf() );
        cv.put( "status", (c.isStatus()? 1 : 0) );

        return gw.getDatabase().insert(TABELA, null, cv) > 0;
    }

    public boolean Update(Cliente c) {
        return true;
    }

    public boolean Delete(Cliente c) {
        return true;
    }

    public Cliente getCliente(int id) {
        return new Cliente();
    }

    public List<Cliente> getClientes() {
        List<Cliente> resultado = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes", null);

        while (cursor.moveToNext()) {
            resultado.add( new Cliente(
                                    cursor.getInt(cursor.getColumnIndex("ID")),
                                    cursor.getString(cursor.getColumnIndex("nome")),
                                    cursor.getString(cursor.getColumnIndex("uf")),
                                    (cursor.getInt(cursor.getColumnIndex("status"))>0)
            ));
        }
        cursor.close();
        return resultado;
    }

}
