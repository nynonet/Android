package fasb.edu.br.crudcliente.modal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class dbGateway {

    private static dbGateway gw;
    private SQLiteDatabase db;

    private dbGateway(Context context) {
        MyDataBase my = new MyDataBase(context);
        db = my.getWritableDatabase();
    }

    public static dbGateway getInstace(Context context){
        if (gw == null) {
            gw = new dbGateway(context);
        }
        return gw;
    }

    public SQLiteDatabase getDatabase() {
        return this.db;
    }
}
