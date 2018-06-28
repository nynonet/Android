package fasb.edu.br.crudcliente.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fasb.edu.br.crudcliente.modal.Cliente;

public class ClienteDAO {
    private String TABELA = "Clientes";
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

        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put( "uf", c.getUf() );
        cv.put( "status", (c.isStatus()? 1 : 0) );

        return gw.getDatabase().update(TABELA, cv, "ID=?", new String[]{c.getId()+""}) > 0;
    }

    public boolean Delete(Cliente c) {
        return gw.getDatabase().delete(TABELA, "ID=?", new String[]{c.getId()+""}) > 0;
    }

    public Cliente getCliente(int id) {

        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes WHERE id=?", new String[]{id+""} );

        Cliente c = new Cliente();

        while (cursor.moveToNext()) {
            c.setId(id);
            c.setNome( cursor.getString( cursor.getColumnIndex("nome")) );
            c.setUf( cursor.getString( cursor.getColumnIndex("uf") ));
            c.setStatus( ( cursor.getInt(cursor.getColumnIndex("status")) )>0 );
        }
        cursor.close();
        return c;
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
